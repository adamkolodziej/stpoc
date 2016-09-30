package com.hybris.mb2bshowcase.c4cintegration.services;



/**
 * @author ddyrcz
 */
public interface C4CTicketService
{

	String createAndSendMasterTicket(String c4cCustomerId, String headline, String note);

	String getTicketsLifeCycleStatusCode(final String masterTicketId);

}
