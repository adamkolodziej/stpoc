/**
 * 
 */
package com.hybris.commercesearch.searchandizing.cockpit.wizard.stategies;

import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.contents.ContentCatalogModel;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cockpit.session.impl.CreateContext;
import de.hybris.platform.cockpit.wizards.generic.strategies.PredefinedValuesStrategy;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
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
public class DefaultContentCatalogVersionDeterminationStrategy implements PredefinedValuesStrategy
{

	private SolrPerspectiveAdminService solrPerspectiveAdminService;
	private CommonI18NService commonI18NService;
	private FlexibleSearchService flexibleSearchService;

	@Override
	public Map<String, Object> getPredefinedValues(final CreateContext ctx)
	{

		final SolrFacetSearchConfigModel solrFacetSearchConfigModel = getSolrPerspectiveAdminService().getActiveFacetSearchConfig();

		final Map<String, Object> ret = new HashMap<String, Object>();
		CatalogVersionModel finalContentCatalogVersionModel = null;
		CatalogVersionModel finalProductCatalogVersionModel = null;
		final Date date = new Date();

		if (solrFacetSearchConfigModel != null)
		{
			final List<CatalogVersionModel> productCatalogVersionList = solrFacetSearchConfigModel.getCatalogVersions();

			if (productCatalogVersionList != null)
			{
				for (final CatalogVersionModel productCatalogVersionModel : productCatalogVersionList)

				{
					if (date.after(productCatalogVersionModel.getModifiedtime()))
					{
						productCatalogVersionModel.getModifiedtime();
						finalProductCatalogVersionModel = productCatalogVersionModel;
					}
				}

				final CatalogModel productCatalog = finalProductCatalogVersionModel.getCatalog();
				final LanguageModel language = getCommonI18NService().getCurrentLanguage();

				final List<CMSSiteModel> cmsSiteList = getCMSSite(productCatalog);

				if (cmsSiteList != null)
				{

					for (final CMSSiteModel cmsSite : cmsSiteList)
					{
						if (cmsSite.getDefaultLanguage().equals(language))
						{
							final List<ContentCatalogModel> contentCatalogList = cmsSite.getContentCatalogs();
							if (contentCatalogList != null)
							{
								for (final ContentCatalogModel contentCatalog : contentCatalogList)
								{
									finalContentCatalogVersionModel = contentCatalog.getActiveCatalogVersion();
								}

							}
						}
					}
				}

				ret.put(AbstractPageModel._TYPECODE + "." + AbstractPageModel.CATALOGVERSION, finalContentCatalogVersionModel);
			}
		}

		return ret;
	}

	public List<CMSSiteModel> getCMSSite(final CatalogModel productCatalog)
	{
		SearchResult<CMSSiteModel> result = null;
		final String QUERY = "select {s2c.source} from {CatalogsForBaseStores AS c2b}, {StoresForCMSSite AS s2c}"
				+ "where {s2c.target}={c2b.source} AND {c2b.target} IN (?productCatalog)";


		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("productCatalog", productCatalog);
		result = getFlexibleSearchService().search(QUERY, params);

		return result.getResult();
	}

	/**
	 * @return the solrSearchandizingAdminService
	 */
	public SolrPerspectiveAdminService getSolrPerspectiveAdminService()
	{
		return solrPerspectiveAdminService;
	}

	public void setSolrSearchandizingAdminService(final SolrPerspectiveAdminService solrPerspectiveAdminService)
	{
		this.solrPerspectiveAdminService = solrPerspectiveAdminService;
	}

	/**
	 * @return the commonI18NService
	 */
	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	/**
	 * @param commonI18NService
	 *           the commonI18NService to set
	 */
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}



}
