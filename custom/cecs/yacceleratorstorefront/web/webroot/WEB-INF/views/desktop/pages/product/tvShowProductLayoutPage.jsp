<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>

<template:page pageTitle="${pageTitle}">
    <div id="globalMessages">
    <common:globalMessages/>
    </div>
		<cms:pageSlot position="Section1" var="feature">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
    
    <cms:pageSlot position="Section2" var="feature">
        <cms:component component="${feature}" element="div" class="row full-width dark-bg episodes-container"/>
    </cms:pageSlot>

    <cms:pageSlot position="CrossSelling" var="comp" element="div" class="span-24">
        <cms:component component="${comp}"/>
    </cms:pageSlot>

    <cms:pageSlot position="Section3" var="feature" element="div" class="span-24 section4 cms_disp-img_slot">
    	<cms:component component="${feature}"/>
    </cms:pageSlot>
</template:page>