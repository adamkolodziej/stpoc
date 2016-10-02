<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="priceCss" required="false" %>
<%@ attribute name="newPrice" required="false" type="de.hybris.platform.commercefacades.product.data.PriceData" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>

<c:set var="priceCss" value="guidedselling-price ${priceCss}" />
    <c:if test="${product.price['class'].simpleName eq 'SubscriptionPricePlanData'}">
        <format:price priceData="${product.price.recurringChargeEntries[0].price}"/>
        &nbsp;${product.subscriptionTerm.billingPlan.billingTime.name}
    </c:if>
    <c:if test="${product.price['class'].simpleName ne 'SubscriptionPricePlanData'}">
        <format:price priceData="${product.price}"/>
    </c:if>
<c:if test="${not empty newPrice}">
        <br><format:price priceData="${newPrice}"/>
</c:if>