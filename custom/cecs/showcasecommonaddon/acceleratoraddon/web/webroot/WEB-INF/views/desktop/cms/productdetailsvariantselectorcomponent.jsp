<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


	<div class="variants ${cssClass}">
		<c:if test="${not empty colors}">
			<span class="text-primary inline-block text-uppercase"><spring:theme code="productdetails.productvariantselector.color"/> <span id="colorName" class="selected-variant-name">${selectedColor}</span></span>
			<ul id="colorVariant" class="options" >
				<c:forEach var="color" items="${colors}">
					<c:choose>
						<c:when test="${fn:toUpperCase(color) == selectedColor}">
							<li class="colorVariantSelect selectedColor" onclick="changeColor('${color}')" data-colorVariant="p/${product.code}/${color}/selectColor" id="${color}" >
								<a href="#">
									<img src="${variantOptionsImages[fn:toUpperCase(color)].url}" title="${color}"/>
								</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="colorVariantSelect" onclick="changeColor('${color}')" data-colorVariant="p/${product.code}/${color}/selectColor" id="${color}">
								<a href="#">
									<img src="${variantOptionsImages[fn:toUpperCase(color)].url}" title="${color}"/>
								</a>
							</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</c:if>
		<c:if test="${not empty sizes}">
			<span class="text-primary inline-block text-uppercase"><spring:theme code="productdetails.productvariantselector.size"/> <span id="sizeName" class="selected-variant-name"></span></span>
			<c:if test="${empty selectedSize}">
				<c:set var="disabled" value="disabled"/>
				<c:set var="cursor" value="cursor:not-allowed;"/>
				<c:set var="selected" value="selected"/>
			</c:if>
			<select id="sizeVariant" class="options" disabled="${disabled}" onchange="location = this.value;" style="${cursor}">
				<option ${selected} disabled hidden value='' id="emptyOption"></option>
				<c:forEach var="size" items="${sizes}">
					<option ${size.sizeEnumName eq selectedSize ? "selected" : ""} value="${size.sizeEnumName}" class="sizeSelect" id="${size.sizeEnumName}">
						${size.sizeEnumName}
					</option>
				</c:forEach>
			</select>
		</c:if>
	</div>
