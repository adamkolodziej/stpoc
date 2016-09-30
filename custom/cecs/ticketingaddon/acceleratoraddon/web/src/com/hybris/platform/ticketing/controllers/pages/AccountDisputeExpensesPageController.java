package com.hybris.platform.ticketing.controllers.pages;

import com.hybris.platform.ticketing.enums.TicketArea;
import com.hybris.platform.ticketing.model.ExpenseDisputeModel;
import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.ticket.enums.CsEventReason;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hybris.platform.ticketing.data.ExpenseDisputeData;
import com.hybris.platform.ticketing.data.TicketData;
import com.hybris.platform.ticketing.facades.ExpenseDisputeFacade;
import com.hybris.platform.ticketing.facades.TicketFacade;
import com.hybris.showcase.data.CurrentExpensesData;
import com.hybris.showcase.facades.ExpenseFacade;


/**
 * @author Ciprian Dragoi
 */
@Controller
@RequestMapping(AccountDisputeExpensesPageController.ROOT_REQUEST_MAPPING)
public class AccountDisputeExpensesPageController extends AbstractAddOnPageController
{

	//request mappings
	public static final String ROOT_REQUEST_MAPPING = "/my-account/expenseDisputes";
	public static final String ADD_DISPUTE_MAPPING = ROOT_REQUEST_MAPPING + "/add";

	//page ids in CMS
	private static final String ADD_DISPUTE_PAGE_ID = "accountExpenseDisputes";
	private static final String REDIRECTTO_EXPENSES_LIST_PAGE_ID = REDIRECT_PREFIX + "/my-account/expenses";

	//internal constants
	private static final Logger LOG = Logger.getLogger(AccountDisputeExpensesPageController.class);

	@Resource
	private ExpenseFacade expenseFacade;

	@Resource
	private TicketFacade ticketFacade;

	@Resource
	private ExpenseDisputeFacade expenseDisputeFacade;

	@RequestMapping(value = "/add/{expenseCode}", method = RequestMethod.GET)
	public String addDispute(@PathVariable("expenseCode") String expenseCode, final Model model, final HttpServletRequest request)
			throws CMSItemNotFoundException
	{

		CurrentExpensesData expense = expenseFacade.getExpenseForCode(expenseCode);
		model.addAttribute("expense", expense);
		model.addAttribute("disputeForm", new ExpenseDisputeData());

		storeCmsPageInModel(model, getContentPageForLabelOrId(ADD_DISPUTE_PAGE_ID));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ADD_DISPUTE_PAGE_ID));

		return getViewForPage(model);
	}

	@RequestMapping(value = "/add/{expenseCode}", method = RequestMethod.POST)
	@RequireHardLogIn
	public String saveOrUpdateDispute(@ModelAttribute("disputeForm") @Validated ExpenseDisputeData dispute,
			@PathVariable("expenseCode") String expenseCode, final BindingResult bindingResult, final Model model,
			final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException
	{

		if (bindingResult.hasErrors())
		{
			return ADD_DISPUTE_MAPPING + "/" + expenseCode;
		}
		CurrentExpensesData expense = expenseFacade.getExpenseForCode(expenseCode);

		//create ticket in other systems
		TicketData ticket = createAndStoreTicket(dispute, expense);

		//link dispute and expense together
		dispute.setExpenseCode(expenseCode);
		dispute.setCsTicketId(ticket.getTicketID());
		dispute.setCsTicket(ticket);

		final ExpenseDisputeData newDispute = expenseDisputeFacade.createDispute(dispute);

		GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.CONF_MESSAGES_HOLDER,
				"text.account.dispute.createConfirmation", new Object[]
		{ ticket.getTicketID() });

		return REDIRECTTO_EXPENSES_LIST_PAGE_ID;
	}

	/**
	 * Create a ticket data from given dispute
	 *
	 * @param dispute
	 * @param expense
	 * @return
	 */
	private TicketData createAndStoreTicket(ExpenseDisputeData dispute, CurrentExpensesData expense)
	{
		String headline = "Dispute for expense #" + expense.getCode();

		final TicketData ticket = new TicketData();

		ticket.setReason(ticketFacade.getTicketReason(CsEventReason.COMPLAINT.getCode()));
		ticket.setHeadline(headline);
		ticket.setNote(dispute.getDescription());
		ticket.setArea(ticketFacade.getTicketArea(TicketArea.EXPENSEDISPUTES.getCode()));

		return ticketFacade.createTicket(ticket);
	}

	/**
	 * Load ExpenseDisputeData from request
	 */
	@ModelAttribute("dispute")
	public ExpenseDisputeData addDispute(@RequestParam(value = "description", required = false) String description)
	{
		ExpenseDisputeData disputeData = new ExpenseDisputeData();
		disputeData.setDescription(description);
		return disputeData;
	}

	/**
	 * Populate model with expenses data
	 */
	@ModelAttribute("expense")
	public CurrentExpensesData addExpense(@RequestParam(value = "expenseCode", required = false) String code)
	{
		if (StringUtils.isEmpty(code))
			return null;
		return expenseFacade.getExpenseForCode(code);
	}

	public void setExpenseFacade(ExpenseFacade expenseFacade)
	{
		this.expenseFacade = expenseFacade;
	}

	public void setTicketFacade(TicketFacade ticketFacade)
	{
		this.ticketFacade = ticketFacade;
	}

	public void setExpenseDisputeFacade(ExpenseDisputeFacade expenseDisputeFacade)
	{
		this.expenseDisputeFacade = expenseDisputeFacade;
	}
}

