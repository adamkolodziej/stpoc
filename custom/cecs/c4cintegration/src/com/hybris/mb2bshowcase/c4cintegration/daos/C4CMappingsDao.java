/**
 * 
 */
package com.hybris.mb2bshowcase.c4cintegration.daos;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.ticket.model.CsTicketModel;


/**
 * @author I307113
 * 
 */
public interface C4CMappingsDao
{
	CustomerModel findCustomerForC4cId(String c4cCustomerID);

	CsTicketModel findC4cTicketForC4cTicketId(String c4cAttemptTicketId);
}
