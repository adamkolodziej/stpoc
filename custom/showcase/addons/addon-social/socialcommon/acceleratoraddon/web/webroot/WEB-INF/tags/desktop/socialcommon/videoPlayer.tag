<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="video" required="true" type="com.hybris.social.common.data.VideoMediaData" %>
<%@ attribute name="isAutoplay" required="false" type="java.lang.Boolean" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty video && not empty video.url}">
	<video class="video-js vjs-default-skin" controls preload="auto" data-autoplay="${isAutoplay}" width="100%" ${video.startingPictureUrl ? 'poster="${video.startingPictureUrl}"' : '' }>
		<source src="${video.url}" type='${video.mediaType}' ></source>
	</video>
</c:if>
