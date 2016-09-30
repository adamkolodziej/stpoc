<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="currentDate" required="false" type="java.lang.Long" %>
<script>alert(1);</script>
<div class="form-group date-picker-widget">
    <input class="date-picker" type="text" style="color: #1c94c4;" value="" data-server-time="${currentDate}"/>
    <input class="btn btn-primary btn-sm set-time-button" type="button" value="Set"/>
    <input class="btn btn-default btn-sm reset-time-button" type="button" value="Reset"/>

    <div class="change-status" style="font-size: 12px; display: none; position:absolute;">
        <spring:theme code="timewidget.success" text="Success"/>
    </div>
</div>