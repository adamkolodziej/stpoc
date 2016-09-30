<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ attribute name="component" required="true" type="com.hybris.showcase.guidedselling.data.BundleComponentData" %>
<%@ attribute name="ngOption" required="true" type="java.lang.String" rtexprvalue="true" %>
<%@ attribute name="ngComponent" required="true" type="java.lang.String" rtexprvalue="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="btn btn-link btn-block check-btn"
        <c:choose>
            <c:when test="${component.selectionMode eq 'ONE'}">
                ng-disabled="{{isDisabled(opt)}}"
                ng-model="${ngComponent}.singleSelection"
                ng-click="singleSelectionChanged(${ngComponent}, opt)"
            </c:when>
            <c:when test="${component.selectionMode eq 'AUTOPICK'}">
                ng-model="${ngOption}.selected"
                disabled="disabled"
            </c:when>
            <c:otherwise>
                ng-disabled="{{isDisabled(opt)}}"
                ng-model="${ngOption}.selected"
                ng-click="selectionChanged(${ngComponent}, opt)"
            </c:otherwise>
        </c:choose>
     uib-btn-checkbox>
    <i class="fa fa-2x"
       ng-class="{'fa-check-square':${ngOption}.selected,'fa-square-o':!${ngOption}.selected, included: isLockedByPackage(opt) && ${ngOption}.selected}">
    </i>
    <div class="price-container">
        <ul class="list-inline list-unstyled">
            <li class="regular">
                <span ng-if="isServicePlan(${ngOption}.product.itemType)">
                    <span ng-bind="${ngOption}.product.price.recurringChargeEntries[0].price.formattedValue"></span>
                    <span ng-if="${ngOption}.bundleRuleChangedPrice == null">
                        <span ng-bind="${ngOption}.product.subscriptionTerm.billingPlan.billingTime.name"></span>
                    </span>
                </span>
                <span ng-if="isProduct(${ngOption}.product.itemType)">
                    <span ng-bind="${ngOption}.product.price.formattedValue"></span>
                </span>
            </li>
            <li class="discount" ng-if="${ngOption}.bundleRuleChangedPrice != null">
                <span >
                    <span ng-bind="${ngOption}.bundleRuleChangedPrice.formattedValue"></span>
                </span>
                <span ng-if="isServicePlan(${ngOption}.product.itemType)">
                    <span ng-bind="${ngOption}.product.subscriptionTerm.billingPlan.billingTime.name"></span>
                </span>
            </li>
        </ul>
    </div>
</div>

<%--
<c:choose>
    <c:when test="${component.selectionMode eq 'ONE'}">
        <input type="radio"
               name="${component.id}"
               id="input-{{${ngOption}.product.code}}"
               ng-disabled="{{isDisabled(opt)}}"
               ng-model="${ngComponent}.singleSelection"
               ng-value="${ngOption}.product.code"
               ng-change="singleSelectionChanged(${ngComponent}, opt)"/>
    </c:when>
    <c:when test="${component.selectionMode eq 'AUTOPICK'}">
        <input type="checkbox"
               id="input-{{${ngOption}.product.code}}"
               ng-model="${ngOption}.selected"
               disabled="disabled"/>
    </c:when>
    <c:otherwise>
        <input type="checkbox"
               id="input-{{${ngOption}.product.code}}"
               ng-disabled="{{isDisabled(opt)}}"
               ng-model="${ngOption}.selected"
               ng-change="selectionChanged(${ngComponent}, opt)"/>
    </c:otherwise>
</c:choose>

<label for="input-{{${ngOption}.product.code}}" ng-class="{included: isLockedByPackage(opt) && ${ngOption}.selected}">
    <span class="fa-stack fa-2x">
      <i class="fa fa-circle-o fa-stack-2x"></i>
      <i class="fa fa-check fa-stack-1x"></i>
    </span>
    <!-- CECS-82: Guidedselling: price change rules - START -->
    <div ng-if="${ngOption}.bundleRuleChangedPrice != null" class="guidedselling-container-price small">
        <div ng-if="isServicePlan(${ngOption}.product.itemType)" class="guidedselling-price-line-through">
            <span ng-bind="${ngOption}.product.price.recurringChargeEntries[0].price.formattedValue"></span>
        </div>
        <div ng-if="isProduct(${ngOption}.product.itemType)" class="guidedselling-price-line-through">
            <span ng-bind="${ngOption}.product.price.formattedValue"></span>
        </div>
        <div ng-if="${ngOption}.bundleRuleChangedPrice != null" class="guidedselling-price">
            <span ng-bind="${ngOption}.bundleRuleChangedPrice.formattedValue"></span>
        </div>
        <div ng-if="isServicePlan(${ngOption}.product.itemType)">
            <span ng-bind="${ngOption}.product.subscriptionTerm.billingPlan.billingTime.name"></span>
        </div>
    </div>
    <div ng-if="${ngOption}.bundleRuleChangedPrice == null" class="guidedselling-container-price">
        <div ng-if="isServicePlan(${ngOption}.product.itemType)" class="guidedselling-price">
            <span ng-bind="${ngOption}.product.price.recurringChargeEntries[0].price.formattedValue"></span>
            <span ng-bind="${ngOption}.product.subscriptionTerm.billingPlan.billingTime.name"></span>
        </div>
        <div ng-if="isProduct(${ngOption}.product.itemType)" class="guidedselling-price">
            <span ng-bind="${ngOption}.product.price.formattedValue"></span>
        </div>
    </div>
    <!-- CECS-82: Guidedselling: price change rules - END -->
    <span ng-show="{{isLockedByPackage(opt) && ${ngOption}.selected}}">
        <spring:theme code="guidedselling.option.included"/>
    </span>
</label>
--%>