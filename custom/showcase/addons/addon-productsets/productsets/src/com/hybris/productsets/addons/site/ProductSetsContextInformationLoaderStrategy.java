/**
 * 
 */
package com.hybris.productsets.addons.site;

import de.hybris.platform.acceleratorcms.preview.strategies.PreviewContextInformationLoaderStrategy;
import de.hybris.platform.cms2.model.preview.PreviewDataModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.core.model.type.ComposedTypeModel;
import de.hybris.platform.search.restriction.SearchRestrictionService;
import de.hybris.platform.search.restriction.session.SessionSearchRestriction;
import de.hybris.platform.servicelayer.type.TypeService;

import java.util.Arrays;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.addon.common.strategies.SiteContextInformationLoaderStrategy;
import com.hybris.productsets.model.ProductSetModel;


/**
 * @author miroslaw.szot
 * 
 */
public class ProductSetsContextInformationLoaderStrategy implements SiteContextInformationLoaderStrategy,
		PreviewContextInformationLoaderStrategy
{
	private static final Logger LOG = Logger.getLogger(ProductSetsContextInformationLoaderStrategy.class);
	private SearchRestrictionService searchRestrictionService;
	private TypeService typeService;

	@Override
	public void initContextFromPreview(final PreviewDataModel preview)
	{
		loadRestrictions();
	}

	@Override
	public void loadContextInformation(final CMSSiteModel site)
	{
		loadRestrictions();
	}

	private void loadRestrictions()
	{
		final SessionSearchRestriction productSetRestriction = createProductSetSearchRestriciton();

		// remove previous restrictions if exist to avoid duplicated ContextQueryFilter's
		if (CollectionUtils.isNotEmpty(searchRestrictionService.getSessionSearchRestrictions()))
		{
			searchRestrictionService.removeSessionSearchRestrictions(Arrays.asList(productSetRestriction));
		}

		searchRestrictionService.addSessionSearchRestrictions(productSetRestriction);
	}

	protected SessionSearchRestriction createProductSetSearchRestriciton()
	{
		final ComposedTypeModel type = typeService.getComposedTypeForCode(ProductSetModel._TYPECODE);
		final String query = "{item:pk} = ANY ("// 
				+ "{{ SELECT {ppr:source} FROM {ProductSet2PublicationPeriodRelation AS ppr JOIN PublicationPeriod as pp ON {pp:pk} = {ppr:target} } "// 
				+ " WHERE {pp:code} IN (?session.activePublicationPeriods) "// 
				+ "}})"// 
				+ " OR NOT EXISTS ({{ "// 
				+ " SELECT {ppr:target} FROM {ProductSet2PublicationPeriodRelation AS ppr} WHERE {ppr:source} = {item:pk} "// 
				+ "}})";// 

		final SessionSearchRestriction cmsItemSearchRestriction = new SessionSearchRestriction("productSetsPubPeriods", query, type);
		return cmsItemSearchRestriction;
	}

	public SearchRestrictionService getSearchRestrictionService()
	{
		return searchRestrictionService;
	}

	@Required
	public void setSearchRestrictionService(final SearchRestrictionService searchRestrictionService)
	{
		this.searchRestrictionService = searchRestrictionService;
	}

	public TypeService getTypeService()
	{
		return typeService;
	}

	@Required
	public void setTypeService(final TypeService typeService)
	{
		this.typeService = typeService;
	}


}
