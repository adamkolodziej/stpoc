<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>

<div>
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
	                <div class="row" ng-if="isVisibleComponent(offer.components[${st.index}])">
	                    <div class="col-xs-12 category">
	                        <h3 id="${comp.id}" ng-bind="offer.components[${st.index}].name" class="category-header"></h3>
	                        <ul>
	                            <c:forEach var="msg" items="${offer.components[st.index].messages}">
	                                <li class="alert ${msg.severity == 'ERROR' ? 'alert-danger' : ''}">
	                                    <span>${msg.content}</span>
	                                </li>
	                            </c:forEach>
	                            <li ng-repeat="msg in offer.components[${st.index}].messages" ng-class="{'alert':true, 'alert-danger':(msg.severity=='ERROR')}">
	                                <span ng-bind="msg.content"></span>
	                            </li>
	                        </ul>
	
	                        <div class="collapse" ng-class="{in:offer.components[${st.index}].disabled}">
	                            <spring:theme code="guidedselling.missing.required.component" text="{0} is required" arguments="{{offer.components[${st.index}].disabledByDependencyMessage}}" />
	                        </div>
	                        <div class="collapse" ng-class="{in:offer.components[${st.index}].disabled == false}">
	                            <div ng-repeat="opt in offer.components[${st.index}].options" data-toggle="tooltip" data-placement="right" title="{{opt.label}}"  class="n-category-option js-entitlement-option col-xs-12 col-sm-6 col-md-4">
	                                <div class="status">
										<span ng-if="opt.addedToExistingContract" class="label label-primary"><spring:theme code="guidedselling.added" text="Added" /></span>
										<span ng-if="opt.removedFromExistingContract" class="label label-primary"><spring:theme code="guidedselling.removed" text="Removed" /></span>
									</div>
									<div class="details">
	                                    <div class="title" ng-bind="opt.product.name"></div>
	                                    <div class="description"></div>
	                                    <!-- CECS-95 - ability to show entitlements on the products - START -->
	                                    <div class="entitlements" ng-if="opt.product.entitlements.length > 0">
	                                        <b><spring:theme code="guidedselling.label.details" text="Details:" /></b>
	                                        <div id="{{opt.product.code}}">
	                                            <ul>
	                                                <li ng-repeat="ent in opt.product.entitlements" ng-class="{'collapse' : $index > 2, 'entitlement-{{opt.product.code}}' : $index > 2}">
	                                                    <span title="{{ent.name}} ({{ent.id}})">{{ent.description || ent.name}}</span>
	                                                    <span ng-if="ent.quantity != 0" class="label label-primary">
	                                                        {{ent.quantity}}
	                                                        <span ng-if="ent.quantity > 1">
	                                                            {{ent.usageUnit.namePlural}}
	                                                        </span>
	                                                        <span ng-if="ent.quantity == 1">
	                                                            {{ent.usageUnit.name}}
	                                                        </span>
	                                                    </span>
	                                                </li>
	                                            </ul>
	                                            <a ng-if="opt.product.entitlements.length > 3" data-toggle="collapse" class="collapsed show-entitlements" href="{{ '.entitlement-' + opt.product.code}}" >
	                                                <spring:theme code="guidedselling.label.showmore" text="show more..." />
	                                            </a>
	                                        </div>
	                                        <!-- CECS-92: Show product images on guidedselling - START -->
	                                        <span ng-repeat="image in opt.product.images">
	                                            <span ng-if="image.imageType == 'PRIMARY' && image.format == 'thumbnail'">
	                                                <img ng-src="{{image.url}}" class="thumb">
	                                            </span>
	                                        </span>
	                                        <!-- CECS-92: Show product images on guidedselling - END -->
	                                    </div>
	                                    <!-- CECS-95 - ability to show entitlements on the products - END -->
	
	                                    <div class="usageCharges" ng-if="opt.usageCharges.length > 0">
	                                        <a data-toggle="collapse" href="{{ '#' + opt.product.code}}_usageCharges" class="collapsed">
	                                            <spring:theme code="guidedselling.label.usageCharges" text="Usage Charges ({0}):" arguments="{{opt.usageCharges.length}}" />
	                                            &nbsp;<i class="fa fa-chevron"></i>
	                                        </a>
	                                        <div id="{{opt.product.code}}_usageCharges" class="collapse">
	                                            <ul>
	                                                <li ng-repeat="usageCharge in opt.usageCharges">
	                                                    <span>{{usageCharge.usageUnit.name}} - <strong>{{usageCharge.price.formattedValue}}</strong></span>
	                                                </li>
	                                            </ul>
	                                        </div>
	                                    </div>
	
	                                </div>
	                                <div class="inputs">
	                                    <div class="input"> 										
	                                      <c:choose>
	                                            <c:when test="${comp.selectionMode eq 'ONE'}">
	                                                <input type="radio"
	                                                       name="${comp.id}"
	                                                       id="input-{{opt.product.code}}"
	                                                       ng-disabled="{{isDisabled(opt)}}"
	                                                       ng-model="offer.components[${st.index}].singleSelection"
	                                                       ng-value="opt.product.code"
	                                                       ng-change="singleSelectionChanged(offer.components[${st.index}], opt)"/>
	                                                <label for="input-{{opt.product.code}}" ng-class="{included: isLockedByPackage(opt) && opt.selected}">
	                                                    <span class="fa-stack fa-2x">
	                                                      <i class="fa fa-circle-o fa-stack-2x"></i>
	                                                      <i class="fa fa-check fa-stack-1x"></i>
	                                                    </span>
	                                                    <!-- CECS-82: Guidedselling: price change rules - START -->
	                                                    <div ng-if="opt.bundleRuleChangedPrice != null" class="guidedselling-container-price small">
	                                                        <div ng-if="isServicePlan(opt.product.itemType)" class="guidedselling-price-line-through">
	                                                            <span ng-bind="opt.product.price.recurringChargeEntries[0].price.formattedValue"></span>
	                                                            <span ng-bind="opt.product.subscriptionTerm.billingPlan.billingTime.name"></span>
	                                                        </div>
	                                                        <div ng-if="isProduct(opt.product.itemType)" class="guidedselling-price-line-through">
	                                                            <span ng-bind="opt.product.price.formattedValue"></span>
	                                                        </div>
	                                                        <div ng-if="opt.bundleRuleChangedPrice != null" class="guidedselling-price">
	                                                            <span ng-bind="opt.bundleRuleChangedPrice.formattedValue"></span>
	                                                        </div>
	                                                    </div>
	                                                    <div ng-if="opt.bundleRuleChangedPrice == null" class="guidedselling-container-price">
	                                                        <div ng-if="isServicePlan(opt.product.itemType)" class="guidedselling-price">
	                                                            <span ng-bind="opt.product.price.recurringChargeEntries[0].price.formattedValue"></span>
	                                                            <span ng-bind="opt.product.subscriptionTerm.billingPlan.billingTime.name"></span>
	                                                        </div>
	                                                        <div ng-if="isProduct(opt.product.itemType)" class="guidedselling-price">
	                                                            <span ng-bind="opt.product.price.formattedValue"></span>
	                                                        </div>
	
	                                                    </div>
	                                                    <!-- CECS-82: Guidedselling: price change rules - END -->
	                                                    <span ng-show="{{isLockedByPackage(opt) && opt.selected}}"><spring:theme code="guidedselling.option.included"/></span>
	                                                </label>
	                                            </c:when>
	                                            <c:when test="${comp.selectionMode eq 'AUTOPICK'}">
	                                                <input type="checkbox" ng-model="opt.selected" disabled="disabled" />
	                                                <label ng-class="{included: isLockedByPackage(opt) && opt.selected}">
	                                                    <span class="fa-stack fa-2x">
	                                                      <i class="fa fa-circle-o fa-stack-2x"></i>
	                                                      <i class="fa fa-check fa-stack-1x"></i>
	                                                    </span>
	                                                    <!-- CECS-82: Guidedselling: price change rules - START -->
	                                                    <div ng-if="opt.bundleRuleChangedPrice != null" class="guidedselling-container-price small">
	                                                        <div ng-if="isServicePlan(opt.product.itemType)" class="guidedselling-price-line-through">
	                                                            <span ng-bind="opt.product.price.recurringChargeEntries[0].price.formattedValue"></span>
	                                                            <span ng-bind="opt.product.subscriptionTerm.billingPlan.billingTime.name"></span>
	                                                        </div>
	                                                        <div ng-if="isProduct(opt.product.itemType)" class="guidedselling-price-line-through">
	                                                            <span ng-bind="opt.product.price.formattedValue"></span>
	                                                        </div>
	                                                        <div ng-if="opt.bundleRuleChangedPrice != null" class="guidedselling-price">
	                                                            <span ng-bind="opt.bundleRuleChangedPrice.formattedValue"></span>
	                                                        </div>
	                                                    </div>
	                                                    <div ng-if="opt.bundleRuleChangedPrice == null" class="guidedselling-container-price">
	                                                        <div ng-if="isServicePlan(opt.product.itemType)" class="guidedselling-price">
	                                                            <span ng-bind="opt.product.price.recurringChargeEntries[0].price.formattedValue"></span>
	                                                            <span ng-bind="opt.product.subscriptionTerm.billingPlan.billingTime.name"></span>
	                                                        </div>
	                                                        <div ng-if="isProduct(opt.product.itemType)" class="guidedselling-price">
	                                                            <span ng-bind="opt.product.price.formattedValue"></span>
	                                                        </div>
	
	                                                    </div>
	                                                    <!-- CECS-82: Guidedselling: price change rules - END -->
	                                                    <spring:theme code="guidedselling.option.included"/>
	                                                </label>
	                                            </c:when>
	                                            <c:otherwise>
	                                                <input type="checkbox"
	                                                       id="input-{{opt.product.code}}"
	                                                       ng-disabled="{{isDisabled(opt)}}"
	                                                       ng-model="opt.selected"
	                                                       ng-change="selectionChanged(offer.components[${st.index}], opt)"/>
	                                                <label for="input-{{opt.product.code}}" ng-class="{included: isLockedByPackage(opt) && opt.selected}">
	                                                    <span class="fa-stack fa-2x">
	                                                      <i class="fa fa-circle-o fa-stack-2x"></i>
	                                                      <i class="fa fa-check fa-stack-1x"></i>
	                                                    </span>
	                                                    <!-- CECS-82: Guidedselling: price change rules - START -->
	                                                    <div ng-if="opt.bundleRuleChangedPrice != null" class="guidedselling-container-price small">
	                                                        <div ng-if="isServicePlan(opt.product.itemType)" class="guidedselling-price-line-through">
	                                                            <span ng-bind="opt.product.price.recurringChargeEntries[0].price.formattedValue"></span>
	                                                            <span ng-bind="opt.product.subscriptionTerm.billingPlan.billingTime.name"></span>
	                                                        </div>
	                                                        <div ng-if="isProduct(opt.product.itemType)" class="guidedselling-price-line-through">
	                                                            <span ng-bind="opt.product.price.formattedValue"></span>
	                                                        </div>
	                                                        <div ng-if="opt.bundleRuleChangedPrice != null" class="guidedselling-price">
	                                                            <span ng-bind="opt.bundleRuleChangedPrice.formattedValue"></span>
	                                                        </div>
	                                                    </div>
	                                                    <div ng-if="opt.bundleRuleChangedPrice == null" class="guidedselling-container-price">
	                                                        <div ng-if="isServicePlan(opt.product.itemType)" class="guidedselling-price">
	                                                            <span ng-bind="opt.product.price.recurringChargeEntries[0].price.formattedValue"></span>
	                                                            <span ng-bind="opt.product.subscriptionTerm.billingPlan.billingTime.name"></span>
	                                                        </div>
	                                                        <div ng-if="isProduct(opt.product.itemType)" class="guidedselling-price">
	                                                            <span ng-bind="opt.product.price.formattedValue"></span>
	                                                        </div>
	
	                                                    </div>
	                                                    <!-- CECS-82: Guidedselling: price change rules - END -->
	                                                    <span ng-show="{{isLockedByPackage(opt) && opt.selected}}"><spring:theme code="guidedselling.option.included"/></span>
	                                                </label>
	                                            </c:otherwise>
	                                        </c:choose>
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
