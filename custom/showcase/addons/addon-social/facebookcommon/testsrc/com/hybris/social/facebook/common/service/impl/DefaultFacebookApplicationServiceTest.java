/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 * 
 */
package com.hybris.social.facebook.common.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.MockSessionService;
import de.hybris.platform.servicelayer.session.Session;
import de.hybris.platform.servicelayer.session.impl.DefaultSession;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hybris.social.facebook.common.model.FacebookApplicationDomainModel;
import com.hybris.social.facebook.common.model.FacebookApplicationModel;
import com.hybris.social.facebook.common.service.dao.FacebookApplicationDao;


/**
 * @author rmcotton
 * 
 */
@UnitTest
public class DefaultFacebookApplicationServiceTest
{
	private DefaultFacebookApplicationService facebookApplicationService;

	private static class MockFacebookApplicationService extends DefaultFacebookApplicationService
	{
		@Override
		public void afterPropertiesSet() throws Exception
		{
			this.ipAddressPattern = Pattern.compile(ipAddressRegex);
		}
	}

	private static class MockSession extends DefaultSession
	{
		private long sessionIdCounter = 1;

		private final Map<String, Object> attributes = new ConcurrentHashMap<String, Object>();
		private final String sessionId;

		@Override
		public String getSessionId()
		{
			return this.sessionId;
		}

		public MockSession()
		{
			super();
			this.sessionId = String.valueOf(sessionIdCounter++);
		}

		@Override
		public <T> Map<String, T> getAllAttributes()
		{

			return (Map<String, T>) Collections.unmodifiableMap(attributes);
		}

		@Override
		public <T> T getAttribute(final String name)
		{

			return (T) attributes.get(name);
		}


		@Override
		public void setAttribute(final String name, final Object value)
		{
			attributes.put(name, value);
		}

		@Override
		public void removeAttribute(final String name)
		{
			attributes.remove(name);
		}
	}

	@Mock
	private FacebookApplicationDao facebookApplicationDao;

	@Mock
	private ModelService modelService;

	private MockSessionService sessionService;

	@Mock
	private ConfigurationService configurationService;

	@Mock
	private Configuration configuration;


	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		sessionService = new MockSessionService()
		{
			@Override
			public Session createSession()
			{
				return new MockSession();
			}
		};

		facebookApplicationService = new MockFacebookApplicationService();
		facebookApplicationService.setModelService(modelService);
		facebookApplicationService.setSessionService(sessionService);
		facebookApplicationService.setFacebookApplicationDao(facebookApplicationDao);
		facebookApplicationService.setConfigurationService(configurationService);
		facebookApplicationService.afterPropertiesSet();

		setupDomain("int1-vanilla-acc-ext.fra.hybris.com", "123456", "SECRET");

		given(configurationService.getConfiguration()).willReturn(configuration);
	}

	protected void setupDomain(final String domain, final String appId, final String secret)
	{
		configuration.setProperty("fbconnect.appid." + domain, appId);
		configuration.setProperty("fbconnect.secret." + domain, secret);

		final FacebookApplicationModel application = new FacebookApplicationModel();
		application.setApplicationId(Long.valueOf(appId));
		application.setApplicationSecret(secret);

		final FacebookApplicationDomainModel domainModel = new FacebookApplicationDomainModel();
		domainModel.setDomain(domain);
		application.setDomains(Collections.singleton(domainModel));


		given(facebookApplicationDao.createDefaultFacebookApplication(Long.valueOf(appId), secret, domain)).willReturn(application);
		given(facebookApplicationDao.findDefaultApplicationForDomain(domain)).willReturn(application);
		given(facebookApplicationDao.findDefaultApplicationForSubDomains(getSubDomains(domain))).willReturn(application);



	}

	protected List<String> getSubDomains(final String serverName)
	{
		final String[] domainParts = StringUtils.split(serverName, '.');
		if (domainParts.length > 0)
		{
			final List<String> domainsToTry = new LinkedList<String>();
			for (int i = 0; i < domainParts.length; i++)
			{
				final StringBuilder domain = new StringBuilder();

				for (int j = i; j < domainParts.length; j++)
				{
					domain.append(domainParts[j]);
					if (j < domainParts.length - 1)
					{
						domain.append(".");
					}
				}
				domainsToTry.add(domain.toString());
			}
			return domainsToTry;
		}
		return null;
	}

	@Test
	public void testExactMatchNoCache()
	{

		final FacebookApplicationModel application = facebookApplicationService
				.initApplication("int1-vanilla-acc-ext.fra.hybris.com");
		assertNotNull(application);
		assertEquals(Long.valueOf("123456"), application.getApplicationId());
		assertEquals("SECRET", application.getApplicationSecret());
	}

	@Test
	public void testSubDomainMatchNoCache()
	{

		final FacebookApplicationModel application = facebookApplicationService
				.initApplication("electronics.int1-vanilla-acc-ext.fra.hybris.com");
		verify(facebookApplicationDao, times(1)).findDefaultApplicationForSubDomains(
				Arrays.asList("int1-vanilla-acc-ext.fra.hybris.com", "fra.hybris.com", "hybris.com", "com"));
		assertNotNull(application);
		assertEquals(Long.valueOf("123456"), application.getApplicationId());
		assertEquals("SECRET", application.getApplicationSecret());

	}
}
