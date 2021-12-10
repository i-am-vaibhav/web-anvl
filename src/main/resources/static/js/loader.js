function hideLoader() {
	setTimeout(function() {
		$('#loading').fadeOut();
	}, 1000);
}


$(window).ready(hideLoader);

function showLoader(){
	$('#loading').fadeIn();
}

// Strongly recommended: Hide loader after 20 seconds, even if the page hasn't
// finished loading
setTimeout(hideLoader, 20 * 1000);