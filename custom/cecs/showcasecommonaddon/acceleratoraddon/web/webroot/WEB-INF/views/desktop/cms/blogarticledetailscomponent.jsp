<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="${cssClass}" id="${blogArticle.code}">
	<div class="article box">
		<div class="col-xs-12">
			<h1>
				<strong>${productTitle}</strong> <span class="thin">${blogArticle.title}</span>
			</h1>
			<ul class="tags list-inline">
				<%-- We dont have category series on demand so what we should add? Now I fetch product category name.--%>
				<li class="tag text-uppercase"><a href="#">${productCategory}</a></li>
				<li class="date text-uppercase"><fmt:formatDate type="date"
																dateStyle="long" value="${blogArticle.publishDate}"/></li>
			</ul>
		</div>
		<div class="post-img">
			<img src="${blogArticle.blogImage.URL}"
				 alt="${blogArticle.blogImage.altText}" class="img-responsive" />
		</div>
		<div class="col-xs-12 article-body">
			<section>${blogArticle.content}</section>
		</div>
		<div class="col-xs-12">
			<ul class="list-unstyled list-inline links">
				<li><spring:theme code="text.BlogArticlesDetails.relatedTo" /></li>
				<li><a href="#">${itemOfInterestLocale}</a></li>
			</ul>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<button class="btn btn-link" ng-click="showArticleList()">
			<i class="fa fa-arrow-left"></i>
			Back
		</button>
	</div>
</div>