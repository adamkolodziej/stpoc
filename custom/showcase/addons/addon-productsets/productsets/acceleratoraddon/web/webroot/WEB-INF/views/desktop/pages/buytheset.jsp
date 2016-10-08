<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="productSet" tagdir="/WEB-INF/tags/addons/productsets/desktop/productset" %>

<div class="resizeableColorbox span-24">
	<div class="edit_product_set">
		<c:url var="buyTheSetUrl" value="/buytheset" />
		<form class="productSetAddToCartForm" action="${buyTheSetUrl}" method="post">
			<div class="product_set_main">
				<productSet:productItem product="${currentProduct}" variants="${currentProductSelectOptions}" imageFormat="product" />
				<div id="buySetContainer" class="scroller">
					<div>
						<productSet:promotions productSet="${productSet}" />
					</div>
					<button id="buyTheSetButton" class="positive large">${not empty buyButtonText ? buyButtonText : "Buy the Set"}</button>
				</div>
			</div><!--/main-->

			<div class="product_set_additional">
				<ul>
				<c:forEach items="${productSet.products}" var="product" varStatus="counter">
					<li>
						<productSet:productItem product="${product}" variants="${productSelectOptions[product.code]}" />
					</li>
					<c:choose>
						<c:when test="${counter.count mod 2 == 0 and !counter.last}">
							</ul><ul>
						</c:when>
						<c:when test="${counter.last}">
							</ul>
						</c:when>
					</c:choose>
				</c:forEach>
				
			</div><!--/product_set_additional-->
		</form>
	</div>
</div>

<script type="text/javascript">
	configureBuyTheSet();
	configureProductSetStyleSelectChange();
</script>