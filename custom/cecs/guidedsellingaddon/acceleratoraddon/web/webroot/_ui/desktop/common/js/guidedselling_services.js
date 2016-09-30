(function() {
    var mod = angular.module('guidedselling-services', []);

    mod.factory('BundleService', ['$http', function($http) {
        var bundle = { };

        bundle.reload = function( bundleNo ) {
            return $http.get(ACC.config.contextPath + '/guidedselling/offer/' + bundleNo);
        };

        bundle.selectConditional = function(offerId, planCode, bundleNo) {
            var bundleRequest = {
                productCode: planCode,
                bundleTemplateId: offerId,
                bundleNo: bundleNo
            };

            return $http.post(ACC.config.contextPath + '/guidedselling/conditional/select', bundleRequest);
        };

        bundle.selectOption = function(offer, comp, opt) {
            var removeOthers = comp.selectionMode == 'ONE' || comp.selectionMode == 'ONEOPTIONAL';
            var addRequest = {
                productCode: opt.product.code,
                bundleTemplateId: comp.id,
                bundleNo: offer.bundleNo,
                removeOthers: removeOthers
            };

            return $http.post(ACC.config.contextPath + '/guidedselling/option/add', addRequest);
        };

        bundle.deselectOption = function(offer, comp, opt) {
            var removeRequest = {
                productCode: opt.product.code,
                bundleTemplateId: comp.id,
                bundleNo: offer.bundleNo,
                entryNumber: opt.orderEntryNumber
            };

            return $http.post(ACC.config.contextPath + '/guidedselling/option/remove', removeRequest);
        };

        bundle.updateSeeWhy = function() {
            $http.get(ACC.config.contextPath + "/guidedselling/seewhy/getCart").
                success(function(data, status, headers, config) {
                    com_seewhy_addon_f.seewhy.addToCart(data.seewhyCart);
                }).
                error(function(data, status, headers, config) {
                    console.log("Failed to get SeeWhy cart: " + data);
                });
        };

        bundle.isSeeWhyConfigured = function() {
            return $http.get(ACC.config.contextPath + '/guidedselling/seewhy/isConfigured');
        };

        bundle.selectPaymentOption = function(bundleNo, orderEntryNumber, productCode, loyaltyPayment) {
            var selectionRequest = {
                "productCode": productCode,
                "bundleNo": bundleNo,
                "isLoyaltyPayment": loyaltyPayment,
                "orderEntryNumber": orderEntryNumber
            };

            return $http.post(ACC.config.contextPath + '/guidedselling/order/payment-selection', selectionRequest);
        };

        return bundle;
    }]);
})();
