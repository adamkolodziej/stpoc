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

<div class="${cssClass} offerComponentsHidden"
     ng-class="{offerComponentsHidden: !isLoadFinished(), offerComponentsShown: isLoadFinished()}">
    <c:if test="${not empty headline}">
        <h2>${headline}</h2>
    </c:if>
    <div>
        <ul>
            <c:forEach var="msg" items="${offer.messages}">
                <li class="alert ${msg.severity == 'ERROR' ? 'alert-danger' : ''}">
                    <span>${msg.content}</span>
                </li>
            </c:forEach>
            <li ng-repeat="msg in offer.messages" ng-class="{'alert':true, 'alert-danger':(msg.severity=='ERROR')}">
                <span ng-bind="msg.content"></span>
            </li>
        </ul>

        <%-- CECS-188 guidedselling: open in edit mode focused on TV Addons - TCO --%>
        <div ng-if="offer.focusedComponentId != undefined">
            <div class="jumbotron">
                <p>
                    <spring:theme code="guidedselling.toggle.hidden.label" text="Some options may be hidden."/>
                </p>
                <a href="" ng-click="toggleHiddenComponents()" class="btn btn-primary btn-sm">
                    <spring:theme code="guidedselling.toggle.hidden" text="Toggle extra components"/>
                </a>
            </div>
        </div>

        <div class="options">
            <c:forEach var="comp" items="${offer.components}" varStatus="st">
                <c:if test="${not empty comp.options}">
                    <!-- START: component #${st.index} id: ${comp.id} -->
                    <div class="row" ng-if="isVisibleComponent(offer.components[${st.index}])"
                         ng-hide="shouldComponentBeHidden(offer.components[${st.index}])">
                        <div class="col-xs-12 category">
                            <h3 id="${comp.id}" class="n-category-header">{{offer.components[${st.index}].name}}</h3>
                            <ul>
                                <c:forEach var="msg" items="${offer.components[st.index].messages}">
                                    <li class="alert ${msg.severity == 'ERROR' ? 'alert-danger' : ''}">
                                        <span>${msg.content}</span>
                                    </li>
                                </c:forEach>
                                <li ng-repeat="msg in offer.components[${st.index}].messages"
                                    ng-class="{'alert':true, 'alert-danger':(msg.severity=='ERROR')}">
                                    <span ng-bind="msg.content"></span>
                                </li>
                            </ul>
                            <div class="collapse" ng-class="{'in':offer.components[${st.index}].disabled}">
                                <spring:theme code="guidedselling.missing.required.component" text="{0} is required"
                                              arguments="{{offer.components[${st.index}].disabledByDependencyMessage}}"/>
                            </div>
                            <div class="collapse" ng-class="{'in':offer.components[${st.index}].disabled == false}">
                                <div class="row">
                                    <div ng-repeat="opt in offer.components[${st.index}].options" data-toggle="tooltip"
                                         data-placement="top" title="{{opt.label}}"
                                         class="n-category-option js-entitlement-option col-xs-12 col-sm-6 col-md-4"
                                         ng-class="{'discounted':opt.bundleRuleChangedPrice != null}"
                                         ng-if="!shouldBeHidden(opt)">
                                        <div class="box no-padding">
                                            <div class="status">
                                                <span ng-if="opt.addedToExistingContract" class="label label-primary"><spring:theme
                                                        code="guidedselling.added" text="Added"/></span>
                                                <span ng-if="opt.removedFromExistingContract"
                                                      class="label label-primary"><spring:theme
                                                        code="guidedselling.removed" text="Removed"/></span>
                                            </div>
                                            <div class="details">
                                                <gs:ngOptionHeader ngProduct="opt.product" format="zoom"/>
                                                <!-- CECS-95 - ability to show entitlements on the products - START -->
                                                <gs:ngEntitlements ngOption="opt"/>
                                                <!-- CECS-95 - ability to show entitlements on the products - END -->
                                                <gs:ngUsageCharges ngOption="opt"/>
                                            </div>
                                            <div class="category-footer"
                                                 ng-class="{'selected':opt.selected,'disabled':isDisabled(opt)}">
                                                <gs:ngSelector component="${comp}" ngOption="opt"
                                                               ngComponent="offer.components[${st.index}]"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <!-- END: component #${st.index} id: ${comp.id} -->
            </c:forEach>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-xs-12">
        <p class="font-24 color-mid-gray no-margin-bottom margin-top-30">
            Additional options you might find useful
        </p>
        <hr class="no-margin-top">
    </div>
</div>

<div class="row">

    <c:forEach var="comp" items="${offer.components}" varStatus="st">
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


                        </c:forEach>
                    </div>

                     <span ng-bind="${ngOption}.product.price.formattedValue"></span>

                    <!-- Button -->
                    <div class="absolute-bottom padding-bottom-10 padding-top-10 padding-left-10 padding-right-10">
                        <a href="#!"
                           class="new-quotation-blue-box-remove-button btn btn-primary btn-block active font-16">Remove
                            for $ 14.03</a>
                        <a href="#!" class="new-quotation-blue-box-add-button btn btn-white-gray btn-block font-16">Add
                            for
                            $11.73</a>
                    </div>
                </div>
            </div>
        </c:if>
    </c:forEach>
</div>