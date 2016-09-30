<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<div style="position: fixed; top: 7px; left: 5px; z-index: 9999;">
    <input class="date-picker" type=text style="color: #1c94c4;" value="${currentDate}" >
    <input class="btn btn-primary btn-sm set-time-button" type="button" value="Set" >
    <input class="btn btn-default btn-sm reset-time-button" type="button" value="Reset" >
    <div class="change-status" style="font-size: 12px; color: ghostwhite; font-weight: bold; display: none; position:absolute;">success</div>
</div>