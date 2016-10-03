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
<c:set var="descriptionActive" value="false"/>

<div class="row">
    <div class="col-xs-12">
        <p class="font-24 color-mid-gray no-margin-bottom margin-top-30">
            Additional options you might find useful
        </p>
        <hr class="no-margin-top">
    </div>
</div>

<div class="row">

    <c:forEach var="comp" items="${offer.components}" varStatus="status">
        <c:if test="${not empty comp.options}">
            <div class="col-xs-4" style="margin-bottom:5px">
                <!-- Box -->
                <div class="new-quotation-blue-box border-radius-10 background-blue">
                    <div class="padding-bottom-10 padding-top-10 padding-left-10 padding-right-10">
                        <!-- Text -->
                        <p class="font-24 color-white">${comp.name}</p>
                        <c:forEach var="opt" items="${comp.options}" varStatus="st">
                            ${opt.product.name}
                            <ul>
                                <c:forEach var="ent" items="${opt.product.entitlements}" varStatus="st">
                                    <c:if test="$(${ent.description || ent.name}">
                                        <li>

                                            <c:choose>
                                                <c:when test="${ent.quantity != 0}">
                                                    ${ent.quantity}
                                                </c:when>
                                                <c:when test="${ent.quantity gt 1}">
                                                    ${ent.usageUnit.namePlural}
                                                </c:when>
                                                <c:when test="${ent.quantity eq 1}">
                                                    ${ent.usageUnit.name}
                                                </c:when>
                                            </c:choose>
                                            <c:if test="${!empty ent.usageCharge}">
                                                -&nbsp${ent.usageCharge.usageUnit.name}
                                            </c:if>
                                            ${ent.usageCharge.usageChargeEntries[0].price.formattedValue}
                                        </li>

                                    </c:if>
                                </c:forEach>

                            </ul>
                            <!-- Button -->
                            <div class="absolute-bottom padding-bottom-10 padding-top-10 padding-left-10 padding-right-40">
                                <c:choose>
                                    <c:when test="${opt.product.itemType eq 'ServicePlan'}">
                                        <a href="#"
                                           class="new-quotation-blue-box-add-button btn btn-white-gray btn-block font-16" data-prodid="${opt.product.code}">
                                            Add for
                                            ${opt.product.price.recurringChargeEntries[0].price.formattedValue}</a>
                                    </c:when>
                                    <c:when test="${opt.product.itemType eq 'Product'}">
                                        <a href="#"
                                           class="new-quotation-blue-box-add-button btn btn-white-gray btn-block font-16" data-prodid="${opt.product.code}">
                                           Add for
                                           ${opt.product.price.formattedValue}</a>
                                    </c:when>
                                </c:choose>
                            </div>
                        </c:forEach>
                    </div>


                </div>
            </div>
        </c:if>
    </c:forEach>
</div>
<script type="text/javascript" src="${commonResourcePath}/js/jquery.1.11.3.min.js"></script>
<script>
<c:url value="/guidedselling/option/add" var="addToCartUrl"/>
$( document ).ready(function() {
    $( ".new-quotation-blue-box-add-button" ).on('click', function() {
        $.ajax({
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            url: "${addToCartUrl}",
            data: JSON.stringify( { "productCode": $(this).attr("data-prodid"), "bundleTemplateId" : "TriCast-TV", "bundleNo" : "${offer.bundleNo}"} ),
            processData: false,
            success: function( data, textStatus, jQxhr ){
            debugger;

            },
            error: function( jqXhr, textStatus, errorThrown ){
               alert("Error: " +  errorThrown );
            }
        });
    });
});
</script>