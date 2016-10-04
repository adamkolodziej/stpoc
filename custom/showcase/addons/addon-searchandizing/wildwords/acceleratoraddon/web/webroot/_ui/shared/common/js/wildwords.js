(function($) {
	if(typeof LiveEditAction == "undefined") {
	    LiveEditAction = function() {};
	}	
	
	LiveEditAction.EDIT_SEARCH = function(componentData, menuItemData) {
		
		if (parent.notifyIframeZkComponent) {
			parent.notifyIframeAboutWildWordsEdit(ACC.previewCurrentLanguage);
		}
	};
})(jQuery);

