<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>

<c:choose>
	<c:when test="${not empty productReferences and component.maximumNumberProducts > 0}">
		<div class="scroller col-xs-12">
			<div class="title">${component.title}</div>
			<div class="row">
				<c:forEach end="${component.maximumNumberProducts}" items="${productReferences}" var="productReference">
					<div class="col-xs-12 col-sm-6 col-md-2">
						<c:url value="${productReference.target.url}/quickView" var="productQuickViewUrl"/>
						<a href="${productQuickViewUrl}" class="box merchandise ">
							<div class="img-container">
								<product:productPrimaryImage product="${productReference.target}" format="product" cssClass="img-responsive"/>
							</div>
							<div class="merchandise-description">
								<c:if test="${component.displayProductTitles}">
									<span class="title">${productReference.target.name}</span>
								</c:if>
								<c:if test="${showRates}">
									<component:rates maxDisplayedRatesAmount="5" filledRatesAmount="${productReference.target.averageRating}" classForFullRate="fa-star" classForEmptyRate="fa-star-o" classForHalfRate="fa-star-half-o"/>
								</c:if>
								<c:if test="${component.displayProductPrices}">
									<div class="price-container">
										<ul class="list-inline list-unstyled">
											<li class="regular">
												<format:fromPrice priceData="${productReference.target.price}"/>
											</li>
										</ul>
									</div>
								</c:if>
							</div>
						</a>
					</div>
				</c:forEach>
			</div>
		</div>
	</c:when>

	<c:otherwise>
		<component:emptyComponent/>
	</c:otherwise>
</c:choose>