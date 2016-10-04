/*
 * @author mgolubovic
 */
function generatePoster(video)
{
	var canvas = document.createElement("canvas");
	var tmpVideo = document.createElement("video");
	tmpVideo.src = video.src;
	tmpVideo.addEventListener('loadedmetadata', function() {
		canvas.width = tmpVideo.videoWidth;
	    canvas.height = tmpVideo.videoHeight;
	    var context = canvas.getContext("2d");
	    tmpVideo.currentTime = (tmpVideo.duration / 2).toFixed(2);
	    tmpVideo.addEventListener('canplay', function() {
		    context.drawImage( tmpVideo, 0, 0, tmpVideo.videoWidth, tmpVideo.videoHeight );
		    var dataUrl = canvas.toDataURL('image/jpeg');
		    $(video).siblings( ".vjs-poster" ).css("display", "block").css("background-image", 'url(' + dataUrl + ')');
	    });
	}, false);
}