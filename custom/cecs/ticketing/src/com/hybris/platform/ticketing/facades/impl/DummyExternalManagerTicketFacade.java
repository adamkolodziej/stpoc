package com.hybris.platform.ticketing.facades.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import com.hybris.platform.ticketing.data.ProductEntitlementData;
import com.hybris.platform.ticketing.data.TicketData;
import com.hybris.platform.ticketing.facades.ExternalManagerTicketFacade;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;
import de.hybris.platform.ticket.model.CsTicketModel;


public class DummyExternalManagerTicketFacade implements ExternalManagerTicketFacade {

	@Override
	public String createExternalTicket(String headline, String note) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStatusForExternalTicketId(String masterTicketId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerModel getCustomerForExternalId(String c4cCustomerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CsTicketModel getTicketForExternalId(String c4cAttemptTicketId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ProductEntitlementData> getProductEntitlementsForGiftCategory() {
		return Collections.emptyList();
	}

	@Override
	public void createTicket(TicketData ticket) {

	}

	@Override
	public TicketData updateTicket(TicketData ticketData) {
		return ticketData;
	}
}
