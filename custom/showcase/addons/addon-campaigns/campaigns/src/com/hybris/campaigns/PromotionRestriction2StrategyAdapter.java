/**
 * 
 */
package com.hybris.campaigns;

import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.promotions.jalo.AbstractPromotionRestriction;
import de.hybris.platform.promotions.jalo.AbstractPromotionRestriction.RestrictionResult;

import java.util.Collection;
import java.util.Date;


/**
 * @author rmcotton
 * 
 */
public interface PromotionRestriction2StrategyAdapter
{
	public RestrictionResult evaluate(final SessionContext ctx, final AbstractPromotionRestriction restriction,
			final Collection<Product> products, final Date date, final AbstractOrder order);
}
