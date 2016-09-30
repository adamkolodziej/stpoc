<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<c:url value="/_ui/css/servicesshowcase.css" var="stylesheetPath"/>
<c:url value="/_ui/js/servicesshowcase.js" var="scriptPath"/>
<c:url value="/_ui/js/servicesshowcase-services.js" var="scriptPathServices"/>
<c:url value="/_ui/js/servicesshowcase-controllers.js" var="scriptPathControllers"/>
<c:url value="/_ui/js/angular.min.js" var="angularScriptPath"/>

<html>
    <head>
        <title>Control panel</title>
        <link rel="stylesheet" type="text/css" href="${stylesheetPath}"/>
        <script type="text/javascript">
            var ACC = { config: {} };
            ACC.config.contextPath = "${contextPath}";
        </script>
        <script src="${angularScriptPath}" type="text/javascript"></script>
        <script src="${scriptPath}" type="text/javascript"></script>
        <script src="${scriptPathServices}" type="text/javascript"></script>
        <script src="${scriptPathControllers}" type="text/javascript"></script>
	</head>
    <body ng-app="servicesshowcase">
        <div class="content" ng-controller="ControlPanelController">
            <h2>Control panel settings</h2>
            <ul>
                <li ng-repeat="msg in controlPanelData.messages" class="alert {{msg.severity}}">
                    <span ng-bind="msg.content"></span>
                </li>
            </ul>
            <div class="top-bar">
            </div>
            <div class="top-banner">
            </div>
            <div class="inner-content">
                <table cellspacing="10">
                    <tr><td><h3>Configuration properties:</h3></td><td></td></tr>
                    <tr ng-repeat="property in controlPanelData.controlPanelProperties">
                        <td><b><span ng-bind="property.key"></span></b></td>
                        <td>
                            <select ng-if="property.possibleValues.length > 0"
                                    ng-model="property.value" ng-options="v as v for v in property.possibleValues"/>
                            <input ng-if="property.possibleValues.length == 0"
                                    ng-model="property.value" type="text" />
                        </td>
                    </tr>
                    <tr><td><button ng-click="saveAll()">Save all</button></td><td></td></tr>
                    <tr><td><h3>Urls:</h3></td><td></td></tr>
                    <tr ng-repeat="url in controlPanelData.controlPanelUrls">
                        <td><b><span ng-bind="url.label"></span></b></td>
                        <td><a href="{{url.url}}">{{url.url}}</a></td>
                    </tr>
                    <tr><td><h3>Actions:</h3></td><td></td></tr>
                    <c:forEach var="segment" items="${applicableBtgSegments}">
                        <tr>
                            <td><b><span>${segment.key}</span></b></td>
                            <td><button ng-click="evaluateSegment('${segment.value}')">Evaluate</button></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="footer">
            </div>
        </div>
    </body>
</html>