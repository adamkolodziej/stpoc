/**
 * 
 */
package com.hybris.productsets.promotions.queryplugin;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.promotions.jalo.PromotionGroup;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.hybris.campaigns.promotion.queryplugin.PromotionQueryBuilderPlugin;


/**
 * @author rmcotton
 * 
 */
public class ProductSetPromotionQueryPlugin implements PromotionQueryBuilderPlugin
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.campaigns.promotion.queryplugin.PromotionQueryBuilderPlugin#getQuery(de.hybris.platform.jalo.SessionContext
	 * , de.hybris.platform.jalo.JaloSession, java.util.Collection, java.util.Collection, java.util.Date, java.util.Map)
	 */
	@Override
	public String getQuery(final SessionContext ctx, final JaloSession jaloSession,
			final Collection<PromotionGroup> promotionGroups, final Collection<Product> products, final Date date,
			final Map<String, Object> queryArgs)
	{
		if (products != null && !products.isEmpty())
		{

			final String promoQuery = "{{ SELECT {psp.PK} as pk, {psp.priority} as prio FROM {ProductSetPromotion AS psp} , {ProductSetRelation AS psr} ,  {ProductSetPromotionRelation AS pspr}  "
					+ " WHERE {psr.target} in (?products) AND {psr.source} = {pspr.source} AND {pspr.target} = {psp.PK}  "
					+ " AND {psp.promotionGroup} IN (?promotionGroups) "
					+ " AND {psp.enabled} = ?true  "
					+ " AND {psp.startDate} <= ?now " + " AND ?now <= {psp.endDate}  }}  ";

			queryArgs.put("products", products);
			queryArgs.put("true", Boolean.TRUE);
			queryArgs.put("now", date);

			return promoQuery;
		}
		return null;
	}

}
