<%@ page trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

	<ul class="nav nav-tabs margin-top-20 margin-bottom-30" role="tablist">
		<c:forEach items="${checkoutSteps}" var="checkoutStep" varStatus="status">
			<c:url value="${checkoutStep.url}" var="stepUrl"/>
			<c:choose>
				<c:when test="${progressBarId eq checkoutStep.progressBarId}">
					<c:set scope="page"  var="currentStepActive"  value="${checkoutStep.stepNumber}"/>
					<li role="presentation" class="active">
					    <a href="#" onclick="window.location='${stepUrl}'" aria-controls="${checkoutStep.stepNumber}" role="tab" data-toggle="tab">
					        <spring:theme code="guidedselling.checkout.step${checkoutStep.stepNumber}.name" />
					    </a>
					</li>
				</c:when>
				<c:when test="${checkoutStep.stepNumber > currentStepActive }">
					<li role="presentation">
                        <a href="#" onclick="window.location='${stepUrl}'" aria-controls="${checkoutStep.stepNumber}" role="tab" data-toggle="tab">
                    	    <spring:theme code="guidedselling.checkout.step${checkoutStep.stepNumber}.name" />
                    	</a>
                    </li>
				</c:when>
				<c:otherwise>
					<li role="presentation">
                        <a href="#" onclick="window.location='${stepUrl}'" aria-controls="${checkoutStep.stepNumber}" role="tab" data-toggle="tab">
                    	    <spring:theme code="guidedselling.checkout.step${checkoutStep.stepNumber}.name" />
                    	</a>
                    </li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>

