/**
 * 
 */
package com.hybris.campaigns.promotion.queryplugin.impl;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.TypeManager;
import de.hybris.platform.promotions.jalo.AbstractPromotion;
import de.hybris.platform.promotions.jalo.OrderPromotion;
import de.hybris.platform.promotions.jalo.PromotionGroup;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.hybris.campaigns.promotion.queryplugin.PromotionQueryBuilderPlugin;


/**
 * @author rmcotton
 * 
 */
public class OrderPromotionQueryBuilderPlugin implements PromotionQueryBuilderPlugin
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.campaigns.promotion.queryplugin.PromotionQueryBuilderPlugin#findOrderAndProductPromotionsSortByPriority
	 * (de.hybris.platform.jalo.SessionContext, de.hybris.platform.jalo.JaloSession, java.util.Collection,
	 * java.util.Collection, java.util.Date, java.util.Map)
	 */
	@Override
	public String getQuery(final SessionContext ctx, final JaloSession jaloSession,
			final Collection<PromotionGroup> promotionGroups, final Collection<Product> products, final Date date,
			final Map<String, Object> queryArgs)
	{
		// Add query for OrderPromotions
		final StringBuilder promQuery = new StringBuilder();
		promQuery.append("{{ SELECT {p3:").append(OrderPromotion.PK).append("}, {p3.").append(OrderPromotion.PRIORITY)
				.append("} as prio ");
		promQuery.append(" FROM {").append(TypeManager.getInstance().getComposedType(OrderPromotion.class).getCode())
				.append(" as p3} ");
		promQuery.append(" WHERE {p3.").append(AbstractPromotion.PROMOTIONGROUP).append("} IN (?promotionGroups) AND");
		promQuery.append(" {p3.").append(AbstractPromotion.ENABLED).append("} =?true AND ");
		promQuery.append(" {p3.").append(AbstractPromotion.STARTDATE).append("} <= ?now AND ");
		promQuery.append(" ?now <= {p3.").append(AbstractPromotion.ENDDATE).append("}").append("        }} ");


		return promQuery.toString();
	}

}
