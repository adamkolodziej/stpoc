package com.hybris.showcase.cecs.ymarketingintegration.dao.impl;

import com.google.common.collect.ImmutableMap;
import com.hybris.showcase.cecs.ymarketingintegration.dao.CEIInitiativeDAO;
import com.hybris.showcase.cecs.ymarketingintegration.model.CEIInitiativeModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collection;


public class DefaultCEIInitiativeDAO implements CEIInitiativeDAO
{

	private FlexibleSearchService flexibleSearchService;

	@Override
	public Collection<CEIInitiativeModel> findInitiativesForUser(final UserModel user)
	{
		final SearchResult<CEIInitiativeModel> result = getFlexibleSearchService().search(
				"select {pk} from {CEIInitiative} where {user} = ?user", ImmutableMap.of("user", user));
		return result.getResult();
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
