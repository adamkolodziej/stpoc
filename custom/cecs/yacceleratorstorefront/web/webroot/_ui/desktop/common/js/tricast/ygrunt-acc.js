if(typeof ACC == 'undefined'){
    var ACC={};
}

ACC.headerMenu = {

    bindAll: function()
    {
        this.bindCategoriesTrigger();
        this.bindMenuTrigger();
        this.bindDropdownTrigger();
        this.bindOrientationChange();
    },

    bindCategoriesTrigger: function()
    {
        $('.js-categories-trigger').on('click', function(e) {
            $(this).toggleClass('active');
            $('.js-top-navigation').slideToggle();
            $('.js-top-menu').slideUp();
            $('.js-menu-trigger').removeClass('active');
            e.preventDefault();

        });
    },

    bindMenuTrigger: function()
    {
        $('.js-menu-trigger').on('click', function(e) {
            $(this).toggleClass('active');
            $('.js-top-menu').slideToggle();
            $('.js-top-navigation').slideUp();
            $('.js-categories-trigger').removeClass('active');
            e.preventDefault();
        });
    },

    bindDropdownTrigger: function() {
        $('.js-dropdown-trigger').on('click', function (e) {
            $(this).find('.trigger').toggleClass('active');
            $('.js-dropdown-items').slideToggle();
            e.preventDefault();
        });
    },

    bindOrientationChange: function() {
        window.addEventListener("orientationchange", function() {
            if(window.orientation == '90' || window.orientation == '-90') {
                $('.js-top-menu, .js-top-navigation').slideDown();
            }
        }, false);
    },

    loadRemainingLoyaltyPoints: function() {
        var loadRemainingLoyaltyPointsUrl = ACC.config.contextPath + '/loyalty-points/remaining';
        $.ajax({
            url: loadRemainingLoyaltyPointsUrl,
            type: 'GET',
            cache: false,
            success: function (response)
            {
                $('#remainingLoyaltyPoints').html(response);
            }
        });
    }
};

ACC.Entitlements = {
    checkGrantUrl: '/entitlements/evaluate/product',
    submitUsageUrl: '/entitlements/submit/usage/product',
    evaluateProduct: function(params) {
        var result;
        $.ajax({
            url: ACC.config.contextPath + ACC.Entitlements.checkGrantUrl,
            type: 'POST',
            data: params,
            async: false,
            dataType: 'json',
            success: function(response) {
                result = response;
            },
            error: function(response) {
                result = {granted: 'false', htmlOutput: "Failed to check entitlement: " + response};
            }
        });
        return result;
    },
    submitUsagePromise: function(params) {
        return $.ajax({
            url: ACC.config.contextPath + ACC.Entitlements.submitUsageUrl,
            type: 'POST',
            data: params,
            dataType: 'json'
        });
    }
};

