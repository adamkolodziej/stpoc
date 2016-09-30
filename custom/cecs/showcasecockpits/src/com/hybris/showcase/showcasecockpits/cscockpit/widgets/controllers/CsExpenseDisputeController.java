/**
 *
 */
package com.hybris.showcase.showcasecockpits.cscockpit.widgets.controllers;

import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.widgets.controllers.WidgetController;


/**
 * @author i309441
 *
 */
public interface CsExpenseDisputeController extends WidgetController
{
	/**
	 * @param expenseDispute
	 * @return boolean
	 */
	public boolean canAcceptDispute(TypedObject expenseDispute);

	public TypedObject getCurrentTicket();

	/**
	 * @param expenseDispObj
	 */
	public void acceptExpenseDispute(TypedObject expenseDispObj);

}
