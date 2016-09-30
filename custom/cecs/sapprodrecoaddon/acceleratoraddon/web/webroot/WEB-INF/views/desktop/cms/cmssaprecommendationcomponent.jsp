<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="component" tagdir="/WEB-INF/tags/shared/component" %>


<jsp:useBean id="random" class="java.util.Random" scope="application" />
<c:set var="cid" value="reco${random.nextInt(1000)}"/>

<div class="scroller ${cssClass}" id="${cid}" data-title="${title}" data-recotype="${recoType}" data-prodcode="${productCode}"
	 data-itemtype="${itemType}" data-includecart="${includeCart}" data-component="${component.uid}"/></div>