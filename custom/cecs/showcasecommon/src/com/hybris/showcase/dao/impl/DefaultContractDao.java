package com.hybris.showcase.dao.impl;

import com.google.common.collect.ImmutableMap;
import com.hybris.showcase.dao.ContractDao;
import com.hybris.showcase.model.ContractModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;
import java.util.Map;

public class DefaultContractDao implements ContractDao
{

	public static final String LATEST_CONTRACT_QUERY = "" + //
			" select {c.pk} from {Contract as c} " + //
			"  where {c.user} = ?user " + //
			"    and {c.modifiedtime} = ({{ " + //
			"	        select max({cc.modifiedtime}) from {Contract as cc} " + //
			"            where {cc.user} = ?user }})";

	private FlexibleSearchService flexibleSearchService;

	@Override
	public ContractModel getContractForCode(final String code)
	{
		final ContractModel example = new ContractModel();
		example.setCode(code);
		final List<ContractModel> result = getFlexibleSearchService().getModelsByExample(example);
		if (CollectionUtils.isNotEmpty(result))
		{
			return result.iterator().next();
		}
		else
		{
			return null;
		}
	}

	@Override
	public ContractModel getLatestContractForUser(final UserModel user)
	{
		try
		{
			final Map<String, Object> parameters = ImmutableMap.of("user", (Object) user);
			final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(LATEST_CONTRACT_QUERY, parameters);
			return getFlexibleSearchService().searchUnique(searchQuery);
		}
		catch (final ModelNotFoundException e)
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
