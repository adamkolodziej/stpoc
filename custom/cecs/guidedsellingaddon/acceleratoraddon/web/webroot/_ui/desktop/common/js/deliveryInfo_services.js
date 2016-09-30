(function() {
    var mod = angular.module('deliveryInfo-services', []);

    mod.factory('DeliveryInfoService', ['$http', function($http) {
        var deliveryInfo = { };

        deliveryInfo.reload = function() {
            return $http.get(ACC.config.contextPath + '/guidedselling/deliveryInfo/getAll');
        };

        deliveryInfo.createNew = function (newDeliveryAddress) {
            return $http.post(ACC.config.contextPath + '/guidedselling/deliveryInfo/add', newDeliveryAddress);
        };

        deliveryInfo.selectDeliveryAddress = function (id) {
            return $http.get(ACC.config.contextPath + '/guidedselling/deliveryInfo/select?deliveryAddressId=' + id);
        };

        deliveryInfo.selectDeliveryMode = function (code) {
            return $http.get(ACC.config.contextPath + '/guidedselling/deliveryInfo/selectDeliveryMode?deliveryModeCode=' + code);
        };

        deliveryInfo.selectRegions = function (countryCode) {
            return $http.get(ACC.config.contextPath + '/guidedselling/deliveryInfo/selectRegions?isoCountryCode=' + countryCode);
        };

        return deliveryInfo;
    }]);
})();