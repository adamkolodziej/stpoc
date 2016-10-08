<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="productSet" tagdir="/WEB-INF/tags/addons/productsets/desktop/productset"%>

<%@ attribute name="productSet" required="true" type="com.hybris.productsets.facades.data.ProductSetData"%>

<div>
	<c:if test="${not empty productSet.promotions}">
		<c:choose>
			<c:when test="${not empty productSet.promotions[0].couldFireMessages}">
				<p>${productSet.promotions[0].couldFireMessages[0]}</p>
			</c:when>
			<c:when test="${not empty productSet.promotions[0].description}">
				<p>${productSet.promotions[0].description}</p>
			</c:when>
			<c:otherwise>
				<p>${productSet.promotions[0].title}</p>
			</c:otherwise>
		</c:choose>
	</c:if>
</div>