<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>


<c:choose>
	<c:when test="${not empty productReferences and component.maximumNumberProducts > 0}">
		<div class="row full-width episodes-container">
			<div class="col-xs-12 suggest-component">
				<h3>${title}</h3>
			</div>
		</div>
		<div class="row full-width dark-bg suggest-component-container">
			<div class="col-xs-12">
				<div class="suggest-tvshow-carousel">
                	<c:forEach end="${component.maximumNumberProducts}" items="${productReferences}" var="productReference">
						<c:url value="${productReference.target.url}/quickView" var="productQuickViewUrl"/>
                		<a href="${productQuickViewUrl}" class="suggest-tvshow-item release">
							<product:productPrimaryImage product="${productReference.target}" format="product"/>
                			<div class="merchandise-description">
                				<h4>${not empty productReference.target.tvShowName ? productReference.target.tvShowName : productReference.target.name}</h4>
                				<h6>
                					<c:if test="${not empty productReference.target.episodeNumber}">
                						<spring:theme code="tvshow.episode" />&nbsp;${productReference.target.episodeNumber},&nbsp;
                					</c:if>
                					<c:if test="${not empty productReference.target.seasonNumber}">
                                    	<spring:theme code="tvshow.season" />&nbsp;${productReference.target.seasonNumber}
                                    </c:if>
                				</h6>
							</div>
                		</a>
                	</c:forEach>
                </div>
			</div>
		</div>
	</c:when>

	<c:otherwise>
		<component:emptyComponent/>
	</c:otherwise>
</c:choose>
