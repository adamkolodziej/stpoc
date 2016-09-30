package com.hybris.showcase.jalo.restrictions;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.ComposedType;

import org.apache.log4j.Logger;


public class CMSProductTypeRestriction extends GeneratedCMSProductTypeRestriction
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(CMSProductTypeRestriction.class.getName());

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
	 * @see de.hybris.platform.cms2.jalo.restrictions.AbstractRestriction#getDescription(de.hybris.platform.jalo.
	 * SessionContext)
	 */
	@Override
	public String getDescription(final SessionContext ctx)
	{
		final ComposedType type = getProductType(ctx);
		return "Page only applies on product type: " + type;
	}

}
