<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<template:page pageTitle="${pageTitle}">

    <jsp:attribute name="pageScripts">
        <script type="text/javascript">
            <c:set var="channelImageFormat" value="thumbnail" />
            var channelCode = "${channelCode}";
            var pageChannels = [
            <c:forEach var="channel" items="${channels}" varStatus="status">
                <c:set var="channelLogo" value="${ycommerce:productImage(channel, channelImageFormat)}" />
                <c:if test="${not empty channelLogo}">
                    <c:set var="channelLogo" value="${channelLogo.url}" />
                </c:if>
                <c:if test="${empty channelLogo}">
                    <spring:theme code="img.missingProductImage.${channelImageFormat}" text="/" var="channelLogo"/>
                </c:if>
                {
                    code:'${channel.code}',
                    name:'${channel.name}',
                    logo: '${channelLogo}',
                    videoSrc:[{
                        src: '${channel.fullVersionMedia.url}',
                        type: '${channel.fullVersionMedia.mediaType}'
                    }],
                    videoPoster:'${channel.videoPoster.url}',
                    shows: ${empty channel.channelSchedule ? '[]' : channel.channelSchedule}
                } <c:if test="${not status.last}">,</c:if>
            </c:forEach>
            ];
        </script>
    </jsp:attribute>

    <jsp:body>

        <div id="globalMessages">
            <common:globalMessages/>
        </div>

        <div>
            <cms:pageSlot position="Section1" var="feature">
                <cms:component component="${feature}" element="div" class="row"/>
            </cms:pageSlot>
            <div class="row">
                <cms:pageSlot position="Section2A" var="feature" element="div" class="col-xs-12 col-sm-6 col-md-5">
                    <cms:component component="${feature}"/>
                </cms:pageSlot>
                <cms:pageSlot position="Section2B" var="feature" element="div" class="col-xs-12 col-sm-6 col-md-3">
                    <cms:component component="${feature}"/>
                </cms:pageSlot>
                <cms:pageSlot position="Section2C" var="feature" element="div"
                              class="col-xs-12 col-sm-12 col-md-3 col-md-offset-1">
                    <cms:component component="${feature}"/>
                </cms:pageSlot>
            </div>
            <cms:pageSlot position="Section3" var="feature" element="div">
                <cms:component component="${feature}" element="div" class="row"/>
            </cms:pageSlot>
        </div>

        <div ng-app="sptelApp">
            <div class="channels-container" ng-controller="ChannelsPageCtrl" >
                <p ng-show="infoText" class="text-warning ng-hide">{{infoText}}</p>

                <div class="row full-width dark-bg channels-tv-player" ng-init="loadJavaVariables()">
                    <div class="col-xs-12">
                        <div class="row">
                            <div class="col-xs-12 col-md-2">
                                <h1>Watch TV</h1>

                                <div class="show-details" ng-cloak="" ng-show="activeShow">
                                    <img ng-src="{{activeChannel.logo}}" class="img-responsive" alt="Image">

                                    <h3 class="show-title">
                                        {{activeShow.name}}
                                        <small>
                                            {{activeShow.time.start}} - {{activeShow.time.end}}
                                        </small>
                                    </h3>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-8" ng-cloak="">
                                <videogular id="video-player"
                                            vg-player-ready="player.onPlayerReady($API)"
                                            vg-update-time="onUpdateTime($currentTime, $duration)"
                                            vg-update-state="onUpdateState($state)"
                                            vg-change-source="onChangeSource($source)"
                                            vg-auto-play="true">
                                    <vg-media vg-src="player.config.sources"></vg-media>

                                    <vg-controls>
                                        <vg-play-pause-button></vg-play-pause-button>
                                        <vg-time-display>{{ currentTime | date:'mm:ss' }}</vg-time-display>
                                        <vg-scrub-bar>
                                            <vg-scrub-bar-current-time></vg-scrub-bar-current-time>
                                        </vg-scrub-bar>
                                        <vg-time-display>{{timeLeft| date:'mm:ss'}}</vg-time-display>
                                        <vg-volume>
                                            <vg-mute-button></vg-mute-button>
                                            <vg-volume-bar></vg-volume-bar>
                                        </vg-volume>
                                        <vg-fullscreen-button></vg-fullscreen-button>
                                    </vg-controls>

                                    <vg-overlay-play></vg-overlay-play>
                                    <vg-poster vg-url='player.config.plugins.poster'></vg-poster>
                                </videogular>
                            </div>
                            <div class="col-xs-12 col-md-2">
                                <div class="panel panel-primary channels-panel">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Upcoming & Recommended</h3>
                                    </div>
                                    <img src="${commonResourcePath}/images/channels-mock/superbowl.png"
                                         class="img-responsive" alt="Image">

                                    <div class="panel-body">
                                        <h3>
                                            Superbowl Sunday
                                            <small>
                                                1:15-2:30pm
                                            </small>
                                        </h3>
                                        <span class="channel-logo"></span>
                                    <span class="show-title">
                                        Ottawa RedBlacks VS Pittsburgh
                                    </span>
                                    </div>
                                    <a href="#" class="btn btn-primary text-uppercase btn-block">ADD TO WATCHLIST</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="not-entitled-container" ng-if="emsCheckResult.granted != 'true'" ng-cloak="">
                    <h1 class="title">
                        <spring:theme code="ems.notentitled.chooseYourUpgrade" text="Choose your Upgrade" />
                    </h1>
                    <p class="description">
                        <spring:theme code="ems.notentitled.chooseYourUpgrade.details" text="To view the content please upgrade your subscription with one of the following option." />
                    </p>
                    <ul class="expired-grants">
                        <li class="alert alert-danger" ng-repeat="grant in emsCheckResult.expiredGrants" ng-cloak="">
                            {{grant.message}}
                        </li>
                    </ul>
                    <div class="row js-eqh">
                        <a ng-href="{{proposal.url}}" ng-repeat="proposal in emsCheckResult.proposals" class="n-category-option js-entitlement-option col-xs-12 col-sm-6 col-md-3">
                            <div class="box no-padding">
                                <div class="details js-details">
                                    <div class="media">
                                        <div class="media-left media-middle">
                                            <img ng-src="{{proposal.image}}" class="media-object" alt="{{proposal.code}}">
                                        </div>
                                        <div class="media-body media-middle">
                                            <h4 class="media-heading">{{proposal.name}}</h4>
                                        </div>
                                    </div>
                                </div>
                                <div class="category-footer">
                                    <div  class="btn btn-link btn-block check-btn">
                                        <div class="price-container">
                                            <ul class="list-inline list-unstyled">
                                                <li class="regular">
                                                    {{proposal.price}} {{proposal.billingTime}}
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-12 channels-filter">
                        <div class="btn-group">
                            <label class="btn" ng-model="channelfilter.name" uib-btn-radio="-1">All Channels</label>
                            <label class="btn" ng-model="channelfilter.name" uib-btn-radio="1">My Favorites</label>
                            <label class="btn" ng-model="channelfilter.name" uib-btn-radio="2">Sport</label>
                            <label class="btn" ng-model="channelfilter.name" uib-btn-radio="3">News</label>
                            <label class="btn" ng-model="channelfilter.name" uib-btn-radio="4">Kids & Family</label>
                            <label class="btn" ng-model="channelfilter.name" uib-btn-radio="5">Documentary</label>
                            <label class="btn" ng-model="channelfilter.name" uib-btn-radio="6">Food & Lifestyle</label>
                            <label class="btn" ng-model="channelfilter.name" uib-btn-radio="7">Action</label>
                            <label class="btn" ng-model="channelfilter.name" uib-btn-radio="8">Comedy</label>
                            <label class="btn" ng-model="channelfilter.name" uib-btn-radio="9">Animated</label>
                            <label class="btn" ng-model="channelfilter.name" uib-btn-radio="10">Reality</label>
                        </div>
                    </div>
                </div>
                <br/>

                <div class="row">
                    <div class="col-xs-12 channels-list">
                        <div class="current-time-indicator"></div>
                        <div class="first-row">
                            <div class="btn-group channel-select" uib-dropdown is-open="status.isopen">
                                <button id="single-button" type="button" class="btn btn-primary" uib-dropdown-toggle
                                        ng-disabled="disabled">
                                    CHANNELS <span class="caret"></span>
                                </button>
                                <ul uib-dropdown-menu role="menu" aria-labelledby="single-button">
                                    <li role="menuitem"><a href="#">Action</a></li>
                                    <li role="menuitem"><a href="#">Another action</a></li>
                                    <li role="menuitem"><a href="#">Something else here</a></li>
                                    <li class="divider"></li>
                                    <li role="menuitem"><a href="#">Separated link</a></li>
                                </ul>
                            </div>
                            <div class="btn btn-primary prev-btn">
                                <i class="fa fa-chevron-left fa-2x"></i>
                            </div>
                            <ul class="timeline">
                                <li class="time">11:00AM</li>
                                <li class="time">12:00PM</li>
                                <li class="time">1:00PM</li>
                                <li class="time">2:00PM</li>
                            </ul>
                            <div class="btn btn-primary next-btn">
                                <i class="fa fa-chevron-right fa-2x"></i>
                            </div>
                        </div>
                        <div class="channel" ng-repeat="channel in channels" ng-cloak="">
                            <div class="number">{{$index+1}}</div>
                            <div class="logo">
                                <img ng-src="{{channel.logo}}" class="img-responsive" alt="Image">
                            </div>
                            <ul class="shows-list">
                                <li class="show-container"
                                    ng-style="{'width':show.length*config.minLength+'%'}"
                                    ng-class="{'active':show.id == activeShow.id}"
                                    ng-repeat="show in channel.shows"
                                    ng-click="loadVideo(show,channel)"
                                    ng-cloak="">
                                    <span class="title">{{show.name}} {{$parent.$index}}{{$index}}</span>
                                    <span class="time">{{show.time.start}}-{{show.time.end}}</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </jsp:body>
</template:page>
