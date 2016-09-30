<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/_ui/css/jquery.css" var="jqueryCss"/>
<c:url value="/_ui/js/facebookmock.js" var="facebook"/>
<c:url value="/_ui/js/jquery-1.11.2.min.js" var="jquery"/>
<c:url value="/_ui/js/jquery-ui-1.11.2.min.js" var="jqueryUi"/>
<c:url value="/_ui/js/timeswitcher.js" var="timeswitcher"/>

<title>Facebook mock</title>
<link rel="stylesheet" type="text/css" href="${jqueryCss}"/>

<%-- JS configuration --%>
<script type="text/javascript">
    /*<![CDATA[*/
    <%-- Define a javascript variable to hold the content path --%>
    var ACC = { config: {} };
    ACC.config.contextPath = "${contextPath}";
    ACC.config.encodedContextPath = "${encodedContextPath}";
    ACC.config.commonResourcePath = "${commonResourcePath}";
    ACC.config.themeResourcePath = "${themeResourcePath}";
    ACC.config.siteResourcePath = "${siteResourcePath}";
    ACC.config.rootPath = "${siteRootUrl}";
    ACC.config.CSRFToken = "${CSRFToken}";
    <c:if test="${request.secure}"><c:url value="/search/autocompleteSecure"  var="autocompleteUrl"/></c:if>
    <c:if test="${not request.secure}"><c:url value="/search/autocomplete"  var="autocompleteUrl"/></c:if>
    ACC.autocompleteUrl = '${autocompleteUrl}';

    <c:forEach var="jsVar" items="${jsVariables}">
    <c:if test="${not empty jsVar.qualifier}" >
    ACC.${jsVar.qualifier} = '${jsVar.value}';
    </c:if>
    </c:forEach>
    /*]]>*/
</script>
<script type="text/javascript" src="${jquery}"></script>
<script type="text/javascript" src="${jqueryUi}"></script>
<script type="text/javascript" src="${facebook}"></script>
<script type="text/javascript" src="${timeswitcher}"></script>

