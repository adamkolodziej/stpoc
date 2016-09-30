<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h2 class="headline">
	<spring:theme code="text.account.profile" text="Profile"/>
</h2>

<dl class="dl-horizontal account-profile-data">
    <dt><spring:theme code="profile.title" text="Title"/></dt>
    <dd>${fn:escapeXml(title.name)}</dd>
    <dt><spring:theme code="profile.firstName" text="First name"/></dt>
    <dd>${fn:escapeXml(customerData.firstName)}</dd>
    <dt><spring:theme code="profile.lastName" text="Last name"/></dt>
    <dd>${fn:escapeXml(customerData.lastName)}</dd>
    <dt><spring:theme code="profile.email" text="E-mail"/></dt>
    <dd>${fn:escapeXml(customerData.displayUid)}</dd>
</dl>

<div class="btn-group account-main-buttons" role="group" aria-label="...">
    <a class="btn btn-info" href="update-password"><i class="fa fa-lock"></i> <spring:theme code="text.account.profile.changePassword" text="Change password"/></a>
    <a class="btn btn-primary" href="update-profile"><i class="fa fa-user"></i> <spring:theme code="text.account.profile.updatePersonalDetails" text="Update personal details"/></a>
    <a class="btn btn-info" href="update-email"><i class="fa fa-envelope"></i> <spring:theme code="text.account.profile.updateEmail" text="Update email"/></a>
</div>
