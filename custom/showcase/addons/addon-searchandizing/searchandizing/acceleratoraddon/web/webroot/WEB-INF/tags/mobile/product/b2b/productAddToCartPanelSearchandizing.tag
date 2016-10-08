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

<%@ attribute name="product" required="true"
	type="de.hybris.platform.commercefacades.product.data.ProductData"%>

<spring:theme code="text.addToCart" var="addToCartText" />
<c:url value="${product.url}" var="productUrl" />
<c:set var="showAddToCart" value="${true}"/>
<c:if test="${not empty variantStyles}">
	<c:set var="showAddToCart" value="${false}"/>
</c:if>
<div class="prod_add_to_cart" data-theme="b">
<div class="prod_add_to_cart_submit">
	<form id="addToCartForm" class="add_to_cart_form" action="<c:url value="/cart/add"/>" method="post">
		<div class="ui-grid-a">
			<div class="ui-block-a">
				<div class="prod_add_to_cart_quantity">
					<c:choose>
						<c:when test="${product.purchasable and product.stock.stockLevelStatus.code ne 'outOfStock'}">
							<input type="hidden" name="qty" id="qty" value="1" />
						</c:when>
						<c:otherwise>
							<input type="hidden" name="qty" id="qty" value="1" />
						</c:otherwise>
					</c:choose>
					<input type="hidden" name="productCodePost" value="${product.code}"/>
				</div>
			</div>
		<div id='addToBasket'>
			<c:set var="buttonType">button</c:set>
			<c:choose>
				<c:when test="${product.stock.stockLevelStatus.code ne 'outOfStock'}">
					<c:set var="buttonType">submit</c:set>
					<spring:theme code="text.addToCart" var="addToCartText"/>
					<button type="${buttonType}" data-rel="dialog" data-transition="pop" data-theme="b" class="positive large <c:if test="${fn:contains(buttonType, 'button')}">out-of-stock</c:if>">
						<spring:theme code="text.addToCart" var="addToCartText"/>
						<spring:theme code="basket.add.to.basket"/>
					</button>
				</c:when>
				<c:otherwise>
					<c:if test="${showAddToCart}">
						<spring:theme code="text.addToCart" var="addToCartText"/>
						<button type="${buttonType}" data-rel="dialog" data-transition="pop" data-theme="b"
							class="positive large" disabled='true'>
							<spring:theme code="product.variants.out.of.stock"/>
						</button>
					</c:if>
				</c:otherwise>
			</c:choose>
		</div>
		<div id='pickUpInStore'>
			<%--Buy Reserve Online and Collect in Store --%>
			<c:if test="${showAddToCart and product.availableForPickup}">
				<a href="#" class="pickUpInStoreButton" data-productCode="${product.code}" data-rel="dialog" data-transition="pop" data-role="button" data-theme="c">
					<spring:theme code="pickup.in.store"/>
				</a>
			</c:if>
		</div>
	</form>
</div>
</div>