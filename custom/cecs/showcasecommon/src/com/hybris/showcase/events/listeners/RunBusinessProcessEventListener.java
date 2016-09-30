package com.hybris.showcase.events.listeners;

import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.events.RunBusinessProcessEvent;


public class RunBusinessProcessEventListener extends AbstractEventListener<RunBusinessProcessEvent>
{

	private ModelService modelService;
	private BusinessProcessService businessProcessService;

	@Override
	protected void onEvent(final RunBusinessProcessEvent event)
	{
		final String processId = String.format("%s-%s-%s", event.getBusinessProcessName(), event.getCustomer().getUid(),
				System.currentTimeMillis());

		final StoreFrontCustomerProcessModel process = getBusinessProcessService().createProcess(processId,
				event.getBusinessProcessName());
		process.setSite(event.getSite());
		process.setCustomer(event.getCustomer());
		process.setLanguage(event.getLanguage());
		process.setCurrency(event.getCurrency());
		process.setStore(event.getBaseStore());

		getModelService().save(process);
		getBusinessProcessService().startProcess(process);
	}

	protected ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	protected BusinessProcessService getBusinessProcessService()
	{
		return businessProcessService;
	}

	@Required
	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}

}
