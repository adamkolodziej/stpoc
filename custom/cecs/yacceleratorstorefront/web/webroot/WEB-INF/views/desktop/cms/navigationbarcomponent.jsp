<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<!-- CECS-80: Site navigation - START -->
<c:choose>
	<c:when test="${not empty component.navigationNode.children}">
		<li class="dropdown yamm-fw"><a href="#" data-toggle="dropdown" class="dropdown-toggle">${component.navigationNode.title} <b class="caret"></b></a>
			<ul class="dropdown-menu">
		       <li>
		           <!-- Content container to add padding -->
		            <div class="yamm-content">
		                <div class="row">
		                	<c:forEach items="${component.navigationNode.children}" var="child">
		                		<c:if test="${child.visible}">
									<ul class="col-sm-3 list-unstyled">
				                        <li class="header">
				                            <strong>${child.title}</strong>
				                        </li>
				                        <c:forEach items="${child.links}" step="${component.wrapAfter}" var="childlink" varStatus="i">
											<li>
												<c:forEach items="${child.links}" var="childlink" begin="${i.index}" end="${i.index + component.wrapAfter - 1}">
													<cms:component component="${childlink}" evaluateRestriction="true" element="li" />
												</c:forEach>
											</li>
		 								</c:forEach>
				                    </ul>
								</c:if>
							</c:forEach>
		                </div>
		            </div>
		        </li>
		    </ul>
		</li>
	</c:when>
	<c:otherwise>
		<li class="dropdown yamm-fw">
			<cms:component component="${component.link}" evaluateRestriction="true"/>
		</li>
	</c:otherwise>
</c:choose>
<!-- CECS-80: Site navigation - END -->
