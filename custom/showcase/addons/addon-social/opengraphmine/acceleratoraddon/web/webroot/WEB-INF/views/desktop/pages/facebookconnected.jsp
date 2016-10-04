<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>

<html>
<head>
<template:javaScriptVariables/>
<c:set value="${facebookUrl}"  var="url" />
<script type="text/javascript">
	
	//Setting href value to ACC.config.contextPath to make possible reload parent on facebook connect event 
	
	window.opener.location.href = ACC.config.contextPath;
	window.close();
	window.opener.location.reload();
</script>
</head>
<body>
</body>
</html>
