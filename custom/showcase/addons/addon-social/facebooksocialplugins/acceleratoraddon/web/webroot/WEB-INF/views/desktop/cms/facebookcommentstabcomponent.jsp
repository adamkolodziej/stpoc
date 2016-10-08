<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:theme code="facebook.comments.tab.title" var="title_resource" />

<c:url value="${urlToCommentOn}"  var="href" />
<c:set var="tab_title" value="${ not empty title ? title : title_resource }" />


<div class="tabHead">${tab_title}</div>
<div class="tabBody"><fb:comments href="${not empty urlToCommentOn ? href : ''}" num_posts="${numberOfPosts}" width="${width}" colorscheme="${colorScheme}" ></fb:comments></div>


