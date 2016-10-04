/**
 * 
 */
package com.hybris.campaigns.promotion.queryplugin;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.promotions.jalo.PromotionGroup;

import java.util.Collection;
import java.util.Date;
import java.util.Map;


/**
 * @author rmcotton
 * 
 */
public interface PromotionQueryBuilderPlugin
{
	String getQuery(final SessionContext ctx, final JaloSession jaloSession, final Collection<PromotionGroup> promotionGroups,
			final Collection<Product> products, final Date date, final Map<String, Object> queryArgs);
}
