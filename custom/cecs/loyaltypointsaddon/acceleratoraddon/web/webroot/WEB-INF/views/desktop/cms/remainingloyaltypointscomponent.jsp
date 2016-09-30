<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div id="remainingLoyaltyPoints" title="<spring:theme code='guidedselling.remaining.loyalty' text='Remaining loyalty points'/>">
${remainingLoyaltyPoints}
</div>