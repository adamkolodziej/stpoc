<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${showStorefrontDebugInfo}">
    <style type="text/css">
        #raise-a-bug-trigger {
            background-color: #f5f5f5;
            border: 1px solid #777;
            border-bottom: none;
            border-right: none;
            bottom: 0;
            color: #444 !important;
            padding: 6px;
            right: 0;
            box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.5);
            display: block;
            font-family: arial,FreeSans,Helvetica,sans-serif;
            font-size: 12px;
            font-weight: bold;
            position: fixed;
            cursor: pointer;
        }
    </style>
    <a id="raise-a-bug-trigger">Raise a Bug</a>
    <script type="text/javascript">
        $(function() {
            $('#raise-a-bug-trigger').click(function(e) {
                window.open("https://jira.hybris.com/rest/collectors/1.0/template/form/368c060b?os_authType=none", "Raise a Bug", "height=600,width=600");
                return false;
            });
        })
    </script>
</c:if>