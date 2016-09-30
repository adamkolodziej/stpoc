package com.hybris.showcase.loyaltypoints.services.impl;

import com.hybris.services.entitlements.api.EntitlementFacade;
import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.api.status.Status;
import com.hybris.showcase.loyaltypoints.constants.LoyaltypointsConstants;
import com.hybris.showcase.guidedselling.data.BundleComponentData;
import com.hybris.showcase.guidedselling.data.BundleComponentOptionData;
import com.hybris.showcase.guidedselling.data.BundleOfferData;
import com.hybris.showcase.loyaltypoints.services.LoyaltyPointsService;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.subscriptionfacades.data.SubscriptionPricePlanData;
import de.hybris.platform.subscriptionservices.model.*;
import de.hybris.platform.subscriptionservices.price.SubscriptionCommercePriceService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;


/**
 * Created by mgolubovic on 1.4.2015..
 */
public class DefaultLoyaltyPointsService implements LoyaltyPointsService
{
	private static final Logger LOG = Logger.getLogger(DefaultLoyaltyPointsService.class);

	private static final String LOYALTY_ENTITLEMENT_TYPE = "loyalty-points";
	private static final String GRANT_MAX_QUANTITY = "maxQuantity";
	private static final String GRANT_REMAINING_QUANTITY = "remainingQuantity";

	private static final String USERS_REMAINING_LOYALTY_POINTS = "UsersRemainingLoyaltyPoints";
	private static final String USERS_USED_LOYALTY_POINTS = "UsersUsedLoyaltyPoints";

	private ModelService modelService;
	private CalculationService calculationService;
	private CartService cartService;
	private ProductService productService;
	private UserService userService;
	private CommonI18NService commonI18NService;
	private EntitlementFacade entitlementFacade;
	private SessionService sessionService;
	private SubscriptionCommercePriceService commercePriceService;

    @Override
    public boolean selectLoyaltyPayment(String productCode, int orderEntryNumber, boolean isLoyaltyPayment)
    {
        boolean isEntryAlredyLoyaltyPayment = isEntryLoyaltyPayment(orderEntryNumber);
        if(isLoyaltyPayment && !isEntryAlredyLoyaltyPayment
                && userHasEnoughLoyaltyForProduct(productCode))
        {
            selectEntryLoyaltyPayment(productCode, orderEntryNumber, isLoyaltyPayment);
            decreaseUserLoyaltyForProduct(productCode);
            return true;
        }
        else if(!isLoyaltyPayment && isEntryAlredyLoyaltyPayment)
        {
            selectEntryLoyaltyPayment(productCode, orderEntryNumber, isLoyaltyPayment);
            restoreUserLoyaltyForProduct(productCode);
            return true;
        }
        else if(!isLoyaltyPayment && !isEntryAlredyLoyaltyPayment)
        {
            return true;
        }
        return false;
    }

	protected void selectEntryLoyaltyPayment(String productCode, int orderEntryNumber, boolean isLoyaltyPayment)
	{
		final CartModel cart = getCartService().getSessionCart();
		for (final AbstractOrderEntryModel entry : cart.getEntries())
		{
			if (entry.getEntryNumber().equals(orderEntryNumber) && entry.getProduct().getCode().equals(productCode))
			{
				entry.setLoyaltyPayment(isLoyaltyPayment);
				for(final AbstractOrderEntryModel childEntry : entry.getChildEntries())
				{
					childEntry.setLoyaltyPayment(isLoyaltyPayment);
					childEntry.setCalculated(Boolean.FALSE);
					childEntry.getOrder().setCalculated(Boolean.FALSE);
					modelService.save(childEntry);
					calculateCart((CartModel)childEntry.getOrder());
				}
				// save cart
				entry.setCalculated(Boolean.FALSE);
				cart.setCalculated(Boolean.FALSE);
				modelService.save(entry);
				// recalculate
				calculateCart(cart);
			}
		}
	}

	@Override
	public boolean userHasEnoughLoyaltyForProduct(String productCode)
	{
		final Integer remainingLoyaltyPoints = getUsersRemainingLoyaltyPoints();
		final Double loyaltyPointsPrice = getLoyaltyPointsPriceForProduct(productCode);

		return (((new Integer(remainingLoyaltyPoints).doubleValue()) - loyaltyPointsPrice.doubleValue()) >= 0.0d);
	}

