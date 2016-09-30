<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common" %>

<c:url var="formUrl" value="/reviews/add" />
<c:set var="topReviewsAmount" value="3" />

<div class="col-xs-12">
    <div id="starAmount" style="display: none;">${starAmount}</div>
    <div class="row">
        <div class="col-xs-12">
            <h3><spring:theme code="customer.reviews" /></h3>
        </div>
        <div class="col-xs-12">
            <ul class="list-unstyled list-inline rating-stars">
                <c:forEach var="i" begin="1" end="${starAmount}">
                    <c:choose>
                        <c:when test="${i <= productReviewed.averageRating}">
                            <li><i class="fa fa-star"></i></li>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${1 - (i - productReviewed.averageRating) >= 0.4}">
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
                <li><div class="text-info">${productReviewed.numberOfReviews}</div></li>
                <li>
                    <fmt:formatNumber type="productAverageRatingFormatted" pattern="###.#" value="${productReviewed.averageRating}" />
                    <spring:theme code="product.rating.average" arguments="${productAverageRatingFormatted},${starAmount}" />
                </li>
            </ul>
        </div>
        <c:if test="${productReviewed.numberOfReviews > 0}">
            <div class="col-xs-12">
                <div class="row">
                    <div class="col-xs-12 col-sm-8">
                        <table class="table table-condensed rating-table">
                            <tbody>
                                <c:forEach items="${starsToPercentageMap}" var="star">
                                    <tr>
                                        <td class="star"><spring:theme code="stars.ordinal" arguments="${star.key}" /></td>
                                        <td class="bar">
                                            <div class="progress sptel-rating-bar">
                                                <div class="progress-bar" role="progressbar" aria-valuenow="${star.value}"
                                                     aria-valuemin="0" aria-valuemax="100" style="width: ${star.value}%;">
                                                    <span class="sr-only"><spring:theme code="stars.percent.complete" arguments="${star.value}" /></span>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="rating">${star.value}%</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-xs-12 col-sm-4">
                        <a href="#" class="btn btn-sptel-secondary text-uppercase review-write"><spring:theme code="review.write" /></a>
                    </div>
                </div>
            </div>
        </c:if>
        <div class="col-xs-12">
            <hr/>
        </div>
        <div class="col-xs-12">
            <h3><spring:theme code="review.top.reviews" /></h3>
        </div>
        <div class="col-xs-12 reviews-container">
            <c:set var="currReview" value="0" scope="page" />
            <c:forEach items="${sortedReviews}" var="review">
                <div class="review <c:if test='${topReviewsAmount <= currReview}'>hiddenreview</c:if>">
                    <ul class="list-unstyled list-inline rating-stars">
                        <c:forEach var="i" begin="1" end="${starAmount}">
                            <c:choose>
                                <c:when test="${i <= review.rating}">
                                    <li><i class="fa fa-star"></i></li>
                                </c:when>
                                <c:otherwise>
                                    <li><i class="fa fa-star-o"></i></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
                    <span class="review-description">
                        <c:choose>
                            <c:when test="${not empty review.headline}">
                                ${review.headline}
                            </c:when>
                            <c:otherwise>
                                 <c:set var="alternativeHeadlineNoNewLineChar" value='${fn:replace(review.comment, "<br/>", " ")}' />
                                 <c:set var="alternativeHeadline" value='${fn:substring(alternativeHeadlineNoNewLineChar, 0, 40)}' />
                                 ${alternativeHeadline}...
                            </c:otherwise>
                        </c:choose>
                    </span>
                    <span class="reviewer-name">
                        <fmt:formatDate pattern="MMMM dd, yyyy" value="${review.creationtime}" var="creationtime" />
                        <spring:theme code="review.creation.time" arguments="${review.commentatorName};${creationtime}" argumentSeparator=";" />
                    </span>
                    <div class="review-text">
                        ${review.comment}
                    </div>
                    <div class="review-footer" data-product-code="${productReviewed.code}" data-customer-comentator-id="${review.commentatorName}">
                        <div class="review-info">
                            <spring:theme code="review.marked.helpful" arguments="${fn:length(review.usersMarkedReviewHelpful)},${fn:length(review.usersMarkedReviewHelpful) + fn:length(review.usersMarkedReviewUnhelpful)}" />
                        </div>
                        <a href="#" class="btn btn-sptel-default btn-sm text-uppercase review-helpful-yes" data-helpfull="yes"><spring:theme code="review.mark.helpful.yes" /></a>
                        <a href="#" class="btn btn-sptel-default btn-sm text-uppercase review-helpful-no" data-helpfull="no"><spring:theme code="review.mark.helpful.no" /></a>
                        <div class="message" style="color: grey;"></div>
                    </div>
                </div>
                <c:set var="currReview" value="${currReview + 1}" />
            </c:forEach>
        </div>
        <div class="col-xs-12">
            <hr/>
        </div>
        <c:if test="${reviewsAmount > topReviewsAmount}">
            <div class="col-xs-12">
                <a href="#" id="show-all-reviews" class="text-info js-load-more-reviews">
                    <span><spring:theme code="review.show.all" arguments=" ${reviewsAmount}" /></span>
                    <span style="display: none;"><spring:theme code="review.hide" /></span>
                </a>
            </div>
        </c:if>
        <div class="col-xs-12">
            <a href="#" class="btn btn-sptel-secondary text-uppercase review-write"><spring:theme code="review.write" /></a>

        <div class="row">
            <c:if test="${not empty submitSuccessful}">
                <div class="col-xs-12">
                    <div class="alert alert-success">
                      ${submitSuccessful}
                    </div>
                </div>
            </c:if>
            <c:if test="${not empty submitFailed}">
                <div class="col-xs-12">
                    <div class="alert alert-danger">
                      ${submitFailed}
                    </div>
                </div>
            </c:if>
                <div>
                    <form:form id="add-review-form" method="post" cssClass="span-5" action="${formUrl}" commandName="addReviewForm">
                        <table class="table">
                            <tr>
                                <spring:theme code="review.form.title" var="formTitle" />
                                <td><formElement:formInputBox idKey="title" labelKey="${formTitle}" path="title" mandatory="false" />
                                <form:errors path="title" cssClass="error" /></td>
                            </tr>
                            <tr>
                                <spring:theme code="review.form.description" var="formDescription" />
                                <td>
                                    <formElement:formTextArea idKey="description" labelKey="${formDescription}" path="description" areaCSS="form-control" mandatory="true"/>
                                    <c:if test="${not empty errors['description']}">
                                        <div class="validation-error" style="color: #b94a48;">${errors['description']}</div>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <spring:theme code="review.form.rating" var="formRating" />
                                <td>
                                    <label class="control-label" for="rating">
                                        <spring:theme code="${formRating}"/>
                                        <span class="mandatory text-danger">
                                            <spring:theme code="login.required" var="loginrequiredText" />
                                            <span title="${loginRequiredText}">*</span>
                                        </span>
                                        <span class="skip"><form:errors path="${path}"/></span>
                                    </label>
                                    <spring:theme code="${placeholder}" var="placeHolderMessage" />
                                    <form:hidden cssClass="form-control" id="rating-stars-input" path="rating"
                                            tabindex="${tabindex}" autocomplete="${autocomplete}" placeholder="${placeHolderMessage}"/>

                                    <div class="rate-it">
                                        <ul class="list-unstyled list-inline rating-stars">
                                            <c:forEach var="i" begin="1" end="${starAmount}">
                                                <li><i class="fa fa-star-o rate-it-${i}" data-rate="${i}"></i></li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <c:if test="${not empty errors['rating']}">
                                        <div class="validation-error" style="color: #b94a48;">${errors['rating']}</div>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <spring:theme code="review.form.name" var="formName" />
                                <td>
                                    <formElement:formInputBox idKey="name" labelKey="${formName}" path="name" mandatory="true" />
                                    <c:if test="${not empty errors['name']}">
                                        <div class="validation-error" style="color: #b94a48;">${errors['name']}</div>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <formElement :formInputBox idKey="CSRFToken" labelKey="CSRFToken" path="CSRFToken" inputCSS="hidden" mandatory="true" />
                                    <input type="hidden" value="${product.code}" name="product" />
                                    <input type="hidden" value="${CSRFToken}" name="CSRFToken" />
                                    <button type="submit" class="btn btn-sptel-secondary text-uppercase">
                                        <spring:theme code="review.form.submit" />
                                    </button>
                                    <button type="submit" class="btn btn-sptel-secondary text-uppercase review-cancel">
                                        <spring:theme code="review.form.cancel" />
                                    </button>
                                </td>
                            </tr>
                        </table>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
