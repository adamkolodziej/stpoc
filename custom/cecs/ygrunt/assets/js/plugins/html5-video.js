;(function ( $, window, document, undefined ) {

    var pluginName = 'yGruntPlayer',
        defaults = {
            onplay: function(event) {
                return true;
            },
            ontimeupdate: function(event) {
                return true;
            }
        };

    // The actual plugin constructor
    function Plugin( element, options ) {
        this.element = element;
        this.video = {
            $_element:$(this.element).find('video'),
            size:{
                width:0,
                height:0
            }

        };
        this.player = this.video.$_element[0];

        this.products = $('.product-notification');

        this.options = $.extend( {}, defaults, options) ;
        this._defaults = defaults;
        this._name = pluginName;

        this.init();
    }


    Plugin.prototype.init = function () {
        this.video.size.height = this.video.$_element.height();
        this.video.size.width = this.video.$_element.width();
        this.bindEvents();
    };

    Plugin.prototype.bindEvents = function() {
        var self = this;
        self.player.addEventListener('timeupdate',function(event){
            self.checkTimeEvent(); // YTODO I guess this should be moved out of the plug in
        }, false);

        self.player.addEventListener('timeupdate', self.options.ontimeupdate, false);

        self.player.addEventListener('touchstart', function(event) {
            self.player.play();
        }, false);

        self.player.addEventListener('play', self.options.onplay);

        self.player.addEventListener('webkitfullscreenchange', self.setFullscreen, false);
        self.player.addEventListener('mozfullscreenchange', self.setFullscreen, false);
        self.player.addEventListener('fullscreenchange', self.setFullscreen, false);

        self.player.addEventListener('ended', self.unmuteProducts, false);

        $('.product-notification').on('click','.panel-close',function(e){
            e.preventDefault();
            var product = $(this).parents('.product-notification'),
            	index = product.data('fragmentIndex'),
            	filterSelector = [ 
            	    '[data-fragment-index="', index, '"]'
            	].join(),
            	allWithIndex = $('.product-notification').filter(filterSelector);
            allWithIndex.fadeOut();
            allWithIndex.data('mute',true);
        });
    };

    Plugin.prototype.setFullscreen = function() {
        var state = document.fullScreen || document.mozFullScreen || document.webkitIsFullScreen,
            body = $('body');
        if(state){
            body.addClass('video-fullscreen');
        }else{
            body.removeClass('video-fullscreen');
        }

    };

    Plugin.prototype.showContainer = function(product) {
        if(!product.is(':visible') && !product.data('mute')){
            product.fadeIn();
        }
        return true;
    };


    Plugin.prototype.checkTimeEvent = function() {
        var self = this;
        self.products.each(function(index,el){
            var $_el = $(el);
            if(parseInt($_el.data('start'))<self.player.currentTime && parseInt($_el.data('end'))>self.player.currentTime){
                if(!$_el.is(':visible')){
                    self.showContainer($_el);
                }
            }else if($_el.is(':visible')){
                $_el.fadeOut('fast');
            }


        });
    };

    Plugin.prototype.unmuteProducts = function() {
        var self = this;
        if (self.products) {
        	self.products.each(function(index,el){
        		$(el).data('mute',false);
        	});
        }
    };

    $.fn[pluginName] = function ( options ) {
        return this.each(function () {
            if (!$.data(this, 'plugin_' + pluginName)) {
                $.data(this, 'plugin_' + pluginName,
                    new Plugin( this, options ));
            }
        });
    }

})( jQuery, window, document );
