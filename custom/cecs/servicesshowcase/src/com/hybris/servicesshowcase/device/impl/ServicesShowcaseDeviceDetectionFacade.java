package com.hybris.servicesshowcase.device.impl;

import de.hybris.platform.acceleratorfacades.device.data.DeviceData;
import de.hybris.platform.acceleratorfacades.device.impl.DefaultDeviceDetectionFacade;
import de.hybris.platform.commerceservices.enums.UiExperienceLevel;
import org.apache.commons.lang.BooleanUtils;

import javax.servlet.http.HttpServletRequest;


public class ServicesShowcaseDeviceDetectionFacade extends DefaultDeviceDetectionFacade
{

	@Override
	public void initializeRequest(final HttpServletRequest request)
	{
		super.initializeRequest(request);
		final DeviceData currentDetectedDevice = getCurrentDetectedDevice();
		if (BooleanUtils.isTrue(currentDetectedDevice.getMobileBrowser())
				|| BooleanUtils.isTrue(currentDetectedDevice.getTabletBrowser()))
		{
			getSessionService().setAttribute(ServicesSessionConstants.REAL_UI_EXPERIENCE_LEVEL_SESSION_ATTRIBUTE,
					UiExperienceLevel.MOBILE);
		}
	}
}
