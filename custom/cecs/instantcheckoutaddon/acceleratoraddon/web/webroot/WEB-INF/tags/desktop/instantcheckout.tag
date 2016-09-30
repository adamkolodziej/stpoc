<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="showPrice" required="false" type="java.lang.Boolean" %>
<%@ attribute name="linkContent" required="false" fragment="true" %>
<%@ attribute name="cssClass" required="false" type="java.lang.String" %>
<%@ attribute name="responseDiv" required="false" type="java.lang.String" %>
<%@ attribute name="disabled" required="false" type="java.lang.Boolean" %>
<%@ attribute name="loyaltyPayment" required="false" type="java.lang.Boolean" %>
<%@ attribute name="successCallbackFunJS" required="false" type="java.lang.String" %>
<%@ attribute name="paramsFunJS" required="false" type="java.lang.String" %>
<%@ attribute name="noContainer" required="false" type="java.lang.Boolean" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>

<c:url var="viewProductUrl" value="${product.url}" />
<c:set var="recurringPrices" value="${product.price['class'].simpleName eq 'SubscriptionPricePlanData' and not empty product.price.recurringChargeEntries}" />

<c:if test="${empty noContainer}">
    <div class="instantcheckout-container">
</c:if>
<c:choose>
    <c:when test="${product.stock.stockLevelStatus.code eq 'outOfStock' }">
        <a href="${viewProductUrl}" class="positive out-of-stock ${cssClass}">
            <spring:theme code="text.addToCart.outOfStock" text="Out of stock" />
        </a>
    </c:when>
    <%-- 	<c:when test="${empty product.price or (not product.soldIndividually and not recurringPrices)}">exclude subscriptions here, for them we need to show monthly price --%>
    <c:when test="${empty product.price}">
        <a href="${viewProductUrl}" class="positive ${cssClass}">
            <spring:theme code="text.instantcheckoutcomponent.viewoptions" text="Show More" />
        </a>
    </c:when>
    <c:otherwise>

        <c:url var="instantcheckoutUrl" value="/instantcheckout/single/${product.code}/ajax?loyaltyPayment=${loyaltyPayment}"/>
        <jsp:invoke var="linkContent" fragment="linkContent"/>

        <a href="${instantcheckoutUrl}" class="positive instantcheckout-button ${cssClass} ${disabled ? 'disabled':''}" data-response-div="${responseDiv}" data-success-callback-fn="${successCallbackFunJS}" data-params-fn="${paramsFunJS}">
            <c:choose>
                <c:when test="${not empty fn:trim(linkContent)}">
                    ${linkContent}
                </c:when>
                <c:when test="${showPrice and recurringPrices}">
                    <c:set var="price" value="${product.price.recurringChargeEntries[fn:length(product.price.recurringChargeEntries)-1].price}" />
                    <c:if test="${loyaltyPayment}">
                        <c:set var="price" value="${product.loyaltyPointsPrice}"/>
                    </c:if>
                        <span>
                            <format:fromPrice priceData="${price}"/>
                            ${product.subscriptionTerm.billingPlan.billingTime.name}
                        </span>
                </c:when>
                <c:when test="${showPrice}">
                        <span>
                            <c:if test="${loyaltyPayment}">
                                <format:fromPrice priceData="${product.loyaltyPointsPrice}"/>
                            </c:if>
                            <c:if test="${not loyaltyPayment}">
                                <format:fromPrice priceData="${product.price}"/>
                            </c:if>
                        </span>
                </c:when>
                <c:otherwise>
                    <spring:theme code="text.instantcheckoutcomponent.buynow" text="BUY NOW" />
                </c:otherwise>
            </c:choose>
        </a>
    </c:otherwise>
</c:choose>
<div class="instantcheckout-message"></div>
<c:if test="${empty noContainer}">
    </div>
</c:if>