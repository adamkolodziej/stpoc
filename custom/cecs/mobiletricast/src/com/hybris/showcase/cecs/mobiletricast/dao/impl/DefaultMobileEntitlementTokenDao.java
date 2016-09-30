package com.hybris.showcase.cecs.mobiletricast.dao.impl;

import com.hybris.showcase.cecs.mobiletricast.dao.MobileEntitlementTokenDao;
import com.hybris.showcase.cecs.mobiletricast.model.MobileEntitlementTokenModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;


public class DefaultMobileEntitlementTokenDao implements MobileEntitlementTokenDao
{

	private FlexibleSearchService flexibleSearchService;

	@Override
	public MobileEntitlementTokenModel getToken(String authToken)
	{
		final MobileEntitlementTokenModel example = new MobileEntitlementTokenModel();
		example.setToken(authToken);
		final List<MobileEntitlementTokenModel> result = getFlexibleSearchService().getModelsByExample(example);
		if (CollectionUtils.isNotEmpty(result))
		{
			return result.iterator().next();
		}
		else
		{
			return null;
		}
	}

	protected FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

}
