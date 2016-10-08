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
package de.hybris.platform.promotioncockpit.session.impl;

import de.hybris.platform.cockpit.components.contentbrowser.AbstractMultiViewToolbarBrowserComponent;
import de.hybris.platform.cockpit.session.BrowserFilter;
import de.hybris.platform.cockpit.session.SearchBrowserModel;
import de.hybris.platform.cockpit.util.UITools;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;

import com.hybris.addon.cockpits.components.toolbar.ToolbarRightAddition;
import com.hybris.addon.cockpits.session.MultipleBrowserFilterEnabled;


/**
 * Wrapper for combo box.
 * 
 * Now it's stateless
 * 
 * @author krzysztof.baranski
 * @author miroslaw.szot
 */
public class ComboboxPromotioncockpitToolbarRightAddition implements ToolbarRightAddition
{
	/** List of values that should be available in combo. */
	private List<BrowserFilter> values;
	/**
	 * If {@code true} then first element of {@code values} list will be selected by default. Otherwise no selection
	 * initially.
	 */
	private boolean firstDefault;

	private String tooltipLabelKey;

	@Override
	public Component getContent(final SearchBrowserModel model, final AbstractMultiViewToolbarBrowserComponent toolbar)
	{
		final Combobox component = new Combobox();
		component.setSclass(getClass().getSimpleName() + "_combobox");
		component.setReadonly(true);
		if (StringUtils.isNotEmpty(tooltipLabelKey))
		{
			component.setTooltiptext(Labels.getLabel(tooltipLabelKey));
		}
		if (CollectionUtils.isNotEmpty(values))
		{
			for (final BrowserFilter filter : values)
			{
				final Comboitem item = new Comboitem(filter.getLabel());
				item.setValue(filter);
				component.appendChild(item);
			}
			if (firstDefault)
			{
				component.setSelectedIndex(0);
			}
		}

		UITools.addBusyListener(component, Events.ON_CHANGE, new EventListener()
		{
			@Override
			public void onEvent(final Event event) throws Exception
			{
				final Combobox combobox = (Combobox) event.getTarget();
				final BrowserFilter currentFilter = (BrowserFilter) combobox.getAttribute("currentFilter");

				final Comboitem selectedItem = combobox.getSelectedItem();
				final BrowserFilter newFilter = selectedItem != null ? (BrowserFilter) selectedItem.getValue() : null;

				combobox.setAttribute("currentFilter", newFilter);

				if (model instanceof MultipleBrowserFilterEnabled)
				{
					final MultipleBrowserFilterEnabled multiFilterModel = (MultipleBrowserFilterEnabled) model;
					multiFilterModel.disableFilter(currentFilter);
					multiFilterModel.enableFilter(newFilter);
				}

				model.setBrowserFilter(newFilter); // not sure if needed
				model.updateItems(0);
			}
		}, null, "general.updating.busy");

		return component;
	}

	public List<BrowserFilter> getValues()
	{
		return values;
	}

	public void setValues(final List<BrowserFilter> values)
	{
		this.values = values;
	}

	public boolean isFirstDefault()
	{
		return firstDefault;
	}

	public void setFirstDefault(final boolean firstDefault)
	{
		this.firstDefault = firstDefault;
	}

	public String getTooltipLabelKey()
	{
		return tooltipLabelKey;
	}

	public void setTooltipLabelKey(final String tooltipLabelKey)
	{
		this.tooltipLabelKey = tooltipLabelKey;
	}
}
