<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="productSet" tagdir="/WEB-INF/tags/addons/productsets/desktop/productset" %>

<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="variants" required="true" type="java.util.Set" %>
<%@ attribute name="imageFormat" required="false" type="java.lang.String" %>

<c:if test="${empty imageFormat}" >
	<c:set var="imageFormat" value="thumbnail" />
</c:if>

<c:url value="${product.url}" var="productUrl"/>

<h1>${product.name}</h1>
<p class="big-price">
	<format:fromPrice priceData="${product.price}"/>
</p>
<div class="summary">
<p>
	${product.summary}
</p>
</div>
<span class="product-image-container">
	<a href="${productUrl}" class="">
		<product:productPrimaryImage product="${product}" format="${imageFormat}"/>
	</a>
</span>
<div class="item_details">				
	<fieldset id="product_container_${product.code}" class="preloader_container">
		<div class="prod_add_to_cart">
			<productSet:quantityField product="${product}" />
		</div><!--/prod_add_to_cart-->
		<div>			
			<productSet:genericVariantSelector code="${product.code}" variants="${variants}" baseStockLevel="${product.stock.stockLevelStatus.code}" />
		</div>
	</fieldset>				
</div><!--/item_details-->