<!-- CECS-151: Most Viewed Component -->
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/addons/showcasecommonaddon/desktop/product" %>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/desktop/action" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>

<c:choose>
	<c:when test="${not empty productData}">
		<div class="row ${cssClass} product-carousel">
			<div class="carousel-component-bg"></div>
			<div class="col-xs-12">
				<div class="header">
					<h3 class="text-uppercase">${title}</h3>
					<c:if test="${showSeeAllBtn and not empty componentQuery}">
						<c:url var="searchUrl" value="${componentQuery}"/>
						<a class="see-all text-uppercase" href="${searchUrl}">
							<spring:theme code="slickproductcarouselcomponent.see.all"/> <i class="fa fa-angle-double-right"></i>
						</a>
					</c:if>
				</div>
				<div class="carousel-container" >
					<div class="carousel-multi" data-pctsl="1">
						<c:forEach items="${productData}" var="product">
							<c:url var="viewProductUrl" value="/video/v/${product.name}" />
							<div class="release">

								<!-- CECS-227 Consumption site - watching video on a tablet START -->
								<c:choose>
									<c:when test="${not empty component.actions}">
										<c:set var="reqProduct" value="${product}" scope="request" />
										<action:actions element="a href" styleClass="product" parentComponent="${component}" />
									</c:when>
									<c:otherwise>
										<a href="${viewProductUrl}" class="product">
											<product:responsiveProductPrimaryImage cssClass="img-responsive multirowfixedproductset-img-width" product="${product}" format="product" />
											<div class="release-description">
												<h3 class="subtitle text-uppercase">${product.name}</h3>
												<span class="subtitle multirowfixedproductset-genre-span">
													<c:if test="${not empty product.classifications}">
														<c:forEach items="${product.classifications}" var="classification">
															<c:forEach items="${classification.features}" var="feature">
																<c:if test="${not empty feature && feature.name == 'Genre'}">
																	<c:forEach items="${feature.featureValues}" var="value">
																		${value.value}
																	</c:forEach>
																</c:if>
															</c:forEach>
														</c:forEach>
													</c:if>
												</span>
											</div>
										</a>
									</c:otherwise>
								</c:choose>
								<!-- CECS-227 Consumption site - watching video on a tablet END -->
							</div>
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