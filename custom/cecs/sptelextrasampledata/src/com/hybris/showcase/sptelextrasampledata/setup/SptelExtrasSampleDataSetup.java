/**
 *
 */
package com.hybris.showcase.sptelextrasampledata.setup;

import de.hybris.bootstrap.config.ConfigUtil;
import de.hybris.bootstrap.config.ExtensionInfo;
import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;
import de.hybris.platform.util.Config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hybris.showcase.sptelextrasampledata.constants.SptelextrasampledataConstants;


/**
 * @author mgolubovic
 */
@SystemSetup(extension = SptelextrasampledataConstants.EXTENSIONNAME)
public class SptelExtrasSampleDataSetup extends AbstractSystemSetup
{
	private static final String SYNC_CATALOGS = "syncCatalog";

	private static final String SAMPLE_DATA = "sptelExtrasSampleData";
	private static final String TRICAST_DATA = "TriCast Originals";

	@Override
	@SystemSetupParameterMethod
	public List<SystemSetupParameter> getInitializationOptions()
	{
		final List<SystemSetupParameter> params = new ArrayList<SystemSetupParameter>();

		params.add(createBooleanSystemSetupParameter(SYNC_CATALOGS, "Synchronize catalogs", true));

		createSampleDataParameter(params);

		return params;
	}

	private void createSampleDataParameter(final List<SystemSetupParameter> params)
	{
		final SystemSetupParameter dataSetParameter = new SystemSetupParameter(SAMPLE_DATA);
		dataSetParameter.setLabel("Choose sample data set");
		dataSetParameter.setMultiSelect(false);
		dataSetParameter.addValue(TRICAST_DATA, true);
		params.add(dataSetParameter);
	}

	@SystemSetup(type = Type.PROJECT, process = Process.ALL)
	public void createProjectData(final SystemSetupContext context)
	{
		final List<String> exts = getLoadedExtensionNames();

		final String sampleDataParameter = context.getParameter(context.getExtensionName() + "_" + SAMPLE_DATA);
		//boolean isSptelDataChecked = StringUtils.equals(sampleDataParameter, TRICAST_DATA);
		boolean isSynchChecked = getBooleanSystemSetupParameter(context, SYNC_CATALOGS);

		if (StringUtils.isBlank(sampleDataParameter))
		{
			isSynchChecked = true;
		}


		final String mediaserverExtName = "mediaserver";
		String productCatalogName = "sptelProductCatalog";

		if (isExtensionLoaded(exts, mediaserverExtName))
		{
			importMedia(context, productCatalogName, mediaserverExtName);
		}
		else
		{
			final String productCatalogs = "/sptelextrasampledata/import/sampledata/productCatalogs";
			importImpexFile(context, productCatalogs + "/" + productCatalogName + "/products-media.impex", false);
		}

		if (isSynchChecked)
		{
			executeCatalogSyncJob(context, productCatalogName);
			executeCatalogSyncJob(context, "sptelContentCatalog");
		}
	}

	/**
	 *
	 */
	private void importMedia(final SystemSetupContext context, final String productCatalogName, final String mediaserverExtName)
	{
		final String productCatalogs = "/sptelextrasampledata/import/sampledata/productCatalogs";
		final ExtensionInfo extension = ConfigUtil.getPlatformConfig(SptelExtrasSampleDataSetup.class)
				.getExtensionInfo(mediaserverExtName);

		final String videoBaseDir = Config.getString("customLocalDirectoryMediaStorageStrategy.baseDir", null);
		final File videoDir = new File(videoBaseDir, "/video/sptel");

		if (videoDir.exists())
		{
			importImpexFile(context, productCatalogs + "/" + productCatalogName + "/products-media-server.impex", false);
			createWebrootLinks(context, extension, videoDir);
		}
		else
		{
			logInfo(context, "video directory doesn't exist, loading default videos");
			importImpexFile(context, productCatalogs + "/" + productCatalogName + "/products-media.impex", false);
		}
	}


	protected void createWebrootLinks(final SystemSetupContext context, final ExtensionInfo extension, final File videoDir)
	{
		final File[] files = videoDir.listFiles();
		for (final File file : files)
		{
			final Path link = new File(extension.getExtensionDirectory(), "/web/webroot/" + file.getName()).toPath();
			if (Files.isSymbolicLink(link))
			{
				logInfo(context, "symbolic link already exists: " + link);
				continue;
			}
			final Path target = file.toPath();
			try
			{
				Files.createSymbolicLink(link, target);
			}
			catch (final IOException e)
			{
				logError(context, "Unable to create symbolic link: " + target + " -> " + link + ": " + e.getMessage(), e);
				logInfo(context, "Try executing following command: mklink " + link + " " + target);
			}
		}
	}

	protected List<String> getLoadedExtensionNames()
	{
		final List<String> loadedExtensionNames = Registry.getCurrentTenant().getTenantSpecificExtensionNames();
		return loadedExtensionNames;
	}

	protected boolean isExtensionLoaded(final List<String> loadedExtensionNames, final String extensionNameToCheck)
	{
		return loadedExtensionNames.contains(extensionNameToCheck);
	}
}
