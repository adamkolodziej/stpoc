ACC.html5videocomponent = {};

$(document).ready(function() {
    $('.html5videocomponent').each(function(index, component) {
        var id = $(this).attr('id');
        var timeEvents = $(this).data('timeevents');

        if(timeEvents.length > 0) {
            var eventsData = timeEvents.split(';');

            ACC.html5videocomponent[id] = [];
            $(eventsData).each(function (idx, event) {
                var data = event.split(':');
                var time = data[0];
                var jsFunctionName = data[1];
                ACC.html5videocomponent[id][time] = jsFunctionName;
            });
        }
    })

    $('.html5videocomponent').each(function(idx, component) {
        component.addEventListener("timeupdate", function() {
            var video = $(component);
            var id = video.attr('id');
            var events = ACC.html5videocomponent[id];
            $(events).each(function(time, jsFunctionName) {
                if(component.currentTime >= time) {
                    if(jsFunctionName != undefined && jsFunctionName != "") {
                        ACC.showcasecommon.executeFunctionByName(jsFunctionName, window);
                    }
                    events[time] = "";
                }
            });
        });
    });

    $('.searchResultsPageSelect').on('change', function() {
        var selectedOption = $(this).find('option:selected');
        var url = selectedOption.data('url');
        if(url !== undefined) {
            window.location = url;
        }
    });

    $(".hiddenreview").hide();

    $("#show-all-reviews").click(function(event) {
        event.preventDefault();
        event.stopPropagation();
        $(".hiddenreview").toggle("slow");
        $("#show-all-reviews span").toggle();
    });

    $(".review-helpful-yes, .review-helpful-no").click(function(event) {
        event.preventDefault();
        
        var actionUrl = ACC.config.contextPath + "/reviews/vote"
            el = $(this),
            reviewFooter = $(this).closest('.review-footer'),
            message = reviewFooter.find('.message'),
            reviewInfo = reviewFooter.find('.review-info'),
            isReviewHelpful = el.data('helpfull') === 'yes',
            productCode = reviewFooter.data('productCode'),
            customerCommentatorId = reviewFooter.data('customerComentatorId');
        
        $.post(actionUrl, {
            rate: isReviewHelpful,
            productCode: productCode,
            customerCommentatorId: customerCommentatorId
        }, function(result) {
            message.text(result).delay(10000).hide(800);

            $.get(actionUrl, {
                productCode: productCode,
                customerCommentatorId: customerCommentatorId
            },function(result) {
                reviewInfo.text(result);
            });
        });
    });

    $(".review-write").click(function(event) {
        event.preventDefault();
        console.log("review-write");

        $(".review-write").hide("slow");
        $("#add-review-form").show("slow");
        $(window).scrollTop($("#add-review-form").offset().top - 100);
    });

    $(".review-cancel").click(function(event) {
        event.preventDefault();
        $(".review-write").show("slow");
        $("#add-review-form").hide("slow");
    });

    if($(".validation-error").text().length <= 0){
        $("#add-review-form").hide();
        $(".review-write").show();
    }
    else {
        $("#add-review-form").show();
        $(".review-write").hide();
        $(window).scrollTop($("#add-review-form").offset().top - 100)
    }


    var enableStars = function(rate) {
        for(var i = 1; i < rate+1; i++) {
            $("i.rate-it-" + i).removeClass("fa-star-o").addClass("fa-star");
        }
    };

    var getDataRate = function(dataRateEl) {
        return parseInt($(dataRateEl).attr('data-rate'));
    }

    var disableStars = function(starAmount, ratedAlready) {
        for(var i = 1; i < starAmount + 1; i++) {
            if(i > ratedAlready) {
                $("i.rate-it-" + i).removeClass("fa-star").addClass("fa-star-o");
            }
        }
    }

    var currReviewRate = 0;
    var starAmount = parseInt($("#starAmount").text());

    var ratingStars = parseInt($("#rating-stars-input").val());
    if(ratingStars > 0) {
        enableStars(ratingStars);
        currReviewRate = ratingStars;
    }

    for(var i=1; i<(starAmount+1); i++) {
        var handler = ".rate-it i.rate-it-" + i;

        $(handler).mouseover(function() {
            var currDataRate = getDataRate(this);
            enableStars(currDataRate);
        }).mouseleave(function() {
            var currDataRate = getDataRate(this);
            if(currReviewRate != currDataRate) {
                disableStars(currDataRate, currReviewRate);
            }
        }).click(function(){
            var currDataRate = getDataRate(this);
            disableStars(starAmount, 0);
            enableStars(currDataRate);
            currReviewRate = currDataRate;
            $("#rating-stars-input").val(currReviewRate)
        });
    };
});

ACC.showcasecommon = {
    executeFunctionByName: function (functionName, context /*, args */) {
        var args = Array.prototype.slice.call(arguments, 2);
        var namespaces = functionName.split(".");
        var func = namespaces.pop();
        for (var i = 0; i < namespaces.length; i++) {
            context = context[namespaces[i]];
        }
        return context[func].apply(context, args);
    }
};

ACC.showcasecommon.hide = function() {
    var e = window.event;
    e.preventDefault();
    $('#product-promotion-fragment').css("display", "none");
};
