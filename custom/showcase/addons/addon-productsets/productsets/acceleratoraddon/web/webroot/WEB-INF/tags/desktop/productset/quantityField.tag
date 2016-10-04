<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="baseProductCode" required="false" type="java.lang.String" %>

<c:if test="${empty baseProductCode}">
	<c:set var="baseProductCode" value="${product.code}" />
</c:if>

<label for="qty"><spring:theme code="buytheset.quantity" text="Quantity*" /></label>
<c:if test="${product.stock.stockLevel gt 0}">
	<c:set var="productStockLevel">${product.stock.stockLevel}&nbsp;<spring:theme code="product.variants.in.stock"/></c:set>
</c:if>
<c:if test="${product.stock.stockLevelStatus.code eq 'lowStock'}">
	<c:set var="productStockLevel"><spring:theme code="product.variants.only.left" arguments="${product.stock.stockLevel}"/></c:set>
</c:if>
<c:if test="${product.stock.stockLevelStatus.code eq 'inStock' and empty product.stock.stockLevel}">
	<c:set var="productStockLevel"><spring:theme code="product.variants.available"/></c:set>
</c:if>
<c:if test="${product.stock.stockLevelStatus.code eq 'outOfStock'}">
	<c:set var="productStockLevel"><spring:theme code="product.variants.out.of.stock"/></c:set>
</c:if>
<input id="product_quantity_${product.code}" name="product_${product.code}" type="number" maxlength="3" min="0" size="1" class="qty ${product.stock.stockLevelStatus.code eq 'outOfStock' ? 'out-of-stock' : ''}" value="${product.stock.stockLevelStatus.code eq 'outOfStock' ? 0 : 1}" ${product.stock.stockLevelStatus.code eq 'outOfStock' ? 'disabled':''}>
<p id="stock_info_${baseProductCode}" class="stock_message">${productStockLevel}</p>