<%@ page trimDirectiveWhitespaces="true" contentType="application/json" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

{
	"status": "${status}",
	"responseHtml": "<spring:escapeBody javaScriptEscape="true">
		<div class="order-response">
			<c:url var="relatedURL" value="${link}"/>
			<c:if test="${status eq 'success' }">
				<spring:theme code="text.instantcheckoutcomponent.ordernumber" />
				<a href="${relatedURL}">${modification.entry.originalOrderCode}</a>
			</c:if>
			<c:if test="${status ne 'success'}">
				<p>
					<spring:theme code="text.instantcheckoutcomponent.${status}" text="${status}" />
					<a href="${relatedURL}"><spring:theme code="text.instantcheckoutcomponent.clickhere" /></a>
				</p>
			</c:if>
		</div>	
	</spring:escapeBody>"
}
