var SELECTOR_PRODUCT_NAME = ".product-info-heading";
var SELECTOR_PRODUCT_DATA_SECTION = ".product-info";
var SELECTOR_PRODUCT_IMAGE_SECTION = ".responsive-product-image-panel";

$(function() {
	enquire.register("only screen and (max-width: 767px)", {
		
		setup : function() {			
	    },
		
	    match : function() {
	    	$(SELECTOR_PRODUCT_IMAGE_SECTION).before($(SELECTOR_PRODUCT_NAME));
	    },  
	    
	    unmatch : function() {
	    	$(SELECTOR_PRODUCT_DATA_SECTION).prepend($(SELECTOR_PRODUCT_NAME));
	    }
	});
});