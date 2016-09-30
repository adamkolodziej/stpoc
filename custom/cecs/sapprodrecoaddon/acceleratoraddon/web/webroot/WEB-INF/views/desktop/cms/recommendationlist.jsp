<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/desktop/action" %>

<c:set var="ulid" value="recommendationUL${recoId}"/>

<jsp:useBean id="random" class="java.util.Random" scope="application" />
<c:set var="divId" value="div${random.nextInt(1000)}"/>

<c:choose>
	<c:when test="${not empty productReferences}">
		<div id="${divId}" class="row grey related-items">
			<h3 class="text-uppercase text-center">${title}</h3>
			<c:forEach items="${productReferences}" var="productReference">
				<div class="col-xs-6 col-md-3 release recommendation-item">
					<c:url value="${productReference.target.url}" var="productQuickViewUrl"/>
					<a href="${productQuickViewUrl}"
					   onclick="registerClickthrough('${productReference.target.code}', '${itemType}', '${recoType}', document.baseURI, event.target.src)">
						<product:productPrimaryImage product="${productReference.target}" format="product" cssClass="img-responsive"/>
					</a>
					<div class="priceContainer"><format:fromPrice priceData="${productReference.target.price}"/></div>
					<h3 class="title">
						<c:forTokens items="${productReference.target.name}" delims=" " var="namePart" varStatus="stat"
									 begin="0" end="5">
							<c:set var="productName" value="${stat.first ? '' : productName} ${namePart}" />
						</c:forTokens>
						<c:if test="${fn:length(productName) < fn:length(productReference.target.name)}">
							<c:set var="productName" value="${productName}..." />
						</c:if>
						<c:out value="${productName}"/>
					</h3>
						<%-- // SE-2867 Upsell section on guidedselling --%>
					<c:set var="product" value="${productReference.target}" scope="request" />
					<div id="actions-container-for-${component.uid}" class="productAddToCartPanelContainer clearfix">
						<ul class="productAddToCartPanel clearfix list-unstyled list-inline">
							<action:actions element="li" styleClass="productAddToCartPanelItem " parentComponent="${component}"/>
						</ul>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:when>

	<c:otherwise>
	</c:otherwise>
</c:choose>