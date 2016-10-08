
ACC.opengraphmine = {
		like: function (accessToken, url)
		{		
			 jQuery.ajax({
				url: ACC.config.contextPath + "/facebook/like?accesstoken=" + accessToken  + "&url=" + url,
				cache: true,
				success: function(result){
				}
			});				  
		},
		
		unlike: function (accessToken, url)
		{		
			 jQuery.ajax({
				url: ACC.config.contextPath + "/facebook/unlike?accesstoken=" + accessToken  + "&url=" + url,
				cache: true,
				success: function(result){
				}
			});				  
		}
};

$(document).ready(function()
{
	var chainFbAsyncInit = window.fbAsyncInit; // chain the Async Init from facebookcommon
 	window.fbAsyncInit = function() {
 	    if(typeof chainFbAsyncInit === 'function'){
 	    	chainFbAsyncInit();
 	    }
 	    
 		FB.Event.subscribe('edge.create',
			function(response) {
 			
 			    var authResponse = FB.getAuthResponse();
 			    
 			    if (undefined != authResponse)
 			    {
 			    	var accessToken = authResponse.accessToken;
 			    	ACC.opengraphmine.like(accessToken, response);
 			    }
		    }
 		);
 		
 		FB.Event.subscribe('edge.remove',
 			function(response) {
 			
			    var authResponse = FB.getAuthResponse();
			    
			    if (undefined != authResponse)
			    {
			    	var accessToken = authResponse.accessToken;
			    	ACC.opengraphmine.unlike(accessToken, response);
			    }
 			}
 	 	);
 		
 	};
});


function getAllFacebookSuggestedProducts() {
	jQuery('.facebookSuggestedProductsContainer').each(function () {
		var componentId = jQuery(this).attr('data-componentid');
		if (componentId) {
			getFacebookSuggestedProducts(componentId);
		}
	});
}

function getFacebookSuggestedProducts(componentId) {
	jQuery.ajax({
		url:   ACC.config.contextPath + '/FacebookSuggestedProductCarouselPageController/ajax/' + componentId,
		cache: true
	}).done(function(data) {
		var selector = '.facebookSuggestedProductsContainer[data-componentid="' + componentId + '"]';
		jQuery(selector).html(data);
		var selector = selector + ' .jcarousel-skin';
		jQuery('.span-4 ' + selector).jcarousel({
			vertical: true
		});
		jQuery('.span-10 ' + selector + ', .span-24 ' + selector + ', .span-20 ' + selector + ', .span-18 ' + selector).each(function() {
			if (!jQuery(this).parents().hasClass('span-4')) {
				jQuery(this).jcarousel({});
			}
		});
	});
}

function initFacebookLogin() {
	jQuery(".facebook-connect-btn").click(function(event) {
		event.preventDefault();
		var scope = jQuery(this).attr('data-scope');
		jQuery.ajax({
			type: "POST",
			data: {scope: scope},
			url: ACC.config.contextPath +"/facebook/connect"
		}).done(function( url ) {
			var w = 800;
			var h = 600;
			var left = (screen.width/2)-(w/2);
			var top = (screen.height/2)-(h/2);
			return window.open(url, 'Facebook Login', 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
		});
	});
	
	jQuery(".facebook-disconnect-btn").click(function(event) {
		event.preventDefault();
		
		jQuery.ajax({
			type: "POST",
			data: {},
			url: ACC.config.contextPath +"/facebook/disconnect",
			dataType: "html"
		}).done(function() {
			window.location.reload();
		});
		
	});
}

jQuery(window).load(function(){
	getAllFacebookSuggestedProducts();
	initFacebookLogin();
});