	@Override
	public boolean isEntryLoyaltyPayment(int orderEntryNumber)
	{
		final CartModel cart = getCartService().getSessionCart();
		for (final AbstractOrderEntryModel entry : cart.getEntries())
		{
			if (entry.getEntryNumber().equals(orderEntryNumber))
			{
                return entry.isLoyaltyPayment();
			}
		}
        return false;
	}

	@Override
	public void decreaseUserLoyaltyForProduct(String productCode)
	{
		final Integer remainingLoyaltyPoints = getUsersRemainingLoyaltyPoints();
		final Double loyaltyPointsPrice = getLoyaltyPointsPriceForProduct(productCode);
		final Integer newRemainingLoyaltyPoints = new Integer(remainingLoyaltyPoints.intValue() - loyaltyPointsPrice.intValue());
		addToUsedLoyaltyPoints(new Integer(loyaltyPointsPrice.intValue()));

		setUsersRemainingLoyaltyPointsCache(newRemainingLoyaltyPoints);
	}

	@Override
	public void restoreUserLoyaltyForProduct(String productCode)
	{
		final Integer remainingLoyaltyPoints = getUsersRemainingLoyaltyPoints();
		final Double loyaltyPointsPrice = getLoyaltyPointsPriceForProduct(productCode);
		final Integer newRemainingLoyaltyPoints = new Integer(remainingLoyaltyPoints.intValue() + loyaltyPointsPrice.intValue());
		removeFromUsedLoyaltyPoints(new Integer(loyaltyPointsPrice.intValue()));

		setUsersRemainingLoyaltyPointsCache(newRemainingLoyaltyPoints);
	}

	@Override
	public Integer getUsersRemainingLoyaltyPoints()
	{
		Integer usersRemainingLoyaltyPoints = sessionService.getAttribute(USERS_REMAINING_LOYALTY_POINTS);
		Boolean invalidateUsersRemainingLoyaltyPoints = sessionService.getAttribute(LoyaltyPointsService.INVALIDATE_USERS_REMAINING_LOYALTY_POINTS);
		if (usersRemainingLoyaltyPoints == null ||
                (invalidateUsersRemainingLoyaltyPoints != null && invalidateUsersRemainingLoyaltyPoints.booleanValue()))
		{
			final UserModel userModel = userService.getCurrentUser();
			final List<GrantData> grants = getEntitlementFacade().getGrants(userModel.getUid(), LOYALTY_ENTITLEMENT_TYPE, null, null);
			int totalRemainingQuantity = 0;
			if (CollectionUtils.isNotEmpty(grants))
			{
				for (final GrantData grant : grants)
				{
					if (grant.getStatus().equals(Status.ACTIVE))
					{
						final String remainingQuantityProp = grant.getProperty(GRANT_REMAINING_QUANTITY);
						final Integer remainingQuantity = Integer.valueOf(remainingQuantityProp);
						if (remainingQuantity.intValue() > 0)
						{
							totalRemainingQuantity += remainingQuantity;
						}
					}
				}
			}

			final CartModel cart = getCartService().getSessionCart();
			Double userUsedLyalityPoints = new Double(0d);
			for (final AbstractOrderEntryModel entry : cart.getEntries())
			{
				if (entry.isLoyaltyPayment())
				{
					for(final AbstractOrderEntryModel childEntry : entry.getChildEntries())
					{
						userUsedLyalityPoints  = userUsedLyalityPoints + childEntry.getLoyaltyPoints();
					}
				}
			}

			usersRemainingLoyaltyPoints = new Integer(totalRemainingQuantity - userUsedLyalityPoints.intValue());
			setUsersRemainingLoyaltyPointsCache(usersRemainingLoyaltyPoints);
            sessionService.setAttribute(LoyaltyPointsService.INVALIDATE_USERS_REMAINING_LOYALTY_POINTS, Boolean.FALSE);
			sessionService.setAttribute(USERS_USED_LOYALTY_POINTS, userUsedLyalityPoints.intValue());
		}

		return usersRemainingLoyaltyPoints;
	}

