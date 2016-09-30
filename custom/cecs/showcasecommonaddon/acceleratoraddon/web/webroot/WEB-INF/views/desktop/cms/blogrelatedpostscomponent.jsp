<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="popular-container box">
	<!-- FIXME Hardcoded. When requirements arrive, rework it. -->
    <h3 class="title"><spring:theme code="text.BlogRelatedPostsComponent.header.${ fn:toLowerCase(displayType) }" /></h3>
    <ul class="list-unstyled">
        <li class="popular-item">
            <div class="media">
                <div class="media-left">
                    <a href="#">
                        <img class="media-object" src="${commonResourcePath}/images/blog-mock/related-1.jpg" alt="">
                    </a>
                </div>
                <div class="media-body">
                    <h4 class="media-heading">Underworld</h4>
                    <div class="season">Season 2 Episode 2</div>
                    <div class="title">The Regret</div>
                    <div class="category text-uppercase">series on demand</div>
                </div>
            </div>
        </li>
        <li class="popular-item">
            <div class="media">
                <div class="media-left">
                    <a href="#">
                        <img class="media-object" src="${commonResourcePath}/images/blog-mock/related-2.jpg" alt="">
                    </a>
                </div>
                <div class="media-body">
                    <h4 class="media-heading">Hailey Giffords</h4>
                    <div class="season"></div>
                    <div class="title">Reveals New <br/> Underwoeld Episode</div>
                    <div class="category text-uppercase">series on demand</div>
                </div>
            </div>
        </li>
        <li class="popular-item">
            <div class="media">
                <div class="media-left">
                    <a href="#">
                        <img class="media-object" src="${commonResourcePath}/images/blog-mock/related-3.jpg" alt="">
                    </a>
                </div>
                <div class="media-body">
                    <h4 class="media-heading">Underworld</h4>
                    <div class="season"></div>
                    <div class="title">James comfortable with big-picture</div>
                    <div class="category text-uppercase">movies on demand</div>
                </div>
            </div>
        </li>
    </ul>
    <a href="#" class="btn btn-sptel-secondary text-uppercase"><spring:theme code="text.BlogRelatedPostsComponent.allPostsButton.${ fn:toLowerCase(displayType) }" /></a>
</div>
