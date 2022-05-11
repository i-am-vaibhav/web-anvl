package com.anvl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anvl.service.ProductService;

@Controller
@RequestMapping("/guest")
public class GuestController {

	@Autowired
	private ProductService service;

	@GetMapping
	public String getGuestPage(Model model) {
		model.addAttribute("login", true);
		model.addAttribute("products", service.getProducts(0));
		return "guest/home";
	}

	@GetMapping(value = "/login")
	public String getLoginPage(Model model) {
		return "guest/login";
	}

}
