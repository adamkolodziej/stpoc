/**
 *
 */
package com.hybris.showcase.ybillingintegration.customer.services.impl;

import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;

import org.apache.log4j.Logger;

import reactor.util.StringUtils;

import com.hybris.showcase.ybillingintegration.services.BusinessPartnerService;
import com.sap.hybris.sapcustomerb2c.outbound.DefaultB2CSapCustomerAccountService;


/**
 * @author Rafal Zymla
 */
public class YBillingCustomerAccountService extends DefaultB2CSapCustomerAccountService
{
	private static final Logger LOG = Logger.getLogger(YBillingCustomerAccountService.class);

	private BusinessPartnerService businessPartnerService;

	@Override
	public void saveAddressEntry(final CustomerModel customerModel, final AddressModel addressModel)
	{
		super.saveAddressEntry(customerModel, addressModel);

		if (StringUtils.isEmpty(customerModel.getYBillingCustomerId()))
		{
			businessPartnerService.createBusinessParter(customerModel);
		}
	}

	public BusinessPartnerService getBusinessPartnerService()
	{
		return businessPartnerService;
	}

	public void setBusinessPartnerService(final BusinessPartnerService businessPartnerService)
	{
		this.businessPartnerService = businessPartnerService;
	}




}
