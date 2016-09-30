package com.hybris.ymarketingintegration.setup;

import de.hybris.platform.btg.enums.BTGRuleType;
import de.hybris.platform.btg.model.BTGConfigModel;
import de.hybris.platform.btg.services.BTGConfigurationService;
import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;
import de.hybris.platform.core.model.type.ComposedTypeModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.type.TypeService;

import java.util.*;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.cecs.ymarketingintegration.btg.model.BTGSAPCategoryOperandModel;
import com.hybris.showcase.cecs.ymarketingintegration.constants.YmarketingintegrationConstants;


/**
 *
 * @author marius.bogdan.ionescu@sap.com
 *
 */
@SystemSetup(extension = YmarketingintegrationConstants.EXTENSIONNAME)
public class YMktIntSetup extends AbstractSystemSetup
{
	private BTGConfigurationService btgConfigurationService;
	private ModelService modelService;
	private TypeService typeService;

	@Override
	@SystemSetupParameterMethod
	public List<SystemSetupParameter> getInitializationOptions() {
		return Collections.emptyList();
	}

	/**
	 * @param context
	 */
	@SystemSetup(type = SystemSetup.Type.ALL, process = SystemSetup.Process.ALL)
	public void importProjectData(final SystemSetupContext context)
	{
		configureBTG(context);
		importImpexFile(context, "/ymarketingintegration/import/ymarketing_config.impex", false);
	}

	private void configureBTG(SystemSetupContext context) {
		final BTGConfigModel config = getBtgConfigurationService().getConfig();
		final Map<BTGRuleType, Collection<ComposedTypeModel>> operandMappings = new LinkedHashMap<BTGRuleType, Collection<ComposedTypeModel>>(
				config.getOperandMapping());
		Collection<ComposedTypeModel> operands = operandMappings.get(BTGRuleType.USER);
		if (operands == null)
		{
			operands = new LinkedHashSet<ComposedTypeModel>();
		}
		else
		{
			operands = new LinkedHashSet<ComposedTypeModel>(operands);
		}
		operandMappings.put(BTGRuleType.USER, operands);

		final ComposedTypeModel operand = getTypeService().getComposedTypeForClass(BTGSAPCategoryOperandModel.class);
		if (!operands.contains(operand))
		{
			operands.add(operand);
		}
		config.setOperandMapping(operandMappings);
		getModelService().save(config);
	}

	/**
	 * @return btgConfigurationService
	 */
	public BTGConfigurationService getBtgConfigurationService()
	{
		return btgConfigurationService;
	}

	/**
	 * @param btgConfigurationService
	 */
	@Required
	public void setBtgConfigurationService(final BTGConfigurationService btgConfigurationService)
	{
		this.btgConfigurationService = btgConfigurationService;
	}

	protected ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	protected TypeService getTypeService()
	{
		return typeService;
	}

	/**
	 * @param typeService
	 */
	@Required
	public void setTypeService(final TypeService typeService)
	{
		this.typeService = typeService;
	}


}
