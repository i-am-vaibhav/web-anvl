/**
 * 
 */
package com.anvl.model;

import lombok.Data;

/**
 * @author DELL
 *
 */
@Data
public class CartResponse {

	private long prodCount;
	private String status;

	public static CartResponse success(long count) {
		CartResponse cartResponse = new CartResponse();
		cartResponse.status = "SUCCESS";
		cartResponse.prodCount = count;
		return cartResponse;
	}

	public static CartResponse failure(long count) {
		CartResponse cartResponse = new CartResponse();
		cartResponse.status = "FAILURE";
		cartResponse.prodCount = count;
		return cartResponse;
	}

}
