<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">

	var com_seewhy_addon_global = 
	{
 		cyServiceCode:"${cyservicecode}",
 		cyTargetURL:"${cytargeturl}",
 		cyDomain:"${cydomain}",
 		cyUseURLEmail:"${cyuseurlemail}",
 		cyStaticEventData01:"${cystaticeventdata01}",
 		cyStaticEventData02:"${cystaticeventdata02}"		
	};
	
</script>


<c:if test="${cmsPageRequestContextData.liveEdit}">
    <div id="seewhyFooterComponent">
        <spring:theme code="seewhy.liveEditMarkerLabel"/>
    </div>
</c:if>