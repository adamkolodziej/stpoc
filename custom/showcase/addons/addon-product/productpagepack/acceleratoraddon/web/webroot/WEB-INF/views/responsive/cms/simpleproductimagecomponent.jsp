<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/responsive/product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="responsive-product-image-component responsive-component row">
<c:choose>
	<c:when test="${showGallery and galleryPosition eq 'left'}">	
	
		<!-- thumbnails -->
		<div class="thumbnails col-sm-3">
			<product:productImageCarousel galleryImages="${galleryImages}" />	
		</div>	
		
		<!-- big image -->
		<div class="detail-image col-sm-9">
			<product:productImagePanel product="${product}" />
		</div>
	
	</c:when>
	
	<c:when test="${showGallery and galleryPosition eq 'right'}">
	
		<!-- big image -->
		<div class="detail-image col-sm-9">
			<product:productImagePanel product="${product}" />
		</div>
		
		<!-- thumbnails -->
		<div class="thumbnails col-sm-3">
			<product:productImageCarousel galleryImages="${galleryImages}" />	
		</div>	
		
	</c:when>
	
	<c:otherwise>
	
		<!-- no gallery -->
		<div class="detail-image row">
			<product:productImagePanel product="${product}" />
		</div>
		
	</c:otherwise>
</c:choose> 
</div>