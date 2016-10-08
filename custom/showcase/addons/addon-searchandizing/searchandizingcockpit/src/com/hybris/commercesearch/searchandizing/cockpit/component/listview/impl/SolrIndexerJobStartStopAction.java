package com.hybris.commercesearch.searchandizing.cockpit.component.listview.impl;

import de.hybris.platform.cockpit.components.listview.ListViewAction;
import de.hybris.platform.core.HybrisEnumValue;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.solrfacetsearch.model.indexer.cron.SolrIndexerCronJobModel;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;


/**
 * @author Richard Cotton
 */
public class SolrIndexerJobStartStopAction extends AbstractJobListViewAction
{
	private EnumerationService enumerationService;

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
						final SolrIndexerCronJobModel cronJobModel = (SolrIndexerCronJobModel) getModel(context);

						if (isRunning(context))
						{
							if (getCronJobService().isAbortable(cronJobModel))
							{
								cronJobModel.setRequestAbort(Boolean.TRUE);
								getCronJobService().requestAbortCronJob(cronJobModel);
							}
						}
						else
						{
							//I guess the logic here is to let the job run and capture errors in log.
							getCronJobService().performCronJob(cronJobModel, true);
						}
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
				event.getTarget().removeEventListener("onCronJobExecute", this.listener);
				event.getTarget().addEventListener("onCronJobExecute", this.listener);

				final SolrIndexerCronJobModel cronJobModel = (SolrIndexerCronJobModel) getModel(context);
				Clients.showBusy(Labels.getLabel("busy.solr.export", new String[]
				{ cronJobModel.getFacetSearchConfig().getName(), getEnumNameOrCode(cronJobModel.getIndexerOperation()) }), true);
				Events.echoEvent("onCronJobExecute", event.getTarget(), null);
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
	public String getImageURI(final ListViewAction.Context context)
	{
		if (isRunning(context))
		{
			return "/cockpit/images/cronjob_stop.png";
		}
		else
		{
			return "/cockpit/images/cronjob_play.png";
		}
	}

	@Override
	public String getTooltip(final ListViewAction.Context context)
	{
		if (isRunning(context))
		{
			return Labels.getLabel("cockpit.job.action.stop");
		}
		else
		{
			return Labels.getLabel("cockpit.job.action.start");
		}
	}

	/**
	 * @return the enumerationService
	 */
	public EnumerationService getEnumerationService()
	{
		return enumerationService;
	}

	/**
	 * @param enumerationService
	 *           the enumerationService to set
	 */
	@Required
	public void setEnumerationService(final EnumerationService enumerationService)
	{
		this.enumerationService = enumerationService;
	}

}
