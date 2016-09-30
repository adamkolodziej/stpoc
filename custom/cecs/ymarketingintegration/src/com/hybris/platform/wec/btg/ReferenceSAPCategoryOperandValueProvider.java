package com.hybris.platform.wec.btg;

import de.hybris.platform.btg.condition.operand.valueproviders.CollectionOperandValueProvider;
import de.hybris.platform.btg.enums.BTGConditionEvaluationScope;
import de.hybris.platform.core.model.user.UserModel;

import java.util.ArrayList;
import java.util.Collection;

import com.hybris.showcase.cecs.ymarketingintegration.btg.model.BTGReferenceSAPCategoryOperandModel;


/**
 *
 * @author marius.bogdan.ionescu@sap.com
 *
 */
public class ReferenceSAPCategoryOperandValueProvider implements
		CollectionOperandValueProvider<BTGReferenceSAPCategoryOperandModel>
{
	@Override
	public Object getValue(final BTGReferenceSAPCategoryOperandModel operand, final UserModel user,
			final BTGConditionEvaluationScope evaluationScope)
	{
		final Collection result = new ArrayList<String>();
		result.addAll(operand.getCategories());
		return result;
	}

	@Override
	public Class getValueType(final BTGReferenceSAPCategoryOperandModel operand)
	{
		return SAPCategorySet.class;
	}

	@Override
	public Class getAtomicValueType(final BTGReferenceSAPCategoryOperandModel operand)
	{
		return String.class;
	}
}
