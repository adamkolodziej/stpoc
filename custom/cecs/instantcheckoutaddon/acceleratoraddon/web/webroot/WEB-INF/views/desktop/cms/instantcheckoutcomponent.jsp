<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="ic" tagdir="/WEB-INF/tags/addons/instantcheckoutaddon/desktop" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ic:instantcheckout product="${productForInstCheckout}" showPrice="${displayPrice}" disabled="${empty productForInstCheckout.variantType ? false : true}">
	<jsp:attribute name="linkContent" >
		<c:if test="${empty buttonLabel}">
			<spring:theme code="text.instantcheckoutcomponent.buywithoneclick" text="Buy with 1-click" />
		</c:if>
		<c:if test="${not empty buttonLabel}">
			${buttonLabel}
		</c:if>
	</jsp:attribute>
</ic:instantcheckout>
