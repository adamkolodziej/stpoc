package com.hybris.showcase.composition.impl;


import com.hybris.datahub.composition.CompositionRuleHandler;
import com.hybris.datahub.domain.CanonicalAttributeDefinition;
import com.hybris.datahub.model.CanonicalItem;
import com.hybris.datahub.model.RawItem;
import com.hybris.datahub.model.CompositionGroup;
import com.hybris.datahub.composition.impl.AbstractCompositionRuleHandler;

public class SampleCompositionHandler extends AbstractCompositionRuleHandler implements CompositionRuleHandler
{
	@Override
	public <T extends CanonicalItem> T compose(final CanonicalAttributeDefinition canonicalAttributeDefinition,
			final CompositionGroup<? extends RawItem> compositionGroup, final T t)
	{
		return null;
	}

	@Override
	public boolean isApplicable(final CanonicalAttributeDefinition canonicalAttributeDefinition)
	{
		return false;
	}
}
