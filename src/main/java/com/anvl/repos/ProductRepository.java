package com.anvl.repos;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.anvl.entities.Product;

/**
 * @author vaibhav
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, BigDecimal> {

	@Query("select new com.anvl.model.Product(p.type as type, count(p.id) as id ) from Product p group by p.type")
	List<com.anvl.model.Product> groupByType();

	Page<Product> findByType(String type, Pageable pageable);

}
