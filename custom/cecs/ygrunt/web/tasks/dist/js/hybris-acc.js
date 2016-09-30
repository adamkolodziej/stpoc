/*!
 * Bootstrap v3.3.1 (http://getbootstrap.com)
 * Copyright 2011-2015 Twitter, Inc.
 * Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE)
 */
/*!
 * Hybris v0.0.1 (http://hybris.com)
 * Copyright 2011-2015 Mateusz Woźniak
 */

if (typeof jQuery === 'undefined') {
  throw new Error('Bootstrap\'s JavaScript requires jQuery')
}

+function ($) {
  var version = $.fn.jquery.split(' ')[0].split('.')
  if ((version[0] < 2 && version[1] < 9) || (version[0] == 1 && version[1] == 9 && version[2] < 1)) {
    throw new Error('Bootstrap\'s JavaScript requires jQuery version 1.9.1 or higher')
  }
}(jQuery);

ACC.homeCarousel = {
  bindCarousel: function ()
  {
    $('.carousel').carousel({
      interval:false
    });
  }
};

ACC.headerMenu = {

  bindAll: function()
  {
    this.bindCategoriesTrigger();
    this.bindMenuTrigger();
    this.bindDropdownTrigger();
    this.bindOrientationChange()
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
  }
};

ACC.eventList = {
    self:{},
    bindAll: function() {
     var self = ACC.eventList.self;
        self.eventContainer = $('.eventProducts');
        self.optionalProducts=self.eventContainer.find('.optionalProducts');
        self.maxHeight=0;

     this.bindEventDetailsSetup();
    },
    findHighestElement: function() {
        var eventList = ACC.eventList,
            self = eventList.self;
        self.optionalProducts.each(function(idx,el){
            var tmpHeight = $(el).height();
            self.maxHeight = (tmpHeight > self.maxHeight ? tmpHeight : self.maxHeight);
        });
    },
    setSameHeightAdditional: function() {
        var eventList = ACC.eventList,
            self = eventList.self;
        self.optionalProducts.each(function(idx,el){
            console.log(el);
            $(el).height(self.maxHeight);
        });
        eventList.setSameHeightLabels();

    },
    setSameHeightLabels: function(){
        $('.details-label','.label-column').each(function(idx,el){
            idx = idx*2 + 1;
            console.log($(el));
            console.log($('.details-label','.productGridItem:eq('+idx+')'));
            console.log('------');
            $(el).height($('.details-product','.productGridItem:eq('+idx+')').height());
        });
    },
    bindOrientationChange: function() {
        window.addEventListener("orientationchange", function() {
            if(window.orientation == '90' || window.orientation == '-90') {
                var eventList = ACC.eventList;
                eventList.setSameHeightLabels();
            }
        }, false);
    },
    bindEventDetailsSetup: function() {
        var eventList = ACC.eventList,
            self = eventList.self;
        $(document).ready(function(){
            if(self.eventContainer.length>0){
                eventList.findHighestElement();
                eventList.setSameHeightAdditional();
                eventList.bindOrientationChange();
            }
        });
    }
};

$(document).ready(function()
{
  ACC.headerMenu.bindAll();
  ACC.eventList.bindAll();
  ACC.homeCarousel.bindCarousel();
});
