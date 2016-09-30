package com.hybris.platform.wec.btg.condition.impl;

import de.hybris.platform.btg.condition.impl.PlainCollectionExpressionEvaluator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.hybris.platform.wec.btg.SAPCategorySet;


public class SAPCategoriesExpressionEvaluator extends PlainCollectionExpressionEvaluator
{
	public SAPCategoriesExpressionEvaluator()
	{
		super(createOperatorMap());
	}

	private static Map<String, Set<Class>> createOperatorMap()
	{
		final Map<String, Set<Class>> operatorMap = new HashMap<>();
		operatorMap.put(CONTAINS_ALL, createSet(SAPCategorySet.class));
		operatorMap.put(CONTAINS_ANY, createSet(SAPCategorySet.class));
		operatorMap.put(NOT_CONTAINS, createSet(SAPCategorySet.class));
		return operatorMap;
	}

	private static Set<Class> createSet(final Class<SAPCategorySet> clazz)
	{
		final Set<Class> set = new HashSet<>(1);
		set.add(clazz);
		return set;
	}

	@Override
	public Class getLeftType()
	{
		return SAPCategorySet.class;
	}
}