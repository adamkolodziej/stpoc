<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="facetData" required="true" type="de.hybris.platform.commerceservices.search.facetdata.FacetData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>

<c:if test="${not empty facetData.values}">
	 <div class="panel">
		<div class="panel-heading">
            <h4 class="panel-title">
                <spring:theme code="text.hideFacet" var="hideFacetText"/>
                <spring:theme code="text.showFacet" var="showFacetText"/>
                <a data-toggle="collapse" data-parent="#accordion" href="#${facetData.code}" aria-expanded="false" aria-controls="${facetData.code}">
                    <spring:theme code="search.nav.facetTitle" arguments="${facetData.name}"/> <i class="fa pull-right"></i>
                </a>
            </h4>
		</div>
		<ycommerce:testId code="facetNav_facet${facetData.name}_links">
            <div id="${facetData.code}" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="${facetData.code}">
                <div class="panel-body facetValues">
                    <c:if test="${not empty facetData.topValues}">
                        <div class="topFacetValues">
                            <c:if test="${facetData.multiSelect}">
                                <c:forEach items="${facetData.topValues}" var="facetValue">
                                    <form class="form-horizontal" action="#" method="get">
                                        <div class="form-group">
                                            <input type="hidden" name="q" value="${facetValue.query.query.value}"/>
                                            <input type="hidden" name="text" value="${searchPageData.freeTextSearch}"/>
                                            <div class="col-xs-12">
                                                <div class="checkbox">
                                                    <label>
                                                        <input type="checkbox" ${facetValue.selected ? 'checked="checked"' : ''} onchange="$(this).closest('form').submit()"/>                                                       
                                                        ${facetValue.name}&nbsp;
                                                        <span class="facetValueCount"><spring:theme code="search.nav.facetValueCount" arguments="${facetValue.count}"/></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </c:forEach>
                                <span class="more">
                                    <a href="#" class="moreFacetValues" ><spring:theme code="search.nav.facetShowMore_${facetData.code}" /></a>
                                </span>
                            </c:if>
                            <c:if test="${not facetData.multiSelect}">
                                <ul class="list-unstyled">
                                    <c:forEach items="${facetData.topValues}" var="facetValue">
                                        <c:url value="${facetValue.query.url}" var="facetValueQueryUrl"/>
                                        <li>
                                            <a href="${facetValueQueryUrl}&amp;text=${searchPageData.freeTextSearch}">
                                                ${facetValue.name}&nbsp;
                                                <span class="facetValueCount"><spring:theme code="search.nav.facetValueCount" arguments="${facetValue.count}"/></span>
                                            </a>

                                        <li>
                                    </c:forEach>
                                </ul>
                                <span class="more">
                                    <a href="#" class="moreFacetValues" ><spring:theme code="search.nav.facetShowMore_${facetData.code}" /></a>
                                </span>
                            </c:if>
                        </div>
                    </c:if>
                    <div class="allFacetValues" style="${not empty facetData.topValues ? 'display:none' : ''}">
                            <c:if test="${facetData.multiSelect}">
                                <c:forEach items="${facetData.values}" var="facetValue">
                                    <form class="form-horizontal" action="#" method="get">
                                        <div class="form-group">
                                            <input type="hidden" name="q" value="${facetValue.query.query.value}"/>
                                            <input type="hidden" name="text" value="${searchPageData.freeTextSearch}"/>
                                            <div class="col-xs-12">
                                                <div class="checkbox">
                                                    <label>
                                                        <input type="checkbox" ${facetValue.selected ? 'checked="checked"' : ''} onchange="$(this).closest('form').submit()"/>
                                                        <c:choose>
                                                        	<c:when test="${facetData.code eq ('Rates') }">
                                                        		<component:rates maxDisplayedRatesAmount="5" filledRatesAmount="${facetValue.name}" classForFullRate="fa-star" classForEmptyRate="fa-star-o" classForHalfRate="fa-star-half-o"/>                                                     		
                                                        	</c:when>
                                                        	<c:otherwise>
                                                        		${facetValue.name}&nbsp;
                                                        	</c:otherwise>
                                                        </c:choose>
                                                        <span class="facetValueCount"><spring:theme code="search.nav.facetValueCount" arguments="${facetValue.count}"/></span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </c:forEach>
                            </c:if>
                            <c:if test="${not facetData.multiSelect}">
                                <ul class="list-unstyled">
                                    <c:forEach items="${facetData.values}" var="facetValue">
                                        <c:url value="${facetValue.query.url}" var="facetValueQueryUrl"/>
                                        <li>
                                            <a href="${facetValueQueryUrl}">
                                                ${facetValue.name}&nbsp;
                                                <span class="facetValueCount"><spring:theme code="search.nav.facetValueCount" arguments="${facetValue.count}"/></span>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        <c:if test="${not empty facetData.topValues}">
                            <span class="more">
                                <a href="#" class="lessFacetValues"><spring:theme code="search.nav.facetShowLess_${facetData.code}" /></a>
                            </span>
                        </c:if>
                    </div>
				</div>
			</div>
		</ycommerce:testId>
	</div>
</c:if>
