(function($) {
	if(typeof LiveEditAction == "undefined") {
	    LiveEditAction = function() {};
	}
	
	LiveEditAction.MANAGE_PROMOTION_BANNER_IMAGE = function(componentData, menuItemData) {
        var cmsComponentUid = componentData.data.componentUid;
        var position = componentData.data.position;
        if (undefined != cmsComponentUid && cmsComponentUid != "" && parent.notifyIframeZkComponent) {
            parent.managePromotionBannerImage(cmsComponentUid,position,ACC.serverPath);
        }
    }
    
    LiveEditAction.EDIT_CAMPAIGN = function(componentData, menuItemData) {
        var cmsComponentUid = componentData.data.componentUid;
        var position = componentData.data.position;
        if (undefined != cmsComponentUid && cmsComponentUid != "" && parent.notifyIframeZkComponent) {
            parent.editCampaign(cmsComponentUid,position,ACC.serverPath);
        }
    }
    
    LiveEditAction.ADDTO_CAMPAIGN = function(componentData, menuItemData) {
        var cmsComponentUid = componentData.data.componentUid;
        var position = componentData.data.position;
        if (undefined != cmsComponentUid && cmsComponentUid != "" && parent.notifyIframeZkComponent) {
            parent.addToCampaign(cmsComponentUid,position,ACC.serverPath);
        }
    }
    
    LiveEditAction.ADD_CONTAINER_TO_CAMPAIGN = function(componentData, menuItemData) {
        var cmsComponentUid = componentData.data.componentUid;
        var position = componentData.data.position;
        if (undefined != cmsComponentUid && cmsComponentUid != "" && parent.notifyIframeZkComponent) {
            parent.addContainerToCampaign(cmsComponentUid,position,ACC.serverPath);
        }
    }
})(jQuery);
