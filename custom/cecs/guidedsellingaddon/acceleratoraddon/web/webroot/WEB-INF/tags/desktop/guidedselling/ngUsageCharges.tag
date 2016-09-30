<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ attribute name="ngOption" required="true" type="java.lang.String" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="usageCharges" ng-if="${ngOption}.usageCharges.length > 0">
    <a data-toggle="collapse" href="{{ '#' + ${ngOption}.product.code}}_usageCharges" class="collapsed">
        <spring:theme code="guidedselling.label.usageCharges" text="Usage Charges ({0}):" arguments="{{${ngOption}.usageCharges.length}}" />
        &nbsp;<i class="fa fa-chevron"></i>
    </a>
    <div id="{{${ngOption}.product.code}}_usageCharges" class="collapse">
        <ul>
            <li ng-repeat="usageCharge in ${ngOption}.usageCharges">
                <span>{{usageCharge.usageUnit.name}} - <strong>{{usageCharge.price.formattedValue}}</strong></span>
            </li>
        </ul>
    </div>
</div>