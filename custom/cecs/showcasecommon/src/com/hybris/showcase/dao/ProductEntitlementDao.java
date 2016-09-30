package com.hybris.showcase.dao;

import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;

/**
 * Created by miroslaw.szot@sap.com on 2015-05-21.
 */
public interface ProductEntitlementDao {
    ProductEntitlementModel findById(String id) throws ModelNotFoundException;
}
