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
package com.hybris.social.facebook.opengraphmine.setup;

import de.hybris.platform.btg.enums.BTGRuleType;
import de.hybris.platform.btg.model.BTGConfigModel;
import de.hybris.platform.btg.services.BTGConfigurationService;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.model.type.ComposedTypeModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.type.TypeService;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.facebook.opengraphmine.constants.OpengraphmineConstants;
import com.hybris.social.facebook.opengraphmine.model.BTGAbstractFacebookOperandModel;


/**
 * Add Facebook Rule type and all Operands to the BTG Configuration.
 * 
 * @author rmcotton
 * 
 */
@SystemSetup(extension = OpengraphmineConstants.EXTENSIONNAME)
public class OpenGraphMineSystemSetup
{
	private BTGConfigurationService btgConfigurationService;
	private TypeService typeService;
	private ModelService modelService;


	@SystemSetup(type = SystemSetup.Type.ALL, process = SystemSetup.Process.ALL)
	public void configureBTG(final SystemSetupContext context)
	{
		final BTGConfigModel config = getBtgConfigurationService().getConfig();
		if (!config.getUsedRuleTypes().contains(BTGRuleType.FACEBOOK))
		{
			final Set<BTGRuleType> ruleTypes = new LinkedHashSet<BTGRuleType>();
			ruleTypes.addAll(config.getUsedRuleTypes());
			ruleTypes.add(BTGRuleType.FACEBOOK);
			config.setUsedRuleTypes(ruleTypes);
		}
		final Map<BTGRuleType, Collection<ComposedTypeModel>> operandMappings = new LinkedHashMap<BTGRuleType, Collection<ComposedTypeModel>>(
				config.getOperandMapping());
		Collection<ComposedTypeModel> operands = operandMappings.get(BTGRuleType.FACEBOOK);
		if (operands == null)
		{
			operands = new LinkedHashSet<ComposedTypeModel>();
		}
		else
		{
			operands = new LinkedHashSet<ComposedTypeModel>(operands);
		}
		operandMappings.put(BTGRuleType.FACEBOOK, operands);

		final ComposedTypeModel baseOperandType = getTypeService().getComposedTypeForClass(BTGAbstractFacebookOperandModel.class);
		for (final ComposedTypeModel subType : baseOperandType.getAllSubTypes())
		{
			if (Boolean.FALSE.equals(subType.getAbstract()) && !operands.contains(subType))
			{
				operands.add(subType);
			}
		}
		config.setOperandMapping(operandMappings);
		getModelService().save(config);

	}

	public BTGConfigurationService getBtgConfigurationService()
	{
		return btgConfigurationService;
	}

	@Required
	public void setBtgConfigurationService(final BTGConfigurationService btgConfigurationService)
	{
		this.btgConfigurationService = btgConfigurationService;
	}

	public TypeService getTypeService()
	{
		return typeService;
	}

	@Required
	public void setTypeService(final TypeService typeService)
	{
		this.typeService = typeService;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

}
