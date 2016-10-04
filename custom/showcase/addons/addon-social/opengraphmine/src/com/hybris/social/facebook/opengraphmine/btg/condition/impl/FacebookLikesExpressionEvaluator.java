/**
 * 
 */
package com.hybris.social.facebook.opengraphmine.btg.condition.impl;

import de.hybris.platform.btg.condition.impl.AbstractCollectionExpressionEvaluator;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import com.hybris.social.facebook.opengraphmine.btg.operand.types.FacebookLikesSet;


/**
 * @author rmcotton
 * 
 */
public class FacebookLikesExpressionEvaluator extends AbstractCollectionExpressionEvaluator
{
	public FacebookLikesExpressionEvaluator()
	{
		super();

		// add collection operators
		addSupportedOperator(CONTAINS_ALL, de.hybris.platform.btg.condition.operand.types.StringSet.class);
		addSupportedOperator(CONTAINS_ANY, de.hybris.platform.btg.condition.operand.types.StringSet.class);
		addSupportedOperator(NOT_CONTAINS, de.hybris.platform.btg.condition.operand.types.StringSet.class);
	}

	@Override
	public Class getLeftType()
	{
		return FacebookLikesSet.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.btg.condition.impl.AbstractCollectionExpressionEvaluator#checkIfContainsAny(java.lang.Object,
	 * java.util.Collection)
	 */
	@Override
	protected boolean checkIfContainsAny(final Object rightOperand, final Collection leftOperand)
	{

		final Collection rightCollection = rightOperand instanceof Collection ? (Collection) rightOperand : Collections
				.singleton(String.valueOf(rightOperand));

		for (final Iterator i = rightCollection.iterator(); i.hasNext();)
		{
			final String like = (String) i.next();

			for (final Iterator j = leftOperand.iterator(); j.hasNext();)
			{
				if (StringUtils.containsIgnoreCase((String) j.next(), like))
				{
					return true;
				}
			}
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.btg.condition.impl.AbstractCollectionExpressionEvaluator#checkIfContainsAll(java.lang.Object,
	 * java.util.Collection)
	 */
	@Override
	protected boolean checkIfContainsAll(final Object rightOperand, final Collection leftOperand)
	{
		final Collection rightCollection = rightOperand instanceof Collection ? (Collection) rightOperand : Collections
				.singleton(String.valueOf(rightOperand));

		for (final Iterator i = rightCollection.iterator(); i.hasNext();)
		{
			boolean found = false;
			final String like = (String) i.next();

			for (final Iterator j = leftOperand.iterator(); j.hasNext();)
			{
				if (StringUtils.containsIgnoreCase((String) j.next(), like))
				{
					found = true;
					break;
				}
			}
			if (!found)
			{
				return false;
			}
		}

		return true;
	}

}
