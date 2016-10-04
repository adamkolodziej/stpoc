/**
 * 
 */
package com.hybris.productsets.services.impl;

import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.servicelayer.daos.CMSPageDao;
import de.hybris.platform.cms2.servicelayer.data.CMSDataFactory;
import de.hybris.platform.cms2.servicelayer.data.RestrictionData;
import de.hybris.platform.cms2.servicelayer.services.CMSRestrictionService;
import de.hybris.platform.core.model.type.ComposedTypeModel;
import de.hybris.platform.servicelayer.type.TypeService;

import java.util.Collection;
import java.util.Set;

import org.apache.log4j.Logger;

import com.hybris.productsets.model.ProductSetModel;
import com.hybris.productsets.model.pages.ProductSetPageModel;
import com.hybris.productsets.services.CMSProductSetPageService;
import com.hybris.productsets.services.ProductSetService;


/**
 * To avoid having other addons dependent on this addon, this class encapsulates the existing cms services. However some
 * code duplicates are still present.
 * 
 * @author mkostic
 * 
 */
public class DefaultCMSProductSetPageService implements CMSProductSetPageService
{
	private static final Logger LOG = Logger.getLogger(DefaultCMSProductSetPageService.class);

	private TypeService typeService;
	private CatalogService catalogService;
	private CMSPageDao cmsPageDao;
	private CMSDataFactory cmsDataFactory;
	private CMSRestrictionService cmsRestrictionService;
	private ProductSetService productSetService;

	@Override
	public ProductSetPageModel getPageForProductSet(final ProductSetModel productSet) throws CMSItemNotFoundException
	{
		final ProductSetPageModel page = (ProductSetPageModel) getSinglePage("ProductSetPage");
		if (page != null)
		{
			LOG.debug("Only one ProductSetPage for productSet [" + productSet.getCode() + "] found. Considering this as default.");
			return page;
		}
		final ComposedTypeModel type = typeService.getComposedTypeForCode("ProductSetPage");
		final Set versions = catalogService.getSessionCatalogVersions();
		final RestrictionData data = cmsDataFactory.createRestrictionData("productSet", productSet);
		final Collection pages = cmsPageDao.findAllPagesByTypeAndCatalogVersions(type, versions);
		final Collection result = cmsRestrictionService.evaluatePages(pages, data);
		if (result.isEmpty())
		{
			throw new CMSItemNotFoundException("No page for product set [" + productSet.getCode() + "] found.");
		}
		if (result.size() > 1)
		{
			LOG.warn("More than one page found for product set [" + productSet.getCode() + "]. Returning default.");
		}
		return ((ProductSetPageModel) result.iterator().next());
	}

	@Override
	public ProductSetPageModel getPageForProductSetCode(final String productSetCode) throws CMSItemNotFoundException
	{
		final ProductSetModel productSetModel = productSetService.getProductSetByCode(productSetCode);
		if (productSetModel == null)
		{
			throw new CMSItemNotFoundException("Could not find productSet with code [" + productSetCode + "]:");
		}
		return getPageForProductSet(productSetModel);
	}

	private AbstractPageModel getSinglePage(final String composedTypeCode)
	{
		final ComposedTypeModel type = this.typeService.getComposedTypeForCode(composedTypeCode);
		final Set versions = this.catalogService.getSessionCatalogVersions();
		final Collection pages = this.cmsPageDao.findAllPagesByTypeAndCatalogVersions(type, versions);
		if (pages.size() == 1)
		{
			final AbstractPageModel page = (AbstractPageModel) pages.iterator().next();
			if (page.getRestrictions().isEmpty())
			{
				return page;
			}
		}
		return null;
	}

	/**
	 * @param typeService
	 *           the typeService to set
	 */
	public void setTypeService(final TypeService typeService)
	{
		this.typeService = typeService;
	}

	/**
	 * @param catalogService
	 *           the catalogService to set
	 */
	public void setCatalogService(final CatalogService catalogService)
	{
		this.catalogService = catalogService;
	}

	/**
	 * @param cmsPageDao
	 *           the cmsPageDao to set
	 */
	public void setCmsPageDao(final CMSPageDao cmsPageDao)
	{
		this.cmsPageDao = cmsPageDao;
	}

	/**
	 * @param cmsDataFactory
	 *           the cmsDataFactory to set
	 */
	public void setCmsDataFactory(final CMSDataFactory cmsDataFactory)
	{
		this.cmsDataFactory = cmsDataFactory;
	}

	/**
	 * @param cmsRestrictionService
	 *           the cmsRestrictionService to set
	 */
	public void setCmsRestrictionService(final CMSRestrictionService cmsRestrictionService)
	{
		this.cmsRestrictionService = cmsRestrictionService;
	}

	/**
	 * @param productSetService
	 *           the productSetService to set
	 */
	public void setProductSetService(final ProductSetService productSetService)
	{
		this.productSetService = productSetService;
	}

}
