<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="cmsPageRequestContextData" required="true" type="de.hybris.platform.acceleratorcms.data.CmsPageRequestContextData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${cmsPageRequestContextData.liveEdit}">
	<c:forEach items="${addOnLiveEditJavaScriptPaths}" var="addOnJavaScript">
    	<script type="text/javascript" src="${addOnJavaScript}"></script>
	</c:forEach>
</c:if>

