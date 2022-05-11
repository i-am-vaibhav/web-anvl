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
		$("#gcart-count").addClass("animated rubberBand")
		let val = $("#cart-count").text();
		if (val == '0') {
			$("#cart-count").text(cart.length);
			$("#cart-count").addClass("animated rubberBand")
			setTimeout(function() {
				$("#cart-count").removeClass("animated rubberBand");
			}, 500);

		}
		setTimeout(function() {
			$("#gcart-count").removeClass("animated rubberBand");
		}, 500);
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
		$("#gcart-count").addClass("animated rubberBand");
		setTimeout(function() {
			$("#gcart-count").removeClass("animated rubberBand");
		}, 500);
	}
}

async function addToCart(id) {
	$.post({
		url: "/webanvl/v1/products",
		data: { id: id, operation: "ADD", username: $("#username").text() },
		beforeSend: beforeSendHandler,
		success: function(response) {
			$("#cart-count").text(response.prodCount);
			$("#cart-count").addClass("animated rubberBand");
			setTimeout(function() {
				$("#cart-count").removeClass("animated rubberBand");
			}, 500);
		}
	});
}

let beforeSendHandler = function(xhr) {
	let header = $("#header").attr("content");
	let token = $("#token").attr("content");
	xhr.setRequestHeader(header, token)
}