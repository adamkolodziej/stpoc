<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url var="orderDetails" value="/my-account/ybillingOrderDetails/" />
<c:url var="contractDetails" value="/my-account/ybillingContractDetails/" />

<c:if test="${not empty contractList}">
    <c:if test="${!showHistory}">

        <h2 class="headline">
            <spring:theme code="text.account.contract.ybilling.overview" text="Contract Overview"/>
        </h2>

    <div class="contractList table-responsive">
        <table class="contractListTable table table-condensed table-bordered table-striped">
            <th class="table-header"><spring:theme code="text.account.contract.ybilling.contractNo" text="Contract Number"/></th>
            <%--<th class="table-header"><spring:theme code="text.account.contract.ybilling.contractStartDate" text="Contract Start Date"/></th>--%>
            <th class="table-header"><spring:theme code="text.account.contract.ybilling.contractEndDate" text="Contract End Date"/></th>
            <th class="table-header"><spring:theme code="text.account.contract.ybilling.contractVersion" text="Contract Version"/></th>
            <%--<th class="table-header"><spring:theme code="text.account.contract.ybilling.contractVersionStartDate" text="Contract Version Start Date"/></th>--%>
            <%--<th class="table-header"><spring:theme code="text.account.contract.ybilling.contractVersionEndDate" text="Contract Version End Date"/></th>--%>
            <th class="table-header"><spring:theme code="text.account.contract.ybilling.contractActivationStatus" text="Activation Status"/></th>
            <th class="table-header"><spring:theme code="text.account.contract.ybilling.contractOrderId" text="Order Id"/></th>
            <%--<th class="table-header"><spring:theme code="text.account.contract.ybilling.contractProcessType" text="Process Type"/></th>--%>
            <%--<th class="table-header"><spring:theme code="text.account.contract.ybilling.contractOrderType" text="Order Type"/></th>--%>
            <th class="table-header"><spring:theme code="text.account.contract.ybilling.contractProduct" text="Product"/></th>
            <th class="table-header"><spring:theme code="text.account.contract.ybilling.contractOrderDate" text="Order Date"/></th>
            <th class="table-header"><spring:theme code="text.account.contract.ybilling.contractOrderStatus" text="Order Status"/></th>

            <c:forEach items="${contractList}" var="contractData">
                 <tr>
                     <td>${contractData.contractNumber}</td>
                     <%--<td><fmt:formatDate value="${contractData.contractStartDate}" pattern="dd/MM/yyyy" /></td>--%>
                     <td title="<fmt:formatDate value="${contractData.contractEndDate}" pattern="dd/MM/yyyy HH:mm:ss" />"><fmt:formatDate value="${contractData.contractEndDate}" pattern="dd/MM/yyyy" /></td>
                     <td title="<spring:theme code="text.account.contract.ybilling.history" text="Contract History" />"><a href="${contractDetails}${contractData.contractNumber}/${contractData.contractVersion}">${contractData.contractVersion}</a></td>
                     <%--<td><fmt:formatDate value="${contractData.contractVersionStartDate}" pattern="dd/MM/yyyy" /></td>--%>
                     <%--<td><fmt:formatDate value="${contractData.contractVersionEndDate}" pattern="dd/MM/yyyy" /></td>--%>
                     <td>
                         <c:if test="${contractData.activationStatus}">
                             <spring:theme code="text.account.ybilling.active" text="active"/>
                         </c:if>
                         <c:if test="${!contractData.activationStatus}">
                             <spring:theme code="text.account.ybilling.inactive" text="inactive"/>
                         </c:if>
                     </td>
                     <td title="<spring:theme code="text.account.order.ybilling.details" text="Order Details"/>"><a href="${orderDetails}${contractData.YBillingOrderId}">${contractData.YBillingOrderId}</a></td>
                     <%--<td>${contractData.processType}</td>--%>
                     <%--<td>${contractData.orderType}</td>--%>
                     <td>${contractData.orderedProductCode}</td>
                     <td title="<fmt:formatDate value="${contractData.contractEndDate}" pattern="dd/MM/yyyy HH:mm:ss" />"><fmt:formatDate value="${contractData.orderDate}" pattern="dd/MM/yyyy" /></td>
                     <td>${contractData.orderStatus}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </c:if>

    <c:if test="${showHistory}">
        <c:set var="contractListSize" scope="session" value="${fn:length(contractList)}"/>

        <h2 class="headline">
            <spring:theme code="text.account.contract.ybilling.details" text="Contract Details"/>
        </h2>

        <label class="control-label">For Contract Id: </label> ${contractList.get(0).contractNumber}

        <h4 class="headline">
            <a href="${contractDetails}"><spring:theme code="text.account.contract.ybilling.overviewBack" text="back to Contract Overview"/></a>
        </h4>

        <table class="contractListTable table table-condensed table-bordered table-striped">
            <tr>
                <td><label class="control-label"><spring:theme code="text.account.contract.ybilling.contractStartDate" text="Contract Start Date"/></label></td>
                <c:forEach items="${contractList}" var="contractData">
                    <td title="<fmt:formatDate value="${contractData.contractStartDate}" pattern="dd/MM/yyyy HH:mm:ss" />"><fmt:formatDate value="${contractData.contractStartDate}" pattern="dd/MM/yyyy" /></td>
                </c:forEach>
            </tr>
            <tr>
                <td><label class="control-label"><spring:theme code="text.account.contract.ybilling.contractEndDate" text="Contract End Date"/></label></td>
                <c:forEach items="${contractList}" var="contractData">
                    <td title="<fmt:formatDate value="${contractData.contractEndDate}" pattern="dd/MM/yyyy HH:mm:ss" />"><fmt:formatDate value="${contractData.contractEndDate}" pattern="dd/MM/yyyy" /></td>
                </c:forEach>
            </tr>
            <tr>
                <td><label class="control-label"><spring:theme code="text.account.contract.ybilling.contractVersion" text="Contract Version"/></label></td>
                <c:forEach items="${contractList}" var="contractData">
                    <td>${contractData.contractVersion}</td>
                </c:forEach>
            </tr>
            <tr>
                <td><label class="control-label"><spring:theme code="text.account.contract.ybilling.contractVersionStartDate" text="Contract Version Start Date"/></label></td>
                <c:forEach items="${contractList}" var="contractData">
                    <td title="<fmt:formatDate value="${contractData.contractVersionStartDate}" pattern="dd/MM/yyyy HH:mm:ss" />"><fmt:formatDate value="${contractData.contractVersionStartDate}" pattern="dd/MM/yyyy" /></td>
                </c:forEach>
            </tr>
            <tr>
                <td><label class="control-label"><spring:theme code="text.account.contract.ybilling.contractVersionEndDate" text="Contract Version End Date"/></label></td>
                <c:forEach items="${contractList}" var="contractData">
                    <td title="<fmt:formatDate value="${contractData.contractVersionEndDate}" pattern="dd/MM/yyyy HH:mm:ss" />"><fmt:formatDate value="${contractData.contractVersionEndDate}" pattern="dd/MM/yyyy" /></td>
                </c:forEach>
            </tr>
            <tr>
                <td><label class="control-label"><spring:theme code="text.account.contract.ybilling.contractActivationStatus" text="Activation"/></label></td>
                <c:forEach items="${contractList}" var="contractData">
                    <td>
                        <c:if test="${contractData.activationStatus}">
                            <spring:theme code="text.account.ybilling.active" text="active"/>
                        </c:if>
                            <c:if test="${!contractData.activationStatus}">
                                <spring:theme code="text.account.ybilling.inactive" text="inactive"/>
                            </c:if>
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <td><label class="control-label"><spring:theme code="text.account.contract.ybilling.contractOrderId" text="Order Id"/></label></td>
                <c:forEach items="${contractList}" var="contractData">
                    <td title="<spring:theme code="text.account.order.ybilling.details" text="Order Details"/>"><a href="${orderDetails}${contractData.YBillingOrderId}">${contractData.YBillingOrderId}</a></td>
                </c:forEach>
            </tr>
            <tr>
                <td><label class="control-label"><spring:theme code="text.account.contract.ybilling.contractProcessType" text="Process Type"/></label></td>
                <c:forEach items="${contractList}" var="contractData">
                    <td>${contractData.processType}</td>
                </c:forEach>
            </tr>
            <tr>
                <td><label class="control-label"><spring:theme code="text.account.contract.ybilling.contractOrderType" text="Order Type"/></label></td>
                <c:forEach items="${contractList}" var="contractData">
                    <td>${contractData.orderType}</td>
                </c:forEach>
            </tr>
            <tr>
                <td><label class="control-label"><spring:theme code="text.account.contract.ybilling.contractProduct" text="Product"/></label></td>
                <c:forEach items="${contractList}" var="contractData">
                    <td>${contractData.orderedProductCode}</td>
                </c:forEach>
            </tr>
            <tr>
                <td><label class="control-label"><spring:theme code="text.account.contract.ybilling.contractOrderDate" text="Order Date"/></label></td>
                <c:forEach items="${contractList}" var="contractData">
                    <td title="<fmt:formatDate value="${contractData.orderDate}" pattern="dd/MM/yyyy HH:mm:ss" />"><fmt:formatDate value="${contractData.orderDate}" pattern="dd/MM/yyyy" /></td>
                </c:forEach>
            </tr>
            <tr>
                <td><label class="control-label"><spring:theme code="text.account.contract.ybilling.contractOrderStatus" text="Order Status"/></label></td>
                <c:forEach items="${contractList}" var="contractData">
                    <td>${contractData.orderStatus}</td>
                </c:forEach>
            </tr>
        </table>


        <h2 class="headline">
            <spring:theme code="text.account.contract.ybilling.history" text="yBilling Contract History"/>
        </h2>

        <div>

            <table class="contractListTable table table-condensed table-bordered table-striped">

                <th width="200"><spring:theme code="text.account.contract.ybilling.contractCharacteristic" text="Characteristic"/></th>
                <th colspan="${contractListSize}"><spring:theme code="text.account.contract.ybilling.contractVersion" text="Contract Version"/></th>

                <tr>
                    <td></td>
                    <c:forEach items="${contractList}" var="contractData">
                        <td><label class="control-label">${contractData.contractVersion}</label></td>
                    </c:forEach>
                </tr>

                <c:forEach items="${chracteristicMap.keySet()}" var="charKey">
                    <tr>
                        <td><label class="control-label" title="${charKey}">${chracteristicMap[charKey]}</label></td>
                        <c:forEach begin="0" end="${contractListSize-1}" varStatus="loop">
                            <td>${characteristicMapList.get(loop.index)[charKey]}</td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </div>
   </c:if>
</c:if>
<c:if test="${empty contractList}">
    <h3>
        <span class="label label-warning  emptyMessage display-block">
            <spring:theme code="text.account.contract.ybilling.details.noResult" text="No contract found for this business partner id."/>
        </span>
    </h3>
</c:if>