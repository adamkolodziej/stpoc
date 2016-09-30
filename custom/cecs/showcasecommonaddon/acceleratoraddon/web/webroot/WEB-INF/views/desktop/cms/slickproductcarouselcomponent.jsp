<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="utils" uri="http://hybris.com/tld/showcasecommonaddonutils" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/addons/showcasecommonaddon/desktop/product" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/desktop/action" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>

<c:choose>
	<c:when test="${not empty productData}">
		<div class="${cssClass} row product-carousel">
			<div class="col-xs-12">
				<!-- CECS-227 Consumption site - watching video on a tablet START -->
				<div class="row highlight">
					<div class="col-xs-12">
						<h3 class="highlight-title">${title}
							<c:if test="${not empty subtitle}">
								<small>${subtitle}</small>
							</c:if>
						</h3>
						<c:if test="${showSeeAllBtn and not empty componentQuery}">
							<c:url var="searchUrl" value="${componentQuery}"/>
							<a class="btn btn-sptel-secondary text-uppercase see-all" href="${searchUrl}">
								<spring:theme code="slickproductcarouselcomponent.see.all"/>
							</a>
						</c:if>
						<div class="clearfix"></div>
						<div class="row merchandise-list">
							<c:forEach items="${productData}" var="product">
							<c:url var="viewProductUrl" value="${product.url}" />
								<div class="col-xs-12 col-sm-6 col-md-3">
									<c:choose>
										<c:when test="${not empty component.actions}">
											<c:set var="reqProduct" value="${product}" scope="request" />
											<action:actions element="a href" styleClass="product" parentComponent="${component}" />
										</c:when>
										<c:otherwise>
												<c:set var="hasPromotion" value=""/><%-- it needs to be reset because it's looped --%>
												<c:if test="${not empty product.potentialPromotions and not empty product.potentialPromotions[0].productBanner}">
													<c:set var="hasPromotion" value="promotion"/>
												</c:if>
												<a href="${viewProductUrl}" class="box merchandise ${hasPromotion}">
													<c:if test="${not empty product.potentialPromotions and not empty product.potentialPromotions[0].productBanner}">
														<img class="promo-image" src="${product.potentialPromotions[0].productBanner.url}" alt="${product.potentialPromotions[0].description}" title="${product.potentialPromotions[0].description}"/>
													</c:if>
													<div class="img-container">
														<product:responsiveProductPrimaryImage cssClass="img-responsive" product="${product}" format="product" />
													</div>
													<div class="merchandise-description">
														<span class="title">${product.name}</span>
														<c:if test="${showRates}">
															<component:rates maxDisplayedRatesAmount="5" filledRatesAmount="${product.averageRating}" classForFullRate="fa-star" classForEmptyRate="fa-star-o" classForHalfRate="fa-star-half-o"/>
														</c:if>
														<c:if test="${showPrice}">
															<div class="price-container">
																<ul class="list-inline list-unstyled">
																	<li class="regular"><format:fromPrice priceData="${product.price}"/></li>
																	<c:if test="${not empty product.potentialPromotions and not empty product.potentialPromotions[0].newPrice}">
																		<li class="discount"><format:fromPrice priceData="${product.potentialPromotions[0].newPrice}"/></li>
																	</c:if>
																</ul>
															</div>
														</c:if>
														<c:if test="${not empty classificationValue }">
															<span>${utils:getCustomFieldStringValue(product, classificationValue) }</span>
														</c:if>
														<c:if test="${not  empty classificationCollection }">
															<span>
																<c:forEach items="${utils:getCustomFieldCollectionValue(product, classificationCollection) }" var="item" varStatus="loop">
																	<c:out value="${item.replace('[', '').replace(']', '')}" /><c:if test="${!loop.last}"> & </c:if>
																</c:forEach>
															</span>
														</c:if>
														<c:if test="${not empty product.categories}">
															<span class="subtitle">${product.categories[0].name}</span>
														</c:if>
													</div>
												</a>
										</c:otherwise>
									</c:choose>
								</div>
							</c:forEach>
							<!-- CECS-227 Consumption site - watching video on a tablet END -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:when>

	<c:otherwise>
		<component:emptyComponent/>
	</c:otherwise>
</c:choose>