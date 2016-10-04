<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="wishlistNav span-4">
	<div class="headline">
		<spring:theme code="productList.navigation_component.title" />
	</div>
	<c:if test="${not empty productLists}">
		<ul>
			<c:url value="/productlists/view/" var="viewListUrl" />
			<c:set var="currentProductListGuid">
				<c:if test="${productListData != null}">${productListData.guid}</c:if>
			</c:set>
			<c:forEach items="${productLists}" var="pl">
				<c:set var="plClassStyle" value="${currentProductListGuid != null && currentProductListGuid == pl.guid ? 'activeList' : ''}"/>
				<li><a class="${plClassStyle}" href="${viewListUrl}${pl.guid}">${pl.name}&nbsp;(${fn:length(pl.entries)})</a></li>
			</c:forEach>
		</ul>
		</c:if>
</div>
