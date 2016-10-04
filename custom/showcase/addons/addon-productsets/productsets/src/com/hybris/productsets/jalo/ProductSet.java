package com.hybris.productsets.jalo;

import de.hybris.platform.catalog.jalo.ProductReference;
import de.hybris.platform.jalo.ConsistencyCheckException;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.ComposedType;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;


public class ProductSet extends GeneratedProductSet
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(ProductSet.class.getName());

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


	/**
	 * Marks the source product which this reference (might) belong to modified. This method is called upon all
	 * modifications done to a {@link ProductReference} to allow proper product modification status.
	 */
	protected void markSourceProductsModified()
	{
		markProductsModified(getProducts());
	}

	protected void markProductsModified(final Collection<Product> products)
	{
		for (final Product product : products)
		{
			product.setModificationTime(new Date());
		}

	}

	@Override
	public void remove(final SessionContext ctx) throws ConsistencyCheckException
	{
		try
		{
			markSourceProductsModified();
		}
		catch (final Exception e)
		{
			LOG.warn("could not mark product modified due to " + e.getMessage() + " - ignored");
		}
		super.remove(ctx);
	}

	/**
	 * Overwritten to mark owning products as modified each time a property of this reference has been changed.
	 */
	@Override
	public Object setProperty(final SessionContext ctx, final String name, final Object value)
	{
		final Object prev = super.setProperty(ctx, name, value);

		// check if value has been changed at all
		if (prev != value && (prev == null || !prev.equals(value)))
		{
			markSourceProductsModified();
			// special case: product has been switched -> notify previous product as well
			if (prev instanceof Collection && name.equals(ProductSet.PRODUCTS))
			{
				final Collection<Product> disjunction = CollectionUtils.disjunction((Collection) value, (Collection) prev);
				for (final Iterator<Product> i = disjunction.iterator(); i.hasNext();)
				{
					final Product p = i.next();
					if (!((Collection) value).contains(p))
					{
						i.remove();
					}
				}
				markProductsModified(disjunction);

			}
		}


		return prev;
	}
}
