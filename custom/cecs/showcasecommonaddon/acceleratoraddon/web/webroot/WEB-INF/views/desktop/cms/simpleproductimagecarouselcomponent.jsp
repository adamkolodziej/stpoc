<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    <div class="row gallery ${cssClass}">
            <div class="col-xs-12">
                <img class="img-responsive main-photo" src="${galleryImages[0].product.url}" alt="${galleryImages[0].product.altText}"/>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <ul class="thumbnails">
                <c:forEach items="${galleryImages}" var="container" varStatus="varStatus">
                    <li class="${varStatus.first ? 'active' : ''}">
                        <a href="#" class="thumbnail-img" data-mainImage="${container.product.url}">
                            <img src="${container.thumbnail.url}" alt="${container.thumbnail.altText}"/>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
