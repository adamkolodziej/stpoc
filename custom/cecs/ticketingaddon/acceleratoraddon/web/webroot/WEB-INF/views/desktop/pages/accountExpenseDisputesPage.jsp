<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

<h2 class="headline">
	<spring:theme code="text.account.expensesDispute" text="Expenses Dispute"/>
</h2>

<c:url var="disputeURL" value="/my-account/expenseDisputes/add/${expenseCode}" />
<form:form action="${disputeURL}" method="POST" commandName="disputeForm">
    <form:errors/>

    <div class="form-group">
        <label for="description"><spring:theme code="text.account.expensesDispute.description" text="Dispute Description"/></label>
        <form:textarea path="description" cssClass="form-control" rows="3" />
    </div>
    <button type="submit" class="btn btn-success"><spring:theme code="text.account.expensesDispute.add" text="Create Dispute"/></button>
</form:form>

<br>
<div class="row">
    <div class="col-md-12">
        <ul class="list-group">
            <li class="list-group-item">
                <div class="row">
                    <div class="col-md-6">
                        ${expense.name}
                    </div>
                    <div class="col-md-3">
                        ${expense.type}
                    </div>
                    <div class="col-md-3">
                        <format:price priceData="${expense.amount}" displayFreeForZero="true"/>
                  </div>
                </div>
            </li>
        </ul>
    </div>
</div>



