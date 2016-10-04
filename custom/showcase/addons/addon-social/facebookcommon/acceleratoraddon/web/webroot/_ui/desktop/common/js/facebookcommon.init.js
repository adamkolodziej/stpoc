ACC.facebookcommon = {
	appendNamespaces : function()
	{
		$("html").attr("xmlns", "http://www.w3.org/1999/xhtml");
		$("html").attr("xmlns:og","http://ogp.me/ns#");
		$("html").attr("xmlns:fb","http://ogp.me/ns/fb#");
	},
	appendFbRoot : function()
	{
		$('body').prepend('<div id="fb-root"></div>');
	}
};


$(document).ready(function()
{
	ACC.facebookcommon.appendNamespaces();
	ACC.facebookcommon.appendFbRoot();

	window.fbAsyncInit = function() {
		var channel = self.location.host + ACC.config.contextPath + "/facebook/channel";
		FB.init({appId: ACC.facebookApplicationId,
			channelURL :  channel,
			status: true,
			cookie: true,
			oauth: true,
			xfbml: true,
			version: 'v2.0'
		});
	};

	// Load the SDK Asynchronously
	(function(d){
		var js, id = 'facebook-jssdk'; if (d.getElementById(id)) {return;}
		js = d.createElement('script'); js.id = id; js.async = true;
		js.src = "//connect.facebook.net/" + ACC.facebookLocale + "/sdk.js";
		d.getElementsByTagName('head')[0].appendChild(js);
	}(document));

});


