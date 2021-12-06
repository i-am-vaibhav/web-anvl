/**
 * 
 */
package com.anvl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DELL
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	private long id;
	private String title;
	private String type;
	private String description;
	private String filename;
	private String height;
	private String width;
	private String price;
	private String rating;

	public Product(String type, long id) {
		super();
		this.type = type;
		this.id = id;
	}

}
