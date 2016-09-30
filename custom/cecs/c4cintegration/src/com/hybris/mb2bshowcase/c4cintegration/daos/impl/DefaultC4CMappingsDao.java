/**
 * 
 */
package com.hybris.mb2bshowcase.c4cintegration.daos.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.ticket.model.CsTicketModel;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.hybris.mb2bshowcase.c4cintegration.daos.C4CMappingsDao;


/**
 * @author I307113
 * 
 */
public class DefaultC4CMappingsDao implements C4CMappingsDao
{
	private final static String CUSTOMER_FOR_C4C_ID_QUEUE = "" + //
			"SELECT {c:pk} FROM {Customer AS c} " + //
			"WHERE  {c.c4cCustomerID} = ?c4cCustomerID"; //

	private final static String C4C_TICKET_FOR_C4C_TICKET_ID = "" + //
			"SELECT {c:pk} FROM {CsTicket AS c} " + //
			"WHERE  {c.c4cAttemptTicketId} = ?c4cAttemptTicketId"; //

	private FlexibleSearchService flexibleSearchService;

	@Override
	public CustomerModel findCustomerForC4cId(final String c4cCustomerID)
	{
		try
		{
			final Map<String, Object> parameters = ImmutableMap.of("c4cCustomerID", (Object) c4cCustomerID);
			final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(CUSTOMER_FOR_C4C_ID_QUEUE, parameters);
			return getFlexibleSearchService().searchUnique(searchQuery);
		}
		catch (final ModelNotFoundException e)
		{
			return null;
		}
	}

	@Override
	public CsTicketModel findC4cTicketForC4cTicketId(final String c4cAttemptTicketId)
	{
		try
		{
			final Map<String, Object> parameters = ImmutableMap.of("c4cAttemptTicketId", (Object) c4cAttemptTicketId);
			final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(C4C_TICKET_FOR_C4C_TICKET_ID, parameters);
			return getFlexibleSearchService().searchUnique(searchQuery);
		}
		catch (final ModelNotFoundException e)
		{
			return null;
		}
	}


	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

}
