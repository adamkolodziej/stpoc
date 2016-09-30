<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.OrderData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>

<%@ attribute name="containerCSS" required="false" type="java.lang.String" %>

<ul class="list-group">
    <li class="list-group-item active"><spring:theme code="text.account.order.orderTotals" text="Order Totals"/></li>
    <li class="list-group-item"><spring:theme code="text.account.order.subtotal" text="Subtotal:"/> <span class="badge"><format:price priceData="${order.subTotal}"/></span></li>
<c:if test="${order.totalDiscounts.value > 0}">
    <li class="list-group-item"><spring:theme code="text.account.order.savings" text="Savings:"/> <span class="badge"><format:price priceData="${order.totalDiscounts}"/></span></li>
</c:if>
    <li class="list-group-item"><spring:theme code="text.account.order.delivery" text="Delivery:"/> <span class="badge"><format:price priceData="${order.totalDiscounts}"/></span></li>
<c:if test="${order.net}" >
    <li class="list-group-item"><spring:theme code="text.account.order.netTax" text="Tax:"/> <span class="badge"><format:price priceData="${order.totalTax}"/>/span></li>
</c:if>
    <li class="list-group-item list-group-item-info">
        <spring:theme code="text.account.order.total" text="Total:"/>
        <span class="badge badge-transparent">
            <c:choose>
                <c:when test="${order.net}">
                    <format:price priceData="${order.totalPriceWithTax}"/>
                </c:when>
                <c:otherwise>
                    <format:price priceData="${order.totalPrice}"/>
                </c:otherwise>
            </c:choose>
        </span>
    </li>
</ul>
