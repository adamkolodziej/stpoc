<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<template:page pageTitle="${pageTitle}">
	<div id="globalMessages">
		<common:globalMessages />
	</div>

	<div class="span-24">

		<cms:pageSlot position="TopContent" var="feature" element="div"
			class="span-24 wide-content-slot cms_disp-img_slot">
			<cms:component component="${feature}" />
		</cms:pageSlot>
		<div class="span-4">
			<nav:accountNav  />
			
			<cms:pageSlot position="Section1" var="feature">
				<cms:component component="${feature}" element="div" class="section1 cms_disp-img_slot"/>
			</cms:pageSlot>
		</div>
		
		<!--/span-4-->
		<div class="span-20 last view_prodlist">
			<div class="prodlist_header">
				<h1>
						<c:out value="${productListData.name}" />

				</h1>
			<!-- 	<ul class="nav">
					<li><a href="#">Edit Details</a></li>
					<li><a href="#">Share</a></li>
					<li><a href="#">Delete</a></li>
				</ul>
				<a class="positive">Add new Product List</a>  -->
			</div>
			<!--/prodlist_header-->
			<div class="item_container_holder">
				<div class="title_holder">
					<h2><spring:theme code="productlists.items" /></h2>
				</div>
				<div class="item_container">

						
						<input type="hidden" value="${productListData.guid}" id="productListDataGuid">
						<input type="hidden" value="<c:url value="/productlists/reorder"/>" id="productlistsReorderUrl">
						<ul id="sortable">
							<c:forEach var="productListEntry"
								items="${productListData.entries}" varStatus="varSt">
								<li id="sortableLi_${productListEntry.product.code}">
								<table class="your_cart">
								<thead>
									<tr>
										<th id="header1">								
											<c:choose>
												<c:when test="${productListData.canModify}">
													<spring:theme code="productlists.move" />
												</c:when>
												<c:otherwise>
													<spring:theme code="productlists.priority" />
												</c:otherwise>
											</c:choose>
										</th>

										<th id="header2" colspan="2"><spring:theme code="productlists.productinfo" /></th>
										<th id="header3"><spring:theme code="productlists.price" /></th>
										<th id="header4"><spring:theme code="productlists.qty" /></th>
										<th id="header5"><spring:theme code="productlists.notes" /></th>
										<th id="header6"><spring:theme code="productlists.actions" /></th>
									</tr>
								</thead>
								<tbody>
								<tr>
								
									<c:url value="${productListEntry.product.url}" var="productUrl" />
									
									<td headers="header1">
										<c:choose>
											<c:when test="${productListData.canModify}">
										
									<span class="dragndrop"> <a
											href="#"></a>
									</span>
									</c:when>
									<c:otherwise>
										<p>${varSt.count}</p>
									</c:otherwise>
										</c:choose>
									</td>
									<td headers="header2" class="product_image"><span
										class="product_image"> <a href="${productUrl}"> <product:productPrimaryImage
													product="${productListEntry.product}" format="thumbnail" />
										</a>
									</span></td>
									<td headers="header2" class="product_details">
										<h2>
											<a href="${productUrl}">${productListEntry.product.name}</a>
										</h2>
										<div class="clear"></div>
										<div class="prod_refine">
											<c:out value="${productListEntry.product.summary}" />
									<!-- 		<form id="sort_form2" name="sort_form2" method="get"
												action="#">
												<label for="sortOptions2">Move to another Product
													List:</label> <select id="sortOptions2" name="sort"
													class="sortOptions">
													<option value="byDate" selected="selected">Select
														list...</option>
													<c:forEach var="productListToMoveTo"
														items="${productListsToMoveTo}">
														<option value="${productListToMoveTo.guid}">${productListToMoveTo.name}</option>
													</c:forEach>
												</select>
												<div class="form-field-button">
													<button class="form">Move</button>
												</div>
											</form>  -->
										</div>
										<!--/prod_refine-->
									</td>
									<td headers="header3">
										<p>
											<format:fromPrice
												priceData="${productListEntry.product.price}" />
										</p>
									</td>
									<td headers="header4">
										<p>
											<c:choose>
												<c:when test="${productListData.canModify}">
													<c:url value="/productlists/edit/${productListData.guid}/entry/${productListEntry.product.code}/update" var="updateWishlistFormAction" />
										
													<form:form id="updateListQtyForm${productListEntry.product.code}" action="${updateWishlistFormAction}" method="post" class="update_productlist_qty_form" >
														<input type="text" maxlength="3"  size="1" id="qty" name="quantity" class="qty" value="${productListEntry.quantity}">
														<a href="#" id="QuantityProduct_${productListEntry.product.code}" class="updateQuantityListProduct"><spring:theme code="basket.page.update"/></a>
													</form:form>
												</c:when>
												<c:otherwise>
													<input type="text" maxlength="3" disabled="disabled" size="1" id="qty" name="quantity" class="qty" value="${productListEntry.quantity}">
												</c:otherwise>
											</c:choose>
										</p>
									</td>
									<td headers="header5">
										<c:choose>
										<c:when test="${productListData.canModify}">
										<form id="updateNotesForm${productListEntry.product.code}" action="<c:url value="/productlists/edit/${productListData.guid}/entry/${productListEntry.product.code}/notes"/>" method="post" class="update_productlist_notes_form">
										
											<div class="form_field-input">
												<textarea id="notes${productListEntry.product.code}" name="notes" class="text" type="text" rows="3" cols="25" maxlength="255"><c:out value="${productListEntry.notes}" /></textarea>
											</div>
											<div  class="form-field-button">
												<button class="form" disabled="disabled"><spring:theme code="productList.updateNotes" /></button>
											</div>
										</form>
										</c:when>
										<c:otherwise>
											<div class="form_field-input">
												<textarea id="notes${productListEntry.product.code}" disabled="disabled" name="notes" class="text" type="text" rows="3" cols="25" maxlength="255"><c:out value="${productListEntry.notes}" /></textarea>
											</div>
										</c:otherwise>
										</c:choose>
										
									</td>
									<!--<td headers="header6">
									<p>Form + Button</p>
								</td>-->
									<td headers="header6" class="quantity">
										
									<!-- 		<p class="strong">
												1 x Purchased by <span>{FriendsName}</span>
											</p>
											<p class="strong">
												1 x Purchased by <span>{FriendsName}</span>
											</p>
											<div class="clear"></div>  -->
											
											
		
											<c:set var="buttonType">button</c:set>
											
											<form id="addToCartForm${productListEntry.product.code}" action="<c:url value="/productlists/move/cart/${productListData.guid}"/>" method="post" class="add_to_cart_form">
												<c:if test="${productListEntry.product.purchasable and productListEntry.product.stock.stockLevelStatus.code ne 'outOfStock' }" >
													<c:set var="buttonType">submit</c:set>
													<label for="qty"><spring:theme code="basket.page.quantity" /></label>
													<input type="text" maxlength="3"  size="1" id="qty" name="qty" class="qty" value="1">
												</c:if>
												<input type="hidden" name="productCodePost" value="${productListEntry.product.code}"/>
												<button type="${buttonType}" class="positive large <c:if test="${fn:contains(buttonType, 'button')}">out-of-stock</c:if>" <c:if test="${fn:contains(buttonType, 'button')}">disabled aria-disabled="true"</c:if>>
													<span class="icon-cart"></span><c:choose><c:when test="${productListEntry.product.purchasable and productListEntry.product.stock.stockLevelStatus.code ne 'outOfStock' }" ><spring:theme code="text.addToCart" /></c:when><c:otherwise><spring:theme code="text.addToCart.outOfStock"/></c:otherwise></c:choose>
												</button>				
											</form>
											
											
										<!-- 	<div class="clear"></div>
											<a href="#" id="#" class="">Download</a> <a href="#" id="#"
												class="">Schedule Delivery</a>  -->
									</td>
								</tr>
								</tbody>
						</table>
						</li>
							</c:forEach>
							</ul>
						
					<!-- BA Moved prod refine into product details column 
				<div class="prod_refine">
					<form id="sort_form2" name="sort_form2" method="get" action="#">
						<label for="sortOptions2">Move to another Product List:</label>
						<select id="sortOptions2" name="sort" class="sortOptions">
							<option value="byDate" selected="selected">Select list...</option>
							<option value="byOrderNumber">Option Value</option>
						</select>				
						<button class="form">Move</button>
					</form>
				</div>-->
					<!--/prod_refine-->
				</div>
				<!--item_container-->
			</div>
			<!--/item_container_holder-->
		</div>
		<!--/span-20-->
		<!-- /LS008 - TITLE -->

	</div>

</template:page>
