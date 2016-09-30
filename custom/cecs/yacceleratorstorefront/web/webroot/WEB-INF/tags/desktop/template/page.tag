<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageTitle" required="false" rtexprvalue="true" %>
<%@ attribute name="pageCss" required="false" fragment="true" %>
<%@ attribute name="pageScripts" required="false" fragment="true" %>
<%@ attribute name="hideHeaderLinks" required="false" %>

<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/common/header" %>
<%@ taglib prefix="footer" tagdir="/WEB-INF/tags/desktop/common/footer" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%-- CECS-46 main navigation - START --%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%-- CECS-46 main navigation - END --%>

<template:master pageTitle="${pageTitle}">

	<jsp:attribute name="pageCss">
		<jsp:invoke fragment="pageCss"/>
	</jsp:attribute>
 
	<jsp:attribute name="pageScripts">
		<jsp:invoke fragment="pageScripts"/>
	</jsp:attribute>

	<jsp:body>

        <header:header hideHeaderLinks="${hideHeaderLinks}"/>
            <%--
			<cms:pageSlot position="Breadcrumb" var="component">
				<cms:component component="${component}"/>
			</cms:pageSlot>
			--%>
			<!-- CECS-46 main navigation - END -->
			<header:bottomHeader />
			<cart:cartRestoration />
			<a id="skip-to-content"></a>
			<jsp:doBody/>
		</div>
        <footer:footer/>

	</jsp:body>
	
</template:master>
