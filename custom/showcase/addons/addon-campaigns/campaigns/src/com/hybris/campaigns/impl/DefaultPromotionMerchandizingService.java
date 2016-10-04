/**
 * 
 */
package com.hybris.campaigns.impl;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.order.CartService;
import de.hybris.platform.promotions.PromotionsService;
import de.hybris.platform.promotions.jalo.AbstractPromotion;
import de.hybris.platform.promotions.jalo.AbstractPromotionRestriction;
import de.hybris.platform.promotions.jalo.PromotionResult;
import de.hybris.platform.promotions.model.AbstractPromotionModel;
import de.hybris.platform.promotions.model.OrderPromotionModel;
import de.hybris.platform.promotions.model.ProductOneToOnePerfectPartnerPromotionModel;
import de.hybris.platform.promotions.model.ProductPerfectPartnerBundlePromotionModel;
import de.hybris.platform.promotions.model.ProductPerfectPartnerPromotionModel;
import de.hybris.platform.promotions.model.ProductPromotionModel;
import de.hybris.platform.promotions.result.PromotionOrderResults;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.time.TimeService;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.campaigns.PromotionMerchandizingService;
import com.hybris.campaigns.order.GetPromotionGroupsStrategy;


/**
 * @author rmcotton
 * 
 */
public class DefaultPromotionMerchandizingService implements PromotionMerchandizingService
{

	private ModelService modelService;
	private CartService cartService;
	private PromotionsService promotionService;
	private TimeService timeService;
	private GetPromotionGroupsStrategy promotionGroupsStrategy;


	@Override
	public <T extends AbstractPromotionModel> List<T> filterPromotionsByRestrictions(final List<T> allPromotions,
			final List<ProductModel> products)
	{
		return filterPromotionsByRestrictions(allPromotions, products, geNowRoundedToMinute());
	}

