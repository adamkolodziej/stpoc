<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row ${cssClass}">
    <div class="col-xs-12">
        <h3><spring:theme code="customer.reviews" /></h3>
    </div>
    <div class="col-xs-12">
        <ul class="list-unstyled list-inline rating-stars">
            <c:forEach var="i" begin="1" end="${starAmount}">
                <c:choose>
                    <c:when test="${i <= averageRating}">
                        <li><i class="fa fa-star"></i></li>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${1 - (i - averageRating) >= 0.4}">
                                <li><i class="fa fa-star-half-o"></i></li>
                            </c:when>
                            <c:otherwise>
                                <li><i class="fa fa-star-o"></i></li>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </ul>
        <ul class="list-unstyled list-inline rating-info">
            <li><div class="text-info">${productNumberOfReviews}</div></li>
            <li>
                <fmt:formatNumber type="productAverageRatingFormatted" pattern="###.#" value="${averageRating}" />
                <spring:theme code="product.rating.average" arguments="${productAverageRatingFormatted},${starAmount}" />
            </li>
        </ul>
    </div>
</div>