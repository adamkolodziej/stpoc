<%@ page trimDirectiveWhitespaces="true"%>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>

<!doctype html>
<c:url value="/_ui/css/underworldtco.css" var="stylesheetPath"/>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${stylesheetPath}"/>
        <template:header/>
    </head>
    <body>
        <div class="top-bar">
            <div class="search-bar"></div>
        </div>
    	<div class="top-banner">
    	    <a class="like-link" id="top-banner-like-link" href="#" data-wasLiked="UNDERWORLD" title="Like Underworld"></a>
    	</div>
    	<div class="content">
            <div class="left-column"></div>
            <div class="right-column">
                <div class="post0UTco"></div>
                <div class="post1UTco">
                    <a class="like-link" id="fb_post1UTco-like-link" href="#" data-wasLiked="BRANDED_MERCHANDISE" title="Like Branded Merchandise"></a>
                </div>
                <div class="post2UTco">
                    <a class="like-link" id="fb_post2UTco-like-link" href="#" data-wasLiked="BRANDED_MERCHANDISE" title="Like Branded Merchandise"></a>
                </div>
                <div class="post3UTco">
                    <a class="like-link" id="fb_post3UTco-like-link" href="#" data-wasLiked="UNDERWORLD" title="Like Underworld"></a>
                </div>
                <div class="post4UTco">
                    <a class="like-link" id="fb_post4UTco-like-link" href="#" data-wasLiked="UNDERWORLD" title="Like Underworld"></a>
                </div>
                <div class="post5UTco">
                    <a class="like-link" id="fb_post5UTco-like-link" href="#" data-wasLiked="UNDERWORLD" title="Like Underworld"></a>
                </div>
            </div>
            <template:datePicker/>
        </div>
        <div class="footer"></div>
    </body>
</html>