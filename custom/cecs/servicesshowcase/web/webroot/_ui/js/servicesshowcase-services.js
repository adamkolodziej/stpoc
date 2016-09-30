(function() {
    var mod = angular.module('servicesshowcase-services', []);

    mod.factory('ControlPanelService', ['$http', function($http) {
        var controlPanelData = {};

        controlPanelData.reload = function () {
            return $http.get(ACC.config.contextPath + '/settings/reload');
        };

        controlPanelData.saveAll = function (controlPanelSettings) {
            return $http.post(ACC.config.contextPath + '/settings/saveAll', controlPanelSettings);
        };

        controlPanelData.evaluateSegment = function (uid) {
            return $http.get(ACC.config.contextPath + '/settings/evaluateSegment?uid=' + uid);
        };

        return controlPanelData;
    }]);
})();