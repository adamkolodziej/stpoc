/**
 * 
 */
package de.hybris.platform.promotions.jalo;


import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.price.Discount;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.promotions.result.PromotionEvaluationContext;
import de.hybris.platform.promotions.result.PromotionOrderResults;
import de.hybris.platform.promotions.util.Helper;
import de.hybris.platform.tx.Transaction;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.DiscountValue;
import de.hybris.platform.voucher.jalo.Voucher;
import de.hybris.platform.voucher.jalo.util.VoucherValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.campaigns.promotion.queryplugin.PromotionQueryBuilderPlugin;


/**
 * Extends the Promotions Manager by adding support for other kinds of promotions beyond order level and product level.
 * 
 * @author rmcotton
 */
public class CampaignsPromotionsManager extends PromotionsManager
{
	private static final Logger LOG = Logger.getLogger(CampaignsPromotionsManager.class.getName());

	private List<PromotionQueryBuilderPlugin> promotionQueryPlugins;


	/**
	 * COPY/PASTE from superclass due to overzealous encapsulation
	 * 
	 * Method to fixup the vouchers applied to the order.
	 * <p/>
	 * This method is called before promotions have been calculates. It identifies any voucher discounts that are applied
	 * to the order, and removes them.
	 * 
	 * @param ctx
	 *           the hybris session context
	 * @param order
	 *           the order to fixup
	 */
	private static ArrayList<Voucher> fixupVouchersRemoveVouchers(final SessionContext ctx, final AbstractOrder order)
	{
		// This method is essentially a hack to try to work around an interaction problem
		// between the promotions extension and the voucher extension. Basically the voucher
		// extension ignores global discounts when calculating its relative discounts.
		// We try to fix this by recalculating the voucher's global discount values
		// based on the total rather than the subtotal.

		try
		{
			// Check to see if the config parameter is set to enable fixups
			if (Boolean.parseBoolean(Config.getParameter("promotions.voucher.fixupVouchers")))
			{
				// Get the list of discounts and check to see if any of them are Vouchers
				final Collection<Discount> discounts = order.getDiscounts();
				if (discounts != null && !discounts.isEmpty())
				{
					final ArrayList<Voucher> appliedVouchers = new ArrayList<Voucher>();

					for (final Discount discount : discounts)
					{
						if (discount instanceof Voucher)
						{
							final Voucher voucher = (Voucher) discount;

							// Ask the voucher to try to run, this will evaluate the voucher conditions and will tell us if the voucher is enabled
							// and also what the 'code' for the voucher is
							final DiscountValue testDiscountValue = voucher.getDiscountValue(order);
							if (testDiscountValue != null)
							{
								// Found discount to remove
								final DiscountValue oldDiscountValue = Helper.findGlobalDiscountValue(ctx, order,
										testDiscountValue.getCode());
								if (oldDiscountValue != null)
								{
									if (LOG.isDebugEnabled())
									{
										LOG.debug("Removing GlobalDiscountValue created by Voucher [" + voucher.getName(ctx) + "]");
									}

									// Remove the global discount value added by the voucher
									order.removeGlobalDiscountValue(ctx, oldDiscountValue);
								}

								// Store the voucher and the test discount, we need to remove all the vouchers before we can add them back again
								appliedVouchers.add(voucher);
							}
						}
					}

					return appliedVouchers;
				}
			}
		}
		catch (final Exception ex)
		{
			LOG.error("Failed to fixupVouchersRemoveVouchers", ex);
		}

		return null;
	}

	/**
	 * COPY/PASTE from superclass due to overzealous encapsulation
	 * 
	 * @param ctx
	 * @param order
	 * @param vouchers
	 */
	private static void fixupVouchersReapplyVouchers(final SessionContext ctx, final AbstractOrder order,
			final ArrayList<Voucher> vouchers)
	{
		// This method is essentially a hack to try to work around an interaction problem
		// between the promotions extension and the voucher extension. Basically the voucher
		// extension ignores global discounts when calculating its relative discounts.
		// We try to fix this by recalculating the voucher's global discount values
		// based on the total rather than the subtotal.

		try
		{
			if (vouchers != null && !vouchers.isEmpty())
			{
				// If we have removed global discount values, we must recalculate the totals
				order.calculateTotals(true);

				final double orderSubtotal = order.getSubtotal(ctx).doubleValue();

				// create global discounts for all vouchers
				for (final Voucher voucher : vouchers)
				{
					if (voucher.isAbsolute().booleanValue())
					{
						// add back in the absolute discount
						order.addGlobalDiscountValue(ctx, voucher.getDiscountValue(order));
					}
					else
					{
						// calculate the relative discount again
						//PRO-70
						final VoucherValue voucherValue = voucher.getVoucherValue(order);
						final double voucherDiscount = voucherValue.getValue();
						final DiscountValue voucherDiscountValue = new DiscountValue(voucher.getCode(), voucherDiscount, true,
								voucherDiscount, order.getCurrency(ctx).getIsoCode(ctx));
						order.addGlobalDiscountValue(ctx, voucherDiscountValue);
						if (LOG.isDebugEnabled())
						{
							LOG.debug("Reapplying Voucher [" + voucher.getName(ctx) + "], Relative Value: [" + voucher.getValue()
									+ "%], Order Total: [" + orderSubtotal + "], New Adjustment Discount [" + voucherDiscountValue + "]");
						}
					}
				}

				// After adding these global discounts we must calculate the totals again
				order.calculateTotals(true);
			}
		}
		catch (final Exception ex)
		{
			LOG.error("Failed to fixupVouchersReapplyVouchers", ex);
		}
	}

