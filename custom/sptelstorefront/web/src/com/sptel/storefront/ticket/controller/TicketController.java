/**
 * 
 */
package com.sptel.storefront.ticket.controller;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.ticket.enums.CsTicketCategory;
import de.hybris.platform.ticket.enums.CsTicketPriority;
import de.hybris.platform.ticket.enums.CsTicketState;
import de.hybris.platform.ticket.model.CsTicketModel;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sptel.core.model.CompanyDetailsModel;
import com.sptel.core.model.IncidentDetailsModel;


@Controller
@RequestMapping(value = "/createIncident")
public class TicketController extends AbstractSearchPageController
{
	@Resource
	private ModelService modelService;

	@RequestMapping(method = RequestMethod.GET)
	public String createIncident(final Model model) throws CMSItemNotFoundException
	{
		final CsTicketModel ticket = (CsTicketModel) modelService.create(CsTicketModel.class);
		model.addAttribute("ticket", ticket);
		storeCmsPageInModel(model, getContentPageForLabelOrId("createIncident"));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId("createIncident"));
		return getViewForPage(model);
	}


	@RequestMapping(value = "/createTicket", method = RequestMethod.POST)
	public String createTicket(final Model model, final HttpServletRequest request) throws CMSItemNotFoundException
	{
		System.out.print("Ticket creation logic here");
		final CompanyDetailsModel companyDetails = modelService.create(CompanyDetailsModel.class);
		companyDetails.setCompanyName(request.getParameter("companyName"));
		companyDetails.setContactName(request.getParameter("contactName"));
		companyDetails.setPhoneNumber(request.getParameter("phoneNumber"));
		companyDetails.setEmailID(request.getParameter("emailID"));
		final IncidentDetailsModel incidentDetails = modelService.create(IncidentDetailsModel.class);
		incidentDetails.setCustomerRef(request.getParameter("customerReference"));
		incidentDetails.setDetailDescription(request.getParameter("desc"));
		incidentDetails.setIntrusiveTestPossible(request.getParameter("test"));
		incidentDetails.setRepeatIssue(request.getParameter("repeat"));
		incidentDetails.setRecentChanges(request.getParameter("changes"));
		incidentDetails.setProductsImpacted(request.getParameter("productsImpacted"));
		incidentDetails.setSymptoms(request.getParameter("symptoms"));
		incidentDetails.setTicketType(request.getParameter("ticketType"));
		final CsTicketModel ticket = (CsTicketModel) modelService.create(CsTicketModel.class);
		ticket.setCategory(CsTicketCategory.INCIDENT);
		ticket.setPriority(CsTicketPriority.HIGH);
		ticket.setCreationtime(new Date());
		ticket.setHeadline("Incident ticket");
		ticket.setState(CsTicketState.NEW);
		ticket.setCompanyDetails(companyDetails);
		ticket.setIncidentDetails(incidentDetails);
		modelService.save(ticket);
		model.addAttribute("ticket", ticket);
		storeCmsPageInModel(model, getContentPageForLabelOrId("ticketSuccess"));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId("ticketSuccess"));
		return getViewForPage(model);
	}

}
