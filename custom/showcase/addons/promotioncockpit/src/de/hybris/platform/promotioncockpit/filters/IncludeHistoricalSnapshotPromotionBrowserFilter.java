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

import de.hybris.platform.cockpit.model.search.Query;
import de.hybris.platform.core.GenericCondition;
import de.hybris.platform.core.GenericQuery;
import de.hybris.platform.util.localization.Localization;

import java.util.List;


/**
 * This filter doesn't filter out any results. It's implemented to display filter in UI to disable
 * {@code ExcludeHistoricalSnapshotPromotionBrowserFilter}.
 * 
 * @author krzysztof.baranski
 */
public class IncludeHistoricalSnapshotPromotionBrowserFilter extends HistoricalSnapshotPromotionBrowserFilter
{
	private static final String LABEL_KEY = "include.historical.snapshot.promotion.label.key";

	@Override
	public String getLabel()
	{
		return Localization.getLocalizedString(LABEL_KEY);
	}

	@Override
	public void filterQuery(final Query query)
	{
		// purposely empty method - we don't want to apply any filtering
	}

	@Override
	public void appendConditions(final List<GenericCondition> conditions, final Query query, final GenericQuery genQuery)
	{
		// purposely empty method - we don't want to apply any filtering
	}
}