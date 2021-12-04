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

});

//$.getJSON("https://jsonip.com?callback=webanvl", function(data) {
//	alert("Your IP address is :- " + data.ip);
//});