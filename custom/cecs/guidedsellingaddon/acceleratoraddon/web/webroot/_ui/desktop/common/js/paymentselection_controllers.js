
(function() {
    var app = angular.module('payment-selection', ['guidedselling-services', 'paymentInfo-services', 'deliveryInfo-services']);

    app.config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    }]);

    app.controller('PaymentSelectionController', ['$scope','BundleService', 'PaymentInfoService' , 'DeliveryInfoService', '$http',
        function ($scope, BundleService, PaymentInfoService, DeliveryInfoService, $http) {
        $scope.offer = {};
        $scope.customerPaymentInfos = {};
        $scope.customerDeliveryInfo = {};
        $scope.newPaymentInfo = {};
        $scope.newDeliveryAddress = {};
        $scope.loyaltyPayment = "false";
        $scope.areComponentsLoaded = false;
        $scope.regions = {};

        var componentsLoad = function(data, status, headers, config) {
            $scope.offer = data;

            ACC.headerMenu.loadRemainingLoyaltyPoints();
            $scope.areComponentsLoaded = true;
            setTimeout(fadeOutErrors,5000);
        };

        var paymentInfosLoad = function(data, status, headers, config) {
            $scope.customerPaymentInfos = data;
        };

        var deliveryInfoLoaded = function(data, status, headers, config) {
             $scope.customerDeliveryInfo = data;
         };

        var componentsLoadFailed = function(data, status, headers, config) {
            $scope.messages = [
                {level: 'error', content: data}
            ]
        };

        var paymentInfosLoadFailed = function(data, status, headers, config) {
            $scope.messages = [
                {level: 'error', content: data}
            ]
        };

        $scope.reload = function(bundleNo) {
            BundleService
                .reload(bundleNo)
                .success(componentsLoad)
                .error(componentsLoadFailed);
            PaymentInfoService
                .reload()
                .success(paymentInfosLoad)
                .error(paymentInfosLoadFailed);
            DeliveryInfoService
                .reload()
                .success(deliveryInfoLoaded)
                .error(componentsLoadFailed);
        };

        $scope.paymentChanged = function(bundleNo, orderEntryNumber, productCode, loyaltyPayment) {
            var post = BundleService.selectPaymentOption(bundleNo, orderEntryNumber, productCode, loyaltyPayment);
            post.success(componentsLoad).error(componentsLoadFailed);
        };

        $scope.containsErrorMessages = function() {
            for(var msg in $scope.offer.messages)
            {
                if($scope.offer.messages[msg].severity === "ERROR")
                {
                    return true;
                }
            }
            return false;
        };

        /** payment info **/
        $scope.showAddPaymentInfo = function() {
        	$scope.newPaymentInfo.accountHolderName = 
        		$scope.customerPaymentInfos.firstName.concat(" " + $scope.customerPaymentInfos.lastName);
            $(".guidedselling-payment").hide();
            $(".guidedselling-add-payment").fadeIn("slow");
        };

        $scope.hideAddPaymentInfo = function() {
            $(".guidedselling-add-payment").hide();
            $(".guidedselling-payment").fadeIn("slow");
        };

        $scope.showSelectPaymentInfo = function() {
            $(".guidedselling-payment").hide();
            $(".guidedselling-select-payment").fadeIn("slow");
        };

        $scope.hideSelectPaymentInfo = function() {
            $(".guidedselling-select-payment").hide();
            $(".guidedselling-payment").fadeIn("slow");
        };

        /** delivery address **/
        $scope.showAddDeliveryAddress = function() {
        	$scope.newDeliveryAddress.firstName = $scope.customerDeliveryInfo.firstName;
        	$scope.newDeliveryAddress.lastName = $scope.customerDeliveryInfo.lastName;
        	$scope.newDeliveryAddress.countryIso = $scope.customerDeliveryInfo.countries.slice(-1).pop().isocode;
        	$scope.newDeliveryAddress.titleCode = $scope.customerDeliveryInfo.titles[0].code;
        	$scope.selectRegions();
            $(".guidedselling-delivery-address").hide();
            $(".guidedselling-add-delivery-address").fadeIn("slow");
        };

        $scope.hideAddDeliveryAddress = function() {
            $(".guidedselling-add-delivery-address").hide();
            $(".guidedselling-delivery-address").fadeIn("slow");
        };

        $scope.showSelectDeliveryAddress = function() {
            $(".guidedselling-delivery-address").hide();
            $(".guidedselling-select-delivery-address").fadeIn("slow");
        };

        $scope.hideSelectDeliveryAddress = function() {
            $(".guidedselling-select-delivery-address").hide();
            $(".guidedselling-delivery-address").fadeIn("slow");
        };

        $scope.addNewPaymentInfo = function() {
            PaymentInfoService.createNew($scope.newPaymentInfo).success(function () {
                $scope.newPaymentInfo = {};
                PaymentInfoService
                    .reload()
                    .success(function (data, status, headers, config) {
                        $scope.hideAddPaymentInfo();
                        paymentInfosLoad(data, status, headers, config);
                    })
                    .error(paymentInfosLoadFailed);
            }).error(function(data, status, headers, config){
                $('.paymentInfo-error').html("<span>" + data + "</span>");
                $('.paymentInfo-error').show(0).delay(5000).hide(0);
            });
        };

        $scope.addNewDeliveryAddress = function () {
            DeliveryInfoService.createNew($scope.newDeliveryAddress).success(function () {
                $scope.newDeliveryAddress = {};
                DeliveryInfoService
                    .reload()
                    .success(function (data, status, headers, config) {
                        $scope.hideAddDeliveryAddress();
                        deliveryInfoLoaded(data, status, headers, config);
                    })
                    .error(paymentInfosLoadFailed);
            }).error(function(data, status, headers, config){
                $('.deliveryAddress-error').html("<span>" + data + "</span>");
                $('.deliveryAddress-error').show(0).delay(5000).hide(0);
            });
        };

        $scope.selectPaymentInfo = function (id) {
            PaymentInfoService.selectPaymentInfo(id).success(function (data, status, headers, config) {
                $scope.hideSelectPaymentInfo();
                if(data === true)
                {
                    PaymentInfoService
                        .reload()
                        .success(paymentInfosLoad)
                        .error(paymentInfosLoadFailed);
                }
            });
        };

        $scope.selectDeliveryAddress = function (id) {
            DeliveryInfoService.selectDeliveryAddress(id).success(function (data, status, headers, config) {
                $scope.hideSelectDeliveryAddress();
                if(data === true)
                {
                    DeliveryInfoService
                        .reload()
                        .success(deliveryInfoLoaded)
                        .error(componentsLoadFailed);
                }
            });
        };

        $scope.selectDeliveryMode = function () {
            DeliveryInfoService.selectDeliveryMode($scope.customerDeliveryInfo.cartDeliveryModeCode)
                .success(function (data, status, headers, config) {
                    if(data === true)
                    {
                        DeliveryInfoService
                            .reload()
                            .success(deliveryInfoLoaded)
                            .error(componentsLoadFailed);
                    }
                });
        };

        $scope.selectRegions = function () {
             DeliveryInfoService.selectRegions($scope.newDeliveryAddress.countryIso)
                .success(function(response){
                     $scope.regions = response;
                }).error(function(){
                     componentsLoadFailed
                })
        };
        
        $scope.shouldBeHidden = function (option) {
        	if (option.product.loyaltyPointsPrice != null) {
        		return false;
        	}
        	return true;
        };
        
        $scope.shouldComponentBeHidden = function (comp) {
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

        function fadeOutErrors() {
            $(".alert").fadeOut("slow");
        };

        $scope.isLoadFinished = function () {
            return $scope.areComponentsLoaded;
        }
        
        $scope.isThereAnyErrors = function () {
        	var messages = $scope.offer.messages;
            for (var index in messages) {
            	if (messages[index].severity === 'ERROR') {
            		return true;
            	}
            }
            return false;
        }

        $scope.reload(1); // temporary solution
    }]);
})();



