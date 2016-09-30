<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>


<c:forEach var="slot" items="${descriptionSlots}">
    <cms:component component="${slot}"/>
</c:forEach>
