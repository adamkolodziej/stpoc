<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

										

<h2 class="headline">
	<spring:theme code="text.account.ticketHistory" text="Support History"/>
</h2>
<c:choose>
	<c:when test="${not empty searchPageData.results}">
		<div>
			<spring:theme code="text.account.ticketHistory.viewTickets" text="View your requests"/>
		</div>

		<nav:pagination top="true"  supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-account/tickets?sort=${searchPageData.pagination.sort}" msgKey="text.account.tickets" numberPagesShown="${numberPagesShown}" />
		<br>

		<table class="table table-condensed table-responsive table-bordered table-hover">
			<thead>
				<tr>
					<th id="header1"><spring:theme code="text.account.ticketHistory.ticketNumber" text="Request Number"/></th>
					<th id="header2"><spring:theme code="text.account.ticketHistory.headline" text="Headline"/></th>
					<th id="header4"><spring:theme code="text.account.ticketHistory.status" text="Status"/></th>
					<th id="header5"><spring:theme code="text.account.ticketHistory.dateCreated" text="Date created"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${searchPageData.results}" var="ticket">

					<c:url value="/my-account/ticket/${ticket.ticketID}" var="myAccountTicketDetailsUrl"/>

					<tr>
						<td headers="header1" class="tablebody">
							<ycommerce:testId code="ticketHistory_ticketNumber_link">
								<a href="${myAccountTicketDetailsUrl}">${ticket.ticketID}</a>
							</ycommerce:testId>
						</td>
						<td headers="header2" class="tablebody">
							<ycommerce:testId code="orderHistory_orderStatus_label">
								<p>${ticket.headline}</p>
							</ycommerce:testId>
						</td>
						<td headers="header4" class="tablebody">
							<ycommerce:testId code="orderHistory_orderStatus_label">
								<p>${ticket.c4cAttemptTicketStatusCode}</p>
							</ycommerce:testId>
						</td>
						<td headers="header5" class="tablebody">
							<ycommerce:testId code="orderHistory_orderStatus_label">
								<p><fmt:formatDate value="${ticket.dateCreated}" pattern="dd/MM/yyyy" /></p>
							</ycommerce:testId>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="row">
			<div class="col-xs-12 col-sm-6">
				<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-account/tickets?sort=${searchPageData.pagination.sort}" msgKey="text.account.tickets" numberPagesShown="${numberPagesShown}"/>
			</div>
		</div>
	</c:when>

	<c:otherwise>
		<div>
			<p>
				<spring:theme code="text.account.tickets.no.found"/>
			</p>
		</div>
	</c:otherwise>
</c:choose>

<br>
<div class="row">
	<div class="col-xs-12 col-sm-6">
		<button class="positive right btn btn-primary btn-small" type="button" onClick="javascript:location.href = 'add-ticket';">
			<ycommerce:testId code="ticketHistory_addTicket_button">
				<spring:theme code="text.account.ticketHistory.createTicket" text="Create Request"/>
			</ycommerce:testId>
		</button>
	</div>
</div>