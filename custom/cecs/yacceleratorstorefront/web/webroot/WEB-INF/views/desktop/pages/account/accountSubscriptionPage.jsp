<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

<!-- CECS-113: Migrate my-account/subscription/{subscriptionId} page from telco -->
<div class="col-xs-12">
	<div class="row">

		<c:url value="${subscriptionData.productUrl}" var="productDetailsUrl"/>
		<c:url value="/my-account/order/${subscriptionData.orderNumber}" var="orderDetailsUrl"/>

		<div class="item_container_holder subscription_details col-xs-12">

			<div class="item_container row">
                <div class="col-xs-12">
                    <div id="plan_name" class="">
                        <ycommerce:testId code="subscription_productName_link">
                            <h2><a href="${productDetailsUrl}">${subscriptionData.name}&nbsp;<i class="fa fa-external-link"></i></a></h2>
                        </ycommerce:testId>
                    </div>
                    <c:if test="${fn:toUpperCase(subscriptionData.subscriptionStatus) ne 'CANCELLED' and upgradable}">
                        <c:url value="/my-account/upgradeSubscriptionComparison?subscriptionId=${subscriptionData.id}" var="upgradeSubscriptionComparisionUrl"/>
                        <%-- <button type="button" onclick="window.location='${upgradeSubscriptionComparisionUrl}'" class="positive right p_action_btn"> --%>
                        <button type="button" onclick="#" class="positive p_action_btn btn btn-success">
                            <spring:theme code="text.account.subscription.upgradeSubscriptionDetail" text="Upgrade Options"/>
                            &nbsp;<i class="fa fa-arrow-up"></i>
                        </button>
                    </c:if>

                    <div class="plan_summary text-secondary vertical-bottom">
                        <ycommerce:testId code="subscription_description_label">
                            ${subscriptionData.description}
                        </ycommerce:testId>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <ul class="list-group">
                    	<li class="list-group-item active"><spring:theme code="text.account.subscription.detail" text="Subscription Details"/></li>
                    	<li class="list-group-item">
                            <span><spring:theme code="text.account.subscription.billingFrequency" text="Billing Frequency"/>:</span>
                            <span class="label label-primary">
                                <ycommerce:testId code="subscription_billingFrequency_label">
                                    ${subscriptionData.billingFrequency}
                                </ycommerce:testId>
                            </span>
                        </li>
                        <c:if test="${subscriptionProductData != null}">
	                    	<li class="list-group-item">
	                            <span><spring:theme code="product.list.viewplans.price" text="Price"/>:</span>
	                            <strong><product:subscriptionPricesLister subscriptionData="${subscriptionProductData}"/></strong>
	                        </li>
                        </c:if>
                        <li class="list-group-item">
                            <spring:theme code="text.account.subscription.Cancellable" text="Cancellable"/>:
                            <ycommerce:testId code="subscription_status_label">
                                <c:choose>
                                    <c:when test="${subscriptionData.cancellable}">
                                        <span class="label label-success"><spring:theme code="text.account.subscription.cancellable.yes"/></span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="label label-danger"><spring:theme code="text.account.subscription.cancellable.no"/></span>
                                    </c:otherwise>
                                </c:choose>
                            </ycommerce:testId>
                        </li>
                        <c:if test="${subscriptionProductData != null}">
	                        <li class="list-group-item">
	                            <span><spring:theme code="product.list.viewplans.entitlements" text="Included"/>:</span>
	                            <span><product:entitlementLister subscriptionData="${subscriptionProductData}"/></span>
	                        </li>
                        </c:if>
                        <c:if test="${subscriptionProductData != null}">
	                        <li class="list-group-item">
	                            <span><spring:theme code="product.list.viewplans.usage.charges" text="Usage Charges"/>:</span>
	                            <span><product:usageChargesLister subscriptionData="${subscriptionProductData}"/></span>
	                        </li>
                        </c:if>
                    </ul>

                </div>
                <div class="col-xs-12 col-sm-6">
                    <ul class="list-group">
                        <li class="list-group-item list-group-item-info">
                            <spring:theme code="text.account.subscription.manageSubscription" text="Manage This Subscription"/>
                        </li>
                        <li class="list-group-item">
                            <span><spring:theme code="text.account.subscription.status" text="Status"/>:</span>
                            <ycommerce:testId code="subscription_status_label">
                                <span class="label label-primary">${subscriptionData.subscriptionStatus}</span>
                            </ycommerce:testId>
                        </li>
                        <li class="list-group-item">
                            <span class="activity"><spring:theme code="text.account.subscription.activity" text="Actitiy"/></span>
                            <span class="order_number"><spring:theme code="text.account.subscription.orderNumber" text="Order Number"/>:</span>
                            <span class="order_number"><ycommerce:testId code="subscription_orderNumber_link">
                                <c:choose>
	                                <c:when test="${subscriptionProductData != null}">
	                                	<a href="${orderDetailsUrl}">${subscriptionData.orderNumber}</a>
	                                </c:when>		                                
	                                <c:otherwise>
	                                    <a href="#">${subscriptionData.orderNumber}</a>
	                                </c:otherwise>
	                            </c:choose>
                            </ycommerce:testId>
                            </span>
                        </li>
                        <li class="list-group-item">
                            <c:url value="/my-account/subscriptionBillingActivity" var="subscriptionBillingActivityUrl" />
                            <form:form class="view_billing_activity_form" id="viewBillingActivityForm" action="${subscriptionBillingActivityUrl}" method="get">
                                <button type="submit" class="function_btn btn btn-info btn-sm">
                                    <spring:theme code="text.account.subscription.viewBillingActivity" text="View Billing Activity"/>
                                </button>
                                <input type="hidden" name="subscriptionId" value="${subscriptionData.id}"/>
                            </form:form>
                        </li>
                        <li class="list-group-item">
                            <c:url value="/my-account/changeSubscriptionState" var="changeSubscriptionStateUrl" />
                            <c:if test="${fn:toUpperCase(subscriptionData.subscriptionStatus) eq 'PAUSED'}">
                                <form:form class="resume_subscription_form inline-block" id="resumeSubscriptionForm" action="${changeSubscriptionStateUrl}" method="post">
                                    <button type="submit" class="r_function_btn btn btn-success btn-sm">
                                        <spring:theme code="text.account.subscription.resumeSubscription" text="Resume Subscription"/>
                                        &nbsp;<i class="fa fa-play"></i>
                                    </button>
                                    <input type="hidden" name="newState" value="ACTIVE"/>
                                    <input type="hidden" name="subscriptionId" value="${subscriptionData.id}"/>
                                </form:form>
                            </c:if>
                            <c:if test="${fn:toUpperCase(subscriptionData.subscriptionStatus) eq 'ACTIVE'}">
                                <form:form class="pause_subscription_form inline-block" id="pauseSubscriptionForm" action="${changeSubscriptionStateUrl}" method="post">
                                    <button type="submit" class="r_function_btn btn btn-warning btn-sm">
                                        <spring:theme code="text.account.subscription.pauseSubscription" text="Pause Subscription"/>
                                        &nbsp;<i class="fa fa-pause"></i>
                                    </button>
                                    <input type="hidden" name="newState" value="PAUSED"/>
                                    <input type="hidden" name="subscriptionId" value="${subscriptionData.id}"/>
                                </form:form>
                            </c:if>

                            <c:url value="/my-account/setAutorenewalStatus" var="setAutorenewStatusUrl" />
                            <c:if test="${!subscriptionData.renewalType}">
                                <form:form class="activate_autorenewal_form inline-block" id="activateAutorenewalForm" action="${setAutorenewStatusUrl}" method="post">
                                    <button type="submit" class="r_function_btn btn-success btn-sm">
                                        <spring:theme code="text.account.subscription.activateAutorenew" text="Activate Auto-Renew"/>
                                        &nbsp;<i class="fa fa-play"></i>
                                    </button>
                                    <input type="hidden" name="autorenew" value="true"/>
                                    <input type="hidden" name="subscriptionId" value="${subscriptionData.id}"/>
                                </form:form>
                            </c:if>
                            <c:if test="${subscriptionData.renewalType}">
                                <form:form class="deactivate_autorenewal_form inline-block" id="deactivateAutorenewalForm" action="${setAutorenewStatusUrl}" method="post">
                                    <button type="submit" class="r_function_btn btn btn-danger btn-sm">
                                        <spring:theme code="text.account.subscription.deactivateAutorenew" text="Deactivate Auto-Renew"/>
                                        &nbsp;<i class="fa fa-stop"></i>
                                    </button>
                                    <input type="hidden" name="autorenew" value="false"/>
                                    <input type="hidden" name="subscriptionId" value="${subscriptionData.id}"/>
                                </form:form>
                            </c:if>
                        </li>
                        <li class="list-group-item">
                            <span><spring:theme code="text.account.subscription.renewalType" text="Renewal Type"/>:</span>
                            <ycommerce:testId code="subscription_renewalType_label">
                                	 <c:choose>
		                                <c:when test="${subscriptionData.renewalType}">
		                                  <span class="label label-info">
		                                    <spring:theme code="text.account.subscription.label.yes" text="Yes"/>
		                                  </span>  
		                                </c:when>		                                
		                                <c:otherwise>
		                                  <span class="label label-danger">	
		                                    <spring:theme code="text.account.subscription.label.no" text="No"/>
		                                  </span>  
		                                </c:otherwise>
		                            </c:choose>
                            </ycommerce:testId>
                        </li>
                        <li class="list-group-item">
                            <span><spring:theme code="text.account.subscription.startDate" text="Start Date"/>:</span>
                            <ycommerce:testId code="subscription_startDate_label">
                                <span class="label label-info"><fmt:formatDate value="${subscriptionData.startDate}" dateStyle="long"/></span>
                            </ycommerce:testId>
                        </li>
                        <li class="list-group-item">
                            <span><spring:theme code="text.account.subscription.endDate" text="End Date"/>:</span>
                            <ycommerce:testId code="subscription_endDate_label">
                                <span class="label label-info"><fmt:formatDate value="${subscriptionData.endDate}" dateStyle="long"/></span>
                            </ycommerce:testId>
                        </li>
                        <li class="list-group-item">
                            <span><spring:theme code="text.account.subscription.placedOn" text="Placed On"/>:</span>
                            <ycommerce:testId code="subscription_placedOn_label">
                                <span class="label label-info"><fmt:formatDate value="${subscriptionData.placedOn}" dateStyle="long"/></span>
                            </ycommerce:testId>
                        </li>
                        <li class="list-group-item">
                            <c:url value="/my-account/replaceSubscriptionPaymentMethod" var="replaceSubscriptionPaymentMethodUrl" />
                            <form:form class="update_paymentmethod_form form-inline" id="updatePaymentmethodForm" action="${replaceSubscriptionPaymentMethodUrl}" method="post">
                                <div class="input-group input-group-sm">
                                    <select name="paymentMethodId" class="form-control" id="paymentMethods" <c:if test="${fn:length(paymentInfos) lt 2}">disabled</c:if>>
                                        <c:forEach items="${paymentInfos}" var="paymentInfo">
                                            <option value="${paymentInfo.subscriptionId}" <c:if test="${paymentInfo.subscriptionId eq subscriptionData.paymentMethodId}">selected</c:if>><spring:theme code="text.account.subscription.paymentMethod" arguments="${paymentInfo.cardTypeData.name},${fn:replace(paymentInfo.cardNumber,'****','**** ')},${paymentInfo.expiryMonth},${paymentInfo.expiryYear}"/></option>
                                        </c:forEach>
                                    </select>
                                    <span class="input-group-btn">
                                         <button type="submit" class="function_btn btn btn-success" <c:if test="${fn:length(paymentInfos) lt 2}">disabled</c:if>>
                                             <spring:theme code="text.account.subscription.updatePaymentMethod" text="Update"/>
                                         </button>
                                    </span>
                                </div>
                                <input type="hidden" name="effectiveFrom" value="NOW"/>
                                <input type="hidden" name="subscriptionId" value="${subscriptionData.id}"/>
                            </form:form>
                        </li>

                    </ul>
                </div>
                <div class="col-xs-12">
                    <c:url value="/my-account/subscriptions" var="backToSubscriptions"/>
                    <button type="button" onclick="window.location='${backToSubscriptions}'" class="r_action_btn btn btn-info"><spring:theme code="text.account.subscription.returntosubscriptions" text="Return To Subscriptions"/></button>
                </div>

			</div>
		</div>
	</div>
</div>