	/**
	 * COPY/PASTE from superclass due to overzealous encapsulation
	 * 
	 * @param ctx
	 * @param promoContext
	 * @param promotion
	 * @return
	 */
	//see https://jira.hybris.com/browse/PRO-89
	private List<PromotionResult> evaluatePromotion(final SessionContext ctx, final PromotionEvaluationContext promoContext,
			final AbstractPromotion promotion)
	{
		final List<PromotionResult> results = promotion.evaluate(ctx, promoContext);
		if (Transaction.current().isRunning())
		{
			Transaction.current().flushDelayedStore();
		}
		return results;
	}

	List<AbstractPromotion> findPromotionsSortByPriority(final SessionContext ctx, final JaloSession jaloSession,
			final Collection<PromotionGroup> promotionGroups, final Collection<Product> products, final Date date)
	{
		// No promotion groups, no matching promotions
		if (promotionGroups == null || promotionGroups.isEmpty())
		{
			return Collections.EMPTY_LIST;
		}

		final StringBuilder promQuery = new StringBuilder("SELECT DISTINCT pprom.pk, pprom.prio FROM (");
		final HashMap args = new HashMap();
		args.put("now", date);
		args.put("true", Boolean.TRUE);
		args.put("promotionGroups", promotionGroups);

		boolean previousQuery = false;
		for (final PromotionQueryBuilderPlugin plugin : getPromotionQueryPlugins())
		{

			final String query = plugin.getQuery(ctx, jaloSession, promotionGroups, products, date, args);
			if (query != null)
			{
				if (previousQuery)
				{
					// add union
					promQuery.append(" UNION ALL ");
				}
				previousQuery = true;
				promQuery.append(query);
			}

		}

		// Close AND and add OrderBy
		promQuery.append(" )pprom ORDER BY pprom.prio DESC");

		return jaloSession.getFlexibleSearch().search(ctx, promQuery.toString(), args, AbstractPromotion.class).getResult();

	}

	/**
	 * Find all promotions that can be evaluated on the list of product specified.
	 * <p/>
	 * This includes all OrderPromotions and any ProductPromotions that are related to the any of the products passed
	 * 
	 * @param ctx
	 *           The hybris context
	 * @param jaloSession
	 *           The jalo session
	 * @param promotionGroups
	 *           The promotion groups to evaluate
	 * @param products
	 *           The list of products to find associated promotions for
	 * @param date
	 *           The date to test against promotions
	 * @return The list of promotions
	 */
	public static List<AbstractPromotion> findOrderAndProductPromotionsSortByPriority(final SessionContext ctx,
			final JaloSession jaloSession, final Collection<PromotionGroup> promotionGroups, final Collection<Product> products,
			final Date date)
	{
		return ((CampaignsPromotionsManager) CampaignsPromotionsManager.getInstance()).findPromotionsSortByPriority(ctx,
				jaloSession, promotionGroups, products, date);
	}