    @Override
    public Integer getUsersUsedLoyaltyPoints()
    {
        Integer currentlyUsedLoyaltyPoints = sessionService.getAttribute(USERS_USED_LOYALTY_POINTS);
        if (currentlyUsedLoyaltyPoints == null)
        {
            currentlyUsedLoyaltyPoints = new Integer(0);
        }
        return currentlyUsedLoyaltyPoints;
    }

	@Override
	public void useLoyaltyPointsOnEms()
	{
		int currentlyUsedLoyaltyPoints = getUsersUsedLoyaltyPoints().intValue();
		if (currentlyUsedLoyaltyPoints > 0)
		{
			// get LOYALTY_ENTITLEMENT_TYPE grants
			// revoke grants one by one until all currentlyUsedLoyaltyPoints are used
            final UserModel userModel = userService.getCurrentUser();
            final List<GrantData> grants = getEntitlementFacade().getGrants(userModel.getUid(), LOYALTY_ENTITLEMENT_TYPE, null, null);

            if (CollectionUtils.isNotEmpty(grants))
            {
                for (final GrantData grant : grants)
                {
                    if (grant.getStatus().equals(Status.ACTIVE) && currentlyUsedLoyaltyPoints > 0)
                    {
                        final String remainingQuantity_str = grant.getProperty(GRANT_REMAINING_QUANTITY);
                        if(StringUtils.isNotEmpty(remainingQuantity_str) && Integer.valueOf(remainingQuantity_str) > 0)
                        {
                            int remainingQuantity = Integer.valueOf(remainingQuantity_str);
                            int difference = currentlyUsedLoyaltyPoints - remainingQuantity;
                            if(difference <= 0)
                            {
                                int grantRemainingQuantity = remainingQuantity - currentlyUsedLoyaltyPoints;
                                getEntitlementFacade().updateGrantProperty(grant.getId(),GRANT_REMAINING_QUANTITY,
                                        String.valueOf(grantRemainingQuantity));
                                currentlyUsedLoyaltyPoints = 0;
                                break;
                            }
                            else
                            {
                                getEntitlementFacade().updateGrantProperty(grant.getId(),GRANT_REMAINING_QUANTITY,
                                        String.valueOf(0));
                                currentlyUsedLoyaltyPoints -= remainingQuantity;
                            }
                        }
                    }
                }
            }
		}
		sessionService.setAttribute(USERS_USED_LOYALTY_POINTS, new Integer(0));
	}

	protected void calculateCart(final CartModel cart)
	{
		try
		{
			calculationService.calculate(cart);
		}
		catch (CalculationException ce)
		{
			LOG.error("Error during cart calculation", ce);
		}
	}

    @Override
	public Double getLoyaltyPointsPriceForProduct(final String productCode)
	{
		final ProductModel productModel = productService.getProductForCode(productCode);
		return  getLoyaltyPointsPriceForProduct(productModel);
	}

    @Override
	public Double getLoyaltyPointsPriceForProduct(final ProductModel productModel)
	{
		final CurrencyModel crdCurrency = getCommonI18NService().getCurrency(LoyaltypointsConstants.CRD_CURRENCY_ISO);
		if (productModel != null && crdCurrency != null)
		{
			final Double loyaltyPointsPrice = sessionService.executeInLocalView(new SessionExecutionBody()
			{
				@Override
				public Object execute()
				{
					getCommonI18NService().setCurrentCurrency(crdCurrency);
					if (productModel instanceof SubscriptionProductModel)
					{
						final SubscriptionPricePlanModel pricePlanModel = getCommercePriceService().getSubscriptionPricePlanForProduct(
								(SubscriptionProductModel) productModel);

						if(CollectionUtils.isNotEmpty(pricePlanModel.getRecurringChargeEntries()))
						{
							return pricePlanModel.getRecurringChargeEntries().iterator().next().getPrice();
						}
						else if(CollectionUtils.isNotEmpty(pricePlanModel.getOneTimeChargeEntries()))
						{
							return pricePlanModel.getOneTimeChargeEntries().iterator().next().getPrice();
						}
						return 0.0D;
					}
                    else
                    {
                        if(hasLoyaltyPointsPrice(productModel))
                        {
                            final PriceInformation info = getCommercePriceService().getWebPriceForProduct(productModel);
                            return info.getPriceValue().getValue();
                        }
                        else
                        {
                            return 0.0D;
                        }
                    }
				}
			});
			return loyaltyPointsPrice;
		}
		return 0.0D;
	}

