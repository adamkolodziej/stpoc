(function() {
    var mod = angular.module('paymentInfo-services', []);

    mod.factory('PaymentInfoService', ['$http', function($http) {
        var paymentInfos = { };

        paymentInfos.reload = function() {
            return $http.get(ACC.config.contextPath + '/guidedselling/paymentInfos/customerPaymentInfos');
        };

        paymentInfos.createNew = function (newPaymentInfo) {
            return $http.post(ACC.config.contextPath + '/guidedselling/paymentInfos/add', newPaymentInfo);
        };

        paymentInfos.selectPaymentInfo = function (id) {
            return $http.get(ACC.config.contextPath + '/guidedselling/paymentInfos/select?paymentInfoId=' + id);
        };

        return paymentInfos;
    }]);
 })();