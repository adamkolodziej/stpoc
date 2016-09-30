<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/addons/showcasecommonaddon/desktop/product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

						<c:choose>
							<c:when test="${not empty disputeExpenseCode}">
								<c:url var="ticketUrl" value="/my-account/ticket/${disputeExpenseCode}" />
								<a href="${ticketUrl}">
									<spring:theme code="text.account.currentExpenses.disputeExpense.ticket" text="Show dispute details"/>
								</a>
							</c:when>
							<c:otherwise>
								<c:url var="disputeUrl" value="/my-account/expenseDisputes/add/${expenseCode}" />
								<a href="${disputeUrl}">
									<spring:theme code="text.account.currentExpenses.disputeExpense" text="Dispute expense"/>
								</a>
							</c:otherwise>
						</c:choose>