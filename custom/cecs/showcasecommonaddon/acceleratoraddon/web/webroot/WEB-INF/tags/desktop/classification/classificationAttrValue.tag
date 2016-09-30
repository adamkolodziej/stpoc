<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="attributeName" required="true" type="java.lang.String" %>
<%@ attribute name="showUnit" required="true" type="java.lang.Boolean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${(not empty product.classifications) && (not empty attributeName)}">
    <c:forEach items="${product.classifications}" var="classification">
        <c:forEach items="${classification.features}" var="feature">
            <c:if test="${feature.name eq attributeName}">
                <c:forEach items="${feature.featureValues}" var="value" varStatus="status">
                    ${value.value}
                    <c:if test="${showUnit}">
                    <c:choose>
                        <c:when test="${feature.range}">
                            ${not status.last ? '-' : feature.featureUnit.symbol}
                        </c:when>
                        <c:otherwise>
                            ${feature.featureUnit.symbol}
                            ${not status.last ? '<br/>' : ''}
                        </c:otherwise>
                    </c:choose>
                    </c:if>
                </c:forEach>
            </c:if>
        </c:forEach>
    </c:forEach>
</c:if>