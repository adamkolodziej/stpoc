/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 * 
 */
package com.hybris.social.facebook.common.service.dao.impl;

import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.tx.Transaction;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hybris.social.facebook.common.model.FacebookApplicationDomainModel;
import com.hybris.social.facebook.common.model.FacebookApplicationModel;
import com.hybris.social.facebook.common.service.dao.FacebookApplicationDao;



/**
 * @author rmcotton
 * 
 */
public class DefaultFacebookApplicationDao extends AbstractItemDao implements FacebookApplicationDao
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.social.facebook.service.dao.FacebookApplicationDao#getDefaultApplicationForDomain(java.lang
	 * .String)
	 */
	@Override
	public FacebookApplicationModel findDefaultApplicationForDomain(final String domain)
	{
		final String query = "select {app.PK} from {FacebookApplication as app}, {FacebookApplication2Domain as app2domain}, {FacebookApplicationDomain as domain} where {app.PK} = {app2domain.source} and {app2domain.target} = {domain.PK} and {domain.domain} = ?domain and {app.default} =?true";
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery(query);
		fsq.addQueryParameter("true", Boolean.TRUE);
		fsq.addQueryParameter("domain", domain);
		final SearchResult<FacebookApplicationModel> result = getFlexibleSearchService().search(fsq);
		if (result.getCount() > 0)
		{
			return result.getResult().get(0);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.social.facebook.service.dao.FacebookApplicationDao#findDefaultApplicationForSubDomains(java
	 * .util.List)
	 */
	@Override
	public FacebookApplicationModel findDefaultApplicationForSubDomains(final List<String> subDomains)
	{
		final String queryPrefix = "select {app.PK} from {FacebookApplication as app}, {FacebookApplication2Domain as app2domain}, {FacebookApplicationDomain as domain} "
				+ " where {app.PK} = {app2domain.source} and {app2domain.target} = {domain.PK} and {app.default} =?true"
				+ " and {domain.domain} in (?subDomains) " + " order by ";

		final StringBuilder orderBy = new StringBuilder("( CASE {domain.domain} ");
		int counter = 0;
		for (final String domain : subDomains)
		{
			orderBy.append("when '").append(domain).append("' THEN ").append(counter++).append(" ");
		}
		orderBy.append(" END ) ASC ");
		final String query = queryPrefix + orderBy.toString();
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery(query);
		fsq.addQueryParameter("true", Boolean.TRUE);
		fsq.addQueryParameter("subDomains", subDomains);
		final SearchResult<FacebookApplicationModel> result = getFlexibleSearchService().search(fsq);
		if (result.getCount() > 0)
		{
			return result.getResult().get(0);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.social.facebook.service.dao.FacebookApplicationDao#findDomain(java.lang.String)
	 */
	@Override
	public FacebookApplicationDomainModel findDomain(final String domain)
	{
		final FacebookApplicationDomainModel example = new FacebookApplicationDomainModel();
		example.setDomain(domain);
		try
		{
			return getFlexibleSearchService().getModelByExample(example);
		}
		catch (final ModelNotFoundException e)
		{
			return null;
		}
	}

	@Override
	public FacebookApplicationModel findApplication(final Long appId)
	{
		final FacebookApplicationModel example = new FacebookApplicationModel();
		example.setApplicationId(appId);
		try
		{
			return getFlexibleSearchService().getModelByExample(example);
		}
		catch (final ModelNotFoundException e)
		{
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.social.facebook.service.dao.FacebookApplicationDao#createDefaultFacebookApplication(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	@Override
	public FacebookApplicationModel createDefaultFacebookApplication(final Long appId, final String secret, final String domain)
	{
		final Transaction tx = Transaction.current();
		tx.begin();
		boolean complete = false;
		try
		{
			final FacebookApplicationDomainModel domainModel = optimisticallyCreateDomain(domain);
			if (domain == null)
			{
				throw new IllegalStateException("unable to create facebook application. Could not create or get domain [" + domain
						+ "]");
			}
			final FacebookApplicationModel application = optimisticallyCreateApplication(appId, secret, domainModel);
			complete = true;
			return application;
		}
		finally
		{
			if (complete && !tx.isRollbackOnly())
			{
				tx.commit();
			}
			else
			{
				tx.rollback();
			}
		}

	}

	public FacebookApplicationModel optimisticallyCreateApplication(final Long appId, final String appSecret,
			final FacebookApplicationDomainModel domain)
	{
		FacebookApplicationModel application = findApplication(appId);
		if (application == null)
		{
			application = getModelService().create(FacebookApplicationModel.class);
			getModelService().initDefaults(application);
			application.setApplicationId(appId);
			application.setApplicationSecret(appSecret);
			application.setDomains(Collections.singleton(domain));
		}
		else
		{
			final Set<FacebookApplicationDomainModel> domains = new HashSet<FacebookApplicationDomainModel>(application.getDomains());
			application.setDomains(domains);
		}
		getModelService().save(application);
		return application;
	}

	public FacebookApplicationDomainModel optimisticallyCreateDomain(final String domain)
	{
		final FacebookApplicationDomainModel example = getModelService().create(FacebookApplicationDomainModel.class);
		example.setDomain(domain);
		try
		{
			getModelService().save(example);
			return example;
		}
		catch (final ModelSavingException e)
		{
			return findDomain(domain);
		}
	}
}
