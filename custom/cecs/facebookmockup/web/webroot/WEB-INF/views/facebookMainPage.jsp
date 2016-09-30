<%@ page trimDirectiveWhitespaces="true"%>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ts" tagdir="/WEB-INF/tags/addons/showcasecommonaddon/desktop"  %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>[y] Facebook</title>
        <link rel="shortcut icon" href="_ui/images/favicon.ico" />
        <link rel="stylesheet" href="_ui/css/style.css?v=1.0">
        <link rel="stylesheet" type="text/css" media="all" href="/yacceleratorstorefront/_ui/desktop/common/css/fontawesome.css"/>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
		<script type="text/javascript" src="/yacceleratorstorefront/_ui/desktop/common/js/jquery-ui-1.11.2.min.js"></script>

        <script type="text/javascript">
            <c:if test="${not empty customer}">
                var FB = FB || {};
                FB.customer = '${customer.name}';
                <c:if test="${not empty customer.profilePicture}">
                FB.avatar = '${customer.profilePicture.url}';
                </c:if>
            </c:if>
        </script>

        <c:set var="avatarUrl" value="sampledata/avatar.jpg" />
        <c:if test="${not empty customer.profilePicture}">
            <c:set var="avatarUrl" value="${customer.profilePicture.url}" />
        </c:if>
    </head>

    <c:set var="mainItemOfInterest" value="UNDERWORLD" />

    <body>
    	
        <div class="time-switcher-component">
            <i class="fa fa-clock-o js-clock-icon"></i>
            <i class="fa fa-times js-close-timeswitcher"></i>

            <div class="time-switcher-component-inner">
                <ts:timeSwitcher/>
            </div>
        </div>
        
        <header class="header-main">
            <div class="container">
                <a href="" class="header-logo"><img src="_ui/images/logo-fb.png" alt="facebook"></a>
                <div class="header-search">
                    <input type="text" placeholder="Search Facebook" class="header-search-input">
                    <button type="submit" class="header-search-btn" value=""><i class="header-search-icon"></i></button>
                </div>
                <ul class="header-menu">
                    <li>
                        <a href=""><img src="${avatarUrl}" alt="${customer.name}" class="avatar"><span class="js-name"></span></a>
                        <span class="separator"></span>
                    </li>
                    <li><a href="" class="text-link">Home</a></li>
                    <li><a href="" class="links"><img src="_ui/images/ico-main-menu-links.png" alt=""></a></li>
                    <li><span class="separator"></span></li>
                    <li><a href="" class="link link-privacy"></a></li>
                    <li><a href="" class="link link-more"></a></li>
                </ul>
            </div>
        </header>

        <main class="content-main">
            <div class="container">
                <div class="profile-container">
                    <div class="profile-information">
                        <div class="profile-background">
                            <img src="sampledata/profile-bg.jpg" alt=""/>
                            <div class="gradient"></div>
                        </div>
                        <div class="profile-info-container">
                            <a href="" class="profile-avatar">
                                <img src="sampledata/profile-avatar.jpg" alt=""/>
                            </a>
                            <div class="profile-right-info">
                                <div class="profile-name">
                                    <h1 class="">TCO Underworld</h1>
                                    <small class="">Movies/TV Shows</small>
                                </div>
                                <div class="buttons">
                                    <c:url var="likeUrl" value="/like/${mainItemOfInterest}" />
                                    <a href="${likeUrl}" class="btn-like js-like" data-title="Underworld" data-content="TCO's Underworld - Official Page">
                                        <img class="not-liked" src="_ui/images/button-profile-like.png" alt=""/>
                                        <img class="liked" src="_ui/images/button-profile-liked.png" alt=""/>
                                    </a>
                                    <a href="" class="btn-msg"><img src="_ui/images/button-profile-msg.png" alt=""/></a>
                                </div>
                                <div class="clearfix"></div>
                                <a href="" class="btn-profile-links"><img src="_ui/images/menu-profile-info-links.png" alt=""/></a>
                            </div>
                        </div>
                    </div>
                    <aside class="sidebar">
                        <img src="_ui/images/profile-sidebar.png" alt=""/>
                    </aside>
                    <div class="feed">
                        <div class="post-publish-box">
                            <form>
                                <a href=""><img src="_ui/images/publish-box-options.png" alt="" class="post-publish-box-options"></a>
                                <div class="post-publish-box-content-wrapper">
                                    <a href=""><img src="${avatarUrl}" class="post-publish-box-avatar" alt=""></a>
                                    <textarea name="post-text" class="post-publish-box-text js-post-text" placeholder="What's on your mind?"></textarea>
                                    <input id="interestItem" type="hidden" name="interestItem" value="${mainItemOfInterest}" />
                                </div>
                                <a href=""><img src="_ui/images/publish-box-icons.png" alt="" class="post-publish-box-icons"></a>
                                <button type="submit" name="submit-btn" class="post-publish-box-submit-btn js-submit-post">Post</button>
                                <a href=""><img src="_ui/images/button-post-visibility.png" alt="" class="post-publish-box-visibility-btn"></a>
                            </form>
                        </div>
                        <ul class="posts js-posts">
                        </ul>
                    </div>
                </div>
                <aside class="timeline">

                </aside>
            </div>
        </main>

        <aside class="contacts">
            <ul class="contacts-list">
                <li class="contact">
                    <img src="_ui/images/avatar-1.jpg" alt="" class="contact-image">
                    <span class="contact-name">Mario Park</span>
                    <span class="contact-status online"></span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-2.jpg" alt="" class="contact-image">
                    <span class="contact-name">Kenny Hayes</span>
                    <span class="contact-status offline">1h</span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-3.jpg" alt="" class="contact-image">
                    <span class="contact-name">Natalie Nunez</span>
                    <span class="contact-status">30min</span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-4.jpg" alt="" class="contact-image">
                    <span class="contact-name">Mitchell Francis</span>
                    <span class="contact-status online"></span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-5.jpg" alt="" class="contact-image">
                    <span class="contact-name">Leonard Ortiz</span>
                    <span class="contact-status online"></span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-6.jpg" alt="" class="contact-image">
                    <span class="contact-name">Antonio Burgess</span>
                    <span class="contact-status offline">6h</span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-7.jpg" alt="" class="contact-image">
                    <span class="contact-name">Shannon Warner</span>
                    <span class="contact-status offline">48min</span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-8.jpg" alt="" class="contact-image">
                    <span class="contact-name">Laura Pratt</span>
                    <span class="contact-status">3h</span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-9.jpg" alt="" class="contact-image">
                    <span class="contact-name">Samantha Ross</span>
                    <span class="contact-status online"></span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-10.jpg" alt="" class="contact-image">
                    <span class="contact-name">Emmett Barton</span>
                    <span class="contact-status">21h</span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-11.jpg" alt="" class="contact-image">
                    <span class="contact-name">Silvia Cobb</span>
                    <span class="contact-status">5min</span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-12.jpg" alt="" class="contact-image">
                    <span class="contact-name">Daniel Spencer</span>
                    <span class="contact-status online"></span>
                </li>
                <li class="contact">
                    <img src="_ui/images/avatar-13.jpg" alt="" class="contact-image">
                    <span class="contact-name">Eleanor Yates</span>
                    <span class="contact-status offline">10h</span>
                </li>
            </ul>
        </aside>

        <div class="chat-hidden"></div>

        <script src="_ui/js/scripts.js"></script>
        <script type="text/javascript" src="_ui/js/timeswitcher.js"></script>
    </body>
</html>