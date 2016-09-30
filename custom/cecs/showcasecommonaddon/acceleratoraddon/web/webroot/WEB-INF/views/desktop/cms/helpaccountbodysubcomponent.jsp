<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
	<div class="col-xs-12 no-padding-left">
    	<h4 class="text-uppercase"><spring:theme code="my-account.helpaccountbodysubcomponent.needhelp"/></h4>
        <span>
        <c:set var="contactUs"><a href="#"><spring:theme code="my-account.helpaccountbodysubcomponent.contactus"/></a></c:set>
        <spring:theme code="my-account.helpaccountbodysubcomponent.underneedhelp" arguments="${contactUs}" text="If you have any question or need help with your account, you may {0} to assist you." />
        <br/>
        <br/>
        <span>
	        <spring:theme code="my-account.helpaccountbodysubcomponent.custserv1"/><br/>
	        <spring:theme code="my-account.helpaccountbodysubcomponent.custserv2"/><br/>
	        <spring:theme code="my-account.helpaccountbodysubcomponent.custserv3"/><br/>
	        <spring:theme code="my-account.helpaccountbodysubcomponent.custserv4"/><br/>
        </span>
	</div>
</div>