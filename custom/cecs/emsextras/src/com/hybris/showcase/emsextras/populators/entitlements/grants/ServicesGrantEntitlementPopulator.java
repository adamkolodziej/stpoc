/**
 * 
 */
package com.hybris.showcase.emsextras.populators.entitlements.grants;

import de.hybris.platform.entitlementfacades.data.EntitlementData;
import de.hybris.platform.entitlementfacades.entitlement.populator.GrantEntitlementPopulator;
import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;

import java.util.Collection;
import java.util.Date;

import javax.annotation.Nullable;

import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.condition.ConditionData;
import com.hybris.showcase.dao.ProductEntitlementDao;


/**
 * @author I307113
 * 
 */
public class ServicesGrantEntitlementPopulator<SOURCE extends GrantData, TARGET extends EntitlementData> extends
		GrantEntitlementPopulator<SOURCE, TARGET>
{
	private static final Logger LOG = Logger.getLogger(ServicesGrantEntitlementPopulator.class);

	private static final String GRANT_REMAINING_QUANTITY = "remainingQuantity";
	private ProductEntitlementDao productEntitlementDao;

	@Override
	public void populate(final SOURCE source, final TARGET target) throws ConversionException
	{
		super.populate(source, target);

		populateQuantities(source, target);

		// CECS-351 EMS: grant source and entitlement's names on account page
		populateGrantSource(source, target);

		populateGeoLocation(source, target);
	}

	protected String getConditionProperty(SOURCE source, String conditionType, String property) {
		for (ConditionData condition : source.getConditions()) {
			if( condition.getType().equals(conditionType) ) {
				return condition.getProperty(property);
			}
		}
		return null;
	}

	private Collection<String> getConditionMultiValueProperty(final SOURCE source, final String conditionType,
			final String property)
	{
		final Collection<ConditionData> filteredConditions = Collections2.filter(source.getConditions(),
				new Predicate<ConditionData>()
				{
					@Override
					public boolean apply(ConditionData condition)
					{
						return StringUtils.equals(condition.getType(), conditionType);
					}
				});
		return Collections2.transform(filteredConditions, new Function<ConditionData, String>()
		{
			@Nullable
			@Override
			public String apply(ConditionData condition)
			{
				return condition.getProperty(property);
			}
		});
	}

	protected void populateGrantSource(SOURCE source, TARGET target) {
		final String grantSource = source.getGrantSource();
		if (StringUtils.isNotBlank(grantSource))
		{
			ProductEntitlementModel productEntitlement = getProductEntitlement(grantSource);
			if( productEntitlement != null ) {
				target.setName(StringUtils.defaultIfBlank(productEntitlement.getDescription(), productEntitlement.getEntitlement().getName()));
			}
		}
	}

	protected void populateQuantities(SOURCE source, TARGET target) {
		// by default (5.4) quantity is loaded from hybris model, but in fact it can be changed in ems-ui
		// this could be related to https://jira.hybris.com/browse/NON-547
		final String quantity = getConditionProperty(source, "metered", "maxQuantity");
		if( StringUtils.isNotBlank( quantity )) {
			try {
				target.setQuantity(Integer.valueOf(quantity));
			} catch (NumberFormatException e) {
				LOG.error("maxQuantity doesn't look like a number.", e);
			}
		}
		// this is how we can get overage flag
		//target.setAllowOverage(Boolean.valueOf(getConditionProperty(source, "metered", "allowOverage")));

		final String grantRemainQty = source.getProperty(GRANT_REMAINING_QUANTITY);
		if (StringUtils.isNotBlank(grantRemainQty))
		{
			target.setRemainingQuantity(Integer.parseInt(grantRemainQty));
		}
	}

	protected ProductEntitlementModel getProductEntitlement(String grantSource) {
		try {
			return productEntitlementDao.findById(grantSource);
		}catch (ModelNotFoundException e) {
			return null;
		}
	}

	private void populateGeoLocation(final SOURCE source, final TARGET target) {
		final Collection<String> geoPath = getConditionMultiValueProperty(source, "geo", "geoPath");
		target.setConditionGeo(geoPath);
	}

	public ProductEntitlementDao getProductEntitlementDao() {
		return productEntitlementDao;
	}

	@Required
	public void setProductEntitlementDao(ProductEntitlementDao productEntitlementDao) {
		this.productEntitlementDao = productEntitlementDao;
	}
}
