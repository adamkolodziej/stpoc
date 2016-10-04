/**
 * 
 */
package com.hybris.campaigns.promotion.queryplugin.impl;

import de.hybris.platform.category.jalo.Category;
import de.hybris.platform.category.jalo.CategoryManager;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.link.Link;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.TypeManager;
import de.hybris.platform.promotions.constants.PromotionsConstants;
import de.hybris.platform.promotions.jalo.AbstractPromotion;
import de.hybris.platform.promotions.jalo.ProductPromotion;
import de.hybris.platform.promotions.jalo.PromotionGroup;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.hybris.campaigns.promotion.queryplugin.PromotionQueryBuilderPlugin;


/**
 * @author rmcotton
 * 
 */
public class ProductPromotionQueryBuilderPlugin implements PromotionQueryBuilderPlugin
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.campaigns.promotion.queryplugin.PromotionQueryBuilderPlugin#findOrderAndProductPromotionsSortByPriority
	 * (de.hybris.platform.jalo.SessionContext, de.hybris.platform.jalo.JaloSession, java.util.Collection,
	 * java.util.Collection, java.util.Date)
	 */
	@Override
	public String getQuery(final SessionContext ctx, final JaloSession jaloSession,
			final Collection<PromotionGroup> promotionGroups, final Collection<Product> products, final Date date,
			final Map<String, Object> args)
	{
		if (products != null && !products.isEmpty())
		{
			// Build query to find all distinct list of all promotions that are related to
			// the source product either via the ProductPromotionRelation or via the CategoryPromotionRelation.
			// Filter the results so that only Promotions with Start and End dates valid for time 'now'
			// order by Priority with the highest value first
			final StringBuilder promQuery = new StringBuilder();
			promQuery.append(" {{ SELECT {p.").append(ProductPromotion.PK).append("} as pk, ");
			promQuery.append(" {p.").append(ProductPromotion.PRIORITY).append("} as prio FROM");
			promQuery.append(" {").append(TypeManager.getInstance().getComposedType(ProductPromotion.class).getCode())
					.append(" AS p");
			promQuery.append(" JOIN ").append(PromotionsConstants.Relations.PRODUCTPROMOTIONRELATION).append(" AS p2p ");
			promQuery.append(" ON {p.").append(ProductPromotion.PK).append("} = {p2p.").append(Link.TARGET).append("} ");
			promQuery.append(" AND {p2p.").append(Link.SOURCE).append("} in (?products) } ");
			promQuery.append(" WHERE {p.").append(AbstractPromotion.PROMOTIONGROUP).append("} IN (?promotionGroups) AND");
			promQuery.append(" {p.").append(AbstractPromotion.ENABLED).append("} =?true AND ");
			promQuery.append(" {p.").append(AbstractPromotion.STARTDATE).append("} <= ?now AND ");
			promQuery.append(" ?now <= {p.").append(AbstractPromotion.ENDDATE).append("} }}");

			args.put("products", products);


			final Set<Category> productCategories = new HashSet<Category>();
			for (final Product product : products)
			{
				for (final Category cat : CategoryManager.getInstance().getCategoriesByProduct(product, ctx))
				{
					productCategories.add(cat);
					productCategories.addAll(cat.getAllSupercategories(ctx));
				}
			}

			if (!productCategories.isEmpty())
			{
				promQuery.append(" UNION ");

				promQuery.append(" {{ SELECT {p.").append(ProductPromotion.PK).append("} as pk, ");
				promQuery.append(" {p.").append(ProductPromotion.PRIORITY).append("} as prio FROM");
				promQuery.append(" {").append(TypeManager.getInstance().getComposedType(ProductPromotion.class).getCode())
						.append(" AS p");
				promQuery.append(" JOIN ").append(PromotionsConstants.Relations.CATEGORYPROMOTIONRELATION).append(" AS c2p ");
				promQuery.append(" ON {p.").append(ProductPromotion.PK).append("} = {c2p.").append(Link.TARGET).append("} ");
				promQuery.append(" AND {c2p.").append(Link.SOURCE).append("} IN (?productCategories) } ");
				promQuery.append(" WHERE {p.").append(AbstractPromotion.PROMOTIONGROUP).append("} IN (?promotionGroups) AND");
				promQuery.append(" {p.").append(AbstractPromotion.ENABLED).append("} =?true AND ");
				promQuery.append(" {p.").append(AbstractPromotion.STARTDATE).append("} <= ?now AND ");
				promQuery.append(" ?now <= {p.").append(AbstractPromotion.ENDDATE).append("} }}");

				// Find all the categories that the product is in, and all of their super categories
				args.put("productCategories", productCategories);
			}
			return promQuery.toString();
		}
		return null;
	}
}
