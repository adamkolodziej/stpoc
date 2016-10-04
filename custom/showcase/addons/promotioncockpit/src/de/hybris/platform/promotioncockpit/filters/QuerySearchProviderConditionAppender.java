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
package de.hybris.platform.promotioncockpit.filters;

import de.hybris.platform.cockpit.model.search.Query;
import de.hybris.platform.core.GenericCondition;
import de.hybris.platform.core.GenericQuery;

import java.util.List;


/**
 * Interface that must be implemented by classes used by {@code ParameterQuerySearchProvider} to apply configurable
 * filters on query.
 * 
 * @author krzysztof.baranski
 */
public interface QuerySearchProviderConditionAppender
{
	/**
	 * Method that allow modifying generic query.
	 * 
	 * @param conditions
	 *           Additional conditions for query.
	 * @param query
	 *           Initial query object.
	 * @param genQuery
	 *           query to which {@code conditions} will be applied.
	 */
	void appendConditions(final List<GenericCondition> conditions, final Query query, final GenericQuery genQuery);
}
