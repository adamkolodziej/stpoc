<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>
<%@ taglib prefix="productSet" tagdir="/WEB-INF/tags/addons/productsets/desktop/productset" %>

<script type="text/javascript">

	jQuery(document).ready(function() {
		productSetCarousel({
			rootContainerId: 'buytheset-${component.uid}'
		});
	});

</script>

<c:choose>
	<c:when test="${not empty productSets}">
		<div id="buytheset-${component.uid}" class="buytheset scroller product_set product_set_buytheset" data-componentuid="${component.uid}">
			<div class="title">
			    ${not empty title ? title : "Complete The Set"}
            </div><!--/title-->
						
			<div class="product_set_container">
				<ul class="product_set_list">
					<c:forEach items="${productSets}" var="productSet" varStatus="status">
						<li>
						
							<spring:url var="buyTheSetUrl" value="/buytheset">
								<spring:param name="productSetCode" value="${productSet.code}" />
								<spring:param name="currentProductCode" value="${currentProduct.code}" />
								<spring:param name="componentUid" value="${component.uid}" />
							</spring:url>
							<input type="hidden" id="current-set-url-${status.count}" value="${buyTheSetUrl}" />
							
							<c:forEach items="${productSet.products}" var="product">
								<c:choose>
									<c:when test="${clickAction.code eq 'showQuickView'}">
										<c:url value="${product.url}/quickView" var="productUrl"/>
										<c:url value="productSetPopup" var="elementPopupClass"/>
									</c:when>
									<c:otherwise>
										<c:url value="${product.url}" var="productUrl"/>
										<c:url value="" var="elementPopupClass"/>
									</c:otherwise>
								</c:choose>
								<div class="product_set_element">
									<span>
										<a href="${productUrl}" class="${elementPopupClass}">
											<product:productPrimaryImage product="${product}" format="thumbnail"/>
										</a>
									</span>
									<h3><a href="${productUrl}" class="${elementPopupClass}">${product.name}</a></h3>
									<p><a href="${productUrl}" class="${elementPopupClass}"><format:fromPrice priceData="${product.price}"/></a></p>
								</div>	
							</c:forEach>

						</li>
					</c:forEach>
				</ul>
			</div>
            <productSet:navigationControls isNavigation="${multipleSets}" />
		</div>
	</c:when>
	<c:otherwise>
		<component:emptyComponent/>
	</c:otherwise>
</c:choose>
