/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.hybris.platform.ticketing.controllers;

import com.hybris.platform.ticketing.model.DisputeExpenseLinkActionModel;


/**
 */
public interface TicketingaddonControllerConstants
{
	public static final String _Prefix = "/view/";
	public static final String _Suffix = "Controller";

	interface Cms
	{

		interface Pages
		{
			/** ticket history cms content page code */
			String TICKET_HISTORY_CMS_PAGE = "tickets";
			String ADD_EDIT_TICKET_CMS_PAGE = "add-edit-ticket";
			String TICKET_DETAILS_CMS_PAGE = "ticket-details";
		}
	}

	interface Views
	{
		interface Cms
		{
		}

		interface Pages
		{
			// CECS-338 Implement a Mashup for C4C allowing to grant entitlement for specific movie
			String EntitlementConsole = "addon:/ticketingaddon/pages/entitlementConsole";
		}
	}
}
