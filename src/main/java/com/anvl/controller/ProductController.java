/**
 * 
 */
package com.anvl.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anvl.model.CartRequest;
import com.anvl.model.CartResponse;

/**
 * @author Vaibhav
 *
 */
@RestController
@RequestMapping("/v1/products")
public class ProductController {

	@PostMapping
	public CartResponse addToCart(CartRequest cartRequest) {
		return CartResponse.failure(0);
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
