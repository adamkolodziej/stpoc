package com.hybris.platform.ticketing.daos.impl;

import com.hybris.platform.ticketing.daos.EntiltementsDao;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;
import de.hybris.platform.jalo.user.Customer;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import org.springframework.beans.factory.annotation.Required;

import java.util.HashMap;
import java.util.Map;

public class DefaultEntitlementsDao implements EntiltementsDao {

	public static final String ENTITLEMENTS_CONSOLE_GET_POSSIBLE_TO_GRANT_QUERY = ""
			+ //
			"select {c.pk} from {ProductEntitlement as c} " + //
			"where ({c.giftValue} > ?c4CsurveyRecommendedValue_09 " + //
			"and {c.giftValue} > ?c4CsurveyRecommendedValue_11)";

	private FlexibleSearchService flexibleSearchService;

	@Override
	public ProductEntitlementModel getProductEntitlementsForUser(
			Customer customer, Integer c4CsurveyRecommendedValue) {
		try {
			double c4CsurveyRecommendedValue_09 = 0.9 * c4CsurveyRecommendedValue;
			double c4CsurveyRecommendedValue_11 = 1.1 * c4CsurveyRecommendedValue;

			final Map<String, Object> params = new HashMap<String, Object>();
			params.put("c4CsurveyRecommendedValue_09",
					(Object) c4CsurveyRecommendedValue_09);
			params.put("c4CsurveyRecommendedValue_11",
					(Object) c4CsurveyRecommendedValue_11);
			final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(
					ENTITLEMENTS_CONSOLE_GET_POSSIBLE_TO_GRANT_QUERY, params);

			final SearchResult<CatalogVersionModel> result = flexibleSearchService
					.search(searchQuery);
			return (ProductEntitlementModel) result.getResult();
		} catch (final ModelNotFoundException e) {
			return null;
		}
	}

	protected FlexibleSearchService getFlexibleSearchService() {
		return flexibleSearchService;
	}

	@Required
	public void setFlexibleSearchService(
			final FlexibleSearchService flexibleSearchService) {
		this.flexibleSearchService = flexibleSearchService;
	}
}
