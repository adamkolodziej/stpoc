/*
 * [y] hybris Platform
 * 
 * Copyright (c) 2000-2016 SAP SE
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information of SAP 
 * Hybris ("Confidential Information"). You shall not disclose such 
 * Confidential Information and shall use it only in accordance with the 
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.sptel.setup;

import static com.sptel.constants.SptelcoreConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.sptel.constants.SptelcoreConstants;
import com.sptel.service.SptelcoreService;


@SystemSetup(extension = SptelcoreConstants.EXTENSIONNAME)
public class SptelcoreSystemSetup
{
	private final SptelcoreService sptelcoreService;

	public SptelcoreSystemSetup(final SptelcoreService sptelcoreService)
	{
		this.sptelcoreService = sptelcoreService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		sptelcoreService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return SptelcoreSystemSetup.class.getResourceAsStream("/sptelcore/sap-hybris-platform.png");
	}
}
