<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<template:page pageTitle="${pageTitle}">
	<div id="globalMessages">
		<common:globalMessages />
	</div>

	<div class="span-24">

		<cms:pageSlot position="TopContent" var="feature" element="div"
			class="span-24 wide-content-slot cms_disp-img_slot">
			<cms:component component="${feature}" />
		</cms:pageSlot>

		<!-- LS003 - My Account Product Lists -->
		<div class="span-4">
			<nav:accountNav  />

			
			<cms:pageSlot position="Section1" var="feature">
				<cms:component component="${feature}" element="div" class="section1 cms_disp-img_slot"/>
			</cms:pageSlot>
			<!--/span-4 nav_column-->
		</div>
		<!--/span-4-->
		<div class="span-20 last my_prodlists">
			<!--
			<div class="prodlist_header">
			<h1>{PersonsName's} {ProdListName}</h1>
			<ul class="nav">
				<li><a href="#">Edit Details</a></li>
				<li><a href="#">Share</a></li>
				<li><a href="#">Delete</a></li>
			</ul>
			<a class="positive">Add new Product List</a>
		</div>-->
			<!--/prodlist_header-->
			<div class="item_container_holder">
				<div class="title_holder">
					<h2>My Product Lists</h2>
				</div>
				<div class="item_container">
					<a class="positive right" href="create">Add new Product List</a>
					<table>
						<tbody>
							<c:forEach var="productList" items="${productLists}">
								<tr>
									<c:url value="/productlists/view/${productList.guid}"
										var="viewEncodedUrl" />
									<c:url value="/productlists/edit/${productList.guid}"
										var="editEncodedUrl" />
									<td><a href="${viewEncodedUrl}">(${fn:length(productList.entries)})
											${productList.name} (${productList.guid})</a></td>
									<td><a href="${viewEncodedUrl}">View</a></td>
									<td><a href="${editEncodedUrl}">Edit</a></td>
									<td><a href="#">Share</a></td>
									<td><a href="#">Delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<!--/item_container_holder-->
		</div>
		<!--/span-20-->
		<!-- /LS003 - My Account Product Lists -->

	</div>

</template:page>