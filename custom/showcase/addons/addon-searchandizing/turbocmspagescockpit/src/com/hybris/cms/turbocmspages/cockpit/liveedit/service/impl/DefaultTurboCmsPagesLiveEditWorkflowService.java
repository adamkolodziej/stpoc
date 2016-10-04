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
package com.hybris.cms.turbocmspages.cockpit.liveedit.service.impl;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.exceptions.RestrictionEvaluationException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.CategoryPageModel;
import de.hybris.platform.cms2.model.restrictions.AbstractRestrictionModel;
import de.hybris.platform.cms2.model.restrictions.CMSCategoryRestrictionModel;
import de.hybris.platform.cms2.servicelayer.data.CMSDataFactory;
import de.hybris.platform.cms2.servicelayer.data.RestrictionData;
import de.hybris.platform.cms2.servicelayer.services.CMSRestrictionService;
import de.hybris.platform.cmscockpit.cms.strategies.CounterpartProductCatalogVersionsStrategy;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.core.PK;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.cms.turbocmspages.cockpit.liveedit.service.TurboCmsPagesLiveEditWorkflowService;
import com.hybris.commercesearch.searchandizing.cockpit.service.SearchandizingLiveEditFacetSearchService;


/**
 * @author rmcotton
 *
 */
public class DefaultTurboCmsPagesLiveEditWorkflowService implements TurboCmsPagesLiveEditWorkflowService
{
	private CounterpartProductCatalogVersionsStrategy counterpartProductCatalogVersionsStrategy;
	private SearchandizingLiveEditFacetSearchService searchandizingLiveEditFacetSearchService;
	private CMSRestrictionService cmsRestrictionService;
	private ModelService modelService;
	private CMSDataFactory cmsDataFactory;
	private CategoryService categoryService;
	private SessionService sessionService;
	private CatalogVersionService catalogVersionService;

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.steroidsliveedit.facades.LiveEditWorkflowFacade#canShowMakeLandingPageButton(java.lang.String,
	 * de.hybris.platform.commercefacades.search.data.SearchStateData)
	 */
	@Override
	public boolean canShowMakeLandingPageButton(final String pagePk, final SearchStateData searchState)
	{
		if (searchState == null || StringUtils.isBlank(pagePk))
		{
			return false;
		}
		final AbstractPageModel page = getModelService().get(PK.parse(pagePk));
		if (Boolean.FALSE.equals(page.getDefaultPage()))
		{
			return false;
		}

		final Boolean result = executeInSessionLocalViewWithProductCatalogRestrictions(new SessionExecutionBody()
		{
			@Override
			public Object execute()
			{
				return Boolean.valueOf(getLiveEditFacetSearchService().isExactCategoryLocation(searchState));
			}
		});
		return result.booleanValue();
	}

	protected <T extends Object> T executeInSessionLocalViewWithProductCatalogRestrictions(final SessionExecutionBody exec)
	{
		return getSessionService().executeInLocalView(new SessionExecutionBody()
		{

			@Override
			public Object execute()
			{
				final Collection<CatalogVersionModel> productCVs = getCounterpartProductCatalogVersionsStrategy()
						.getCounterpartProductCatalogVersions();
				getCatalogVersionService().setSessionCatalogVersions(productCVs);

				return exec.execute();
			}
		});
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.steroidsliveedit.service.LiveEditWorkflowService#canEditPage(java.lang.String,
	 * de.hybris.platform.commercefacades.search.data.SearchStateData)
	 */
	@Override
	public boolean canActivateLandingPageEdit(final String pagePk, final SearchStateData searchState)
	{
		if (searchState == null || StringUtils.isBlank(pagePk))
		{
			return false;
		}

		final AbstractPageModel page = getModelService().get(PK.parse(pagePk));

		if (page instanceof CategoryPageModel)
		{
			final CategoryModel category = getCategoryForPage(searchState);
			if (category != null)
			{
				final RestrictionData rd = getCMSDataFactory().createRestrictionData(category);

				for (final AbstractRestrictionModel restriction : page.getRestrictions())
				{

					if (restriction instanceof CMSCategoryRestrictionModel)
					{

						try
						{
							final boolean result = getCMSRestrictionService().evaluate(restriction, rd);
							if (result)
							{
								return true;
							}
						}
						catch (final RestrictionEvaluationException e)
						{
							throw new IllegalStateException("unexpected error evaluating category restriction for page [" + page.getPk()
									+ "] and category [" + category.getCode() + "]", e);
						}
					}
				}
			}

		}


		return false;
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.steroidsliveedit.facades.LiveEditWorkflowFacade#getOrCreateCategoryRestriction(de.hybris.platform.
	 * commercefacades.search.data.SearchStateData)
	 */
	@Override
	public CategoryModel getCategoryForPage(final SearchStateData searchState)
	{
		final CategoryModel category = executeInSessionLocalViewWithProductCatalogRestrictions(new SessionExecutionBody()
		{
			@Override
			public Object execute()
			{
				final String categoryCode = getLiveEditFacetSearchService().getCategoryCode(searchState);
				return getCategoryService().getCategoryForCode(categoryCode);
			}
		});
		return category;
	}

	@Required
	public void setCMSRestrictionService(final CMSRestrictionService cmsRestrictionService)
	{
		this.cmsRestrictionService = cmsRestrictionService;
	}

	public CMSRestrictionService getCMSRestrictionService()
	{
		return this.cmsRestrictionService;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public CMSDataFactory getCMSDataFactory()
	{
		return cmsDataFactory;
	}

	@Required
	public void setCMSDataFactory(final CMSDataFactory cmsDataFactory)
	{
		this.cmsDataFactory = cmsDataFactory;
	}

	public SearchandizingLiveEditFacetSearchService getLiveEditFacetSearchService()
	{
		return searchandizingLiveEditFacetSearchService;
	}

	@Required
	public void setLiveEditFacetSearchService(
			final SearchandizingLiveEditFacetSearchService searchandizingLiveEditFacetSearchService)
	{
		this.searchandizingLiveEditFacetSearchService = searchandizingLiveEditFacetSearchService;
	}

	public SessionService getSessionService()
	{
		return sessionService;
	}

	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	public CounterpartProductCatalogVersionsStrategy getCounterpartProductCatalogVersionsStrategy()
	{
		return counterpartProductCatalogVersionsStrategy;
	}

	@Required
	public void setCounterpartProductCatalogVersionsStrategy(
			final CounterpartProductCatalogVersionsStrategy counterpartProductCatalogVersionsStrategy)
	{
		this.counterpartProductCatalogVersionsStrategy = counterpartProductCatalogVersionsStrategy;
	}

	public CatalogVersionService getCatalogVersionService()
	{
		return catalogVersionService;
	}

	@Required
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}

	public CategoryService getCategoryService()
	{
		return categoryService;
	}

	@Required
	public void setCategoryService(final CategoryService categoryService)
	{
		this.categoryService = categoryService;
	}
}
