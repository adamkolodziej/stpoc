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
package com.hybris.social.facebook.opengraphmine.process.event;

import com.hybris.social.facebook.opengraphmine.model.FacebookConnectionModel;
import com.restfb.FacebookClient;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.converters.PopulatorList;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.processengine.enums.ProcessState;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.social.connect.ConnectionData;

import com.hybris.social.facebook.opengraphmine.model.process.FacebookSynchronizationProcessModel;
import com.hybris.social.facebook.opengraphmine.process.event.dao.FacebookSynchronizationProcessDao;
import com.hybris.social.facebook.opengraphmine.service.converter.FacebookConnectionConverter;


/**
 * Starts a new facebook profile sync. This results in the configured business process workflow name being invoked. For
 * performance reasons this veto's concurrent sync requests for the same facebook account.
 * 
 * @author rmcotton
 */
public class FacebookProfileSyncEventListener<E extends AbstractFacebookSyncEvent> extends AbstractEventListener<E>
{
	private static final Logger LOG = Logger.getLogger(FacebookProfileSyncEventListener.class);

	private String processName = "fbSynchronizationProcess";

	private BusinessProcessService businessProcessService;

	private ModelService modelService;

	private FacebookSynchronizationProcessDao facebookSynchronizationProcessDao;

	private FacebookConnectionConverter facebookConnectionConverter;

	private PopulatorList<E, FacebookSynchronizationProcessModel> contextParametersPopulators;

	private Set<Class> assignableEventClasses;

	protected boolean isAssignable(final E event)
	{
		for (final Class clazz : getAssignableEventClasses())
		{
			if (clazz.isAssignableFrom(event.getClass()))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	protected void onEvent(final E profileSyncEvent)
	{
		if (!isAssignable(profileSyncEvent))
		{
			return;
		}

		if (profileSyncEvent.isForce() || !isExistingProcessRunning(profileSyncEvent))
		{
			final FacebookSynchronizationProcessModel process = (FacebookSynchronizationProcessModel) businessProcessService
					.createProcess(
							getProcessName() + "-" + profileSyncEvent.getFacebookUser().getUid() + "-" + System.currentTimeMillis(),
							getProcessName());
			process.setUser(profileSyncEvent.getFacebookUser());
			process.setApplicationId(profileSyncEvent.getAppId().toString());
			process.setApplicationSecret(profileSyncEvent.getAppSecret());

			final PopulatorList<E, FacebookSynchronizationProcessModel> contextParamPopulators = getContextParameterPopulators();
			if (contextParamPopulators != null)
			{
				for (final Populator<E, FacebookSynchronizationProcessModel> p : contextParamPopulators.getPopulators())
				{
					p.populate(profileSyncEvent, process);
				}
			}
			modelService.save(process);
			businessProcessService.startProcess(process);
		}
		else
		{
			LOG.warn("veto profile sync for [ " + profileSyncEvent.getFacebookUser().getUid()
					+ "] as another synchronization process is currently running");
		}
	}

	protected boolean isExistingProcessRunning(final E profileSyncEvent)
	{
		for (final FacebookSynchronizationProcessModel process : facebookSynchronizationProcessDao
				.getFacebookSynchronizationProcessesForUser(profileSyncEvent.getFacebookUser(), ProcessState.RUNNING))
		{
			if (StringUtils
					.equals(process.getConnection().getProviderUserId(), profileSyncEvent.getFacebookUser().getUid()))
			{
				return true;
			}
		}

		return false;
	}

	@Required
	public void setProcessName(final String processName)
	{
		this.processName = processName;
	}

	public String getProcessName()
	{
		return processName;
	}

	public BusinessProcessService getBusinessProcessService()
	{
		return businessProcessService;
	}

	@Required
	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public FacebookSynchronizationProcessDao getFacebookSynchronizationProcessDao()
	{
		return facebookSynchronizationProcessDao;
	}

	@Required
	public void setFacebookSynchronizationProcessDao(final FacebookSynchronizationProcessDao facebookSynchronizationProcessDao)
	{
		this.facebookSynchronizationProcessDao = facebookSynchronizationProcessDao;
	}

	public FacebookConnectionConverter getFacebookConnectionConverter()
	{
		return facebookConnectionConverter;
	}

	@Required
	public void setFacebookConnectionConverter(final FacebookConnectionConverter facebookConnectionConverter)
	{
		this.facebookConnectionConverter = facebookConnectionConverter;
	}

	public PopulatorList<E, FacebookSynchronizationProcessModel> getContextParameterPopulators()
	{
		return contextParametersPopulators;
	}

	public void setContextParameterPopulators(
			final PopulatorList<E, FacebookSynchronizationProcessModel> contextParameterPopulators)
	{
		this.contextParametersPopulators = contextParameterPopulators;
	}

	public Set<Class> getAssignableEventClasses()
	{
		return assignableEventClasses;
	}

	@Required
	public void setAssignableEventClasses(final Set<Class> assignableEventClasses)
	{
		this.assignableEventClasses = assignableEventClasses;
	}
}
