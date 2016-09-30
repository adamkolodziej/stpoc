package com.hybris.platform.ticketing.daos;

import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;
import de.hybris.platform.jalo.user.Customer;


public interface EntiltementsDao
{
	ProductEntitlementModel getProductEntitlementsForUser(Customer customer, Integer c4CsurveyRecommendedValue);
}
