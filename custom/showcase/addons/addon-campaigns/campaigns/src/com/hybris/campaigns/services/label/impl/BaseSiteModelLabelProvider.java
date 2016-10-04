/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 * 
 */
package com.hybris.campaigns.services.label.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.cockpit.services.label.AbstractModelLabelProvider;


public class BaseSiteModelLabelProvider extends AbstractModelLabelProvider<BaseSiteModel>
{
	@Override
	protected String getItemLabel(final BaseSiteModel item)
	{
		return item.getName() != null ? item.getName() : item.getUid();
	}

	@Override
	protected String getItemLabel(final BaseSiteModel item, final String languageIso)
	{
		return null;
	}

	@Override
	protected String getItemDescription(final BaseSiteModel item)
	{
		return null;
	}

	@Override
	protected String getItemDescription(final BaseSiteModel item, final String languageIso)
	{
		return null;
	}

	@Override
	protected String getIconPath(final BaseSiteModel item)
	{
		return null;
	}

	@Override
	protected String getIconPath(final BaseSiteModel item, final String languageIso)
	{
		return null;
	}
}