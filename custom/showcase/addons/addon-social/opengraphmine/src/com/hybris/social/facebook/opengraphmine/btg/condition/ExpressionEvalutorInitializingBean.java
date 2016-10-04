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
package com.hybris.social.facebook.opengraphmine.btg.condition;

import de.hybris.platform.btg.condition.ExpressionEvaluator;
import de.hybris.platform.btg.condition.ExpressionEvaluatorRegistry;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;


/**
 * Simple InitializingBean allowing us to register additional BTG expression evalutors.
 * 
 * @author rmcotton
 */
public class ExpressionEvalutorInitializingBean implements InitializingBean
{
	private ExpressionEvaluatorRegistry registry;
	private List<ExpressionEvaluator> expressionEvaluators;


	public ExpressionEvaluatorRegistry getRegistry()
	{
		return registry;
	}

	@Required
	public void setRegistry(final ExpressionEvaluatorRegistry registry)
	{
		this.registry = registry;
	}

	public List<ExpressionEvaluator> getExpressionEvaluators()
	{
		return expressionEvaluators;
	}

	@Required
	public void setExpressionEvaluators(final List<ExpressionEvaluator> expressionEvaluators)
	{
		this.expressionEvaluators = expressionEvaluators;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception
	{
		for (final ExpressionEvaluator evaluator : getExpressionEvaluators())
		{
			getRegistry().addExpressionEvaluator(evaluator);
		}

	}

}
