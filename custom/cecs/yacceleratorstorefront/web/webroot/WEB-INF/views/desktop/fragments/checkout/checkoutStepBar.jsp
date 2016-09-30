<%@ page trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>


<div data-role="navbar" class="checkoutStep">
	<ul data-theme="b">
		<c:forEach items="${checkoutSteps}" var="checkoutStep" varStatus="status">
			<c:url value="${checkoutStep.url}" var="stepUrl"/>
			<c:choose>
				<c:when test="${progressBarId eq checkoutStep.progressBarId}">
					<c:set scope="page"  var="currentStepActive"  value="${checkoutStep.stepNumber}"/>
					<li class="step${checkoutStep.stepNumber} current">
						<a href="${stepUrl}">				
							<span>
								<spring:theme code="${checkoutStep.stepNumber}" />
							</span>
							<div>
								<%--<span><spring:theme code="guidedselling.checkout.step${checkoutStep.stepNumber}" /></span>--%>
								<span><spring:theme code="guidedselling.checkout.step${checkoutStep.stepNumber}.name" /></span>
							</div>
							<div class="arrow"></div>
						</a>
					</li>
					
				</c:when>
				<c:when test="${checkoutStep.stepNumber > currentStepActive }">
					<li class="checkoutStep20 step${checkoutStep.stepNumber} disabledStep">
						<a href="${stepUrl}">				
							<span>
								<spring:theme code="${checkoutStep.stepNumber}" />
							</span>
							<div>
								<%--<span><spring:theme code="guidedselling.checkout.step${checkoutStep.stepNumber}" /></span>--%>
								<span><spring:theme code="guidedselling.checkout.step${checkoutStep.stepNumber}.name" /></span>
							</div>
							<div class="arrow"></div>
						</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="checkoutStep20 step${checkoutStep.stepNumber}">
						<a href="${stepUrl}">				
							<span>
								<spring:theme code="${checkoutStep.stepNumber}" />
							</span>
							<div>
								<%--<span><spring:theme code="guidedselling.checkout.step${checkoutStep.stepNumber}" /></span>--%>
								<span><spring:theme code="guidedselling.checkout.step${checkoutStep.stepNumber}.name" /></span>
							</div>
							<div class="arrow"></div>
						</a>
					</li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>
</div>

