<%@ page trimDirectiveWhitespaces="true" contentType="application/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="gs" tagdir="/WEB-INF/tags/addons/guidedsellingaddon/desktop/guidedselling" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="ic" tagdir="/WEB-INF/tags/addons/instantcheckoutaddon/desktop" %>

{
    "granted": "${granted}",
    "expiredGrants": [
        <c:forEach var="grant" items="${expiredGrants}" varStatus="status">
        {
            <spring:theme var="message" code="ems.notentitled.expired" text="{0} has expired" arguments="${grant.name}" />
            "grantName": "${grant.name}",
            "message": "${message}"
        } <c:if test="${not status.last}">,</c:if>
        </c:forEach>
    ],
    "proposals": [
        <c:set var="format" value="cartIcon" />

        <c:forEach var="proposal" items="${proposals}" varStatus="status">
            <c:set var="product" value="${proposal.product}" />
            <c:set var="productImage" value="${ycommerce:productImage(product, format)}" />
            <c:url var="actionUrl" value="${proposal.activationUrl}" />
            <c:if test="${not empty productImage}">
                <c:set var="productImage" value="${productImage.url}" />
            </c:if>
            <c:if test="${empty productImage}">
                <spring:theme code="img.missingProductImage.${format}" text="/" var="productImage"/>
            </c:if>
            {
                "code": "${product.code}",
                "name": "${product.name}",
                "image": "${productImage}",
                "url": "${actionUrl}",
                <c:if test="${product.price['class'].simpleName eq 'SubscriptionPricePlanData'}">
                    "price": "${product.price.recurringChargeEntries[0].price.formattedValue}",
                    "priceValue": "${product.price.recurringChargeEntries[0].price.value}",
                    "billingTime": "${product.subscriptionTerm.billingPlan.billingTime.name}"
                </c:if>
                <c:if test="${product.price['class'].simpleName ne 'SubscriptionPricePlanData'}">
                    "price": "${product.price.formattedValue}",
                    "priceValue": "${product.price.value}",
                    "billingTime": "paynow"
                </c:if>
            } <c:if test="${not status.last}">,</c:if>
        </c:forEach>
    ],
    "htmlOutput": "<spring:escapeBody javaScriptEscape="true">
    <c:if test="${not granted}">
        <div class="not-entitled-container">
            <h1 class="title">
                <spring:theme code="ems.notentitled" text="Not entitled" />
            </h1>
            <p class="description">
                <spring:theme code="ems.notentitled.details" text="You are not entitled to watch this video. Check out our offer now." />
            </p>
            <ul class="expired-grants">
            <c:forEach var="grant" items="${expiredGrants}">
                <li class="alert alert-danger"><spring:theme code="ems.notentitled.expired" text="{0} has expired" arguments="${grant.name}" /></li>
            </c:forEach>
            </ul>
            <div class="row js-eqh">
                <c:forEach var="proposal" items="${proposals}">
                    <c:set var="product" value="${proposal.product}" />

                    <c:url var="actionUrl" value="${proposal.activationUrl}" />
                    <div class="n-category-option js-entitlement-option col-xs-12 col-sm-6 col-md-3">
                        <div class="box no-padding">
                            <div class="details js-details">
                                <div class="media">
                                    <div class="media-left media-middle">
                                        <product:productPrimaryImage product="${product}" format="zoom" cssClass="media-object" showMissing="false" />
                                    </div>
                                    <div class="media-body media-middle">
                                        <h4 class="media-heading">${product.name}</h4>
                                          <c:if test="${not empty product.episodeNumber and not empty product.seasonNumber}">
                                                <div>
                                                    <h4 class="media-heading"> ${product.tvShowName} </h4>
                                                </div>
                                                <div>
                                                 <h5 class="media-heading"> S<fmt:formatNumber type="number"  maxIntegerDigits="3"  pattern="#00" value="${product.seasonNumber}" />E<fmt:formatNumber type="number"  maxIntegerDigits="3"  pattern="#00" value="${product.episodeNumber}" />
                                                 </h5>
                                                </div>
                                           </c:if>

                                        <c:set var="description" value="{{opt.product.description}}" />
                                        <c:if test="${descriptionActive and not empty description}">
                                            <div>
                                                ${description}
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <c:choose>
                                <c:when test="${product.itemType eq 'Product'}">
                                    <div class="category-footer evaluation-footer">
                                        <div class="btn-group">
                                            <a href="${actionUrl}" class="btn check-btn">
                                                <div class="price-container">
                                                    <ul class="list-inline list-unstyled">
                                                        <c:if test="${opt.product.loyaltyPointsPrice eq null}">
                                                            <gs:price product="${product}" />
                                                        </c:if>
                                                    </ul>
                                                </div>
                                            </a>
                                            <ic:instantcheckout product="${product}" successCallbackFunJS="refreshMoviePage" cssClass="btn btn-primary" noContainer="true"/>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <a href="${actionUrl}">
                                        <div class="category-footer">
                                            <div  class="btn btn-link btn-block check-btn">
                                                <div class="price-container">
                                                    <ul class="list-inline list-unstyled">
                                                        <c:if test="${opt.product.loyaltyPointsPrice eq null}">
                                                            <gs:price product="${product}" />
                                                        </c:if>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:if>
</spring:escapeBody>"
}
