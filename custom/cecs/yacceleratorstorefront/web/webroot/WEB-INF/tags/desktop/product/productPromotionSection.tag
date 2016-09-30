<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<div class="bundle">
	<ycommerce:testId code="productDetails_promotion_label">
		<c:if test="${not empty product.potentialPromotions}">
			<c:choose>
				<c:when test="${not empty product.potentialPromotions[0].couldFireMessages}">
					<p>${product.potentialPromotions[0].couldFireMessages[0]}</p>
				</c:when>
				<c:otherwise>
					<c:if test="${not empty product.potentialPromotions[0].newPrice}">
						<p class="promotion">
							<img class="promo-image" src="${product.potentialPromotions[0].productBanner.url}" alt="${product.potentialPromotions[0].description}" title="${product.potentialPromotions[0].description}"/>
							<span class="oldPrice"><format:price priceData="${product.price}"/></span>&nbsp;&nbsp;
							<format:price priceData="${product.potentialPromotions[0].newPrice}" />
						</p>
					</c:if>
					<c:if test="${empty product.potentialPromotions[0].productBanner}">
						product.potentialPromotions[0].description
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:if>
	</ycommerce:testId>
</div>