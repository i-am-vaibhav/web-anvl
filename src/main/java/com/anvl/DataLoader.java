/**
 * 
 */
package com.anvl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.anvl.entities.Product;
import com.anvl.repos.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author DELL
 *
 */
@Component
public class DataLoader implements CommandLineRunner {

	private static final String PRODUCTS_PATH = "./src/main/resources/static/products/products.json";

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private ProductRepository repository;

	@Override
	public void run(String... args) throws Exception {
		Long products = repository.count();
		try {
			if (products == 0) {
				List<Product> productsList = null;
				TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>() {
				};
				InputStream inputStream = new FileInputStream(PRODUCTS_PATH);
				productsList = mapper.readValue(inputStream, typeReference);
				repository.saveAll(productsList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
