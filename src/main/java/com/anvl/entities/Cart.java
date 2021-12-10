/**
 * 
 */
package com.anvl.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author DELL
 *
 */
@Data
@Entity
@Table(name = "tbl_cart")
public class Cart {

	@Id
	@GeneratedValue
	private BigDecimal id;

	private String username;
	
	private String productName;

	private BigDecimal product_id;

	private BigDecimal price;

}
