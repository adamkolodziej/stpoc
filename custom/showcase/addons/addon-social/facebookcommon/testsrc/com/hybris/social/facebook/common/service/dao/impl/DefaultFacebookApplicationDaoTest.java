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
package com.hybris.social.facebook.common.service.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalBaseTest;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.hybris.social.facebook.common.model.FacebookApplicationModel;
import com.hybris.social.facebook.common.service.dao.FacebookApplicationDao;


/**
 * @author rmcotton
 * 
 */
@IntegrationTest
public class DefaultFacebookApplicationDaoTest extends ServicelayerTransactionalBaseTest
{

	@Resource
	private FacebookApplicationDao defaultFacebookApplicationDao;

	private FacebookApplicationModel application1;


	@Before
	public void setUp()
	{
		application1 = defaultFacebookApplicationDao.createDefaultFacebookApplication(Long.valueOf(1234567l), "SECRET",
				"int1-vanilla-acc-ext.fra.hybris.com");
	}

	@Test
	public void testFindApplication()
	{
		assertEquals(application1, defaultFacebookApplicationDao.findApplication(Long.valueOf(1234567l)));
		assertNull(defaultFacebookApplicationDao.findApplication(Long.valueOf(12345678l)));
	}

	@Test
	public void testFullDomainLookup()
	{
		assertEquals(application1,
				defaultFacebookApplicationDao.findDefaultApplicationForDomain("int1-vanilla-acc-ext.fra.hybris.com"));
		assertNull(defaultFacebookApplicationDao.findDefaultApplicationForDomain("electronics.int1-vanilla-acc-ext.fra.hybris.com"));
	}

	@Test
	public void testDomainPartsLookup()
	{
		assertEquals(application1, defaultFacebookApplicationDao.findDefaultApplicationForSubDomains(Arrays.asList(
				"electronics.int1-vanilla-acc-ext.fra.hybris.com", "int1-vanilla-acc-ext.fra.hybris.com")));
		assertNull(defaultFacebookApplicationDao.findDefaultApplicationForSubDomains(Arrays
				.asList("electronics.int1-vanilla-acc-ext.fra.hybris.com")));

	}

}