	@Override
	public Double getLoyaltyPointsPriceForProduct(final String productCode, final String billingTimeCode)
	{
		final ProductModel productModel = productService.getProductForCode(productCode);
		return getLoyaltyPointsPriceForProduct(productModel, billingTimeCode);
	}

	@Override
	public Double getLoyaltyPointsPriceForProduct(final ProductModel productModel, final String billingTimeCode)
	{
		final CurrencyModel crdCurrency = getCommonI18NService().getCurrency(LoyaltypointsConstants.CRD_CURRENCY_ISO);
		if (productModel != null && crdCurrency != null && StringUtils.isNotEmpty(billingTimeCode))
		{
			final Double loyaltyPointsPrice = sessionService.executeInLocalView(new SessionExecutionBody()
			{
				@Override
				public Object execute()
				{
					getCommonI18NService().setCurrentCurrency(crdCurrency);
					if(productModel instanceof SubscriptionProductModel)
					{
						final SubscriptionPricePlanModel pricePlanModel = getCommercePriceService().getSubscriptionPricePlanForProduct(
								(SubscriptionProductModel) productModel);

						if (CollectionUtils.isNotEmpty(pricePlanModel.getRecurringChargeEntries()))
						{
							final RecurringChargeEntryModel recurringChargeEntryModel = pricePlanModel.getRecurringChargeEntries().iterator().next();
							if(recurringChargeEntryModel != null && recurringChargeEntryModel.getBillingTime().getCode().equals(billingTimeCode))
							{
								return recurringChargeEntryModel.getPrice();
							}
						}
						if (CollectionUtils.isNotEmpty(pricePlanModel.getOneTimeChargeEntries()))
						{
							final OneTimeChargeEntryModel oneTimeChargeEntryModel = pricePlanModel.getOneTimeChargeEntries().iterator().next();
							if(oneTimeChargeEntryModel != null && oneTimeChargeEntryModel.getBillingTime().getCode().equals(billingTimeCode))
							{
								return oneTimeChargeEntryModel.getPrice();
							}
						}
						if(CollectionUtils.isNotEmpty(pricePlanModel.getUsageCharges()))
						{
							final UsageChargeModel usageChargeModel = pricePlanModel.getUsageCharges().iterator().next();
							if(usageChargeModel != null && usageChargeModel.getBillingTime().getCode().equals(billingTimeCode))
							{
								return usageChargeModel.getUsageChargeEntries().iterator().next().getPrice();
							}
						}
						return 0.0D;
					}
					else
					{
                        if(hasLoyaltyPointsPrice(productModel))
                        {
						    final PriceInformation info = getCommercePriceService().getWebPriceForProduct(productModel);
						    return info.getPriceValue().getValue();
					    }
                        else
                        {
                            return 0.0D;
                        }
					}
				}
			});
			return loyaltyPointsPrice;
		}
		return 0.0D;
	}

    @Override
    public boolean isEligibleForLoyaltyPayment(BundleOfferData bundleOfferData)
    {
        for(final BundleComponentData bundleComponentData : bundleOfferData.getComponents())
        {
            for(final BundleComponentOptionData bundleComponentOptionData : bundleComponentData.getOptions())
            {
                if(bundleComponentOptionData.isSelected())
                {
                    final PriceData loyaltyPrice = bundleComponentOptionData.getProduct().getLoyaltyPointsPrice();
                    if (isLoyaltyGreaterThanZero(loyaltyPrice))
                    {
                        return (getUsersRemainingLoyaltyPoints().intValue() > 0);
                    }
                }
            }
        }
        return false;
    }

    protected boolean hasLoyaltyPointsPrice(final ProductModel productModel)
    {
        final Collection<PriceRowModel> priceRows = productModel.getEurope1Prices();
        if(CollectionUtils.isNotEmpty(priceRows))
        {
            for(final PriceRowModel priceRowModel : priceRows)
            {
                if(priceRowModel.getCurrency().getIsocode().equals(LoyaltypointsConstants.CRD_CURRENCY_ISO))
                {
                    return true;
                }
            }
            return false;
        }
        else {
            return false;
        }
    }

