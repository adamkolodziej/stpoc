/**
 *
 */
package com.hybris.showcase.ybillingintegration.order.hooks;

import de.hybris.platform.commerceservices.order.hook.CommercePlaceOrderMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.servicelayer.exceptions.SystemException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.ybillingintegration.services.YBillingOrderService;
import com.hybris.showcase.ybillingintegration.ssc.ConfigurationMappingService;


/**
 * @author Sebastian Weiner
 */
public class YBillingOrderCreationPlaceOrderMethodHook implements CommercePlaceOrderMethodHook
{
	private static final Logger LOG = Logger.getLogger(YBillingOrderCreationPlaceOrderMethodHook.class);
	private YBillingOrderService yBillingOrderService;
	private ConfigurationMappingService configurationMappingService;

	@Override
	public void afterPlaceOrder(final CommerceCheckoutParameter parameter, final CommerceOrderResult orderModel)
			throws InvalidCartException
	{
		//do nothing
	}

	@Override
	public void beforePlaceOrder(final CommerceCheckoutParameter parameter) throws InvalidCartException
	{
		if (!configurationMappingService.isIntegrationWithYbilling())
		{
			return;
		}

		try
		{
			if (cartInEditMode(parameter))
			{
				getyBillingOrderService().changeOrder(parameter.getCart());
			}
			else
			{
				getyBillingOrderService().createOrder(parameter.getCart());
			}
		}
		catch (SystemException | IllegalArgumentException e)
		{
			LOG.fatal("Unable to complete order request for " + parameter.getCart().getUser().getUid(), e);
		}
	}

	private boolean cartInEditMode(final CommerceCheckoutParameter parameter)
	{
		return parameter.getCart().getOrderChanges() != null;
	}

	@Override
	public void beforeSubmitOrder(final CommerceCheckoutParameter parameter, final CommerceOrderResult result)
			throws InvalidCartException
	{
		//do nothing

	}

	public YBillingOrderService getyBillingOrderService()
	{
		return yBillingOrderService;
	}

	@Required
	public void setyBillingOrderService(final YBillingOrderService yBillingOrderService)
	{
		this.yBillingOrderService = yBillingOrderService;
	}

	public ConfigurationMappingService getConfigurationMappingService()
	{
		return configurationMappingService;
	}

	@Required
	public void setConfigurationMappingService(final ConfigurationMappingService configurationMappingService)
	{
		this.configurationMappingService = configurationMappingService;
	}
}
