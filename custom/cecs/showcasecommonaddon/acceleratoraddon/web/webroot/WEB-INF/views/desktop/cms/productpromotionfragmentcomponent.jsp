<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="ic" tagdir="/WEB-INF/tags/addons/instantcheckoutaddon/desktop" %>
<%@ taglib prefix="productList" tagdir="/WEB-INF/tags/addons/productlists/desktop/productlists" %>
<c:url var="viewProductUrl" value="${product.url}" />

<div class="panel-heading">
    <h3 class="panel-title">${product.name}</h3>
    <a href="#" class="panel-close text-danger">
        <i title="<spring:theme code="label.btn.close"/>" class="fa fa-close"></i>
    </a>
</div>
<div class="panel-body text-center">
    <div class="product-promotion-fragment-image">
        <a href="${viewProductUrl}">
            <c:set var="hasPromotion" value=""/><%-- it needs to be reset because it's looped --%>
        	<c:if test="${not empty product.potentialPromotions and not empty product.potentialPromotions[0].productBanner}">
        		<c:set var="hasPromotion" value="promotion"/>
	        	<img class="promo-image" src="${product.potentialPromotions[0].productBanner.url}" alt="${product.potentialPromotions[0].description}" title="${product.potentialPromotions[0].description}"/>
	        </c:if>
        	<product:productPrimaryImage product="${product}" format="thumbnail"/>
        </a>
    </div>
    <div class="product-promotion-fragment-description">
    <span class="label label-warning ${hasPromotion}">
        <format:price priceData="${product.price}"/>
    </span>
    	<c:if test="${not empty hasPromotion}">
        	<span class="newPrice"><format:price priceData="${product.potentialPromotions[0].newPrice}"/></span>
        </c:if>
        <span><spring:theme code="product.paynow"/></span>
    </div>
</div>
<div id="promotion-actions-${component.uid}" class="panel-footer block-buttons">
    <div class="row">
        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
            <div class="instantcheckout-container">
                  <spring:theme code="label.btn.review" var="reviewLabel" />
                  <productList:productAddToProductListPanel buttonText="${reviewLabel}" product="${product}" cssStyle="product-promotion-fragment-btn btn btn-primary btn-block"/>
            </div>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
            <ic:instantcheckout product="${product}" showPrice="${false}" cssClass="product-promotion-fragment-btn btn btn-sptel-primary btn-block" responseDiv="promotion-actions-${component.uid}">
                <jsp:attribute name="linkContent" >
                    <spring:theme code="text.instantcheckoutcomponent.buynow" text="BUY NOW" />
                </jsp:attribute>
            </ic:instantcheckout>
        </div>
    </div>
</div>