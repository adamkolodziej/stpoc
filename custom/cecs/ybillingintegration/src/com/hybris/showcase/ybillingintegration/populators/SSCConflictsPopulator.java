package com.hybris.showcase.ybillingintegration.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.sap.productconfig.runtime.interf.model.ConfigModel;
import de.hybris.platform.sap.productconfig.runtime.interf.model.ConflictModel;
import de.hybris.platform.sap.productconfig.runtime.interf.model.CsticModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.guidedselling.data.*;
import com.hybris.showcase.ybillingintegration.facades.SSCConfigurationManager;


/**
 * CECS-700 Get rid of yBilling dependency on guideselling<br />
 * CECS-668 evaluate SSC rules during guidedselling <br />
 * Created by miroslaw.szot@sap.com on 2016-01-26.
 */
public class SSCConflictsPopulator implements Populator<BundleOfferPopulatorParameters, BundleOfferData>
{
	private static final Logger LOG = Logger.getLogger(SSCConflictsPopulator.class);
	private SSCConfigurationManager sscConfigurationManager;

	@Override
	public void populate(BundleOfferPopulatorParameters parameters, BundleOfferData offerData) throws ConversionException
	{
		if (!sscConfigurationManager.isSlcEnabled())
		{
			return;
		}

		final AbstractOrderModel order = parameters.getOrder();
		if (!(order instanceof CartModel))
		{
			return;
		}

		final List<AbstractOrderEntryModel> bundleEntries = parameters.getBundleEntries();
		final List<BundleComponentData> components = offerData.getComponents();

		try
		{
			final ConfigModel configModel = sscConfigurationManager.retrieveConfiguration(bundleEntries.get(0));

			if (configModel == null){
				return;
			}

			final List<CsticModel> conflictedCstics = configModel.getRootInstance().getCstics().stream() //
					.filter(cs -> cs.hasConflicts()) //
					.collect(Collectors.toList());

			for (final BundleComponentData component : components)
			{
				conflictedCstics.stream() //
						.filter(cs -> cs.getName().equals(component.getId())) //
						.findAny() //
						.ifPresent(cs -> addConflicts(component, cs.getConflicts()));

			}
		}
		catch (IllegalStateException e)
		{
			LOG.fatal("Unable to check SSC conflicts for order: " + order.getCode(), e);
		}
		catch (Exception e){
			LOG.fatal("Exception caught while getting configuration");
			return;
		}
	}

	protected void addConflicts(BundleComponentData component, List<ConflictModel> conflicts)
	{
		List<ConfigMessage> messages = component.getMessages() == null ? new ArrayList<>() : component.getMessages();

		for (ConflictModel conflict : conflicts)
		{
			ConfigMessage configMessage = new ConfigMessage();
			configMessage.setContent(conflict.getText());
			configMessage.setSeverity(Severity.ERROR);
			messages.add(configMessage);
		}

		component.setMessages(messages);
	}

	public SSCConfigurationManager getSscConfigurationManager()
	{
		return sscConfigurationManager;
	}

	@Required
	public void setSscConfigurationManager(SSCConfigurationManager sscConfigurationManager)
	{
		this.sscConfigurationManager = sscConfigurationManager;
	}

}
