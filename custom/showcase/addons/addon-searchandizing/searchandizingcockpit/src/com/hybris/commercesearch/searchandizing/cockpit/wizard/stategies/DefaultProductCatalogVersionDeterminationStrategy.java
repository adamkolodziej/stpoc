/**
 * 
 */
package com.hybris.commercesearch.searchandizing.cockpit.wizard.stategies;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cockpit.session.impl.CreateContext;
import de.hybris.platform.cockpit.wizards.generic.strategies.PredefinedValuesStrategy;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hybris.commercesearch.searchandizing.cockpit.service.SolrPerspectiveAdminService;


/**
 * @author shailesh.gajera
 * 
 */
public class DefaultProductCatalogVersionDeterminationStrategy implements PredefinedValuesStrategy
{

	private SolrPerspectiveAdminService solrPerspectiveAdminService;

	@Override
	public Map<String, Object> getPredefinedValues(final CreateContext ctx)
	{

		final SolrFacetSearchConfigModel solrFacetSearchConfigModel = getSolrPerspectiveAdminService().getActiveFacetSearchConfig();

		final Map<String, Object> ret = new HashMap<String, Object>();
		CatalogVersionModel finalCatalogVersionModel = null;
		Date date = new Date();

		if (solrFacetSearchConfigModel != null)
		{
			final List<CatalogVersionModel> catalogVersionList = solrFacetSearchConfigModel.getCatalogVersions();

			if (catalogVersionList != null)
			{
				for (final CatalogVersionModel catalogVersionModel : catalogVersionList)

				{
					if (date.after(catalogVersionModel.getModifiedtime()))
					{
						date = catalogVersionModel.getModifiedtime();
						finalCatalogVersionModel = catalogVersionModel;
					}
				}

				ret.put(ProductModel._TYPECODE + "." + ProductModel.CATALOGVERSION, finalCatalogVersionModel);
			}
		}

		return ret;
	}

	/**
	 * @return the solrSearchandizingAdminService
	 */
	public SolrPerspectiveAdminService getSolrPerspectiveAdminService()
	{
		return solrPerspectiveAdminService;
	}

	/**
	 * the solrSearchandizingAdminService to set
	 */
	public void setSolrPerspectiveAdminService(final SolrPerspectiveAdminService solrPerspectiveAdminService)
	{
		this.solrPerspectiveAdminService = solrPerspectiveAdminService;
	}

}
