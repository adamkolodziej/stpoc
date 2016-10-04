// Product Set Carousel
// @author = Dariusz Małachowski

function productSetCarousel(options) {
	
	var defaults = {			
		rootContainerId: "#",
		vertical: false,
		buttonNextHTML: null,
		buttonPrevHTML: null,
		itemFallbackDimension: 150
	};
	
	var settings = $.extend( true, {}, defaults, options );
	var rootContainerId = getRootId();
	
	jQuery(rootContainerId + ' .product_set_list').jcarousel({
    	vertical: settings.vertical,
    	buttonNextHTML: settings.buttonNextHTML,
    	buttonPrevHTML: settings.buttonPrevHTML,
    	initCallback: initCallbacks
    });
	
	
	function initCallbacks(carousel) {
		var scroll_size = carousel.options.size;
		
		
		jQuery(rootContainerId + ' .productset-nav-next').bind('click', function() {
	        carousel.next();
	        var index = carousel.index(carousel.last, scroll_size);
	        jQuery(rootContainerId + ' .scroll-step').html(index);
	        setButtonUrl(index);
	        return false;
	    });

	    jQuery(rootContainerId + ' .productset-nav-prev').bind('click', function() {
	        carousel.prev();
	        var index = carousel.index(carousel.last, scroll_size);
	        jQuery(rootContainerId + ' .scroll-step').html(index);
	        setButtonUrl(index);
	        return false;
	    });
	    
	    setButtonUrl(1);
	    
	    jQuery(rootContainerId + ' .scroll-size').html(scroll_size);
		
		
	}
	
	function getRootId() {
		return "#" + settings.rootContainerId;
	}
	
	function setButtonUrl(index) {
		var currentUrl = $('#current-set-url-'+index).val();
		$(rootContainerId + ' .buyTheSetButton').attr('href',currentUrl);
	}
	
};
