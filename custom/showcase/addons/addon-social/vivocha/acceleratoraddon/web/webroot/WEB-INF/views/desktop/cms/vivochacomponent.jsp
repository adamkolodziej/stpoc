<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${cmsPageRequestContextData.liveEdit}">
	<div id="vivochaComponentLiveEditMarker">
		<spring:theme code="vivocha.liveEditMarkerLabel"/>
	</div>
</c:if>

<script type="text/javascript">
$(document).ready(function() {
	<c:choose>
		<c:when test="${accountLink != null}">
			var url = '//${accountLink}/a/${accountName}/api/vivocha.js';
		</c:when>
		<c:otherwise>
			var url = '//hybris.vivocha.com/a/${accountName}/api/vivocha.js';
		</c:otherwise>
	</c:choose>

	<%--
		There is also jQuery.getScript() method but it does prevent cacheing requests what
		results in many same request on single page. 
	--%>
	$.ajax({
		url: url,
		dataType: "script",
		cache: false,
		success: onVivochaJsLoaded
	});
});

function onVivochaJsLoaded() {
	vivocha.ready(function() {
		var hybrisData = [
	   		{
	   			name: "userid",
	   			desc: "User ID",
	   			value: "${userid}",
	   			visible: true,
	   			type: "text"
	   		},
	   		{
	   			name: "userName",
	   			desc: "User name",
	   			value: "${userName}",
	   			visible: true,
	   			type: "text"
	   		},
	   		{
	   			name: "cartid",
	   			desc: "Cart ID",
	   			value: "${cartid}",
	   			visible: true,
	   			type: "text"
	   		}
	   	];
		
		vivocha.api.setNickname("${userName}");
		vivocha.api.setData({
			name: "hybrisData",
			desc: "Customer informations",
			data: hybrisData
		});
	});
}
</script>
