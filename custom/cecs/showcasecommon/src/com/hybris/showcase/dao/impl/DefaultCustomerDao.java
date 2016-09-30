/**
 *
 */
package com.hybris.showcase.dao.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.dao.CustomerDao;


/**
 * @author Rafal Zymla
 */
public class DefaultCustomerDao implements CustomerDao
{

	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<CustomerModel> getCustomersWithLoginEnabled()
	{
		final String queryString = //
		"SELECT {p:" + CustomerModel.PK + "} "//
				+ "FROM {" + CustomerModel._TYPECODE + " AS p} WHERE {p:loginDisabled} = ?loginDisabled";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("loginDisabled", Boolean.FALSE);

		return flexibleSearchService.<CustomerModel> search(query).getResult();
	}

	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	@Override
	public Collection<CustomerModel> getCustomers()
	{
		final SearchResult<CustomerModel> result = flexibleSearchService
				.search("select {pk} from {Customer} where {sapConsumerID} is not null or {sapContactID} is not null");
		return result.getResult();
	}

}
