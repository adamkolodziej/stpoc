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
package de.hybris.platform.promotioncockpit.filters;

import de.hybris.platform.cockpit.session.BrowserFilter;


/**
 * Base class for filters that filter out snapshot promotions.
 * 
 * @author krzysztof.baranski
 */
public abstract class HistoricalSnapshotPromotionBrowserFilter implements BrowserFilter, QuerySearchProviderConditionAppender
{
	@Override
	public boolean exclude(final Object item)
	{
		return false;
	}
}
