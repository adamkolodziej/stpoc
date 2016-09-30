(function () {
    var mod = angular.module('servicesshowcase-controllers', ['servicesshowcase-services']);

    mod.controller('ControlPanelController', ['$scope', 'ControlPanelService','$timeout', function ($scope, ControlPanelService, $timeout) {
        $scope.controlPanelData = {};

        var propertiesLoad = function (data, status, headers, config) {
            $scope.controlPanelData = data;
            $timeout(function(){$scope.controlPanelData.messages = [];}, 5000);
        };

        var propertiesLoadFailed = function(data, status, headers, config) {
            $scope.controlPanelData.messages = [
                {severity: 'ERROR', content: data}
            ]
        };

        var notification = function (data, status, headers, config) {
            $scope.controlPanelData.messages = [data];
            $timeout(function(){$scope.controlPanelData.messages = [];}, 5000);
        };

        $scope.reload = function () {
            ControlPanelService
                .reload()
                .success(propertiesLoad)
                .error(propertiesLoadFailed);
        };

        $scope.saveAll = function () {
            ControlPanelService.saveAll($scope.controlPanelData)
                .success(propertiesLoad)
                .error(propertiesLoadFailed);
        };

        $scope.evaluateSegment = function (uid) {
            ControlPanelService.evaluateSegment(uid)
                .success(notification)
                .error(notification);
        };

        $scope.reload();

    }]);
})();
