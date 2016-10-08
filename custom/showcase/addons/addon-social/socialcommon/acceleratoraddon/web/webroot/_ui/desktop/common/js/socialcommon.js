
$(document).ready(function() {
	
	/** video js **/
	ACC.Player.resize();
	window.onresize = function () {ACC.Player.resize(); }
	
});

ACC.Player = {
	bindEvents: function () {
		/** BIND VIDEO EVENT HANDELRS HERE **/
		$(this).on("loadedmetadata", function() {
			if(!$(this).hasOwnProperty('poster') || $(this).attr('poster') != '')
			{
				generatePoster(this);
			}
		});
		ACC.Player.autoplay(this);
	},
	resize: function () {
		$(".video-js").each( function () {
			var width = $(this).parent().width();
			var aspectRatio = 9/16;
			_V_(this).ready(function () {
				this.width(width).height( width * aspectRatio );
			}).ready(function () {
				$("video").each(ACC.Player.bindEvents);});
	  });
	},
	autoplay: function (video) {
		if($(video).data('autoplay') == true)
		{
			setTimeout(function () {
				if(document.contains(video))
				{
					if(video.currentTime <= 0 || !video.paused)	 {
						video.play();
					}
				}
			}, 5000);
		}
	}
};