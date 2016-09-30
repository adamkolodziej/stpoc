<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/addons/showcasecommonaddon/desktop/product" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>

<c:choose>
	<c:when test="${not empty productReferences}">
        <div class="${cssClass}">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <h3 class="text-uppercase text-center">${headline}</h3>
            </div>
            <c:forEach items="${productReferences}" var="reference">
                <c:url var="viewProductUrl" value="${reference.target.url}" />
                <div class="col-xs-6 col-sm-3 release">
                    <a href="${viewProductUrl}">
                        <product:responsiveProductPrimaryImage cssClass="img-responsive" product="${reference.target}" format="product" />
                    </a>
                    <h3 class="title">${reference.target.name}</h3>
                    <c:if test="${not empty reference.target.categories}">
                        <span class="subtitle">${reference.target.categories[0].name}</span>
                    </c:if>
                </div>
            </c:forEach>
        </div>
	</c:when>

	<c:otherwise>
		<component:emptyComponent/>
	</c:otherwise>
</c:choose>