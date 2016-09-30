<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<c:if test="${ fn:length(seasonsColection) >= 1 }">
    <div class="col-xs-12 col-sm-2">
        <select name="" id="" class="season-selector">
            <c:forEach items="${seasonsColection}" var="season">
                <option value="${season.code}"
                        ${season.code eq currentSeason.code ? 'selected' : ''} >
                    ${ season.name }
                </option>
            </c:forEach>
        </select>
    </div>
</c:if>

<div class="col-xs-12">
    <ul class="episodes-list list-unstyled">
        <jsp:include page="../fragments/tvseasonlistcomponentitems.jsp" />
    </ul>
</div>

<div class="col-xs-12 col-sm-3">
    <a href="#" class="btn btn-tricast-primary text-uppercase"><spring:theme code="text.TVSeasonListComponent.showAllEpisodes" /></a>
</div>
