<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ attribute name="ngProduct" required="true" type="java.lang.String" %>
<%@ attribute name="format" required="true" type="java.lang.String" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="media">
	<div class="media-left media-middle">
		<ul class="list-unstyled">
			<li ng-repeat="image in ${ngProduct}.images"
				ng-if="image.imageType == 'PRIMARY' && image.format == '${format}'"
				class="entitlementImage">
				<img ng-src="{{image.url}}" class="media-object">
			</li>
		</ul>
	</div>
	<div class="media-body media-middle">
		<h4 class="media-heading">{{${ngProduct}.name}}</h4>
	</div>
</div>