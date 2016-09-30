(function() {
    var app = angular.module('guidedselling', ['guidedselling-services', 'guidedselling-controllers']);

    app.config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    }]);


//   CECS-860 Guidedselling entitlements size fix

    setTimeout(function(){ setEqualHeight(); }, 100);


})();

function setEqualHeight(){
    console.log('set equal height2');

    var $eqh = $('.js-eqh'),
        rows = [],
        minHeight= 0;
    if($eqh.length>0){
        $eqh.each(function(idx,el){
            var $el = $(el),
                $entitlements = $el.find('.js-entitlement-option');
            if($entitlements.length>0){
                $entitlements.each(function(idx,el){
                    if(idx%3==0){
                        tmp=[];
                        rows.push(tmp);
                    }
                    tmp.push(el);
                });
            }
        });
    }

    $(rows).each(function(idx,el){
        minHeight = 0;
        $(el).each(function(idx,el){
            var $details = $(el).find('.js-details');
            console.log($details.height());
            if(minHeight == 0){
                minHeight=$details.height()+20;
            }else{
                if($details.height()>minHeight){
                    minHeight=$details.height()+20;
                }
            }
        });
        $(el).each(function(idx,el){
            var $el = $(el);
            $el.find('.js-details').css('min-height',minHeight);
        });
    })
}