	/**
	 * COPY/PASTE from superclass due to overzealous encapsulation and use of static methods
	 * 
	 * 
	 * Update the promotions on the specified {@link AbstractOrder} object.
	 * <p/>
	 * The resulting promotions can be retrieved later by calling {@link #getPromotionResults}. The order must be
	 * calculated before calling this method. {@link #updatePromotions} must be called after calling
	 * {@link AbstractOrder#recalculate()} on the {@link AbstractOrder}. Where the {@link AutoApplyMode} is set to
	 * {@link AutoApplyMode#KEEP_APPLIED} the state of any previously applied {@link PromotionResult} is recorded and if
	 * it is still in the fired state ({@link PromotionResult#isApplied()}) after reevaluating the promotions it will be
	 * automatically reapplied.
	 * <p/>
	 * The promotion results are stored in the database and the same {@link PromotionOrderResults} can be obtained later
	 * by calling {@link #getPromotionResults}.
	 * 
	 * @param ctx
	 *           The hybris session context
	 * @param promotionGroups
	 *           The promotion groups to evaluate
	 * @param order
	 *           The AbstractOrder object to update the promotions for
	 * @param evaluateRestrictions
	 *           If <i>true</i> any promotion restrictions will be observed, if <i>false</i> all promotion restrictions
	 *           are ignored
	 * @param productPromotionMode
	 *           The auto apply mode. This determines whether this method applies any product promotional changes to line
	 *           items or discounts to the overall amount
	 * @param orderPromotionMode
	 *           The auto apply mode. This determines whether this method applies any order promotional changes to line
	 *           items or discounts to the overall amount
	 * @param date
	 *           The effective date for the promotions to check. Use this to to see the effects of promotions in the past
	 *           or future.
	 * @return The promotion results
	 * 
	 * @see #getPromotionResults(de.hybris.platform.jalo.order.AbstractOrder)
	 * @see #getPromotionResults(de.hybris.platform.jalo.SessionContext,de.hybris.platform.jalo.order.AbstractOrder)
	 * @see #getPromotionResults(SessionContext, Collection, AbstractOrder, boolean, AutoApplyMode, AutoApplyMode, Date)
	 */
	@Override
	public PromotionOrderResults updatePromotions(final SessionContext ctx, final Collection<PromotionGroup> promotionGroups,
			final AbstractOrder order, final boolean evaluateRestrictions, final AutoApplyMode productPromotionMode,
			final AutoApplyMode orderPromotionMode, Date date)
	{
		try
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("updatePromotions for [" + order + "] promotionGroups=[" + Helper.join(promotionGroups)
						+ "] evaluateRestrictions=[" + evaluateRestrictions + "] productPromotionMode=[" + productPromotionMode
						+ "] orderPromotionMode=[" + orderPromotionMode + "] date=[" + date + "]");
			}

