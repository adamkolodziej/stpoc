<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ attribute name="ngProduct" required="true" type="java.lang.String" %>
<%@ attribute name="format" required="true" type="java.lang.String" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- CECS-92: Show product images on guidedselling - START -->
<div ng-repeat="image in ${ngProduct}.images"
	 ng-if="image.imageType == 'PRIMARY' && image.format == '${format}'"
	 class="entitlementImage">
	<span>
		<img ng-src="{{image.url}}" class="thumb img-responsive">
	</span>
</div>