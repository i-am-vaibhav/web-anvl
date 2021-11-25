/**
 * 
 */
package com.anvl.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author Vaibhav
 *
 */
@Controller
public class MainController {

	@ModelAttribute("user")
	public Principal getUser(Principal principal) {
		return principal;
	}
	
	@GetMapping("/home")
	public String getHomePage(Model model) {
		return "main/home";
	}

}
