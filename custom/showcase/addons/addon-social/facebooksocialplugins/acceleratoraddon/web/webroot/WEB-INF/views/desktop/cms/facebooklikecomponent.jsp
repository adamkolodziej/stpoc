<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="${urlToLike}"  var="href" />
<fb:like href="${not empty urlToLike ? href : ''}" send="${sendButton}" layout="${layoutStyle}"  width="${width}" show_faces="${showFaces}" action="${verbToDisplay}" colorscheme="${colorScheme}" font="${font}"></fb:like>