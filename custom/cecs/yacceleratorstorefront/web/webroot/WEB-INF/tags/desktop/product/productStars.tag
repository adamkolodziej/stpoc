<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="rating" required="true" %>
<%@ attribute name="addClass" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>




<c:if test="${not empty rating}">
<c:set var="starWidth" value="16" />
<c:set var="starCount" value="5" />


<div class="stars-container">
    <div class="stars-size-control" style="width: <fmt:formatNumber maxFractionDigits="0" value="${rating * 20}" />%;">
        <span class="rating fa">
            <span class="active"></span>
            <span class="active"></span>
            <span class="active"></span>
            <span class="active"></span>
            <span class="active"></span>
        </span>
    </div>
</div>
</c:if>