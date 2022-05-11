/**
 * 
 */
package com.anvl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anvl.model.CartRequest;
import com.anvl.model.CartResponse;
import com.anvl.service.CartService;

/**
 * @author Vaibhav
 *
 */
@RestController
@Secured(value = { "ROLE_USER", "ROLE_ADMIN" })
@RequestMapping("/v1/products")
public class ProductController {

	@Autowired
	private CartService cartService;

	@PostMapping
	public CartResponse addToCart(CartRequest cartRequest) {
		return cartService.add(cartRequest);
	}

	@DeleteMapping
	public CartResponse removeFromCart(CartRequest cartRequest) {
		return CartResponse.failure(0);
	}

	@DeleteMapping("/all")
	public CartResponse removeAllFromCart(CartRequest cartRequest) {
		return CartResponse.failure(0);
	}

	@PostMapping("/proceed")
	public CartResponse proceedCart(CartRequest cartRequest) {
		return CartResponse.failure(0);
	}

}
