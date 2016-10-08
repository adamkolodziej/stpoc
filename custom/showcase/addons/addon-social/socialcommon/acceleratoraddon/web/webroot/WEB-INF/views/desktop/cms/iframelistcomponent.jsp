<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="socialcommon" uri="/WEB-INF/tld/addons/socialcommon/socialcommontags.tld" %>

<div class="iframe-list-component line-heading">
	<c:if test="${not empty title}">
		<div class="heading">
			<h4>${title}</h4>
		</div>
	</c:if>	
	<ul class="list-content">
		<c:forEach items="${items}" var="iframe">
			<li><socialcommon:iframe iframe="${iframe}" /></li>
		</c:forEach>
	</ul>
</div>