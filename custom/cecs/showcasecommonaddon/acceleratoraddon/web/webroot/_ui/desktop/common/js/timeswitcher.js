TimeSwitcher = {
    pad: function (n) {
        return n < 10 ? '0' + n : n;
    },
    formatDate: function (date) {
        return date.getFullYear() + "/"
            + TimeSwitcher.pad(date.getMonth() + 1) + "/"
            + TimeSwitcher.pad(date.getDate()) + " "
            + TimeSwitcher.pad(date.getHours()) + ":"
            + TimeSwitcher.pad(date.getMinutes());
    },
    toServerTime: function (timestamp) {
        return timestamp;
        //return timestamp - new Date().getTimezoneOffset() * 60 * 1000;
    },
    toLocalTime: function (timestamp) {
        //return timestamp;
        return timestamp + new Date().getTimezoneOffset() * 60 * 1000;
    },
    onSelect: function (datetext) {
        $('.date-picker').val(TimeSwitcher.formatDate(new Date(datetext)));
    },
    setTimeOnClick: function ($datePicker) {
        return function (event) {
            var setTimeActionUrl = ACC.config.contextPath + "/session/time";
            var timezoneOffset = new Date().getTimezoneOffset();
            var selectedDateMillis = TimeSwitcher.toServerTime(new Date($datePicker.val()).getTime());
            $.post(setTimeActionUrl, {currentTime: selectedDateMillis, timezoneOffset: timezoneOffset}, function () {
                $(".change-status").show();
                setTimeout("$('.change-status').hide();", 1000);
            });
        }
    },
    resetTimeOnclick: function (event) {
        var setTimeActionUrl = ACC.config.contextPath + "/session/time/clear";
        $.post(setTimeActionUrl, {}, function () {
            $(".change-status").show();
            setTimeout("$('.change-status').hide();", 1000);
        });
        $(".date-picker").val(TimeSwitcher.formatDate(new Date()));
    }
};

$(document).ready(function () {
    $('.date-picker-widget').each(function () {
        var $datePicker = $(this).find('.date-picker');
        var $setTimeButton = $(this).find('.set-time-button');
        var $resetTimeButton = $(this).find('.reset-time-button');

        // send selected date (in millis) to server
        $setTimeButton.click(TimeSwitcher.setTimeOnClick($datePicker));

        // convert millis to javascript Date - initially this comes from server as UTC
        var displayedDate = new Date();
        if ($datePicker.data('serverTime') != undefined) {
            var localDate = TimeSwitcher.toLocalTime($datePicker.data('serverTime'));
            displayedDate = new Date(localDate);
        }

        //update date input field
        $(".date-picker").val(TimeSwitcher.formatDate(displayedDate));

        $datePicker.datepicker({onSelect: TimeSwitcher.onSelect});

        // send current date (in millis to server)
        $resetTimeButton.click(TimeSwitcher.resetTimeOnclick);
    });
});