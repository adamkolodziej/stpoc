<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="${url}" var="pageUrl" />

<div class="col-xs-6">
    <div class="account-tile <c:if test="${cmsPage.uid != 'account'}">tile-secondary</c:if>">
        <a href="${pageUrl}" class="account-content">
            <div>
                <span>
                    <span class="value ${icon}"></span>
                    <span class="title">${title}</span>
                </span>
            </div>
        </a>
    </div>
</div>
