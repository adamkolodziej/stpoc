package com.hybris.showcase.ybillingintegration.order.hooks;

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.hook.CommerceUpdateCartEntryHook;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.OrderService;
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
public class SSCUpdateCartMethodHook implements CommerceUpdateCartEntryHook
{
	private static final Logger LOG = Logger.getLogger(SSCUpdateCartMethodHook.class);
	private SSCConfigurationManager sscConfigurationManager;
	private CartService cartService;

	@Override
	public void afterUpdateCartEntry(CommerceCartParameter parameter, CommerceCartModification result)
	{

	}

	@Override
	public void beforeUpdateCartEntry(CommerceCartParameter parameter)
	{
		if (!sscConfigurationManager.isSlcEnabled())
		{
			return;
		}

		if (parameter.getQuantity() > 0)
		{
			return;
		}

		final CartModel order = parameter.getCart();
		final AbstractOrderEntryModel entry = cartService.getEntryForNumber(order, Long.valueOf(parameter.getEntryNumber()).intValue());

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
				LOG.debug("Updating SSC configuration for cart " + order.getCode() + " bundle " + entry.getBundleNo());
				final ConfigModel configModel = sscConfigurationManager.retrieveConfiguration(entry);

				configModel.getRootInstance().getCstics().stream() //
						.filter(cs -> cs.getName().equals(csticCode)) //
						.findAny() //
						.ifPresent(cs -> {
							cs.removeValue(csticValue);
							sscConfigurationManager.updateConfiguration(configModel);
						});
			}
		}
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

	public CartService getCartService() {
		return cartService;
	}

	@Required
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
}