$(document).ready(function()
{
    ACC.headerMenu.bindAll();

    $('.carousel-multi').each(function(idx,el){
        var productsToShow = (typeof $(el).data('pcts') !== 'undefined' ? $(el).data('pcts') : 4),
            productsToSlide = (typeof $(el).data('pctsl') !== 'undefined' ? $(el).data('pcts') : 4);
        $(el).slick({
            infinite: false,
            dots:false,
            arrows:true,
            slidesToShow:productsToShow,
            slidesToScroll:productsToSlide,
            variableWidth: true,
            responsive: [
                {
                    breakpoint: 768,
                    settings: {
                        arrows: false,
                        centerMode: false,
                        slidesToShow:2,
                        slidesToScroll:2,
                        dots:false
                    }
                }
            ]
        });
    });

    $('.sugegst-mock-carousel').slick({
        infinite:true,
        arrows:true,
        slidesToShow:6,
        slidesToScroll:6,
        variableWidth: true
    });

    $('.suggest-tvshow-carousel').slick({
            infinite:true,
            arrows:true,
            slidesToShow:4,
            slidesToScroll:1,
            variableWidth:true
     });

    $('.toggle-details').on('click',function(e){
        e.preventDefault();

        var target = $(this).data('target'),
            $_target = $(target);

        $_target.slideToggle();
        $(this).toggleClass('active');
    });


    $('.html5-video-player').yGruntPlayer({
        onplay: function(event) {
            var $player = $(this);
            var freePreview = $player.data('freePreview');
            if( freePreview ) {
                return;
            }
            var videoProduct = $player.data('videoProduct');
            var entitlementType = $player.data('entitlementType');

            var checkResult = ACC.Entitlements.evaluateProduct({
                productCode: videoProduct,
                entitlementType: entitlementType
            });

            if( checkResult.granted === 'false' ) {
                var $container = $('.js-not-entitled-container'),
                    $playerContainer = $player.parents('.html5-video-player'),
                    $chargesContainer = $('.js-usage-charge-fit');
                $container.html(checkResult.htmlOutput);
                $player.get(0).pause();
                $playerContainer.hide();
                $chargesContainer.html('');
                ACC.InstantCheckout.bindAll();
            }
        },
        ontimeupdate: function(event) {
            var self = this;
            var $player = $(self);
            if( self.currentTime > 10 ) {
                var usageSubmitted = $player.data('usageSubmitted');
                var showFreeVersion = $player.data('showFreeVersion');
                if( ! usageSubmitted && !showFreeVersion) {
                    var videoProduct = $player.data('videoProduct');
                    var entitlementType = $player.data('entitlementType');

                    ACC.Entitlements.submitUsagePromise({
                        productCode: videoProduct,
                        entitlementType: entitlementType
                    }).done(function(response) {
                        console.log("usage submitted: " + response);
                    }).fail(function(response) {
                        console.log("usage not submitted: " + response);
                    });
                    $player.data('usageSubmitted', true);
                }
            }
        }
    });

    // CECS-170 Product image component - START
    $('.gallery').on('click','.thumbnail-img',function(event) {
        event.preventDefault();
        var imgSrc = $(this).data("mainimage");
        $('.main-photo','.gallery').attr("src",imgSrc);
        $('li','.gallery .thumbnails').removeClass('active');
        $(this).parent('li').addClass('active');
    });
    // CECS-170 Product image component - END

    $('.dropdown-toggle','.main-navbar .user-login').on('click',function(){
        var self = $(this);
        if($(document).width() <= 768){
            $('.navbar-collapse').animate({scrollTop:self.position().top},1000)
        }

    });

    // CECS-133 Signing in from top of the page leads to login error page - START
    $('.dropdown-login').on('submit','#loginForm',function(event) {
        var success = false;
        var username = $('#j_username').val();
        var password = $('#j_password').val();
        var restLoginUrl = "/rest/oauth/token?client_id=mobile_android&client_secret=secret&grant_type=password&username=" + username + "&password=" + password;
        $.ajax({
            url: restLoginUrl,
            async: false,
            type: 'POST',
            success: function ()
            {
                success = true;
            }
        });
        if(success == true)
        {
            return;
        }
        else
        {
            event.preventDefault();
            if($('#login-error-section').length == 0)
            {
                $('.form_field_error-message').prepend('<div id="login-error-section"><span class="alert-danger">' +
                ACC.addons.servicescore['loginDropdown.error'] + '</span></br><div>');
            }
            else
            {
                $('#login-error-section').fadeIn('fast');
            }
            setTimeout(function() {
                $('#login-error-section').fadeOut('fast');
            }, 3000);
        }
    });
    // CECS-133 Signing in from top of the page leads to login error page - END

    $('.configure-package').on('click', '.show-entitlements', function () {
        $(this).toggleClass('entitlements-shown');
    });


    if($(document).width()>=1024){
        var $summaryColumn = $('.summary-column');

        $summaryColumn.affix({
            offset: {
                top: 553,
                bottom: 219
            }
        });

        $summaryColumn.on('affix.bs.affix',function(){
            $summaryColumn.data('affix-width',$summaryColumn.width());
            $summaryColumn.css('position','fixed');
        });

        $summaryColumn.on('affixed.bs.affix',function(){
            $summaryColumn.width($summaryColumn.data('affix-width'));
        });
        $summaryColumn.on('affix-top.bs.affix',function(){
            $summaryColumn.css('position','relative');
        });
    }


    var choosedProductColor = $(".selectedColor");
    if (choosedProductColor.length > 0) {
    	loadDetailsForVariantProduct(choosedProductColor);
    }

    //CECS-780: fix instantcheckout
    //we need to enable the 'buy with 1-click' button
    var selectedProductSize = $('#sizeVariant').val();
    if (selectedProductSize) {
        var $buyWith1ClickBtn = $(".positive.instantcheckout-button.disabled");
        $buyWith1ClickBtn.removeClass("disabled");
    }

	 // CECS-183 Product details variant selector
	 $(".colorVariantSelect").click(function(){
	 	if (!$(this).hasClass("selectedColor")) {
	 		loadDetailsForVariantProduct($(this));
	 		$(".selectedColor").removeClass("selectedColor");
	 		$(this).addClass("selectedColor");
	 		$("#emptyOption").attr('selected', 'selected');

	 		var $buyWith1ClickBtn = $(".positive.instantcheckout-button");
	        $buyWith1ClickBtn.addClass("disabled");
	 	}
	 });
	 
	 ACC.instatntCheckout.checkWatchOrBuy();
});

