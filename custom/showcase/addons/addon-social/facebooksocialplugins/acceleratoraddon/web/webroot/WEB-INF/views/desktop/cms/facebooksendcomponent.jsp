<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="${urlToSend}"  var="href" />
<fb:send href="${not empty urlToSend ? href : ''}" colorscheme="${colorScheme}" font="${font}"></fb:send>