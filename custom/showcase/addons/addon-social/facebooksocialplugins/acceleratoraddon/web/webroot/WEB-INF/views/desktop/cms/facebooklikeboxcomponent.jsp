<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fb:like-box href="${facebookPageUrl}" width="${width}"  <c:if test="${not empty height}">height="${height}"</c:if>  show_faces="${showFaces}" stream="${showStream}" header="${showHeader}" force_wall="${forceWall}" colorscheme="${colorScheme}" border_color="${borderColor}"></fb:like-box>
