/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2011 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package de.hybris.platform.promotioncockpit.session.impl;

import de.hybris.platform.cockpit.session.impl.DefaultSearchBrowserArea;

import org.apache.log4j.Logger;


/**
 * @author michal.milinski
 */
public class PromotioncockpitBrowserArea extends DefaultSearchBrowserArea
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(PromotioncockpitBrowserArea.class);

	@Override
	public String getDefaultBrowserClass()
	{
		return PromotioncockpitSearchBrowserModel.class.getName();
	}
}
