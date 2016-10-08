<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="gs" tagdir="/WEB-INF/tags/addons/guidedsellingaddon/desktop/guidedselling" %>

    <template:page pageTitle="${pageTitle}">


        <c:if test="${not empty message}">
            <spring:theme code="${message}"/>
        </c:if>

        <div id="globalMessages">
            <common:globalMessages/>
        </div>

        <div class="row grey-light">
            <div class="col-xs-12 col-sm-offset-1 col-sm-10">
                <h4 class="text-uppercase text-center packages-title">
                    <spring:theme code="guidedselling.label.plans.list" text="Plans"/>
                </h4>
                <c:forEach var="plan" items="${plans}" varStatus="planLoop">
                    <c:set var="planLoopCount" value="${planLoop.count}"/>
                    <div class="package row">
                        <div class="main-details">
                            <div class="col-xs-12 col-sm-3 ">
                                <div class="image-container text-center">
                                    <product:productPrimaryImage product="${plan}" format="product"
                                                                 cssClass="img-responsive"/>
                                </div>
                            </div>

                            <div class="col-xs-12 col-sm-9">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <span class="contract-time text-uppercase text-dark">
                                            ${plan.subscriptionTerm.termOfServiceNumber}${plan.subscriptionTerm.termOfServiceFrequency.name}
                                            <spring:theme code="guidedselling.contract.length.description"/>
                                        </span>
                                        <ul class="package-title text-uppercase list-inline">
                                            <li>${plan.name}</li>
                                        </ul>
                                        <ul class="package-quick-details list-unstyled text-dark">
                                            ${plan.description}
                                        </ul>
                                    </div>
                                    <div class="col-xs-12 package-main-details text-dark">
                                        <%--
                                        <c:forEach var="slot" items="${pack.descriptionSlots}">
                                            <div class="detail">
                                                <span class="title text-uppercase">${slot.name}
                                                    <i class="fa fa-info-circle text-primary"></i>
                                                </span>
                                                <span class="value">
                                                    ${slot.content}
                                                </span>
                                            </div>
                                        </c:forEach>
                                        --%>
                                        <div class="detail">
                                            <gs:entitlements product="${plan}"/>
                                        </div>
                                        <div class="pricing">
                                            <span class="title text-uppercase">
                                                <i class="fa fa-info-circle text-primary"></i>
                                            </span>
                                            <span class="value">
                                                <strong class="price">
                                                    ${plan.price.recurringChargeEntries[0].price.formattedValue}
                                                </strong>
                                                <i>/${plan.subscriptionTerm.billingPlan.billingTime.name}</i>
                                            </span>
                                            <!-- CECS-194 Packages Page - complete implementation START -->
                                            <ul class="additional-payments list-unstyled">
                                                <c:forEach var="recurrChargeFormat"
                                                           items="${plan.price.recurringChargeEntries}">
                                                    <li>+
                                                        <spring:theme text="${recurrChargeFormat.label}"
                                                                      arguments="${recurrChargeFormat.price.formattedValue}"
                                                                      argumentSeparator="::"/>
                                                    </li>
                                                </c:forEach>
                                                <c:forEach var="oneChargeFormat"
                                                           items="${pack.price.oneTimeChargeEntries}">
                                                    <li>+
                                                        <strong>${oneChargeFormat.price.formattedValue}</strong>
                                                        <c:out value="${oneChargeFormat.billingTime.name} - ${oneChargeFormat.name}"/>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                            <!-- CECS-194 Packages Page - complete implementation END -->
                                        </div>
                                        <c:url var="guidedSellingURL"
                                               value="/guidedselling/${bundleTemplateId}/conditional/${plan.code}"/>
                                        <a class="purchase" href="${guidedSellingURL}">
                                            <span class="fa-stack fa-2x">
                                                <i class="fa fa-circle-thin fa-stack-2x"></i>
                                                <i class="fa fa-long-arrow-right fa-stack-1x"></i>
                                            </span>
                                            <small>
                                                <spring:theme code="guidedselling.purchase"/>
                                            </small>
                                        </a>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

    </template:page>