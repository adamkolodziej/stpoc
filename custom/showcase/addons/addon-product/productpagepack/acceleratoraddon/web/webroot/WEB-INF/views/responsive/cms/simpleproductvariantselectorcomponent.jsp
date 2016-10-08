<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="variant" tagdir="/WEB-INF/tags/responsive/variant" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${selectorType eq 'apparel'}">
		<variant:apparelVariantSelector product="${product}" />	
	</c:when>
	<c:otherwise>
		<product:productVariantSelector product="${product}" />	
	</c:otherwise>
</c:choose>	