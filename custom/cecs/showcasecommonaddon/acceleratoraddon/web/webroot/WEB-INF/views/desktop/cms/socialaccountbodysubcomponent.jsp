<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>	
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<c:set var="twitterUserProfileLink" value="#" />
<c:if test="${not empty twitterUserProfileId}">
	<c:set var="twitterUserProfileLink" value="https://twitter.com/${twitterUserProfileId}" />
</c:if>

<c:choose>
  <c:when test="${not empty fbUser.profilePictureUrl}">
    <c:set var="profilePicture" value="${fbUser.profilePictureUrl}" />
  </c:when>
  <c:when test="${not empty fbUser.smallProfilePicture}">
    <c:set var="profilePicture" value="${fbUser.smallProfilePicture.url}" />
  </c:when>
  <c:otherwise>
    <c:set var="profilePicture" value="${commonResourcePath}/images/profilePicture.png" />
  </c:otherwise>
</c:choose>

<div class="row">
	<div class="col-xs-12 dashed-bottom">
        <div class="row">
            <div class="col-xs-12">
                <div class="media">
                    <div class="media-left">
                        <a href="#">
                            <img src="${profilePicture}" class="facebook-user-image fb-size-${size} img-circle media-object" alt="${fbUser.displayName}" />
                        </a>
                    </div>
                    <div class="media-body">
                        <h1 class="customer-name">${user.name}</h1>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 hidden">
                <a href="logout"><spring:theme code="my-account.socialaccountbodysubcomponent.signout"/></a>
            </div>
            <div class="col-xs-12">
                <div class="social">
                    <div class="btn-group">
                        <a class="btn btn-link btn-xs color-fb" href="${fbUser.profileLink}"><i class="fa fa-facebook fa-fw "></i></a>
                        <a class="btn btn-link btn-xs color-twt" href="${twitterUserProfileLink}"><i class="fa fa-twitter fa-fw "></i></a>
                        <a class="btn btn-link btn-xs color-gp" href="#"><i class="fa fa-google-plus fa-fw "></i></a>
                        <a class="btn btn-link btn-xs color-li" href="#"><i class="fa fa-linkedin fa-fw "></i></a>
                    </div>
                </div>
            </div>
        </div>
	</div>
</div>