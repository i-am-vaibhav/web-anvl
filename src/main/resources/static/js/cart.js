async function openCart() {
	let cart = [];
	try {
		cart = JSON.parse(sessionStorage.getItem("cart"));
		if (cart == null) {
			cart = [];
		}
	} catch (e) {
		cart = [];
	}
	sessionStorage.clear();
	let ids = [];
	for (var i = 0; i < cart.length; i++) {
		ids.push(cart[i].id);
	}
	$.post({
		url : "/webanvl/v1/product/cart?cids=" + ids,
		beforeSend : beforeSendHandler,
		success : function() {
			window.location.href = "/webanvl/v1/product/cart"
		}
	});
}