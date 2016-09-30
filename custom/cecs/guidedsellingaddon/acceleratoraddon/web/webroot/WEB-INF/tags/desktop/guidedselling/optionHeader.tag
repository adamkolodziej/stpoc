<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="imageOnly" required="false" type="java.lang.Boolean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>


<c:set value="zoom" var="format" />
<c:set value="${ycommerce:productImage(product, format)}" var="primaryImage" />
<c:choose>
	<c:when test="${imageOnly}">
		<c:if test="${not empty primaryImage}">
			<product:productPrimaryImage product="${product}" format="zoom" cssClass="img-responsive" showMissing="false" />
		</c:if>
	</c:when>
	<c:otherwise>
	<div class="media">
		<div class="media-left media-middle">
			<c:if test="${not empty primaryImage}">
				<product:productPrimaryImage product="${product}" format="zoom" cssClass="media-object" showMissing="false" />
			</c:if>
		</div>
		<div class="media-body media-middle">
			<h4 class="media-heading">${product.name}</h4>
		</div>
	</div>
	</c:otherwise>
</c:choose>
