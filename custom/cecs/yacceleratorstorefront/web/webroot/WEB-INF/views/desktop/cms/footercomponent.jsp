<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<!-- CECS-100: Footer component rework - START -->
<div class="clearfix"></div>
<div class="copyright">
    ${notice}
</div>
<c:forEach items="${navigationNodes}" var="node">
    <c:if test="${node.visible}">
        <ul class="links">
            <c:forEach items="${node.links}" step="${component.wrapAfter}" varStatus="i">
                <c:forEach items="${node.links}" var="childlink" begin="${i.index}" end="${i.index + component.wrapAfter - 1}">
                    <cms:component component="${childlink}" evaluateRestriction="true" element="li" />
                </c:forEach>
            </c:forEach>
        </ul>
    </c:if>
</c:forEach>
<!-- CECS-100: Footer component rework - END -->
<!--<div class="copyright">${notice}</div>-->
