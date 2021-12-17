/**
 * 
 */
$(document).ready(function() {

	$(".more").click(function() {
		let text = $(this).text();
		if (text == "more") {
			$(this).text("less");
			$(this).parent().prev().toggleClass("text-truncate");
		} else {
			$(this).text("more");
			$(this).parent().prev().toggleClass("text-truncate");
		}
	});

	let cart = [];
	try {
		cart = JSON.parse(sessionStorage.getItem("cart"));
		if (cart == null) {
			cart = [];
		}
	} catch (e) {
		cart = [];
	}
	if (cart) {
		$("#gcart-count").text(cart.length);
		let val = $("#cart-count").text();
		if (val == '0') {
			$("#cart-count").text(cart.length);
		}
	}

});

async function addCartToCacheStorage(id) {
	let cart = [];
	try {
		cart = JSON.parse(sessionStorage.getItem("cart"));
		if (cart == null) {
			cart = [];
		}
	} catch (e) {
		cart = [];
	}
	let data = { id: id, operation: 'ADD' };
	if (cart != null) {
		cart.push(data);
		sessionStorage.cart = JSON.stringify(cart);
		$("#gcart-count").text(cart.length);
	}
}

async function addToCart(id) {
	$.post({
		url: "/webanvl/v1/products",
		data: { id: id, operation: "ADD", username: $("#username").text() },
		beforeSend: beforeSendHandler,
		success: function(response) {
			$("#cart-count").text(response.prodCount);
		}
	});
}

let beforeSendHandler = function(xhr) {
	let header = $("#header").attr("content");
	let token = $("#token").attr("content");
	xhr.setRequestHeader(header, token)
}