    protected boolean isLoyaltyGreaterThanZero(final PriceData loyaltyPrice)
    {
        if(loyaltyPrice != null)
        {
            if(loyaltyPrice instanceof SubscriptionPricePlanData)
            {
                final SubscriptionPricePlanData subscriptionLoyaltyPrice = (SubscriptionPricePlanData)loyaltyPrice;
                PriceData firstCharge = null;
                if(CollectionUtils.isNotEmpty(subscriptionLoyaltyPrice.getRecurringChargeEntries()))
                {
                    firstCharge = subscriptionLoyaltyPrice.getRecurringChargeEntries().get(0).getPrice();
                }
                else if(CollectionUtils.isNotEmpty(subscriptionLoyaltyPrice.getOneTimeChargeEntries()))
                {
                    firstCharge = subscriptionLoyaltyPrice.getOneTimeChargeEntries().get(0).getPrice();
                }
                return (firstCharge != null && firstCharge.getValue() != null &&
                        firstCharge.getValue().compareTo(BigDecimal.ZERO) > 0);
            }
            else if(loyaltyPrice.getValue() != null && (loyaltyPrice.getValue().compareTo(BigDecimal.ZERO) > 0 ))
            {
                return true;
            }
        }
        return false;
    }

    protected void setUsersRemainingLoyaltyPointsCache(final Integer usersRemainingLoyaltyPoints)
	{
		sessionService.setAttribute(USERS_REMAINING_LOYALTY_POINTS, usersRemainingLoyaltyPoints);
	}

	protected void addToUsedLoyaltyPoints(final Integer usedLoyaltyPoints)
	{
		Assert.notNull(usedLoyaltyPoints);
		Integer currentlyUsedLoyaltyPoints = sessionService.getAttribute(USERS_USED_LOYALTY_POINTS);
		if (currentlyUsedLoyaltyPoints == null)
		{
			currentlyUsedLoyaltyPoints = new Integer(0);
		}
		currentlyUsedLoyaltyPoints = currentlyUsedLoyaltyPoints + usedLoyaltyPoints;
		sessionService.setAttribute(USERS_USED_LOYALTY_POINTS, currentlyUsedLoyaltyPoints);
	}

	protected void removeFromUsedLoyaltyPoints(final Integer restoredLoyaltyPoints)
	{
		Assert.notNull(restoredLoyaltyPoints);
		Integer currentlyUsedLoyaltyPoints = sessionService.getAttribute(USERS_USED_LOYALTY_POINTS);
		if (currentlyUsedLoyaltyPoints == null)
		{
			currentlyUsedLoyaltyPoints = new Integer(0);
		}
		currentlyUsedLoyaltyPoints = currentlyUsedLoyaltyPoints - restoredLoyaltyPoints;
		sessionService.setAttribute(USERS_USED_LOYALTY_POINTS, currentlyUsedLoyaltyPoints);
	}

	@Override
	public void invalidateRemainingLoyaltyPointsCache()
	{
		sessionService.setAttribute(LoyaltyPointsService.INVALIDATE_USERS_REMAINING_LOYALTY_POINTS, Boolean.TRUE);
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(ModelService modelService)
	{
		this.modelService = modelService;
	}

	public CalculationService getCalculationService()
	{
		return calculationService;
	}

	@Required
	public void setCalculationService(CalculationService calculationService)
	{
		this.calculationService = calculationService;
	}

	public CartService getCartService()
	{
		return cartService;
	}

	@Required
	public void setCartService(CartService cartService)
	{
		this.cartService = cartService;
	}

	public ProductService getProductService()
	{
		return productService;
	}

	@Required
	public void setProductService(ProductService productService)
	{
		this.productService = productService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	public EntitlementFacade getEntitlementFacade()
	{
		return entitlementFacade;
	}

	@Required
	public void setEntitlementFacade(EntitlementFacade entitlementFacade)
	{
		this.entitlementFacade = entitlementFacade;
	}

	public SessionService getSessionService()
	{
		return sessionService;
	}

	@Required
	public void setSessionService(SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	public SubscriptionCommercePriceService getCommercePriceService()
	{
		return commercePriceService;
	}

	@Required
	public void setCommercePriceService(SubscriptionCommercePriceService commercePriceService)
	{
		this.commercePriceService = commercePriceService;
	}
}
