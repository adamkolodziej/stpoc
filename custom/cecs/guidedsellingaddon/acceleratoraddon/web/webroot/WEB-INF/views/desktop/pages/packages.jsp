<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="gs" tagdir="/WEB-INF/tags/addons/guidedsellingaddon/desktop/guidedselling" %>

<template:page pageTitle="${pageTitle}">

	<c:set var="showUsageCharges" scope="session" value="false"/>
	<c:set var="showOneTimeChargeTotal" scope="session" value="false"/>

    <c:if test="${not empty message}">
        <spring:theme code="${message}"/>
    </c:if>

    <div id="globalMessages">
        <common:globalMessages/>
    </div>
    
	<div id="checkoutFunnel">
        <gs:checkoutProgressBar />
    </div>

    <div class="row grey-light">
        <div class="col-xs-12 col-sm-offset-1 col-sm-10">
            <h4 class="text-uppercase text-center packages-title"><spring:theme code="guidedselling.label.packageslist"/></h4>
            <c:forEach var="pack" items="${packages}" varStatus="packLoop">
                <c:set var="packLoopCount" value="${packLoop.count}" />
                <div class="package row">
                    <div class="main-details">
                        <div class="col-xs-12 col-sm-3 ">
                            <div class="image-container text-center"> 
                            	 <gs:packagePrimaryImage product="${pack}" cssClass="img-responsive" showMissing="true" />                               
                            </div>
                        </div>

                        <div class="col-xs-12 col-sm-9">
                            <div class="row">
                                <div class="col-xs-12">
                                    <span class="contract-time text-uppercase text-dark">
                                        ${pack.subscription.subscriptionTerm.termOfServiceNumber}${pack.subscription.subscriptionTerm.termOfServiceFrequency.name} <spring:theme code="guidedselling.contract.length.description"/>
                                    </span>
                                    <ul class="package-title text-uppercase list-inline">
                                        <li>${pack.name}</li>
                                    </ul>
                                    <ul class="package-quick-details list-unstyled text-dark">
                                        ${pack.description}
                                    </ul>
                                </div>
                                <div class="col-xs-12 package-main-details text-dark">
                                    <cms:component component="${pack.descriptionSlotsContainer}"/>
                                    <div class="pricing">
                                        <span class="title text-uppercase"><i class="fa fa-info-circle text-primary"></i></span>
                                        	<c:if test="${not empty pack.recurringChargeEntryTotalPrice.formattedValue && not empty pack.subscription.subscriptionTerm.billingPlan.billingTime.name}"> 
		                                        <span class="value">
		                                            	<strong class="price">${pack.recurringChargeEntryTotalPrice.formattedValue}</strong><i>/${pack.subscription.subscriptionTerm.billingPlan.billingTime.name}</i>
		                                        </span>
                                            </c:if>
                                        <!-- CECS-194 Packages Pag:-)e - complete implementation START -->
                                        <ul class="additional-payments list-unstyled">
                                        	<li>								
                                        	 	<c:if test="${not empty pack.subscription.name}">* <strong>${pack.subscription.name}</strong> - </c:if>
                                        	 	<c:if test="${pack.subscription.price.recurringChargeEntries[0].discounted}"> <strong><spring:theme code="guidedselling.packages.discounted"/>&nbsp;</strong></c:if>
                                        	 	<spring:theme text="${pack.subscription.price.recurringChargeEntries[0].label}" arguments="${pack.subscription.price.recurringChargeEntries[0].price.formattedValue}" argumentSeparator="::" /> </li>
                                        	 	
                                            <c:forEach var="recurrChargeFormat" items="${pack.recurringChargeEntryFormattedList}">
                                                <li>* <strong>${recurrChargeFormat.packageName}</strong> - 
                                                <c:if test="${recurrChargeFormat.discounted}"> <strong><spring:theme code="guidedselling.packages.discounted"/>&nbsp;</strong></c:if>
                                                <spring:theme text="${recurrChargeFormat.label}" arguments="${recurrChargeFormat.price.formattedValue}" argumentSeparator="::" /> </li>
                                            </c:forEach>                                           
                                           <!--   -->
                                        </ul>
                                        
                                        <c:if test="${showOneTimeChargeTotal}">
	                                        <span class="value">
	                                            <strong>${pack.onInstallationTotalPrice.formattedValue}</strong><i> <spring:theme code="guidedselling.packages.onInstallation"/></i>
	                                        </span>
                                        </c:if>
                                       
	                                        <ul class="additional-payments list-unstyled">                                            
	                                            <c:forEach var="oneChargeFormat" items="${pack.oneTimeChargeEntryFormattedList}">
	                                                <li>* <strong>${oneChargeFormat.packageName}</strong> - <strong>${oneChargeFormat.price.formattedValue}</strong> <c:out value="${oneChargeFormat.billingTime.name} - ${oneChargeFormat.name}" /> </li>
	                                            </c:forEach>                                           
	                                        </ul>                                        
                                        <!-- CECS-194 Packages Page - complete implementation END -->
                                        
                                       <c:if test="${showUsageCharges}">    
                                         <ul class="additional-payments list-unstyled">                                     
                                           <c:forEach var="usageCharge" items="${pack.usageCharges}">
                                                <c:forEach var="usageChargeEntry" items="${usageCharge.usageChargeEntries}">
                                                    <li>* ${usageCharge.usageUnit.name} - <strong>${usageChargeEntry.price.formattedValue}</strong></li>
                                                </c:forEach>
                                            </c:forEach>
                                         </ul>
                                       </c:if>     
                                       
                                    </div>
                                    <c:url var="guidedSellingURL" value="/guidedselling/${bundleTemplateId}/package/${pack.code}" />
                                    <a class="purchase btn btn-tricast-primary" href="${guidedSellingURL}">
                                        <spring:theme code="guidedselling.purchase"/>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div id="package-info-${packLoopCount}" class="col-xs-12 package-info-container">
                            <cms:component component="${pack.relatedComponentsContainer}"/>
                        </div>
                        <div class="toggle-details-container">
                            <a class="toggle-details" href="#" data-target="#package-info-${packLoopCount}">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-circle-thin fa-stack-2x"></i>
                                    <i class="fa fa-stack-1x switch-icon"></i>
                                </span>
                                <span class="text-open"><spring:theme code="guidedselling.fulldetails"/></span>
                                <span class="text-close"><spring:theme code="guidedselling.close"/></span>
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

</template:page>
