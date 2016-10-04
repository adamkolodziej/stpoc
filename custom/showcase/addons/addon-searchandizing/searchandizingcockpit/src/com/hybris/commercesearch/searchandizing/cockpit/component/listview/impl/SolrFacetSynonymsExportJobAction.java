/**
 * 
 */
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


/**
 * @author shailesh.gajera
 * 
 */
public class SolrFacetSynonymsExportJobAction extends AbstractSynonymsExportJobAction
{

	protected static final String CURRENT_STATUS_KEY = "currentStatus";

	private final int RUNNING_CRON_JOBS = 1;
	private final int ACTIVE_CRON_JOBS = 2;
	private final int UNKNOWN_CRON_JOBS = 0;

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
						final int Current_Batch_Status = isRunning(context);

						if (Current_Batch_Status == ACTIVE_CRON_JOBS)
						{
							//Batch Not running. Need to initiate batch
							performSynonymExportJob(context);
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
					event.getTarget().removeEventListener("onSynonymExport", this.listener);
					event.getTarget().addEventListener("onSynonymExport", this.listener);

					if (target instanceof Image)
					{
						((Image) target).setSrc(getBusyImagePath());
					}
					else
					{
						Clients.showBusy(Labels.getLabel("busy.sync"), true);
					}
					Events.echoEvent("onSynonymExport", event.getTarget(), null);
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
		context.getMap().put(CURRENT_STATUS_KEY, Integer.valueOf(isRunning(context)));

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
					tooltip = Labels.getLabel("cockpit.job.action.stopSynonyms");
					break;
				//Active Batches--No Running Jobs			
				case ACTIVE_CRON_JOBS:
					tooltip = Labels.getLabel("cockpit.job.action.startSynonyms");
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


}
