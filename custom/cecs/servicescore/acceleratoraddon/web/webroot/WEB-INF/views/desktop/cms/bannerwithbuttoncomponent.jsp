<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/desktop/action" %>

<c:url value="${not empty page ? page.label : urlLink}" var="encodedUrl" />

<div class="row">
    <div class="col-xs-12 ${cssClass}">
    <c:choose>
        <c:when test="${empty encodedUrl || encodedUrl eq '#'}">
            <img class="img-responsive" src="${media.url}" alt="${media.altText}"/>
            <div class="banner-description description-center">
                ${headline}
            </div>
        </c:when>
        <c:otherwise>
            <a href="${encodedUrl}" class="">
                <img class="img-responsive" src="${media.url}" alt="${media.altText}"/>
                <c:if test="${not empty headline}">
                    <div class="banner-info">
                        <button class="btn btn-sptel-primary">${btnText}</button>
                        <div class="banner-description">
                                ${headline}
                        </div>
                    </div>
                </c:if>
            </a>
        </c:otherwise>
    </c:choose>
    </div>
</div>