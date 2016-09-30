<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/addons/showcasecommonaddon/desktop/product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:url var="changedUrl" value="v/${reqProduct.code}" />
<c:url var="viewProductUrl" value="${fn:replace(url, 'p/{productCode}', changedUrl)}" scope="request" />

<a href="${viewProductUrl}" class="product">
	<product:responsiveProductPrimaryImage cssClass="img-responsive multirowfixedproductset-img-width" product="${reqProduct}" format="product" />
	<c:choose>
        	<c:when test="${not empty reqProduct.tvShowName and not empty reqProduct.seasonNumber and not empty reqProduct.episodeNumber}">
        	<!-- TV Episode product type -->
			<div class="release-description">
				<h4 class="title text-uppercase">
						${reqProduct.tvShowName}
					<small class="text-uppercase">
						<spring:theme code="productdetails.tvepisodeproducttype.seasonandepisodeformat"
																arguments="${reqProduct.seasonNumber},${reqProduct.episodeNumber}"/>&nbsp;${reqProduct.name}
					</small>
				</h4>
			</div>
		</c:when>
		<c:otherwise>
			<div class="release-description">
				<h4 class="title text-uppercase">
					${reqProduct.name}
				</h4>
			</div>
		</c:otherwise>
	</c:choose>
</a>