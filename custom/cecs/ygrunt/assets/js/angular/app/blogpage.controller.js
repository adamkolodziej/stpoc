'use strict';

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
