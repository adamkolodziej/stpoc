<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<template:page pageTitle="${pageTitle}">
	

	<c:if test="${not empty message}">
		<spring:theme code="${message}"/>
	</c:if>
	
	<div id="globalMessages">
		<common:globalMessages/>
	</div>

	<cms:pageSlot position="Section1" var="comp" element="div" class="section1 cms_disp-img_slot">
		<cms:component component="${comp}"/>
	</cms:pageSlot>

    <div class="row">
        <div class="col-xs-12 col-sm-4">
            <cms:pageSlot position="ProductImages" var="comp" >
                <cms:component component="${comp}"/>
            </cms:pageSlot>
        </div>
        <div class="col-xs-12 col-sm-8">
            <cms:pageSlot position="ProductDetailPanelSection" var="feature" element="div" class="detailpanelsection ">
                <cms:component component="${feature}"/>
            </cms:pageSlot>
        </div>
        <div class="col-xs-12 col-sm-6">
            <cms:pageSlot position="ProductBuyingSection" var="feature">
                <cms:component component="${feature}"/>
            </cms:pageSlot>
        </div>
    </div>

	<div class="productDetailsPanel">
        <div class="row">
            <div class="col-xs-12 col-sm-5 productDescription">
                <cms:pageSlot position="SocialButtons" var="feature" element="div" class="socialbuttons ">
                    <div class="socialbutton span-2 ${(elementPos%5 == 4) ? '' : ''}">
                        <cms:component component="${feature}"  />
                    </div>
                </cms:pageSlot>
                <cms:pageSlot position="SocialWidgets" var="feature" element="div" class="socialwidgets ">
                    <cms:component component="${feature}"  />
                </cms:pageSlot>

                <cms:pageSlot position="Section2" var="feature" element="div" class="section2 cms_disp-img_slot ">
                    <cms:component component="${feature}"/>
                </cms:pageSlot>
            </div>
        </div>
    </div>

	<cms:pageSlot position="CrossSelling" var="comp" element="div" class="">
		<cms:component component="${comp}"/>
	</cms:pageSlot>

	<cms:pageSlot position="Section3" var="feature" element="div" class="section3 row cms_disp-img_slot">
		<cms:component component="${feature}"/>
	</cms:pageSlot>

		
	<cms:pageSlot position="UpSelling" var="comp" element="div" class="">
		<cms:component component="${comp}"/>
	</cms:pageSlot>

	<cms:pageSlot position="Section4" var="feature" element="div" class="section4 cms_disp-img_slot">
		<cms:component component="${feature}"/>
	</cms:pageSlot>
	

</template:page>
