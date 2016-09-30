/**
 *
 */
package com.hybris.showcase.cecs.ymarketingintegration.cockpit.components.browserarea.actions;

import de.hybris.platform.btg.model.BTGSegmentModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.cockpit.components.listview.AbstractListViewAction;
import de.hybris.platform.cockpit.components.notifier.Notification;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.reports.jasperreports.JasperReportsCache;
import de.hybris.platform.cockpit.session.UICockpitPerspective;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Required;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Menupopup;

import com.hybris.showcase.cecs.ymarketingintegration.services.InitiativesService;
import com.hybris.showcase.services.CustomerService;
import com.hybris.showcase.services.OfflineBTGEvaluationService;


/**
 * @author n.pavlovic
 *
 */
public class BTGSegmentEvaluateAction extends AbstractListViewAction
{
	private InitiativesService initiativesService;
	private OfflineBTGEvaluationService offlineBTGEvaluationService;
	private CMSSiteService cmsSiteService;
	private CustomerService customerService;


	private JasperReportsCache jasperReportCache;

	@Override
	public EventListener getEventListener(final Context context)
	{
		return new EventListener()
		{
			@Override
			public void onEvent(final Event event) throws Exception
			{
				final TypedObject typedObject = context.getItem();
				final Notification notification;

				if (typedObject != null && (typedObject.getObject() instanceof BTGSegmentModel))
				{
					final BTGSegmentModel segment = (BTGSegmentModel) typedObject.getObject();
					final Collection<CustomerModel> customers = getCustomerService().getCustomers();

					for (final CustomerModel customer : customers)
					{
						getInitiativesService().retrieveInitiativesForCustomer(customer);
					}
					final Collection<CMSSiteModel> sites = getCmsSiteService().getSites();
					getOfflineBTGEvaluationService().evaluateSegment(segment, customers, sites, true);
					jasperReportCache.invalidateAll();

					notification = new Notification(Labels.getLabel("cei.initiatives.sync.end"));
				}
				else
				{
					notification = new Notification(Labels.getLabel("cei.initiatives.sync.error"));
				}
				final UICockpitPerspective currentPerspective = UISessionUtils.getCurrentSession().getCurrentPerspective();
				currentPerspective.getBrowserArea().update();
				currentPerspective.getNotifier().setNotification(notification);
			}
		};
	}

	@Override
	public String getImageURI(final Context paramContext)
	{
		return "/cockpit/images/icon_workflow.png";
	}

	@Override
	public String getTooltip(final Context paramContext)
	{
		return Labels.getLabel("cei.segment.evaluate.action");
	}

	@Override
	public Menupopup getPopup(final Context paramContext)
	{
		return null;
	}

	@Override
	public Menupopup getContextPopup(final Context paramContext)
	{
		return null;
	}

	@Override
	protected void doCreateContext(final Context arg0)
	{
		// empty
	}

	public InitiativesService getInitiativesService()
	{
		return initiativesService;
	}

	@Required
	public void setInitiativesService(final InitiativesService initiativesService)
	{
		this.initiativesService = initiativesService;
	}

	public OfflineBTGEvaluationService getOfflineBTGEvaluationService()
	{
		return offlineBTGEvaluationService;
	}

	@Required
	public void setOfflineBTGEvaluationService(final OfflineBTGEvaluationService offlineBTGEvaluationService)
	{
		this.offlineBTGEvaluationService = offlineBTGEvaluationService;
	}

	public CMSSiteService getCmsSiteService()
	{
		return cmsSiteService;
	}

	@Required
	public void setCmsSiteService(final CMSSiteService cmsSiteService)
	{
		this.cmsSiteService = cmsSiteService;
	}

	public JasperReportsCache getJasperReportCache()
	{
		return jasperReportCache;
	}

	@Required
	public void setJasperReportCache(final JasperReportsCache jasperReportCache)
	{
		this.jasperReportCache = jasperReportCache;
	}

	/**
	 * @return the customerService
	 */
	public CustomerService getCustomerService()
	{
		return customerService;
	}

	/**
	 * @param customerService
	 *           the customerService to set
	 */
	@Required
	public void setCustomerService(final CustomerService customerService)
	{
		this.customerService = customerService;
	}
}
