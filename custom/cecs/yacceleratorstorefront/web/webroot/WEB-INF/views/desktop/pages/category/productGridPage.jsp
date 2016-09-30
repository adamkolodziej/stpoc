<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>

<template:page pageTitle="${pageTitle}">
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	<cms:pageSlot position="Section1" var="feature">
		<cms:component component="${feature}" element="div" class="span-24 section1 cms_disp-img_slot"/>
	</cms:pageSlot>

	<div class="row">
		<div class="col-xs-12 col-sm-3 col-md-3 refinements-container facetNavigation">
			<a class="btn btn-dark btn-block visible-xs show-filters collapsed" data-toggle="collapse" href="#refinements" aria-expanded="false" aria-controls="refinements">
				Filters <i class="fa pull-right"></i>
			</a>
			<cms:pageSlot position="ProductLeftRefinements" var="feature">
				<cms:component component="${feature}"/>
			</cms:pageSlot>
		</div>
		<div class="col-xs-12 col-sm-9 col-md-9">
			<cms:pageSlot position="ProductGridSlot" var="feature">
				<cms:component component="${feature}"/>
			</cms:pageSlot>
		</div>
	</div>
</template:page>