package com.hybris.showcase.ybillingintegration.order.hooks;

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.hook.CommerceAddToCartMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.sap.productconfig.runtime.interf.model.ConfigModel;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.ybillingintegration.facades.SSCConfigurationManager;


/**
 * CECS-700 Get rid of yBilling dependency on guideselling<br />
 * CECS-668 evaluate SSC rules during guidedselling <br />
 * Created by miroslaw.szot@sap.com on 2016-01-26.
 */
public class SSCAddToCartMethodHook implements CommerceAddToCartMethodHook
{
	private static final Logger LOG = Logger.getLogger(SSCAddToCartMethodHook.class);
	private SSCConfigurationManager sscConfigurationManager;

	@Override
	public void beforeAddToCart(CommerceCartParameter parameters) throws CommerceCartModificationException
	{

	}

	// WARN this is injected into bundleAddToCartMethodHooks and it can work only there
	// probably we should turn it into separate interface
	@Override
	public void afterAddToCart(CommerceCartParameter parameters, CommerceCartModification result)
			throws CommerceCartModificationException
	{
		if (!sscConfigurationManager.isSlcEnabled())
		{
			return;
		}

		final AbstractOrderEntryModel entry = result.getEntry();
		final AbstractOrderModel order = entry.getOrder();
		boolean newBundle = isNewBundle(entry);

		final BundleTemplateModel component = entry.getBundleTemplate();
		final boolean bundleEntry = component != null;

		final String csticValue = entry.getProduct().getYBillingId();
		final boolean csticValueMapped = StringUtils.isNotBlank(csticValue);

		if (bundleEntry && csticValueMapped)
		{
			final boolean bundleMappedToSSC = StringUtils.isNotBlank(component.getParentTemplate().getYBillingId());

			final String csticCode = component.getYBillingId();

			if (bundleMappedToSSC)
			{
				final ConfigModel configModel;
				if (newBundle)
				{
					LOG.debug("Creating new SSC configuration for cart " + order.getCode() + " bundle " + entry.getBundleNo());
					configModel = sscConfigurationManager.createConfiguration(entry);
				}
				else
				{
					LOG.debug("Updating SSC  configuration for cart " + order.getCode() + " bundle " + entry.getBundleNo());
					configModel = sscConfigurationManager.retrieveConfiguration(entry);
				}

				configModel.getRootInstance().getCstics().stream() //
						.filter(cs -> cs.getName().equals(csticCode)) //
						.findAny() //
						.ifPresent(cs -> {
							cs.addValue(csticValue);
							sscConfigurationManager.updateConfiguration(configModel);
						});
			}
		}
	}

	private boolean isNewBundle(AbstractOrderEntryModel entry)
	{
		return entry.getOrder().getEntries().stream()
				.filter(e -> e.getBundleNo() != null && e.getBundleNo() > 0 && e.getBundleNo().equals(entry.getBundleNo()))
				.count() == 1;
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
