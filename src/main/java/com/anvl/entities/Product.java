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
@Table(name = "TBL_PRODUCT")
public class Product {

	@Id
	@GeneratedValue
	private BigDecimal id;
	private String title;
	private String type;
	private String description;
	private String filename;
	private String height;
	private String width;
	private BigDecimal price;
	private String rating;

}
