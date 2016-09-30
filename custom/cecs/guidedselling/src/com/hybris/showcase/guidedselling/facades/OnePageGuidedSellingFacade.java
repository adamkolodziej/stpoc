package com.hybris.showcase.guidedselling.facades;

import de.hybris.platform.b2ctelcofacades.bundle.GuidedSellingFacade;
import de.hybris.platform.b2ctelcofacades.data.BundleTabData;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;

import java.util.List;

import com.hybris.showcase.guidedselling.data.BundleOfferData;
import com.hybris.showcase.guidedselling.data.BundlePackageData;
import com.hybris.showcase.guidedselling.data.ConfigMessage;

/**
 * Created by I303845 on 2015-02-02.
 */
public interface OnePageGuidedSellingFacade extends GuidedSellingFacade {
    List<BundleTabData> getBundleTabs(String bundleTemplateId);

    BundleOfferData getBundleOffer(int bundleNo);
    BundleOfferData getBundleOffer(String orderCode, int bundleNo);

    List<BundlePackageData> getPackages(String bundleTemplateId);

    BundlePackageData getPackageByCode(String packageCode);

    List<ProductData> getConditionalProducts(String bundleTemplateId);

    List<ProductData> getConditionalProducts(String bundleTemplateId, List<ProductOption> options);

    BundleOfferData startWithConditionalProduct(String bundleTemplateId, String productCode, int bundleNo) throws CommerceCartModificationException;

    BundleOfferData chooseProduct(String bundleTemplateId, String productCode, int bundleNo) throws CommerceCartModificationException;

    BundleOfferData startWithPackage(String bundleTemplateId, String packageCode);

    boolean canReusePackageOffer(String bundleTemplateId, String packageCode);

    boolean canReuseProductOffer(String bundleTemplateId, String productCode);

    BundleOfferData loadOrder(String orderCode);

    BundleTemplateModel getRootBundleTemplate(String orderCode);

    boolean selectLoyaltyPayment(String productCode, int orderEntryNumber, boolean isLoyaltyPayment);

    Integer getRemainingLoyaltyPoints();

    void addMessagesForComponent(final List<ConfigMessage> messages, final String componentId, final BundleOfferData offer);

}
