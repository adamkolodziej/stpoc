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
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>

<c:url value="/sbg-sop-mock/handle-form-post" var="sopMockUrl" />

<div class="accountContentPane payment-container">
    <h2 class="headline">
        <spring:theme code="text.account.paymentDetails" text="Payment Details"/>
    </h2>

    <form:form method="post" commandName="sopPaymentDetailsForm" class="create_update_payment_form" action="${sopMockUrl}?editMode=${isEditMode}&paymentInfoId=${paymentInfo.id}">
        <div class="payment_details_left_col">
            <h1>
                <spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.paymentCard" />
            </h1>

            <p>
                <spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.enterYourCardDetails" />
            </p>

            <p>
                <spring:theme code="form.required" />
            </p>
            <dl>
                <form:hidden path="sessionToken" value="${sessionToken}" />

                <c:if test="${isEditMode}">
                    <c:set var="selectCSSClass" value="uneditable"/>
                </c:if>

                <formElement:formSelectBox idKey="payment.cardType"
                    labelKey="payment.cardType" path="cardTypeCode" mandatory="true"
                    skipBlank="false"
                    skipBlankMessageKey="payment.cardType.pleaseSelect"
                    selectCSSClass="${selectCSSClass}"
                    items="${cardTypes}" tabindex="1" disabled="${isEditMode}"/>
                <c:if test="${isEditMode}">
                    <form:hidden path="cardTypeCode" id="payment.cardType"/>
                 </c:if>
                <formElement:formInputBox idKey="payment.nameOnCard"
                    labelKey="payment.nameOnCard" path="nameOnCard" inputCSS="text"
                    mandatory="true" tabindex="2" readonly="${isEditMode}"/>
                <formElement:formInputBox idKey="payment.cardNumber"
                    labelKey="payment.cardNumber" path="cardNumber" inputCSS="text"
                    mandatory="true" tabindex="3" readonly="${isEditMode}"/>

                <template:errorSpanField path="startMonth">
                    <dt>
                        <label for="StartMonth"><spring:theme code="payment.startDate" /></label>
                    </dt>

                    <c:set var="cardDate" value="card_date"/>
                    <c:if test="${isEditMode}">
                        <c:set var="cardDate" value="${cardDate} uneditable"/>
                    </c:if>

                    <dd>
                        <div class="control-group">
                        <form:select id="StartMonth" path="startMonth" cssClass="${cardDate} form-control" tabindex="4"  disabled="${isEditMode}">
                            <option value="" label="<spring:theme code='payment.month'/>" />
                            <form:options items="${months}" itemValue="code" itemLabel="name" />
                        </form:select>
                        <c:if test="${isEditMode}">
                            <form:hidden path="startMonth" id="StartMonth"/>
                        </c:if>
                        </div>
                        <div class="control-group">
                        <form:select id="StartYear" path="startYear" cssClass="${cardDate} form-control" tabindex="5"  disabled="${isEditMode}">
                            <option value="" label="<spring:theme code='payment.year'/>" />
                            <form:options items="${startYears}" itemValue="code" itemLabel="name" />
                        </form:select>
                        <c:if test="${isEditMode}">
                            <form:hidden path="startYear" id="StartYear"/>
                        </c:if>
                        </div>
                    </dd>

                    <dt>
                        <label for="ExpiryMonth"><spring:theme code="payment.expiryDate" /></label>
                    </dt>
                    <dd>
                        <template:errorSpanField path="expiryMonth">
                            <form:select id="ExpiryMonth" path="expiryMonth" cssClass="${cardDate} form-control" tabindex="6" disabled="${isEditMode}">
                                <option value="" label="<spring:theme code='payment.month'/>" />
                                <form:options items="${months}" itemValue="code" itemLabel="name" />
                            </form:select>
                            <c:if test="${isEditMode}">
                                <form:hidden path="expiryMonth" id="ExpiryMonth"/>
                            </c:if>
                        </template:errorSpanField>

                        <template:errorSpanField path="expiryYear">
                            <form:select id="ExpiryYear" path="expiryYear" cssClass="${cardDate} form-control" tabindex="7" disabled="${isEditMode}">
                                <option value="" label="<spring:theme code='payment.year'/>" />
                                <form:options items="${expiryYears}" itemValue="code" itemLabel="name" />
                            </form:select>
                            <c:if test="${isEditMode}">
                                <form:hidden path="expiryYear" id="ExpiryYear"/>
                            </c:if>
                        </template:errorSpanField>
                    </dd>
                </template:errorSpanField>

                <formElement:formInputBox idKey="payment.issueNumber"
                    labelKey="payment.issueNumber" path="issueNumber"
                    inputCSS="text" mandatory="false" tabindex="8" readonly="${isEditMode}"/>

                <c:if test="${isFirstPaymentMethod}">
                    <form:hidden path="makeAsDefault" value="true"/>
                </c:if>
                <c:if test="${not isFirstPaymentMethod and not paymentInfo.defaultPaymentInfo}">
                    <formElement:formCheckbox idKey="makeAsDefault" labelKey="payment.makeAsDefault" path="makeAsDefault" mandatory="false" inputCSS="add-address-left-input" labelCSS="add-address-left-label" />
                </c:if>
            </dl>
        </div>

        <div class="payment_details_right_col">

            <h1>
                <spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.billingAddress" />
            </h1>

             <p>
                <spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.billingAddressDiffersFromDeliveryAddress" />
            </p>
            <dl>
                <dt class="left">
                    <form:checkbox id="differentAddress" path="newBillingAddress" tabindex="9"/>
                    <label for="differentAddress" class="differentAddress"><spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.enterDifferentBillingAddress" /></label>
                </dt>
                <dd></dd>
            </dl>

            <p>
                <spring:theme code="form.required" />
            </p>
            <dl id="newBillingAddressFields">
                <form:hidden path="billingAddress.addressId"
                    class="create_update_address_id" />
                <formElement:formSelectBox idKey="address.title"
                    labelKey="address.title" path="billingAddress.titleCode"
                    mandatory="true" skipBlank="false"
                    skipBlankMessageKey="address.title.pleaseSelect"
                    items="${titles}" tabindex="10"/>
                <formElement:formInputBox idKey="address.firstName"
                    labelKey="address.firstName" path="billingAddress.firstName"
                    inputCSS="text" mandatory="true" tabindex="11"/>
                <formElement:formInputBox idKey="address.surname"
                    labelKey="address.surname" path="billingAddress.lastName"
                    inputCSS="text" mandatory="true" tabindex="12"/>

                <formElement:formInputBox idKey="address.line1"
                    labelKey="address.line1" path="billingAddress.line1"
                    inputCSS="text" mandatory="true" tabindex="13"/>
                <formElement:formInputBox idKey="address.line2"
                    labelKey="address.line2" path="billingAddress.line2"
                    inputCSS="text" mandatory="false" tabindex="14"/>
                <formElement:formInputBox idKey="address.townCity"
                    labelKey="address.townCity" path="billingAddress.townCity"
                    inputCSS="text" mandatory="true" tabindex="15"/>
                <formElement:formInputBox idKey="address.postcode"
                    labelKey="address.postcode" path="billingAddress.postcode"
                    inputCSS="text" mandatory="true" tabindex="16"/>
                <formElement:formSelectBox idKey="address.country"
                    labelKey="address.country" path="billingAddress.countryIso"
                    mandatory="true" skipBlank="false"
                    skipBlankMessageKey="address.selectCountry"
                    items="${billingCountries}" itemValue="isocode" tabindex="17"/>
                <form:hidden path="billingAddress.shippingAddress" />
                <form:hidden path="billingAddress.billingAddress" />
            </dl>
        </div>
        <div class="save_payment_details">
            <ycommerce:testId code="editPaymentMethod_savePaymentMethod_button">
                <c:if test="${not isEditMode}">
                    <button class="btn btn-success" tabindex="19" id="lastInTheForm">
                        <spring:theme code="text.account.paymentDetails.saveNewPaymentDetails" text="Save New Payment Details" />
                    </button>
                </c:if>
                <c:if test="${isEditMode}">
                    <button class="btn btn-success" tabindex="19" id="lastInTheForm">
                        <spring:theme code="text.account.paymentDetails.savePaymentDetails" text="Save Payment Details"/>
                    </button>
                </c:if>
            </ycommerce:testId>

            <button class="btn btn-success" style="margin-right: 10px;" type="button" onclick="window.history.back();">
                <spring:theme code="checkout.multi.cancel" text="Cancel" />
            </button>
        </div>
    </form:form>

</div>