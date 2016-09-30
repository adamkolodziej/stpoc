package de.hybris.platform.yacceleratorstorefront.security;

import de.hybris.platform.commerceservices.enums.UiExperienceLevel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.session.SessionService;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import com.hybris.services.entitlements.api.EntitlementFacade;
import com.hybris.services.entitlements.api.GrantData;
import com.hybris.servicesshowcase.device.impl.ServicesSessionConstants;
import com.hybris.showcase.cecs.mobiletricast.constants.MobiletricastConstants;


/**
 * Created by i303841 on 2/3/15.
 */
//CECS-76 check for MobileAccess entitlement after login
public class ServicesAuthenticationProvider extends AcceleratorAuthenticationProvider
{
	private final String MOBILETRICAST_REQUIRE_ENTITLEMENT = "mobiletricast.require.entitlement";

	private EntitlementFacade entitlementFacade;
	private SessionService sessionService;
	private ConfigurationService configurationService;

	@Override
	protected void additionalAuthenticationChecks(final UserDetails details, final AbstractAuthenticationToken authentication)
	{
		super.additionalAuthenticationChecks(details, authentication);

		final UiExperienceLevel uiExperienceLevel = getSessionService()
				.getAttribute(ServicesSessionConstants.REAL_UI_EXPERIENCE_LEVEL_SESSION_ATTRIBUTE);

		if (Objects.equals(uiExperienceLevel, UiExperienceLevel.MOBILE))
		{
			final boolean entitlementCheckRequired = getConfigurationService().getConfiguration()
					.getBoolean(MOBILETRICAST_REQUIRE_ENTITLEMENT, true);
			if (entitlementCheckRequired)
			{
				final List<GrantData> grants = getEntitlementFacade().getGrants(details.getUsername(), null, null, null);
				for (final GrantData grant : grants)
				{
					if (StringUtils.equals(grant.getEntitlementType(), MobiletricastConstants.MOBILE_ACCESS_ENTITLEMENT))
					{
						return;
					}
				}
				throw new NoEntitlementException("No entitlement to view a site.");
			}
		}
	}

	protected EntitlementFacade getEntitlementFacade()
	{
		return entitlementFacade;
	}

	@Required
	public void setEntitlementFacade(final EntitlementFacade entitlementFacade)
	{
		this.entitlementFacade = entitlementFacade;
	}

	protected SessionService getSessionService()
	{
		return sessionService;
	}

	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
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
