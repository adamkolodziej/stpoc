<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="col-xs-12 category box-xs">
    <h3 class="category-header">
        <i class="fa fa-question-circle"></i>
        ${headline}
    </h3>
    <div class="category-option faq">
        <div role="tabpanel" class="nav-tabs-container">

            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <c:forEach items="${bundleTemplateData.bundleTemplates}" var="childBundleTemplate" varStatus="status">
                    <c:if test="${not empty childBundleTemplate.faqEntries}">
                    <li role="presentation" class="${status.first ? 'active' : ''}"><a href="#tab-${status.index}" aria-controls="tab-${status.index}" role="tab" data-toggle="tab">${childBundleTemplate.name}</a></li>
                    </c:if>
                </c:forEach>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <c:forEach items="${bundleTemplateData.bundleTemplates}" var="childBundleTemplate" varStatus="status">
                <c:if test="${not empty childBundleTemplate.faqEntries}">
                <div role="tabpanel" class="tab-pane${status.first ? ' active' : ''}" id="tab-${status.index}">
                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                        <c:forEach items="${childBundleTemplate.faqEntries}" var="faqEntry" varStatus="innerStatus">
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="heading-${status.index}-${innerStatus.index}">
                                <h4 class="panel-title">
                                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapse-${status.index}-${innerStatus.index}" aria-expanded="false" aria-controls="collapse-${status.index}-${innerStatus.index}">
                                        <i class="fa fa-chevron"></i>${faqEntry.question}
                                    </a>
                                </h4>
                            </div>
                            <div id="collapse-${status.index}-${innerStatus.index}" class="panel-collapse collapse " role="tabpanel" aria-labelledby="heading-${status.index}-${innerStatus.index}">
                                <div class="panel-body">
                                    ${faqEntry.answer}
                                </div>
                            </div>
                        </div>
                        </c:forEach>
                    </div>
                    <a href="#" class="text-orange see-all"><spring:theme code="guidedselling.guidedsellingfaqcomponent.seeall" text="See all questions"/><i class="fa fa-chevron-down"></i></a>
                </div>
                </c:if>
                </c:forEach>
            </div>

        </div>
    </div>
</div>