<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="com.hybris.showcase.guidedselling.data.BundlePackageData" %>
<%-- CECS add class parameter --%>
<%@ attribute name="cssClass" required="false" type="java.lang.String" %>
<%@ attribute name="showMissing" required="false" type="java.lang.Boolean" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>

<%-- CECS show missing image START --%>
<c:if test="${empty showMissing}">
	<c:set var="showMissing" value="${true}" />
</c:if>
<%-- CECS show missing image END--%>

<c:choose>
	<c:when test="${not empty product.thumbnail}">
		<c:choose>
			<c:when test="${not empty product.thumbnail.altText}">
                <img src="${product.thumbnail.url}" alt="${fn:escapeXml(product.thumbnail.altText)}" title="${fn:escapeXml(product.thumbnail.altText)}" class="${cssClass}"/><%-- CECS add class parameter --%>
			</c:when>
			<c:otherwise>
                <img src="${product.thumbnail.url}" alt="${fn:escapeXml(product.name)}" title="${fn:escapeXml(product.name)}" class="${cssClass}"/><%-- CECS add class parameter --%>
			</c:otherwise>
		</c:choose>
	</c:when>
	<%-- CECS show missing image START --%>
	<c:when test="${showMissing}">
		<theme:image code="guidedselling.img.missingPackageImage" alt="${fn:escapeXml(product.name)}" title="${fn:escapeXml(product.name)}" cssClass="${cssClass}"/><%-- CECS add class parameter --%>
	</c:when>
	<%-- CECS show missing image END--%>
</c:choose>