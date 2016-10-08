<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ attribute name="buttonText" required="false" type="java.lang.String" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>


<div class="prod_add_to_prodlist">
	<c:url value="/productlists/prodlist/additem" var="plAddItemUrl" />
	<input type="hidden" name="productCodePost" value="${product.code}" />
	<spring:theme code="productlists.addtolist" arguments="${listType}" var="fallbackButtonMessage" />

	<button id="addToProdListButton" type="button" class="positive large"
		onclick="plAddToProductList('${plAddItemUrl}', '${product.code}')">${not empty buttonText ? buttonText : fallbackButtonMessage}</button>
</div>
