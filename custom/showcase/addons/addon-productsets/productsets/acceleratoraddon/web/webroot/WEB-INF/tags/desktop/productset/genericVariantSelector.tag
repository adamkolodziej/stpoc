<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>

<%@ attribute name="code" required="true" type="java.lang.String" %>
<%@ attribute name="baseStockLevel" required="false" type="java.lang.String" %>
<%@ attribute name="baseProductCode" required="false" type="java.lang.String" %>
<%@ attribute name="variants" required="true" type="java.util.Set" %>

<c:if test="${empty baseProductCode}">
	<c:set var="baseProductCode" value="${code}" />
</c:if>

<c:choose>
	<c:when test="${not empty variants}">
		<c:forEach items="${variants}" var="variant" varStatus="status">
			<spring:url var="reloadUrl" value="/buytheset/variant" />
			<c:set var="selectCssClass" value="" />
			
			<c:if test="${variant.leaf}">
				<c:set var="selectCssClass" value="leaf_change_select" />
			</c:if>
			
			<select class="${selectCssClass}" data-productcode="${code}" data-baseproductcode="${baseProductCode}" data-reloadurl="${reloadUrl}" data-stockurl="${stockUrl}" <c:if test="${variant.leaf}">id="variant_product_${baseProductCode}" name="variant_product_${baseProductCode}"</c:if>  <c:if test="${!variant.leaf}">disabled</c:if>>
				<c:set var="messegeSelectKey">product.variants.select.${variant.type}</c:set>	
				<option value="" <c:if test="${empty variant.selectedOption}">selected="selected"</c:if>><spring:theme code="${messegeSelectKey}"/></option>				
				<c:forEach items="${variant.options}" var="item">	
					<c:set var="selectString">${item.value}</c:set>				 
					<option value="${item.code}" <c:if test="${item.value == variant.selectedOption}">selected</c:if>>${selectString}</option>
				</c:forEach>
			</select>
			<br />
			<c:if test="${variant.leaf and not empty variant.selectedOption}">
				<c:set var="showAddButton" value="1" />	
			</c:if>
		</c:forEach>
	</c:when>
	<c:when test="${empty variants and baseStockLevel eq 'outOfStock'}">
		<c:set var="showAddButton" value="0" />	
	</c:when>
	<c:otherwise>
		<c:set var="showAddButton" value="1" />	
	</c:otherwise>
</c:choose>

<spring:url var="urltocart" value="/buytheset/addProduct" />
<button id="add_to_cart_${baseProductCode}" class="neutral addToCartButtonSet <c:if test="${empty showAddButton or showAddButton eq 0}">hidden</c:if>" data-productcode="${code}" data-urltocart="${urltocart}"><spring:theme code="basket.add.to.basket" text="Add To Bag" /></button>