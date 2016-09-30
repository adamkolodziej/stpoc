package com.hybris.showcase.emsextras.facades;

import com.hybris.services.entitlements.api.ExecuteResult;
import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.condition.CriterionData;
import com.hybris.showcase.servicescore.data.EntitlementProposalData;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.entitlementservices.data.EmsGrantData;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * CECS-242 entitlement check
 *
 * Created by miroslaw.szot@sap.com on 2015-05-20.
 */
public interface EMSFacade {
    String TIME_FRAME_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    List<CriterionData> createCriteriaForProduct(String productCode, String entitlementType);

    ExecuteResult check(String entitlementType, List<CriterionData> criteria, boolean details);
    ExecuteResult check(String entitlementType, List<CriterionData> criteria, String userUid, boolean details);

    ExecuteResult checkMetered(String entitlementType, List<CriterionData> criteria, int quantity, boolean details);
    ExecuteResult checkMetered(String entitlementType, List<CriterionData> criteria, String userUid, int quantity, boolean details);

    ExecuteResult use(String entitlementType, List<CriterionData> criteria, int quantity, boolean details);
    ExecuteResult use(String entitlementType, List<CriterionData> criteria, String userUid, int quantity, boolean details);
    CriterionData createMeteredCriterion(int quantity);

    CriterionData createTimeCriterion(Date date);

    CriterionData createTimeCriterion();

}
