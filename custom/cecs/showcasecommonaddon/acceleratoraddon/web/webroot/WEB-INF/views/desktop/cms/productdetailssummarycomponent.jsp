<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:choose>
<c:when test="${not empty product.summary}">
<div class="description ${cssClass} interlinejustify">
    <div class="row">
        <div class="col-xs-12 col-sm-8">
            <div class="text-uppercase text-primary"><spring:theme code="productdetails.productdetailssummarycomponent.description"/></div>
            <span class="text text-dark padding-top">${product.summary} - ${product.description}</span>
        </div>
    </div>
</div>
</c:when>
<c:otherwise>
	<component:emptyComponent/>
</c:otherwise>
</c:choose>

