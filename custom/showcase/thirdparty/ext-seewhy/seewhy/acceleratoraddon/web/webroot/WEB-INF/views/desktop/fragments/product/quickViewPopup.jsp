<jsp:directive.include file="/WEB-INF/views/desktop/fragments/product/quickViewPopup.jsp" />
<script type="text/javascript">
     
	/*<![CDATA[*/
			           
	<c:forEach var="jsVarHolder" items="${jsAddOnsVariables}">
	
		<c:if test = "${jsVarHolder.key == 'seewhy'}">
		
			ACC.addons.${jsVarHolder.key} = [];
			<c:forEach var="jsVar" items="${jsVarHolder.value}">
				<c:if test="${not empty jsVar.qualifier}" >
					ACC.addons.${jsVarHolder.key}['${jsVar.qualifier}'] = '${jsVar.value}';
				</c:if>
			</c:forEach>
		
		</c:if>
		
	</c:forEach>
	/*]]>*/
	
	com_seewhy_addon_f.seewhy.productBrowsed();

</script>

