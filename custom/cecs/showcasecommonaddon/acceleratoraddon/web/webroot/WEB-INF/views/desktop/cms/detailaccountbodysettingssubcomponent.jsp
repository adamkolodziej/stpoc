<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<div class="row">
	<div class="col-xs-12 dashed-bottom">
    	<h4 class="text-uppercase"><spring:theme code="my-account.detailaccount1bodysubcomponent.accountsettings"/></h4>
    	<c:url value="/my-account/update-profile" var="updateProfile"/>
    	<c:url value="/my-account/update-email" var="updateEmail"/>
    	<c:url value="/my-account/update-password" var="updatePassword"/>
    	<c:url value="/my-account/change-billing-options" var="changeBillingOptions"/>

    	<ul class="list-unstyled">
	        <li><a href="${updateProfile}"><spring:theme code="text.account.profile.updatePersonalDetails"/></a></li>
	        <li><a href="${updateEmail}"><spring:theme code="text.account.profile.updateEmail"/></a></li>
	        <li><a href="${updatePassword}"><spring:theme code="text.account.profile.changePassword"/></a></li>
	        <li><a href="${changeBillingOptions}"><spring:theme code="my-account.detailaccount1bodysubcomponent.changebillingoptions"/></a></li>
		</ul>
	</div>
</div> 
