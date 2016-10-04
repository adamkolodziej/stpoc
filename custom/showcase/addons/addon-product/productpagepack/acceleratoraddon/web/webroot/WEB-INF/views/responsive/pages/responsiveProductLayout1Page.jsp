<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/responsive/cart" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="productpage" tagdir="/WEB-INF/tags/addons/productpagepack/responsive" %>

<template:page pageTitle="${pageTitle}">
	<jsp:attribute name="pageScripts">
		<productpage:includeJavaScript product="${product}"/>
	</jsp:attribute>

	<jsp:body>
		<c:if test="${not empty message}">
			<section id="alert" class="alert alert-info">
				<spring:theme code="${message}"/>
			</section>
		</c:if>
		
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>		
		<common:globalMessages/>		

		<section class="section1 cms_disp-img_slot">
			<div class="inner row">
				<cms:pageSlot position="Section1" var="comp">
					<cms:component component="${comp}" />
				</cms:pageSlot>
			</div>
		</section>

		<main class="product-details">
			<div class="inner row">
				<div class="product-images col-sm-6 col-md-6">
					<cms:pageSlot position="ProductImagesSection" var="comp" >
						<cms:component component="${comp}"/>
					</cms:pageSlot>
				</div>
			
				<div class="product-info col-sm-6 col-md-6">
					<div class="product-info-heading">
						<ycommerce:testId code="productDetails_name_label_${product.code}">
							<h2>${product.name}</h2>
						</ycommerce:testId>
						<ycommerce:testId code="productDetails_price_label_${product.code}">
							<product:productPricePanel product="${product}"/>
						</ycommerce:testId>
					</div>
					
					<cms:pageSlot position="ProductDetailsPanelSection" var="comp" element="div" class="section-product-details">
						<cms:component component="${comp}"  />
					</cms:pageSlot>
					
					<cms:pageSlot position="ProductOrderSection" var="comp" element="div" class="section-product-order">
						<cms:component component="${comp}"  />
					</cms:pageSlot>
										
					<cms:pageSlot position="SocialSection" var="comp" element="div" class="section-product-social">
						<cms:component component="${comp}"  />
					</cms:pageSlot>
				</div>
			</div>
		</main>

        <section class="productTabSection cms_disp-img_slot">
            <div class="inner row">
                <cms:pageSlot position="ProductTabSection" var="comp">
                    <cms:component component="${comp}"/>
                </cms:pageSlot>
            </div>
        </section>

		<section class="section2 cms_disp-img_slot">
			<div class="inner row">
				<cms:pageSlot position="Section2" var="comp" >
					<cms:component component="${comp}"/>
				</cms:pageSlot>
			</div>
		</section>
	</jsp:body>
</template:page>
