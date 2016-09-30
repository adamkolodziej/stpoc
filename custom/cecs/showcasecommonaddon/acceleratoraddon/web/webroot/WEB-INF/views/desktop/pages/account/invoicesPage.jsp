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


<h2><spring:theme code="text.account.invoices" text="Invoices"/></h2>


<c:url var="invoices" value="/my-account/invoices/" />
<c:choose>
	<c:when test="${not empty ybillingInvoices}">
	<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-account/invoices?sort=${searchPageData.pagination.sort}" msgKey="text.account.invoices.page" numberPagesShown="${numberPagesShown}"/>
		<table id="invoicesTable" class="table table-condensed table-responsive table-bordered table-hover">
			<tr>
				<th><spring:theme code="text.account.invoices.table.invoice.number" text="Invoice number"/></th>
				<th><spring:theme code="text.account.invoices.table.billing.period" text="Billing Period"/></th>
				<th><spring:theme code="text.account.invoices.table.billing.date" text="Billing State"/></th>
				<th><spring:theme code="text.account.invoices.table.total.amount" text="Total Amount"/></th>
				<th><spring:theme code="text.account.invoices.table.payment.status" text="Payment Status"/></th>
				<th><spring:theme code="text.account.invoices.table.document" text=""/></th>
				<th><spring:theme code="text.account.invoices.table.document" text=""/></th>
			</tr>
			
			<c:forEach items="${ybillingInvoices}" var="invoice">
				<tr>
					<td>${invoice.invoiceNumber}</td>
					<td><fmt:formatDate value="${invoice.billingPeriodStart}" pattern="dd/MM/yyyy" /> - <fmt:formatDate value="${invoice.billingPeriodEnd}" pattern="dd/MM/yyyy" /></td>
					<td><fmt:formatDate value="${invoice.invoiceDate}" pattern="dd/MM/yyyy" /></td>
					<td><format:fromPrice priceData="${invoice.amountDue}"/></td>
					<td>
						<c:choose>
							<c:when test="${invoice.paymentStatus eq 'TO_PAY'}">
								<spring:theme code="text.account.invoices.table.status.to.pay" text="To Pay"/>
							</c:when>
							<c:when test="${invoice.paymentStatus eq 'PAID'}">
								<spring:theme code="text.account.invoices.table.status.paid" text="Paid"/>
							</c:when>
							<c:when test="${invoice.paymentStatus eq 'DELAYED'}">
								<spring:theme code="text.account.invoices.table.status.delayed" text="Delayed"/>
							</c:when>
							<c:when test="${invoice.paymentStatus eq 'CANCELED'}">
								<spring:theme code="text.account.invoices.table.status.canceled" text="Canceled"/>
							</c:when>
						</c:choose>
					</td>
					<td><a href="${invoices}${invoice.invoiceNumber}"> <spring:theme code="text.account.invoices.details.show" text="Show details"/> </a></td>
					<c:if test="${not empty invoice.docURL}">
						<td>
							<a href="${invoice.docURL}" target="_blank">
								<spring:theme code="text.account.invoices.download" text="Download PDF"/>
							</a>
						</td>
					</c:if>
					<c:if test="${empty invoice.docURL}">
						<td>
							<c:url var="exportPath" value="/my-account/exportInvoice">
								<c:param name="invoiceNumber" value="${invoice.invoiceNumber}" />
							</c:url>

							<a href="${exportPath}" target="_blank">
								<spring:theme code="text.account.invoices.download" text="Download PDF"/>
							</a>
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-account/invoices?sort=${searchPageData.pagination.sort}" msgKey="text.account.invoices.page" numberPagesShown="${numberPagesShown}"/>
	</c:when>
	<c:otherwise>
		<h3>
	        <span class="label label-warning  emptyMessage display-block">
	            <spring:theme code="text.account.invoices.no.invoiced" text="You have no invoices."/>
	        </span>
	    </h3>
	</c:otherwise>
</c:choose>
