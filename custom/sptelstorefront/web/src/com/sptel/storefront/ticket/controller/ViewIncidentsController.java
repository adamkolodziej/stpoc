/**
 * 
 */
package com.sptel.storefront.ticket.controller;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.ticket.enums.CsTicketState;
import de.hybris.platform.ticket.model.CsTicketModel;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/viewIncident")
public class ViewIncidentsController extends AbstractSearchPageController
{
	@Resource
	private FlexibleSearchService flexibleSearchService;

	@RequestMapping(method = RequestMethod.GET)
	public String viewIncident(final Model model) throws CMSItemNotFoundException
	{
		final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery("select {pk} from {CsTicket}");
		final List<CsTicketModel> tickets = flexibleSearchService.<CsTicketModel> search(flexibleSearchQuery).getResult();
		model.addAttribute("tickets", tickets);
		storeCmsPageInModel(model, getContentPageForLabelOrId("viewIncident"));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId("viewIncident"));
		return getViewForPage(model);
	}

	@RequestMapping(value = "/sortIncidents", method = RequestMethod.POST)
	public String sortIncidents(final Model model, @RequestParam("sortCriteria") final String sortCriteria)
			throws CMSItemNotFoundException
	{
		final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery("SELECT {" + CsTicketModel.PK + "} FROM {"
				+ CsTicketModel._TYPECODE + "} WHERE {" + CsTicketModel.STATE + "}=?state");
		CsTicketState csTicketState = null;
		if (sortCriteria.equalsIgnoreCase("closed"))
		{
			csTicketState = CsTicketState.CLOSED;
		}
		else if (sortCriteria.equalsIgnoreCase("new"))
		{
			csTicketState = CsTicketState.NEW;
		}
		flexibleSearchQuery.addQueryParameter(CsTicketModel.STATE, csTicketState);
		final List<CsTicketModel> tickets = flexibleSearchService.<CsTicketModel> search(flexibleSearchQuery).getResult();
		model.addAttribute("tickets", tickets);
		storeCmsPageInModel(model, getContentPageForLabelOrId("viewIncident"));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId("viewIncident"));
		return getViewForPage(model);

	}

}
