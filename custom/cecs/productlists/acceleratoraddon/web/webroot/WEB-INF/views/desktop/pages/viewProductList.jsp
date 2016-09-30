<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<template:page pageTitle="${pageTitle}">
    <div class="container" style="color:#000;">
        <div class="col-xs-12">
            <div class="row">
                <div>
                    <div class="cols-xs-12">
                        <cms:pageSlot position="TopContent" var="feature" element="div"
                                      class="cols-xs-12 cms_disp-img_slot">
                            <cms:component component="${feature}"/>
                        </cms:pageSlot>
                    </div>
                    <div class="cols-xs-2">
                        <cms:pageSlot position="Section1" var="feature">
                            <cms:component component="${feature}" element="div" class="section1 cms_disp-img_slot"/>
                        </cms:pageSlot>
                    </div>

                    <div class="cols-xs-10 last view_prodlist">
                        <div>
                            <h2 class="headline">
                                <c:out value="${productListData.name}"/>
                            </h2>
                        </div>

                        <div>
                            <input type="hidden" value="${productListData.guid}" id="productListDataGuid">
                            <input type="hidden" value="<c:url value="/productlists/reorder"/>"
                                   id="productlistsReorderUrl">
                            <table class="table table-bordered table-condensed table-striped table-responsive">
                                <thead>
                                <tr>
                                    <th id="header2" colspan="2"><spring:theme
                                            code="productlists.productinfo"/></th>
                                    <th id="header3"><spring:theme code="productlists.price"/></th>
                                    <th id="header4"><spring:theme code="productlists.qty"/></th>
                                    <th id="header5"><spring:theme code="productlists.notes"/></th>
                                    <th id="header6"><spring:theme code="productlists.actions"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="productListEntry"
                                           items="${productListData.entries}" varStatus="varSt">
                                    <tr>
                                        <c:url value="${productListEntry.product.url}" var="productUrl"/>
                                        <td headers="header2" class="product_image"><span
                                                class="product_image"> <a href="${productUrl}">
                                            <product:productPrimaryImage
                                                    product="${productListEntry.product}" format="thumbnail"/>
                                        </a>
									</span></td>
                                        <td headers="header2" class="product_details">
                                            <h2>
                                                <a href="${productUrl}">${productListEntry.product.name}</a>
                                            </h2>

                                            <div class="clear"></div>
                                            <div class="prod_refine">
                                                <c:out value="${productListEntry.product.summary}"/>
                                            </div>
                                        </td>
                                        <td headers="header3">
                                            <p>
                                                <format:fromPrice
                                                        priceData="${productListEntry.product.price}"/>
                                            </p>
                                        </td>
                                        <td headers="header4">
                                            <p>
                                                <c:choose>
                                                    <c:when test="${productListData.canModify}">
                                                        <c:url value="/productlists/edit/${productListData.guid}/entry/${productListEntry.product.code}/update"
                                                               var="updateWishlistFormAction"/>

                                                        <form:form
                                                                id="updateListQtyForm${productListEntry.product.code}"
                                                                action="${updateWishlistFormAction}" method="post"
                                                                class="update_productlist_qty_form">
                                                            <input type="text" maxlength="3" size="1" id="qty"
                                                                   name="quantity" class="qty"
                                                                   value="${productListEntry.quantity}">
                                                            <a href="#"
                                                               id="QuantityProduct_${productListEntry.product.code}"
                                                               class="updateQuantityListProduct positive btn btn-primary text-uppercase btn-xs-block btn-sm-inline-block visible"><spring:theme
                                                                    code="basket.page.update"/></a>
                                                        </form:form>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="text" maxlength="3" disabled="disabled" size="1"
                                                               id="qty" name="quantity" class="qty"
                                                               value="${productListEntry.quantity}">
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                        </td>
                                        <td headers="header5">
                                            <c:choose>
                                                <c:when test="${productListData.canModify}">
                                                    <form id="updateNotesForm${productListEntry.product.code}"
                                                          action="<c:url value="/productlists/edit/${productListData.guid}/entry/${productListEntry.product.code}/notes"/>"
                                                          method="post" class="update_productlist_notes_form">

                                                        <div class="form_field-input">
                                                            <textarea id="notes${productListEntry.product.code}"
                                                                      name="notes" class="text" type="text" rows="3"
                                                                      cols="25" maxlength="255"><c:out
                                                                    value="${productListEntry.notes}"/></textarea>
                                                        </div>
                                                        <div class="form-field-button">
                                                            <button class="positive btn btn-primary text-uppercase btn-xs-block btn-sm-inline-block visible" disabled="disabled"><spring:theme
                                                                    code="productList.updateNotes"/></button>
                                                        </div>
                                                    </form>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="form_field-input">
                                                        <textarea id="notes${productListEntry.product.code}"
                                                                  disabled="disabled" name="notes" class="text"
                                                                  type="text" rows="3" cols="25" maxlength="255"><c:out
                                                                value="${productListEntry.notes}"/></textarea>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td headers="header6" class="quantity">
                                            <c:set var="buttonType">button</c:set>

                                            <form id="addToCartForm${productListEntry.product.code}"
                                                  action="<c:url value="/productlists/move/cart/${productListData.guid}"/>"
                                                  method="post" class="add_to_cart_form">
                                                <c:if test="${productListEntry.product.purchasable and productListEntry.product.stock.stockLevelStatus.code ne 'outOfStock' }">
                                                    <c:set var="buttonType">submit</c:set>
                                                    <label for="qty"><spring:theme code="basket.page.quantity"/></label>
                                                    <input type="text" maxlength="3" size="1" id="qty" name="qty"
                                                           class="qty" value="1">
                                                </c:if>
                                                <input type="hidden" name="productCodePost"
                                                       value="${productListEntry.product.code}"/>
                                                <button type="${buttonType}"
                                                        class="positive btn btn-primary text-uppercase btn-xs-block btn-sm-inline-block visible " <c:if test="${fn:contains(buttonType, 'button')}">out-of-stock</c:if>"
                                                        <c:if test="${fn:contains(buttonType, 'button')}">disabled
                                                        aria-disabled="true"</c:if>>
                                                    <span class="icon-cart"></span><c:choose><c:when
                                                        test="${productListEntry.product.purchasable and productListEntry.product.stock.stockLevelStatus.code ne 'outOfStock' }"><spring:theme
                                                        code="text.addToCart"/></c:when><c:otherwise><spring:theme
                                                        code="text.addToCart.outOfStock"/></c:otherwise></c:choose>
                                                </button>
                                            </form>

                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>

                        <form>
                            <div class="control-group">
                                <div class="form-field-button">
                                    <c:url var="cancelUrl" value="/productlists/list"/>
                                    <a class="form positive btn btn-primary text-uppercase btn-xs-block btn-sm-inline-block visible" type="button" href="${cancelUrl}">
                                        <spring:theme code="productslists.allLists"/>
                                    </a>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</template:page>
