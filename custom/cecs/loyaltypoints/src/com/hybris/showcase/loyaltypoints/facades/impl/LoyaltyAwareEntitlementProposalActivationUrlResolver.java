package com.hybris.showcase.loyaltypoints.facades.impl;

import com.hybris.showcase.emsextras.facades.impl.EntitlementProposalActivationUrlResolver;
import com.hybris.showcase.loyaltypoints.services.LoyaltyPointsService;
import com.hybris.showcase.model.ContractModel;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.core.model.product.ProductModel;
import org.springframework.beans.factory.annotation.Required;

/**
 * CECS-538 if product can be paid with credits block possibility for one-click order
 *
 * Created by miroslaw.szot@sap.com on 2015-08-21.
 */
public class LoyaltyAwareEntitlementProposalActivationUrlResolver extends EntitlementProposalActivationUrlResolver {
    private LoyaltyPointsService loyaltyPointsService;

    @Override
    protected String resolveDefault(ProductModel product) {
        // HACK: if product is related to Credits than you can't order it with one click
        if( canBePaidWithCredits(product) ) {
            return getProductModelUrlResolver().resolve(product);
        }

        return super.resolveDefault(product);
    }

    @Override
    protected String resolveContractUpdateUrl(ProductModel product, ContractModel contract, BundleTemplateModel focusedComponent) {
        // HACK: if product is related to Credits than you can't order it with one click
        if( canBePaidWithCredits(product) ) {
            return "/guidedselling/edit/" + contract.getCode() + "/focus/" + focusedComponent.getId() + "/preselect/" + product.getCode();
        }

        return super.resolveContractUpdateUrl(product, contract, focusedComponent);
    }


    protected boolean canBePaidWithCredits(ProductModel product) {
        final Double credits = loyaltyPointsService.getLoyaltyPointsPriceForProduct(product);

        return credits != null;
    }

    public LoyaltyPointsService getLoyaltyPointsService() {
        return loyaltyPointsService;
    }

    @Required
    public void setLoyaltyPointsService(LoyaltyPointsService loyaltyPointsService) {
        this.loyaltyPointsService = loyaltyPointsService;
    }
}
