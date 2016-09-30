<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>

<template:page pageTitle="${pageTitle}">

    <div id="globalMessages">
        <common:globalMessages/>
    </div>
    <div ng-app="tricastApp">
        <div class="row merchandise-page" ng-controller="MerchandisePageCtrl">
            <div class="col-xs-12 col-sm-2">
                <h3 class="page-title">
                    Merchandise
                </h3>
                <div class="box">
                    <ul class="list-unstyled facet-list">
                        <li>
                            <a href="#" class="text-info text-uppercase">
                                tv series
                            </a>
                        </li>
                        <li>
                            <a href="#" class="text-info text-uppercase">
                                movies
                            </a>
                        </li>
                        <li>
                            <a href="#" class="text-info text-uppercase">
                                stars
                            </a>
                        </li>
                        <li>
                            <a href="#" class="text-info text-uppercase">
                                new arrivals
                            </a>
                        </li>
                        <li>
                            <a href="#" class="text-info text-uppercase">
                                best sellers
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <%--ANGULAR ONLY FOR MOCKUP--%>
            <div class="col-xs-12 col-sm-10" ng-repeat="highlight in highlightList" ng-cloak="">
                <div class="row highlight">
                    <div class="col-xs-12">
                        <h3 class="highlight-title">
                            {{highlight.title}}
                            <small>{{highlight.subtitle}}</small>
                        </h3>
                        <a href="#" class="btn btn-tricast-secondary text-uppercase see-all">See all</a>
                        <div class="clearfix"></div>
                        <div class="row merchandise-list">
                            <div class="col-xs-12 col-sm-6 col-md-3" ng-repeat="merchandise in highlight.merchandiseList">
                                <a href="#" class="box merchandise" ng-class="{'discount':merchandise.promotion,'exclusive':merchandise.exclusive}">
                                    <div class="img-container">
                                        <img ng-src="${commonResourcePath}/images/products/mock/{{merchandise.picture}}" alt="" class="img-responsive"/>
                                    </div>
                                    <div class="exclusive-container">
                                        exclusive
                                    </div>
                                    <div class="merchandise-description">
                                        <span class="title">
                                            {{merchandise.title}}
                                        </span>
                                        <ul class="product-rating list-unstyled list-inline">
                                            <li>
                                                <i class="fa fa-star"></i>
                                            </li>
                                            <li>
                                                <i class="fa fa-star"></i>
                                            </li>
                                            <li>
                                                <i class="fa fa-star"></i>
                                            </li>
                                            <li>
                                                <i class="fa fa-star"></i>
                                            </li>
                                            <li>
                                                <i class="fa fa-star"></i>
                                            </li>
                                        </ul>
                                        <div class="price-container">
                                            <ul class="list-inline list-unstyled">
                                                <li class="regular">{{merchandise.price.regular|currency}}</li>
                                                <li class="discount">{{merchandise.price.discount|currency}}</li>
                                            </ul>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template:page>
