<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="payment-details box">
    <div class="guidedselling-payment">
        <div class="guidedselling-cart-payment">

            <div ng-if="customerPaymentInfos.allPaymentInfos.length > 0">
                <div class="title"><spring:theme code="guidedselling.payment.info" text="Payment info" /></div>

                <div>
                    <span ng-bind="customerPaymentInfos.cartPaymentInfo.cardNumber"></span>
                    <span ng-bind="customerPaymentInfos.cartPaymentInfo.cardTypeData.name"></span>
                </div>

                <div>
                    <span><spring:theme code="text.expires" text="Expires" /></span>
                    <span ng-bind="customerPaymentInfos.cartPaymentInfo.expiryMonth"></span>
                    <span>/</span>
                    <span ng-bind="customerPaymentInfos.cartPaymentInfo.expiryYear"/>
                </div>
                <div ng-bind="customerPaymentInfos.cartPaymentInfo.accountHolderName"></div>
                <div ng-bind="customerPaymentInfos.cartPaymentInfo.billingAddress.line1"></div>
            </div>

            <div ng-if="customerPaymentInfos.allPaymentInfos.length == 0">
                <span class="title"><spring:theme code="guidedselling.payment.info.missing"/></span>
            </div>

        </div>

        <button type="submit"
                ng-click="showAddPaymentInfo()"
                class="btn btn-primary btn-half-block btn-lg text-uppercase purchase">
            <spring:theme code="guidedselling.payment.info.add" text="Add" />
        </button>

        <button type="submit"
                ng-click="showSelectPaymentInfo()"
                class="btn btn-primary btn-half-block btn-lg text-uppercase purchase"
                ng-if="customerPaymentInfos.allPaymentInfos.length > 0">
            <spring:theme code="guidedselling.payment.info.select" text="Select" />
        </button>
    </div>
    <div class="guidedselling-add-payment" style="display: none;">
									<span class="title"><spring:theme
                                            code="guidedselling.payment.newPaymentDetails"
                                            text="New payment Details" /></span>

        <form name="addPaymentInfo" class="addPaymentInfo-form"
              novalidate>
            <spring:theme code="payment.cardNumber" text="Card Number" />
            <br />
            <input type="text" ng-model="newPaymentInfo.cardNumber"
                   required /><br />
            <spring:theme code="payment.cardType" text="Card Type" />
            <br />
            <select
                    ng-options="card.code as card.name for card in customerPaymentInfos.cardTypes"
                    ng-model="newPaymentInfo.cardType" required></select><br />
            <spring:theme code="text.expires" text="Expires" />
            <br />
            <spring:theme code="guidedselling.payment.year" text="" />
            &nbsp <select
                ng-options="year.code as year.name for year in customerPaymentInfos.expiryYears"
                ng-model="newPaymentInfo.expiryYear" required></select><br />
            <spring:theme code="guidedselling.payment.month" text="" />
            &nbsp <select
                ng-options="month.code as month.name for month in customerPaymentInfos.months"
                ng-model="newPaymentInfo.expiryMonth" required></select><br />
            <spring:theme code="profile.title" text="Title" />
            <br />
            <input type="text" ng-model="newPaymentInfo.accountHolderName"
                   required /><br />
            <spring:theme code="payment.issueNumber" text="Issue number" />
            <br />
            <input type="text" ng-model="newPaymentInfo.issueNumber"
                   required /><br />
            <br />
            <div class="alert alert-danger paymentInfo-error"
                 style="display: none;"></div>
            <button type="submit"
                    ng-click="addNewPaymentInfo()"
                    class="btn btn-primary btn-half-block btn-lg text-uppercase purchase">
                <spring:theme code="guidedselling.payment.info.add"
                              text="Add" />
            </button>
            <button type="submit" ng-click="hideAddPaymentInfo()"
                    class="btn btn-primary btn-half-block btn-lg text-uppercase purchase">
                <spring:theme code="guidedselling.payment.info.cancel"
                              text="Cancel" />
            </button>
        </form>
    </div>
    <div class="guidedselling-select-payment" style="display: none;">
        <div
                class="costs guidedselling-cart-payment checkoutInfo-selection"
                ng-repeat="paymentInfo in customerPaymentInfos.allPaymentInfos"
                ng-click="selectPaymentInfo(paymentInfo.id)">
            <span ng-bind="paymentInfo.cardNumber"></span>&nbsp <span
                ng-bind="paymentInfo.cardTypeData.name"></span>&nbsp <span
                ng-bind="paymentInfo.accountHolderName"></span>&nbsp <span
                ng-bind="paymentInfo.billingAddress.line1"></span> <br />
        </div>
        <button type="submit" ng-click="hideSelectPaymentInfo()"
                class="btn btn-primary btn-half-block btn-lg text-uppercase purchase">
            <spring:theme code="guidedselling.payment.info.cancel"
                          text="Cancel" />
        </button>
    </div>
</div>