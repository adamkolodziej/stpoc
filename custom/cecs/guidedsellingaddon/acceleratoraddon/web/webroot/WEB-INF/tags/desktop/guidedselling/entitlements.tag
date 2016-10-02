<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


	<c:if test="${not empty product.entitlements}">
				<ul>
					<c:forEach var="ent" items="${product.entitlements}" varStatus="loop">
						<c:set var="liClass" value="collapse entitlement-${product.code}" />
						<li class="${loop.index > 2 ? liClass : ''}">
							<c:set var="name" value="${ent.name}" />
							<c:if test="${not empty ent.description}">
								<c:set var="name" value="${ent.description}" />
							</c:if>

							<c:if test="${ent.quantity eq 0}">
								<span title="${ent.name} (${ent.id})">${name}</span>
							</c:if>
							<c:if test="${ent.quantity ne 0}">
								<span title="${ent.name} (${ent.id})">${name}</span>
	                            <span class="label label-primary">
	                                ${ent.quantity}
	                                <span>
											${ent.quantity > 1 ? ent.usageUnit.namePlural : ent.usageUnit.name}
									</span>
	                            </span>
							</c:if>
							<c:if test="${not empty ent.usageCharge}">
	                            <span>-&nbsp${ent.usageCharge.usageUnit.name}
	                                <c:if test="${not empty ent.usageCharge.usageChargeEntries}">
										<strong>&nbsp${ent.usageCharge.usageChargeEntries[0].price.formattedValue}</strong>
									</c:if>
	                            </span>
							</c:if>
						</li>
					</c:forEach>
				</ul>
	</c:if>