ACC.instatntCheckout = {
	checkActionUrl: ACC.config.contextPath + "/entitlements/evaluate/products",
	buyActionUrl: [ACC.config.contextPath + "/instantcheckout/single/","/ajax"],
	
	classes: {
		messages: {
			loading: '.js-loading',
			watch: '.js-watch',
			watchNow: '.js-watch-now',
			buy: '.js-buy',
			price: '.js-price',
			watchNowPrice: '.js-watch-now-price',
			error: '.js-buy-watch-error-message'
		},
		
		buttons: '.js-buy-watch-button',
		withPrice: 'buy-watch-button-price' // class without dot on the beggining. It's added/removed but not used for filtering
	},
	
	checkWatchOrBuy: function() {
		var $buyWatchButtons = $(ACC.instatntCheckout.classes.buttons);
		
		if ($buyWatchButtons.length == 0) {
			return;
		}
	
		$buyWatchButtons.off('click');
		$buyWatchButtons.on('click', ACC.instatntCheckout.onClick);
		
		setTimeout(ACC.instatntCheckout.checkWatchOrBuyCallback, 500);
	},

	checkWatchOrBuyCallback: function() {
		var classes = ACC.instatntCheckout.classes,
			$buyWatchButtons = $(classes.buttons),
			productCodes = [];
		
		$buyWatchButtons.each(function() {
			productCodes.push($(this).data('code'));
		});
		
		$.post(ACC.instatntCheckout.checkActionUrl, {
			'entitlementType': 'video_streaming',
			'productCodes': productCodes
		}, function(result) {
			angular.forEach(result, function(value, key) {
				var selector = [
				        '[data-code="',
				        value.productCode,
				        '"]'
				    ].join(''),
					$button = $buyWatchButtons.filter(selector),
					accessWithEntitlement = value.granted && value.withUsageCharges,
					accessWithoutEntitlement = value.granted && !value.withUsageCharges,
					entitlementEnable = ACC.config.showcasecommonPpvEnabled=='true', // flag is string
					
					buyFlag = (!accessWithoutEntitlement && !accessWithEntitlement ) || (accessWithEntitlement && !entitlementEnable),
					watchFlag = accessWithoutEntitlement,
					watchNowFlag = accessWithEntitlement && entitlementEnable,
					isFormatedPrice = value.formatedPrice !== null && value.formatedPrice !== undefined;

				$button.find(classes.messages.loading).hide();
				$button.find(classes.messages.watch).toggle(watchFlag);
				$button.find(classes.messages.watchNow).toggle(watchNowFlag);
				$button.find(classes.messages.buy).toggle(buyFlag && !watchNowFlag);
				$button.data('state', buyFlag ? 'buy' : 'watch');
				
				$button.find(classes.messages.watchNowPrice).toggle(!value.granted).text(value.formatedPrice);
				if (buyFlag || watchNowFlag) {
					$button.addClass(classes.withPrice);
					$button.find(classes.messages.watchNowPrice).text(isFormatedPrice ? value.formatedPrice : '').toggle(watchNowFlag);
					$button.find(classes.messages.price).toggle(!isFormatedPrice || !watchNowFlag);
				} else {
					$button.removeClass(classes.messages.withPrice);
				}
			})
		});
	},

	onClick: function(evt) {
		var classes = ACC.instatntCheckout.classes,
			$button = $(this),
			$errorMessage = $button.siblings(classes.messages.error),
			state = $button.data('state');
	
		$errorMessage.html('');
		
		if ('buy' === state) {
			$button.removeClass('buy-watch-button-price');
			$button.find(classes.messages.loading).show();
			$button.find(classes.messages.buy).hide();
			$button.find(classes.messages.watchNow).hide();
			$button.find(classes.messages.price).hide();
			$button.find(classes.messages.watchNowPrice).hide();
			
			setTimeout(function() {
				$.get(ACC.instatntCheckout.buyActionUrl.join($button.data('code')), {
					loyaltyPayment: false
				}, function(result) {
					if (result.status == 'success') {
						ACC.instatntCheckout.checkWatchOrBuyCallback();
					} else {
						$errorMessage.html(result.responseHtml);
						$button.data('state', 'buy');
						$button.find(classes.messages.buy).show();
					}
				});
			}, 500);
			
			evt.stopPropagation();
			evt.preventDefault();
			return false;
		}
		if ('loading' === state) {
			evt.stopPropagation();
			evt.preventDefault();
			return false;
		}
	}

}

function refreshMoviePage() {
	$('.html5-video-player').show();
	$('.js-not-entitled-container').hide();
}

function loadDetailsForVariantProduct(choosedProductColor) {
	var colorVariantSelectUrl = choosedProductColor.attr("data-colorVariant");
    $.ajax({
        type: "GET",
        url: colorVariantSelectUrl,
        success: function(data){
            $("#sizeVariant").attr("disabled", false).removeAttr("style");
            $(".sizeSelect").hide().removeClass("sizeSelect");

            for (i = 0; i < data.length; i += 2) {
                $("#" + data[i])
                    .attr("value", ACC.config.contextPath + data[i + 1])
                    .addClass("sizeSelect");
            }

            $(".sizeSelect").show();
        }
    });
}



function changeColor(color) {
    $("#colorName").html(color);
}

function changeSize(size) {
    $("#sizeName").html(size);
}
