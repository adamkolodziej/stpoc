<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${not empty media}">
		<div class="dynamic_disp-img simple-banner">
			<div class="title">${title}	</div>
			<div class="thumb">
				<img title="${media.altText}" alt="${media.altText}" src="${media.url}">
			</div>
			<div class="details">
				<p>${content}</p>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<component:emptyComponent/>
	</c:otherwise>
</c:choose>