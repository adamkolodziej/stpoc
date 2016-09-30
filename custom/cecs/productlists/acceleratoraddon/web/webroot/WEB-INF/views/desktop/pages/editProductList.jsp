<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<template:page pageTitle="${pageTitle}">
	<div id="globalMessages">
		<common:globalMessages />
	</div>

	<div class="span-24">

		<cms:pageSlot position="TopContent" var="feature" element="div"
			class="span-24 wide-content-slot cms_disp-img_slot">
			<cms:component component="${feature}" />
		</cms:pageSlot>

		<!-- LS009 - EDIT PRODUCT LIST DETAILS -->
		<div class="span-18">
			<div class="item_container_holder">
				<div class="title_holder">
					<h2>Edit Product List Details</h2>
				</div>
				<div class="item_container">

					<form:form modelAttribute="editProductListForm" method="post">
						<div class="form_field-elements">
							<dl>
								<dt>Product List ID</dt>
								<dd>${editProductListForm.guid}</dd>
							</dl>
							<div class="form_field-label">
								<label class="" for="#">Product List Name <span
									class="mandatory"> <img width="5" height="6"
										alt="Required" title="Required"
										src="/yacceleratorstorefront/_ui/desktop/common/images/mandatory.gif">
								</span> <span class="skip"></span>
								</label>
							</div>
							<div class="form_field-input">
								<form:input id="#" path="name" class="text" />
							</div>
							<div class="form-field-button">
								<button type="submit" class="form">Update Details</button>
								<a class="form" type="button">cancel</a>
							</div>
						</div>
						<!--/form_field-elements-->
					</form:form>
					<p class="required">Fields marked * are required</p>
				</div>
				<!--/item_container-->
			</div>
			<!--/item_container_holder-->
		</div>
		<!--/span-18-->
		<!-- /LS009 - EDIT PRODUCT LIST DETAILS -->
		
	</div>

	<cms:pageSlot position="SideContent" var="feature" element="div"
		class="span-24 narrow-content-slot cms_disp-img_slot">
		<cms:component component="${feature}" />
	</cms:pageSlot>
		
</template:page>