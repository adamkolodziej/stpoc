<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="filledRatesAmount" required="true" type="java.lang.Double" %>
<%@ attribute name="maxDisplayedRatesAmount" required="true" type="java.lang.Integer" %>
<%@ attribute name="classForFullRate" required="true" type="java.lang.String" %>
<%@ attribute name="classForHalfRate" required="true" type="java.lang.String" %>
<%@ attribute name="classForEmptyRate" required="true" type="java.lang.String" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<ul class="product-rating list-unstyled list-inline">
	<c:forEach var="i" begin="1" end="${maxDisplayedRatesAmount }">
	    <c:choose>
	        <c:when test="${i <= filledRatesAmount}">
	            <li><i class="fa ${classForFullRate }"></i></li>
	        </c:when>
	        <c:otherwise>
	            <c:choose>
	                <c:when test="${1 - (i - filledRatesAmount) >= 0.4}">
	                    <li><i class="fa ${classForHalfRate }"></i></li>
	                </c:when>
	                <c:otherwise>
	                    <li><i class="fa ${classForEmptyRate }"></i></li>
	                </c:otherwise>
	            </c:choose>
	        </c:otherwise>
	    </c:choose>
	</c:forEach>
</ul>