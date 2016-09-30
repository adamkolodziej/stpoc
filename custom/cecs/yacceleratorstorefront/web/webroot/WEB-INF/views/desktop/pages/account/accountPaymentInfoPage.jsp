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
<div class="accountContentPane payment-container">
    <h2 class="headline">
        <spring:theme code="text.account.paymentDetails" text="Payment Details"/>
    </h2>
    <div>
        <p>
        <spring:theme code="text.account.paymentDetails.managePaymentDetails" text="Manage your saved payment details."/>
        </p>
    </div>
    <c:url value='/my-account/payment-details/add' var="addPaymentInfoUrl"/>
    <div class="row">
        <div class="col-xs-12">
            <a class="btn btn-primary" href="${addPaymentInfoUrl}">
                <spring:theme code="text.account.paymentInfo.add" text="Add"/>
            </a>
            <br/><br/>
        </div>
    </div>
    <div class="row">
        <c:choose>
            <c:when test="${not empty paymentInfoData}">
                <c:forEach items="${paymentInfoData}" var="paymentInfo">
                    <div class="col-xs-6">

                        <div class="paymentItem panel-item">
                            <div class=" panel panel-default <c:if test="${paymentInfo.defaultPaymentInfo}">panel-primary</c:if> ">
                                <div class="panel-heading">
                                    <h3 class="panel-title">
                                        <c:if test="${paymentInfo.defaultPaymentInfo}">
                                            <spring:theme code="text.default" text="Default"/>
                                        </c:if>
                                    </h3>
                                </div>
                                <div class="panel-body">
                                    <dl class="dl-horizontal data-list">
                                        <dt><spring:theme code="payment.cardNumber" text="Card Number"/></dt>
                                        <dd>${fn:escapeXml(paymentInfo.cardNumber)}</dd>
                                        <dt><spring:theme code="payment.cardType" text="Card Type"/></dt>
                                        <dd>${fn:escapeXml(paymentInfo.cardType)}</dd>
                                        <dt><spring:theme code="text.expires" text="Expires"/></dt>
                                        <dd>${fn:escapeXml(paymentInfo.expiryMonth)} / ${fn:escapeXml(paymentInfo.expiryYear)}</dd>
                                    </dl>
                                    <dl class="dl-horizontal data-list">
                                        <dt><spring:theme code="profile.title" text="Title"/></dt>
                                        <dd><c:out value="${fn:escapeXml(paymentInfo.billingAddress.title)} ${fn:escapeXml(paymentInfo.billingAddress.firstName)} ${fn:escapeXml(paymentInfo.billingAddress.lastName)}"/></dd>
                                        <dt><spring:theme code="address.line1" text=""/></dt>
                                        <dd>${fn:escapeXml(paymentInfo.billingAddress.line1)}</dd>
                                        <dt><spring:theme code="address.line2" text=""/></dt>
                                        <dd>${fn:escapeXml(paymentInfo.billingAddress.line2)}</dd>
                                        <dt><spring:theme code="address.townCity" text=""/></dt>
                                        <dd>${fn:escapeXml(paymentInfo.billingAddress.town)}</dd>
                                        <dt><spring:theme code="address.postalcode" text=""/></dt>
                                        <dd>${fn:escapeXml(paymentInfo.billingAddress.postalCode)}</dd>
                                        <dt><spring:theme code="address.country" text=""/></dt>
                                        <dd>${fn:escapeXml(paymentInfo.billingAddress.country.name)}</dd>
                                    </dl>
                                </div>
                                <div class="panel-footer">
                                    <div class="btn-group" role="group" aria-label="...">
                                        <c:if test="${not paymentInfo.defaultPaymentInfo}">
                                            <c:url value="/my-account/set-default-payment-details" var="setDefaultPaymentActionUrl"/>
                                            <form:form id="setDefaultPaymentDetails${paymentInfo.id}" action="${setDefaultPaymentActionUrl}" method="post">
                                                <input type="hidden" name="paymentInfoId" value="${paymentInfo.id}"/>
                                                <button type="submit" class="btn btn-primary submitSetDefault" id="${paymentInfo.id}" href="#">
                                                    <spring:theme code="text.setDefault" text="Set as default"/>
                                                </button>
                                            </form:form>
                                        </c:if>

                                        <script type="text/javascript">
                                            var id = '${paymentInfo.id}';
                                        </script>

                                        <c:url value='/my-account/payment-details/edit?paymentInfoId=${paymentInfo.id}' var="editPaymentInfoUrl"/>
                                        <a class="btn btn-info" href="${editPaymentInfoUrl}">
                                            <spring:theme code="text.account.paymentInfo.edit" text="Edit"/>
                                        </a>

                                        <c:url value="/my-account/remove-payment-method" var="removePaymentActionUrl"/>
                                        <form:form id="removePaymentDetails${paymentInfo.id}" action="${removePaymentActionUrl}" method="post">
                                            <input type="hidden" name="paymentInfoId" value="${paymentInfo.id}"/>
                                            <button type="submit" class="submitRemove btn btn-danger" id="${paymentInfo.id}" href="#">
                                                <spring:theme code="text.remove" text="Remove"/>
                                            </button>
                                        </form:form>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="col-xs-12">
                    <h3>
                        <span class="label label-warning  emptyMessage display-block">
                            <spring:theme code="text.account.paymentDetails.noPaymentInformation" text="No Saved Payment Details"/>
                        </span>
                    </h3>
                </div>

            </c:otherwise>
        </c:choose>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <a class="btn btn-primary" href="${addPaymentInfoUrl}">
                <spring:theme code="text.account.paymentInfo.add" text="Add"/>
            </a>
        </div>
    </div>


</div>