	@Override
	public <T extends AbstractPromotionModel> List<T> filterPromotionsByRestrictions(final List<T> allPromotions)
	{
		return filterPromotionsByRestrictions(allPromotions, Collections.<ProductModel> emptyList(), geNowRoundedToMinute());
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
	protected <T extends AbstractPromotionModel> List<T> filterPromotionsByRestrictions(final List<T> allPromotions,
			final List<ProductModel> applicableProducts, final Date date)
	{
		final List<T> availablePromotions = new ArrayList<T>(allPromotions.size());

		for (final T model : allPromotions)
		{
			if (!Boolean.TRUE.equals(model.getEnabled()))
			{
				continue;
			}

			boolean satifiedRestrictions = true;

			final AbstractPromotion promotion = getModelService().getSource(model);


			final Collection<AbstractPromotionRestriction> restrictions = promotion.getRestrictions();
			if (restrictions != null)
			{
				for (final AbstractPromotionRestriction restriction : restrictions)
				{
					// Check restriction
					final AbstractPromotionRestriction.RestrictionResult result = restriction.evaluate(JaloSession.getCurrentSession()
							.getSessionContext(), getModelService().getAllSources(applicableProducts, new LinkedHashSet<Product>()),
							date, null);
					if (result == AbstractPromotionRestriction.RestrictionResult.DENY)
					{
						satifiedRestrictions = false;
						break;
					}

				}
			}

			if (satifiedRestrictions)
			{
				availablePromotions.add((T) getModelService().get(promotion));
			}

		}

		return availablePromotions;
	}

	@Override
	public Set<ProductModel> getApplicableProductsFromPromotion(final ProductPromotionModel promotion)
	{
		final Set<ProductModel> products = new HashSet<ProductModel>();
		products.addAll(promotion.getProducts());
		for (final CategoryModel cm : promotion.getCategories())
		{
			products.addAll(cm.getProducts());
		}

		if (promotion instanceof ProductOneToOnePerfectPartnerPromotionModel)
		{
			products.add(((ProductOneToOnePerfectPartnerPromotionModel) promotion).getBaseProduct());
			products.add(((ProductOneToOnePerfectPartnerPromotionModel) promotion).getPartnerProduct());
		}
		else if (promotion instanceof ProductPerfectPartnerBundlePromotionModel)
		{
			products.add(((ProductPerfectPartnerBundlePromotionModel) promotion).getBaseProduct());
			products.addAll(((ProductPerfectPartnerBundlePromotionModel) promotion).getPartnerProducts());
		}

		else if (promotion instanceof ProductPerfectPartnerPromotionModel)
		{
			products.addAll(((ProductPerfectPartnerPromotionModel) promotion).getPartnerProducts());
		}

		return products;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.showcasepromotions.PromotionMerchandizingService#isFuturePromotion(de.hybris.platform.promotions
	 * .model.AbstractPromotionModel)
	 */
	@Override
	public boolean isFuturePromotion(final AbstractPromotionModel promotion)
	{
		final Date now = getNow();
		if (promotion.getEndDate() != null && promotion.getEndDate().before(now))
		{
			return false;
		}
		if (promotion.getStartDate() != null && promotion.getStartDate().after(now))
		{
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.showcasepromotions.PromotionMerchandizingService#isPastPromotion(de.hybris.platform.promotions
	 * .model.AbstractPromotionModel)
	 */
	@Override
	public boolean isPastPromotion(final AbstractPromotionModel promotion)
	{
		final Date now = getNow();
		if (promotion.getEndDate() != null && promotion.getEndDate().before(now))
		{
			return true;
		}
		if (promotion.getStartDate() != null && promotion.getStartDate().before(now))
		{
			return false;
		}
		return false;

	}

	protected Date getNow()
	{
		return getTimeService().getCurrentTime();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.showcasepromotions.PromotionMerchandizingService#isAppliedPromotion(de.hybris.platform.promotions
	 * .model.AbstractPromotionModel)
	 */
	@Override
	public boolean isAppliedPromotion(final AbstractPromotionModel promotion)
	{
		if (getCartService().hasSessionCart())
		{
			final PromotionOrderResults results = getPromotionsService().getPromotionResults(getCartService().getSessionCart());
			if (promotion instanceof OrderPromotionModel)
			{

				return containsPromotion(results.getAppliedOrderPromotions(), promotion);
			}
			else
			{
				return containsPromotion(results.getAppliedProductPromotions(), promotion);
			}
		}

		return false;
	}

	protected boolean containsPromotion(final List<PromotionResult> results, final AbstractPromotionModel promotion)
	{
		final AbstractPromotion source = getModelService().getSource(promotion);
		for (final PromotionResult result : results)
		{
			if (result.getPromotion().equals(source))
			{
				return true;
			}
		}
		return false;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.showcasepromotions.PromotionMerchandizingService#getProductPromotions(de.hybris.platform.core
	 * .model.product.ProductModel, boolean)
	 */
	@Override
	public Collection<ProductPromotionModel> getProductPromotions(final ProductModel product, final boolean evaluateRestrictions)
	{
		final List<ProductModel> productBranch = new LinkedList<ProductModel>();
		recursiveAddProductAndParents(product, productBranch);
		final LinkedHashSet<ProductPromotionModel> promotions = new LinkedHashSet<ProductPromotionModel>();
		for (final ProductModel p : productBranch)
		{
			promotions.addAll(getPromotionsService().getProductPromotions(getPromotionGroupsStrategy().getPromotionGroups(), p,
					evaluateRestrictions, geNowRoundedToMinute()));
		}
		return promotions;
	}

	protected void recursiveAddProductAndParents(final ProductModel productModel, final List<ProductModel> list)
	{
		if (list.contains(productModel))
		{
			return;
		}
		list.add(productModel);
		if (productModel instanceof VariantProductModel)
		{
			recursiveAddProductAndParents(((VariantProductModel) productModel).getBaseProduct(), list);
		}
	}

	/**
	 * returns a <code>Date</code> of the current minute to assure that queries can be cached for up to 1 minute.
	 * 
	 * @return the truncated Date
	 */
	protected Date geNowRoundedToMinute()
	{
		final Calendar now = Calendar.getInstance();
		now.setTime(getNow());
		now.set(Calendar.MILLISECOND, 0);
		now.set(Calendar.SECOND, 0);
		return now.getTime();
	}

	/**
	 * @return the commerceCartService
	 */
	public CartService getCartService()
	{
		return cartService;
	}

	/**
	 * @param cartService
	 *           the commerceCartService to set
	 */
	@Required
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}

	/**
	 * @return the timeService
	 */
	public TimeService getTimeService()
	{
		return timeService;
	}

	/**
	 * @param timeService
	 *           the timeService to set
	 */
	@Required
	public void setTimeService(final TimeService timeService)
	{
		this.timeService = timeService;
	}

	/**
	 * @return the promotionService
	 */
	public PromotionsService getPromotionsService()
	{
		return promotionService;
	}

	/**
	 * @param promotionService
	 *           the promotionService to set
	 */
	@Required
	public void setPromotionsService(final PromotionsService promotionService)
	{
		this.promotionService = promotionService;
	}

	/**
	 * @return the promotionGroupsStrategy
	 */
	public GetPromotionGroupsStrategy getPromotionGroupsStrategy()
	{
		return promotionGroupsStrategy;
	}

	/**
	 * @param promotionGroupsStrategy
	 *           the promotionGroupsStrategy to set
	 */
	@Required
	public void setPromotionGroupsStrategy(final GetPromotionGroupsStrategy promotionGroupsStrategy)
	{
		this.promotionGroupsStrategy = promotionGroupsStrategy;
	}


}
