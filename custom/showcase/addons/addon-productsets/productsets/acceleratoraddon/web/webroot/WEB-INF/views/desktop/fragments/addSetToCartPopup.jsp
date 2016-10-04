<%@ page trimDirectiveWhitespaces="true" contentType="application/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

{	"cartData": {
		"total": 	"${cartData.totalPrice.value}",
		"products": [
		<c:forEach items="${cartData.entries}" var="cartEntry" varStatus="status">
			{
				"sku":		"${cartEntry.product.code}",
				"name": 	"${cartEntry.product.name}",
				"qty": 		"${cartEntry.quantity}",
				"price": 	"${cartEntry.basePrice.value}",
				"categories": [
				<c:forEach items="${cartEntry.product.categories}" var="category" varStatus="categoryStatus">
					"${category.name}"<c:if test="${not categoryStatus.last}">,</c:if>
				</c:forEach>
				]
			}<c:if test="${not status.last}">,</c:if>
		</c:forEach>
		]
	},
	"cartPopupHtml":	"<spring:escapeBody javaScriptEscape="true">
							<spring:theme code="text.addToCart" var="addToCartText"/>
							<c:url value="/cart" var="cartUrl"/>
							<c:url value="/cart/checkout" var="checkoutUrl"/>
						<div id="addToCartLayer">
							<div class="title">
								<h3> <spring:theme code="basket.added.to.basket" /></h3>
								<a href="#" class="close" id="add_to_cart_close"></a>
							</div>

							<div class="cart_modal_popup">
								<c:if test="${errorMsg}">
									<div class="cart_popup_error_msg">
										<spring:theme code="${errorMsg}" />
									</div>
								</c:if>
							
								<ul>
									<c:forEach items="${entries}" var="entry">
										<c:url value="${entry.product.url}" var="entryProductUrl"/>
										<li class="cart_modal_popup">
											<div class="prod_image">
												<a href="${entryProductUrl}">
													<product:productPrimaryImage product="${entry.product}" format="cartIcon"/>
												</a>
											</div>
											<c:choose>
												<c:when test="${not empty entry.errorMsg}">
													<div class="prod_info">
														<p>${entry.product.name}</p>
														<div class="cart_popup_error_msg">
															<spring:theme code="${entry.errorMsg}" />
														</div>
													</div>
												</c:when>
												<c:otherwise>
													<div class="prod_info">
														<p>${entry.product.name}</p>
														<p class="prod_options">
															<c:forEach items="${entry.product.baseOptions}" var="baseOptions">
																<c:forEach items="${baseOptions.selected.variantOptionQualifiers}" var="baseOptionQualifier">
																	<c:if test="${baseOptionQualifier.qualifier eq 'style' and not empty baseOptionQualifier.image.url}">
																		<span class="prod_color">
																			<spring:theme code="product.variants.colour"/>&nbsp;
																			<img src="${baseOptionQualifier.image.url}"  alt="${baseOptionQualifier.value}" title="${baseOptionQualifier.value}"/>
																		</span>
																	</c:if>
																	<c:if test="${baseOptionQualifier.qualifier eq 'size'}">
																		<span class="prod_size"><spring:theme code="product.variants.size"/>&nbsp;${baseOptionQualifier.value}</span>
																	</c:if>
																</c:forEach>
															</c:forEach>
														</p>
														<p class="prod_quantity"><spring:theme code="popup.cart.quantity.added"/>&nbsp;${entry.quantity}</p>
														<div class="prod_price">
															<format:price priceData="${entry.product.price}"/>
														</div>
													</div>
												</c:otherwise>
											</c:choose>
											
										</li>
									</c:forEach>
								</ul>								
							</div>

							<div class="links">
								<a href="${cartUrl}" class="button positive">
									<spring:theme code="checkout.checkout" />
								</a>
							</div>
							</div>
						</spring:escapeBody>"
}
