/**
 * 
 */
package com.hybris.platform.ticketing.facades;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

import java.util.List;

import com.hybris.platform.ticketing.data.TicketAreaData;
import com.hybris.platform.ticketing.data.TicketData;
import com.hybris.platform.ticketing.data.TicketReasonData;


/**
 * @author bartosz.mekier
 */
public interface TicketFacade
{
	SearchPageData<TicketData> getTicketsForCustomer(final String userId, final PageableData pageableData);

	SearchPageData<TicketData> getTicketsForCustomer(PageableData pageableData);

	TicketData createTicket(final TicketData ticket);

	TicketData getTicketForTicketID(String ticketID);

	TicketReasonData getTicketReason(String reason);

	TicketAreaData getTicketArea(String area);

	List<TicketAreaData> getTicketAreas();

	List<TicketReasonData> getTicketReasonTypes();
}
