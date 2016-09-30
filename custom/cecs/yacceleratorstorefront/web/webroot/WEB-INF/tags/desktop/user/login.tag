<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="actionNameKey" required="true" type="java.lang.String" %>
<%@ attribute name="action" required="true" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<script type="text/javascript" src="${commonResourcePath}/js/jquery-1.11.2.min.js"></script>
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
                        <form class="margin-top-30 margin-bottom-30" action="${action}" method="post" id="loginForm">
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

