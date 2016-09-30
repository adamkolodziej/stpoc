<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/addons/showcasecommonaddon/desktop/nav" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/addons/showcasecommonaddon/desktop/product" %>

<nav:pagination top="true" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}" numberPagesShown="${numberPagesShown}"/>

<div class="row">
    <div class="col-xs-12">
        <div class="row items-list">
            <c:forEach items="${searchPageData.results}" var="product" varStatus="status">
                <product:productGridMerchandiseItem product="${product}" />
            </c:forEach>
        </div>
    </div>
</div>

<nav:pagination top="false" supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}" numberPagesShown="${numberPagesShown}"/>
