<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="format" required="true" type="java.lang.String" %>
<%@ attribute name="cssClass" required="true" type="java.lang.String" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set value="${ycommerce:productImage(product, format)}" var="primaryImage"/>

<c:choose>
	<c:when test="${not empty primaryImage}">
		<c:choose>
			<c:when test="${not empty primaryImage.altText}">
				<img class="${cssClass}" src="${primaryImage.url}" alt="${fn:escapeXml(primaryImage.altText)}" title="${fn:escapeXml(primaryImage.altText)}"/>
			</c:when>
			<c:otherwise>
				<img class="${cssClass}" src="${primaryImage.url}" alt="${fn:escapeXml(product.name)}" title="${fn:escapeXml(product.name)}"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<spring:theme code="img.missingProductImage.${format}" text="/" var="imagePath"/>
        <c:choose>
        	<c:when test="${originalContextPath ne null}">
        		<c:url value="${imagePath}" var="imageUrl" context="${originalContextPath}"/>
        	</c:when>
        	<c:otherwise>
        		<c:url value="${imagePath}" var="imageUrl" />
        	</c:otherwise>
        </c:choose>
        <img class="${cssClass}" src="${imageUrl}" alt="${fn:escapeXml(product.name)}" title="${fn:escapeXml(product.name)}" />
	</c:otherwise>
</c:choose>