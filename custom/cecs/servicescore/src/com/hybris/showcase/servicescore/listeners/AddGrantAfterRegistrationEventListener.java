/**
 *
 */
package com.hybris.showcase.servicescore.listeners;

import de.hybris.platform.commerceservices.event.AbstractSiteEventListener;
import de.hybris.platform.commerceservices.event.RegisterEvent;
import de.hybris.platform.entitlementservices.exception.EntitlementFacadeException;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;

import org.apache.log4j.Logger;

import com.hybris.showcase.emsextras.facades.GrantingEntitlementsFacade;
import org.springframework.beans.factory.annotation.Required;


/**
 * @author i324342
 */
public class AddGrantAfterRegistrationEventListener extends AbstractSiteEventListener<RegisterEvent>
{
	private static final Logger LOG = Logger.getLogger(AddGrantAfterRegistrationEventListener.class);
	private String productCode;

	private GrantingEntitlementsFacade grantingEntitlementsFacade;

	@Override
	protected void onSiteEvent(final RegisterEvent event)
	{
		try
		{
			getGrantingEntitlementsFacade().grantEntitlementsFromProduct(event.getCustomer().getUid(), productCode);
		}
		catch (final EntitlementFacadeException | ModelNotFoundException e)
		{
			LOG.fatal("Unable to grant free entitlement", e);
		}
	}

	@Override
	protected boolean shouldHandleEvent(final RegisterEvent event)
	{
		return true;
	}

	public GrantingEntitlementsFacade getGrantingEntitlementsFacade()
	{
		return grantingEntitlementsFacade;
	}

	@Required
	public void setGrantingEntitlementsFacade(final GrantingEntitlementsFacade grantingEntitlementsFacade)
	{
		this.grantingEntitlementsFacade = grantingEntitlementsFacade;
	}

	public String getProductCode() {
		return productCode;
	}

	@Required
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
}
