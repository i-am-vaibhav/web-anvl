package com.anvl.repos;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anvl.entities.Product;

public interface ProductRepository extends JpaRepository<Product, BigDecimal> {

}
