<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>

<div class="content content-bg">

	<div class="container">
		<div id="globalMessages">
			<common:globalMessages/>
		</div>

		<div class="col-md-7">
			<div class="item_container_holder">
				<div class="title_holder">
					<div class="title">
						<div class="title-top">
							<span></span>
						</div>
					</div>
				</div>
				<div class="account-content accountContentPane">
					<h2 class="headline">
						<spring:theme code="text.account.ticketDetails" text="Request Details"/>
					</h2>


					<table class="account-profile-data table table-condensed table-responsive table-bordered table-hover">
						<tr>
							<td style="font-weight: bold;"><spring:theme code="text.account.ticketDetails.ticketId" text="Id"/></td>
							<td>${ticketData.ticketID}
								<c:if test="${not empty ticketData.c4cAttemptTicketId}">
									[${ticketData.c4cAttemptTicketId}]
								</c:if>
							</td>
						</tr>
						<c:if test="${not empty ticketData.reason}">
							<tr>
								<td style="font-weight: bold;"><spring:theme code="text.account.ticketDetails.reason" text="Reason"/></td>
								<td>${ticketData.reason.name}</td>
							</tr>
						</c:if>
						<c:if test="${not empty ticketData.area}">
							<tr>
								<td style="font-weight: bold;"><spring:theme code="text.account.ticketDetails.area" text="Area"/></td>
								<td>${ticketData.area.name}</td>
							</tr>
						</c:if>
						<tr>
							<td style="font-weight: bold;"><spring:theme code="text.account.ticketDetails.note" text="Note"/></td>
							<td>${ticketData.note}</td>
						</tr>
						<c:if test="${not empty ticketData.order}">
						<tr>
							<td style="font-weight: bold;"><spring:theme code="text.account.ticketDetails.order" text="Order"/></td>
							<td>${ticketData.order.code}</td>
						</tr>
						</c:if>
						<tr>
							<td style="font-weight: bold;"><spring:theme code="text.account.ticketHistory.status" text="Status"/></td>
							<td>${ticketData.c4cAttemptTicketStatusCode}</td>
						</tr>
						<tr>
							<td style="font-weight: bold;"><spring:theme code="text.account.ticketDetails.headline" text="Headline"/></td>
							<td>>${ticketData.headline}</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
