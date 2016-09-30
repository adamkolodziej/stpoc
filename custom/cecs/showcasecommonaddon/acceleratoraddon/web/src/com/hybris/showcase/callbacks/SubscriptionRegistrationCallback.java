/**
 * 
 */
package com.hybris.showcase.callbacks;

import de.hybris.platform.commercefacades.user.data.RegisterData;
import de.hybris.platform.subscriptionfacades.SubscriptionFacade;
import de.hybris.platform.subscriptionfacades.exceptions.SubscriptionFacadeException;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;


/**
 * @author npavlovic
 * 
 *         CECS-111: Migrate my-account subscriptions page from telco
 */
public class SubscriptionRegistrationCallback extends RegistrationCallback
{
	private static final Logger LOG = Logger.getLogger(SubscriptionRegistrationCallback.class);
	private SubscriptionFacade subscriptionFacade;

	@Override
	public void afterAutoLogin(final RegisterData data) throws RegistrationException
	{
		try
		{
			subscriptionFacade.updateProfile(new HashMap<String, String>());
		}
		catch (final SubscriptionFacadeException e)
		{
			LOG.warn(String.format("Creating new subscription billing profile for user %s failed", data.getLogin()), e);
			throw new RegistrationException("warn", "registration.error.subscription.billing.profil", e);
		}
	}

	public SubscriptionFacade getSubscriptionFacade()
	{
		return subscriptionFacade;
	}

	@Required
	public void setSubscriptionFacade(final SubscriptionFacade subscriptionFacade)
	{
		this.subscriptionFacade = subscriptionFacade;
	}
}
