package com.hybris.showcase.cecs.ymarketingintegration.btg;

import de.hybris.platform.btg.condition.operand.types.StringSet;
import de.hybris.platform.btg.condition.operand.valueproviders.CollectionOperandValueProvider;
import de.hybris.platform.btg.enums.BTGConditionEvaluationScope;
import de.hybris.platform.core.model.user.UserModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.cecs.ymarketingintegration.dao.CEIInitiativeDAO;
import com.hybris.showcase.cecs.ymarketingintegration.model.CEIInitiativeModel;
import com.sap.wec.adtreco.btg.SAPInitiativeSet;
import com.sap.wec.adtreco.model.BTGSAPInitiativeOperandModel;


public class LocalSAPInitiativeValueProvider implements CollectionOperandValueProvider<BTGSAPInitiativeOperandModel>
{

	private CEIInitiativeDAO ceiInitiativeDAO;

	@Override
	public Object getValue(final BTGSAPInitiativeOperandModel operand, final UserModel user,
			final BTGConditionEvaluationScope scope)
	{
		final List<String> result = new ArrayList<>();
		final Collection<CEIInitiativeModel> initiatives = getCeiInitiativeDAO().findInitiativesForUser(user);
		for (final CEIInitiativeModel initiative : initiatives)
		{
			result.add(initiative.getId());
			//Moved to LocallyPersistedSAPCategoryValueProvider
			//result.add(initiative.getCategoryId());
		}
		return new StringSet(result);
	}

	@Override
	public Class<?> getValueType(final BTGSAPInitiativeOperandModel operand)
	{
		return SAPInitiativeSet.class;
	}

	@Override
	public Class<?> getAtomicValueType(final BTGSAPInitiativeOperandModel operand)
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
