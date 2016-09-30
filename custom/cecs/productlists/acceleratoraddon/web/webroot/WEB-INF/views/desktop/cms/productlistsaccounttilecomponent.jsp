<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="listsUrl" value="/productlists/list"/>

<div class="col-xs-6">
    <div class="account-tile <c:if test="${cmsPage.uid != 'account'}">tile-secondary</c:if>">
        <a href="${listsUrl}" class="account-content">
            <div>
                <span>
                    <span class="value wishlists">
                        <div class="number">
                            ${numberOfLists}
                        </div>
                    </span>
                    <span class="title"><spring:theme code="productlists.wishlists"/></span>
                </span>
            </div>
        </a>
    </div>
</div>
