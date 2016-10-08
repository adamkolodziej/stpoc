<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/mobile/product"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/mobile/cart"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/mobile/storepickup"%>
<%@ taglib prefix="addon" tagdir="/WEB-INF/tags/addons/searchandizing/mobile/product/b2b" %>

<%@ attribute name="product" required="true"
	type="de.hybris.platform.commercefacades.product.data.ProductData"%>


<spring:theme code="text.addToCart" var="addToCartText" />
<c:url value="${product.url}" var="productUrl" />

<storepickup:findPickupStores product="${product}" />

<div class="spotlight  clearfix">

	<!--title_holder-->
	<div class="title_holder">
		<!--title-->
		<h2>${product.manufacturer}&nbsp;${product.name}</h2>
	</div>

	<div class="thumb">
		<div class="thumb-left">
			<a href="${productUrl}"> <product:productPrimaryImage product="${product}" format="thumbnail" zoomable="false" />
			</a>
		</div>
		<div class="thumb-right">
			<div>
				<ycommerce:testId code="searchPage_price_label_${product.code}">
					<p class="price">
						<format:price priceData="${product.price}" />
						<c:if test="${product.stock.stockLevelStatus.code eq 'lowStock' }">
							<span class='listProductLowStock '><spring:theme code="product.variants.only.left" arguments="${product.stock.stockLevel}" /></span>
						</c:if>
					</p>
				</ycommerce:testId>
			</div>
			<div>
				<span class="stars large" style="display: inherit;"> <c:if
						test="${not empty product.averageRating}">
						<span
							style="width: <fmt:formatNumber maxFractionDigits="0" value="${product.averageRating * 24}" />px;"></span>
					</c:if>
				</span>
			</div>
		</div>
	</div>
	<!--thumb-->
	<div class="details">
		<p style="display: none;">${product.summary}</p>
		<a href="${productUrl}" class="ui-btn ui-shadow ui-btn-corner-all ui-btn-up-c">
			<span class="ui-btn-inner ui-btn-corner-all">
				<span class="ui-btn-text"><spring:theme code="searchandizing.text.moreInfo" /></span>
			</span>
		</a>
		<%--<addon:productAddToCartPanelSearchandizing product="${product}" /> --%>
	</div>
	<!--details-->

</div>
<!--banner-->