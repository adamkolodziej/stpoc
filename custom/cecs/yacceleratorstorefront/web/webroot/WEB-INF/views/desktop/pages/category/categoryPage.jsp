<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>

<template:page pageTitle="${pageTitle}">
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	<div class="row">
		<cms:pageSlot position="Section1" var="feature">
			<cms:component component="${feature}" element="div" class="col-xs-12"/>
		</cms:pageSlot>
	</div>
	<div class="row merchandise-page">
		<div class="col-xs-12 col-sm-3 refinements-container facetNavigation">
			<nav:facetNavAppliedFilters pageData="${searchPageData}"/>
			<nav:facetNavRefinements pageData="${searchPageData}"/>
			<cms:pageSlot position="Section4" var="feature">
				<cms:component component="${feature}" element="div" class="section4 small_detail"/>
			</cms:pageSlot>
		</div>
		<div class="col-xs-12 col-sm-9">
			<cms:pageSlot position="Section2" var="feature">
				<cms:component component="${feature}" />
			</cms:pageSlot>
			<cms:pageSlot position="Section3" var="feature" element="div" class="row">
				<cms:component component="${feature}" element="div" class="col-xs-12"/>
			</cms:pageSlot>
		</div>
	</div>
</template:page>