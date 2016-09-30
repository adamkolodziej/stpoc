
            <%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
            <%@ attribute name="hideHeaderLinks" required="false" %>

            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/common/header" %>
            <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
            <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
            <%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
            <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
            <%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
            <%-- CECS-46 main navigation - START --%>
            <%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
            <%-- CECS-46 main navigation - END --%>

            <%-- Test if the UiExperience is currently overriden and we should show the UiExperience prompt --%>
            <c:if test="${uiExperienceOverride and not sessionScope.hideUiExperienceLevelOverridePrompt}">
                <c:url value="/_s/ui-experience?level=" var="clearUiExperienceLevelOverrideUrl"/>
                <c:url value="/_s/ui-experience-level-prompt?hide=true" var="stayOnDesktopStoreUrl"/>
                <div class="backToMobileStore">
                    <a href="${clearUiExperienceLevelOverrideUrl}">
                    	<span class="greyDot">&lt;</span><spring:theme code="text.swithToMobileStore"/>
                    </a>
                    <span class="greyDot closeDot"><a href="${stayOnDesktopStoreUrl}">x</a></span>
                </div>
            </c:if>



            <nav class="navbar navbar-default main-navbar navbar-fixed-top yamm">
                    <cms:pageSlot position="TopHeaderSlot" var="component" element="div">
                            <cms:component component="${component}"/>
                        </cms:pageSlot>
            	<div class="container">
            		<div class="navbar-header">
            			<!-- CECS-105: Site logo banner should link to homepage - START -->
            			<cms:pageSlot position="SiteLogo" var="component" element="div">
            				<cms:component component="${component}"/>
            			</cms:pageSlot>
            			<!-- CECS-105: Site logo banner should link to homepage - END -->
            			<button type="button" class="navbar-toggle pull-left collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            				<span class="sr-only"></span>
            				<span class="icon-bar"></span>
            				<span class="icon-bar"></span>
            				<span class="icon-bar"></span>
                            <i class="fa fa-close fa-2x icon-x"></i>
            			</button>
            		</div>
            		<div id="navbar" class="navbar-collapse collapse">
            			<cms:pageSlot position="NavigationBarRight" var="component" element="div" class="nav navbar-right">
            				<cms:component component="${component}" element="div"/>
            			</cms:pageSlot>
            			<cms:pageSlot position="SearchBox" var="component" element="div" class="nav navbar-right search-box">
            				<cms:component component="${component}" element="div"/>
            			</cms:pageSlot>
            			<ul class="nav navbar-nav navbar-right user-login">
            				<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
            					<c:set var="maxNumberChars" value="25"/>
            					<c:if test="${fn:length(user.firstName) gt maxNumberChars}">
            						<c:set target="${user}" property="firstName" value="${fn:substring(user.firstName, 0, maxNumberChars)}..."/>
            					</c:if>
            					<li class="logged_in">
            						<ycommerce:testId code="header_LoggedUser">
            							<spring:theme code="header.welcome" arguments="${user.firstName},${user.lastName}" htmlEscape="true"/>
            						</ycommerce:testId>
            					</li>
            				</sec:authorize>
            				<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
            					<li>
            						<ycommerce:testId code="header_signOut">
            							<a class="btn btn-tricast-primary" href="<c:url value='/logout'/>">
            								<spring:theme code="header.link.logout"/>
            							</a>
            						</ycommerce:testId>
            					</li>
            				</sec:authorize>
            				<!-- CECS-101: Login dropdown - START -->
            				<jsp:include page="/loginDropdown" />
            				<!-- CECS-101: Login dropdown - END -->
            			</ul>
            			<ul class="nav navbar-nav navbar-right">
            				<c:if test="${empty hideHeaderLinks}">
            					<c:if test="${uiExperienceOverride}">
            						<li class="backToMobileLink">
            							<c:url value="/_s/ui-experience?level=" var="backToMobileStoreUrl"/>
            							<a href="${backToMobileStoreUrl}"><spring:theme code="text.backToMobileStore"/></a>
            						</li>
            					</c:if>
            				</c:if>

            				<!-- CECS-80: Site navigation - START -->
            				<nav:topNavigation/>
            				<!-- CECS-80: Site navigation - END -->
            			</ul>
            		</div>
            		<!--/.nav-collapse -->
            	</div>
            	<!--/.container-fluid -->
            </nav>

            <cms:pageSlot position="NavigationBannerSection" var="component" element="div" class="container top-banner">
            	<cms:component component="${component}" />
            </cms:pageSlot>
