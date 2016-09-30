<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<c:set var="showFullVersion" value="${not showFreeVersion}" />
<c:set var="playerClass" value="col-xs-12" />
<c:if test="${not empty usageCharge and showFullVersion}">
    <c:set var="playerClass" value="col-xs-12 col-sm-8 col-md-8" />
    <c:set var="usageChargeClass" value="col-xs-12 col-sm-4 col-md-4 usage-charge-fit js-usage-charge-fit" />
</c:if>
<div class="col-xs-12 js-not-entitled-container">

</div>



<div class="${playerClass}">
    <div class="row">
        <div class="col-xs-12 html5-video-player-container">
            <div class="embed-responsive embed-responsive-16by9 html5-video-player">
                <c:choose>
                    <c:when test="${not empty exception}">
                        <div class="alert alert-danger" role="alert">${exception}</div>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${not empty videoUrl}">
                                <div>
                                        <%--HIDE IF TRAILER--%>
                                    <h3 class="video-title">
                                            ${videoName}
                                        <c:if test="${showFreeVersion}">
                                        <span class="freepreview">
                                            <spring:theme code="html5videocomponent.freepreview" />
                                        </span>
                                        </c:if>
                                    </h3>
                                    <div class="product-notification-container-normal">
                                        <c:forEach items="${timeEvents}" var="product"  varStatus="status" >
                                            <div id="product-promotion-fragment-${status.index}-1" class="product-notification panel panel-primary" data-mute="false" data-start="${product.timeStart}" data-end="${product.timeEnd}" data-fragment-index="${status.index}">
                                                <cms:component component="${product.contentComponent}" evaluateRestriction="false"/>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <video id="${playerId}" class="html5-main-video embed-responsive-item video-component-relative" controls preload="auto" data-show-free-version="${showFreeVersion}" data-free-preview="${freePreview}" data-video-product="${videoProductCode}" data-entitlement-type="${entitlementType}">
                                        <source src="${videoUrl}" type="${videoMediaType}">
                                    </video>
                                    <div class="html5-video-content">
                                        <div class="popup-container">
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <spring:theme code="html5videocomponent.not.available"/>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<c:if test="${not empty usageCharge and showFullVersion}">
    <div class="${usageChargeClass}">
        <h3 class="title">${usageCharge.name}</h3>
        <ul class="usage-charges list-unstyled">
            <c:forEach items="${usageCharge.usageChargeEntries}" var="usageChargeEntry">
                <li>
                    <c:if test="${usageChargeEntry['class'].simpleName eq 'TierUsageChargeEntryData'}">
                        <spring:theme code="product.list.viewplans.tierUsageChargeEntry" arguments="${usageChargeEntry.tierStart}^${usageChargeEntry.tierEnd}^^${usageChargeEntry.price.formattedValue}^${usageCharge.usageUnit.name}" argumentSeparator="^"/><br/>
                    </c:if>
                    <c:if test="${usageChargeEntry['class'].simpleName eq 'OverageUsageChargeEntryData'}">
                        <span class="price">
                                ${usageChargeEntry.price.formattedValue}
                        </span>
                        <span class="name">
                                ${usageCharge.usageUnit.name}
                        </span>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<div class="col-xs-12 col-sm-3 product-notification-container-fullscreen">
    <c:forEach items="${timeEvents}" var="product"  varStatus="status" >
        <div id="product-promotion-fragment-${status.index}-2" class="product-notification panel panel-primary" data-mute="false" data-start="${product.timeStart}" data-end="${product.timeEnd}" data-fragment-index="${status.index}">
            <cms:component component="${product.contentComponent}" evaluateRestriction="false"/>
        </div>
    </c:forEach>
</div>
