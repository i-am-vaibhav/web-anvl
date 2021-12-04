/**
 * 
 */
package com.anvl.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

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
		model.addAttribute("products", service.getProducts(0));
		return "main/home";
	}
	
	@GetMapping("/{page}/page")
	public String getProducts(Model model,@PathVariable Integer page) {
		model.addAttribute("products", service.getProducts(page));
		return "product/list";
	}

}
