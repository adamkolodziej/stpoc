/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.hybris.showcase.cecs.ymarketingintegration.interaction;

import de.hybris.platform.sap.core.configuration.global.SAPGlobalConfigurationService;

import javax.annotation.Resource;


/**
 *
 */
public class HMCConfigurationReader
{
	@Resource(name = "sapCoreDefaultSAPGlobalConfigurationService")
	private SAPGlobalConfigurationService globalConfigurationService;

	private String rfcDestinationId;
	private String idOrigin;

	/**
	 * Get the RFC Destination details from the hMC SAP Integration HTTP Destination configuration
	 *
	 */
	public void loadRFCConfiguration()
	{
		final String rfcId = (String) globalConfigurationService.getProperty("sapproductrecommendation_rfcdest");
		this.setRfcDestinationId(rfcId);
	}

	public void loadIdOriginConfiguration()
	{
		idOrigin = (String) globalConfigurationService.getProperty("sapadtreco_idOrigin");
		if(idOrigin == null){
			idOrigin = "";
		}
	}

	public SAPGlobalConfigurationService getGlobalConfigurationService()
	{
		return globalConfigurationService;
	}

	public void setGlobalConfigurationService(final SAPGlobalConfigurationService globalConfigurationService)
	{
		this.globalConfigurationService = globalConfigurationService;
	}

	public String getRfcDestinationId()
	{
		this.loadRFCConfiguration();
		return rfcDestinationId;
	}

	public void setRfcDestinationId(final String rfcDestinationId)
	{
		this.rfcDestinationId = rfcDestinationId;
	}

	public String getIdOrigin() {
		loadIdOriginConfiguration();
		return idOrigin;
	}
}