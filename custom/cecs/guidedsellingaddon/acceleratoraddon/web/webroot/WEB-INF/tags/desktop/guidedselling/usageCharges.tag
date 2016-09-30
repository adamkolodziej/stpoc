<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ attribute name="option" required="true" type="com.hybris.showcase.guidedselling.data.BundleComponentOptionData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${not empty option.usageCharges}">
    <div class="usageCharges">
        <a data-toggle="collapse" href="#${option.product.code}_usageCharges" class="collapsed">
            <spring:theme code="guidedselling.label.usageCharges" text="Usage Charges ({0}):" arguments="${fn:length(option.usageCharges)}" />
            &nbsp;<i class="fa fa-chevron"></i>
        </a>
        <div id="${option.product.code}_usageCharges" class="collapse">
            <ul>
                <c:forEach var="usageCharge" items="${option.usageCharges}">
                    <li>
                        <span>${usageCharge.usageUnit.name} - <strong>${usageCharge.price.formattedValue}</strong></span>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</c:if>
