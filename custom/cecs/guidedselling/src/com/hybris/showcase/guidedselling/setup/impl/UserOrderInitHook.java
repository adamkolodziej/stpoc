/**
 *
 */
package com.hybris.showcase.guidedselling.setup.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.commerceservices.setup.data.ImportData;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.time.TimeService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.hybris.showcase.guidedselling.facades.OnePageGuidedSellingFacade;
import com.hybris.showcase.guidedselling.model.BundlePackageModel;
import com.hybris.showcase.guidedselling.services.BundlePackageService;
import com.hybris.showcase.setup.PostInitHook;


/**
 * @author marius.bogdan.ionescu@sap.com
 *
 */
public class UserOrderInitHook implements PostInitHook
{

	private static final Logger LOG = Logger.getLogger(UserOrderInitHook.class);

	@Resource
	private CartService cartService;
	@Resource
	private UserService userService;
	@Resource
	private ModelService modelService;
	@Resource
	private CMSSiteService cmsSiteService;
	@Resource
	private CheckoutFacade checkoutFacade;
	@Resource
	private BaseSiteService baseSiteService;
	@Resource
	private BundlePackageService bundlePackageService;
	@Resource
	private CatalogVersionService catalogVersionService;
	@Resource
	private OnePageGuidedSellingFacade guidedSellingFacade;
	@Resource
	private SessionService sessionService;
	@Resource
	private TimeService timeService;

	private CatalogVersionModel catalogVersion = null;

	private String site = "tricast";
	private String productCatalog = "tricastProductCatalog";
	private String customer = "brown.joe.ny@gmail.com";
	private String packageCode = "fibreStarter";
	private String months;


	@Override
	public void performPostInitHooks(final AbstractSystemSetup systemSetup, final SystemSetupContext context,
			final List<ImportData> importDataList)
	{
		boolean isTricast = false;
		for (final ImportData id : importDataList)
		{
			for (final String storeName : id.getStoreNames())
			{
				if (storeName.equals(site))
				{
					isTricast = true;
					break;
				}
			}
			if (isTricast)
			{
				break;
			}
		}

		if (!isTricast)
		{
			LOG.info("------------------------- UserOrderInitHook skip, no Tricast active.");
			return;
		}

		catalogVersion = catalogVersionService.getCatalogVersion(productCatalog, "Online");

		final UserModel userModel = userService.getUserForUID(customer);

		sessionService.executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public Object execute()
			{
				userService.setCurrentUser(userModel);
				cartService.getSessionCart().setUser(userModel);

				try
				{
					LOG.info("------------------------- start:" + customer);

					final BaseSiteModel siteModel = baseSiteService.getBaseSiteForUID(site);
					baseSiteService.setCurrentBaseSite(site, false);

					cmsSiteService.setCurrentCatalogVersion(catalogVersion);
					cmsSiteService.setCurrentSiteAndCatalogVersions(site, false);

					cartService.getSessionCart().setSite(siteModel);
					cartService.getSessionCart().setStore(siteModel.getStores().get(0));

					modelService.refresh(cartService.getSessionCart().getUser());
					cartService.changeCurrentCartUser(userModel);

					final Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.MONTH, Integer.parseInt(getMonths()));
					calendar.set(Calendar.HOUR_OF_DAY, 0);
					calendar.set(Calendar.MINUTE, 0);
					calendar.set(Calendar.SECOND, 0);
					cartService.getSessionCart().setContractStartDate(calendar.getTime());

					timeService.setCurrentTime(calendar.getTime());

					for (final UserOrderInitData orderInitData : initOrderData())
					{
						placeOrderOnBehalf(orderInitData);
					}
				}
				catch (final InvalidCartException | CMSItemNotFoundException e)
				{
					LOG.fatal("Unable to place order during init.", e);
				}
				catch (final RuntimeException e)
				{
					LOG.fatal("Unable to place order during init.", e);
				}
				finally
				{
					timeService.resetTimeOffset();
				}
				return null;
			}
		}, userModel);

	}

	private void placeOrderOnBehalf(final UserOrderInitData customersData) throws InvalidCartException
	{
		for (final BundlePackageModel bpm : customersData.getBundles())
		{
			LOG.info("##### Adding bundle: " + bpm.getBundleTemplate());
			guidedSellingFacade.startWithPackage(bpm.getBundleTemplate().getId(), bpm.getCode());
		}
		checkoutFacade.placeOrder();
	}

	private List<UserOrderInitData> initOrderData()
	{
		final List<UserOrderInitData> data = new ArrayList<UserOrderInitData>();

		final UserOrderInitData customerData = new UserOrderInitData();

		final List<BundlePackageModel> customersProducts = new ArrayList<BundlePackageModel>();

		//final BundlePackageModel packageModel = new BundlePackageModel();
		//packageModel.setCode(packageCode);
		//packageModel.setCatalogVersion(catalogVersion);

		customersProducts.add(bundlePackageService.getPackageByCode(packageCode));
		customerData.setBundles(customersProducts);

		data.add(customerData);

		return data;
	}

	/**
	 * @return the site
	 */
	public String getSite()
	{
		return site;
	}

	/**
	 * @param site
	 *           the site to set
	 */
	public void setSite(final String site)
	{
		this.site = site;
	}

	/**
	 * @return the productCatalog
	 */
	public String getProductCatalog()
	{
		return productCatalog;
	}

	/**
	 * @param productCatalog
	 *           the productCatalog to set
	 */
	public void setProductCatalog(final String productCatalog)
	{
		this.productCatalog = productCatalog;
	}

	/**
	 * @return the customer
	 */
	public String getCustomer()
	{
		return customer;
	}

	/**
	 * @param customer
	 *           the customer to set
	 */
	public void setCustomer(final String customer)
	{
		this.customer = customer;
	}

	/**
	 * @return the packageCode
	 */
	public String getPackageCode()
	{
		return packageCode;
	}

	/**
	 * @param packageCode
	 *           the packageCode to set
	 */
	public void setPackageCode(final String packageCode)
	{
		this.packageCode = packageCode;
	}

	/**
	 * @return the months
	 */
	public String getMonths()
	{
		return months;
	}

	/**
	 * @param months
	 *           the months to set
	 */
	public void setMonths(final String months)
	{
		this.months = months;
	}







}
