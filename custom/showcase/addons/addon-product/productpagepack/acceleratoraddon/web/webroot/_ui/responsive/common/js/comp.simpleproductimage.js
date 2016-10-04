var SELECTOR_PRODUCT_CAROUSEL = ".responsive-product-image-carousel";

var SELECTOR_CAROUSEL_CONTAINER = ".thumbnails";
var SELECTOR_PRIMARY_IMAGE_CONTAINER = ".detail-image";

var CSS_ORIENTATION_H = "horizontal";
var CSS_ORIENTATION_V = "vertical";

var SELECTOR_THUMBNAIL_ITEM = ".responsive-product-image-carousel a.thumb";
var SELECTOR_PRIMARY_IMAGE = "#primary_image_link .responsive-image-container";

$(function() {
	enquire.register("only screen and (max-width: 767px)", {
		
		setup : function() {			
	    },
		
	    match : function() {
	    	$productCarousel = $(SELECTOR_PRODUCT_CAROUSEL);
	    	$productCarousel.removeClass(CSS_ORIENTATION_V);
	    	$productCarousel.addClass(CSS_ORIENTATION_H);
	    	
	    	$(SELECTOR_PRIMARY_IMAGE_CONTAINER).after($(SELECTOR_CAROUSEL_CONTAINER));
	    	
	    },  
	    
	    unmatch : function() {
	    	$productCarousel = $(SELECTOR_PRODUCT_CAROUSEL);
	    	$productCarousel.removeClass(CSS_ORIENTATION_H);
	    	$productCarousel.addClass(CSS_ORIENTATION_V);
	    	
	    	$(SELECTOR_PRIMARY_IMAGE_CONTAINER).before($(SELECTOR_CAROUSEL_CONTAINER));
	    }
	});

    $(SELECTOR_THUMBNAIL_ITEM).click(function (event) {
        event.preventDefault();
        $(SELECTOR_PRIMARY_IMAGE).html($(this).html());
    });
});

$(document).ready(function() {
	$(".modal-zoom").colorbox({
		onComplete: function() {
		    ACC.common.refreshScreenReaderBuffer();
		},
		onClosed: function() {
			ACC.common.refreshScreenReaderBuffer();
		}
	});
});