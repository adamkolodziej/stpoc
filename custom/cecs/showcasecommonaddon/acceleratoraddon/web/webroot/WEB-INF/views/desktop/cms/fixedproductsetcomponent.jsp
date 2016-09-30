<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/addons/showcasecommonaddon/desktop/product" %>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/desktop/action" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>

<c:choose>
	<c:when test="${not empty productData}">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 fixed-product-title">
			<h3 class="text-uppercase">${title}</h3>
		</div>
		<div class="col-xs-12">

			<div class="row">
				<c:forEach items="${productData}" var="product">
					<fmt:formatNumber var="productsNumber" value="${12 / fn:length(productData)}" maxFractionDigits="0" />
					<c:url var="viewProductUrl" value="${product.url}" />
					<div class="col-xs-6 col-sm-${productsNumber > 1 ? productsNumber : 2} release">
						<a href="${viewProductUrl}">
							<!-- CECS-227 Consumption site - watching video on a tablet START -->
							<c:choose>
								<c:when test="${not empty component.actions}">
									<c:set var="reqProduct" value="${product}" scope="request" />
									<action:actions element="a href" styleClass="product" parentComponent="${component}" />
								</c:when>
								<c:otherwise>
									<a href="${viewProductUrl}" class="product">
										<product:responsiveProductPrimaryImage cssClass="img-responsive" product="${product}" format="product" />
									<c:choose>
										<c:when test="${not empty product.tvShowName and not empty product.seasonNumber and not empty product.episodeNumber}">
										<!-- TV Episode product type -->
											<div class="release-description">
												<h4 class="title text-uppercase">
													${product.tvShowName}
													<small class="text-uppercase">
														<spring:theme code="productdetails.tvepisodeproducttype.seasonandepisodeformat"
																		 arguments="${product.seasonNumber},${product.episodeNumber}"/>&nbsp;${product.name}
													</small>
												</h4>
											</div>
										</c:when>
										<c:otherwise>
											<div class="release-description">
												<h4 class="title text-uppercase">
													${product.name}
												</h4>
											</div>
										</c:otherwise>
									</c:choose>
									</a>
								</c:otherwise>
							</c:choose>
							<!-- CECS-227 Consumption site - watching video on a tablet END -->
							<c:if test="${displayProductName}">
								<h3 class="title">${product.name}</h3>
								<c:if test="${not empty product.categories}">
									<span class="subtitle">${product.categories[0].name}</span>
								</c:if>
							</c:if>
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