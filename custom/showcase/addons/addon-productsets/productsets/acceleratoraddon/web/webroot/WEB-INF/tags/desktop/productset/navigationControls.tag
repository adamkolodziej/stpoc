<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="isNavigation" required="true" type="java.lang.Boolean" %>

<div class="controls">
	<a href="#" class="button positive buyTheSetButton">${not empty component.buyButtonText ? component.buyButtonText : "Buy the Set"}</a>
	<c:if test="${isNavigation}">
		<div class="navigation">		
			<div class="prev productset-nav-prev"></div>
			<div class="pagination">
				<p><span class="scroll-step">1</span><spring:theme code="buytheset.navigation.of" text="of" /><span class="scroll-size">1</span></p>
			</div><!--/pagination-->
			<div class="next productset-nav-next"></div>
		</div><!--/navigation-->
	</c:if>
</div><!--/controls-->