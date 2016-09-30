<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<template:page pageTitle="${pageTitle}">

	<div id="globalMessages">
		<common:globalMessages/>
	</div>

    <cms:pageSlot position="Section1" var="feature">
            <cms:component component="${feature}" element="div" class="row"/>
     </cms:pageSlot>
    <div ng-app="sptelApp">
        <div class="blog-container" ng-controller="BlogPageCtrl" ng-init="loadPageCode('${cmsPage.uid}')">
            <div class="row full-width dark-bg">
                <div class="col-xs-12">
                    <h1 class="page-title" ng-cloak="">{{name}}</h1>
                </div>
                <div class="col-xs-12 blog-filter">
                    <div class="btn-group" data-toggle="buttons">
                        <label class="btn btn-primary active" ng-click="blogfilter.opt=-1; postsForCurrentFilter = [];">
                            <input type="radio" name="options" id="option1" autocomplete="off" checked> All Posts
                        </label>
                        <label class="btn btn-primary" ng-click="blogfilter.opt='ChannelProduct'; postsForCurrentFilter = [];">
                            <input type="radio" name="options" id="option2" autocomplete="off"> TV Channels
                        </label>
                        <label class="btn btn-primary" ng-click="blogfilter.opt='TVShowProduct'; postsForCurrentFilter = [];">
                            <input type="radio" name="options" id="option3" autocomplete="off"> Series On Demand
                        </label>
                        <label class="btn btn-primary" ng-click="blogfilter.opt='MovieProduct'; postsForCurrentFilter = [];">
                            <input type="radio" name="options" id="option4" autocomplete="off"> Movies On Demand
                        </label>
                        <label class="btn btn-primary" ng-click="blogfilter.opt='ServicePlan'; postsForCurrentFilter = [];">
                            <input type="radio" name="options" id="option5" autocomplete="off"> Broadband & Phone
                        </label>
                    </div>
                </div>
            </div>
            <div class="row" compile-html="article" ng-show="showArticle"></div>
            <div class="row" ng-hide="showArticle">
                <div class="col-xs-12 col-sm-9">
                    <div class="row">
                        <div class="col-xs-12"></div>
                    </div>
                    <div class="row posts-list">
                        <div class="col-xs-12 col-sm-6" ng-repeat="post in getPostsForCurrentPage()">
                            <div class="post box" ng-cloak="">
                                <div class="header clearfix">
                                    <h2 class="title">
                                        <a href="#" ng-click="loadArticle(post.code)">
                                            <strong>{{post.title}}</strong>
                                        </a>
                                    </h2>
                                    <div class="pull-left category text-uppercase">
                                        {{post.categoryName}}
                                    </div>
                                    <div class="pull-right date text-uppercase">
                                        {{post.date|date}}
                                    </div>
                                </div>
                                <div class="img-background" ng-style="{'background-image':'url('+post.imageURL+')'}"></div>
                                <div class="description text-left">
                                    {{post.summary}}
                                </div>
                                <button class="btn btn-sptel-secondary read-more text-uppercase" ng-click="loadArticle(post.code)"><spring:theme code="text.BlogArticles.continueReading" /></button>
                            </div>
                        </div>
                    </div>
                    <div class="row pagination-container">
                        <div class="col-xs-12 ">
                            <ul class="pagination-list list-unstyled list-inline pull-left">                          	
                                <li ng-repeat="n in getPagesRange()"><a ng-click="activatePageNumber($event)" href="#" class="btn btn-sptel-secondary">{{n}}</a></li>
                                <li class="">
                                    <button ng-disabled="true" class="btn-link">...</button>
                                </li>
                                <li><a ng-disabled="currentPageNumber == maxPagesNumber" ng-click="activateLastPage()" href="#" class="btn btn-sptel-secondary">LAST</a></li>
                            </ul>
                            <div class="pull-right">
                                <div class="count text-uppercase">
                                    page {{currentPageNumber}} of {{maxPagesNumber}}
                                </div>
                                <ul class="list-unstyled list-inline text-uppercase nav-buttons">
                                    <li><a ng-disabled="currentPageNumber == 1" ng-click="activatePrevPage()" href="#" class="btn btn-sptel-secondary">Prev</a></li>
                                    <li><a ng-disabled="currentPageNumber == maxPagesNumber" ng-click="activateNextPage()" href="#" class="btn btn-sptel-secondary">Next</a></li>
                                </ul>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
                <cms:pageSlot position="Section2C" var="feature" element="div" class="col-xs-12 col-sm-3">
                    <cms:component component="${feature}" />
                </cms:pageSlot>
                <cms:pageSlot position="Section3" var="feature" element="div">
                            <cms:component component="${feature}" element="div" class="row"  />
                </cms:pageSlot>
            </div>
        </div>
    </div>

</template:page>
