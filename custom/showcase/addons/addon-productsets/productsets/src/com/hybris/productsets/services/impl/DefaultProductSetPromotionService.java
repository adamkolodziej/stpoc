/**
 * 
 */
package com.hybris.productsets.services.impl;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.promotions.jalo.AbstractPromotionRestriction;
import de.hybris.platform.promotions.jalo.ProductSetPromotion;
import de.hybris.platform.promotions.model.AbstractPromotionModel;
import de.hybris.platform.promotions.model.AbstractPromotionRestrictionModel;
import de.hybris.platform.promotions.model.ProductSetPromotionModel;
import de.hybris.platform.promotions.model.PromotionGroupModel;
import de.hybris.platform.promotions.util.Helper;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.productsets.jalo.ProductSet;
import com.hybris.productsets.model.ProductSetModel;
import com.hybris.productsets.services.ProductSetPromotionService;
import com.hybris.productsets.services.dao.ProductSetDao;


/**
 * @author rmcotton
 * 
 */
public class DefaultProductSetPromotionService implements ProductSetPromotionService
{
	private static final Logger LOG = Logger.getLogger(DefaultProductSetPromotionService.class);

	private ModelService modelService;
	private ProductSetDao productSetDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.productsets.services.ProductSetPromotionService#getProductSetPromotions(java.util.Collection,
	 * com.hybris.productsets.model.ProductSetModel)
	 */
	@Override
	public List<ProductSetPromotionModel> getProductSetPromotions(final Collection<PromotionGroupModel> promotionGroups,
			final ProductSetModel productSet)
	{
		return getProductSetPromotions(promotionGroups, productSet, true, Helper.getDateNowRoundedToMinute());
	}






	/**
	 * Get the ordered list of {@link ProductSetPromotion} instances that are related to the {@link ProductSe} specified.
	 * 
	 * @param ctx
	 *           The session context
	 * @param promotionGroups
	 *           The promotion groups to evaluate
	 * @param product
	 *           The product that the promotions are related to
	 * @param evaluateRestrictions
	 *           Flag, pass false to ignore any restrictions specified on the promotions, pass true to observe the
	 *           restrictions.
	 * @param date
	 *           The date to check for promotions, typically the current date
	 * @return The list of {@link ProductSetPromotion} related to the {@link ProductSet} specified
	 */
	@Override
	public List<ProductSetPromotionModel> getProductSetPromotions(final Collection<PromotionGroupModel> promotionGroups,
			final ProductSetModel set, final boolean evaluateRestrictions, Date date)
	{
		try
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("getProductPromotions for [" + set + "] promotionGroups=[" + Helper.join(promotionGroups)
						+ "] evaluateRestrictions=[" + evaluateRestrictions + "] date=[" + date + "]");
			}

			if (promotionGroups != null && set != null && !promotionGroups.isEmpty())
			{
				if (date == null)
				{
					// Default date to now
					date = Helper.getDateNowRoundedToMinute();
				}


				final List<ProductSetPromotionModel> allPromotions = getProductSetDao().findPromotionsForSet(set, promotionGroups,
						date);

				List<ProductSetPromotionModel> availablePromotions = null;
				if (evaluateRestrictions)
				{
					availablePromotions = filterPromotionsByRestrictions(allPromotions, set, date);
				}
				else
				{
					availablePromotions = new ArrayList<ProductSetPromotionModel>(allPromotions);
				}

				// Dump out list of available promotions
				if (LOG.isDebugEnabled())
				{
					for (final ProductSetPromotionModel promotion : availablePromotions)
					{
						LOG.debug("getProductSetPromotions for [" + set + "] available promotion [" + promotion + "]");
					}
				}

				return availablePromotions;
			}
		}
		catch (final Exception ex)
		{
			LOG.error("Failed to getProductSetPromotions", ex);
		}
		return Collections.emptyList();
	}




	/**
	 * Filter a list of promotions by their restrictions
	 * 
	 * @param allPromotions
	 *           The promotions list to filter
	 * @param product
	 *           Optional product to pass when evaluating restrictions
	 * @param date
	 *           The restriction date
	 * @return The filtered list of promotions
	 */
	public <T extends AbstractPromotionModel> List<T> filterPromotionsByRestrictions(final List<T> allPromotions,
			final ProductSetModel productSet, final Date date)
	{
		final ArrayList<T> availablePromotions = new ArrayList<T>(allPromotions.size());

		for (final T promotion : allPromotions)
		{
			boolean satifiedRestrictions = true;

			final Collection<AbstractPromotionRestrictionModel> restrictions = promotion.getRestrictions();
			if (restrictions != null)
			{
				for (final AbstractPromotionRestriction restriction : getModelService().getAllSources(restrictions,
						new ArrayList<AbstractPromotionRestriction>()))
				{
					// Check restriction
					final AbstractPromotionRestriction.RestrictionResult result = restriction.evaluate(JaloSession.getCurrentSession()
							.getSessionContext(), getModelService().getAllSources(productSet.getProducts(), new ArrayList<Product>()),
							date, null);
					if (result == AbstractPromotionRestriction.RestrictionResult.DENY
							|| result == AbstractPromotionRestriction.RestrictionResult.ADJUSTED_PRODUCTS)
					{
						satifiedRestrictions = false;
						break;
					}
				}
			}

			if (satifiedRestrictions)
			{
				availablePromotions.add(promotion);
			}
		}

		return availablePromotions;
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
	 * @return the productSetDao
	 */
	public ProductSetDao getProductSetDao()
	{
		return productSetDao;
	}




	/**
	 * @param productSetDao
	 *           the productSetDao to set
	 */
	@Required
	public void setProductSetDao(final ProductSetDao productSetDao)
	{
		this.productSetDao = productSetDao;
	}

}
