<%@ attribute name="subscriptionData" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>

<!-- CECS-113: Migrate my-account/subscription/{subscriptionId} page from telco -->
<c:if test="${not empty subscriptionData.entitlements}">
    <ul>
        <c:forEach items="${subscriptionData.entitlements}" var="entitlement" varStatus="loop">
            <li>
                <strong>${entitlement.name}</strong>

                    <%--<c:if test="${entitlement.usageUnit eq null}">
                        <spring:theme code="text.account.subscription.label.yes" text="Yes"/>
                    </c:if>--%>
                    <c:if test="${entitlement.usageUnit ne null}">
                        <span class="label label-primary">

                            <c:choose>
                                <c:when test="${entitlement.quantity lt 0}">
                                    <spring:theme code="product.list.viewplans.entitlements.unlimited"/>
                                </c:when>
                                <c:when test="${entitlement.quantity eq 1}">
                                    <spring:theme code="product.list.viewplans.entitlements.meteredEntitlement" arguments="${entitlement.quantity}^${entitlement.usageUnit.name}" argumentSeparator="^" />
                                </c:when>
                                <c:otherwise>
                                    <spring:theme code="product.list.viewplans.entitlements.meteredEntitlement" arguments="${entitlement.quantity}^${entitlement.usageUnit.namePlural}" argumentSeparator="^" />
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </c:if>

            </li>
        </c:forEach>
    </ul>
</c:if>