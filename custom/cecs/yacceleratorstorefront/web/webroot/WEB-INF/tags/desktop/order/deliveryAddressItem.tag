<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.OrderData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="hasShippedItems" value="${order.deliveryItemsQuantity > 0}" />
<ul class="list-group orderBox address">
    <li class="list-group-item active"><spring:theme code="text.deliveryAddress" text="Delivery Address"/></li>
	<c:if test="${not hasShippedItems}">
        <li class="list-group-item"><spring:theme code="checkout.pickup.no.delivery.required"/></li>
	</c:if>
	<c:if test="${hasShippedItems}">
        <li class="list-group-item">${fn:escapeXml(order.deliveryAddress.title)} ${fn:escapeXml(order.deliveryAddress.firstName)} ${fn:escapeXml(order.deliveryAddress.lastName)} </li>
        <li class="list-group-item">${fn:escapeXml(order.deliveryAddress.line1)}</li>
        <li class="list-group-item">${fn:escapeXml(order.deliveryAddress.line2)}</li>
        <li class="list-group-item">${fn:escapeXml(order.deliveryAddress.town)}</li>
        <li class="list-group-item">${fn:escapeXml(order.deliveryAddress.region.name)}</li>
        <li class="list-group-item">${fn:escapeXml(order.deliveryAddress.postalCode)}</li>
        <li class="list-group-item">${fn:escapeXml(order.deliveryAddress.country.name)}</li>
	</c:if>
</ul>
