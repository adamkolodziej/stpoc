package de.hybris.platform.promotions.jalo;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.Currency;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.promotions.result.PromotionEvaluationContext;
import de.hybris.platform.promotions.result.PromotionOrderView;
import de.hybris.platform.promotions.util.Helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;


public class ProductSetFixedPricePromotion extends GeneratedProductSetFixedPricePromotion
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(ProductSetFixedPricePromotion.class.getName());

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

	private boolean hasPromotionPriceRowForCurrency(final AbstractOrder order,
			final Collection<PromotionPriceRow> promotionPriceRows)
	{
		final String name = this.getComposedType().getName() + " (" + this.getCode() + ": " + this.getTitle() + ")";
		if (promotionPriceRows.isEmpty())
		{
			LOG.warn(name + " has no PromotionPriceRow. Skipping evaluation");
			return false;
		}
		final Currency currency = order.getCurrency();
		for (final PromotionPriceRow ppr : promotionPriceRows)
		{
			if (currency.equals(ppr.getCurrency()))
			{
				return true;
			}
		}
		LOG.warn(name + " has no PromotionPriceRow for currency " + currency.getName() + ". Skipping evaluation");
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.promotions.jalo.AbstractPromotion#evaluate(de.hybris.platform.jalo.SessionContext,
	 * de.hybris.platform.promotions.result.PromotionEvaluationContext)
	 */
	@Override
	public List<PromotionResult> evaluate(final SessionContext ctx, final PromotionEvaluationContext promoContext)
	{
		final List<PromotionResult> results = new ArrayList<PromotionResult>();

		final PromotionsManager.RestrictionSetResult rsr = this.findEligibleProductsInBasket(ctx, promoContext);
		final Collection<PromotionPriceRow> promotionPriceRows = this.getProductSetPrice(ctx);

		final AbstractOrder order = promoContext.getOrder();
		final boolean hasValidPromotionPriceRow = hasPromotionPriceRowForCurrency(order, promotionPriceRows);


		if (hasValidPromotionPriceRow && rsr.isAllowedToContinue() && !rsr.getAllowedProducts().isEmpty())
		{

			final List<Product> allBundleProducts = rsr.getAllowedProducts();
			final PromotionOrderView view = promoContext.createView(ctx, this, allBundleProducts);
			final long neededToFireCount = allBundleProducts.size();

			while (true)
			{
				promoContext.startLoggingConsumed(this);

				// Count the number of available bundle products in the basket.  Take one of each.
				long foundCount = 0;
				for (final Product product : allBundleProducts)
				{
					final long availableQuantity = view.getQuantity(ctx, product);
					if (availableQuantity > 0)
					{
						view.consume(ctx, product, 1);
						foundCount++;
					}
				}

				if (foundCount == neededToFireCount)
				{
					// Firing, so work out the discount.  Go through each consumed item and add the price up
					final List<PromotionOrderEntryConsumed> consumedEntries = promoContext.finishLoggingAndGetConsumed(this, true);
					double bundleRetailValue = 0.0D;
					for (final PromotionOrderEntryConsumed poec : consumedEntries)
					{
						bundleRetailValue += poec.getUnitPrice(ctx);
					}

					final Double offerValue = this.getPriceForOrder(ctx, this.getProductSetPrice(ctx), promoContext.getOrder(),
							ProductSetFixedPricePromotion.PRODUCTSETPRICE);
					if (offerValue != null)
					{
						Helper.adjustUnitPrices(ctx, promoContext, consumedEntries, offerValue.doubleValue(), bundleRetailValue);

						final PromotionOrderAdjustTotalAction poata = PromotionsManager.getInstance()
								.createPromotionOrderAdjustTotalAction(ctx, offerValue.doubleValue() - bundleRetailValue);
						final List<AbstractPromotionAction> actions = new ArrayList<AbstractPromotionAction>();
						actions.add(poata);

						final PromotionResult result = PromotionsManager.getInstance().createPromotionResult(ctx, this,
								promoContext.getOrder(), 1.0F);
						result.setConsumedEntries(ctx, consumedEntries);
						result.setActions(actions);

						results.add(result);
					}
					else
					{
						promoContext.abandonLogging(this);
					}
				}
				else
				{
					if (foundCount > 0)
					{
						final float certainty = (float) foundCount / (float) neededToFireCount;
						final PromotionResult result = PromotionsManager.getInstance().createPromotionResult(ctx, this,
								promoContext.getOrder(), certainty);
						result.setConsumedEntries(ctx, promoContext.finishLoggingAndGetConsumed(this, false));
						results.add(result);
					}
					else
					{
						promoContext.abandonLogging(this);
					}
					break;
				}
			}

		}

		// There is no partially fired state for this promotion, it either has products to discount or it doesn't.
		return results;


	}




	@Override
	public String getResultDescription(final SessionContext ctx, final PromotionResult promotionResult, final Locale locale)
	{
		final AbstractOrder order = promotionResult.getOrder(ctx);
		if (order != null)
		{
			final Currency orderCurrency = order.getCurrency(ctx);

			final Double fixedUnitPrice = this.getPriceForOrder(ctx, this.getProductSetPrice(ctx), promotionResult.getOrder(ctx),
					ProductSetFixedPricePromotion.PRODUCTSETPRICE);
			if (fixedUnitPrice != null)
			{
				if (promotionResult.getFired(ctx))
				{
					final double totalDiscount = promotionResult.getTotalDiscount(ctx);

					// "Buy this set for {1} - You have saved {3}"
					final Object[] args =
					{ fixedUnitPrice, Helper.formatCurrencyAmount(ctx, locale, orderCurrency, fixedUnitPrice.doubleValue()),
							Double.valueOf(totalDiscount), Helper.formatCurrencyAmount(ctx, locale, orderCurrency, totalDiscount) };
					return formatMessage(this.getMessageFired(ctx), args, locale);
				}
				else if (promotionResult.getCouldFire(ctx))
				{
					// Avoid having to run a query again to see the size of the result set.  We can infer this from the certainty and the consumed count
					final long consumedCount = promotionResult.getConsumedCount(ctx, true);
					final long neededCount = Math.round(consumedCount / promotionResult.getCertainty(ctx).floatValue());

					if (LOG.isDebugEnabled())
					{
						LOG.debug("(" + getPK() + ") getResultDescription: consumedCount=[" + consumedCount + "] certainty=["
								+ promotionResult.getCertainty(ctx) + "] neededCount=[" + neededCount + "]");
					}

					// "Buy {0,choice,1#one more item|1<another {0,number,integer} items} from this bundle to get these products for {2}"
					final Object[] args =
					{ Long.valueOf(neededCount - consumedCount), fixedUnitPrice,
							Helper.formatCurrencyAmount(ctx, locale, orderCurrency, fixedUnitPrice.doubleValue()) };
					return formatMessage(this.getMessageCouldHaveFired(ctx), args, locale);
				}
			}
		}
		return "";
	}



}
