<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/desktop/action" %>

<h2 class="headline">
	<spring:theme code="text.account.orderHistory" text="Orders"/>
</h2>
<c:if test="${not empty searchPageData.results}">
	<div class="">
		<spring:theme code="text.account.orderHistory.viewOrders" text="View your orders"/>
	</div>

	<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-account/orders?sort=${searchPageData.pagination.sort}" msgKey="text.account.orderHistory.page" numberPagesShown="${numberPagesShown}"/>

	<table class="table table-condensed table-responsive table-bordered table-hover">
		<thead>
			<tr>
				<th id="header1">
					<spring:theme code="text.account.orderHistory.orderNumber" text="Order Number"/>
				</th>
				<th id="header2">
					<spring:theme code="text.account.orderHistory.orderStatus" text="Order Status"/>
				</th>
				<th id="header3">
					<spring:theme code="text.account.orderHistory.datePlaced" text="Date Placed"/>
				</th>
				<th id="header4">
					<spring:theme code="text.account.orderHistory.total" text="Total"/>
				</th>
				<th id="header5">
					<spring:theme code="text.account.orderHistory.actions" text="Actions"/>
				</th>
			</tr>
		</thead>

		<tbody>
		<c:forEach items="${searchPageData.results}" var="order">
			<c:set var="labelClass" value="primary"></c:set>
			<c:if test="${fn:toUpperCase(order.statusDisplay) eq 'ERROR'}">
				<c:set var="labelClass" value="danger"></c:set>
			</c:if>
			<tr >
				<td headers="header1">
					<ycommerce:testId code="orderHistory_orderNumber_link">
						${order.code}
					</ycommerce:testId>
				</td>
				<td headers="header2">
					<ycommerce:testId code="orderHistory_orderStatus_label">
							<span class="label label-${labelClass}"><spring:theme code="text.account.order.status.display.${order.statusDisplay}"/></span>
					</ycommerce:testId>
				</td>
				<td headers="header3">
					<ycommerce:testId code="orderHistory_orderDate_label">
							<fmt:formatDate value="${order.placed}" pattern="dd/MM/yyyy"/>
					</ycommerce:testId>
				</td>
				<td headers="header4">
					<ycommerce:testId code="orderHistory_Total_links">
						${order.total.formattedValue}
					</ycommerce:testId>
				</td>
				<td headers="header5">
					<c:set var="orderCode" value="${order.code}" scope="request"/>
					<action:actions element="div" parentComponent="${component}"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-account/orders?sort=${searchPageData.pagination.sort}" msgKey="text.account.orderHistory.page" numberPagesShown="${numberPagesShown}"/>

</c:if>
<c:if test="${empty searchPageData.results}">
    <h3>
        <span class="label label-warning  emptyMessage display-block">
            <spring:theme code="text.account.orderHistory.noOrders" text="You have no orders"/>
        </span>
    </h3>
</c:if>



