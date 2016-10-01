<!-- CECS-46 main navigation -->
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="breadcrumb"
	tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>

<c:if test="${fn:length(breadcrumbs) > 0}">
	<div class="row full-width breadcrumb-section hidden-xs">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
	</div>
</c:if>