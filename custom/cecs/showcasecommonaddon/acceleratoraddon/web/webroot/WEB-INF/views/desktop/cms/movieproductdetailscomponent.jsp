<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/desktop/action"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="showcase" tagdir="/WEB-INF/tags/addons/showcasecommonaddon/desktop/product" %>

<div class="row full-width dark-bg product-summary-container"
	style="background-image:url('${backgroundImage}');">
	<div class="col-xs-12">
		<div class="row">
			<div class="col-xs-12">
				<h1 class="product-title">
					${product.name}&nbsp<small>(${ product.releaseYear })</small>
				</h1>
			</div>
			<div class="col-xs-12 col-sm-2 product-image-container text-center">
				<product:productPrimaryImage product="${product}" format="product" cssClass="img-responsive" />
			</div>
			<div class="col-xs-12 col-sm-8 season-description-container">
				<h4 class="number">
					<c:forEach var="genre" items="${product.genre}" varStatus="loop">
						<c:out value="${genre}" />
						<c:if test="${!loop.last}"> & </c:if>
					</c:forEach>
				</h4>
				
				<span class="season-description">${product.description}</span>
				
				<h4 class="number">
					<spring:theme code="text.MovieProductDetailsComponent.castAndCredits" />
				</h4>
				
				<span class="starring">
					<b><spring:theme code="text.MovieProductDetailsComponent.actors" />:</b>
					<c:forEach var="actor" items="${product.starring}" varStatus="loop">
						<c:out value="${actor}" /><c:if test="${!loop.last}">, </c:if>
					</c:forEach>
				</span>
				<span class="starring">
					<b><spring:theme code="text.MovieProductDetailsComponent.productionCompanies" />:</b>
					<c:forEach var="producer" items="${product.productionCompanies}" varStatus="loop">
						<c:out value="${producer}" />
						<c:if test="${!loop.last}">, </c:if>
					</c:forEach>
				</span>
				<span class="starring">
					<b><spring:theme code="text.MovieProductDetailsComponent.director" />:</b>
					<c:forEach var="director" items="${product.director}" varStatus="loop">
						<c:out value="${director}" />
						<c:if test="${!loop.last}">, </c:if>
					</c:forEach>
				</span>
				
				<c:if test="${not empty blog}">
				    <div class="additional-info">
					    <span class="title text-yellow"> ${blog.title}</span>
					    <span>
					     ${blog.summary}
					    </span>
				    </div>
				</c:if>
			</div>
			<div class="col-xs-12 col-sm-2">
				<div class="btn-group btn-group-vertical" role="group">
					<showcase:watchBuyButton movieProduct="${product}" />
					<a href="#" class="btn btn-link text-info">
						<spring:theme code="text.MovieProductDetailsComponent.morePurchaseOptions" />
					</a>
					<action:actions parentComponent="${component}"/>
				</div>
			</div>
		</div>
	</div>
</div>