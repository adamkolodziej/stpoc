<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="row">
	<div class="col-xs-12 text-center mock-center">
		<h1 class="text-center">
			<spring:theme code="tco.favorite.movies.h1" />
			<small>
				<spring:theme code="tco.favorite.movies.h1.small" />
			</small>
		</h1>
		<c:url value="/packages/TriCast" var="tricastPack" />
		<img src="${commonResourcePath}/images/homepage-mock/start-watching-bg.png" alt="" class="img-responsive"/>
		<a href="${tricastPack}">
			<button class="btn btn-sptel-primary btn-lg text-uppercase"><spring:theme code="tco.favorite.movies.subscribe.now" /></button>
		</a>
	</div>
</div>