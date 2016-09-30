/**
 *
 */
package com.hybris.showcase.showcasecockpits.components.listview.actions.impl;

import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.cockpit.components.notifier.Notification;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.cronjob.model.JobModel;
import de.hybris.platform.productcockpit.components.listview.impl.AbstractProductAction;
import de.hybris.platform.servicelayer.cronjob.CronJobDao;
import de.hybris.platform.servicelayer.cronjob.CronJobService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Menupopup;


/**
 * @author i324342
 */
public class StartConfigurationMappingAction extends AbstractProductAction
{
	private static final Logger LOG = Logger.getLogger(StartConfigurationMappingAction.class);

	protected String ICON_FUNC_SYNC_CONFIG = "cockpit/images/icon_func_sync_config.png";
	private static final String PROD_CONFIG_TEMPLATE = "ProductConfigurationTemplate";

	private CronJobService cronJobService;
	private ModelService modelService;
	private BaseSiteService baseSiteService;

	@Override
	public String getImageURI(final Context paramContext)
	{
		final TypedObject item = getItem(paramContext);
		if ((item != null) && (item.getType().getCode().equals(PROD_CONFIG_TEMPLATE)))
		{
			return ICON_FUNC_SYNC_CONFIG;
		}
		return null;
	}

	@Override
	public EventListener getEventListener(final Context paramContext)
	{
		EventListener ret = null;
		final TypedObject item = getItem(paramContext);

		if (item != null)
		{
			final Object itemObject = item.getObject();
			if (item.getType().getCode().equals(PROD_CONFIG_TEMPLATE))
			{
				ret = new EventListener()
				{
					@Override
					public void onEvent(final Event paramEvent) throws Exception
					{
						final ProductModel product = (ProductModel) itemObject;

						if (product.getApprovalStatus().equals(ArticleApprovalStatus.APPROVED))
						{
							final JobModel jobModel = cronJobService.getJob("productConfigurationSyncJob");
							final CronJobModel cronJob = modelService.create("ProductConfigurationSyncCronJob");
							cronJob.setActive(Boolean.TRUE);
							cronJob.setJob(jobModel);
							cronJob.setRemoveOnExit(Boolean.TRUE);
							modelService.setAttributeValue(cronJob, "template", product);
							modelService.setAttributeValue(cronJob, "site", baseSiteService.getBaseSiteForUID("tricast"));
							modelService.save(cronJob);

							cronJobService.performCronJob(cronJob, true);

							final Notification notification = new Notification(Labels.getLabel("message.configuration.sync.end"));
							UISessionUtils.getCurrentSession().getCurrentPerspective().getNotifier().setNotification(notification);
						}
						else
						{
							Messagebox.show(Labels.getLabel("message.item.product.activated"));
						}


					}
				};

			}
		}
		return ret;
	}

	@Override
	public Menupopup getPopup(final Context paramContext)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	@Override
	public Menupopup getContextPopup(final Context paramContext)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTooltip(final Context paramContext)
	{
		final String labelSrc = "gridview.item.sync.config.mapping";
		return Labels.getLabel(labelSrc);
	}

	@Override
	protected void doCreateContext(final Context arg0)
	{
		// YTODO Auto-generated method stub

	}

	public CronJobService getCronJobService() {
		return cronJobService;
	}

	@Required
	public void setCronJobService(CronJobService cronJobService) {
		this.cronJobService = cronJobService;
	}

	public ModelService getModelService() {
		return modelService;
	}

	@Required
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public BaseSiteService getBaseSiteService() {
		return baseSiteService;
	}

	@Required
	public void setBaseSiteService(BaseSiteService baseSiteService) {
		this.baseSiteService = baseSiteService;
	}
}
