/**
 *
 */
package com.hybris.showcase.facades;

import de.hybris.platform.subscriptionfacades.SubscriptionFacade;


/**
 * @author I307113
 *
 */
public interface ServicesSubscriptionFacade extends SubscriptionFacade
{
	boolean setEmailRemindersAndBillingEpaper(final Boolean isEmailEnabled, final Boolean isBillingEpaper);
}
