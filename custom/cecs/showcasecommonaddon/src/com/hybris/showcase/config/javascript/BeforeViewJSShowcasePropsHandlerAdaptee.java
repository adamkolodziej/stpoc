/**
 *
 */
package com.hybris.showcase.config.javascript;

import de.hybris.platform.acceleratorservices.storefront.data.JavaScriptVariableData;
import de.hybris.platform.addonsupport.interceptors.BeforeViewHandlerAdaptee;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

import com.hybris.addon.common.interceptors.helper.JavaScriptVariableHelper;


/**
 *
 */
public class BeforeViewJSShowcasePropsHandlerAdaptee implements BeforeViewHandlerAdaptee
{

	@Resource(name = "configurationService")
	private ConfigurationService configurationService;

	@Override
	public String beforeView(final HttpServletRequest request, final HttpServletResponse response, final ModelMap model,
			final String viewName) throws Exception
	{
		final List<JavaScriptVariableData> javaScriptVariableData = JavaScriptVariableHelper.getVariables(model);

		javaScriptVariableData.add(JavaScriptVariableHelper.createJavaScriptVariable("config.showcasecommonPpvEnabled",
				configurationService.getConfiguration().getString("showcasecommon.ppv.enabled", "false")));

		return viewName;
	}

}
