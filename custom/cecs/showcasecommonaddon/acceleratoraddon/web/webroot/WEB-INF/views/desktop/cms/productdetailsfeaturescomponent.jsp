<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-xs-12 col-sm-4 margin-vertical">
    <div class="${cssClass} text-dark product-features">
    <c:if test="${not empty product.classifications}">
        <h3>${headline}</h3>
        <c:forEach items="${product.classifications}" var="classification">
            <dl class="dl-horizontal">
                <c:forEach items="${classification.features}" var="feature">
                    <dt>${feature.name}</dt>
                    <dd>
                        <c:forEach items="${feature.featureValues}" var="value" varStatus="status">
                            ${value.value}
                            <c:choose>
                                <c:when test="${feature.range}">
                                    ${not status.last ? '-' : feature.featureUnit.symbol}
                                </c:when>
                                <c:otherwise>
                                    ${feature.featureUnit.symbol}
                                    ${not status.last ? '<br/>' : ''}
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </dd>
                </c:forEach>
            </dl>
        </c:forEach>
    </c:if>
    </div>
</div>
