/**
 * 
 */
package com.hybris.productsets.converters.populator;

import de.hybris.platform.commercefacades.product.data.PromotionData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.Currency;
import de.hybris.platform.order.CartService;
import de.hybris.platform.promotions.PromotionsService;
import de.hybris.platform.promotions.jalo.AbstractPromotion;
import de.hybris.platform.promotions.jalo.ProductSetFixedPricePromotion;
import de.hybris.platform.promotions.jalo.PromotionResult;
import de.hybris.platform.promotions.model.AbstractPromotionModel;
import de.hybris.platform.promotions.model.ProductSetFixedPricePromotionModel;
import de.hybris.platform.promotions.model.ProductSetPromotionModel;
import de.hybris.platform.promotions.model.PromotionPriceRowModel;
import de.hybris.platform.promotions.result.PromotionOrderResults;
import de.hybris.platform.promotions.util.Helper;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.util.Assert;

import com.hybris.productsets.model.ProductSetModel;



/**
 * @author dilic
 * 
 */
public class DefaultProductSetPromotionsPropertiesPopulator implements Populator<ProductSetPromotionModel, PromotionData>
{

	private CartService cartService;
	private ModelService modelService;
	private PromotionsService promotionService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final ProductSetPromotionModel source, final PromotionData target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setCode(source.getCode());
		target.setTitle(getTitleMessage(source));
		target.setEndDate(source.getEndDate());
		target.setDescription(source.getDescription());
		target.setPromotionType(source.getPromotionType());

		if (source instanceof ProductSetFixedPricePromotionModel)
		{
			processPromotionMessages(source, target);
		}

	}

	protected void processPromotionMessages(final AbstractPromotionModel source, final PromotionData prototype)
	{
		if (getCartService().hasSessionCart())
		{
			final CartModel cartModel = getCartService().getSessionCart();
			if (cartModel != null)
			{
				final AbstractPromotion promotion = getModelService().getSource(source);

				final PromotionOrderResults promoOrderResults = getPromotionService().getPromotionResults(cartModel);
				if (promoOrderResults != null)
				{
					prototype.setCouldFireMessages(getCouldFirePromotionsMessages(promoOrderResults, promotion));
					prototype.setFiredMessages(getFiredPromotionsMessages(promoOrderResults, promotion));
				}
			}
		}
	}

	protected List<String> getCouldFirePromotionsMessages(final PromotionOrderResults promoOrderResults,
			final AbstractPromotion promotion)
	{
		final List<String> descriptions = new LinkedList<String>();

		addDescriptions(descriptions, filter(promoOrderResults.getAllResults(), promotion), true);

		return descriptions;
	}

	protected List<String> getFiredPromotionsMessages(final PromotionOrderResults promoOrderResults,
			final AbstractPromotion promotion)
	{
		final List<String> descriptions = new LinkedList<String>();

		addDescriptions(descriptions, filter(promoOrderResults.getAllResults(), promotion), false);

		return descriptions;
	}

	protected void addDescriptions(final List<String> descriptions, final List<PromotionResult> promotionResults,
			final boolean couldFire)
	{
		if (promotionResults != null)
		{
			for (final PromotionResult promoResult : promotionResults)
			{
				if (promoResult.getPromotion() instanceof ProductSetFixedPricePromotion)
				{
					if (promoResult.getCouldFire() == couldFire)
					{
						descriptions.add(promoResult.getDescription());
					}
				}
			}
		}
	}

	protected List<PromotionResult> filter(final List<PromotionResult> results, final AbstractPromotion promotion)
	{
		final List<PromotionResult> filteredResults = new LinkedList<PromotionResult>();
		for (final PromotionResult result : results)
		{
			if (result.getPromotion().equals(promotion))
			{
				filteredResults.add(result);
			}
		}
		return filteredResults;
	}

	protected String getTitleMessage(final AbstractPromotionModel promotionModel)
	{
		final String name = promotionModel.getName();
		final String title = promotionModel.getTitle();
		String descriptions = null;

		if (name != null || title != null)
		{
			final SessionContext ctx = JaloSession.getCurrentSession().getSessionContext();
			final Currency orderCurrency = ctx.getCurrency();
			final Locale locale = ctx.getLocale();
			final ProductSetFixedPricePromotionModel psFixedPricePromotion = (ProductSetFixedPricePromotionModel) promotionModel;
			final Double setPrice = getSetPriceWithDiscount(psFixedPricePromotion.getProductSetPrice(), orderCurrency);
			if (setPrice != null)
			{
				final double setDiscount = getSetDiscount(psFixedPricePromotion.getProductSets(), orderCurrency, setPrice)
						.doubleValue();
				final Object[] args =
				{ setPrice, Helper.formatCurrencyAmount(ctx, locale, orderCurrency, setPrice.doubleValue()),
						Double.valueOf(setDiscount), Helper.formatCurrencyAmount(ctx, locale, orderCurrency, setDiscount) };
				final MessageFormat messageFormat = new MessageFormat(name != null ? name : title, locale);
				descriptions = messageFormat.format(args);
			}
		}
		return descriptions;
	}

	protected Double getSetPriceWithDiscount(final Collection<PromotionPriceRowModel> prices, final Currency currency)
	{
		Double result = null;
		if (prices != null && !prices.isEmpty())
		{
			for (final PromotionPriceRowModel priceRow : prices)
			{
				if (priceRow.getCurrency().getSymbol().equals(currency.getSymbol()))
				{
					result = Double.valueOf(priceRow.getPrice().doubleValue());
				}
			}
		}
		return result;
	}

	protected Double getSetDiscount(final Collection<ProductSetModel> sets, final Currency currency, final Double setPrice)
	{
		double result = 0.0;
		if (setPrice != null && sets != null && !sets.isEmpty())
		{
			result = getProductSetPrice(sets.iterator().next().getProducts(), currency) - setPrice.doubleValue();
		}
		return Double.valueOf(result);
	}

	protected double getProductSetPrice(final Set<ProductModel> products, final Currency currency)
	{
		double result = 0d;
		if (products != null && !products.isEmpty())
		{
			for (final ProductModel product : products)
			{
				if (product.getEurope1Prices() != null && !product.getEurope1Prices().isEmpty())
				{
					for (final PriceRowModel priceRow : product.getEurope1Prices())
					{
						if (priceRow.getCurrency().getName().equals(currency.getName()))
						{
							result += priceRow.getPrice().doubleValue();
						}
					}
				}
			}
		}

		return result;
	}

	/**
	 * @return the cartService
	 */
	public CartService getCartService()
	{
		return cartService;
	}

	/**
	 * @param cartService
	 *           the cartService to set
	 */
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
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
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the promotionService
	 */
	public PromotionsService getPromotionService()
	{
		return promotionService;
	}

	/**
	 * @param promotionService
	 *           the promotionService to set
	 */
	public void setPromotionService(final PromotionsService promotionService)
	{
		this.promotionService = promotionService;
	}

}
