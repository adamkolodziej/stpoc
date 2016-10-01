package com.hybris.servicesshowcase.facades.impl;

import de.hybris.platform.btg.model.BTGSegmentModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.util.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.servicesshowcase.facades.ControlPanelFacade;
import com.hybris.servicesshowcase.services.BTGSegmentEvaluationService;
import com.hybris.showcase.servicesshowcase.data.ControlPanelData;
import com.hybris.showcase.servicesshowcase.data.ControlPanelMessage;
import com.hybris.showcase.servicesshowcase.data.ControlPanelPropertyData;
import com.hybris.showcase.servicesshowcase.data.ControlPanelUrlData;
import com.hybris.showcase.servicesshowcase.data.Severity;


/**
 * Created by m.golubovic on 25.6.2015..
 */
public class DefaultControlPanelFacade implements ControlPanelFacade
{
	private BTGSegmentEvaluationService btgSegmentEvaluationService;
	private CMSSiteService cmsSiteService;

	@Override
	public ControlPanelData getControlPanelData(final String serverName)
	{
		final ControlPanelData controlPanelData = new ControlPanelData();
		final List<ControlPanelPropertyData> controlPanelProperties = new ArrayList<>();
		for (final String key : getControlPanelProperties())
		{
			final ControlPanelPropertyData controlPanelProperty = new ControlPanelPropertyData();
			controlPanelProperty.setKey(key);
			controlPanelProperty.setValue(Config.getString(key, null));
			controlPanelProperty.setPossibleValues(getPossibleValues(key));
			controlPanelProperties.add(controlPanelProperty);
		}
		controlPanelData.setControlPanelProperties(controlPanelProperties);

		final List<ControlPanelUrlData> controlPanelUrls = new ArrayList<>();
		final Map<String, String> urls = getControlPanelUrls();
		for (final String key : urls.keySet())
		{
			final ControlPanelUrlData controlPanelUrlData = new ControlPanelUrlData();
			controlPanelUrlData.setLabel(key);
			controlPanelUrlData.setUrl(serverName + urls.get(key));
			controlPanelUrls.add(controlPanelUrlData);
		}
		controlPanelData.setControlPanelUrls(controlPanelUrls);

		return controlPanelData;
	}

	@Override
	public ControlPanelData saveAll(final ControlPanelData controlPanelData)
	{
		clearMessages(controlPanelData);
		for (final ControlPanelPropertyData controlPanelPropertyData : controlPanelData.getControlPanelProperties())
		{
			Config.setParameter(controlPanelPropertyData.getKey(), controlPanelPropertyData.getValue());
		}
		addMessage(controlPanelData, Severity.INFO, "All changes have been saved successfully.");

		return controlPanelData;
	}

	@Override
	public Map<String, String> getApplicableBtgSegmentsMap()
	{
		final Collection<CMSSiteModel> sites = cmsSiteService.getSites();
		final List<BTGSegmentModel> segments = btgSegmentEvaluationService.getApplicableBtgSegments((List<CMSSiteModel>) sites);
		final Map<String, String> segmentsMap = new TreeMap<>();
		for (final BTGSegmentModel btgSegmentModel : segments)
		{
			segmentsMap.put(btgSegmentModel.getName(), btgSegmentModel.getUid());
		}
		return segmentsMap;
	}

	@Override
	public ControlPanelMessage evaluateSegment(final String uid)
	{
		final ControlPanelMessage controlPanelMessage = new ControlPanelMessage();
		final Collection<CMSSiteModel> sites = cmsSiteService.getSites();
		try
		{
			final List<BTGSegmentModel> segments = btgSegmentEvaluationService.getApplicableBtgSegments((List<CMSSiteModel>) sites);
			for (final BTGSegmentModel segmentModel : segments)
			{
				if (segmentModel.getUid().equals(uid))
				{
					btgSegmentEvaluationService.evaluateSegment(segmentModel);
					controlPanelMessage.setSeverity(Severity.INFO);
					controlPanelMessage.setContent("Segment evaluated successfully.");
					return controlPanelMessage;
				}
			}
		}
		catch (final Exception ex)
		{
			controlPanelMessage.setSeverity(Severity.ERROR);
			controlPanelMessage.setContent("Error during segment evaluation.");
			return controlPanelMessage;
		}

		controlPanelMessage.setSeverity(Severity.ERROR);
		controlPanelMessage.setContent("Segment not applicable.");
		return controlPanelMessage;
	}

	protected List<String> getControlPanelProperties()
	{
		final List<String> properties = new ArrayList<String>();
		properties.add("map.email.brown.joe.ny_at_gmail.com");
		properties.add("map.email.jane.brewster.ny_at_gmail.com");
		properties.add("email.attach.images");
		properties.add("emailservice.send.enabled");
		properties.add("seewhy.key");
		properties.add("seewhy.mock.products");
		properties.add("mobilesptel.require.entitlement");
		return properties;
	}

	protected List<String> getPossibleValues(final String key)
	{
		if (key.equals("email.attach.images") || key.equals("emailservice.send.enabled")
				|| key.equals("mobilesptel.require.entitlement"))
		{
			return Arrays.asList("true", "false");
		}
		else if (key.equals("seewhy.key"))
		{
			return Arrays.asList("disabled", "value", "mock");
		}
		return new ArrayList<>();
	}

	protected Map<String, String> getControlPanelUrls()
	{
		final Map<String, String> urls = new TreeMap<>();
		urls.put("storefront", "/yacceleratorstorefront/");
		urls.put("ems-ui", "/emsui/");
		urls.put("ems init console", "/entitlements-web/init-app-web/console/main");
		return urls;
	}

	protected void clearMessages(final ControlPanelData controlPanelData)
	{
		controlPanelData.setMessages(new ArrayList<ControlPanelMessage>());
	}

	protected void addMessage(final ControlPanelData controlPanelData, final Severity severity, final String message)
	{
		List<ControlPanelMessage> allMessages = controlPanelData.getMessages();
		if (allMessages == null)
		{
			allMessages = new ArrayList<>();
		}
		final ControlPanelMessage controlPanelMessage = new ControlPanelMessage();
		controlPanelMessage.setSeverity(severity);
		controlPanelMessage.setContent(message);
		allMessages.add(controlPanelMessage);

		controlPanelData.setMessages(allMessages);
	}

	public BTGSegmentEvaluationService getBtgSegmentEvaluationService()
	{
		return btgSegmentEvaluationService;
	}

	@Required
	public void setBtgSegmentEvaluationService(final BTGSegmentEvaluationService btgSegmentEvaluationService)
	{
		this.btgSegmentEvaluationService = btgSegmentEvaluationService;
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
}
