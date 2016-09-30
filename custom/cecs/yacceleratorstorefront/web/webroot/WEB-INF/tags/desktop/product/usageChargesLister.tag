<%@ attribute name="subscriptionData" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>

<!-- CECS-113: Migrate my-account/subscription/{subscriptionId} page from telco -->
<c:if test="${not empty subscriptionData.price and not empty subscriptionData.price.usageCharges}">
    <ul class="usage-charges">
        <c:forEach items="${subscriptionData.price.usageCharges}" var="usageCharge" varStatus="loop">
            <li>
                <strong>${usageCharge.name}:</strong>
                <span class="label label-info">
                    <c:if test="${not empty usageCharge.usageChargeEntries}">
                        <c:forEach items="${usageCharge.usageChargeEntries}" var="usageChargeEntry">
                            <c:if test="${usageChargeEntry['class'].simpleName eq 'TierUsageChargeEntryData'}">
                                <spring:theme code="product.list.viewplans.tierUsageChargeEntry" arguments="${usageChargeEntry.tierStart}^${usageChargeEntry.tierEnd}^^${usageChargeEntry.price.formattedValue}^${usageCharge.usageUnit.name}" argumentSeparator="^"/><br/>
                            </c:if>
                            <c:if test="${usageChargeEntry['class'].simpleName eq 'OverageUsageChargeEntryData'}">
                                <spring:theme code="product.list.viewplans.overageUsageChargeEntry" arguments="${usageChargeEntry.price.formattedValue},${usageCharge.usageUnit.name}"/>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </span>
            </li>
        </c:forEach>
    </ul>
</c:if>