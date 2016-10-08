<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>

<c:choose>
<c:when test="${not empty banner}">
<c:url value="${url}" var="encodedUrl" />


<div class="simple_banner">
	<c:choose>
		<c:when test="${empty encodedUrl || encodedUrl eq '#'}">
			<img title="${banner.altText}" alt="${banner.altText}" src="${banner.url}">
		</c:when>
		<c:otherwise>		
			<a href="${encodedUrl}" title="${component.link.linkName}" ${component.link.target == null || component.link.target == 'SAMEWINDOW' ? '' : 'target="_new"'}><img title="${banner.altText}" alt="${banner.altText}" src="${banner.url}"></a>
		</c:otherwise>
	</c:choose>
</div>
</c:when>
<c:otherwise>
	<component:emptyComponent/>
</c:otherwise>
</c:choose>