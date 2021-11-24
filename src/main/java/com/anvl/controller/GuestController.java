package com.anvl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guest")
public class GuestController {

	@GetMapping
	public String getGuestPage(Model model) {
		model.addAttribute("login", true);
		return "guest/home";
	}

	@GetMapping(value = "/login")
	public String getLoginPageContent(Model model) {
		return "guest/login";
	}

}
