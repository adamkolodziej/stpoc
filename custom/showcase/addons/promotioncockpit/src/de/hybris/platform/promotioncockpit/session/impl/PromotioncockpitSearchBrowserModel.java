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

import de.hybris.platform.cockpit.components.contentbrowser.AbstractBrowserComponent;
import de.hybris.platform.cockpit.components.contentbrowser.AbstractContentBrowser;
import de.hybris.platform.cockpit.components.contentbrowser.DefaultSearchContentBrowser;
import de.hybris.platform.cockpit.components.contentbrowser.SearchToolbarBrowserComponent;
import de.hybris.platform.cockpit.model.meta.ObjectTemplate;
import de.hybris.platform.cockpit.model.meta.PropertyDescriptor;
import de.hybris.platform.cockpit.model.search.ExtendedSearchResult;
import de.hybris.platform.cockpit.model.search.Query;
import de.hybris.platform.cockpit.model.search.SearchType;
import de.hybris.platform.cockpit.services.search.SearchProvider;
import de.hybris.platform.cockpit.session.BrowserFilter;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.cockpit.session.impl.DefaultSearchBrowserModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zul.Hbox;

import com.hybris.addon.cockpits.components.toolbar.ToolbarRightAddition;
import com.hybris.addon.cockpits.session.MultipleBrowserFilterEnabled;


/**
 * @author michal.milinski
 * @author krzysztof.baranski
 */
public class PromotioncockpitSearchBrowserModel extends DefaultSearchBrowserModel implements MultipleBrowserFilterEnabled
{
	private static final Logger LOG = Logger.getLogger(PromotioncockpitSearchBrowserModel.class);

	private List<ToolbarRightAddition> promotioncockpitToolbarRightAdditions;
	private final List<BrowserFilter> additionalFilters = new ArrayList<BrowserFilter>();

	public PromotioncockpitSearchBrowserModel()
	{
		super(UISessionUtils.getCurrentSession().getTypeService().getObjectTemplate("AbstractPromotion"));
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		final PromotioncockpitSearchBrowserModel dis = (PromotioncockpitSearchBrowserModel) super.clone();
		return dis;
	}

	@Override
	public BrowserFilter getBrowserFilter()
	{
		return null;
	}

	@Override
	protected SearchProvider getSearchProvider()
	{
		return (SearchProvider) SpringUtil.getBean("promotionParameterQuerySearchProvider");
	}

	protected List<ToolbarRightAddition> getPromotioncockpitToolbarRightAdditions()
	{
		if (promotioncockpitToolbarRightAdditions == null)
		{
			promotioncockpitToolbarRightAdditions = (List<ToolbarRightAddition>) SpringUtil
					.getBean("promotioncockpitToolbarRightAddition");
		}
		return promotioncockpitToolbarRightAdditions;
	}

	@Override
	public void enableFilter(final BrowserFilter filter)
	{
		if (filter != null)
		{
			additionalFilters.add(filter);
		}
	}

	@Override
	public void disableFilter(final BrowserFilter filter)
	{
		if (filter != null)
		{
			additionalFilters.remove(filter);
		}
	}

	protected void includeToolbarRightAdditions(final Hbox container, final SearchToolbarBrowserComponent toolbar)
	{
		final List<ToolbarRightAddition> toolbarRightAdditions = getPromotioncockpitToolbarRightAdditions();
		final Component first = container.getFirstChild();

		if (toolbarRightAdditions != null)
		{
			for (final ToolbarRightAddition toolbarRightAddition : toolbarRightAdditions)
			{
				final Component newChild = toolbarRightAddition.getContent(this, toolbar);

				if (first != null)
				{
					container.insertBefore(newChild, first);
				}
				else
				{
					container.appendChild(newChild);
				}

			}
		}
	}

	@Override
	public AbstractContentBrowser createViewComponent()
	{
		return new DefaultSearchContentBrowser()
		{
			@Override
			protected AbstractBrowserComponent createToolbarComponent()
			{
				return new SearchToolbarBrowserComponent(getModel(), this)
				{
					@Override
					protected HtmlBasedComponent createToolbar()
					{
						final HtmlBasedComponent toolbar = super.createToolbar();
						//toolbar.setSclass("cms_catalog_browser_toolbar");
						return toolbar;
					}

					@Override
					protected Hbox createRightToolbarHbox()
					{
						final Hbox ret = super.createRightToolbarHbox();

						includeToolbarRightAdditions(ret, this);

						return ret;
					}
				};
			}
		};
	}

	@Override
	protected ExtendedSearchResult doSearchInternal(final Query query)
	{
		if (query == null)
		{
			throw new IllegalArgumentException("Query can not be null.");
		}

		ExtendedSearchResult result = null;

		final SearchProvider searchProvider = this.getSearchProvider();
		if (searchProvider != null)
		{
			Query searchQuery = null;

			final int pageSize = query.getCount() > 0 ? query.getCount() : getPageSize();

			SearchType selectedType = null;
			if (query.getSelectedTypes().size() == 1)
			{
				selectedType = query.getSelectedTypes().iterator().next();
			}
			else if (!query.getSelectedTypes().isEmpty())
			{
				selectedType = query.getSelectedTypes().iterator().next();
				LOG.warn("Query has ambigious search types. Using '" + selectedType.getCode() + "' for searching.");
			}

			if (selectedType == null)
			{
				selectedType = this.getSearchType();
			}

			searchQuery = new Query(Collections.singletonList(selectedType), query.getSimpleText(), query.getStart(), pageSize);
			searchQuery.setNeedTotalCount(!isSimplePaging());
			searchQuery.setParameterValues(query.getParameterValues());
			searchQuery.setParameterOrValues(query.getParameterOrValues());

			final ObjectTemplate selTemplate = (ObjectTemplate) query.getContextParameter(SearchProvider.SELECTED_OBJECT_TEMPLATE);
			if (selTemplate != null)
			{
				searchQuery.setContextParameter(SearchProvider.SELECTED_OBJECT_TEMPLATE, selTemplate);
			}

			// sort
			final Map<PropertyDescriptor, Boolean> sortCriterion = getSortCriterion(query);

			PropertyDescriptor sortProp = null;
			boolean asc = false;

			if (sortCriterion != null && !sortCriterion.isEmpty())
			{
				sortProp = sortCriterion.keySet().iterator().next();
				if (sortProp == null)
				{
					LOG.warn("Could not add sort criterion (Reason: Specified sort property is null).");
				}
				else
				{
					if (sortCriterion.get(sortProp) != null)
					{
						asc = sortCriterion.get(sortProp).booleanValue();
					}
					searchQuery.addSortCriterion(sortProp, asc);
				}
			}

			// update advanced search model
			this.updateAdvancedSearchModel(searchQuery, sortProp, asc);

			try
			{
				final Query clonedQuery = (Query) searchQuery.clone();
				setLastQuery(clonedQuery);
			}
			catch (final CloneNotSupportedException e)
			{
				LOG.error("Cloning the query is not supported");
			}

			// filter
			if (getBrowserFilter() != null)
			{
				getBrowserFilter().filterQuery(searchQuery);
			}

			for (final BrowserFilter filter : additionalFilters)
			{
				filter.filterQuery(searchQuery);
			}

			result = searchProvider.search(searchQuery);
			this.updateLabels();
		}

		return result;
	}

}
