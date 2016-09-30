<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- CECS-89: Migrate my-account entitlements page - START -->
<div class="col-xs-12">
	<div class="row">
		<div>
           <div>
               <h2><spring:theme code="text.account.entitlements" text="Access & Entitlements"/></h2>
           </div>
           
		   <!-- CECS-260 entitlements page improvements START -->
           <nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-account/entitlements?sort=${searchPageData.pagination.sort}" msgKey="text.account.entitlements" numberPagesShown="${numberPagesShown}"/>
           <!-- CECS-260 entitlements page improvements END -->
           
           <div>
               <c:if test="${not empty grants}">
                   <table class="table table-condensed table-responsive table-bordered table-hover" id="entitlements">
                       <thead>
                           <tr>
                               <th id="header1"><spring:theme code="text.account.entitlements.sort.name" text="Entitlement Name"/></th>
                               <th id="start"><spring:theme code="text.account.entitlements.sort.startDate" text="Start Date"/></th>
                               <th id="end"><spring:theme code="text.account.entitlements.sort.endDate" text="End Date"/></th>
                               <th id="header4"><spring:theme code="text.account.entitlements.sort.status" text="Status"/></th>
                               <th id="meteredEntitlement"><spring:theme code="text.account.entitlements.sort.metered" text="Remaining Qty"/></th>
                               <th id="location"><spring:theme code="text.account.entitlements.location" text="Location Restriction"/></th>
                           </tr>
                       </thead>
                       <tbody>
                       <c:forEach items="${grants}" var="grant">
                           <tr>
                               <td headers="header1">
                                   <ycommerce:testId code="entitlements_entitlementType_link">
                                       <p>${grant.name}</p>
                                   </ycommerce:testId>
                               </td>
                               <td headers="start">
                                   <ycommerce:testId code="redundant">
										<p><fmt:formatDate value="${grant.startTime}" pattern="dd/MM/yyyy" /></p>
                                   </ycommerce:testId>
                               </td>
                               <td headers="end">
                                   <ycommerce:testId code="redundant">
                                       <c:choose>
                                           <c:when test="${empty grant.endTime}">
                                               <p><spring:theme code="text.account.entitlements.date.end.unlimited" text="Unlimited"/></p>
                                           </c:when>
                                           <c:otherwise>
                                               <p><fmt:formatDate value="${grant.endTime}" pattern="dd/MM/yyyy" /></p>
                                           </c:otherwise>
                                       </c:choose>
                                   </ycommerce:testId>
                               </td>
                               <td headers="header4">
                                   <ycommerce:testId code="entitlements_status_label">
                                       <p>${grant.status}</p>
                                   </ycommerce:testId>
                               </td>
                               <td headers="meteredEntitlement">
                                   <ycommerce:testId code="entitlements_status_label">
                                       <c:choose>
                                       		<c:when test="${grant.remainingQuantity > -1 and grant.quantity > 0}">
                                       			<c:choose>
	                                       			<c:when test="${grant.quantity == 1}">
	                                       				<p><spring:theme code="text.account.entitlements.remainingQty" arguments="${grant.remainingQuantity},${grant.quantity},${grant.usageUnit.name}"/></p>
	                                       			</c:when>
	                                       			<c:otherwise>
	                                       				<p><spring:theme code="text.account.entitlements.remainingQty" arguments="${grant.remainingQuantity},${grant.quantity},${grant.usageUnit.namePlural}"/></p>
	                                       			</c:otherwise>
                                       			</c:choose>
                                       		</c:when>
                                       		<c:otherwise>
                                       			<spring:theme code="text.account.entitlements.metered.nometered" text=" - "/>
                                       		</c:otherwise>
                                       </c:choose>
                                   </ycommerce:testId>
                               </td>
                               <td headers="location">
                                   <c:if test="${not empty grant.conditionGeo}">
                                       <c:choose>
                                           <c:when test="${fn:length(grant.conditionGeo) gt 1}">
                                               <ul>
                                                   <c:forEach var="location" items="${grant.conditionGeo}">
                                                       <li>${location}</li>
                                                   </c:forEach>
                                               </ul>
                                           </c:when>
                                           <c:otherwise>
                                               ${grant.conditionGeo.iterator().next()}
                                           </c:otherwise>
                                       </c:choose>
                                   </c:if>
                               </td>
                           </tr>
                       </c:forEach>
                       </tbody>
                   </table>
               </c:if>
               <c:if test="${empty grants}">
                   <p><spring:theme code="text.account.entitlements.noEntitlements" text="You have no entitlements"/></p>
               </c:if>
           </div>
           
           <!-- CECS-260 entitlements page improvements START -->
           <nav:pagination top="false" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="/my-account/entitlements?sort=${searchPageData.pagination.sort}" msgKey="text.account.entitlements" numberPagesShown="${numberPagesShown}"/>
           <!-- CECS-260 entitlements page improvements END -->
           
       </div>
	</div>
</div>
<!-- CECS-89: Migrate my-account entitlements page - END -->
