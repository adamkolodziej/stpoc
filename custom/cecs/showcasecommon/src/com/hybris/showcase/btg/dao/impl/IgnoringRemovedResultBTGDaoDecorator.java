package com.hybris.showcase.btg.dao.impl;

import de.hybris.platform.btg.dao.impl.ResultCachingBTGDaoDecorator;
import de.hybris.platform.btg.enums.BTGResultScope;
import de.hybris.platform.btg.model.BTGConditionModel;
import de.hybris.platform.btg.model.BTGConditionResultModel;
import de.hybris.platform.btg.model.BTGRuleModel;
import de.hybris.platform.btg.model.BTGRuleResultModel;
import de.hybris.platform.btg.model.BTGSegmentModel;
import de.hybris.platform.btg.model.BTGSegmentResultModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.exceptions.ModelLoadingException;

import org.apache.log4j.Logger;


public class IgnoringRemovedResultBTGDaoDecorator extends ResultCachingBTGDaoDecorator
{

	private static final Logger LOG = Logger.getLogger(IgnoringRemovedResultBTGDaoDecorator.class);

	@Override
	public BTGSegmentResultModel getLastResult(final UserModel user, final BTGSegmentModel segment,
			final BTGResultScope resultScope, final String jaloSessionId)
	{
		try
		{
			return super.getLastResult(user, segment, resultScope, jaloSessionId);
		}
		catch (final ModelLoadingException e)
		{
			logLoadingFailure(e);
			return null;
		}
	}

	@Override
	public BTGRuleResultModel getLastResult(final UserModel user, final BTGRuleModel rule, final BTGResultScope resultScope,
			final String jaloSessionId)
	{
		try
		{
			return super.getLastResult(user, rule, resultScope, jaloSessionId);
		}
		catch (final ModelLoadingException e)
		{
			logLoadingFailure(e);
			return null;
		}
	}

	@Override
	public BTGConditionResultModel getLastResult(final UserModel user, final BTGConditionModel condition,
			final BTGResultScope resultScope, final String jaloSessionId)
	{
		try
		{
			return super.getLastResult(user, condition, resultScope, jaloSessionId);
		}
		catch (final ModelLoadingException e)
		{
			logLoadingFailure(e);
			return null;
		}
	}

	private void logLoadingFailure(final ModelLoadingException e)
	{
		LOG.info("Ignoring failed BTG result loading: " + e.getMessage());
	}
}
