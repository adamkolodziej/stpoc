<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
{ 
	"storefrontCart" : <jsp:directive.include file="/WEB-INF/views/mobile/fragments/cart/addToCartPopup.jsp" />,
	"seewhyCart" :
 	{  		
	  	<c:forEach var="jsVarHolder" items="${jsAddOnsVariables}">
	  	
	  		<c:if test = "${jsVarHolder.key == 'seewhy'}">
	  	
				<c:forEach var="jsVar" items="${jsVarHolder.value}" varStatus="jsVarStatus">
					<c:if test="${not empty jsVar.qualifier}" >
						"${jsVar.qualifier}" : "${jsVar.value}"<c:if test="${not jsVarStatus.last}">,</c:if>
					</c:if>
				</c:forEach>
			
			</c:if>
			
		</c:forEach>		
 	}
}