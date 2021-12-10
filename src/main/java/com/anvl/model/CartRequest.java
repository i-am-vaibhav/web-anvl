package com.anvl.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartRequest {

	private BigDecimal id;
	private String username;
	private String operation; // ADD, REMOVE, REMOVE_ALL

	public static CartRequest add(BigDecimal id) {
		CartRequest request = new CartRequest();
		request.id = id;
		request.operation = "ADD";
		return request;
	}

	public static CartRequest remove(BigDecimal id) {
		CartRequest request = new CartRequest();
		request.id = id;
		request.operation = "REMOVE";
		return request;
	}

	public static CartRequest removeAll() {
		CartRequest request = new CartRequest();
		request.operation = "REMOVE_ALL";
		return request;
	}

}
