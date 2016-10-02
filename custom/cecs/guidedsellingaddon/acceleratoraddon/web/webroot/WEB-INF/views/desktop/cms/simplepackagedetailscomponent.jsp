<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="gs"
	tagdir="/WEB-INF/tags/addons/guidedsellingaddon/desktop/guidedselling"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:if test="${renderComponent eq false}">
	<div class="options">
		<div class="row">
			<div class="col-xs-12">

				<div class="col-xs-12 col-sm-3 ">
					<div class="image-container text-center">
						<gs:packagePrimaryImage product="${packageData}"
							cssClass="img-responsive" showMissing="true" />
					</div>
				</div>
				<ul class="package-title list-inline">
					<li>${packageData.name}</li>
				</ul>

				<c:choose>
					<c:when
						test="${packageData.subscription.subscriptionTerm.termOfServiceNumber <= 1}">
						<c:set var="prettyFrequency"
							value="${fn:replace(packageData.subscription.subscriptionTerm.termOfServiceFrequency.name,'(s)','')}" />
					</c:when>

					<c:otherwise>
						<c:set var="prettyFrequency"
							value="${fn:replace(packageData.subscription.subscriptionTerm.termOfServiceFrequency.name,'(s)','s')}" />
					</c:otherwise>
				</c:choose>

				</br></br>
				<span class="contract-time text-dark">
					${packageData.subscription.subscriptionTerm.termOfServiceNumber}&nbsp;${prettyFrequency}&nbsp;<spring:theme code="guidedselling.contract.length.description" />
				</span>
				</br></br>

				<div class="${cssClass}">
					<c:url value="/packages/${bundleTemplateId}" var="packagesUrl" />
					<a class="pull-left text-info" href="${packagesUrl}"> <spring:theme code="guidedselling.simplepackagedetailscomponent.changePackage" text="Change package" /></a>
				</div>
			</div>
		</div>
	</div>
</c:if>
