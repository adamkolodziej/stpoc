/**
 * 
 */
package com.hybris.platform.ticketing.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.entitlementservices.exception.EntitlementFacadeException;
import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.ticket.enums.CsEventReason;
import de.hybris.platform.ticket.enums.CsInterventionType;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticket.service.TicketBusinessService;
import de.hybris.platform.ticket.service.TicketException;

import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hybris.platform.ticketing.controllers.TicketingaddonControllerConstants;
import com.hybris.platform.ticketing.data.ProductEntitlementData;
import com.hybris.platform.ticketing.facades.ExternalManagerTicketFacade;
import com.hybris.showcase.emsextras.facades.GrantingEntitlementsFacade;


/**
 * @author I307113
 */
@Controller
@RequestMapping("/entitlements-console")
public class EntitlementsConsoleController extends AbstractPageController
{
	private static final Logger LOG = Logger.getLogger(EntitlementsConsoleController.class);

	@Resource(name = "externalManagerTicketFacade")
	private ExternalManagerTicketFacade externalManagerTicketFacade;

	@Resource(name = "ticketBusinessService")
	private TicketBusinessService ticketBusinessService;

	@Resource(name = "grantingEntitlementsFacade")
	private GrantingEntitlementsFacade grantingEntitlementsFacade;

	@Resource(name = "productService")
	private ProductService productService;

	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String getEntitlementsConsolePage(final Model model,
			@RequestParam(value = "customer", required = false) final String externalCustomerId,
			@RequestParam(value = "ticket", required = false) final String externalTicketId,
			@RequestParam(value = "surveyvalue", required = false) final Integer surveyValue) throws CMSItemNotFoundException
	{
		String c4cCustomerId = externalCustomerId;
		if (StringUtils.isBlank(c4cCustomerId))
		{
			CustomerModel customer = (CustomerModel) userService.getCurrentUser();
			if( StringUtils.isNotBlank(customer.getC4cCustomerID()) ) {
				c4cCustomerId = customer.getC4cCustomerID();
			} else {
				c4cCustomerId = "1001680"; // hack for joe brown
			}
		}
		final CustomerModel customer = externalManagerTicketFacade.getCustomerForExternalId(c4cCustomerId);
		Collection<ProductEntitlementData> productEntitlements = externalManagerTicketFacade
				.getProductEntitlementsForGiftCategory();

		model.addAttribute("customer", c4cCustomerId);
		model.addAttribute("ticket", externalTicketId);
		model.addAttribute("surveyValue", surveyValue);

		model.addAttribute("ycustomer", customer);
		model.addAttribute("productEntitlements", productEntitlements);

		return TicketingaddonControllerConstants.Views.Pages.EntitlementConsole;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String getEntitlementsConsolePage(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, @RequestParam(value = "entitlementId") final String entitlementId,
			@RequestParam(value = "entitlementName") final String entitlementName,
			@RequestParam(value = "customer") final String externalCustomerId,
			@RequestParam(value = "ticket") final String externalTicketId,
			@RequestParam(value = "productCode") final String productCode) throws CMSItemNotFoundException
	{
		final CustomerModel customer = externalManagerTicketFacade.getCustomerForExternalId(externalCustomerId);
		final CsTicketModel ticket = externalManagerTicketFacade.getTicketForExternalId(externalTicketId);

		final String resolvedUrl = response.encodeURL(request.getContextPath() + "/p/" + productCode);

		String[] ticketNoteMessageParams =
		{ resolvedUrl, entitlementName };
		String ticketNote = getMessageSource().getMessage("EntitlementsConsoleController.getEntitlementsConsolePage.ticketNote",
				ticketNoteMessageParams, getI18nService().getCurrentLocale());
		String grantEntitlementResponse = "";

		Collection<ProductEntitlementModel> productEntitlementModel = productService.getProductForCode(productCode)
				.getProductEntitlements();
		for (ProductEntitlementModel productEntitlement : productEntitlementModel)
		{
			try
			{
				grantingEntitlementsFacade.grantProductEntitlement(customer.getUid(), productEntitlement);
				grantEntitlementResponse = ticketNote;
			}
			catch (EntitlementFacadeException e)
			{
				LOG.error("Entitlements are no granted", e);
				// workaround for C4C mashup issue: there is a problem to have dynamically injected ticket id
				//grantEntitlementResponse = e.getLocalizedMessage();
			}
		}

		try
		{
			ticketBusinessService.addNoteToTicket(ticket, CsInterventionType.CALL, CsEventReason.UPDATE, ticketNote, null);
			ticketBusinessService.updateTicket(ticket);
		}
		catch (IllegalArgumentException e)
		{
			LOG.error("Illegal Argument", e);
			// workaround for C4C mashup issue: there is a problem to have dynamically injected ticket id
			//grantEntitlementResponse = e.getLocalizedMessage();
		}
		catch (TicketException e)
		{
			LOG.error("Ticket Exception", e);
			// workaround for C4C mashup issue: there is a problem to have dynamically injected ticket id
			//grantEntitlementResponse = e.getLocalizedMessage();
		}

		return grantEntitlementResponse;
	}
}
