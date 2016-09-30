<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:url value="/search/" var="searchUrl"/>
<c:url value="/search/autocomplete/${component.uid}" var="autocompleteUrl"/>


<div class="siteSearch">
	<form name="search_form_${component.uid}" method="get" action="${searchUrl}" class="navbar-form form-inline">
		<div class="form-group row">
			<spring:theme code="text.search" var="searchText"/>
			<label class="control-label skip visible-print-block" for="input_${component.uid}">${searchText}</label>
            <spring:theme code="search.placeholder" var="searchPlaceholder"/>
            <ycommerce:testId code="header_search_button">
                    <button class="siteSearchSubmit btn btn-link btn-sm" type="submit"/><span class="glyphicon glyphicon-search"></span></button>
            </ycommerce:testId>
            <ycommerce:testId code="header_search_input">
                    <input
                        id="input_${component.uid}"
                        class="siteSearchInput form-control input-sm"
                        type="text"
                        name="text"
                        value=""
                        maxlength="100"
                        placeholder="${searchPlaceholder}"
                        data-options='{"autocompleteUrl" : "${autocompleteUrl}","minCharactersBeforeRequest" : "${component.minCharactersBeforeRequest}","waitTimeBeforeRequest" : "${component.waitTimeBeforeRequest}","displayProductImages" : ${component.displayProductImages}}'/>
            </ycommerce:testId>
        </div>
	</form>
</div>