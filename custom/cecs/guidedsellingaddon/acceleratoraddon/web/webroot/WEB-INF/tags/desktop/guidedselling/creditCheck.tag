<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="creditCheckOkay" required="true" type="java.lang.Boolean" %>

<div class="credit-check-details box">
    <div>
        <span class="title"><spring:theme code="guidedselling.creditcheck.title" text="Credit Check" /></span><br />
    </div>
    <c:if test="${creditCheckOkay}">
        <h4>
            <spring:theme code="guidedselling.creditcheck.valid"
                          text="Your Credit Check is valid!" />
        </h4>
        <h4>
            <spring:theme code="guidedselling.creditcheck.purchase"
                          text="You are able to place your order." />
        </h4>
    </c:if>
    <c:if test="${!creditCheckOkay}">
        <h4>
            <spring:theme code="guidedselling.creditcheck.invalid"
                          text="Your Credit Check is invalid!" />
        </h4>
        <h4>
            <spring:theme code="guidedselling.creditcheck.cantpurchase"
                          text="You can't place your order, insufficient funds." />
        </h4>
    </c:if>
</div>