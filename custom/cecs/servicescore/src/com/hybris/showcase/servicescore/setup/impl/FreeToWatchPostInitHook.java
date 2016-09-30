/**
 *
 */
package com.hybris.showcase.servicescore.setup.impl;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.commerceservices.setup.data.ImportData;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.entitlementservices.exception.EntitlementFacadeException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.emsextras.facades.GrantingEntitlementsFacade;
import com.hybris.showcase.services.CustomerService;
import com.hybris.showcase.setup.PostInitHook;


/**
 * @author i324342
 */
public class FreeToWatchPostInitHook implements PostInitHook
{
	private static final Logger LOG = Logger.getLogger(FreeToWatchPostInitHook.class);
	private String productCode;

	private GrantingEntitlementsFacade grantingEntitlementsFacade;
	private CustomerService customerService;
	@Resource
	private SessionService sessionService;
	@Resource
	private UserService userService;

	@Resource
	private CMSSiteService cmsSiteService;

	@Resource
	private BaseSiteService baseSiteService;
	@Resource
	private CatalogVersionService catalogVersionService;

	@Override
	public void performPostInitHooks(final AbstractSystemSetup systemSetup, final SystemSetupContext context,
			final List<ImportData> importDataList)
	{
		final List<CustomerModel> customers = new ArrayList<CustomerModel>(customerService.getCustomers());

		try
		{
			for (final CustomerModel customerModel : customers)
			{
				sessionService.executeInLocalView(new SessionExecutionBody()
				{
					@Override
					public Object execute()
					{
						final String site = "tricast";
						final String productCatalog = "tricastProductCatalog";
						final CatalogVersionModel catalogVersion = catalogVersionService.getCatalogVersion(productCatalog, "Online");

						userService.setCurrentUser(customerModel);
						baseSiteService.setCurrentBaseSite(site, false);

						try
						{
							cmsSiteService.setCurrentCatalogVersion(catalogVersion);
							cmsSiteService.setCurrentSiteAndCatalogVersions(site, false);

							getGrantingEntitlementsFacade().grantEntitlementsFromProduct(customerModel.getUid(), getProductCode());
						}
						catch (final EntitlementFacadeException | CMSItemNotFoundException e)
						{
							LOG.error("Error", e);
						}
						return customerModel;
					}
				}, customerModel);
			}
		}
		catch (final UnknownIdentifierException e)
		{
			LOG.error("unable to grant free entitlement for customers", e);
		}
	}


	/**
	 * @return the customerService
	 */
	public CustomerService getCustomerService()
	{
		return customerService;
	}

	/**
	 * @param customerService
	 *           the customerService to set
	 */
	public void setCustomerService(final CustomerService customerService)
	{
		this.customerService = customerService;
	}

	/**
	 * @return the grantEntitlementFacade
	 */
	public GrantingEntitlementsFacade getGrantingEntitlementsFacade()
	{
		return grantingEntitlementsFacade;
	}

	/**
	 * @param grantingEntitlementsFacade
	 *           the grantEntitlementFacade to set
	 */
	public void setGrantingEntitlementsFacade(final GrantingEntitlementsFacade grantingEntitlementsFacade)
	{
		this.grantingEntitlementsFacade = grantingEntitlementsFacade;
	}

	public String getProductCode()
	{
		return productCode;
	}

	@Required
	public void setProductCode(final String productCode)
	{
		this.productCode = productCode;
	}



}
