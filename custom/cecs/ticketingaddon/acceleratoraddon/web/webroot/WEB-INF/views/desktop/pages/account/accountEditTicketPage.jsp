<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>

<div class="account-content item_container tickets accountContentPane" >
	<h2 class="headline">
		<spring:theme code="text.account.tickets.ticketDetails" text="Request Details"/>
	</h2>


	<div><spring:theme code="text.account.tickets.addEditform" text="Please use this form to add/edit a request."/></div>
	<div class="required"><spring:theme code="form.required" text="Fields marked * are required"/></div>

	<form:form action="add-ticket" method="post" commandName="ticketForm">
			<form:hidden path="ticketID"/>

			<formElement:formSelectBox idKey="ticket.reason" labelKey="ticket.reason" path="reason" mandatory="true" skipBlank="false" skipBlankMessageKey="ticket.reason.pleaseSelect" items="${ticketReasons}"  selectedValue="${ticketForm.reason}"/>
			<%--<formElement:formSelectBox idKey="ticket.order" labelKey="ticket.order" itemLabel="code" path="orderID" mandatory="false" skipBlank="false" skipBlankMessageKey="ticket.order.pleaseSelect" items="${orders}"  selectedValue="${ticketForm.orderID}"/>--%>
			<formElement:formSelectBox idKey="ticket.area" labelKey="ticket.area" path="area" mandatory="true" skipBlank="false" skipBlankMessageKey="ticket.area.pleaseSelect" items="${ticketAreas}"  selectedValue="${ticketForm.area}"/>
			<formElement:formInputBox  idKey="ticket.headline" labelKey="ticket.headline" path="headline" inputCSS="text" mandatory="true"/>
			<formElement:formTextArea idKey="ticket.note" labelKey="ticket.note" path="note" areaCSS="text" mandatory="true"/>

			<button class="form btn btn-primary btn-small"><spring:theme code="text.account.ticket.saveTicket" text="Save request"/></button>

	</form:form>

</div>
