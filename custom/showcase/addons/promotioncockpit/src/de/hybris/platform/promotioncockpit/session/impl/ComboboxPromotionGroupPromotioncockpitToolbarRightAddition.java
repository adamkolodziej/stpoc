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
import de.hybris.platform.cockpit.model.meta.PropertyDescriptor;
import de.hybris.platform.cockpit.model.meta.impl.ItemAttributePropertyDescriptor;
import de.hybris.platform.cockpit.model.search.Operator;
import de.hybris.platform.cockpit.model.search.Query;
import de.hybris.platform.cockpit.model.search.SearchParameterValue;
import de.hybris.platform.cockpit.services.search.impl.ItemAttributeSearchDescriptor;
import de.hybris.platform.cockpit.session.BrowserFilter;
import de.hybris.platform.cockpit.session.SearchBrowserModel;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.cockpit.util.UITools;
import de.hybris.platform.core.PK;
import de.hybris.platform.promotions.model.PromotionGroupModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.spring.SpringUtil;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;

import com.hybris.addon.cockpits.components.toolbar.ToolbarRightAddition;
import com.hybris.addon.cockpits.session.MultipleBrowserFilterEnabled;


public class ComboboxPromotionGroupPromotioncockpitToolbarRightAddition implements ToolbarRightAddition
{
	private List<BrowserFilter> values;

	@Override
	public Component getContent(final SearchBrowserModel model, final AbstractMultiViewToolbarBrowserComponent toolbar)
	{
		final Combobox component = new Combobox();
		component.setSclass(getClass().getSimpleName() + "_combobox");
		component.setTooltiptext(Labels.getLabel("promotioncockpit.promotiongroup.filter.tooltip"));
		component.setReadonly(true);
		initValues();

		if (values != null && !values.isEmpty())
		{
			for (final BrowserFilter filter : values)
			{
				final Comboitem item = new Comboitem(filter.getLabel());
				item.setValue(filter);
				component.appendChild(item);
			}
			component.setSelectedIndex(0);
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

	protected FlexibleSearchService getFlexibleSearchService()
	{
		return (FlexibleSearchService) SpringUtil.getBean("flexibleSearchService");
	}

	private void initValues()
	{
		if (values == null)
		{
			values = new ArrayList<BrowserFilter>();
		}
		else
		{
			values.clear();
		}

		final SearchResult<PromotionGroupModel> searchResult = getFlexibleSearchService().search(
				"SELECT {" + PromotionGroupModel.PK + "} FROM {" + PromotionGroupModel._TYPECODE + "} ORDER BY {"
						+ PromotionGroupModel.IDENTIFIER + "}");

		values.add(new EmptyPKBrowserFilterImpl(Labels.getLabel("promotioncockpit.promotiongroup.filter.all")));
		for (final PromotionGroupModel promoGroup : searchResult.getResult())
		{
			values.add(new PKBrowserFilterImpl(promoGroup.getName() != null ? promoGroup.getName() : promoGroup.getIdentifier(),
					promoGroup.getPk()));
		}
	}

	public static class PKBrowserFilterImpl implements BrowserFilter
	{
		private final String label;
		private final PK pk;

		public PKBrowserFilterImpl(final String label, final PK pk)
		{
			this.label = label;
			this.pk = pk;
		}

		@Override
		public String getLabel()
		{
			return label;
		}

		@Override
		public boolean exclude(final Object item)
		{

			return false;
		}

		@Override
		public void filterQuery(final Query query)
		{
			final PropertyDescriptor propertyDescriptor = UISessionUtils.getCurrentSession().getTypeService()
					.getPropertyDescriptor("abstractPromotion.PromotionGroup");
			final ItemAttributeSearchDescriptor searchDescriptor = new ItemAttributeSearchDescriptor(
					(ItemAttributePropertyDescriptor) propertyDescriptor);
			query.addParameterValue(new SearchParameterValue(searchDescriptor, pk, Operator.EQUALS));
		}
	}

	public static class EmptyPKBrowserFilterImpl extends PKBrowserFilterImpl
	{
		EmptyPKBrowserFilterImpl(final String label)
		{
			super(label, null);
		}

		@Override
		public void filterQuery(final Query query)
		{
			//intentionally empty
		}
	}
}
