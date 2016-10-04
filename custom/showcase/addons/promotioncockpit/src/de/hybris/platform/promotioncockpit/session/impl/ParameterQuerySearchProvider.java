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
package de.hybris.platform.promotioncockpit.session.impl;

import de.hybris.platform.cockpit.model.search.Query;
import de.hybris.platform.cockpit.services.search.impl.GenericQuerySearchProvider;
import de.hybris.platform.core.GenericCondition;
import de.hybris.platform.core.GenericQuery;
import de.hybris.platform.promotioncockpit.filters.QuerySearchProviderConditionAppender;

import java.util.List;


/**
 * QuerySearchProvider that can be extended with objects that implements QuerySearchProviderConditionAppender interface.
 * 
 * @author krzysztof.baranski
 */
public class ParameterQuerySearchProvider extends GenericQuerySearchProvider
{
	List<QuerySearchProviderConditionAppender> conditionAppenders;

	@Override
	public List<GenericCondition> createConditions(final Query query, final GenericQuery genQuery)
	{
		final List<GenericCondition> conditions = super.createConditions(query, genQuery);
		if (conditionAppenders != null && !conditionAppenders.isEmpty())
		{
			for (final QuerySearchProviderConditionAppender appender : conditionAppenders)
			{
				appender.appendConditions(conditions, query, genQuery);
			}
		}
		return conditions;
	}

	// Getters and setters

	public List<QuerySearchProviderConditionAppender> getConditionAppenders()
	{
		return conditionAppenders;
	}

	public void setConditionAppenders(final List<QuerySearchProviderConditionAppender> conditionAppenders)
	{
		this.conditionAppenders = conditionAppenders;
	}
}
