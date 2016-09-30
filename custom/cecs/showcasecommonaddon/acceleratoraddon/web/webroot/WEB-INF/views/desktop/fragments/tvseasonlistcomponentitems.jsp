<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="showcase" tagdir="/WEB-INF/tags/addons/showcasecommonaddon/desktop/product" %>

<c:forEach items="${ seasonsEpisodeList }" var="episode">
	<li class="media episode">
		<div class="media-left media-top">
			<a href="<c:url value="${ episode.url }" />"><product:productPrimaryImage product="${ episode }" format="product" cssClass="media-object"/></a>
		</div>
		<div class="media-body row">
			<div class="col-xs-12">
				<h4>
					<a href="<c:url value="${ episode.url }" />" class="media-heading">${ episode.episodeNumber }. ${ episode.name }</a>
				</h4>
			</div>
			<div class="col-xs-12 col-sm-9">
				<span class="episode-description">
					${ episode.description }
				</span>
				<ul class="tags list-unstyled list-inline">
					<li class="tag"><fmt:formatDate pattern="MMMM dd,yyyy" value="${ episode.releaseDate }" /></li>
					<li class="duration">${ episode.running }&nbsp;<spring:theme code="text.TVSeasonListComponent.minutes" /></li>
				</ul>
			</div>
			<div class="col-xs-12 col-sm-3">
				<showcase:watchBuyButton episodeProduct="${episode}" />
			</div>
		</div>
	</li>
</c:forEach>
