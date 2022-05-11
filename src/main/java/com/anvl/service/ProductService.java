/**
 * 
 */
package com.anvl.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.anvl.entities.Product;
import com.anvl.repos.ProductRepository;

/**
 * @author Vaibhav
 *
 */
@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	private static final Logger log = LoggerFactory.getLogger(ProductService.class);

	public Page<Product> getProducts(int count) {
		log.debug("getProducts started");
		PageRequest pageRequest = PageRequest.of(count, 8);
		Page<Product> products = null;
		try {
			products = repository.findAll(pageRequest);
		} catch (Exception e) {
			log.error("getProducts exception :: {} ", e);
			return Page.empty(pageRequest);
		}
		log.debug("getProducts ended");
		return products;
	}

	public List<com.anvl.model.Product> getProductCategories() {
		return repository.groupByType();
	}

	public Page<Product> getProductsByType(String type, int page) {
		return repository.findByType(type, PageRequest.of(page, 8));
	}

}
