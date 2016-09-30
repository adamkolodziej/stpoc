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
    <div id="componentDiv" class="${cssClass} offerComponentsShown">
        <c:if test="${not empty headline}">
            <h2>${headline}</h2>
        </c:if>
        <div class="options">
            <div class="row">
                <div class="col-xs-12 category js-eqh">
                    <h3 class="n-category-header"><spring:theme code="guidedselling.included.header" text="Included in this Package"></spring:theme></h3>
                    <c:forEach var="comp" items="${offer.components}" varStatus="st">
                        <c:if test="${not comp.disabled}">
                            <c:forEach var="opt" items="${comp.options}">
                                <c:if test="${opt.selected and (opt.lockedByPackage or comp.selectionMode == 'AUTOPICK')}">
                                    <div class="n-category-option js-entitlement-option col-xs-12 col-sm-6 col-md-4 ${opt.bundleRuleChangedPrice != null ? 'discounted' : ''}" title="${opt.disableMessage}">
                                        <div class="box no-padding">
                                            <div class="details js-details">
                                                <!-- CECS-95 - ability to show entitlements on the products -->
                                                <gs:optionHeader product="${opt.product}" />
                                                <gs:entitlements product="${opt.product}" />
                                                <gs:usageCharges option="${opt}" />
                                            </div>
                                            <div class="category-footer selected disabled">
                                                <div class="btn btn-link btn-block check-btn" disabled="disabled">
                                                    <i class="fa fa-2x fa-check-square included"></i>
                                                    <div class="price-container">
                                                        <ul class="list-inline list-unstyled">
                                                            <gs:price product="${opt.product}" priceCss="${priceCss}" newPrice="${opt.bundleRuleChangedPrice}" />
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</c:if>
