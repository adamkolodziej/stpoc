<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="newsletter box">
	<!-- FIXME Hardcoded. When requirements arrive, rework it. -->
	<h3 class="title"><spring:theme code="text.BlogNewsletterComponent.header" /></h3>
	<span><spring:theme code="text.BlogNewsletterComponent.description" /></span>
	<form class="form-horizontal">
	    <input type="text" name="name" id="inputID" class="form-control" value="" title="" required="required" placeholder="<spring:theme code="text.BlogNewsletterComponent.placeholder" />" >
	</form>
	<a href="#" class="btn btn-sptel-secondary text-uppercase"><spring:theme code="text.BlogNewsletterComponent.singUpButton" /></a>
</div>