package de.hybris.platform.promotions.jalo;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.promotions.jalo.PromotionsManager.RestrictionSetResult;
import de.hybris.platform.promotions.result.PromotionEvaluationContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.hybris.productsets.jalo.ProductSet;


public abstract class ProductSetPromotion extends GeneratedProductSetPromotion
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(ProductSetPromotion.class.getName());

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


	@Override
	protected void buildDataUniqueKey(final SessionContext ctx, final StringBuilder builder)
	{

		final Set<Product> uniqueProducts = new LinkedHashSet<Product>();
		for (final ProductSet s : getProductSets(ctx))
		{
			uniqueProducts.addAll(s.getProducts(ctx));
		}


		buildDataUniqueKeyForProducts(ctx, builder, uniqueProducts);
	}

	protected RestrictionSetResult findEligibleProductsInBasket(final SessionContext ctx,
			final PromotionEvaluationContext promoContext)
	{
		final Collection<Product> products = PromotionsManager.getBaseProductsForOrder(ctx, promoContext.getOrder());
		for (final ProductSet set : getProductSets())
		{

			if (products.containsAll(set.getProducts()))
			{
				if (promoContext.getObserveRestrictions())
				{
					return PromotionsManager.getInstance().evaluateRestrictions(ctx, new ArrayList<Product>(set.getProducts()),
							promoContext.getOrder(), this, promoContext.getDate());
				}
				else
				{
					return new PromotionsManager.RestrictionSetResult(new ArrayList<Product>(set.getProducts()));
				}
			}

		}

		return new PromotionsManager.RestrictionSetResult();

	}


}
