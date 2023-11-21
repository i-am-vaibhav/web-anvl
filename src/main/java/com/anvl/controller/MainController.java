/**
 * 
 */
package com.anvl.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.anvl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anvl.entities.Product;
import com.anvl.model.CartItem;
import com.anvl.service.CartService;
import com.anvl.service.ProductService;

/**
 * @author Vaibhav
 *
 */
@Controller
public class MainController {

	private final ProductService productService;
	private final CartService cartService;
	private final UserService userService;

	public MainController(ProductService productService, CartService cartService, UserService userService) {
		this.productService = productService;
		this.cartService = cartService;
		this.userService = userService;
	}

	@ModelAttribute("user")
	public Principal getUser(Principal principal) {
		return principal;
	}

	@GetMapping("/home")
	public String getHomePage(Model model) {
		model.addAttribute("cartcount", cartService.getCountOfProduct(getAuthentication().getName()));
		model.addAttribute("usercount", userService.getUserCount());
		model.addAttribute("products", productService.getProductCategories());
		return "main/home";
	}

	@Secured(value = { "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping("/v1/products/{page}/page")
	public String getProducts(Model model, @PathVariable Integer page) {
		Page<Product> productsByType = productService.getProducts(page);
		model.addAttribute("products", productsByType);
		Authentication authentication = getAuthentication();
		int totalPages = productsByType.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("cartcount", cartService.getCountOfProduct(authentication.getName()));
		return "product/list";
	}

	@Secured(value = { "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping("/v1/products/type/{type}/page/{page}")
	public String getProductsByType(Model model, @PathVariable String type, @PathVariable Integer page) {
		model.addAttribute("type", type);
		Authentication authentication = getAuthentication();
		Page<Product> productsByType = productService.getProductsByType(type, page);
		model.addAttribute("products", productsByType);
		int totalPages = productsByType.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(0, totalPages - 1).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("cartcount", cartService.getCountOfProduct(authentication.getName()));
		return "product/list";
	}

	@PostMapping("/v1/product/cart")
	@ResponseBody
	public void cartPage(@RequestParam(required = false) List<String> cids, Model model) {
		if (!CollectionUtils.isEmpty(cids))
			cartService.addById(cids);
	}

	@GetMapping("/v1/product/cart")
	public String cartPage(Model model) {
		Authentication authentication = getAuthentication();
		if (authentication == null) {
			return "redirect:/guest/login";
		}
		Pair<List<CartItem>, BigDecimal> cart = cartService.getCart(authentication.getName());
		model.addAttribute("cartcount", cartService.getCountOfProduct(authentication.getName()));
		model.addAttribute("cartProducts", cart.getFirst());
		model.addAttribute("cartAmount", cart.getSecond());
		return "product/cart";
	}

	private Authentication getAuthentication() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication;
	}

}
