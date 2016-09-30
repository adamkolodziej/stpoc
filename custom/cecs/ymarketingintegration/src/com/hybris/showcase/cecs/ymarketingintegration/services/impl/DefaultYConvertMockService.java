package com.hybris.showcase.cecs.ymarketingintegration.services.impl;

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.processengine.enums.ProcessState;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.subscriptionservices.model.BillingTimeModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.cecs.ymarketingintegration.services.YConvertMockService;
import com.hybris.showcase.events.RunBusinessProcessEvent;


/**
 * Created by miroslaw.szot@sap.com on 2016-02-05.
 */
public class DefaultYConvertMockService implements YConvertMockService
{
	private static final Logger LOG = Logger.getLogger(DefaultYConvertMockService.class);
	public static final String SEEWHY_ADDON_PROPERTY = "seewhy.key";
	public static final String SEEWHY_MODE_MOCK = "mock";
	public static final String SEEWHY_MOCK_PRODUCTS = "seewhy.mock.products";

	private ConfigurationService configurationService;
	private CommonI18NService commonI18NService;
	private EventService eventService;
	private BaseSiteService baseSiteService;
	private String emailProcessId;
	private UserService userService;
	private FlexibleSearchService flexibleSearchService;

	@Override
	public void onAddToCart(CartModel cart, UserModel customer, CommerceCartModification result)
	{
		if (isMockActive())
		{
			final Configuration configuration = configurationService.getConfiguration();
			final List mockedProducts = Arrays.asList(configuration.getString(SEEWHY_MOCK_PRODUCTS).split(","));

			if (result != null && result.getEntry() != null && result.getQuantityAdded() > 0)
			{
				final Set<String> cartProducts = Collections.singleton(result.getEntry().getProduct().getCode());
				if (shouldMock(cartProducts, mockedProducts, cart, customer))
				{
					sendEvent(cart, (CustomerModel) customer);
				}
			}
			else
			{
				LOG.warn("add to cart failed - yConvert - skipping...");
			}
		}
	}

	private boolean isMockActive()
	{
		final Configuration configuration = configurationService.getConfiguration();
		final String seeWhyProperty = configuration.getString(SEEWHY_ADDON_PROPERTY);
		return SEEWHY_MODE_MOCK.equals(seeWhyProperty);
	}

	protected void sendEvent(CartModel cart, CustomerModel customer)
	{
		final RunBusinessProcessEvent event = new RunBusinessProcessEvent();
		event.setBusinessProcessName(emailProcessId);
		event.setCustomer(customer);
		final BaseStoreModel store = cart.getStore();
		event.setBaseStore(store);
		event.setCurrency(cart.getCurrency());
		event.setLanguage(commonI18NService.getCurrentLanguage());
		event.setSite(baseSiteService.getCurrentBaseSite());

		eventService.publishEvent(event);
	}

	@Override
	public void onLogin(CartModel cart)
	{
		if (isMockActive())
		{
			final Configuration configuration = configurationService.getConfiguration();
			final List<String> mockedProducts = Arrays.asList(configuration.getString(SEEWHY_MOCK_PRODUCTS).split(","));

			final Set<String> cartProducts = cart.getEntries().stream() //
					.map(e -> e.getProduct().getCode()) //
					.collect(Collectors.toSet());

			if (shouldMock(cartProducts, mockedProducts, cart, cart.getUser()))
			{
				sendEvent(cart, (CustomerModel) cart.getUser());
			}
		}
	}

	protected boolean shouldMock(Set<String> cartProducts, List<String> mockedProducts, CartModel cart, UserModel customer)
	{
		final BillingTimeModel billingTime = cart.getBillingTime();

		if (billingTime != null && !"paynow".equals(billingTime.getCode()))
		{
			return false;
		}

		if (userService.isAnonymousUser(customer))
		{
			return false;
		}

		if (anyOtherYConvertProcessesRunning(customer))
		{
			return false;
		}

		return mockedProducts.stream() //
				.anyMatch(p -> cartProducts.contains(p));
	}

	protected boolean anyOtherYConvertProcessesRunning(UserModel customer)
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery( //
				"SELECT count(*) FROM {StoreFrontCustomerProcess}" + //
						" WHERE {customer} = ?customer" + //
						" AND {processDefinitionName} = ?processDefinitionName" + //
						" AND {state} IN (?states)");

		query.addQueryParameter("customer", customer);
		query.addQueryParameter("processDefinitionName", getEmailProcessId());
		query.addQueryParameter("states", Arrays.asList(ProcessState.CREATED, ProcessState.RUNNING, ProcessState.WAITING));
		query.setResultClassList(Arrays.asList(Long.class));

		Long cnt = (Long) flexibleSearchService.search(query).getResult().get(0);
		return cnt.longValue() > 0;
	}

	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	@Required
	public void setConfigurationService(ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}

	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	public EventService getEventService()
	{
		return eventService;
	}

	@Required
	public void setEventService(EventService eventService)
	{
		this.eventService = eventService;
	}

	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	@Required
	public void setBaseSiteService(BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	public String getEmailProcessId()
	{
		return emailProcessId;
	}

	@Required
	public void setEmailProcessId(String emailProcessId)
	{
		this.emailProcessId = emailProcessId;
	}

	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	@Required
	public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}
}
