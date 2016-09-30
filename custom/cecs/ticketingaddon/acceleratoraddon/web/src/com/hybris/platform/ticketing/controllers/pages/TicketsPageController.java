/**
 * 
 */
package com.hybris.platform.ticketing.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.Breadcrumb;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.order.OrderFacade;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.data.OrderHistoryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.ticket.enums.CsEventReason;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hybris.platform.ticketing.controllers.TicketingaddonControllerConstants;
import com.hybris.platform.ticketing.data.TicketAreaData;
import com.hybris.platform.ticketing.data.TicketData;
import com.hybris.platform.ticketing.data.TicketReasonData;
import com.hybris.platform.ticketing.enums.TicketArea;
import com.hybris.platform.ticketing.facades.ExternalManagerTicketFacade;
import com.hybris.platform.ticketing.facades.TicketFacade;
import com.hybris.platform.ticketing.forms.TicketForm;

/**
 * @author bartosz.mekier
 * 
 */
@Controller
public class TicketsPageController extends AbstractAddOnPageController
{
	private static final String REDIRECT_MY_ACCOUNT = REDIRECT_PREFIX + "/my-account";
	private static final String REDIRECT_TO_TICKETS_PAGE = REDIRECT_PREFIX + "/my-account/tickets";
	public static final int MAX_PAGE_LIMIT = 100;//should be configured
	private static final String PAGINATION_TYPE = "pagination.type";
	private static final String PAGINATION_NUMBER_OF_RESULTS_COUNT = "pagination.number.results.count";
	private static final String TICKET_CODE_PATH_VARIABLE_PATTERN = "{ticketCode:.*}";

	private static final Logger LOG = Logger.getLogger(TicketsPageController.class);

	public static enum ShowMode
	{
		Page, All
	}
	
	@Autowired
	private TicketFacade ticketFacade;

	@Autowired
	private ExternalManagerTicketFacade externalManagerTicketFacade;

	@Resource(name = "accountBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder accountBreadcrumbBuilder;

	@Resource(name = "ticketingBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder ticketingBreadcrumbBuilder;

	@Resource(name = "enumerationService")
	private EnumerationService enumerationService;

	@Resource(name = "orderFacade")
	private OrderFacade orderFacade;

	@RequestMapping(value = "/my-account/tickets", method = RequestMethod.GET)
	@RequireHardLogIn
	public String orders(@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode, final Model model)
			throws CMSItemNotFoundException
	{

		final PageableData pageableData = createPageableData(page, 5, sortCode, showMode);
		final SearchPageData<TicketData> searchPageData = ticketFacade.getTicketsForCustomer(pageableData);
		
		populateModel(model, searchPageData, showMode);

		storeCmsPageInModel(model, getContentPageForLabelOrId(TicketingaddonControllerConstants.Cms.Pages.TICKET_HISTORY_CMS_PAGE));
		setUpMetaDataForContentPage(model,
				getContentPageForLabelOrId(TicketingaddonControllerConstants.Cms.Pages.TICKET_HISTORY_CMS_PAGE));
		model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.ticketHistory"));
		model.addAttribute("metaRobots", "no-index,no-follow");
		
		return getViewForPage(model);
	}	

	@ModelAttribute("ticketReasons")
	public List<TicketReasonData> getTicketReasons()
	{
		return ticketFacade.getTicketReasonTypes();
	}

	@ModelAttribute("ticketAreas")
	public List<TicketAreaData> getTicketAreas()
	{
		return ticketFacade.getTicketAreas();
	}

	@ModelAttribute("orders")
	public List<OrderHistoryData> getOrders()
	{
		// Get all order statuses except for the Pending Quote status
		final List<OrderStatus> validStates = enumerationService.getEnumerationValues(OrderStatus._TYPECODE);
		final OrderStatus[] orderStatuses = validStates.toArray(new OrderStatus[validStates.size()]);

		return orderFacade.getOrderHistoryForStatuses(orderStatuses);
	}

	@RequestMapping(value = "/my-account/add-ticket", method = RequestMethod.GET)
	@RequireHardLogIn
	public String createTicket(final Model model) throws CMSItemNotFoundException
	{
		final TicketForm ticketForm = new TicketForm();
		ticketForm.setReason(CsEventReason.COMPLAINT.getCode());
		ticketForm.setArea(TicketArea.CUSTOMERCONVENIENCE.getCode());
		model.addAttribute("ticketForm", ticketForm);

		storeCmsPageInModel(model, getContentPageForLabelOrId(TicketingaddonControllerConstants.Cms.Pages.ADD_EDIT_TICKET_CMS_PAGE));
		setUpMetaDataForContentPage(model,
				getContentPageForLabelOrId(TicketingaddonControllerConstants.Cms.Pages.ADD_EDIT_TICKET_CMS_PAGE));
		
		final List<Breadcrumb> breadcrumbs = accountBreadcrumbBuilder.getBreadcrumbs(null);
		breadcrumbs.addAll(ticketingBreadcrumbBuilder.getBreadcrumbs("text.account.ticketHistory.createTicket"));
		
		model.addAttribute("breadcrumbs", breadcrumbs);
		model.addAttribute("metaRobots", "no-index,no-follow");
		
		return getViewForPage(model);
	}

	@RequestMapping(value = "/my-account/add-ticket", method = RequestMethod.POST)
	@RequireHardLogIn
	public String addTicket(@Valid final TicketForm ticketForm, final BindingResult bindingResult, final Model model,
			final HttpServletRequest request, final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		if (bindingResult.hasErrors())
		{
			GlobalMessages.addErrorMessage(model, "form.global.error");
			model.addAttribute("ticketForm", new TicketForm());

			storeCmsPageInModel(model, getContentPageForLabelOrId(TicketingaddonControllerConstants.Cms.Pages.ADD_EDIT_TICKET_CMS_PAGE));
			setUpMetaDataForContentPage(model,
					getContentPageForLabelOrId(TicketingaddonControllerConstants.Cms.Pages.ADD_EDIT_TICKET_CMS_PAGE));
			model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.ticketDetails"));
			model.addAttribute("metaRobots", "no-index,no-follow");
			return getViewForPage(model);
		}
		
		TicketData ticket = new TicketData();
		ticket.setReason(ticketFacade.getTicketReason(ticketForm.getReason()));
		ticket.setTicketID(ticketForm.getTicketID());

		String orderId = ticketForm.getOrderID();
		if (orderId != null)
		{
			final OrderData orderData = new OrderData();
			orderData.setCode(orderId);
			ticket.setOrder(orderData);
		}
		ticket.setHeadline(ticketForm.getHeadline());
		ticket.setNote(ticketForm.getNote());
		ticket.setArea(ticketFacade.getTicketArea(ticketForm.getArea()));

		ticket = ticketFacade.createTicket(ticket);

		try {
			externalManagerTicketFacade.createTicket(ticket);
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, "account.confirmation.ticket.added");
		} catch (RuntimeException e) {
			LOG.error("Problem while submitting ticket to C4C", e);
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.INFO_MESSAGES_HOLDER, "account.confirmation.ticket.added.local");
		}

		return REDIRECT_TO_TICKETS_PAGE;
	}

