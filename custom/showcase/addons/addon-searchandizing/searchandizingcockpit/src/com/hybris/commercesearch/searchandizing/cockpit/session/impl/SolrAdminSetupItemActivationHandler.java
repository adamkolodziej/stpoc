/**
 * 
 */
package com.hybris.commercesearch.searchandizing.cockpit.session.impl;

import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.commercesearch.model.AbstractSolrSearchProfileModel;
import de.hybris.platform.commercesearch.model.SolrHeroProductDefinitionModel;
import de.hybris.platform.commerceservices.model.solrsearch.config.SolrSortFieldModel;
import de.hybris.platform.commerceservices.model.solrsearch.config.SolrSortModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrIndexedPropertyModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrIndexedTypeModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrStopWordModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrSynonymConfigModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrValueRangeSetModel;
import de.hybris.platform.solrfacetsearch.model.redirect.SolrFacetSearchKeywordRedirectModel;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.commercesearch.searchandizing.cockpit.service.SolrPerspectiveAdminService;


/**
 * @author rmcotton
 * 
 */
public class SolrAdminSetupItemActivationHandler implements SolrItemActivationHandler
{

	private SolrPerspectiveAdminService solrPerspectiveAdminService;


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.searchandizing.cockpit.session.impl.SearchandizingItemActivationHandler#handleItemActivated
	 * (de.hybris.platform.cockpit.model.meta.TypedObject)
	 */
	@Override
	public void handleItemActivated(final TypedObject activeItem)
	{
		final Object activatedItem = activeItem.getObject();
		if (activatedItem instanceof SolrFacetSearchConfigModel)
		{
			getSolrPerspectiveAdminService().setActiveFacetSearchConfig((SolrFacetSearchConfigModel) activatedItem);
		}
		else if (activatedItem instanceof SolrIndexedTypeModel)
		{
			getSolrPerspectiveAdminService().setActiveIndexedType((SolrIndexedTypeModel) activatedItem);
		}
		else if (activatedItem instanceof SolrIndexedPropertyModel)
		{
			getSolrPerspectiveAdminService().setActiveIndexedType(((SolrIndexedPropertyModel) activatedItem).getSolrIndexedType());
		}
		else if (activatedItem instanceof SolrSortModel)
		{
			getSolrPerspectiveAdminService().setActiveIndexedType(((SolrSortModel) activatedItem).getIndexedType());
		}
		else if (activatedItem instanceof SolrSortFieldModel)
		{
			if (((SolrSortFieldModel) activatedItem).getSort() != null)
			{
				getSolrPerspectiveAdminService()
						.setActiveIndexedType(((SolrSortFieldModel) activatedItem).getSort().getIndexedType());
			}
			else
			{
				getSolrPerspectiveAdminService().setActiveFacetSearchConfig(null);
			}
		}
		else if (activatedItem instanceof SolrFacetSearchKeywordRedirectModel)
		{
			getSolrPerspectiveAdminService().setActiveFacetSearchConfig(
					((SolrFacetSearchKeywordRedirectModel) activatedItem).getFacetSearchConfig());
		}
		else if (activatedItem instanceof SolrSynonymConfigModel)
		{
			getSolrPerspectiveAdminService().setActiveFacetSearchConfig(
					((SolrSynonymConfigModel) activatedItem).getFacetSearchConfig());
		}
		else if (activatedItem instanceof SolrStopWordModel)
		{
			getSolrPerspectiveAdminService().setActiveFacetSearchConfig(((SolrStopWordModel) activatedItem).getFacetSearchConfig());
		}
		else if (activatedItem instanceof SolrHeroProductDefinitionModel)
		{
			getSolrPerspectiveAdminService().setActiveIndexedType(((SolrHeroProductDefinitionModel) activatedItem).getIndexedType());
		}
		else if (activatedItem instanceof SolrValueRangeSetModel)
		{
			if (((SolrValueRangeSetModel) activatedItem).getFacetSearchConfigs().size() == 1)
			{
				getSolrPerspectiveAdminService().setActiveFacetSearchConfig(
						((SolrValueRangeSetModel) activatedItem).getFacetSearchConfigs().get(0));
			}
		}
		else if (activatedItem instanceof AbstractSolrSearchProfileModel)
		{
			getSolrPerspectiveAdminService().setActiveIndexedType(((AbstractSolrSearchProfileModel) activatedItem).getIndexedType());
		}
	}

	/**
	 * @return the searchandizingAdminService
	 */
	@Required
	public SolrPerspectiveAdminService getSolrPerspectiveAdminService()
	{
		return solrPerspectiveAdminService;
	}

	/**
	 * @param solrPerspectiveAdminService
	 *           the searchandizingAdminService to set
	 */
	@Required
	public void setSolrPerspectiveAdminService(final SolrPerspectiveAdminService solrPerspectiveAdminService)
	{
		this.solrPerspectiveAdminService = solrPerspectiveAdminService;
	}

}
