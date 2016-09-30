<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="format" required="true" type="java.lang.String" %>
<%-- CECS add class parameter --%>
<%@ attribute name="cssClass" required="false" type="java.lang.String" %>
<%@ attribute name="showMissing" required="false" type="java.lang.Boolean" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:set value="${ycommerce:productImage(product, format)}" var="primaryImage"/>

<%-- CECS show missing image START --%>
<c:if test="${empty showMissing}">
	<c:set var="showMissing" value="${true}" />
</c:if>
<%-- CECS show missing image END--%>

<c:choose>
	<c:when test="${not empty primaryImage}">
		<c:choose>
			<c:when test="${not empty primaryImage.altText}">
                <img src="${primaryImage.url}" alt="${fn:escapeXml(primaryImage.altText)}" title="${fn:escapeXml(primaryImage.altText)}" class="${cssClass}"/><%-- CECS add class parameter --%>
			</c:when>
			<c:otherwise>
                <img src="${primaryImage.url}" alt="${fn:escapeXml(product.name)}" title="${fn:escapeXml(product.name)}" class="${cssClass}"/><%-- CECS add class parameter --%>
			</c:otherwise>
		</c:choose>
	</c:when>
	<%-- CECS show missing image START --%>
	<c:when test="${showMissing}">
		<theme:image code="img.missingProductImage.${format}" alt="${fn:escapeXml(product.name)}" title="${fn:escapeXml(product.name)}" cssClass="${cssClass}"/><%-- CECS add class parameter --%>
	</c:when>
	<%-- CECS show missing image END--%>
</c:choose>