	@RequestMapping(value = "/my-account/ticket/" + TICKET_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	@RequireHardLogIn
	public String ticketDetails(@PathVariable("ticketCode") final String ticketCode, final Model model)
			throws CMSItemNotFoundException
	{
		try
		{
			TicketData ticketData = ticketFacade.getTicketForTicketID(ticketCode);

			ticketData = ticketFacade.getTicketForTicketID(ticketCode);
			ticketData = externalManagerTicketFacade.updateTicket(ticketData);
			model.addAttribute("ticketData", ticketData);
			
			storeCmsPageInModel(model, getContentPageForLabelOrId(TicketingaddonControllerConstants.Cms.Pages.TICKET_DETAILS_CMS_PAGE));
			setUpMetaDataForContentPage(model,
					getContentPageForLabelOrId(TicketingaddonControllerConstants.Cms.Pages.TICKET_DETAILS_CMS_PAGE));
			
			final List<Breadcrumb> breadcrumbs = accountBreadcrumbBuilder.getBreadcrumbs(null);
			breadcrumbs.addAll(ticketingBreadcrumbBuilder.getBreadcrumbs("text.account.ticketDetails"));


			model.addAttribute("breadcrumbs", breadcrumbs);
			model.addAttribute("metaRobots", "no-index,no-follow");
		}
		catch (final UnknownIdentifierException e)
		{
			LOG.warn("Attempted to load a ticket that does not exist or is not visible", e);
			return REDIRECT_MY_ACCOUNT;
		}
		
		return getViewForPage(model);
	}

	protected PageableData createPageableData(final int pageNumber, final int pageSize, final String sortCode,
			final ShowMode showMode)
	{
		final PageableData pageableData = new PageableData();
		pageableData.setCurrentPage(pageNumber);
		pageableData.setSort(sortCode);

		if (ShowMode.All == showMode)
		{
			pageableData.setPageSize(MAX_PAGE_LIMIT);
		}
		else
		{
			pageableData.setPageSize(pageSize);
		}
		return pageableData;
	}

	protected void populateModel(final Model model, final SearchPageData<?> searchPageData, final ShowMode showMode)
	{
		final int numberPagesShown = getSiteConfigService().getInt(PAGINATION_NUMBER_OF_RESULTS_COUNT, 5);
		final String paginationType = getSiteConfigService().getString(PAGINATION_TYPE, "pagination");
		
		model.addAttribute("numberPagesShown", Integer.valueOf(numberPagesShown));
		model.addAttribute("paginationType", paginationType);
		model.addAttribute("searchPageData", searchPageData);
		model.addAttribute("isShowAllAllowed", calculateShowAll(searchPageData, showMode));
		model.addAttribute("isShowPageAllowed", calculateShowPaged(searchPageData, showMode));
	}

	protected Boolean calculateShowAll(final SearchPageData<?> searchPageData, final ShowMode showMode)
	{
		return Boolean
				.valueOf((showMode != ShowMode.All && searchPageData.getPagination().getTotalNumberOfResults() > searchPageData
						.getPagination().getPageSize()));
	}

	protected Boolean calculateShowPaged(final SearchPageData<?> searchPageData, final ShowMode showMode)
	{
		return Boolean
				.valueOf(showMode == ShowMode.All
						&& (searchPageData.getPagination().getNumberOfPages() > 1 || searchPageData.getPagination().getPageSize() == getMaxSearchPageSize()));
	}

	protected int getMaxSearchPageSize()
	{
		return MAX_PAGE_LIMIT;
	}
	
}
