<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>

<template:page pageTitle="${pageTitle}">
	<div id="globalMessages">
		<common:globalMessages/>
	</div>

    <div class="row full-width dark-bg">
        <cms:pageSlot position="Section1" var="feature">
            <cms:component component="${feature}" element="div" class="col-xs-12"/>
        </cms:pageSlot>
    </div>

	<div class="row">
		<cms:pageSlot position="Section2A" var="feature" element="div" class="col-xs-12 col-sm-6">
			<cms:component component="${feature}" element="div" />
		</cms:pageSlot>

		<cms:pageSlot position="Section2B" var="feature" element="div" class="col-xs-12 col-sm-6">
			<cms:component component="${feature}" element="div" />
		</cms:pageSlot>

		<cms:pageSlot position="Section2C" var="feature" element="div" class="col-xs-12 col-sm-6">
			<cms:component component="${feature}" element="div"/>
		</cms:pageSlot>
	</div>
	<div class="row">
		<cms:pageSlot position="Section3" var="feature">
			<cms:component component="${feature}" />
		</cms:pageSlot>
	</div>
	
</template:page>
