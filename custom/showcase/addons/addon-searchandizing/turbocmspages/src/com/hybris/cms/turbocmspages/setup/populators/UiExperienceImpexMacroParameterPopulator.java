/**
 * 
 */
package com.hybris.cms.turbocmspages.setup.populators;

import de.hybris.platform.acceleratorservices.config.SiteConfigService;
import de.hybris.platform.acceleratorservices.uiexperience.UiExperienceService;
import de.hybris.platform.addonsupport.setup.impl.AddOnDataImportEventContext;
import de.hybris.platform.commerceservices.enums.UiExperienceLevel;
import de.hybris.platform.commerceservices.setup.data.ImpexMacroParameterData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.cms.turbocmspages.setup.populators.dao.UiExperienceSupportDao;


/**
 * @author rmcotton
 * 
 */
public class UiExperienceImpexMacroParameterPopulator implements Populator<AddOnDataImportEventContext, ImpexMacroParameterData>
{
	private UiExperienceSupportDao uiExperienceSupportDao;
	private SiteConfigService siteConfigService;
	private SessionService sessionService;
	private BaseSiteService baseSiteService;
	private UiExperienceService uiExperienceService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final AddOnDataImportEventContext source, final ImpexMacroParameterData target)
			throws ConversionException
	{
		if (target.getAdditionalParameterMap() == null)
		{
			target.setAdditionalParameterMap(new HashMap<String, String>());
		}


		for (final UiExperienceLevel level : UiExperienceLevel.values())
		{

			getSessionService().executeInLocalView(new SessionExecutionBody()
			{
				@Override
				public void executeWithoutResult()
				{
					final String macroParamName = "import" + level.getCode();
					getBaseSiteService().setCurrentBaseSite(source.getBaseSite(), false);
					getUiExperienceService().setOverrideUiExperienceLevel(level);
					final String configSetting = getSiteConfigService().getProperty("turbocmspages.enabled");
					final boolean enabled;
					if (configSetting == null)
					{
						enabled = getUiExperienceSupportDao().hasUiExperienceRestrictions(source.getContentCatalog(), level);
					}
					else
					{
						enabled = Boolean.valueOf(configSetting).booleanValue();
					}
					target.getAdditionalParameterMap().put(macroParamName, String.valueOf(enabled));
				}

			});


		}


	}

	public UiExperienceSupportDao getUiExperienceSupportDao()
	{
		return uiExperienceSupportDao;
	}

	@Required
	public void setUiExperienceSupportDao(final UiExperienceSupportDao uiExperienceSupportDao)
	{
		this.uiExperienceSupportDao = uiExperienceSupportDao;
	}

	public SiteConfigService getSiteConfigService()
	{
		return siteConfigService;
	}

	@Required
	public void setSiteConfigService(final SiteConfigService siteConfigService)
	{
		this.siteConfigService = siteConfigService;
	}

	public SessionService getSessionService()
	{
		return sessionService;
	}

	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	public UiExperienceService getUiExperienceService()
	{
		return uiExperienceService;
	}

	@Required
	public void setUiExperienceService(final UiExperienceService uiExperienceService)
	{
		this.uiExperienceService = uiExperienceService;
	}


}
