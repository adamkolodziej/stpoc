<!-- CECS-154: Content Top Links Component -->
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<div class="row grey hidden-xs">
    <div class="col-xs-12">
        <ul class="nav navbar-nav packages-categories">
			<c:forEach items="${component.links}" var="link">
	            <li>
					<cms:component component="${link}" evaluateRestriction="true" element="li" />
	            </li>
			</c:forEach>
        </ul>
    </div>
</div>
