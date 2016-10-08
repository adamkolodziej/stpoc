<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="suggestion" required="true" type="com.hybris.social.facebook.opengraphmine.facade.data.FacebookSuggestedProductData" %>

<div class="friend_store">
	<img src="${suggestion.bestCandidate.smallProfilePictureURL}"/>
	<div class="friend_store_modal">
		<div>
			<h1>${suggestion.bestCandidate.firstname}&nbsp;${suggestion.bestCandidate.lastname}</h1>
			<ul>
				<c:if test="${suggestion.likedByBestCandidate}">
					<li class="liked">Liked</li>
				</c:if>
				<c:if test="${suggestion.commentedByBestCandidate}">
					<li class="commented">Commented</li>
				</c:if>
				<c:if test="${suggestion.purchasedByBestCandidate}">
					<li class="purchased">Purchased</li>
				</c:if>
			</ul>						
		</div>									
	</div>
</div>