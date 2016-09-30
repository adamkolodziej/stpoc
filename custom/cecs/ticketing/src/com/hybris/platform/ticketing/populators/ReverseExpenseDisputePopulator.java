/**
 *
 */
package com.hybris.platform.ticketing.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.ticket.model.CsTicketModel;
import de.hybris.platform.ticket.service.TicketService;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.platform.ticketing.data.ExpenseDisputeData;
import com.hybris.platform.ticketing.model.ExpenseDisputeModel;
import com.hybris.showcase.model.ExpenseModel;
import com.hybris.showcase.services.ExpenseService;


public class ReverseExpenseDisputePopulator implements Populator<ExpenseDisputeData, ExpenseDisputeModel>
{
	private ExpenseService expenseService;
	private TicketService ticketService;


	@Override
	public void populate(final ExpenseDisputeData source, final ExpenseDisputeModel target) throws ConversionException
	{
		if(target.getCode() == null) {
			target.setCode(source.getCode());
		}
		
		target.setDescription(source.getDescription());

		if(source.getExpenseCode() != null) {
			final ExpenseModel expenseModel = expenseService.getExpenseForCode(source.getExpenseCode());
			target.setExpense(expenseModel);
		}

		if (source.getCsTicket() != null)
		{
			final CsTicketModel ticketModel = ticketService.getTicketForTicketId(source.getCsTicketId());
			target.setCsTicket(ticketModel);
		}
	}

	public ExpenseService getExpenseService()
	{
		return expenseService;
	}

	@Required
	public void setExpenseService(ExpenseService expenseService)
	{
		this.expenseService = expenseService;
	}

	public TicketService getTicketService()
	{
		return ticketService;
	}

	@Required
	public void setTicketService(TicketService ticketService)
	{
		this.ticketService = ticketService;
	}

}
