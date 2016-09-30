<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>

<template:page pageTitle="${pageTitle}">
	<div id="globalMessages">
		<common:globalMessages/>
	</div>

	<div class="row">
		<div class="col-xs-12 wide-content-slot advert">
			<cms:pageSlot var="feature" position="TopContent">
				<cms:component component="${feature}"/>
			</cms:pageSlot>
		</div>	
		
		<div class="col-xs-12 item_container_holder subscription_billing_activity">
            <h2><spring:theme code="text.account.subscription.billingActiviy" text="Billing Activity"/></h2>
            <div class="item_container">
				<h2 class="text-secondary">
				${subscriptionData.name}<br/>
                <small>${subscriptionData.description}</small>
                </h2>


  				<c:if test="${not empty billingActivities}">
                    <div class="table-responsive">

                        <table id="billingActivities" class="table table-bordered table-condensed table-striped text-dark">
                            <thead>
                                <tr>
                                    <th id="header1"><spring:theme code="text.account.subscription.billingActivity.billingPeriod" text="Billing Period"/></th>
                                    <th id="header2"><spring:theme code="text.account.subscription.billingActivity.billingDate" text="Billing Date"/></th>
                                    <th id="header3"><spring:theme code="text.account.subscription.billingActivity.paymentAmount" text="Payment Amount"/></th>
                                    <th id="header4"><spring:theme code="text.account.subscription.billingActivity.paymentStatus" text="Payment Status"/></th>
                                    <th id="header5"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${billingActivities}" var="billingActivity">

                                    <c:url value="/my-account/downloadSubscriptionBillingActivityDetail" var="billingActivityDetailUrl"/>

                                    <tr>
                                        <td headers="header1">
                                            <ycommerce:testId code="subscription_billing_activity_billingPeriod">
                                                <p>${billingActivity.billingPeriod}</p>
                                            </ycommerce:testId>
                                        </td>
                                        <td headers="header2">
                                            <ycommerce:testId code="subscription_billing_activity_billingDate">
                                                <p>${billingActivity.billingDate}</p>
                                            </ycommerce:testId>
                                        </td>
                                        <td headers="header3">
                                            <ycommerce:testId code="subscription_billing_activity_paymentAmount">
                                                <p>${billingActivity.paymentAmount}</p>
                                            </ycommerce:testId>
                                        </td>
                                        <td headers="header4">
                                            <ycommerce:testId code="subscription_billing_activity_paymentStatus">
                                                <p>${billingActivity.paymentStatus}</p>
                                            </ycommerce:testId>
                                        <td headers="header5">
                                            <c:url value="/my-account/downloadSubscriptionBillingActivityDetail" var="downloadBillingActivityDetailUrl" />
                                            <form:form class="resume_subscription_form" id="resumeSubscriptionForm" action="${downloadBillingActivityDetailUrl}" method="get">
                                                <c:if test="${billingActivity.paymentStatus == 'Paid'}">
                                                    <button type="submit" class="function_btn btn btn-primary btn-sm">
                                                        <spring:theme code="text.account.subscription.billingActivity.download" text="Download"/>
                                                        &nbsp;<i class="fa fa-download"></i>
                                                    </button>
                                                    <input type="hidden" name="billingActivityId" value="${billingActivity.billingId}"/>
                                                </c:if>
                                            </form:form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
				</c:if>
				<c:if test="${empty billingActivities}">
                    <div class="alert alert-warning">
					    <spring:theme code="text.account.subscriptions.noSubscriptions" text="You have no subscriptions"/>
                    </div>
				</c:if>

            </div>
		</div>
        <div class="col-xs-12">
            <c:url value="/my-account/subscription/${subscriptionData.id}" var="backToSubscriptionDetails"/>
            <button type="submit" onclick="window.location='${backToSubscriptionDetails}'" class="r_action_btn btn btn-primary"><spring:theme code="text.account.subscription.returnToSubscriptionDetails" text="Return To Subscription Details"/></button>
        </div>

	</div>
</template:page>
