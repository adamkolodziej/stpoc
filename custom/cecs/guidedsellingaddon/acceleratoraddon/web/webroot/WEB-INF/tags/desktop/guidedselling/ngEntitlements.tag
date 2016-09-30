<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ attribute name="ngOption" required="true" type="java.lang.String" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- CECS-92: Show product images on guidedselling - END -->
<div class="entitlements" ng-if="${ngOption}.product.entitlements.length > 0">
    <div class="entitlementContainer">
        <div id="{{${ngOption}.product.code}}">
            <ul>
                <li ng-repeat="ent in ${ngOption}.product.entitlements"
                    ng-class="{'collapse' : $index > 2, 'entitlement-{{${ngOption}.product.code}}' : $index > 2}">
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
                    <span ng-if="ent.usageCharge">-&nbsp{{ent.usageCharge.usageUnit.name}}
                        <strong ng-if="ent.usageCharge.usageChargeEntries">{{ent.usageCharge.usageChargeEntries[0].price.formattedValue}}</strong>
                    </span>
                </li>
            </ul>
            <a ng-if="${ngOption}.product.entitlements.length > 3" data-toggle="collapse" class="collapsed show-entitlements"
               href="{{ '.entitlement-' + ${ngOption}.product.code}}">
                <span class="more">
                    <spring:theme code="guidedselling.label.showmore" text="show more..."/>
                </span>
                <span class="less">
                    <spring:theme code="guidedselling.label.showless" text="show less..."/>
                </span>
            </a>
        </div>
    </div>
</div>