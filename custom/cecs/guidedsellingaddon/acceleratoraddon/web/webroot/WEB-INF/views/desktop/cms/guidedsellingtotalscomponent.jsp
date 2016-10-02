<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<h2 class="column-title">${headline}</h2>
<div class="border-radius-10 background-gray padding-bottom-10 padding-top-10 padding-left-10 padding-right-10">
<div ng-repeat="orderPrice in offer.orderPrices" class="" ng-class="{offerComponentsHidden: !isLoadFinished(), offerComponentsShown: isLoadFinished()}">

    <span class="title text-pink">
        <i class="fa pull-left fa-2x" ng-class="{'fa-shopping-cart':orderPrice.billingTime.code=='paynow','fa-file-text-o':orderPrice.billingTime.code=='onfirstbill','fa-refresh':orderPrice.billingTime.code=='monthly'}"></i>
        <span ng-bind="orderPrice.billingTime.name" class="pull-left text-description"></span>
        <span ng-bind="orderPrice.totalPrice.formattedValue" class="pull-right text-description"></span>
    </span>
    <table class="table table-condensed">
        <tr ng-repeat="totalsEntry in orderPrice.totalsEntries" >
            <td class="text-left description">
                <span ng-bind="totalsEntry.productName"></span>
                <span ng-if="totalsEntry.addedToExistingContract" class="label label-primary"><spring:theme code="guidedselling.added" text="Added" /></span>
            </td>
            <td class="text-right price" ng-bind="totalsEntry.totalPrice.formattedValue"></td>
        </tr>
    </table>
</div>
</div>

<c:url var="placeOrderUrl" value="/guidedselling/order/summary"></c:url>
<a href="${placeOrderUrl}" class="btn btn-sptel-primary btn-block text-uppercase purchase">
	<spring:theme code="guidedselling.guidedsellingtotalscomponent.${offer.changeOrder ? 'continue' : 'purchasepackage'}" />
</a>

<%--
<div class="col-sm-3">
                                    <div class="border-radius-10 background-gray padding-bottom-10 padding-top-10 padding-left-10 padding-right-10">
                                        <p class="font-32">Your quotation</p>

                                        <!-- Pay on check -->
                                        <div>
                                            <span class="glyphicon glyphicon-home font-24" aria-hidden="true"></span>

                                            <!-- Pay on check-out -->
                                            <div class="row">
                                                <div class="col-xs-7">
                                                    <p class="font-16">Pay on check-out</p>
                                                </div>
                                                <div class="col-xs-5 text-right">
                                                    <p class="font-16 color-red"><strong>$ 3456.00</strong></p>
                                                </div>
                                            </div>

                                            <!-- Golden plan -->
                                            <div class="row">
                                                <div class="col-xs-6">
                                                    <p class="no-margins">Golden plan</p>
                                                </div>
                                                <div class="col-xs-6 text-right">
                                                    <p class="no-margins">$ 2456.00</p>
                                                </div>
                                            </div>

                                            <!-- Fiber router -->
                                            <div class="row">
                                                <div class="col-xs-6">
                                                    <p>Fiber router</p>
                                                </div>
                                                <div class="col-xs-6 text-right">
                                                    <p>$ 1000.00</p>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- On your first bill -->
                                        <div class="margin-top-20">
                                            <span class="glyphicon glyphicon-book font-24" aria-hidden="true"></span>

                                            <!-- On your first bill -->
                                            <div class="row">
                                                <div class="col-xs-7">
                                                    <p class="font-16">On your first bill</p>
                                                </div>
                                                <div class="col-xs-5 text-right">
                                                    <p class="font-16 color-red"><strong>$ 3456.00</strong></p>
                                                </div>
                                            </div>

                                            <!-- Golden plan -->
                                            <div class="row">
                                                <div class="col-xs-6">
                                                    <p class="no-margins">Golden plan</p>
                                                </div>
                                                <div class="col-xs-6 text-right">
                                                    <p class="no-margins">$ 2456.00</p>
                                                </div>
                                            </div>

                                            <!-- Fiber router -->
                                            <div class="row">
                                                <div class="col-xs-6">
                                                    <p>Fiber router</p>
                                                </div>
                                                <div class="col-xs-6 text-right">
                                                    <p>$ 1000.00</p>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Monthy reccuring -->
                                        <div class="margin-top-20">
                                            <span class="glyphicon glyphicon-refresh font-24" aria-hidden="true"></span>

                                            <!-- Monthy reccuring -->
                                            <div class="row">
                                                <div class="col-xs-7">
                                                    <p class="font-16">Monthy reccuring</p>
                                                </div>
                                                <div class="col-xs-5 text-right">
                                                    <p class="font-16 color-red"><strong>$ 1256.00</strong></p>
                                                </div>
                                            </div>

                                            <!-- Row -->
                                            <div class="row">
                                                <div class="col-xs-6">
                                                    <p class="no-margins">Golden plan</p>
                                                </div>
                                                <div class="col-xs-6 text-right">
                                                    <p class="no-margins">$ 256.00</p>
                                                </div>
                                            </div>

                                            <!-- Row -->
                                            <div class="row">
                                                <div class="col-xs-6">
                                                    <p>Fiber router</p>
                                                </div>
                                                <div class="col-xs-6 text-right">
                                                    <p>$ 1000.00</p>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Additional Options -->
                                        <div class="margin-top-20">
                                            <!-- Additional Options -->
                                            <div class="row">
                                                <div class="col-xs-12">
                                                    <p class="font-16">Additional Options</p>
                                                </div>
                                            </div>

                                            <!-- Row -->
                                            <div class="row">
                                                <div class="col-xs-6">
                                                    <p class="no-margins">5070THBRPG-WE</p>
                                                </div>
                                                <div class="col-xs-6 text-right">
                                                    <p class="no-margins">$ 14.03</p>
                                                </div>
                                            </div>

                                            <!-- Row -->
                                            <div class="row">
                                                <div class="col-xs-6">
                                                    <p>5070THPRPG-WE</p>
                                                </div>
                                                <div class="col-xs-6 text-right">
                                                    <p>$ 14.03</p>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Buttons -->
                                        <div class="margin-top-30">
                                            <a href="#!" class="btn btn-primary btn-block">Send quotation for approval</a>
                                        </div>
                                    </div>
                                </div>
--%>