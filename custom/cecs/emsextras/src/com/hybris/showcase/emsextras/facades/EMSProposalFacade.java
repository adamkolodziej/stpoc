package com.hybris.showcase.emsextras.facades;

import com.hybris.services.entitlements.api.ExecuteResult;
import com.hybris.services.entitlements.condition.CriterionData;
import com.hybris.showcase.servicescore.data.EntitlementProposalData;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.entitlementfacades.data.EntitlementData;

import java.util.Collection;
import java.util.List;

/**
 * CECS-193 Notify customer about expiring subscriptions or entitlements
 *
 * Created by miroslaw.szot@sap.com on 2015-05-20.
 */
public interface EMSProposalFacade {

    List<EntitlementProposalData> getProposals(String productCode, String entitlementType, String userUid, Collection<ProductOption> options);

    List<EntitlementData> getExpiredGrants(ExecuteResult... meteredResult);
}
