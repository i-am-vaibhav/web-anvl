/**
 * 
 */
package com.anvl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author DELL
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Product {

	@NonNull
	private String title;
	@NonNull
	private Long id;
	private String type;
	private String description;
	private String filename;
	private String height;
	private String width;
	private String price;
	private String rating;

}
