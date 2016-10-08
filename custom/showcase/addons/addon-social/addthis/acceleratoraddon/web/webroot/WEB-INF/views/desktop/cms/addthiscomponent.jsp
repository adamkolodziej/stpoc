<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://granule.com/tags/accelerator" %>

<!-- AddThis Button BEGIN -->
<div class="addthis_toolbox ${orientation eq 'HORIZONTAL' ? 'addthis_default_style' : 'addthis_floating_style'} ${size eq 'BIG' ? ' addthis_32x32_style' : ' addthis_16x16_style'}">
	<c:forEach items="${buttons}" var="button" varStatus="status">
		<a class="addthis_button_${button}"></a>
	</c:forEach>
	<c:if test="${isCompactButton}">
		<a class="addthis_button_compact"></a>
	</c:if>
	<c:if test="${isCounterButton}">
		<a class="addthis_counter ${orientation eq 'HORIZONTAL' ? 'addthis_bubble_style' : ''}"></a>
	</c:if>
</div>
<g:compress urlpattern="${encodingAttributes}">
	<script type="text/javascript">
		var addthis_config = 
		{
			ui_language: '${config.ui_language}',
			services_compact: '${config.services_compact}',
			services_exclude: '${config.services_exclude}',
			services_expanded: '${config.services_expanded}',
			ui_click: '${config.ui_click}'
		};           
	</script>
</g:compress>	
<script type="text/javascript" src="//s7.addthis.com/js/300/addthis_widget.js#pubid=${analyticsUser}"></script>
<!-- AddThis Button END -->