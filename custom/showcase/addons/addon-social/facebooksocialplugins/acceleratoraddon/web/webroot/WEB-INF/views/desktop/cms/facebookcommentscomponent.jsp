<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="${urlToCommentOn}"  var="href" />
<fb:comments href="${not empty urlToCommentOn ? href : ''}" num_posts="${numberOfPosts}" width="${width}" colorscheme="${colorScheme}" ></fb:comments>
