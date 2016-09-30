<%@ page trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>


<span class="price ${cssClass}">
<c:choose>
	<c:when test="${empty product.volumePrices}">
		<div class="big-price right">
			<product:productPromotionSection product="${product}"/>
			<c:if test="${empty product.potentialPromotions }">
				<format:fromPrice priceData="${product.price}" />
			</c:if>
		</div>
	</c:when>
	<c:otherwise>
		<table class="volume-prices" cellpadding="0" cellspacing="0" border="0">
			<thead>
					<th class="volume-prices-quantity"><spring:theme code="product.volumePrices.column.qa"/></th>
					<th class="volume-price-amount"><spring:theme code="product.volumePrices.column.price"/></th>
			</thead>
			<tbody>
				<c:forEach var="volPrice" items="${product.volumePrices}">
					<tr>
						<td class="volume-price-quantity">
							<c:choose>
								<c:when test="${empty volPrice.maxQuantity}">
									${volPrice.minQuantity}+
								</c:when>
								<c:otherwise>
									${volPrice.minQuantity}-${volPrice.maxQuantity}
								</c:otherwise>
							</c:choose>
						</td>
						<td class="volume-price-amount">${volPrice.formattedValue}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>
</span>