'use strict';
// Source: assets/js/angular/app.js
angular.module('tricastApp', [
    "ngSanitize",
    "com.2fdevs.videogular",
    "com.2fdevs.videogular.plugins.controls",
    "com.2fdevs.videogular.plugins.poster",
    "ui.bootstrap"
]).config(function($locationProvider){
    $locationProvider.html5Mode({
        enabled: false,
        requireBase: false
    });
});

// Source: assets/js/angular/app/blogpage.controller.js
angular.module('tricastApp')
.controller('BlogPageCtrl', function ($scope,$http,$location,$anchorScroll,$sce) {
	$scope.name = 'Blog';
    $scope.maxPostsNumberOnPage = 4;
    $scope.currentPageNumber = 1;
    $scope.maxButtonsNumberOnPage = 5;
    $scope.allPosts = [];
    $scope.postsForCurrentFilter = [];
    $scope.postStartInd = 0;
    $scope.postEndInd = 0;       
    $scope.blogfilter = {
        opt:-1
    };

    $scope.currentPageCode = ''//=ACC.config.defaultBlogPageCode;

    $anchorScroll.yOffset = 130;

    $scope.loadContent = function(){
        $http.get(ACC.config.contextPath+'/blog/all/'+$scope.currentPageCode).then(
            function(response){
                $scope.allPosts = response.data;
                $scope.setPostsIndexes();
            },
            function(response){
            }
        );
    }
    
    $scope.updatePostsForCurrentFilter = function () {
    	$scope.allPosts.forEach(function(item, index, array) {
    		if (item.categoryCode === $scope.blogfilter.opt || $scope.blogfilter.opt === -1) {
    			$scope.postsForCurrentFilter.push(item);
            }
    	});
        $scope.maxPagesNumber = Math.ceil($scope.postsForCurrentFilter.length / $scope.maxPostsNumberOnPage);
        $scope.currentPageNumber = 1;
        $scope.setPostsIndexes();
    }
    
    $scope.getPostsForCurrentPage = function() {
    	if ($scope.postsForCurrentFilter.length == 0) {
    		$scope.updatePostsForCurrentFilter();
    	}
    	return $scope.postsForCurrentFilter.slice($scope.postStartInd, $scope.postEndInd);
    }

    $scope.loadArticle = function(articleId){
        $http.get(ACC.config.contextPath+'/blogArticle/'+articleId).then(
            function(response){
                $scope.article = $sce.trustAsHtml(response.data);
                $scope.showArticle = true;
                $location.path('/article');
                $location.hash(articleId);
                $anchorScroll(articleId);
            },
            function(response){
                console.log('Error:');
                console.log(response);
            }
        )
    };

    $scope.showArticleList = function(){
        $scope.showArticle = false;
        $location.path('');
        $location.hash('');
        $scope.article = '';
    };


    if($location.hash().length>0){
        $scope.loadArticle($location.hash());
    }

    $scope.loadPageCode = function(uid){
        $scope.currentPageCode = uid;
        $scope.loadContent();
    }
    
    $scope.getPagesRange = function() {
    	var pagesRange = [];
    	var movingStep = Math.floor($scope.maxButtonsNumberOnPage / 2); 
    	
    	var startInd = $scope.currentPageNumber - movingStep;
    	if ($scope.currentPageNumber + movingStep > $scope.maxPagesNumber) {
    		startInd = $scope.maxPagesNumber - $scope.maxButtonsNumberOnPage + 1;
    	}
    	if (startInd < 1) {
    		startInd = 1;
    	}
    	
    	var endInd = startInd + $scope.maxButtonsNumberOnPage - 1;
    	if (endInd > $scope.maxPagesNumber) {
    		endInd = $scope.maxPagesNumber;
    	}
    	
    	for (var i = startInd; i <= endInd; i++) {
    		pagesRange.push(i);
    	}
    	
    	return pagesRange;
    }
    
    $scope.setPostsIndexes = function () {
    	$scope.postStartInd = $scope.maxPostsNumberOnPage * $scope.currentPageNumber - $scope.maxPostsNumberOnPage;
    	$scope.postEndInd = $scope.postStartInd + $scope.maxPostsNumberOnPage;
    }
    
    $scope.activatePageNumber = function($event) {
    	$scope.currentPageNumber = parseInt($event.target.innerText);
    	$scope.setPostsIndexes();
    }
    
    $scope.activateLastPage = function() {
    	$scope.currentPageNumber = $scope.maxPagesNumber;
    	$scope.setPostsIndexes();
    }
    
    $scope.activateNextPage = function() {
    	$scope.currentPageNumber = $scope.currentPageNumber + 1;
    	$scope.setPostsIndexes();
    }
    
    $scope.activatePrevPage = function() {
    	$scope.currentPageNumber = $scope.currentPageNumber - 1;
    	$scope.setPostsIndexes();
    }
});

// Source: assets/js/angular/app/channelspage.controller.js
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

// Source: assets/js/angular/app/compileHtml.directive.js
angular.module('tricastApp')
    .directive("compileHtml", function($parse, $sce, $compile) {
        return {
            restrict: "A",
            link: function (scope, element, attributes) {

                var expression = $sce.parseAsHtml(attributes.compileHtml);

                var getResult = function () {
                    return expression(scope);
                };

                scope.$watch(getResult, function (newValue) {
                    var linker = $compile(newValue);
                    element.html(linker(scope));
                });
            }
        }
    });

// Source: assets/js/angular/app/merchandisepage.controller.js
angular.module('tricastApp')
    .controller('MerchandisePageCtrl', function ($scope) {
        $scope.highlightList = [
            {
                title:'Best Selling',
                subtitle:'Most Popular Highlights',
                merchandiseList:[
                    {
                        title:'Armageddon - Book to the movie',
                        rating:'5',
                        price:{
                            regular:19.99,
                            discount:0
                        },
                        promotion:false,
                        exclusive:false,
                        picture:'bs-1.jpg'
                    },
                    {
                        title:'Brother Roses Classic T-shirt',
                        rating:'5',
                        price:{
                            regular:9.99,
                            discount:0
                        },
                        promotion:false,
                        exclusive:false,
                        picture:'bs-2.jpg'
                    },
                    {
                        title:'Joy Coffee Mug',
                        rating:'5',
                        price:{
                            regular:21.99,
                            discount:0
                        },
                        promotion:false,
                        exclusive:true,
                        picture:'bs-4.jpg'
                    },
                    {
                        title:'Rebellion Womans T-Shirt',
                        rating:'5',
                        price:{
                            regular:19.99,
                            discount:9.99
                        },
                        promotion:true,
                        exclusive:false,
                        picture:'bs-5.jpg'
                    }
                ]

            }
        ]
    });
