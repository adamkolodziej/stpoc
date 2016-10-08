<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>

<template:page pageTitle="${pageTitle}">
	<div id="globalMessages">
		<common:globalMessages/>
	</div>

	<cms:pageSlot position="Section1" var="feature">
		<cms:component component="${feature}" element="div" class="span-24 section1 cms_disp-img_slot"/>
	</cms:pageSlot>

	<div class="span-24">
		<div class="span-6 facetNavigation">
			<nav:facetNavAppliedFilters pageData="${searchPageData}"/>
			<nav:facetNavRefinements pageData="${searchPageData}"/>

			<cms:pageSlot position="Section5" var="feature">
				<cms:component component="${feature}" element="div" class="section5 cms_disp-img_slot"/>
			</cms:pageSlot>
		</div>
		<div class="span-18 last">
		
			<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}"  numberPagesShown="${numberPagesShown}"/>
			
			<div class="productList">
				<c:set var="heroCount" value="0"/>
					<c:forEach items="${searchPageData.results}" var="product" varStatus="status" > 
						<c:if test="${product != null && product.solrHeroProductDefinitions != null}">
							<c:forEach var="solrHeroProductDefinition" items="${product.solrHeroProductDefinitions}">
								<c:if test="${solrHeroProductDefinition.categoryCode == searchPageData.categoryCode}">
									<product:productListerItem product="${product}"/>
									<c:set var="heroCount" value="${status.count}"/>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
			</div>
		
			<cms:pageSlot position="Section2" var="feature">
				<cms:component component="${feature}" element="div" class="section2 cms_disp-img_slot"/>
			</cms:pageSlot>

				<cms:pageSlot position="Section3" var="feature">
					<cms:component component="${feature}" element="div" class="section3 cms_disp-img_slot"/>
				</cms:pageSlot>

				<div class="productList">
					<c:forEach items="${searchPageData.results}" var="product" varStatus="status" begin="${heroCount}">
						<product:productListerItem product="${product}"/>
					</c:forEach>
				</div>

				<cms:pageSlot position="Section4" var="feature">
					<cms:component component="${feature}" element="div" class="section4 cms_disp-img_slot last"/>
				</cms:pageSlot>
			
			
				<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}"  numberPagesShown="${numberPagesShown}"/>
		
		</div>
	</div>



</template:page>