<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="cmsPageRequestContextData" required="true" type="de.hybris.platform.acceleratorcms.data.CmsPageRequestContextData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${cmsPageRequestContextData.liveEdit}">

	<c:forEach items="${addOnLiveEditCommonCssPaths}" var="addOnCommonCss">
    	<link rel="stylesheet" type="text/css" media="all" href="${addOnCommonCss}" />
	</c:forEach>


	<c:forEach items="${addOnLiveEditThemeCssPaths}" var="addOnThemeCss">
    	<link rel="stylesheet" type="text/css" media="all" href="${addOnThemeCss}" />
	</c:forEach>

</c:if>
