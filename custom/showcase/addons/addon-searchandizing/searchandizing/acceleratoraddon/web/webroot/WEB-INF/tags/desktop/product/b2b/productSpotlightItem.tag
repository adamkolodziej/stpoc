<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData"%>


<spring:theme code="text.addToCart" var="addToCartText"/>
<c:url value="${product.url}" var="productUrl"/>

<div class="spotlight  clearfix">
    <div class="title_holder">

        <!--title-->
        <h2>
            ${product.manufacturer}&nbsp;${product.name}
        </h2>
    </div>
    <!--title_holder-->


    <div class="thumb">
	
		<span class="stars large" style="display: inherit;">
			<c:if test="${not empty product.averageRating}">
                <span style="width: <fmt:formatNumber maxFractionDigits="0" value="${product.averageRating * 24}" />px;"></span>
            </c:if>
					
		</span>
        <ycommerce:testId code="searchPage_price_label_${product.code}">
            <p class="price" >
                <c:choose>
                    <c:when test="${product.multidimensional and (product.priceRange.minPrice.value ne product.priceRange.maxPrice.value)}">
                        <format:price priceData="${product.priceRange.minPrice}"/> - <format:price priceData="${product.priceRange.maxPrice}"/>
                    </c:when>
                    <c:otherwise>
                        <format:price priceData="${product.price}"/>
                    </c:otherwise>
                </c:choose>
                <c:if test="${product.stock.stockLevelStatus.code eq 'lowStock' }">
                    <span class='listProductLowStock '><spring:theme code="product.variants.only.left" arguments="${product.stock.stockLevel}"/></span>
                </c:if>
            </p>
        </ycommerce:testId>
        <a href="${productUrl}">
            <product:productPrimaryImage product="${product}" format="thumbnail"/>
        </a>

    </div>



    <!--thumb-->
    <div class="details">
        <p>${product.summary}</p>

        <div class="cart">

            <div class="add_to_basket ">
                <ycommerce:testId code="searchPage_addToCart_button_${product.code}">
                    <c:set var="buttonType">submit</c:set>
                    <c:choose>
                        <c:when test="${product.stock.stockLevelStatus.code eq 'outOfStock' }">
                            <c:set var="buttonType">button</c:set>
                            <spring:theme code="text.addToCart.outOfStock" var="addToCartText"/>
                        </c:when>

                    </c:choose>

                    <c:choose>
                        <c:when test="${product.multidimensional}">
                            <c:url var="backToProductUrl" value="${productUrl}" />
                            <c:url var="productOrderFormUrl" value="${productUrl}/orderForm"/>
                            <a href="${backToProductUrl}" class="button right" ><spring:theme code="product.view.details" /></a>
                            <sec:authorize ifAnyGranted="ROLE_CUSTOMERGROUP">
                                <a href="${productOrderFormUrl}"  class="button right"><spring:theme code="order.form" /></a>
                            </sec:authorize>
                        </c:when>
                        <c:otherwise>
                            <form id="addToCartForm${product.code}" action="<c:url value="/cart/add"/>" method="post"
                                  class="add_to_cart_form">
                                <input type="hidden" name="productCodePost" value="${product.code}"/>
                                <button type="${buttonType}"
                                        class="positive large <c:if test="${fn:contains(buttonType, 'button')}">out-of-stock</c:if>"
                                        <c:if test="${fn:contains(buttonType, 'button')}">disabled aria-disabled="true"</c:if>>
                                    <span class="icon-cart"></span>${addToCartText}
                                </button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </ycommerce:testId>
            </div>


        </div>

    </div>
    <!--details-->

</div>
<!--banner-->