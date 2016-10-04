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
package com.hybris.social.facebook.opengraphmine.btg.condition.impl;

import de.hybris.platform.btg.condition.impl.AbstractExpressionEvaluator;
import de.hybris.platform.core.enums.Gender;


/**
 * @author rmcotton
 * 
 */
public class GenderExpressionEvaluator extends AbstractExpressionEvaluator
{
	private static final String EQUALS = "equals";
	private static final String NOT_EQUALS = "notEquals";

	public GenderExpressionEvaluator()
	{
		super(Gender.class);
		this.allowLeftNull = true;
		addSupportedOperator(EQUALS, Gender.class);
		addSupportedOperator(NOT_EQUALS, Gender.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.btg.condition.impl.AbstractExpressionEvaluator#evaluateTerm(java.lang.Object,
	 * java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean evaluateTerm(final Object leftOperand, final String operator, final Object rightOperand)
	{
		if (leftOperand == null)
		{
			return false;
		}
		else if (operator.equals(EQUALS))
		{
			return leftOperand.equals(rightOperand);
		}
		else
		{
			return !leftOperand.equals(rightOperand);
		}
	}

}
