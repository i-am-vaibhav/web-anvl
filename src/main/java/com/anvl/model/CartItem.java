package com.anvl.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {

	private String name;
	private BigDecimal price;
	private Long count;
	private BigDecimal pid;
	private String img;

	public CartItem(String name, BigDecimal price, Long count, BigDecimal pid, String img) {
		super();
		this.name = name;
		this.price = price;
		this.count = count;
		this.pid = pid;
		this.img = img;
	}

}
