<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>

<div class="accountContentPane addressbook-container">
    <h2 class="headline">
        <spring:theme code="text.account.addressBook" text="Address Book"/>
    </h2>
    <div>
        <spring:theme code="text.account.addressBook.manageYourAddresses" text="Manage your address book"/>
    </div>

    <c:choose>
        <c:when test="${not empty addressData}">
            <div class="row">
                <c:forEach items="${addressData}" var="address">
                    <div class="addressItem panel-item">
                        <div class=" panel panel-default <c:if test="${address.defaultAddress}">panel-primary</c:if> ">
                            <div class="panel-heading">
                                <h3 class="panel-title">
                                    <c:if test="${address.defaultAddress}">
                                        <ycommerce:testId code="addressBook_isDefault_label">
                                            <span class="is-default-address"><spring:theme code="text.default" text="Default"/></span>
                                        </ycommerce:testId>
                                    </c:if>
                                </h3>
                            </div>
                            <div class="panel-body">
                                <ycommerce:testId code="addressBook_address_label">
                                    <dl class="dl-horizontal data-list">
                                        <dt><spring:theme code="profile.title" text="Title"/></dt>
                                        <dd>${fn:escapeXml(address.title)}&nbsp;${fn:escapeXml(address.firstName)}&nbsp;${fn:escapeXml(address.lastName)}</dd>
                                        <dt><spring:theme code="address.line1" text=""/></dt>
                                        <dd>${fn:escapeXml(address.line1)}</dd>
                                        <dt><spring:theme code="address.line2" text=""/></dt>
                                        <dd>${fn:escapeXml(address.line2)}</dd>
                                        <dt><spring:theme code="address.townCity" text=""/></dt>
                                        <dd>${fn:escapeXml(address.town)}</dd>
                                        <dt><spring:theme code="address.region" text=""/></dt>
                                        <dd>${fn:escapeXml(address.region.name)}</dd>
                                        <dt><spring:theme code="address.postalcode" text=""/></dt>
                                        <dd>${fn:escapeXml(address.postalCode)}</dd>
                                        <dt><spring:theme code="address.country" text=""/></dt>
                                        <dd>${fn:escapeXml(address.country.name)}</dd>
                                    </dl>
                                </ycommerce:testId>
                            </div>
                            <div class="panel-footer">
                                <div class="btn-group" role="group" aria-label="...">
                                    <ycommerce:testId code="addressBook_addressOptions_label">
                                        <c:if test="${not address.defaultAddress}">
                                            <ycommerce:testId code="addressBook_isDefault_button">
                                                <a class="btn btn-primary" href="set-default-address/${address.id}">
                                                    <spring:theme code="text.setDefault" text="Set as default"/>
                                                </a>
                                            </ycommerce:testId>
                                        </c:if>
                                        <ycommerce:testId code="addressBook_editAddress_button">
                                            <a class="btn btn-info" href="edit-address/${address.id}">
                                                <spring:theme code="text.edit" text="Edit"/>
                                            </a>
                                        </ycommerce:testId>
                                        <ycommerce:testId code="addressBook_removeAddress_button">
                                            <a class="btn btn-danger removeAddressButton" data-address-id="${address.id}"/>
                                                <spring:theme code="text.remove" text="Remove"/>
                                            </a>
                                        </ycommerce:testId>
                                    </ycommerce:testId>
                                </div>


                            </div>
                        </div>
                        <div style="display:none">
                            <div id="popup_confirm_address_removal_${address.id}">
                                <div class="panel-item">
                                    <dl class="dl-horizontal address-data">
                                        <dt><spring:theme code="profile.title" text="Title"/></dt>
                                        <dd>${fn:escapeXml(address.title)}&nbsp;${fn:escapeXml(address.firstName)}&nbsp;${fn:escapeXml(address.lastName)}</dd>
                                        <dt><spring:theme code="address.line1" text=""/></dt>
                                        <dd>${fn:escapeXml(address.line1)}</dd>
                                        <dt><spring:theme code="address.line2" text=""/></dt>
                                        <dd>${fn:escapeXml(address.line2)}</dd>
                                        <dt><spring:theme code="address.townCity" text=""/></dt>
                                        <dd>${fn:escapeXml(address.town)}</dd>
                                        <dt><spring:theme code="address.line1" text=""/></dt>
                                        <dd>${fn:escapeXml(address.region.name)}</dd>
                                        <dt><spring:theme code="address.postalcode" text=""/></dt>
                                        <dd>${fn:escapeXml(address.postalCode)}</dd>
                                        <dt><spring:theme code="address.country" text=""/></dt>
                                        <dd>${fn:escapeXml(address.country.name)}</dd>
                                    </dl>
                                    <spring:theme code="text.adress.remove.confirmation" text="Are you sure you would like to delete this address?"/>
                                    <div class="btn-group" role="group" aria-label="...">
                                        <a class="btn btn-danger removeAddressButton" data-address-id="${address.id}" href="remove-address/${address.id}">
                                            <spring:theme code="text.yes" text="Yes"/>
                                        </a>
                                        <a class="btn btn-success closeColorBox" data-address-id="${address.id}">
                                            <spring:theme code="text.no" text="No"/>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <h3>
                <span class="label label-warning  emptyMessage display-block">
                    <spring:theme code="text.account.addressBook.noSavedAddresses"/>
                </span>
            </h3>
        </c:otherwise>
    </c:choose>

    <ycommerce:testId code="addressBook_addNewAddress_button">
        <a href="add-address" class="btn btn-primary">
            <spring:theme code="text.account.addressBook.addAddress" text="Add new address"/>
        </a>
    </ycommerce:testId>

</div>