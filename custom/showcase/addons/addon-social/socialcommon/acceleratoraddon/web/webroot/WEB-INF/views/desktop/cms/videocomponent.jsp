<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="socialcommon" tagdir="/WEB-INF/tags/addons/socialcommon/desktop/socialcommon"%>

<div class="video-component">
	<h2>${title}</h2>
	<c:if test="${not empty videoMediaData}">
		<socialcommon:videoPlayer video="${videoMediaData}" />
	</c:if>
	<c:if test="${empty videoMediaData}">
		<spring:theme code="text.video.playback.unavailable" text="Video playback is not available now..." />
	</c:if>
</div>
