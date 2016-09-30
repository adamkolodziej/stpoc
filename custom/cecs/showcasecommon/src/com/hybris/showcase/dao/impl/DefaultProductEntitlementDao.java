package com.hybris.showcase.dao.impl;

import com.hybris.showcase.dao.ProductEntitlementDao;
import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

/**
 *
 */
public class DefaultProductEntitlementDao extends AbstractItemDao implements ProductEntitlementDao {

    @Override
    public ProductEntitlementModel findById(String id) throws ModelNotFoundException {
        validateParameterNotNull(id, "Entitlement id must not be null");
        final ProductEntitlementModel example = new ProductEntitlementModel();
        example.setId(id);
        return getFlexibleSearchService().getModelByExample(example);
    }
}
