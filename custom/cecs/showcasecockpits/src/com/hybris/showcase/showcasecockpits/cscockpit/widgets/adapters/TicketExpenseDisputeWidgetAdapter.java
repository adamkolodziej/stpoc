/**
 *
 */
package com.hybris.showcase.showcasecockpits.cscockpit.widgets.adapters;

import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.widgets.models.impl.DefaultItemWidgetModel;
import de.hybris.platform.cscockpit.widgets.adapters.AbstractInitialisingWidgetAdapter;
import de.hybris.platform.ticket.model.CsTicketModel;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.platform.ticketing.model.ExpenseDisputeModel;
import com.hybris.platform.ticketing.services.ExpenseDisputeService;
import com.hybris.showcase.showcasecockpits.cscockpit.widgets.controllers.CsExpenseDisputeController;


/**
 * @author Adrian Sbarcea (SAP - i309441)
 *
 */
public class TicketExpenseDisputeWidgetAdapter
		extends AbstractInitialisingWidgetAdapter<DefaultItemWidgetModel, CsExpenseDisputeController>
{
	private ExpenseDisputeService expenseDisputeService;

	@Override
	protected boolean updateModel()
	{
		boolean changed = false;
		final TypedObject ticket = this.getWidgetController().getCurrentTicket();
		if (ticket != null)
		{
			final CsTicketModel ticketModel = (CsTicketModel) ticket.getObject();
			final ExpenseDisputeModel expDisp = getExpenseDisputeService().findExpenseDisputeByTicket(ticketModel.getTicketID());

			this.getWidgetModel().setItem(this.getCockpitTypeService().wrapItem(expDisp));
			changed = true;
		}
		return changed;
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
}
