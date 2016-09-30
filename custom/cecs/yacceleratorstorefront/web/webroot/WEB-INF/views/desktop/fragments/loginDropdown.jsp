<%@ page trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<!-- CECS-101: Login dropdown - START -->
<sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
	<li class="dropdown yamm-fw">
		<ycommerce:testId code="header_Login_link">
			<a href="#" data-toggle="dropdown" class="dropdown-toggle btn btn-sptel-primary"><spring:theme code="header.link.login"/><b class="caret"></b></a>
		</ycommerce:testId>
		<ul class="dropdown-menu">
			<li>
				<!-- Content container to add padding -->
				<div class="yamm-content">
					<div class="row">
						<div class="yCmsContentSlot col-xs-12 col-sm-push-1 col-sm-2 column-separator">
							<div class="userLogin dropdown-login">
								<h3><spring:theme code="login.signin"/></h3>
								<form:form action="${actionUrl}" method="post" commandName="loginForm">
									<div class="form_field-elements">
										<div class="control-group">
											<formElement:formInputBox idKey="j_username" labelKey="login.email" path="j_username" inputCSS="text" mandatory="true"/>
										</div>
										<div class="control-group">
											<formElement:formPasswordBox idKey="j_password" labelKey="login.password" path="j_password" inputCSS="text password" mandatory="true"/>
										</div>
										<div class="form_field_error-message">
											<a href="javascript:void(0)" data-url="<c:url value='/login/pw/request'/>" class="password-forgotten">
												<spring:theme code="login.link.forgottenPwd"/>
											</a>
										</div>
									</div>
									<div class="form-actions clearfix row vertical-both">
										<div class="col-xs-12 col-sm-10">
											<button type="submit" class="btn btn-sptel-primary btn-block"><spring:theme code="login.signin"/></button>
										</div>
									</div>
								</form:form>
							</div>
						</div>
						<div class="yCmsContentSlot col-xs-12 col-sm-push-1 col-sm-2 column-separator social-signin-slot">
							<div>
								<h3><spring:theme code="login.socialmedia"/></h3>
								<div class="row">
									<cms:pageSlot position="SocialSignInSlot" var="feature">
								        <cms:component component="${feature}"/>
								    </cms:pageSlot>
						    	</div>
						    </div>
						</div>
						<div class="yCmsContentSlot col-xs-12 col-sm-push-1 col-sm-5">
							<div class="userRegister">
								<h3><spring:theme code="login.newuser"/></h3>
								<div class="row">
									<div class="col-xs-12">
										<h5><spring:theme code="login.membershipbenefits"/></h5>
										<ul class="account-benefits">
											<spring:theme code="login.membershipbenefits.text"/>
										</ul>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12 col-sm-6">
										<c:url value="/login" var="loginUrl"/>
										<a href="${loginUrl}" class="btn btn-sptel-primary btn-block ">
											<spring:theme code="login.createaccount"/>
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</li>
		</ul>
	</li>
</sec:authorize>
<!-- CECS-101: Login dropdown - END -->
