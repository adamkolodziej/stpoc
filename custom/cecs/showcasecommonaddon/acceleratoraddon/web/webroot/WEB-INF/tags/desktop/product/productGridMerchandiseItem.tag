<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/desktop/storepickup" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/desktop/action" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>

<spring:theme code="text.addToCart" var="addToCartText"/>
<c:url value="${product.url}" var="productUrl"/>

<c:set value="${not empty product.potentialPromotions}" var="hasPromotion"/>

<ycommerce:testId code="product_wholeProduct">
	<div class="productGridMerchandiseItem ${hasPromotion ? 'productGridItemPromotion' : ''} col-xs-6 col-sm-4 col-md-3">
        <c:if test="${not empty product.potentialPromotions and not empty product.potentialPromotions[0].productBanner}">
            <c:set var="hasPromotion" value="promotion"/>
        </c:if>
		<a href="${productUrl}" title="${product.name}" class="productMainLink product ${hasPromotion}">
            <c:if test="${not empty product.potentialPromotions and not empty product.potentialPromotions[0].productBanner}">
                <img class="promo-image" src="${product.potentialPromotions[0].productBanner.url}" alt="${product.potentialPromotions[0].description}" title="${product.potentialPromotions[0].description}"/>
            </c:if>
            <product:productPrimaryImageBackground product="${product}" format="product"/>
            <div class="product-description">
                <c:choose>
                    <c:when test="${not empty product.tvShowName and not empty product.seasonNumber and not empty product.episodeNumber}">
                    <!-- TV Episode product type -->
                        <span class="name">
                            <span class="inner">
                                <ycommerce:testId code="product_tvShowName">${product.tvShowName}<br/></ycommerce:testId>
                                <ycommerce:testId code="product_productName">
                                    <spring:theme code="productdetails.tvepisodeproducttype.seasonandepisodeformat" arguments="${product.seasonNumber},${product.episodeNumber}"/>&nbsp;${product.name}
                                </ycommerce:testId>
                            </span>
                        </span>
                    </c:when>
                    <c:otherwise>
                        <span class="name">
                            <span class="inner">
                                <ycommerce:testId code="product_productName">${product.name}</ycommerce:testId>
                            </span>
                        </span>
                    </c:otherwise>
                </c:choose>

                <component:rates maxDisplayedRatesAmount="5" filledRatesAmount="${product.averageRating > 0 ? product.averageRating : 0}" classForFullRate="fa-star" classForEmptyRate="fa-star-o" classForHalfRate="fa-star-half-o"/>

                <c:set var="buttonType">submit</c:set>
                <ycommerce:testId code="product_productPrice">
                    <c:if test="${not empty product.price}">
                    	<div class="price-container">
                    		<ul class="list-inline list-unstyled">
                    			<li class="regular"><span class="price"><format:fromPrice priceData="${product.price}"/></span></li>
                    			<c:if test="${not empty product.potentialPromotions and not empty product.potentialPromotions[0].newPrice}">
                    				<li class="product-discount"><format:fromPrice priceData="${product.potentialPromotions[0].newPrice}"/></li>
                    			</c:if>
                    		</ul>
                    	</div>
                    </c:if>
                </ycommerce:testId>
            </div>
		</a>
	</div>
</ycommerce:testId>