			if (promotionGroups != null && order != null)
			{
				if (date == null)
				{
					// Default value is Now
					date = Helper.getDateNowRoundedToMinute();
				}

				if (!order.isCalculated(ctx).booleanValue())
				{
					if (LOG.isDebugEnabled())
					{
						LOG.debug("updatePromotions order [" + order + "] not calculated, calculating");
					}
					order.calculate(date);
				}

				// Record list of promotions to keep applied if AutoApplyMode.KEEP_APPLIED is specifed
				final List<String> promotionResultsToKeepApplied = new ArrayList<String>();

				// Find the current total value of promotions active
				final List<PromotionResult> currResults = this.getPromotionResultsInternal(ctx, order);
				double oldTotalAppliedDiscount = 0;
				if (currResults != null && !currResults.isEmpty())
				{
					for (final PromotionResult pr : currResults)
					{
						if (pr.getFired(ctx))
						{
							final boolean prApplied = pr.isApplied(ctx);
							if (prApplied)
							{
								// We want to capture the total applied discount even for promotion results that are now invalid.
								oldTotalAppliedDiscount += pr.getTotalDiscount(ctx);
							}

							if (pr.isValid(ctx))
							{
								if (((productPromotionMode == AutoApplyMode.KEEP_APPLIED && pr.getPromotion(ctx) instanceof ProductPromotion) || (orderPromotionMode == AutoApplyMode.KEEP_APPLIED && pr
										.getPromotion(ctx) instanceof OrderPromotion)) && prApplied)
								{
									final String prKey = pr.getDataUnigueKey(ctx);
									if (prKey != null && prKey.length() > 0)
									{
										if (LOG.isDebugEnabled())
										{
											LOG.debug("updatePromotions found applied PromotionResult [" + pr + "] key [" + prKey
													+ "] that should be reapplied");
										}
										promotionResultsToKeepApplied.add(prKey);
									}
								}
							}
						}
					}
				}

				// Delete any results stored from a previous run
				deleteStoredPromotionResults(ctx, order, true);

				// Find all runnable promotions in the system

				// Get the list of base products in the cart
				final Collection<Product> products = getBaseProductsForOrder(ctx, order);

				// Find the promotions that can be evaluated
				// will find all OrderPromotions and any ProductPromotions that are related to the products specified
				final List<AbstractPromotion> activePromotions = findOrderAndProductPromotionsSortByPriority(ctx, getSession(),
						promotionGroups, products, date);

				if (LOG.isDebugEnabled())
				{
					LOG.debug("updatePromotions found [" + activePromotions.size() + "] promotions to run");
				}

				final List<PromotionResult> results = new LinkedList<PromotionResult>();

				double newTotalAppliedDiscount = 0.0D;
				if (!activePromotions.isEmpty())
				{
					// Remove existing vouchers to prevent them from interfering with the order threshold calculations
					final ArrayList<Voucher> vouchers = fixupVouchersRemoveVouchers(ctx, order);

					final PromotionEvaluationContext promoContext = new PromotionEvaluationContext(order, evaluateRestrictions, date);

					for (final AbstractPromotion promotion : activePromotions)
					{
						if (LOG.isDebugEnabled())
						{
							LOG.debug("updatePromotions evaluating promotion [" + promotion + "]");
						}
						final List<PromotionResult> promoResults = evaluatePromotion(ctx, promoContext, promotion);
						if (LOG.isDebugEnabled())
						{
							LOG.debug("updatePromotions promotion [" + promotion + "] returned [" + promoResults.size() + "] results");
						}

						// Work out if we need to apply this promotion
						boolean autoApply = false;
						boolean keepApplied = false;

						if ((productPromotionMode == AutoApplyMode.APPLY_ALL && orderPromotionMode == AutoApplyMode.APPLY_ALL)
								|| (productPromotionMode == AutoApplyMode.APPLY_ALL && promotion instanceof ProductPromotion)
								|| (orderPromotionMode == AutoApplyMode.APPLY_ALL && promotion instanceof OrderPromotion))
						{
							autoApply = true;
						}
						else if ((productPromotionMode == AutoApplyMode.KEEP_APPLIED && orderPromotionMode == AutoApplyMode.KEEP_APPLIED)
								|| (productPromotionMode == AutoApplyMode.KEEP_APPLIED && promotion instanceof ProductPromotion)
								|| (orderPromotionMode == AutoApplyMode.KEEP_APPLIED && promotion instanceof OrderPromotion))
						{
							keepApplied = true;
						}

						boolean needsCalculateTotals = false;

						if (autoApply || keepApplied)
						{
							// Apply the promotion results if required
							for (final PromotionResult pr : promoResults)
							{
								if (pr.getFired(ctx))
								{
									if (autoApply)
									{
										if (LOG.isDebugEnabled())
										{
											LOG.debug("updatePromotions auto applying result [" + pr + "] from promotion [" + promotion
													+ "]");
										}
										needsCalculateTotals |= pr.apply(ctx);

										// Add this promotion to the new total
										newTotalAppliedDiscount += pr.getTotalDiscount(ctx);
									}
									else if (keepApplied)
									{
										final String prKey = pr.getDataUnigueKey(ctx);
										if (prKey == null || prKey.length() == 0)
										{
											LOG.error("updatePromotions promotion result [" + pr + "] from promotion [" + promotion
													+ "] returned NULL or Empty DataUnigueKey");
										}
										else
										{
											// See if the promotion result is in the list of promotions to keep applied
											if (promotionResultsToKeepApplied.remove(prKey))
											{

												if (LOG.isDebugEnabled())
												{
													LOG.debug("updatePromotions keeping applied the result [" + pr + "] from promotion ["
															+ promotion + "]");
												}
												needsCalculateTotals |= pr.apply(ctx);

												// Add this promotion to the new total
												newTotalAppliedDiscount += pr.getTotalDiscount(ctx);
											}
										}
									}
								}
							}
						}

						if (needsCalculateTotals)
						{
							order.calculateTotals(true);
						}

						results.addAll(promoResults);
					}

					// Fixup vouchers, if required
					fixupVouchersReapplyVouchers(ctx, order, vouchers);
				}


				// Log all the PromotionResults that could not be reapplied.
				if (LOG.isDebugEnabled())
				{
					for (final String prKey : promotionResultsToKeepApplied)
					{
						LOG.debug("updatePomrotions PromotionResult not reapplied because it did not fire [" + prKey + "]");
					}
				}

				final double appliedDiscountChange = newTotalAppliedDiscount - oldTotalAppliedDiscount;

				if (LOG.isDebugEnabled())
				{
					LOG.debug("updatePromotions for [" + order + "] returned [" + results.size()
							+ "] PromotionResults appliedDiscountChange=[" + appliedDiscountChange + "]");
				}

				return new PromotionOrderResults(ctx, order, Collections.unmodifiableList(results), appliedDiscountChange);
			}
		}
		catch (final Exception ex)
		{
			LOG.error("Failed to updatePromotions", ex);
		}
		return null;
	}

	/**
	 * @return the promotionQueryPlugins
	 */
	public List<PromotionQueryBuilderPlugin> getPromotionQueryPlugins()
	{
		return promotionQueryPlugins;
	}

	/**
	 * @param promotionQueryPlugins
	 *           the promotionQueryPlugins to set
	 */
	@Required
	public void setPromotionQueryPlugins(final List<PromotionQueryBuilderPlugin> promotionQueryPlugins)
	{
		this.promotionQueryPlugins = promotionQueryPlugins;
	}
}
