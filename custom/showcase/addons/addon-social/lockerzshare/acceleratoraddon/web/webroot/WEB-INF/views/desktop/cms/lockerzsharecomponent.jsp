<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://granule.com/tags/accelerator" %>

<!-- LockerzShare Component BEGIN -->
<div class="a2a_kit a2a_default_style">
	<c:choose>
		<c:when test="${!empty buttons}">
			<a class="a2a_dd" href="http://www.addtoany.com/share_save">${linkText}</a>
			<span class="a2a_divider"></span>
			<c:forEach items="${buttons}" var="button" varStatus="status">
				<a class="a2a_button_${button}"></a>
			</c:forEach>	
		</c:when>
		<c:otherwise>
			<a class="a2a_dd" href="http://www.addtoany.com/share_save">
				<c:choose>
					<c:when test="${style eq 'link'}">
						${linkText}
					</c:when>
					<c:otherwise>
						<div class="lockerzshare_${style}"></div>
					</c:otherwise>
				</c:choose>
			</a>
		</c:otherwise>
	</c:choose>
</div>
<g:compress urlpattern="${encodingAttributes}">
	<script type="text/javascript">
	var a2a_config = {
		locale: '${config.locale}'	
	};
	</script>
</g:compress>	
<script type="text/javascript" src="http://static.addtoany.com/menu/page.js"></script>
<!-- LockerzShare Component END -->