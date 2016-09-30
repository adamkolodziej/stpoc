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
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/desktop/action" %>

<c:url var="invoices" value="/my-account/invoices" />

<h2 class="headline">
	<spring:theme code="text.account.currentExpenses" text="Current Expenses"/>
</h2>

<c:if test="${not empty results}">
	<h4><spring:theme code="text.account.currentExpenses.viewExpenses" text="View your expenses for current"/>
    <a href=${invoices}> <spring:theme code="text.account.currentExpenses.invoice" text=" invoice"/> </a> </h4>

	<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-account/expenses?sort=${searchPageData.pagination.sort}" msgKey="text.account.currentExpenses.page" numberPagesShown="${numberPagesShown}"/>

	<div class="expensesList table-responsive">
		<table class="expensesListTable table table-condensed table-bordered table-striped">
			<thead>
			<tr>
				<th id="header4">
				</th>
				<th id="header1">
					<spring:theme code="text.account.currentExpenses.name" text="Name"/>
				</th>
				<th id="header2">
					<spring:theme code="text.account.currentExpenses.type" text="Type"/>
				</th>
				<th id="header3">
					<spring:theme code="text.account.currentExpenses.amount" text="Amount"/>
				</th>
				<th id="header6">
					<spring:theme code="text.account.currentExpenses.date" text="Expense Date"/>
				</th>
				<th id="header5"> </th>
			</tr>
			</thead>
			<tbody>

			<c:forEach items="${results}" var="expense" varStatus="expenseStatus">
				<tr>
					<td headers="header4">
						<input type="checkbox" name="expenseCode" value="${expense.code}" form="exportExpensesForm">
					</td>
					<td headers="header1">
							${expense.name}
					</td>
					<td headers="header2">
							${expense.type}
					</td>
					<td headers="header3">
						<format:price priceData="${expense.amount}" displayFreeForZero="true"/>
					</td>
					<td headers="header6">
                        <fmt:formatDate value="${expense.expenseDate}" pattern="dd/MM/yyyy" />
					</td>
					<td headers="header5">

						<c:set var="expenseCode" value="${expense.code}" scope="request"/>
                        <action:actions element="div" parentComponent="${component}" />

					</td>
				</tr>
			</c:forEach>
                <tr>
                    <td colspan="3" class="total total-header"> <spring:theme code="text.account.currentExpenses.total" text="TOTAL:"/> </td>
                    <td class="total"> <format:price priceData="${total}" displayFreeForZero="true"/> </td>
                    <td colspan="2"></td>
                </tr>
			</tbody>
		</table>
	</div>
	
	<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-account/expenses?sort=${searchPageData.pagination.sort}" msgKey="text.account.currentExpenses.page" numberPagesShown="${numberPagesShown}"/>
	<div>

		<c:url var="exportUrl" value="/my-account/exportExpenses" />
		<form action="${exportUrl}" method="GET" id="exportExpensesForm" target="_blank">
			<input class="btn btn-success" type="submit" value='<spring:theme code="text.account.currentExpenses.downloadSelected" text="Download selected"/>'></input>
		</form>

	</div>

</c:if>
<c:if test="${empty results}">
    <h3>
        <span class="label label-warning  emptyMessage display-block">
            <spring:theme code="text.account.currentExpenses.noCurrentExpenses" text="You have no current expenses."/>
        </span>
    </h3>
</c:if>