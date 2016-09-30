/**
 *
 */
package com.hybris.showcase.cecs.ymarketingintegration.event;

import de.hybris.platform.commerceservices.event.RegisterEvent;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.yacceleratorcore.event.RegistrationEventListener;


/**
 * @author Adrian Sbarcea (SAP - i309441)
 *
 */
public class YmarketingintegrationRegistrationEventListener extends RegistrationEventListener
{
	@Override
	protected void onSiteEvent(final RegisterEvent registerEvent)
	{
		//Start CECS-641
		final CustomerModel customer = registerEvent.getCustomer();
		customer.setSapContactID(customer.getUid());
		//End CECS-641

		super.onSiteEvent(registerEvent);
	}
}

