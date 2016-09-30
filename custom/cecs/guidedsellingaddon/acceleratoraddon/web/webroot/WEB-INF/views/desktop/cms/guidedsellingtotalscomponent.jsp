<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<h2 class="column-title">${headline}</h2>

<div ng-repeat="orderPrice in offer.orderPrices" class="costs offerComponentsHidden" ng-class="{offerComponentsHidden: !isLoadFinished(), offerComponentsShown: isLoadFinished()}">
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

<c:url var="placeOrderUrl" value="/guidedselling/order/summary"></c:url>
<a href="${placeOrderUrl}" class="btn btn-tricast-primary btn-block text-uppercase purchase">
	<spring:theme code="guidedselling.guidedsellingtotalscomponent.${offer.changeOrder ? 'continue' : 'purchasepackage'}" />
</a>
