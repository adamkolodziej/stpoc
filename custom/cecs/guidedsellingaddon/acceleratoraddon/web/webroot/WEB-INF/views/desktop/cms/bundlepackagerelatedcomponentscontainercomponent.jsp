<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<div role="tabpanel" class="package-infos">
    <!-- Nav tabs -->
    <div class="nav-tabs-container">
        <ul class="nav nav-tabs" role="tablist">
            <c:forEach var="component" items="${relatedComponents}" varStatus="componentLoop">
                <c:set var="componentLoopIndex" value="${componentLoop.index}" />
                <li role="presentation" <c:if test="${componentLoopIndex == 0}">class="active"</c:if>>
                    <a href="#package-${component.uid}-${componentLoopIndex}" aria-controls="package-${component.uid}-${componentLoopIndex}" role="tab" data-toggle="tab">${component.title}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <!-- Tab panes -->
    <div class="tab-content">
        <c:forEach var="component" items="${relatedComponents}" varStatus="componentLoop">
            <c:set var="componentLoopIndex" value="${componentLoop.index}" />

            <c:set var="classActive" value="" />
            <c:if test="${componentLoopIndex == 0}">
                <c:set var="classActive" value=" active" />
            </c:if>

            <div role="tabpanel" class="tab-pane${classActive}" id="package-${component.uid}-${componentLoopIndex}">
                <cms:component component="${component}"/>
            </div>
        </c:forEach>
    </div>
</div>
