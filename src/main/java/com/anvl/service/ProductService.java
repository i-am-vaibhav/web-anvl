/**
 * 
 */
package com.anvl.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.anvl.entities.Product;
import com.anvl.repos.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Vaibhav
 *
 */
@Service
public class ProductService {

	private static final String PRODUCTS_PATH = "./src/main/resources/static/products/products.json";

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private ProductRepository repository;

	private static final Logger log = LoggerFactory.getLogger(ProductService.class);

	public Page<Product> getProducts(int count) {
		log.debug("getProducts started");
		PageRequest pageRequest = PageRequest.of(count, 8);
		Page<Product> products = repository.findAll(pageRequest);
		try {
			if (products.isEmpty()) {
				List<Product> productsList = null;
				TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>() {
				};
				InputStream inputStream = new FileInputStream(PRODUCTS_PATH);
				productsList = mapper.readValue(inputStream, typeReference);
				repository.saveAll(productsList);
				products = repository.findAll(pageRequest);
			}
		} catch (Exception e) {
			log.error("getProducts exception :: {} ", e);
			return Page.empty(pageRequest);
		}
		log.debug("getProducts ended");
		return products;
	}

}
