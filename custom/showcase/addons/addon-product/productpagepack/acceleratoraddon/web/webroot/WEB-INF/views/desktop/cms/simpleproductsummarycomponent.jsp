<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>

<c:choose>
<c:when test="${not empty product.summary}">
<div class="summary">
	${product.summary}
</div>
</c:when>
<c:otherwise>
	<component:emptyComponent/>
</c:otherwise>
</c:choose>