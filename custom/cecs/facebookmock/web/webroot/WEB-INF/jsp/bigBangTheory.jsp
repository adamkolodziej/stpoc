<%@ page trimDirectiveWhitespaces="true"%>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>

<!doctype html>
<c:url value="/_ui/css/facebookmock.css" var="stylesheetPath"/>
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
    	    <a class="like-link" id="top-banner-like-link" href="#" data-wasLiked="page"></a>
    	</div>
    	<div class="content">
            <div class="left-column"></div>
            <div class="right-column">
                <div class="fb_merchandisePost">
                    <a class="like-link" id="fb_merchandisePost-like-link" href="#" data-wasLiked="merchandisePost"></a>
                </div>
                <div class="fb_bigBangPost">
                    <a class="like-link" id="fb_bigBangPost-like-link" href="#" data-wasLiked="bigBangPost"></a>
                </div>
            </div>
        </div>
        <template:datePicker/>
        <div class="footer"></div>
    </body>
</html>