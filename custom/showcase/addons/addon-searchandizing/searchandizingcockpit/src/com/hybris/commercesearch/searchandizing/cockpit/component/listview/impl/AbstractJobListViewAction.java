package com.hybris.commercesearch.searchandizing.cockpit.component.listview.impl;

import de.hybris.platform.cockpit.components.listview.AbstractListViewAction;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.impl.DefaultCronJobService;

import org.springframework.beans.factory.annotation.Required;
import org.zkoss.zul.Menupopup;

import com.hybris.commercesearch.searchandizing.cockpit.component.listview.JobListViewAction;


/**
 * @author radoslaw.golabek
 */
public abstract class AbstractJobListViewAction extends AbstractListViewAction implements JobListViewAction
{
	private DefaultCronJobService cronJobService;


	protected DefaultCronJobService getCronJobService()
	{
		return cronJobService;
	}

	@Required
	public void setCronJobService(final DefaultCronJobService cronJobService)
	{
		this.cronJobService = cronJobService;
	}

	@Override
	public boolean isRunning(final Context context)
	{
		return getCronJobService().isRunning(getModel(context));
	}

	@Override
	protected void doCreateContext(final Context context)
	{
		// empty
	}

	protected static CronJobModel getModel(final Context context)
	{
		final TypedObject item = context.getItem();
		return (CronJobModel) item.getObject();
	}

	@Override
	public Menupopup getContextPopup(final Context context)
	{
		return null;
	}

	@Override
	public Menupopup getPopup(final Context context)
	{
		return null;
	}

}
