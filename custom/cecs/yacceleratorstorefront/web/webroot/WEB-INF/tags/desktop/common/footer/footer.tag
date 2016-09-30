<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<div class="footer">
    <div class="container-fluid">
        <cms:pageSlot position="Footer" var="feature" element="div" class="">
            <cms:component component="${feature}"/>
        </cms:pageSlot>
    </div>
</div>
