<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="gs" tagdir="/WEB-INF/tags/addons/guidedsellingaddon/desktop/guidedselling"%>

<c:if test="${not offer.changeOrder}">

    <div class="row">
        <div class="col-xs-12">
            <p class="font-24 color-mid-gray no-margin-bottom margin-top-30">
                Options included in ${packageData.name}
            </p>
            <hr class="no-margin-top">
        </div>
    </div>

    <div class="row margin-bottom-50">

        <c:forEach var="comp" items="${offer.components}" varStatus="st">

            <c:if test="${not comp.disabled}">
                <c:forEach var="opt" items="${comp.options}">
                    <c:if test="${opt.selected and (opt.lockedByPackage or comp.selectionMode == 'AUTOPICK')}">
                        <!-- Box -->
                        <div class="col-xs-4" style="margin-bottom:5px">
                            <div class="new-quotation-gray-box border-radius-10 background-gray padding-bottom-10 padding-top-10 padding-left-10 padding-right-10">
                                <!-- Text -->
                                <p class="font-24 color-mid-gray">
                                    ${opt.product.name}<br>
                                    <gs:price product="${opt.product}" priceCss="${priceCss}"
                                                                                  newPrice="${opt.bundleRuleChangedPrice}"/>

                                </p>
                                <p class="font-16 color-mid-gray">

                                </p>
                                <gs:entitlements product="${opt.product}"/>
                                <gs:usageCharges option="${opt}"/>
                            </div>


                        </div>
                    </c:if>
                </c:forEach>
            </c:if>

        </c:forEach>

    </div>

</c:if>