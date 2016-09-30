<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/addons/showcasecommonaddon/desktop/product" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/desktop/action" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>

<c:choose>
	<c:when test="${not empty productData}">
		<div class="row full-width featured-collections-component ${cssClass} product-carousel" >
			<div class="carousel-component-bg"></div>
			<div class="col-xs-12">
				<h3 class="highlight-title">
					${title}
					<c:if test="${not empty subtitle}">
						<small>
							<spring:theme code="${subtitle}"/>
						</small>
					</c:if>
				</h3>
				<c:if test="${showSeeAllBtn and not empty componentQuery}">
					<c:url var="searchUrl" value="${componentQuery}"/>
					<a class="btn btn-sptel-secondary text-uppercase see-all" href="${searchUrl}">
						<spring:theme code="slickproductcarouselcomponent.see.all"/>
					</a>
				</c:if>
				<div class="carousel-container" style="background: url('${bannerUrl}') 0 0 no-repeat ${backgroundColor};">
					<div class="carousel-multi" data-pctsl="1">
						<c:forEach items="${productData}" var="product">
							<c:choose>
								<c:when test="${not empty component.actions}">
									<c:set var="reqProduct" value="${product}" scope="request" />
									<action:actions element="a href" styleClass="product" parentComponent="${component}" />
								</c:when>
								<c:otherwise>
									<c:url var="viewProductUrl" value="${product.url}" />
									<div class="release">
										<c:set var="hasPromotion" value=""/><%-- it needs to be reset because it's looped --%>
										<c:if test="${not empty product.potentialPromotions and not empty product.potentialPromotions[0].productBanner}">
											<c:set var="hasPromotion" value="promotion"/>
										</c:if>
										<a href="${viewProductUrl}" data-index="1" class="product ${hasPromotion}">
											<c:if test="${not empty product.potentialPromotions and not empty product.potentialPromotions[0].productBanner}">
												<img class="promo-image" src="${product.potentialPromotions[0].productBanner.url}" alt="${product.potentialPromotions[0].description}" title="${product.potentialPromotions[0].description}"/>
											</c:if>
											<product:responsiveProductPrimaryImage cssClass="img-responsive multirowfixedproductset-img-width" product="${product}" format="product" />
											<!-- TV Episode product type -->
											<c:choose>
												<c:when test="${not empty product.tvShowName}">
													<div class="release-description">
														<h4 class="title text-uppercase">
															${product.tvShowName}
															<small class="text-uppercase">
																${product.name}
															</small>
														</h4>
														<c:if test="${not empty product.categories}">
															<span class="subtitle">${product.categories[0].name}</span>
														</c:if>
												</c:when>
												<c:otherwise>
													<div class="merchandise-description">
														<span class="title">${product.name}</span>
												</c:otherwise>
											</c:choose>
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
											</div>
										</a>
									</div>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</c:when>

	<c:otherwise>
		<component:emptyComponent/>
	</c:otherwise>
</c:choose>