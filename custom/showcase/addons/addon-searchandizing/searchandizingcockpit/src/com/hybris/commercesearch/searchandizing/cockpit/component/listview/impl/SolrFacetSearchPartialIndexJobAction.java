package com.hybris.commercesearch.searchandizing.cockpit.component.listview.impl;

import de.hybris.platform.cockpit.components.listview.ListViewAction;
import de.hybris.platform.cockpit.events.impl.ItemChangedEvent;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.core.HybrisEnumValue;

import java.util.Collections;

import org.apache.commons.lang.StringUtils;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Image;


public class SolrFacetSearchPartialIndexJobAction extends AbstractFacetIndexJobListAction
{
	private final String INDEXER_TYPE = "UPDATE";
	protected static final String CURRENT_STATUS_KEY = "currentStatus";

	private final int RUNNING_CRON_JOBS = 1;
	private final int ACTIVE_CRON_JOBS = 2;
	private final int UNKNOWN_CRON_JOBS = 0;


	protected static final String ICON_BUSY_RUNNING_JOBS = "cockpit/images/job_busy.gif";
	protected static final String ICON_ACTIVE_JOBS = "cockpit/images/partialUpdate_play.png";


	@Override
	public EventListener getEventListener(final ListViewAction.Context context)
	{
		return new EventListener()
		{
			private final EventListener listener = new EventListener()
			{

				@Override
				public void onEvent(final Event event)
				{

					try
					{
						final int Current_Batch_Status = isRunning(context, INDEXER_TYPE);

						if (Current_Batch_Status == ACTIVE_CRON_JOBS)
						{
							//Batch Not running. Need to initiate batch
							startCronJob(context, INDEXER_TYPE);
						}
						else if (Current_Batch_Status == RUNNING_CRON_JOBS)
						{
							//Search Cron Job Running with Busy Image. No action Required 

						}
						UISessionUtils.getCurrentSession().sendGlobalEvent(
								new ItemChangedEvent(this, context.getItem(), Collections.EMPTY_LIST));
					}
					finally
					{
						Clients.showBusy(null, false);
					}
				}


			};

			@Override
			public void onEvent(final Event event) throws Exception
			{
				final Object status = context.getMap().get(CURRENT_STATUS_KEY);
				if (!(RUNNING_CRON_JOBS == ((Integer) status).intValue()))
				{
					final Component target = event.getTarget();
					event.getTarget().removeEventListener("onPartialIndexUpdate", this.listener);
					event.getTarget().addEventListener("onPartialIndexUpdate", this.listener);

					if (target instanceof Image)
					{
						((Image) target).setSrc(getBusyImagePath());
					}
					else
					{
						Clients.showBusy(Labels.getLabel("busy.sync"), true);
					}
					Events.echoEvent("onPartialIndexUpdate", event.getTarget(), null);
				}
			}
		};
	}

	protected String getEnumNameOrCode(final HybrisEnumValue enumValue)
	{
		final String name = getEnumerationService().getEnumerationName(enumValue);
		if (StringUtils.isBlank(name))
		{
			return enumValue.getCode();
		}
		return name;
	}


	@Override
	protected void doCreateContext(final Context context)
	{
		context.getMap().put(CURRENT_STATUS_KEY, Integer.valueOf(isRunning(context, INDEXER_TYPE)));

	}


	@Override
	public String getTooltip(final Context context)
	{
		String tooltip = null;
		if (checkTypeRestriction(context))
		{
			switch (getCurrentStatus(context))
			{
			//Running Batch Jobs
				case RUNNING_CRON_JOBS:
					tooltip = Labels.getLabel("cockpit.job.action.stopPartial");
					break;
				//Active Batches--No Running Jobs			
				case ACTIVE_CRON_JOBS:
					tooltip = Labels.getLabel("cockpit.job.action.startPartial");
					break;
				//No Batches Defined
				case UNKNOWN_CRON_JOBS:
					break;
				default:
					break;
			}
		}
		return tooltip;
	}


	@Override
	public String getImageURI(final Context context)
	{
		String imgURI = null;

		if (checkTypeRestriction(context))
		{
			switch (getCurrentStatus(context))
			{

				case RUNNING_CRON_JOBS:
					imgURI = getBusyImagePath();
					break;

				case ACTIVE_CRON_JOBS:
					imgURI = getActiveJobsImagePath();
					break;

				case UNKNOWN_CRON_JOBS:
					break;
				default:
					break;
			}
		}
		return imgURI;
	}


	public String getBusyImagePath()
	{
		return ICON_BUSY_RUNNING_JOBS;
	}

	public String getActiveJobsImagePath()
	{
		return ICON_ACTIVE_JOBS;
	}


}
