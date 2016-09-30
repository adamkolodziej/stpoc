$(document).ready(function() {
	"use strict";
	
	var setActionUrl = ACC.config.contextPath + "/tvseasonlistcomponent/season",
		seasonSelector = $('.season-selector'),
		episodesList = $('.episodes-list');
	
	seasonSelector.change(function() {
		$.post(setActionUrl, {
			seasonCode: seasonSelector.val()
		}, function (result) {
			episodesList.html(result);
			ACC.instatntCheckout.checkWatchOrBuy();
		});
	});
	
});
