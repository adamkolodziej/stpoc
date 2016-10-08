<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>


<%@ taglib prefix="addon" tagdir="/WEB-INF/tags/addons/searchandizing/mobile/product/b2c" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/mobile/storepickup"%>

<c:choose>
	<c:when test="${not empty productData}">
	<div class="span-20 last">
		<div class="spotlight_holder">
			<div class="spotlight_item">
				<addon:productSpotlightItem product="${productData[0]}"/>
			</div>
			<c:if test="${fn:length(productData) gt 1}">
				<div class="spotlight_item last">
					<addon:productSpotlightItem product="${productData[1]}"/>
				</div>
			</c:if>
		</div>
	</div>
	</c:when>
	<c:otherwise>
		<component:emptyComponent/>
	</c:otherwise>
</c:choose>