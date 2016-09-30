ACC.InstantCheckout = {

	bindAll: function() {
		ACC.InstantCheckout.bind();
	},
	
	bind: function(parent) {
		var instantcheckoutButtons= $('.instantcheckout-button');
		instantcheckoutButtons.click(ACC.InstantCheckout.instantcheckoutButtonClick);
	},
	
	instantcheckoutButtonClick: function() {
		var instantCheckoutUrl = $(this).attr('href');
		var $anchor = $(this);

		$.getJSON(instantCheckoutUrl, function(response) {
		    ACC.headerMenu.loadRemainingLoyaltyPoints();
            var responseDiv = $anchor.data('responseDiv');
            if( responseDiv == undefined || responseDiv == "") {
                $anchor.next(".instantcheckout-message").html(response.responseHtml);
            } else {
                $('#'+responseDiv).html(response.responseHtml);
            }
            
            if (response.status === "success") {
            	var $callbackFn = $anchor.attr("data-success-callback-fn");
            	if ($callbackFn) {
            		var $paramsFn = $anchor.attr("data-params-fn");
            		eval($callbackFn)($paramsFn);
            	}
            }
		});
		$anchor.addClass('disabled');

		return false;
	}
};

$(document).ready(ACC.InstantCheckout.bindAll);