package com.hybris.showcase.cecs.ymarketingintegration.btg;

import de.hybris.platform.btg.condition.operand.types.StringSet;
import de.hybris.platform.btg.condition.operand.valueproviders.CollectionOperandValueProvider;
import de.hybris.platform.btg.enums.BTGConditionEvaluationScope;
import de.hybris.platform.core.model.user.UserModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.platform.wec.btg.SAPCategorySet;
import com.hybris.showcase.cecs.ymarketingintegration.btg.model.BTGSAPCategoryOperandModel;
import com.hybris.showcase.cecs.ymarketingintegration.dao.CEIInitiativeDAO;
import com.hybris.showcase.cecs.ymarketingintegration.model.CEIInitiativeModel;


public class LocalSAPCategoryValueProvider implements CollectionOperandValueProvider<BTGSAPCategoryOperandModel>
{

	private CEIInitiativeDAO ceiInitiativeDAO;

	@Override
	public Object getValue(final BTGSAPCategoryOperandModel operand, final UserModel user, final BTGConditionEvaluationScope scope)
	{
		final List<String> result = new ArrayList<>();
		final Collection<CEIInitiativeModel> initiatives = getCeiInitiativeDAO().findInitiativesForUser(user);
		for (final CEIInitiativeModel initiative : initiatives)
		{
			result.add(initiative.getCategoryId());
			// quick hack for local initiatives - Scenario 2 - see facebook mock
			result.add(initiative.getId());
		}
		return new StringSet(result);
	}

	@Override
	public Class<?> getValueType(final BTGSAPCategoryOperandModel operand)
	{
		return SAPCategorySet.class;
	}

	@Override
	public Class<?> getAtomicValueType(final BTGSAPCategoryOperandModel operand)
	{
		return String.class;
	}

	protected CEIInitiativeDAO getCeiInitiativeDAO()
	{
		return ceiInitiativeDAO;
	}

	@Required
	public void setCeiInitiativeDAO(final CEIInitiativeDAO ceiInitiativeDAO)
	{
		this.ceiInitiativeDAO = ceiInitiativeDAO;
	}

}
