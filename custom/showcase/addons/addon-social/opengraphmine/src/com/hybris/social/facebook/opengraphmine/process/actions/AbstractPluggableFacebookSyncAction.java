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
package com.hybris.social.facebook.opengraphmine.process.actions;

import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.task.RetryLaterException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.model.process.FacebookSynchronizationProcessModel;


/**
 * Supports the ability to plug in a converter to copy various data from a source to the facebook user account.
 * 
 * @author rmcotton
 */
public abstract class AbstractPluggableFacebookSyncAction<SOURCE> extends AbstractSimpleDecisionAction
{
	private Converter<SOURCE, FacebookUserModel> converter;

	private static final Logger LOG = Logger.getLogger(AbstractPluggableFacebookSyncAction.class);

	@Override
	public Transition executeAction(final BusinessProcessModel arg0) throws RetryLaterException, Exception
	{
		final FacebookSynchronizationProcessModel model = (FacebookSynchronizationProcessModel) arg0;
		final SOURCE source = getSource(model);
		try
		{
			converter.convert(source, model.getUser());
			modelService.save(model.getUser());
		}
		catch (final Exception e)
		{
			LOG.error("facebook sync action execution failed", e);
			return Transition.NOK;
		}
		return Transition.OK;
	}

	@Required
	public void setConverter(final Converter<SOURCE, FacebookUserModel> converter)
	{
		this.converter = converter;
	}

	public Converter<SOURCE, FacebookUserModel> getConverter()
	{
		return this.converter;
	}

	protected abstract SOURCE getSource(final FacebookSynchronizationProcessModel processModel);

}
