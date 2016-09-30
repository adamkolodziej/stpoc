package com.hybris.showcase.emsextras.facades;

import com.hybris.services.entitlements.api.GrantData;
import de.hybris.platform.entitlementservices.exception.EntitlementFacadeException;
import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;

import java.util.List;

public interface GrantingEntitlementsFacade {

    GrantData grantProductEntitlement(String userId, ProductEntitlementModel productEntitlementModel) throws EntitlementFacadeException;

    List<GrantData> grantEntitlementsFromProduct(String userId, String productCode) throws EntitlementFacadeException;
}
