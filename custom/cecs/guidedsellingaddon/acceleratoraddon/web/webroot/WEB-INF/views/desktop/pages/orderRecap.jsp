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
     
    <div id="checkoutFunnel">
        <gs:guidedsellingOrderCheckoutProgressBar/>
    </div>  

    <div class="row text-dark configure-package">
		<div class="col-xs-12 col-sm-9 configure-column">

			<div class="header text-center">
				<h3 class=""><spring:theme code="guidedselling.order.placed.header" arguments="${offer.orderCode}" /></h3>
			</div>

			<ul class="list-unstyled">
				<c:forEach var="msg" items="${offer.messages}">
					<li>
						<span class="${msg.severity}">${msg.content}</span>
					</li>
				</c:forEach>
			</ul>

			<div class="options">
				<div class="row">
					<div class="col-xs-12 n-category">
						<h3 class="n-category-header"><spring:theme code="type.GuidedSellingComponent.includedInOrder.name" text="Included in your Order"/></h3>
						<c:forEach var="comp" items="${offer.components}" varStatus="st">
						<c:if test="${fn:length(comp.options) gt 0}">
							<c:url var="editUrl" value="/guidedselling/edit/${offer.orderCode}/focus/${comp.id}" />
							<div class="row js-eqh">
								<div class="col-xs-12">
									<h3 id="${comp.id}" class="n-category-header">${comp.name}</h3>
								</div>
								<c:if test="${comp.disabled}">
									<spring:theme code="guidedselling.missing.required.component" text="{0} is required" arguments="${comp.disabledByDependencyMessage}" />
								</c:if>
								<c:if test="${not comp.disabled}">
									<c:url var="editUrl" value="/guidedselling/edit/${offer.orderCode}/focus/${comp.id}" />
									<c:choose>
										<c:when test="${not comp.anythingSelected}">
											<div class="col-xs-12">
												<c:set var="opt" value="${comp.options[0]}"/>
												<h3 class="text-left nothing-selected">
													<spring:theme code="guidedselling.component.not.selected" text="Nothing is selected" />
												</h3>
												<div class="row">
													<div class="n-category-option js-entitlement-option col-xs-12 col-sm-6 col-md-4" title="${opt.disableMessage}">
														<div class="box no-padding">
															<div class="edit-contract">
																<a class="btn btn-sptel-secondary btn-block" href="${editUrl}"><spring:theme code="guidedselling.add" text="Add Options" /></a>
															</div>
															<div class="details js-details">
																<!-- CECS-95 - ability to show entitlements on the products -->
																<gs:optionHeader product="${opt.product}" imageOnly="true" />
																<span class="more text-center">
																	<c:set var="compOptionsLength" value="${fn:length(comp.options)}"/>
																	<c:choose>
																		<c:when test="${compOptionsLength gt 1}">
																			<spring:theme code="guidedselling.moreOptions" text="And {0} more ..." arguments="${compOptionsLength-1}"/>
																		</c:when>
																		<c:otherwise>
																			<spring:theme code="guidedselling.onlyOption" text="{0} option available" arguments="${compOptionsLength}"/>
																		</c:otherwise>
																	</c:choose>
																</span>
															</div>
														</div>
													</div>
												</div>
											</div>
										</c:when>
										<c:otherwise>
											<c:forEach var="opt" items="${comp.options}">
												<c:if test="${opt.selected}">
													<div class="n-category-option js-entitlement-option col-xs-12 col-sm-6 col-md-4" title="${opt.disableMessage}">
														<div class="box no-padding">
															<div class="edit-contract">
																<a class="btn btn-sptel-secondary btn-block" href="${editUrl}"><spring:theme code="guidedselling.edit" text="Edit" /></a>
															</div>
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
																		<ul class="list-inline list-unstyled ${not empty opt.bundleRuleChangedPrice ? 'promotion' : ''}">
																			<gs:price product="${opt.product}" priceCss="${priceCss}" newPrice="${opt.bundleRuleChangedPrice}" />
																		</ul>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</c:if>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</c:if>
							</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<%--TODO:SHOULD BE COMPONENT LIKE GUIDEDSELLINGTOTALSCOMPONENT.JSP--%>
		<c:if test="${not empty offer.orderPrices}">
			<div class="col-xs-12 col-sm-3">
				<div class="summary-column box">
					<div class="totals">
						<h2 class="column-title"><spring:theme code="guidedselling.label.totals"/></h2>
						<c:forEach var="orderPrice" items="${offer.orderPrices}">
							<div class="costs">
								<c:if test="${orderPrice.totalPrice.value != 0}">
									<span class="title text-pink">
										<c:choose>
											<c:when test="${orderPrice.billingTime.code=='paynow'}">
												<c:set var="iconClass" value="fa-shopping-cart"/>
											</c:when>
											<c:when test="${orderPrice.billingTime.code=='onfirstbill'}">
												<c:set var="iconClass" value="fa-file-text-o"/>
											</c:when>
											<c:when test="${orderPrice.billingTime.code=='monthly'}">
												<c:set var="iconClass" value="fa-refresh"/>
											</c:when>
											<c:otherwise >
												<c:set var="iconClass" value=""/>
											</c:otherwise>
										</c:choose>
										<i class="fa pull-left fa-2x ${iconClass}"></i>
										<span class="pull-left text-description">${orderPrice.billingTime.name}</span>
										<span class="pull-right text-description">${orderPrice.totalPrice.formattedValue}</span>
									</span>
									<table class="table table-condensed">
										<c:forEach var="totalsEntry" items="${orderPrice.totalsEntries}">
											<tr>
												<td class="text-left description">
														${totalsEntry.productName}
													<c:if test="${totalsEntry.addedToExistingContract}">
														<span class="label label-primary"><spring:theme code="guidedselling.added" text="Added" /></span>
													</c:if>
												</td>
												<td class="text-right price">${totalsEntry.totalPrice.formattedValue}</td>
											</tr>
										</c:forEach>
									</table>
								</c:if>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</c:if>
	</div>

</template:page>
