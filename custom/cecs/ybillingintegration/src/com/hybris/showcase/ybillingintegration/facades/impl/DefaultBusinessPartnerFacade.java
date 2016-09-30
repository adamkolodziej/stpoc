/**
 *
 */
package com.hybris.showcase.ybillingintegration.facades.impl;

import de.hybris.platform.core.model.user.CustomerModel;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.ybillingintegration.facades.BusinessPartnerFacade;
import com.hybris.showcase.ybillingintegration.services.BusinessPartnerService;


/**
 * @author Rafal Zymla
 *
 */
public class DefaultBusinessPartnerFacade implements BusinessPartnerFacade
{

	private BusinessPartnerService businessPartnerService;

	@Override
	public String createBusinessPartner(final CustomerModel customer)
	{
		return businessPartnerService.createBusinessParter(customer);

	}


	public BusinessPartnerService getBusinessPartnerService()
	{
		return businessPartnerService;
	}

	@Required
	public void setBusinessPartnerService(final BusinessPartnerService businessPartnerService)
	{
		this.businessPartnerService = businessPartnerService;
	}





}
