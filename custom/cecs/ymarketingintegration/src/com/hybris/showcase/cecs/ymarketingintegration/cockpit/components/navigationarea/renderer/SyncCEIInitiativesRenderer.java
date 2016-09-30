package com.hybris.showcase.cecs.ymarketingintegration.cockpit.components.navigationarea.renderer;

import de.hybris.platform.cockpit.components.navigationarea.renderer.AbstractNavigationAreaSectionRenderer;
import de.hybris.platform.cockpit.components.notifier.Notification;
import de.hybris.platform.cockpit.components.sectionpanel.Section;
import de.hybris.platform.cockpit.components.sectionpanel.SectionPanel;
import de.hybris.platform.cockpit.events.CockpitEvent;
import de.hybris.platform.cockpit.events.CockpitEventAcceptor;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.cockpit.util.UITools;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.CronJobDao;
import de.hybris.platform.servicelayer.cronjob.CronJobService;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.api.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;


public class SyncCEIInitiativesRenderer extends AbstractNavigationAreaSectionRenderer implements CockpitEventAcceptor
{
	private static final String SYNC_INITIATIVES_CRONJOB_CODE = "retrieveInitiativesCronJob";

	private CronJobDao cronJobDao;
	private CronJobService cronJobService;

	@Override
	public void render(final SectionPanel panel, final Component parent, final Component captionComponent, final Section section)
	{
		final HtmlBasedComponent syncImage = new Image("cockpit/images/icon_workflow.png");
		UITools.addBusyListener(syncImage, Events.ON_CLICK, getSyncEventListener(), null, null);
		syncImage.setTooltiptext(Labels.getLabel("cei.sync"));

		final org.zkoss.zul.api.Label syncLabel = new Label();
		syncLabel.setValue(Labels.getLabel("cei.sync"));
		UITools.addBusyListener(syncLabel, Events.ON_CLICK, getSyncEventListener(), null, null);

		final Component buttonDiv = new Div();
		buttonDiv.appendChild(syncImage);
		buttonDiv.appendChild(syncLabel);
		parent.appendChild(buttonDiv);
	}

	private EventListener getSyncEventListener()
	{
		return new EventListener()
		{
			@Override
			public void onEvent(final Event event)
			{
				final List<CronJobModel> cronJobs = getCronJobDao().findCronJobs(SYNC_INITIATIVES_CRONJOB_CODE);
				if (CollectionUtils.isEmpty(cronJobs))
				{
					throw new IllegalStateException(
							String.format(
									"%s cronjob isn't present in the system, but it should be imported as essential data in ymarketingintegration extension.",
									SYNC_INITIATIVES_CRONJOB_CODE));
				}
				final CronJobModel syncCronJob = cronJobs.iterator().next();
				getCronJobService().performCronJob(syncCronJob, true);

				final Notification notification = new Notification(Labels.getLabel("cei.initiatives.sync.end"));
				UISessionUtils.getCurrentSession().getCurrentPerspective().getNotifier().setNotification(notification);
			}
		};
	}

	@Override
	public void onCockpitEvent(final CockpitEvent event)
	{
		// intentionally empty
	}

	protected CronJobDao getCronJobDao()
	{
		return cronJobDao;
	}

	@Required
	public void setCronJobDao(final CronJobDao cronJobDao)
	{
		this.cronJobDao = cronJobDao;
	}

	protected CronJobService getCronJobService()
	{
		return cronJobService;
	}

	@Required
	public void setCronJobService(final CronJobService cronJobService)
	{
		this.cronJobService = cronJobService;
	}
}
