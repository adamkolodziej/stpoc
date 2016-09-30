<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<c:url var="contractDetails" value="/my-account/ybillingContractDetails/" />

<h2 class="headline">
    <spring:theme code="text.account.order.ybilling.details" text="yBilling Order Details"/>
</h2>

<c:if test="${not empty orderDataList}">

    <label class="control-label">For Order Id: </label> ${orderDataList.get(0).YBillingOrderId}
    
    <h4 class="headline">
        <a href="${contractDetails}"><spring:theme code="text.account.contract.ybilling.overviewBack" text="back to Contract Overview"/></a>
    </h4>

<div class="orderList table-responsive">
    <table class="orderListTable table table-condensed table-bordered table-striped">
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.order.ybilling.orderItem" text="Order Item"/></label></td>
                <c:forEach items="${orderDataList}" var="orderData">
                    <td>${orderData.orderItem}</td>
                </c:forEach>
        </tr>
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.contract.ybilling.contractProcessType" text="Process Type"/></label></td>
                <c:forEach items="${orderDataList}" var="orderData">
                    <td>${orderData.processType}</td>
                </c:forEach>
        </tr>
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.contract.ybilling.contractOrderType" text="Order Type"/></label></td>
                <c:forEach items="${orderDataList}" var="orderData">
                    <td>${orderData.orderType}</td>
                </c:forEach>
        </tr>
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.contract.ybilling.contractProduct" text="Product"/></label></td>
                <c:forEach items="${orderDataList}" var="orderData">
                    <td>${orderData.orderedProductCode}</td>
                </c:forEach>
        </tr>
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.contract.ybilling.contractOrderDate" text="Order Date"/></label></td>
                <c:forEach items="${orderDataList}" var="orderData">
                    <td title="<fmt:formatDate value="${orderData.orderDate}" pattern="dd/MM/yyyy HH:mm:ss" />"><fmt:formatDate value="${orderData.orderDate}" pattern="dd/MM/yyyy" /></td>
                </c:forEach>
        </tr>
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.contract.ybilling.contractOrderStatus" text="Order Status"/></label></td>
                <c:forEach items="${orderDataList}" var="orderData">
                    <td>${orderData.orderStatus}</td>
                </c:forEach>
        </tr>
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.order.ybilling.oneTimeNetValue" text="One Time Net Value"/></label></td>
                <c:forEach items="${orderDataList}" var="orderData">
                    <td><format:price priceData="${orderData.oneTimeNetValue}"/></td>
                </c:forEach>
        </tr>
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.order.ybilling.oneTimeTaxAmount" text="One Time Tax Amount"/></label></td>
                <c:forEach items="${orderDataList}" var="orderData">
                    <td><format:price priceData="${orderData.oneTimeTaxAmount}"/></td>
                </c:forEach>
        </tr>
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.order.ybilling.oneTimeGrossValue" text="One Time Gross Value"/></label></td>
                <c:forEach items="${orderDataList}" var="orderData">
                    <td><format:price priceData="${orderData.oneTimeGrossValue}"/></td>
                </c:forEach>
        </tr>
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.order.ybilling.recurringNetValue" text="Recurring Net Value"/></label></td>
                <c:forEach items="${orderDataList}" var="orderData">
                    <td><format:price priceData="${orderData.recurringNetValue}"/></td>
                </c:forEach>
        </tr>
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.order.ybilling.recurringTaxAmount" text="Recurring Tax Amount"/></label></td>
                <c:forEach items="${orderDataList}" var="orderData">
                    <td><format:price priceData="${orderData.recurringTaxAmount}"/></td>
                </c:forEach>
        </tr>
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.order.ybilling.recurringGrossValue" text="Recurring Gross Value"/></label></td>
            <c:forEach items="${orderDataList}" var="orderData">
                <td><format:price priceData="${orderData.recurringGrossValue}"/></td>
            </c:forEach>
        </tr>
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.order.ybilling.recurringDuration" text="Recurring Duration"/></label></td>
            <c:forEach items="${orderDataList}" var="orderData">
                <td>${orderData.recurringDuration}</td>
            </c:forEach>
        </tr>
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.order.ybilling.recurringTimeUnit" text="Recurring Time Unit"/></label></td>
            <c:forEach items="${orderDataList}" var="orderData">
                <td>${orderData.recurringTimeUnit}</td>
            </c:forEach>
        </tr>
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.order.ybilling.soldTo" text="Sold To"/></label></td>
            <c:forEach items="${orderDataList}" var="orderData">
                <td>${orderData.soldTo}</td>
            </c:forEach>
        </tr>
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.order.ybilling.soldToAddress" text="Sold To Address"/></label></td>
            <c:forEach items="${orderDataList}" var="orderData">
                <td>${orderData.soldToAddress}</td>
            </c:forEach>
        </tr>
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.order.ybilling.shipTo" text="Ship To"/></label></td>
            <c:forEach items="${orderDataList}" var="orderData">
                <td>${orderData.shipTo}</td>
            </c:forEach>
        </tr>
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.order.ybilling.shipToAddress" text="Ship To Address"/></label></td>
            <c:forEach items="${orderDataList}" var="orderData">
                <td>${orderData.shipToAddress}</td>
            </c:forEach>
        </tr>
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.order.ybilling.billTo" text="Bill To"/></label></td>
            <c:forEach items="${orderDataList}" var="orderData">
                <td>${orderData.billTo}</td>
            </c:forEach>
        </tr>
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.order.ybilling.billToAddress" text="Bill To Address"/></label></td>
            <c:forEach items="${orderDataList}" var="orderData">
                <td>${orderData.billToAddress}</td>
            </c:forEach>
        </tr>
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.order.ybilling.payer" text="Payer"/></label></td>
            <c:forEach items="${orderDataList}" var="orderData">
                <td>${orderData.payerTo}</td>
            </c:forEach>
        </tr>
        <tr>
            <td><label class="control-label"><spring:theme code="text.account.order.ybilling.payerAddress" text="Payer Address"/></label></td>
            <c:forEach items="${orderDataList}" var="orderData">
                <td>${orderData.payerToAddress}</td>
            </c:forEach>
        </tr>
    </table>
</div>

    <h3 class="headline">
        <spring:theme code="text.account.order.ybilling.history" text="yBilling Contract History"/>
    </h3>

<div>
    <c:set var="orderListSize" scope="session" value="${fn:length(orderDataList)}"/>
    <table class="orderListTable table table-condensed table-bordered table-striped">

        <th width="200">Characteristic</th>
        <th colspan="${orderListSize}">Order Item</th>

        <tr>
            <td></td>
            <c:forEach items="${orderDataList}" var="orderData">
                <td><label class="control-label">${orderData.orderItem}</label></td>
            </c:forEach>
        </tr>

        <c:forEach items="${chracteristicMap.keySet()}" var="charKey">
            <tr>
                <td><label class="control-label" title="${charKey}">${chracteristicMap[charKey]}</label></td>
                <c:forEach begin="0" end="${orderListSize-1}" varStatus="loop">
                    <td>${characteristicMapList.get(loop.index)[charKey]}</td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
</div>
</c:if>
<c:if test="${empty orderDataList}">
    <h3>
        <span class="label label-warning  emptyMessage display-block">
            <spring:theme code="text.account.order.ybilling.details.noResult" text="No order found for this id."/>
        </span>
    </h3>
</c:if>