<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>

<%@ attribute name="episodeProduct" required="false" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="movieProduct" required="false" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="seasonProduct" required="false" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="episodeToWatch" required="false" %>


<c:if test="${ not empty episodeProduct }">
	<a href="<c:url value="${ episodeProduct.viewUrl }" />" class="btn btn-tricast-primary text-uppercase btn-block js-buy-watch-button" style="overflow: hidden" data-code="${episodeProduct.code}" data-state="loading">
		<span class="js-loading buy-watch-button-loading-text"><spring:theme code="text.TVShowProduct.watchBuyLoading" /></span>
		<span class="js-watch buy-watch-button-watch-text"><spring:theme code="text.TVSeasonListComponent.watchEpisode" /></span>
		<span class="js-watch-now buy-watch-now-button-watch-now-text"><spring:theme code="text.TVSeasonListComponent.watchNowEpisode" /></span>
		<span class="js-buy buy-watch-button-buy-text"><spring:theme code="text.TVSeasonListComponent.buyEpisode" /></span>
		<span class="js-price buy-watch-button-buy-price"><format:price priceData="${ episodeProduct.price }"/></span>
		<span class="js-watch-now-price buy-watch-button-buy-price"></span>
	</a>
</c:if>

<c:if test="${ not empty movieProduct }">
	<a href="<c:url value="${ movieProduct.viewUrl }" />" class="btn btn-tricast-primary text-uppercase btn-block js-buy-watch-button" data-code="${movieProduct.code}" data-state="loading">
		<span class="js-loading buy-watch-button-loading-text"><spring:theme code="text.TVShowProduct.watchBuyLoading" /></span>
		<span class="js-watch buy-watch-button-watch-text"><spring:theme code="text.MovieProductDetailsComponent.watchMovie" /></span>
		<span class="js-watch-now buy-watch-now-button-watch-now-text"><spring:theme code="text.MovieProductDetailsComponent.watchNowMovie" /></span>
		<span class="js-buy buy-watch-button-buy-text"><spring:theme code="text.MovieProductDetailsComponent.buyMovie" /></span>
		<span class="js-price buy-watch-button-buy-price"><format:price priceData="${ movieProduct.price }"/></span>
		<span class="js-watch-now-price buy-watch-button-buy-price"></span>
	</a>
</c:if>

<c:if test="${ not empty seasonProduct }">
	<a href="<c:url value="/video/v/${episodeToWatch}"/>" class="btn btn-tricast-primary text-uppercase js-buy-watch-button" data-code="${seasonProduct.code}" data-state="loading">
		<span class="js-loading buy-watch-button-loading-text"><spring:theme code="text.TVShowProduct.watchBuyLoading"/></span>
		<span class="js-watch buy-watch-button-watch-text"><spring:theme code="text.TVShowProductDetailsComponent.watchSeason" arguments="${seasonProduct.seasonNumber}" htmlEscape="false" /></span>
		<span class="js-watch-now buy-watch-now-button-watch-now-text"><spring:theme code="text.TVShowProductDetailsComponent.watchNowSeason" arguments="${seasonProduct.seasonNumber}" htmlEscape="false" /></span>
		<span class="js-buy buy-watch-button-buy-text"><spring:theme code="text.TVShowProductDetailsComponent.buySeason" arguments="${seasonProduct.seasonNumber}" htmlEscape="false" /></span>
		<span class="js-price buy-watch-button-buy-price"><format:price priceData="${ seasonProduct.price }"/></span>
		<span class="js-watch-now-price buy-watch-button-buy-price"></span>
	</a>
</c:if>

<div class="js-buy-watch-error-message buy-watch-button-error-text"></div>