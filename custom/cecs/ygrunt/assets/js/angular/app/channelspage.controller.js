'use strict';

angular.module('tricastApp')
    .controller('ChannelsPageCtrl', function ($scope, $sce, $timeout, $anchorScroll, $http, $httpParamSerializer) {
        //VARIABLES
        var notEntitledPosterUrl = "/yacceleratorstorefront/_ui/desktop/common/images/channels-mock/not-entitled.jpg";

        $scope.player = {};
        $scope.API = null;
        $scope.activeShow = '';
        $scope.entitlementType = 'video_streaming';
        $scope.emsCheckResult = { granted: 'true'};
        $scope.infoText = null;

        $anchorScroll.yOffset = 130;


        $scope.player.config  = {
            autoHide: false,
            autoHideTime: 3000,
            autoPlay: false,
            sources: [],
            plugins: {
                poster: ""
            }
        };

        $scope.channelfilter = {
            name:1
        };
        $scope.status = {
            isopen: false
        };

        $scope.config = {
            minLength:0.4762
        };

        $scope.player.onPlayerReady = function(API) {
            $scope.API = API;
            console.log(API);
        };

        $scope.loadVideo = function(show,channel){
            $scope.API.stop();
            $scope.player.config.sources=channel.videoSrc;
            $scope.player.config.plugins.poster = channel.videoPoster;
            $anchorScroll('video-player');
            $scope.activeShow = show;
            $scope.activeChannel = channel;
            //Uncomment for autoplay
            //$timeout($scope.API.play.bind($scope.API), 100);
        };

        $scope.onChangeSource = function(source){
            console.log('source changed');
        };

        $scope.onUpdateTime = function(currentTime,totalTime){
            //TODO:ABLE TO LOAD PRODUCT OVERLAY
        };

        $scope.onUpdateState = function(state){
            console.log(state);
            if(state==='play'){
                console.info('check entitlement');
                var data = {
                    'productCode': $scope.activeChannel.code,
                    'entitlementType': $scope.entitlementType
                };

                $http({
                        url:ACC.config.contextPath + ACC.Entitlements.checkGrantUrl,
                        method:'POST',
                        data: $httpParamSerializer(data),
                        headers:{
                            "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8;"
                        }
                    }
                ).then(function successCallback(response) {
                        console.log(response);
                        var checkResult = response.data;
                        if( checkResult.granted === 'false' ) {
                            $scope.API.stop();
                            $scope.player.config.plugins.poster=$sce.trustAsResourceUrl(notEntitledPosterUrl);
                        }
                        $scope.emsCheckResult = checkResult;
                    }, function errorCallback(response) {
                        console.log(response);
                        $scope.infoText = 'Entitlement access to this video could not be checked.';
                        $scope.API.stop();
                    });
            }
        };


        $scope.loadJavaVariables = function(){
            $scope.populateChannels(pageChannels);
        };

        $scope.populateChannels = function(pageChannels){
            angular.forEach(pageChannels, function( channel ) {
                channel.logo = $sce.trustAsResourceUrl(channel.logo);
                channel.videoSrc.src = $sce.trustAsResourceUrl(channel.videoSrc.src);
                channel.videoPoster = $sce.trustAsResourceUrl(channel.videoPoster);
            });
            $scope.channels = pageChannels;

            var channelIter = 0;
            var showIter = 0;

            if(channelCode) {
                for(var i = 0; i < $scope.channels.length ; i++) {
                    if($scope.channels[i].code == channelCode) {
                        channelIter = i;
                        var currChannel = $scope.channels[i];

                        for(var j = 0; j < currChannel.shows.length ; j++) {
                            if(currChannel.shows[j].play == true){
                                showIter = j;
                            }
                        }
                    }
                }
            }

            var channel = $scope.channels[channelIter];
            var show= channel.shows[showIter];

            $scope.player.config.sources = channel.videoSrc;
            $scope.player.config.plugins.poster = channel.videoPoster;
            $scope.activeShow = show;
            $scope.activeChannel = channel;
        };
    });
