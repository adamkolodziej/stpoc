<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/desktop/user" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>

<template:page pageTitle="${pageTitle}">

	<div id="globalMessages">
		<common:globalMessages/>
	</div>
    <div class="row">
        <div id="content">
            <div class="container">
                        <div class="row">
                            <div class="col-xs-12">
                                <p class="color-blue font-32 margin-top-30">
                                    Restricted area, please login to access system.
                                </p>

                                <p class="color-mid-gray font-16">
                                    Terms and conditions: Lorem ipsum dolor sit amet,
                                    consectetur adipiscing elit. Aenean euismod bibendum
                                    laoreet. Proin gravida dolor sit amet lacus accumsan
                                    et viverra justo commodo.
                                </p>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-4 col-md-offset-4">
                                <form class="margin-top-30 margin-bottom-30" action="j_spring_security_check" method="post" id="loginForm">
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">Username</label>
                                        <input type="text" class="form-control" id="j_username" name="j_username" placeholder="">
                                        <p class="margin-top-10 margin-bottom-30 color-light-gray">Usually your email address</p>
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputPassword1">Password</label>
                                        <input type="password" class="form-control" id="j_password" name="j_password" placeholder="">
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-6">
                                            <a href="#!" title="Forgotten password?" class="color-blue">
                                                Forgotten password?
                                            </a>
                                        </div>

                                        <div class="col-xs-6 text-right">
                                            <button id="login" type="submit" class="btn btn-primary">Login</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

    </div>

</template:page>
