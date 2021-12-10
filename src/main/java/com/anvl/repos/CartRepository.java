/**
 * 
 */
package com.anvl.repos;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anvl.entities.Cart;
import com.anvl.model.CartItem;

/**
 * @author Vaibhav
 *
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, BigDecimal> {

	long countByUsername(String username);
	
	@Query("select new com.anvl.model.CartItem(max(c.productName) as name,sum(c.price) as price,count(*) as count,c.product_id as pid) from Cart c where c.username = :username group by c.product_id")
	List<CartItem> getCartByUsername(@Param("username") String username);
	
}
