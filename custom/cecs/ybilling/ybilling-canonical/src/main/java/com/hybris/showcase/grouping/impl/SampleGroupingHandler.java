package com.hybris.showcase.grouping.impl;

import com.hybris.datahub.model.RawItem;
import com.hybris.datahub.grouping.GroupingHandler;
import com.hybris.datahub.model.CompositionGroup;

import java.util.List;

public class SampleGroupingHandler implements GroupingHandler
{
	@Override
	public <T extends RawItem> List<CompositionGroup<T>> group(final CompositionGroup<T> tCompositionGroup)
	{
		return null;
	}

	@Override
	public <T extends RawItem> boolean isApplicable(final CompositionGroup<T> tCompositionGroup)
	{
		return false;
	}

	@Override
	public int getOrder()
	{
		return 0;
	}
}
