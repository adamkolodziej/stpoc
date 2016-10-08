(function($) {
	if(typeof LiveEditAction == "undefined") {
	    LiveEditAction = function() {};
	}
	
	LiveEditAction.EDIT_SET = LiveEditAction._edit_item;
	LiveEditAction.EDIT_PRODUCT_SETS = function(componentData, menuItemData) {	
		if (parent.notifyIframeEditProductSets) {
			parent.notifyIframeEditProductSets();
		}
	}
})(jQuery);

