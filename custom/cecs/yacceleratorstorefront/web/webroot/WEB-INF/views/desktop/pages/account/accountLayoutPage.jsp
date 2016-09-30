
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>

<template:page pageTitle="${pageTitle}">
	<div id="globalMessages">
		<common:globalMessages/>
	</div>
    <div class="row account">
    	<div class="col-xs-12 col-sm-9">
            <div class="row">
                <div class="col-xs-12">
					<cms:pageSlot position="BodyContent" var="feature">
						<cms:component component="${feature}"/>
					</cms:pageSlot>
                </div>
            </div>
    	</div>
        <div class="col-xs-12 col-sm-3 ">
        	<div class="row smaller-padding">
				<cms:pageSlot position="SideContent" var="feature">
						<cms:component component="${feature}"/>
				</cms:pageSlot>
			</div>
        </div>
    </div>

</template:page>
