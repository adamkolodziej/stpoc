<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageData" required="true" type="de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>


<c:if test="${not empty pageData.breadcrumbs}">
    <div class="panel-group collapse refinements" id="remove_refinements" role="tablist" aria-multiselectable="true">
        <div class="panel">
            <div class="panel-heading">
                <h4 class="panel-title text-uppercase">
                    <spring:theme code="text.hideFacet" var="hideFacetText"/>
                    <spring:theme code="text.showFacet" var="showFacetText"/>
                    <spring:theme code="search.nav.appliedFilters"/>
                </h4>
            </div>
            <div class="panel-body">
                <ul class="list-unstyled">
                    <c:forEach items="${pageData.breadcrumbs}" var="breadcrumb">
                        <li class="remove_item">
                            <c:url value="${breadcrumb.removeQuery.url}" var="removeQueryUrl"/>
                            <a class="text-danger" href="${removeQueryUrl}" >
                                <span class="remove_item_name">${breadcrumb.facetValueName}</span>
                                <i class="fa fa-remove"></i>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>

</c:if>
