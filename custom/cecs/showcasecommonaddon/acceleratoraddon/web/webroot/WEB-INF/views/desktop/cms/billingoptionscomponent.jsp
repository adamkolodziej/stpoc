<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="row">
	<div class="col-xs-12 col-sm-12">
    	<h2 class="headline"><spring:theme code="my-account.edit.billing-title"/></h2>
        <span><spring:theme code="my-account.edit.billing-undertitle"/></span>
        <br/>
        <br/>
        <c:url var="billingFormUrl" value="/my-account/edit/billing" />
        <form:form id="billingForm" commandName="billingForm" action="${billingFormUrl}" method="POST">
        	<spring:theme code="my-account.edit.select-billing"/><br/>
	        <div class="radio">
		        <form:radiobutton path="billingOption" value="false" /><spring:theme code="my-account.edit.paperbilling" />
		        <br/>
		    </div>
		    <div class="radio">
		        	<form:radiobutton path="billingOption" value="true" /><spring:theme code="my-account.edit.epaperbilling"/>
		        	<br/>
	        </div>
	        <br/>
	        <div class="checkbox">		
			    	<form:checkbox path="remainder" value="true" /><spring:theme code="my-account.edit.payment-reminder"/>
			</div>
			<br/>
			<c:url var="billingFormCancelUrl" value="/my-account" />
            <div class="form-actions">
                <button type="button" class="btn btn-danger" onclick="window.location='${billingFormCancelUrl}'"><spring:theme code="text.account.profile.cancel" text="Cancel"/></button>
                <button type="submit" class="btn btn-success" id="billingFormSubmitButton" ><spring:theme code="text.account.profile.saveUpdates"/></button>
            </div>
		</form:form>
	</div>
</div>
