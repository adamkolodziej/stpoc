$(document).ready(function() {
    $( ".date-picker" ).datepicker({
    	onSelect: function(datetext){
            $('.date-picker').val(formatDate(new Date(datetext)));
    	}
    });
    
    // convert millis to javascript Date
    var displayedDate = new Date(+$( ".date-picker" ).val());
    
    function pad(n) { 
    	return n < 10 ? '0' + n : n;
    }
    
    //update date input field
    $( ".date-picker" ).val(formatDate(displayedDate));
    
    var setTimeActionUrl = ACC.config.contextPath + "/facebookmock/session/time";
    
    // send selected date (in millis) to server 
    $('.set-time-button').click(function(event) {  
        var selectedDateMillis = new Date($('.date-picker').val()).getTime();
     	$.post(setTimeActionUrl, {currentTime:selectedDateMillis}, function() {
     		$(".change-status").show();
     		setTimeout( "$('.change-status').hide();", 1000);
     	});
    });
    
    // send current date (in millis to server)
    $('.reset-time-button').click(function(event) {  
    	var clientDateMillis = new Date().getTime();
     	$.post(setTimeActionUrl, {currentTime:clientDateMillis}, function() {
     		$(".change-status").show();
     		setTimeout( "$('.change-status').hide();", 1000);
     	});
     	$( ".date-picker" ).val(formatDate(new Date(clientDateMillis)));
    });
    
    function formatDate(date) {
    	return date.getFullYear() + "/"
		+ pad(date.getMonth() + 1) + "/"
		+ pad(date.getDate()) + " "
		+ pad(date.getHours()) + ":"
		+ pad(date.getMinutes());
    }


    
});