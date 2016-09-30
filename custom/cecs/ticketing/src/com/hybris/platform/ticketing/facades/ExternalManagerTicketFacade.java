package com.hybris.platform.ticketing.facades;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.hybris.platform.ticketing.data.ProductEntitlementData;

import com.hybris.platform.ticketing.data.TicketData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;
import de.hybris.platform.ticket.model.CsTicketModel;

/**
 * @author ddyrcz
 */
public interface ExternalManagerTicketFacade
{
	String createExternalTicket(String headline, String note);

	String getStatusForExternalTicketId(final String masterTicketId);

	CustomerModel getCustomerForExternalId(String externalCustomerId);

	CsTicketModel getTicketForExternalId(String externalTicketId);

	Collection<ProductEntitlementData> getProductEntitlementsForGiftCategory();

	void createTicket(TicketData ticket);

	TicketData updateTicket(TicketData ticketData);
}
