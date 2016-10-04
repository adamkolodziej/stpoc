//follow me - load your price
function fmLoadYourPrice() {
	$( "div.spotlight" ).each(function( index ) {

		var parentObj = $(this);
		var priceObj = parentObj.find("p.price");
		if (priceObj.children().length > 0) {
			//test
		} else {
			var aObj = parentObj.find("a");
			var aHref = aObj.attr('href');
			if (aHref != null && aHref.length > 0) {
				var code = aHref.substring(aHref.lastIndexOf("/") + 1);
				getPrice(code, priceObj);
			}
		}
	});
}
//get price for single product
function getPrice(code, priceObj) {
	$.ajax({
		url: (ACC.getYourPriceUrl + '?code=' + code), 
		global: false, 
		cache: false,
		success:
		function(data) {
			var priceStr = data;
			if (priceObj.text().trim() != priceStr.trim()) {
				priceObj.html("<span class=\"oldPrice\">" + priceObj.text() + "</span>" + 
					"<br/><span class=\"newPrice\">" + priceStr + "</span>");
			} 
		}
	});
}

// should add condition to load it only for b2b

if (ACC.anonymousUser == 'false' && ACC.isListerPage == 'true') {
    fmLoadYourPrice();
}