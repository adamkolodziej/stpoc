<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<template:page pageTitle="${pageTitle}">
	<div id="globalMessages">
		<common:globalMessages />
	</div>

	<div class="span-24">

		<cms:pageSlot position="TopContent" var="feature" element="div"
			class="span-24 wide-content-slot cms_disp-img_slot">
			<cms:component component="${feature}" />
		</cms:pageSlot>

		<!-- LS012 - FIND A PRODUCT LIST -->
		<div class="span-18">
			<div class="item_container_holder">
				<div class="title_holder">
					<h2>Find a Product List</h2>
				</div>

				<div class="item_container">
					<form id="#">
						<div class="form_field-elements">
							<div class="form_field-label">
								<label class="" for="#">Product List Number <span
									class="mandatory"> <img width="5" height="6"
										alt="Required" title="Required"
										src="/yacceleratorstorefront/_ui/desktop/common/images/mandatory.gif">
								</span> <span class="skip"></span>
								</label>
							</div>
							<div class="form_field-input">
								<input id="#" name="#" class="text" type="text" value="">
							</div>
							<div class="form_field-label">
								<label class="" for="password">Product List Password <span
									class="mandatory"> <img width="5" height="6"
										alt="Required" title="Required"
										src="/yacceleratorstorefront/_ui/desktop/common/images/mandatory.gif">
								</span> <span class="skip"></span>
								</label>
							</div>
							<div class="form_field-input">
								<input id="password" name="pwd" class="text password strength"
									type="password" value="">
								<div>
									<div class="pstrength-bar" id="password_bar"
										style="border: 1px solid white; font-size: 1px; height: 5px; width: 0px;"></div>
								</div>
								<div class="pstrength-info" id="password_text"></div>
								<div class="pstrength-minchar" id="password_minchar">Minimum
									length is 6 characters</div>
							</div>
							<div class="form_field-label">
								<label class="" for="register.checkPwd">Confirm Password
									<span class="mandatory"> <img width="5" height="6"
										alt="Required" title="Required"
										src="/yacceleratorstorefront/_ui/desktop/common/images/mandatory.gif">
								</span> <span class="skip"></span>
								</label>
							</div>
							<div class="form_field-input">
								<input id="#" name="#" class="text password" type="password"
									value="">
							</div>

							<div class="form_field-label">
								<label class="" for="address.townCity">Town/City <span
									class="mandatory"> <img width="5" height="6"
										alt="Required" title="Required"
										src="/yacceleratorstorefront/_ui/desktop/common/images/mandatory.gif">
								</span> <span class="skip"></span>
								</label>
							</div>
							<div class="form_field-input">
								<input id="address.townCity" name="townCity" class="text"
									type="text" value="">
							</div>
							<div class="form_field-label">
								<label class="" for="address.postcode">Post Code <span
									class="mandatory"> <img width="5" height="6"
										alt="Required" title="Required"
										src="/yacceleratorstorefront/_ui/desktop/common/images/mandatory.gif">
								</span> <span class="skip"></span>
								</label>
							</div>
							<div class="form_field-input">
								<input id="address.postcode" name="postcode" class="text"
									type="text" value="">
							</div>
							<div class="form-field-button">
								<button class="form">Display List</button>
							</div>
						</div>
					</form>
					<p class="required">Fields marked * are required</p>
					<p>If you cannot find the Product List you require, please try
						again or contact customer services on 0845 123 456</p>
				</div>
				<!--/item_container-->
			</div>
			<!--/item_container_holder-->
		</div>
		<!--/span-18-->
		<!-- /LS012 - FIND A PRODUCT LIST -->

	</div>
	
</template:page>