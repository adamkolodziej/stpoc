<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>

<c:if test="${not empty componentId}">
	<div class="facebookSuggestedProductsContainer" data-componentId="${componentId}"></div>
</c:if>

<c:if test="${empty componentId}">
	<component:emptyComponent/>
</c:if>