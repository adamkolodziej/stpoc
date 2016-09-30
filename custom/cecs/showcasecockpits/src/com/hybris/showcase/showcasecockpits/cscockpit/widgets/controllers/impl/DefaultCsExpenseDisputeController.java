/**
 *
 */
package com.hybris.showcase.showcasecockpits.cscockpit.widgets.controllers.impl;

import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cscockpit.widgets.controllers.impl.AbstractCallContextDependantController;
import de.hybris.platform.cscockpit.widgets.events.CsCockpitEvent;
import de.hybris.platform.ticket.enums.CsInterventionType;
import de.hybris.platform.ticket.enums.CsResolutionType;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticket.service.TicketBusinessService;
import de.hybris.platform.ticket.service.TicketException;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.platform.ticketing.model.ExpenseDisputeModel;
import com.hybris.platform.ticketing.services.ExpenseDisputeService;
import com.hybris.showcase.showcasecockpits.cscockpit.widgets.controllers.CsExpenseDisputeController;


/**
 * @author Adrian Sbarcea (SAP - i309441)
 *
 */
public class DefaultCsExpenseDisputeController extends AbstractCallContextDependantController
		implements CsExpenseDisputeController
{
	private static final Logger LOG = Logger.getLogger(DefaultCsExpenseDisputeController.class);

	private ExpenseDisputeService expenseDisputeService;
	private TicketBusinessService ticketBusinessService;

	@Override
	public void dispatchEvent(final String context, final Object source, final Map<String, Object> data)
	{
		this.dispatchEvent(CONTROLLER_CONTEXT, new CsCockpitEvent(source, context, data));
	}


	public TypedObject getCurrentTicket()
	{
		return getCallContextController().getCurrentTicket();
	}

	public void acceptExpenseDispute(final TypedObject expenseDispute)
	{
		//set the Expense to amount = 0
		getExpenseDisputeService().acceptExpenseDispute((ExpenseDisputeModel) expenseDispute.getObject());

		//resolve the ticket
		try
		{
			getTicketBusinessService().resolveTicket((CsTicketModel) getCurrentTicket().getObject(), CsInterventionType.EMAIL,
					CsResolutionType.CLOSED, "Ticket was closed because the Expense Dispute was accepted by the CS Agent.");
		}
		catch (final TicketException e)
		{
			LOG.error("Cannot resolve Ticket when accepting the Expense Dispute!", e);
			e.printStackTrace();
		}
	}

	@Override
	public boolean canAcceptDispute(final TypedObject expenseDispute)
	{
		//we need to check that the current ticket has an expense dispute associated to it
		//and that this ticket is resolvable, in order to display the Accept Expense Dispute button
		final TypedObject ticket = getCurrentTicket();
		if ((ticket != null && ticket.getObject() instanceof CsTicketModel)
				&& (expenseDispute != null && expenseDispute.getObject() instanceof ExpenseDisputeModel))
		{
			final CsTicketModel csTicket = (CsTicketModel) ticket.getObject();
			return this.getTicketBusinessService().isTicketResolvable(csTicket);
		}
		return false;
	}


	/**
	 * @return the expenseDisputeService
	 */
	public ExpenseDisputeService getExpenseDisputeService()
	{
		return expenseDisputeService;
	}


	/**
	 * @param expenseDisputeService
	 *           the expenseDisputeService to set
	 */
	@Required
	public void setExpenseDisputeService(final ExpenseDisputeService expenseDisputeService)
	{
		this.expenseDisputeService = expenseDisputeService;
	}


	/**
	 * @return the ticketBusinessService
	 */
	public TicketBusinessService getTicketBusinessService()
	{
		return ticketBusinessService;
	}


	/**
	 * @param ticketBusinessService
	 *           the ticketBusinessService to set
	 */
	@Required
	public void setTicketBusinessService(final TicketBusinessService ticketBusinessService)
	{
		this.ticketBusinessService = ticketBusinessService;
	}


}
