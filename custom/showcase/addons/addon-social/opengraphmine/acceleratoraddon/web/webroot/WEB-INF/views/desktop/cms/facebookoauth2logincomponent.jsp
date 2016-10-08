<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty facebookUser}">
	<a href="#" class="facebook-btn fb-size-${size} facebook-connect-btn" data-scope="${scope}">
		<span class="fb-icon"></span>
		<span class="fb-text">
			<c:if test="${empty labelLogin}">
				Connect
			</c:if>
			<c:if test="${not empty labelLogin}">
				${labelLogin}
			</c:if>
		</span>
	</a>
</c:if>

<c:if test="${not empty facebookUser}">
	<img class="facebook-user-image fb-size-${size}" src="${facebookUserImage}"
		title="${facebookUser}">
	<a href="#" class="facebook-btn fb-size-${size} facebook-disconnect-btn" data-scope="${scope}">
		<span class="fb-icon"></span>
		<span class="fb-text">
			<c:if test="${empty labelLogout}">
				Disconnect
			</c:if>
			<c:if test="${not empty labelLogout}">
				${labelLogout}
			</c:if>
		</span>
	</a>
</c:if>