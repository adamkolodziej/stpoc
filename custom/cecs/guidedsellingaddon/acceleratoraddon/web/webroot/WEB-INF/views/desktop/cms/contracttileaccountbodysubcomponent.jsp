<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="${targetUrl}" var="url" />

<div class="col-xs-6">
    <div class="account-tile <c:if test="${cmsPage.uid != 'account'}">tile-secondary</c:if>">
        <a href="${url}" class="account-content">
            <div>
                <span>
                    <c:choose>
                        <c:when test="${noOfServices gt 0}">
                            <span class="value services">
                                <div class="number">
                                        ${noOfServices}
                                </div>
                            </span>
                        </c:when>
                        <c:otherwise>
                            <span class="message">
                                <div class="number">
                                        ${noContractMessage}
                                </div>
                            </span>
                        </c:otherwise>
                    </c:choose>
                    <span class="title">${title}</span>
                </span>
            </div>
        </a>
    </div>
</div>
