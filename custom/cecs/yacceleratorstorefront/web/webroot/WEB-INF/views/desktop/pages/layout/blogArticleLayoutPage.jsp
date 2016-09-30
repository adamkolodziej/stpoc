<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<div class="col-xs-12">
    <cms:pageSlot position="Section1" var="feature">
        <cms:component component="${feature}" element="div" class="row"/>
    </cms:pageSlot>
    <div class="row">
        <cms:pageSlot position="Section2A" var="feature" element="div" class="col-xs-12 col-sm-6 col-md-9">
            <cms:component component="${feature}" />
        </cms:pageSlot>
        <cms:pageSlot position="Section2C" var="feature" element="div" class="col-xs-12 col-sm-12 col-md-3">
            <cms:component component="${feature}" />
        </cms:pageSlot>
    </div>
    <cms:pageSlot position="Section3" var="feature" element="div">
        <cms:component component="${feature}" element="div" class="row"  />
    </cms:pageSlot>
</div>
