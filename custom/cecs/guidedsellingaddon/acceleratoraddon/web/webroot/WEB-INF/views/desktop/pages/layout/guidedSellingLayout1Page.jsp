<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="gs" tagdir="/WEB-INF/tags/addons/guidedsellingaddon/desktop/guidedselling" %>

<template:page pageTitle="${pageTitle}">
    <cms:pageSlot position="Section1" var="feature">
        <cms:component component="${feature}" element="div" class=" section1 cms_disp-img_slot"/>
    </cms:pageSlot>

    <div id="globalMessages">
        <common:globalMessages/>
    </div>
    
    <div id="checkoutFunnel">
        <gs:guidedsellingCheckoutProgressBar/>
    </div>  

    <div class="row text-dark configure-package">
        <div ng-app="guidedselling">
            <div ng-controller="GuidedSellingController">
                <cms:pageSlot position="Section2A" var="feature" element="div" class="col-xs-12 col-sm-9 configure-column">
                    <cms:component component="${feature}"/>
                </cms:pageSlot>

                <div class="col-xs-12 col-sm-3">
                    <div class="summary-column box">
                        <cms:pageSlot position="Section2B" var="feature">
                            <cms:component component="${feature}"/>
                        </cms:pageSlot>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <cms:pageSlot position="Section3" var="feature" element="div" class="section3 cms_disp-img_slot">
        <cms:component component="${feature}"/>
    </cms:pageSlot>
</template:page>
