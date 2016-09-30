<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="delivery-details box">
    <c:if test="${!downloadOnly && !isEditMode}">
        <div class="guidedselling-delivery-address">
            <div class="guidedselling-cart-delivery-address">

                <div ng-if="customerDeliveryInfo.allDeliveryAddresses.length > 0">
					<div class="title"><spring:theme code="guidedselling.delivery.info" text="Delivery info" /></div>
                    <select
                            ng-options="deliveryMode.code as deliveryMode.name for deliveryMode in customerDeliveryInfo.allDeliveryModes"
                            ng-model="customerDeliveryInfo.cartDeliveryModeCode"
                            ng-change="selectDeliveryMode()">
                    </select>

                    <div>
                        <span ng-bind="customerDeliveryInfo.deliveryAddress.title"></span>
                        <span ng-bind="customerDeliveryInfo.deliveryAddress.firstName"></span>
                        <span ng-bind="customerDeliveryInfo.deliveryAddress.lastName"></span>
                    </div>

                    <div>
                    	<span ng-bind="customerDeliveryInfo.deliveryAddress.line1"></span>
                    	<span ng-bind="customerDeliveryInfo.deliveryAddress.line2"></span>
                    </div>
                    <div ng-bind="customerDeliveryInfo.deliveryAddress.town"></div>
                    <div ng-bind="customerDeliveryInfo.deliveryAddress.country.name"></div>
                    <div ng-bind="customerDeliveryInfo.deliveryAddress.postalCode"></div>
                    <div ng-bind="customerDeliveryInfo.deliveryAddress.region.name"></div>
                </div>

                <div ng-if="customerDeliveryInfo.allDeliveryAddresses.length == 0">
                    <span class="title"><spring:theme code="guidedselling.delivery.info.missing"/></span>
                </div>

            </div>
            <button type="submit" ng-click="showAddDeliveryAddress()"
                    class="btn btn-primary btn-half-block btn-lg text-uppercase purchase">
                <spring:theme code="guidedselling.payment.info.add"
                              text="Add" />
            </button>
            <button type="submit" ng-click="showSelectDeliveryAddress()"
                    class="btn btn-primary btn-half-block btn-lg text-uppercase purchase"
                    ng-if="customerDeliveryInfo.allDeliveryAddresses.length > 0">
                <spring:theme code="guidedselling.payment.info.select"
                              text="Select" />
            </button>
        </div>
        <div class="guidedselling-add-delivery-address"
             style="display: none;">
            <form name="addDeliveryAddress"
                  class="addDeliveryAddress-form" novalidate>
											<span class="title"><spring:theme
                                                    code="guidedselling.delivery.newDeliveryAddress"
                                                    text="New delivery address" /></span><br />
                <spring:theme code="address.country" text="Country" />
                <br />
                <select
                        ng-options="country.isocode as country.name for country in customerDeliveryInfo.countries"
                        ng-model="newDeliveryAddress.countryIso" ng-change="selectRegions()" required></select><br />
                <spring:theme code="address.title" text="Title" />
                <br />
                <select
                        ng-options="title.code as title.name for title in customerDeliveryInfo.titles"
                        ng-model="newDeliveryAddress.titleCode" required></select><br />
                <spring:theme code="profile.firstName" text="First Name:" />
                <br />
                <input type="text" ng-model="newDeliveryAddress.firstName"
                       required /><br />
                <spring:theme code="profile.lastName" text="Last Name:" />
                <br />
                <input type="text" ng-model="newDeliveryAddress.lastName"
                       required /><br />
                <spring:theme code="address.line2" text="House No:" />
                <br />
                <input type="text" ng-model="newDeliveryAddress.line2"
                       required /><br />
                <spring:theme code="address.line1" text="Street Name:" />
                <br />
                <input type="text" ng-model="newDeliveryAddress.line1"
                       required /><br />
                <spring:theme code="address.townCity" text="City:" />
                <br />
                <input type="text" ng-model="newDeliveryAddress.townCity"
                       required /><br />

                <spring:theme code="address.selectState" text="State / Province:" />
                <br />
                <select ng-options="region.isocode as region.name for region in regions"
                        ng-model="newDeliveryAddress.regionIso" required></select><br />


                <spring:theme code="address.postalcode" text="Postal Code:" />
                <br />
                <input type="text" ng-model="newDeliveryAddress.postcode"
                       required /><br />
                <br />
                <div class="alert alert-danger deliveryAddress-error"
                     style="display: none;"></div>
                <button type="submit"
                        ng-click="addNewDeliveryAddress()"
                        class="btn btn-primary btn-half-block btn-lg text-uppercase purchase">
                    <spring:theme code="guidedselling.payment.info.add"
                                  text="Add" />
                </button>
                <button type="submit" ng-click="hideAddDeliveryAddress()"
                        class="btn btn-primary btn-half-block btn-lg text-uppercase purchase">
                    <spring:theme code="guidedselling.payment.info.cancel"
                                  text="Cancel" />
                </button>
            </form>
        </div>
        <div class="guidedselling-select-delivery-address"
             style="display: none;">
            <div
                    class="costs guidedselling-cart-delivery-address checkoutInfo-selection"
                    ng-repeat="deliveryAddress in customerDeliveryInfo.allDeliveryAddresses"
                    ng-click="selectDeliveryAddress(deliveryAddress.id)">
                <span ng-bind="deliveryAddress.title"></span>&nbsp <span
                    ng-bind="deliveryAddress.firstName"></span>&nbsp <span
                    ng-bind="deliveryAddress.lastName"></span>&nbsp-&nbsp <span
                    ng-bind="deliveryAddress.line1"></span><br />
                <span ng-bind="deliveryAddress.town"></span>&nbsp <span
                    ng-bind="deliveryAddress.country.name"></span> <br />
            </div>
            <button type="submit" ng-click="hideSelectDeliveryAddress()"
                    class="btn btn-primary btn-half-block btn-lg text-uppercase purchase">
                <spring:theme code="guidedselling.payment.info.cancel"
                              text="Cancel" />
            </button>
        </div>
    </c:if>
    <c:if test="${downloadOnly}">
        <div class="guidedselling-delivery-address">
										<span class="title"><spring:theme
                                                code="guidedselling.delivery.info" text="Delivery info" /></span><br />
            <h4>
                <spring:theme code="guidedselling.delivery.downloadonly"
                              text="All cart entries are download only." />
            </h4>
            <h4>
                <spring:theme code="guidedselling.delivery.notnecessary"
                              text="Delivery info not necessary." />
            </h4>
        </div>
    </c:if>
    <c:if test="${isEditMode}">
        <div class="guidedselling-delivery-address">
										<span class="title"><spring:theme
                                                code="guidedselling.delivery.info" text="Delivery info" /></span><br />
            <h4>
                <spring:theme code="guidedselling.delivery.editmode"
                              text="Delivery info can't be changed when editing existing order." />
            </h4>
        </div>
    </c:if>
</div>