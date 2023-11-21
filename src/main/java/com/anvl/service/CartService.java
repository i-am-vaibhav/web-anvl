/**
 * 
 */
package com.anvl.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.anvl.entities.Cart;
import com.anvl.entities.Product;
import com.anvl.model.CartItem;
import com.anvl.model.CartRequest;
import com.anvl.model.CartResponse;
import com.anvl.repos.CartRepository;
import com.anvl.repos.ProductRepository;

/**
 * @author vaibhav
 *
 */
@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductRepository productRepository;

	public CartResponse add(CartRequest request) {
		Cart cart = new Cart();
		BigDecimal id = request.getId();
		long countByUsername = cartRepository.countByUsername(request.getUsername());
		CartResponse response = CartResponse.failure(countByUsername);
		Optional<Product> findById = productRepository.findById(id);
		if (findById.isPresent()) {
			Product product = findById.get();
			cart.setProduct_id(id);
			cart.setProductName(product.getTitle());
			cart.setUsername(request.getUsername());
			cart.setPrice(product.getPrice());
			cart.setImg(product.getFilename());
			cartRepository.save(cart);
			response = CartResponse.success(countByUsername + 1);
		}
		return response;
	}

	public long getCountOfProduct(String username) {
		return cartRepository.countByUsername(username);
	}

	public Pair<List<CartItem>, BigDecimal> getCart(String username) {
		List<CartItem> cartByUsername = cartRepository.getCartByUsername(username);
		return Pair.of(cartByUsername,
				cartByUsername.stream().map(cart -> cart.getPrice()).reduce(BigDecimal.ZERO, (b, big) -> big.add(b)));
	}

	public void addById(List<String> ids) {
		for (String string : ids) {
			BigDecimal id = new BigDecimal(string);
			Optional<Product> findById = productRepository.findById(id);
			if (findById.isPresent()) {
				Product product = findById.get();
				Cart cart = new Cart();
				cart.setProduct_id(id);
				cart.setProductName(product.getTitle());
				cart.setUsername(getAuthentication().getName());
				cart.setPrice(product.getPrice());
				cart.setImg(product.getFilename());
				cartRepository.save(cart);
			}
		}
	}

	private Authentication getAuthentication() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication;
	}

}
