<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="variant" tagdir="/WEB-INF/tags/responsive/variant" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/responsive/nav/breadcrumb" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>
<%@ taglib prefix="responsivecms" uri="http://hybris.com/tld/responsivecms"%>

<template:page pageTitle="${pageTitle }">
	<common:globalMessages/>
	<section class="mainbody cms_disp-img_slot">
		<div class="inner row">
		
			<div class="lookbook-left gallery col-sm-4 col-md-3">
				<div class="image-holder has-dots">
					<div class="responsive-product-image responsive-image-container responsive-component">
						<responsivecms:noscript media="${productSet.mediaContainer}"></responsivecms:noscript>
						<img class="responsive-image" src="${request.contextPath}/_ui/responsive/common/images/spinner.gif"/>
					</div>
				</div>
        	</div>
        	<div class="lookbook-right col-sm-8 col-md-9">
         	    <h1>${productSet.name }</h1>
				<ul class="result-grid">
				<c:forEach items="${productSet.products }" var="product">
						<li class="result-container" >
                            <c:url var="productUrl" value="${product.url}" />
                            <a href="${productUrl}" class="productMainLink" title="${product.name}">
							    <product:responsiveProductPrimaryImage product="${product}"/>
                            </a>
                            <product:productPricePanel  product="${product}"/>
							<variant:apparelVariantSelector product="${product}"/>
							<product:productAddToCartPanel product="${product}" allowAddToCart="${true}"/>

						</li>
				</c:forEach>
				</ul>
        	</div>
		</div>
	</section>
</template:page>
