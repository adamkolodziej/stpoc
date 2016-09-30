(function () {
    var mod = angular.module('guidedselling-controllers', ['guidedselling-services','ui.bootstrap']);

    mod.controller('GuidedSellingController', ['$scope', 'BundleService','$timeout', function ($scope, BundleService, $timeout) {
        $scope.offer = {};
        $scope.ignoreFocus = false;
        $scope.areComponentsLoaded = false;

        var componentsLoad = function(data, status, headers, config) {
            $scope.offer = data;
            // YTODO This must be done different way
            $scope.areComponentsLoaded = true;
            $timeout(refreshTooltips,2000);
        };

        var componentsLoadFailed = function(data, status, headers, config) {
            $scope.messages = [
                {level: 'error', content: data}
            ]
        };

        $scope.chooseSubscription = function (offerId, planCode, bundleNo) {
            BundleService
                .selectConditional(offerId, planCode, bundleNo)
                .success(componentsLoad)
                .error(componentsLoadFailed);
            $scope.updateSeeWhy();
        };

        $scope.reload = function(bundleNo) {
            BundleService
                .reload(bundleNo)
                .success(componentsLoad)
                .error(componentsLoadFailed);
        };

        $scope.selectionChanged = function(component, option) {
            var post;

            if( option.selected ) {
                post = BundleService.selectOption($scope.offer, component, option);
                $scope.updateSeeWhy();
            } else {
                post = BundleService.deselectOption($scope.offer, component, option);
            }

            post.success(componentsLoad).error(componentsLoadFailed);
        };

        $scope.singleSelectionChanged = function(component, option) {
            var post = BundleService.selectOption($scope.offer, component, option);
            $scope.updateSeeWhy();
            post.success(componentsLoad).error(componentsLoadFailed);
        };

        $scope.isServicePlan = function(itemType) {
            return itemType === "ServicePlan" || itemType === "ServiceAddOn" || itemType === "SubscriptionProduct";
        };

        $scope.isProduct = function(itemType) {
            return itemType === "Product";
        };

        $scope.isVisibleComponent = function(comp) {
            return $scope.offer.focusedComponentId == undefined || comp.id == $scope.offer.focusedComponentId || $scope.ignoreFocus;
        };

        $scope.toggleHiddenComponents = function() {
            $scope.ignoreFocus = ! $scope.ignoreFocus;
        };

        $scope.updateSeeWhy = function() {
            if($scope.seeWhyPresent === undefined) {
                BundleService
                    .isSeeWhyConfigured()
                    .success(function(data) {
                        $scope.seeWhyPresent = (data === "Y");
                        if($scope.seeWhyPresent) {
                            BundleService.updateSeeWhy();
                        }
                    })
                    .error(componentsLoadFailed);
            }
            else if($scope.seeWhyPresent) {
                BundleService.updateSeeWhy();
            }
        };

        $scope.isDisabled = function(option) {
            return option.disabled || $scope.isLockedByPackage(option);
        };

        $scope.isLockedByPackage = function(option) {
            return option.lockedByPackage === true;
        };

        $scope.isHidePreselectedProducts = function () {
            return ACC.hidePreselectedProducts === "true";
        };

        $scope.isHideDisabledProducts = function () {
            return ACC.hideDisabledProducts === "true";
        };

        $scope.shouldBeHidden = function (option) {
            return (((option.disabled || ($scope.isLockedByPackage(option) && !option.selected)) && $scope.isHideDisabledProducts())
            || ($scope.isLockedByPackage(option) && option.selected && $scope.isHidePreselectedProducts()));
        };

        $scope.shouldComponentBeHidden = function (comp) {
            if(!$scope.isHidePreselectedProducts() && !$scope.isHideDisabledProducts())
            {
                return false;
            }
            if(comp === undefined)
            {
                return true;
            }
            for(var opt_index in comp.options)
            {
                if(!$scope.shouldBeHidden(comp.options[opt_index]))
                {
                    return false;
                }
            }
            return true;
        };
        
        $scope.isLoadFinished = function () {
            return $scope.areComponentsLoaded;
        }

        $scope.reload(1); // temporary solution

        //    TMP MOCK FOR STYLING
        $scope.tmpOptions = [
            {
                name:'TriCast Wi-Fi',
                checked:true,
                discounted:true
            },
            {
                name:'TriCast Wi-Fi 2 ',
                checked:false,
                discounted:false
            }
        ];
        $scope.radioModel = 'Middle';


    }]);

    function refreshTooltips (){
        $('[data-toggle="tooltip"]').tooltip();
    }
})();
