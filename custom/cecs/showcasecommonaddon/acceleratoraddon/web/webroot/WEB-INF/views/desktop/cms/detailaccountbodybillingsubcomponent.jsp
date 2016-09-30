<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>


<c:url var="expensesURL" value="/my-account/expenses" />
<c:url var="invoicesURL" value="/my-account/invoices" />
<c:choose>
    <c:when test="${not empty totalBilling}">
    	<fmt:formatDate value="${totalBilling.invoiceDate}" var="billingDate" pattern="MMMM" />
    	
        <div class="row">
            <div class="col-xs-12 dashed-bottom">
                <h4 class="text-uppercase"><spring:theme code="my-account.detailaccount2bodysubcomponent.billing"/></h4>
                <ul class="list-unstyled">
                <li><a href="${expensesURL}" class="inline-block"><spring:theme code="my-account.detailaccount2bodysubcomponent.currentlySpent"/></a><span class="inline-block">&nbsp;<format:fromPrice priceData="${totalBilling.amountDue}"/> &nbsp;<spring:theme code="my-account.detailaccount2bodysubcomponent.dueby"/> &nbsp;${billingDate}</span></li>
                <li><spring:theme code="my-account.detailaccount2bodysubcomponent.invoiceBalance"/><span class="inline-block">&nbsp;<format:fromPrice priceData="${invoiceBalance}"/></li>
                <li><a href="${invoicesURL}"><spring:theme code="my-account.detailaccount2bodysubcomponent.mostrecent"/></a></li>
                </ul>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <component:emptyComponent/>
    </c:otherwise>
</c:choose>
