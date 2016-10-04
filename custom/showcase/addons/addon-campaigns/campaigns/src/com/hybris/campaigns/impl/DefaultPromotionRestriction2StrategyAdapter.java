/**
 * 
 */
package com.hybris.campaigns.impl;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.promotions.jalo.AbstractPromotionRestriction;
import de.hybris.platform.promotions.jalo.AbstractPromotionRestriction.RestrictionResult;
import de.hybris.platform.promotions.model.AbstractPromotionRestrictionModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.campaigns.PromotionRestriction2StrategyAdapter;
import com.hybris.campaigns.PromotionRestrictionStrategy;
import com.hybris.campaigns.PromotionRestrictionStrategy.PromotionRestrictionStrategyResult;


/**
 * @author rmcotton
 * 
 */
public class DefaultPromotionRestriction2StrategyAdapter implements PromotionRestriction2StrategyAdapter
{
	private ModelService modelService;
	private PromotionRestrictionStrategy strategy;


	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.showcasepromotions.PromotionRestriction2StrategyAdapter#evaluate(de.hybris.platform.jalo.
	 * SessionContext, java.util.Collection, java.util.Date, de.hybris.platform.jalo.order.AbstractOrder)
	 */
	@Override
	public RestrictionResult evaluate(final SessionContext ctx, final AbstractPromotionRestriction r,
			final Collection<Product> products, final Date date, final AbstractOrder order)
	{
		final PromotionRestrictionStrategyResult result = getStrategy().evaluate(
				(AbstractPromotionRestrictionModel) getModelService().get(r),
				getModelService().getAll(products, new LinkedHashSet<ProductModel>()), date,
				order != null ? (AbstractOrderModel) getModelService().get(order) : null);

		if (result.getRestrictionResult().equals(RestrictionResult.DENY))
		{
			return RestrictionResult.DENY;
		}
		else if (result.getRestrictionResult().equals(RestrictionResult.ALLOW))
		{
			return RestrictionResult.ALLOW;
		}
		else
		{
			if (CollectionUtils.isNotEmpty(result.getProductsToAdd()))
			{
				products.addAll(getModelService().getAllSources(result.getProductsToAdd(),
						new ArrayList<Product>((result.getProductsToAdd().size()))));
			}
			if (CollectionUtils.isNotEmpty(result.getProductsToRemove()))
			{
				products.removeAll(getModelService().getAllSources(result.getProductsToRemove(),
						new ArrayList<Product>(result.getProductsToRemove().size())));
			}
			return RestrictionResult.ADJUSTED_PRODUCTS;
		}
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}


	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}


	/**
	 * @return the strategy
	 */
	public PromotionRestrictionStrategy getStrategy()
	{
		return strategy;
	}


	/**
	 * @param strategy
	 *           the strategy to set
	 */
	@Required
	public void setStrategy(final PromotionRestrictionStrategy strategy)
	{
		this.strategy = strategy;
	}
}
