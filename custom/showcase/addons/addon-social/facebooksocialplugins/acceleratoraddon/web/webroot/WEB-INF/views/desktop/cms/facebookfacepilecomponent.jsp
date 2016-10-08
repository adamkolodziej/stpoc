<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="${url}"  var="href" />

<fb:facepile href="${not empty url ? href : ''}" size="${pictureSize}" max_rows="${maxRows}" width="${width}"  colorscheme="${colorScheme}"  ></fb:facepile>
