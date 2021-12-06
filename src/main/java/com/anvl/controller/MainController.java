/**
 * 
 */
package com.anvl.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.anvl.entities.Product;
import com.anvl.service.ProductService;

/**
 * @author Vaibhav
 *
 */
@Controller
public class MainController {

	@Autowired
	private ProductService service;

	@ModelAttribute("user")
	public Principal getUser(Principal principal) {
		return principal;
	}

	@GetMapping("/home")
	public String getHomePage(Model model) {
		model.addAttribute("products", service.getProductCategories());
		return "main/home";
	}

	@GetMapping("/v1/products/{page}/page")
	public String getProducts(Model model, @PathVariable Integer page) {
		Page<Product> productsByType = service.getProducts(page);
		model.addAttribute("products", productsByType);
		int totalPages = productsByType.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "product/list";
	}

	@GetMapping("/v1/products/type/{type}/page/{page}")
	public String getProductsByType(Model model, @PathVariable String type, @PathVariable Integer page) {
		model.addAttribute("type", type);
		Page<Product> productsByType = service.getProductsByType(type, page);
		model.addAttribute("products", productsByType);
		int totalPages = productsByType.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "product/list";
	}

}
