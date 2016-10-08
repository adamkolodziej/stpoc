<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="${url}"  var="href" />
<fb:recommendations-bar 
	href="${url}" 
	site="${domains}" 
	trigger="${trigger}" 
	read_time="${readTime}" 
	action="${action}" 
	side="${side}" 
	site="${site}" 
	ref="${ref}" 
	num_recommendations="${numRecommendations}" 
	max_age="${maxAge}">
</fb:recommendations-bar>