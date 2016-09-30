<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ attribute name="buttonText" required="false" type="java.lang.String" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ attribute name="cssStyle" required="false" type="java.lang.String"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

<spring:theme code="productlists.addtolist" arguments="${listType}" var="fallbackButtonMessage" />
<c:url value="/productlists/prodlist/additem" var="plAddItemUrl" />

<a id="addToProdListButton" class="js-add-to-product-list ${cssStyle}"
		data-url="${plAddItemUrl}" data-product-code="${product.code}">${not empty buttonText ? buttonText : fallbackButtonMessage}</a>