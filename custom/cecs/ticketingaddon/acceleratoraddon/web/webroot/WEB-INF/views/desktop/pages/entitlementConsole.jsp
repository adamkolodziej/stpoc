<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="${commonResourcePath}/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){

            $(".grantEntitlement").submit(function(e) {

                e.preventDefault();

                var actionUrl = $(".grantEntitlement").attr("action"),
                        currElement = $(this),
                        entitlementId = $('input[name="entitlementId"]', this).attr("value"),
                        entitlementName = $('input[name="entitlementName"]', this).attr("value"),
                        customer = $('input[name="customer"]', this).attr("value"),
                        ticket = $('input[name="ticket"]', this).attr("value"),
                        productCode = $('input[name="productCode"]', this).attr("value"),
                        CSRFToken = $('input[name="CSRFToken"]', this).attr("value");

                $.ajax({
                    url: actionUrl,
                    type: 'POST',
                    data: {
                        "entitlementId":entitlementId,
                        "entitlementName":entitlementName,
                        "customer":customer,
                        "ticket": ticket,
                        "productCode": productCode,
                        "CSRFToken":CSRFToken
                    },
                    success: function (response){
                        $(currElement).html(response);
                    }
                });
            });
        });
    </script>
    <style type="text/css">
        * {margin:0;padding:0;font-size: 1em;}
        body{ font-family: Arial, Helvetica, sans-serif; font-size:15px; line-height:18px;color:#666666; padding:20px; }
        .content {  }
        .content .title{ border-bottom: 1px solid #dddddd; padding:5px 0; margin-bottom:5px; width:100%; }
        .content .title h3 { text-transform: uppercase; font-weight:normal; }
        .content .entry{ border-bottom: 1px solid #dddddd; padding:0 1%; width:98%; }
        .content .entry > div { float:left; }
        .entry { height:50px; line-height:51px; width:100%; font-size:12px; color:#000; }
        .entry .index { width:5%; text-align:center; }
        .entry .img { width:10%; }
        .entry .img img { height:48px; display:block; margin:0 auto; margin-top:2px; }
        .entry .img img.thumb-large {z-index:90; opacity:0;position:absolute;left:-999px; }
        .entry .img:hover img.thumb-large {z-index:90; height:192px;opacity:1;left:250px; }
        .smallThumb {float:left;margin:0 auto;width:100%}
        .entry .name { width:60%; }
        .entry .badge { width:10%; text-align: center; }
        .entry .eform { width:15%; text-align: center; }
        .entry .eform .btn { padding:2px 4px; }
        .entryLabel { width:98%; padding:0 1%; height:30px; line-height:30px; border-bottom: 2px solid #dddddd; color:#666; font-size:12px; text-align: center; font-weight:bold; }
        .entryLabel > div{ float:left; }
        .entryLabel .index { width:5%; }
        .entryLabel .img { width:10%; }
        .entryLabel .name { width:60%; text-align: left; }
        .entryLabel .badge { width:10%; }
        .entryLabel .eform { width:15%; }
        .msg { color:#000;font-size:14px;padding:25px 0; text-align: center; border-bottom: 1px solid #dddddd; }
    </style>
</head>
<body>

<c:url var="formUrl" value="/entitlements-console" />
<c:set var="c4cGiftValue" value="${surveyValue}" />
<c:set var="accetableGiftPercent" value="10" />
<c:set var="topAccetableGiftPercent" value="${100 + accetableGiftPercent}" />
<c:set var="bottomAccetableGiftPercent" value="${100 - accetableGiftPercent}" />

<div id="wrapper">
    <div class="content">
        <div class="title">
            <h3><spring:theme code="entitlements.console.account.recommendations.for"/>${ycustomer.name}</h3>
        </div>    
        
        <div class="entryLabel">
            <div class="index">ID</div>
            <div class="img">&nbsp;</div>
            <div class="name">Name</div>
            <div class="badge">Gift value</div>
            <div class="eform">Granted</div>
        </div>
        <c:forEach items="${productEntitlements}" var="entry" varStatus="loop">
            <div class="entry ${entry.id}">
                <div class="index">
                        ${loop.index}
                </div>
                <div class="img">
                <div class="smallThumb">
                    <product:productPrimaryImage product="${entry.product}" format="thumbnail" cssClass="thumb" showMissing="true" />
                    </div>
                    <div class="largeThumb">
                    <product:productPrimaryImage product="${entry.product}" format="thumbnail" cssClass="thumb-large" showMissing="true" />
                    </div>
                </div>
                
                <div class="name">
                        ${entry.name}
                </div>
                <div class="badge">
                    <c:choose>
                        <c:when test="${empty entry.giftValue}">
                            <span>[ 0 ]</span>
                        </c:when>
                        <c:otherwise>
                            <span>[ ${entry.giftValue} ]</span>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="eform">
                    <c:set var="buttonEnabled" value="" />
                    <c:if test="${entry.granted}">
                        <c:set var="buttonEnabled" value="disabled" />
                    </c:if>
                    <form class="grantEntitlement" action="${formUrl}" method="post">
                        <input type="hidden" value="${entry.id}" name="entitlementId" />
                        <input type="hidden" value="${entry.name}" name="entitlementName" />
                        <input type="hidden" value="${customer}" name="customer" />
                        <input type="hidden" value="${ticket}" name="ticket" />
                        <input type="hidden" value="${entry.product.code}" name="productCode" />
                        <input type="hidden" name="CSRFToken" value="${CSRFToken}">
                        <button ${buttonEnabled} class="btn" type="submit">
                            <spring:theme code="entitlements.console.account.grant"/>
                        </button>
                    </form>
                </div>
            </div>
        </c:forEach>
        <c:if test="${fn:length(productEntitlements) lt 1}">
            <div class="msg">
                <p>No records found</p>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>