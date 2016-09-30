<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="banners-container ${cssClass} banners-${fn:length(banners)}">
    <ul class="banners-list">
        <c:forEach items="${banners}" var="banner" varStatus="status">
            <li class="banner">
                <c:url value="${not empty page ? page.label : banner.urlLink}" var="encodedUrl" />
                <c:choose>
                    <c:when test="${empty encodedUrl || encodedUrl eq '#'}">
                        <a href="#" class="">
                            <img class="img-responsive" src="${banner.media.url}" alt="${banner.media.altText}"/>
                            <div class="banner-description">
                                <h4 class="title">${banner.headline} <small>${banner.content}</small></h4>
                            </div>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="${encodedUrl}" class="">
                            <img class="img-responsive" src="${banner.media.url}" alt="${banner.media.altText}"/>
                            <div class="banner-description">
                                <h4 class="title">${banner.headline} <small>${banner.content}</small></h4>
                            </div>
                        </a>
                    </c:otherwise>
                </c:choose>
            </li>
        </c:forEach>
    </ul>
</div>