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

	<cms:pageSlot position="Section1" var="comp" element="div" class="span-24 section1 cms_disp-img_slot">
		<cms:component component="${comp}"/>
	</cms:pageSlot>

	<div class="productDetailsPanel">

        <div class="span-14 productImage">
            <cms:pageSlot position="ProductImages" var="comp"  >
                <cms:component component="${comp}"/>
            </cms:pageSlot>
        </div>

        <div class="span-10 productDescription last">
            <ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
                <product:productPricePanel product="${product}"/>
            </ycommerce:testId>
            <ycommerce:testId code="productDetails_productNamePrice_label_${product.code}">
                <h1>
                        ${product.name}
                </h1>
            </ycommerce:testId>

            <cms:pageSlot position="ProductDetailPanelSection" var="feature" element="div" class="span-10 detailpanelsection last">
                <cms:component component="${feature}"/>
            </cms:pageSlot>

            <cms:pageSlot position="SocialButtons" var="feature" element="div" class="span-10 socialbuttons last">
                <div class="socialbutton span-2 ${(elementPos%5 == 4) ? 'last' : ''}">
                    <cms:component component="${feature}"  />
                </div>
            </cms:pageSlot>

            <cms:pageSlot position="SocialWidgets" var="feature" element="div" class="span-10 socialwidgets last">
                <cms:component component="${feature}"  />
            </cms:pageSlot>

            <cms:pageSlot position="Section2" var="feature" element="div" class="span-10 section2 cms_disp-img_slot last">
                <cms:component component="${feature}"/>
            </cms:pageSlot>
        </div>

    </div>

	<cms:pageSlot position="CrossSelling" var="comp" element="div" class="span-24">
		<cms:component component="${comp}"/>
	</cms:pageSlot>

	<cms:pageSlot position="Section3" var="feature" element="div" class="span-24 section3 cms_disp-img_slot">
		<cms:component component="${feature}"/>
	</cms:pageSlot>

		
	<cms:pageSlot position="UpSelling" var="comp" element="div" class="span-24">
		<cms:component component="${comp}"/>
	</cms:pageSlot>
	
	<product:productPageTabs />

	<cms:pageSlot position="Section4" var="feature" element="div" class="span-24 section4 cms_disp-img_slot">
		<cms:component component="${feature}"/>
	</cms:pageSlot>
	

</template:page>
