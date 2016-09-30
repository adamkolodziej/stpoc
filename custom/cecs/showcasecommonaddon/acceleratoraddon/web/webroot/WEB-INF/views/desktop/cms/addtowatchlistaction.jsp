<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="productList" tagdir="/WEB-INF/tags/addons/productlists/desktop/productlists" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:theme code="text.AddToWatchList.button" var="watchlistlabel" />
<productList:productAddToProductListPanel buttonText="${watchlistlabel}" product="${product}" cssStyle="btn btn-sptel-primary text-uppercase"/>