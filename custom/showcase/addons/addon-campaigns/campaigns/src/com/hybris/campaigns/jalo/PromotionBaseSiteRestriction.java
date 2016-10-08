/*
 *  
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
 */
package com.hybris.campaigns.jalo;

import de.hybris.platform.basecommerce.jalo.site.BaseSite;
import de.hybris.platform.core.Registry;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.ComposedType;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hybris.campaigns.PromotionRestriction2StrategyAdapter;


public class PromotionBaseSiteRestriction extends GeneratedPromotionBaseSiteRestriction
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(PromotionBaseSiteRestriction.class.getName());

	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes)
			throws JaloBusinessException
	{
		// business code placed here will be executed before the item is created
		// then create the item
		final Item item = super.createItem(ctx, type, allAttributes);
		// business code placed here will be executed after the item was created
		// and return the item
		return item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.promotions.jalo.AbstractPromotionRestriction#evaluate(de.hybris.platform.jalo.SessionContext,
	 * java.util.Collection, java.util.Date, de.hybris.platform.jalo.order.AbstractOrder)
	 */
	@Override
	public RestrictionResult evaluate(final SessionContext ctx, final Collection<Product> products, final Date date,
			final AbstractOrder order)
	{
		final PromotionRestriction2StrategyAdapter strategy = Registry.getApplicationContext().getBean(
				"promotionBaseSiteRestrictionAdapter", PromotionRestriction2StrategyAdapter.class);
		return strategy.evaluate(ctx, this, products, date, order);
	}

	@Override
	protected Object[] getDescriptionPatternArguments(final SessionContext ctx)
	{
		return new Object[]
		{ getSiteNames() };
	}

	protected String getSiteNames()
	{
		final StringBuilder sb = new StringBuilder();

		if (getRestrictedToSites().isEmpty())
		{
			return "n/a";
		}

		for (final Iterator<BaseSite> i = getRestrictedToSites().iterator(); i.hasNext();)
		{
			final BaseSite site = i.next();
			final String siteName = site.getName();
			if (StringUtils.isBlank(siteName))
			{
				sb.append(siteName);
			}
			else
			{
				sb.append("[" + site.getUid() + "]");
			}
			if (i.hasNext())
			{
				sb.append(", ");
			}
		}
		return sb.toString();

	}
}
