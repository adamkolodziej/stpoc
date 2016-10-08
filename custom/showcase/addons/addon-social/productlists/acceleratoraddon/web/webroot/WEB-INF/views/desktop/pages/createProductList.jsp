<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
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

		<!-- LS004 - My Account New Product List -->
		<div class="span-12 add_new_prodlist">
			<div class="item_container_holder">
				<div class="title_holder">
					<h2>Add New Product List</h2>
				</div>
				<div class="item_container">
					<form:form modelAttribute="createForm" method="post">
						<div class="form_field-elements">
							<div class="form_field-label">
								<label class="" for="#">Enter Product List Name <span
									class="mandatory"> <img width="5" height="6"
										alt="Required" title="Required"
										src="/yacceleratorstorefront/_ui/desktop/common/images/mandatory.gif">
								</span> <span class="skip"></span>
								</label>
							</div>
							<div class="form_field-input">
								<form:input id="#" path="name" />
							</div>
							<c:forEach var="address" items="${createForm.addresses}"
								varStatus="status">
								<div class="item_container delivery_addresses_list">
									<h3>Product List Address</h3>
									<div class="existing_address">
										<ul>
											<li><c:out
													value="${fn:escapeXml(address.title)} ${fn:escapeXml(address.firstName)} ${fn:escapeXml(address.lastName)}" /></li>
											<li>${fn:escapeXml(address.line1)}</li>
											<li>${fn:escapeXml(address.line2)}</li>
											<li>${fn:escapeXml(address.town)}</li>
											<li>${fn:escapeXml(address.postalCode)}</li>
											<li>${fn:escapeXml(address.country.name)}</li>
										</ul>
										<!-- BA Edit button not present on wireframe but left in below incase you were reusing an existing control -->
										<!--<a href="/yacceleratorstorefront/en/checkout/multi/edit-delivery-address/?editAddressCode=8796256894999" class="right">Edit</a>-->
									</div>
									<div class="form_field-label add-address-left-label">
										<label class="add-address-left-label" for="#"> Use
											this address between these dates:<span class="skip"></span>
										</label>
									</div>
									<div class="form_field-input">
										<input id="#" name="#" class="add-address-left-input"
											type="checkbox" value="true"><input type="hidden"
											name="#" value="on">
									</div>
									<fieldset class="datePicker">
										<div class="datePicker_left_col_start">
											<div class="form_field-label">
												<label class="" for="#"> Start Date<span
													class="mandatory"> <img width="5" height="6"
														alt="Required" title="Required"
														src="/yacceleratorstorefront/_ui/desktop/common/images/mandatory.gif">
												</span> <span class="skip"></span>
												</label>
											</div>

											<div class="form_field-input">
												<input id="#" name="#" class="text" type="text" value="">
												<a href="#" class="calendar"></a>
											</div>
										</div>
										<div class="datePicker_left_col_end">
											<div class="form_field-label">
												<label class="" for="#"> End Date<span
													class="mandatory"> <img width="5" height="6"
														alt="Required" title="Required"
														src="/yacceleratorstorefront/_ui/desktop/common/images/mandatory.gif">
												</span> <span class="skip"></span>
												</label>
											</div>
											<div class="form_field-input">
												<input id="#" name="#" class="text" type="text" value="">
												<a href="#" class="calendar"></a>
											</div>
										</div>
									</fieldset>
								</div>
							</c:forEach>
							<div class="form-field-button">
								<button type="submit" class="form">Add</button>
								<a class="form" type="button">cancel</a>
							</div>
						</div>
					</form:form>
				</div>
				<!--/item_container-->
			</div>
			<!--/item_container_holder-->
		</div>
		<!--/span-12-->
		<!-- /LS004 - My Account New Product List -->
	</div>

	<cms:pageSlot position="SideContent" var="feature" element="div"
		class="span-24 narrow-content-slot cms_disp-img_slot">
		<cms:component component="${feature}" />
	</cms:pageSlot>

</template:page>