<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="gs"
		   tagdir="/WEB-INF/tags/addons/guidedsellingaddon/desktop/guidedselling"%>
<%@ taglib prefix="ts" tagdir="/WEB-INF/tags/addons/showcasecommonaddon/desktop"  %>

<template:page pageTitle="${pageTitle}">

	<div ng-app="payment-selection">
	<div ng-controller="PaymentSelectionController">

		<c:if test="${not empty message}">
			<spring:theme code="${message}" />
		</c:if>

		<div id="globalMessages">
			<common:globalMessages />
		</div>

		<div id="checkoutFunnel">
			<gs:guidedsellingSummaryCheckoutProgressBar />
		</div>

		<div class="row text-dark configure-package">
			<div class="col-xs-12 col-sm-9 configure-column">

				<ul class="list-unstyled">
					<c:forEach var="msg" items="${offer.messages}">
						<li><span class="${msg.severity}">${msg.content}</span></li>
					</c:forEach>
				</ul>

				<div class="checkout-details row">
					<div class="offerComponentsHidden"
						 ng-class="{offerComponentsHidden: !isLoadFinished(), offerComponentsShown: isLoadFinished()}">

						<div class="col-xs-4">
							<gs:deliveryDetails />
						</div>

						<div class="col-xs-4">
							<gs:paymentDetails />
						</div>

						<div class="col-xs-4">
							<gs:creditCheck creditCheckOkay="${creditCheckOkay}" />
							</br>
							<div class="contract-start-date box">
								<div>
									<span class="title">
										<c:if test="${not isEditMode}">
											<spring:theme code="guidedselling.contract.startDate"/>
										</c:if>
										<c:if test="${isEditMode}">
											<spring:theme code="guidedselling.contract.changeDate"/>
										</c:if>
									</span>
								</div>
								<ts:timeSwitcher/>
							</div>
						</div>

					</div>

					<div class="options col-xs-12">
					<c:if test="${eligibleForLoyaltyPayment}">
						<div class="row">
							<div class="col-xs-12 category js-eqh">
								<h3 class="n-category-header"><spring:theme code="guidedselling.option.additional.payment" text="Additional payment options:"/></h3>
								<div class="row">

									<c:forEach var="comp" items="${offer.components}" varStatus="st">
										<!-- START: component #${st.index} id: ${comp.id}-->
										<c:if test="${not empty comp.options}">
											<c:if test="${comp.disabled}">
													<spring:theme code="guidedselling.missing.required.component" text="{0} is required" arguments="${comp.disabledByDependencyMessage}" />
											</c:if>
											<c:if test="${not comp.disabled}">
	<%--
												<c:if test="${not comp.anythingSelected}">
													<div class="col-xs-12">
														<span>
															<spring:theme code="guidedselling.component.not.selected" text="Nothing is selected" />
														</span>
													</div>
												</c:if>
	--%>
												<c:forEach var="opt" items="${comp.options}">
												<c:set var="newSelection" value="${offer.changeOrder ? opt.addedToExistingContract : true}" />
												<c:if test="${opt.selected and newSelection and not empty opt.product.loyaltyPointsPrice}">
														<div class="n-category-option js-entitlement-option col-xs-12 col-sm-6 col-md-4" title="${opt.disableMessage}">
															<div class="box no-padding">
																<div class="details">
																	<!-- CECS-95 - ability to show entitlements on the products -->
																	<gs:optionHeader product="${opt.product}" />
																	<gs:entitlements product="${opt.product}" />
																	<gs:usageCharges option="${opt}" />
																</div>
																<div class="footer-select">
																	<select class="form-control"
																			ng-model="loyaltyPayment${opt.product.code}"
																			ng-change="paymentChanged(${offer.bundleNo},${opt.orderEntryNumber},'${opt.product.code}',loyaltyPayment${opt.product.code})"
																			ng-init="loyaltyPayment${opt.product.code}='${opt.loyaltyPayment}'">
																		<c:if test="${opt.bundleRuleChangedPrice != null}">
																			<c:if test="${opt.product.price['class'].simpleName eq 'SubscriptionPricePlanData'}">
																				<c:set var="billingTime" value="${opt.product.subscriptionTerm.billingPlan.billingTime.name.toLowerCase()}"/>
																			</c:if>
																			<option value="false">
																				<c:out value="${opt.bundleRuleChangedPrice.formattedValue} ${billingTime}" />
																			</option>
																		</c:if>
																		<c:if test="${opt.product.price['class'].simpleName eq 'SubscriptionPricePlanData'}">
																			<c:if test="${opt.bundleRuleChangedPrice eq null}">
																				<option value="false">
																					<c:out value="${opt.product.price.recurringChargeEntries[0].price.formattedValue} ${opt.product.subscriptionTerm.billingPlan.billingTime.name.toLowerCase()}" />
																				</option>
																			</c:if>
																			<option value="true">
																				<c:out value="${opt.product.loyaltyPointsPrice.recurringChargeEntries[0].price.formattedValue} ${opt.product.subscriptionTerm.billingPlan.billingTime.name.toLowerCase()}" />
																			</option>
																		</c:if>
																		<c:if test="${opt.product.price['class'].simpleName ne 'SubscriptionPricePlanData'}">
																			<c:if test="${opt.bundleRuleChangedPrice eq null}">
																				<option value="false">${opt.product.price.formattedValue}</option>
																			</c:if>
																			<option value="true">${opt.product.loyaltyPointsPrice.formattedValue}</option>
																		</c:if>
																	</select>
																</div>
																<div class="category-footer selected disabled">
																	<div class="btn btn-link btn-block check-btn" disabled="disabled">
																		<i class="fa fa-2x fa-check-square included"></i>
																		<div class="price-container">
																			<ul class="list-inline list-unstyled">
																				<c:if test="${opt.product.loyaltyPointsPrice eq null}">
																					<gs:price product="${opt.product}" priceCss="${priceCss}" newPrice="${opt.bundleRuleChangedPrice}" />
																				</c:if>
																			</ul>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</c:if>
												</c:forEach>
											</c:if>
										</c:if>
										<!-- END: component #${st.index} id: ${comp.id} -->
									</c:forEach>
								</div>
								</div>
							</div>
							</c:if>
						</div>
					</div>
				</div>

				<%--TODO:SHOULD BE COMPONENT LIKE GUIDEDSELLINGTOTALSCOMPONENT.JSP--%>
				<c:if test="${not empty offer.orderPrices}">
					<div class="col-xs-12 col-sm-3">
						<div class="summary-column box">
							<h2 class="column-title">
								<%--IF COMPONENT USE ${headline}}--%>
								<spring:theme code="guidedselling.checkout.step4.name" />
								</h2>
							<div class="totals">
								<div>
									<div ng-repeat="orderPrice in offer.orderPrices" class="costs">
										<span class="title text-pink">
											<i class="fa pull-left fa-2x" ng-class="{'fa-shopping-cart':orderPrice.billingTime.code=='paynow','fa-file-text-o':orderPrice.billingTime.code=='onfirstbill','fa-refresh':orderPrice.billingTime.code=='monthly'}"></i>
											<span ng-bind="orderPrice.billingTime.name" class="pull-left text-description"></span>
											<span ng-bind="orderPrice.totalPrice.formattedValue" class="pull-right text-description"></span>
										</span>
										<table class="table table-condensed">
											<tr ng-repeat="totalsEntry in orderPrice.totalsEntries">
												<td class="text-left description" ng-bind="totalsEntry.productName"></td>
												<td class="text-right price" ng-bind="totalsEntry.totalPrice.formattedValue"></td>
											</tr>
										</table>
									</div>
								</div>

								<ul>
									<c:forEach var="msg" items="${offer.messages}">
										<li><span class="${msg.severity}">${msg.content}</span></li>
									</c:forEach>
								</ul>
								<div class="offerComponentsHidden"
									ng-class="{offerComponentsHidden: !isLoadFinished(), offerComponentsShown: isLoadFinished()}">
									<div ng-repeat="msg in offer.messages"
										class="alert alert-danger {{msg.level}}">
										<span ng-bind="msg.content" ng-class="{{msg.severity}}"></span>
									</div>
								</div>

								<c:url var="placeOrderUrl" value="/guidedselling/order/new"></c:url>
								<form action="${placeOrderUrl}" method="post">
									<input type="hidden" name="bundleNo" value="${offer.bundleNo}" />
									<input type="hidden" name="rootBundleTemplateId"
										value="${offer.rootBundleTemplateId}" /> <input type="hidden"
										name="sourceProductCode" value="${offer.sourceProductCode}" />
									<input type="hidden" name="sourcePackageCode"
										value="${offer.sourcePackageCode}" />
									<button type="submit" ng-disabled="isThereAnyErrors()"
										class="btn btn-sptel-primary btn-block text-uppercase purchase ${!creditCheckOkay ? 'disabled' : ''}">
										<spring:theme
											code="guidedselling.guidedsellingtotalscomponent.${offer.changeOrder ? 'updateorder' : 'placeorder'}" />
									</button>
								</form>

							</div>
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</template:page>
