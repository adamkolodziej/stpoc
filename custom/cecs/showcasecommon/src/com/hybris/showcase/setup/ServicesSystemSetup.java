/**
 * 
 */
package com.hybris.showcase.setup;

import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.constants.ShowcasecommonConstants;


/**
 * @author n.pavlovic
 * 
 */
@SystemSetup(extension = ShowcasecommonConstants.EXTENSIONNAME)
public class ServicesSystemSetup extends AbstractSystemSetup
{
	private static final Log LOG = LogFactory.getLog(ServicesSystemSetup.class);

	private static final String INIT_EMS = "initEMS";

	private static final String SBG_URI_PROPERTY = "subscription.client.endpoint.uri";
	private static final String EMS_URI_PROPERTY = "ems.client.endpoint.uri";

	private ConfigurationService configurationService;

	@SystemSetupParameterMethod
	@Override
	public List<SystemSetupParameter> getInitializationOptions()
	{
		final List<SystemSetupParameter> params = new ArrayList<SystemSetupParameter>();

		params.add(createBooleanSystemSetupParameter(INIT_EMS, "Initialize EMS", true));

		return params;
	}

	@SystemSetup(type = SystemSetup.Type.PROJECT, process = SystemSetup.Process.INIT)
	public void createData(final SystemSetupContext context)
	{
		if (getBooleanSystemSetupParameter(context, INIT_EMS))
		{
			String emsUri = getConfigurationService().getConfiguration().getString(EMS_URI_PROPERTY);

			if (!StringUtils.isBlank(emsUri))
			{
				//Change URL string from http://localhost:9001/entitlements-web/rest/ to http://localhost:9001/entitlements-web/
				final int firstChar = StringUtils.indexOf(emsUri, "rest");
				emsUri = emsUri.substring(0, firstChar);
				doPost(emsUri, "EMS");
			}
		}
	}

	private void doPost(final String url, final String label)
	{
		final HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url + "init-app-web/console/initializeSystem");

		try
		{
			int statusCode = client.executeMethod(method);
			if (statusCode == HttpStatus.SC_OK)
			{
				method = new PostMethod(url + "init-app-web/console/initializeSchema");
				method.setParameter("tenant", "single");
				method.setParameter("projectData", "true");

				statusCode = client.executeMethod(method);

				if (statusCode != HttpStatus.SC_OK)
				{
					LOG.error(label + " initialize schema error: " + HttpStatus.getStatusText(statusCode));
				}
			}
			else
			{
				LOG.error(label + " initialize system error: " + HttpStatus.getStatusText(statusCode));
			}
		}
		catch (final IOException e)
		{
			LOG.error(label + " initialization error: " + e.getMessage());
		}
		finally
		{
			method.releaseConnection();
		}
	}

	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	@Required
	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}
}
