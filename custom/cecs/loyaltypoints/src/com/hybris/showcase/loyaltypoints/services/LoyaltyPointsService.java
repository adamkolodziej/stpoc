package com.hybris.showcase.loyaltypoints.services;

import com.hybris.showcase.guidedselling.data.BundleOfferData;
import de.hybris.platform.core.model.product.ProductModel;

/**
 * Created by mgolubovic on 1.4.2015..
 */
public interface LoyaltyPointsService
{
    static final String INVALIDATE_USERS_REMAINING_LOYALTY_POINTS = "InvalidateUsersRemainingLoyaltyPoints";

    boolean selectLoyaltyPayment(String productCode, int orderEntryNumber, boolean isLoyaltyPayment);

    boolean userHasEnoughLoyaltyForProduct(String productCode);

    boolean isEntryLoyaltyPayment(int orderEntryNumber);

    void decreaseUserLoyaltyForProduct(String productCode);

    void restoreUserLoyaltyForProduct(String productCode);

    Integer getUsersRemainingLoyaltyPoints();

    Integer getUsersUsedLoyaltyPoints();

    void useLoyaltyPointsOnEms();

    Double getLoyaltyPointsPriceForProduct(final String productCode);

    Double getLoyaltyPointsPriceForProduct(final ProductModel product);

    Double getLoyaltyPointsPriceForProduct(final String productCode, final String billingTimeCode);

    Double getLoyaltyPointsPriceForProduct(final ProductModel product, final String billingTimeCode);

    void invalidateRemainingLoyaltyPointsCache();

    boolean isEligibleForLoyaltyPayment(final BundleOfferData bundleOfferData);
}
