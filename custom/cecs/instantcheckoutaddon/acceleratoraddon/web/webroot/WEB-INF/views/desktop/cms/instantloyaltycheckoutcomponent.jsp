<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="ic" tagdir="/WEB-INF/tags/addons/instantcheckoutaddon/desktop" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not hidden}">
    <ic:instantcheckout product="${product}" showPrice="${displayPrice}" disabled="${disabled}" loyaltyPayment="${loyaltyPayment}">
        <jsp:attribute name="linkContent" >
            <c:if test="${not empty buttonLabel}">
                ${buttonLabel}
            </c:if>
        </jsp:attribute>
    </ic:instantcheckout>
</c:if>