package com.hybris.showcase.ybillingintegration.facades.impl;

import static java.lang.System.err;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.sap.productconfig.runtime.interf.ConfigurationProvider;
import de.hybris.platform.sap.productconfig.runtime.interf.ConfigurationProviderFactory;
import de.hybris.platform.sap.productconfig.runtime.interf.impl.KBKeyImpl;
import de.hybris.platform.sap.productconfig.runtime.interf.model.ConfigModel;
import de.hybris.platform.sap.productconfig.runtime.interf.model.CsticModel;
import de.hybris.platform.sap.productconfig.runtime.interf.model.CsticValueModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.ybillingintegration.facades.SSCConfigurationManager;


/**
 * CECS-700 Get rid of yBilling dependency on guideselling<br />
 * CECS-668 evaluate SSC rules during guidedselling <br />
 * Created by miroslaw.szot@sap.com on 2016-01-26.
 */
public class DefaultSSCConfigurationManager implements SSCConfigurationManager
{
	private static final Logger LOG = Logger.getLogger(DefaultSSCConfigurationManager.class);
	private ConfigurationProviderFactory configurationProviderFactory;
	private ConfigurationService configurationService;
	private ModelService modelService;

	private String getStoredConfigId(AbstractOrderEntryModel entry)
	{
		final AbstractOrderModel order = entry.getOrder().getParent() != null ? entry.getOrder().getParent() : entry.getOrder();
		final Map<Integer, String> bundleNoToConfigId = order.getBundleNoToConfigId();
		if (bundleNoToConfigId == null)
		{
			return null;
		}
		return bundleNoToConfigId.get(entry.getBundleNo());
	}

	private void updateStoredConfigId(AbstractOrderEntryModel entry, String configId)
	{
		final AbstractOrderModel order = entry.getOrder().getParent() != null ? entry.getOrder().getParent() : entry.getOrder();
		Map<Integer, String> bundleNoToConfigId = order.getBundleNoToConfigId();
		if (bundleNoToConfigId == null)
		{
			bundleNoToConfigId = new HashMap<>();
		}
		else
		{
			bundleNoToConfigId = new HashMap<>(bundleNoToConfigId);
		}

		if (StringUtils.isBlank(configId))
		{
			bundleNoToConfigId.remove(entry.getBundleNo());
		}
		else
		{
			bundleNoToConfigId.put(entry.getBundleNo(), configId);
		}
		order.setBundleNoToConfigId(bundleNoToConfigId);
		modelService.save(order);
	}

	@Override
	public ConfigModel createConfiguration(AbstractOrderEntryModel entry)
	{
		releaseSession(entry);

		final String yBillingId = entry.getBundleTemplate().getParentTemplate().getYBillingId();
		final KBKeyImpl kbKeyImpl = new KBKeyImpl(yBillingId, null, null, null);
		final ConfigModel configModel = getConfigurationProvider().createDefaultConfiguration(kbKeyImpl);
		updateStoredConfigId(entry, configModel.getId());

		if (LOG.isDebugEnabled())
		{
			dumpConfigInfo(configModel);
		}
		return configModel;
	}

	@Override
	public ConfigModel retrieveConfiguration(AbstractOrderEntryModel entry)
	{
		final String configId = getStoredConfigId(entry);
		final ConfigModel configModel = getConfigurationProvider().retrieveConfigurationModel(configId);

		return configModel;
	}

	@Override
	public ConfigModel updateConfiguration(ConfigModel configModel)
	{
		final boolean success = getConfigurationProvider().updateConfiguration(configModel);
		if (!success)
		{
			LOG.error("Problem updating configuration: " + configModel.getId() + " / " + configModel.getRootInstance().getName());
		}

		final ConfigModel updatedConfig = getConfigurationProvider().retrieveConfigurationModel(configModel.getId());

		if (LOG.isDebugEnabled())
		{
			dumpConfigInfo(updatedConfig);
		}

		return updatedConfig;
	}

	@Override
	public void releaseSession(AbstractOrderEntryModel entry)
	{
		String configId = getStoredConfigId(entry);
		if (StringUtils.isNotBlank(configId))
		{
			getConfigurationProvider().releaseSession(configId);
		}
		updateStoredConfigId(entry, null);
	}

	protected ConfigurationProvider getConfigurationProvider()
	{
		final ConfigurationProvider configurationProvider = configurationProviderFactory.getProvider();
		return configurationProvider;
	}

	private void dumpConfigInfo(ConfigModel configModel)
	{
		err.println();
		err.println("config id: " + configModel.getId());
		err.println("config instance id: " + configModel.getRootInstance().getId());
		err.println("config instance name: " + configModel.getRootInstance().getName());
		err.println("name: " + configModel.getRootInstance().getLanguageDependentName());
		err.println();
		final List<CsticModel> cstics = configModel.getRootInstance().getCstics();
		for (CsticModel cstic : cstics)
		{
			err.println("cstic " + cstic.getName());
			err.println("cstic " + cstic.getLanguageDependentName());
			final List<CsticValueModel> assignableValues = cstic.getAssignableValues();
			for (CsticValueModel value : assignableValues)
			{
				err.println("cstic value: '" + value.getName() + "' " + value.getDeltaPrice().getPriceValue() + " "
						+ value.getDeltaPrice().getCurrency());
			}
			final List<CsticValueModel> assignedValues = cstic.getAssignedValues();
			for (CsticValueModel value : assignedValues)
			{
				err.println("Assigned cstic value: '" + value.getName() + "' " + value.getDeltaPrice().getPriceValue() + " "
						+ value.getDeltaPrice().getCurrency());
			}
			err.println();
		}
	}

	@Override
	public boolean isSlcEnabled()
	{
		final boolean ybillingEnabled = configurationService.getConfiguration()
				.getBoolean("showcasecommon.ybillingintegration.enabled", false);

		final boolean slcConfigured = StringUtils
				.isNotBlank(configurationService.getConfiguration().getString("crm.database_hostname"));

		return ybillingEnabled && slcConfigured;
	}

	public ConfigurationProviderFactory getConfigurationProviderFactory()
	{
		return configurationProviderFactory;
	}

	@Required
	public void setConfigurationProviderFactory(ConfigurationProviderFactory configurationProviderFactory)
	{
		this.configurationProviderFactory = configurationProviderFactory;
	}

	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	@Required
	public void setConfigurationService(ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(ModelService modelService)
	{
		this.modelService = modelService;
	}
}
