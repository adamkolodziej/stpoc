<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="responsivecms" uri="http://hybris.com/tld/responsivecms"%>

<c:set var="componentId" value="responsiveCarousel-${uid}" />

<script type="text/javascript">
/*<![CDATA[*/
	$(document).ready(function() {
		$('#${componentId}').carousel();
        ProductSetCollectionCarouselCallback.init('#${componentId}');
	});
/*]]>*/
</script>

<div class="responsive-productset-collection-component responsive-component">
	<div class="inner">
		<div id="${componentId}" class="carousel slide horizontal" data-items-row="${perRow}">
			<!-- Carousel items -->
			<div class="carousel-inner">
				<c:forEach items="${productSets}" var="row" varStatus="rowCounter">
				<div class="item ${rowCounter.count == 1 ? 'active': ''}">
					<c:forEach items="${row}" var="column" varStatus="counter">
						<div class="responsive-component responsive-image-container">
								<a href="${request.contextPath}/sets/${column.code }">
									<responsivecms:noscript media="${column.mediaContainer}"/>
									<img class="responsive-image" src="${request.contextPath}/_ui/responsive/common/images/spinner.gif"/>
								</a>
						</div>
					</c:forEach>
				</div>
				</c:forEach>
			</div>
			
			<!-- Carousel nav -->
			<a class="left carousel-control" href="#${componentId}" data-slide="prev"></a>
			<a class="right carousel-control" href="#${componentId}" data-slide="next"></a>
		</div>
	</div>
</div>