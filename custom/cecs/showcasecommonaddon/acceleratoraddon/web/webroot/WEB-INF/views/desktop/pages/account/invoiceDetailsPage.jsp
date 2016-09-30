<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/desktop/address"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>

<div class="accountContentPane">
<h2><spring:theme code="text.account.invoice.details.title" text="Invoice details"/></h2>


<c:choose>
	<c:when test="${not empty invoice}">
		
		<div class="account-profile-data">
	  		<dl class="dl-horizontal data-list">
                   <dt><spring:theme code="text.account.invoices.table.invoice.number" text="Invoice number"/></dt>
                   <dd>${invoice.invoiceNumber}</dd>
                    <dt><spring:theme code="text.account.invoice.details.date" text="Invoice date"/></dt>
                   <dd><fmt:formatDate value="${invoice.invoiceDate}" pattern="dd/MM/yyyy" /></dd>
                    <dt><spring:theme code="text.account.invoices.table.billing.period" text="Invoice number"/></dt>
                   <dd><fmt:formatDate value="${invoice.billingPeriodStart}" pattern="dd/MM/yyyy" /> - <fmt:formatDate value="${invoice.billingPeriodEnd}" pattern="dd/MM/yyyy" /></dd>
                    <dt><spring:theme code="text.account.invoices.table.total.amount" text="Total amount"/></dt>
                   <dd><format:fromPrice priceData="${invoice.amountDue}"/></dd>
               </dl>
		</div>
		<div class="expensesList table-responsive">
		<table class="expensesListTable table table-condensed table-bordered table-striped">
			<thead>
			<tr>
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
				
			</tr>
			</thead>
			<tbody>

			<c:forEach items="${expenses}" var="expense" varStatus="expenseStatus">
				<tr>
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
					
				</tr>
			</c:forEach>
                
			</tbody>
		</table>
	</div>
	</c:when>
	<c:otherwise>
		<h3>
	        <span class="label label-warning  emptyMessage display-block">
	            <spring:theme code="text.account.invoices.no.invoiced" text="You have no invoices."/>
	        </span>
	    </h3>
	</c:otherwise>
</c:choose>
</div>
