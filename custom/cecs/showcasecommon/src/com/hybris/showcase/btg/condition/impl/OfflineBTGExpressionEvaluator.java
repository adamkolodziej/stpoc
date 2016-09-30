package com.hybris.showcase.btg.condition.impl;

import de.hybris.platform.btg.condition.ConditionEvaluationException;
import de.hybris.platform.btg.condition.impl.DefaultBTGExpressionEvaluator;
import de.hybris.platform.btg.enums.BTGConditionEvaluationScope;
import de.hybris.platform.btg.model.BTGExpressionModel;
import de.hybris.platform.btg.model.BTGRuleModel;
import de.hybris.platform.btg.services.impl.BTGEvaluationContext;
import de.hybris.platform.core.model.user.UserModel;


/**
 * CECS-477 CSA: clicking segment re-evaluation evaluated other segments <br />
 * Created by miroslaw.szot@sap.com on 2015-08-04.
 */
public class OfflineBTGExpressionEvaluator extends DefaultBTGExpressionEvaluator
{
	@Override
	public boolean evaluate(final BTGExpressionModel condition, final UserModel user, final BTGEvaluationContext context)
			throws ConditionEvaluationException
	{
		final BTGConditionEvaluationScope contextScope = context.getEvaluationScope();
		BTGConditionEvaluationScope conditionScope = condition.getEvaluationScope();
		final BTGRuleModel rule = condition.getRule();
		final BTGConditionEvaluationScope segmentScope = rule.getSegment().getScope();

		if (conditionScope == null)
		{
			conditionScope = segmentScope;
		}

		if (contextScope == conditionScope)
		{
			return super.evaluate(condition, user, context);
		}

		return false;
	}

	protected boolean isOneRuleSegment(final BTGRuleModel rule)
	{
		return rule.getConditions().size() == 1 && rule.getSegment().getRules().size() == 1;
	}
}
