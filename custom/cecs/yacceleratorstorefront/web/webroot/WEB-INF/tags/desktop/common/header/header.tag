 <%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
            <%@ attribute name="hideHeaderLinks" required="false" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

        <header>
            <c:if test="${user.name eq 'Anonymous'}">
            <!-- Login section -->
            <div class="login-section">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12 text-right">
                            <c:url value="/login" var="login"/>
                            <a href="${login}" title="Please login" class="font-16">Please login</a>
                        </div>
                    </div>
                </div>
            </div>
            </c:if>

            <!-- Menu section -->
            <div class="menu-section">
                <div class="container">
                    <div class="row">
                        <!-- Logo -->
                        <div class="col-md-4">
                            <c:url value="/" var="home"/>
                            <a href="${home}" title="SP Telecom">
                                <img src="/yacceleratorstorefront/_ui/desktop/common/images/sptel-logo.png" alt="SP Telecom">
                            </a>
                        </div>

                        <!-- Links -->
                        <div class="col-md-8 text-right">
                            <ul>
                                <li><a href="#!" title="Connecting businesses">Connecting businesses</a></li>
                                <li><a href="#!" title="Coverage">Coverage</a></li>
                                <li><a href="#!" title="About">About</a></li>
                                <li><a href="#!" title="">Contact</a></li>
                                <li><a href="#!" title="Search">Search</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </header>