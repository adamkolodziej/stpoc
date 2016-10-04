/**
 * 
 */
package com.hybris.campaigns.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.promotions.jalo.AbstractPromotionRestriction.RestrictionResult;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.campaigns.PromotionRestrictionStrategy;
import com.hybris.campaigns.model.PromotionBaseSiteRestrictionModel;


/**
 * @author rmcotton
 * 
 */
public class BaseSitePromotionRestrictionStrategy implements PromotionRestrictionStrategy<PromotionBaseSiteRestrictionModel>
{

	private BaseSiteService baseSiteService;
	private BaseStoreService baseStoreService;
	private CatalogVersionService catalogVersionService;

	private final static PromotionRestrictionStrategyResult ALLOW_RESULT = new PromotionRestrictionStrategyResult()
	{

		@Override
		public RestrictionResult getRestrictionResult()
		{
			return RestrictionResult.ALLOW;
		}

		@Override
		public Collection<ProductModel> getProductsToRemove()
		{
			return Collections.EMPTY_LIST;
		}

		@Override
		public Collection<ProductModel> getProductsToAdd()
		{
			return Collections.EMPTY_LIST;
		}
	};

	private final static PromotionRestrictionStrategyResult DENY_RESULT = new PromotionRestrictionStrategyResult()
	{

		@Override
		public RestrictionResult getRestrictionResult()
		{
			return RestrictionResult.DENY;
		}

		@Override
		public Collection<ProductModel> getProductsToRemove()
		{
			return Collections.EMPTY_LIST;
		}

		@Override
		public Collection<ProductModel> getProductsToAdd()
		{
			return Collections.EMPTY_LIST;
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.showcasepromotions.PromotionRestrictionStrategy#evaluate(java.util.Collection,
	 * java.util.Date, de.hybris.platform.core.model.order.AbstractOrderModel)
	 */
	@Override
	public PromotionRestrictionStrategyResult evaluate(final PromotionBaseSiteRestrictionModel restriction,
			final Collection<ProductModel> products, final Date date, final AbstractOrderModel order)
	{

		// sites empty, ALLOW
		if (restriction.getRestrictedToSites().isEmpty())
		{
			return ALLOW_RESULT;
		}


		// get the site from the provided order or base site service. if not null compare
		final BaseSiteModel currentBaseSite = order != null && order.getSite() != null ? order.getSite() : getBaseSiteService()
				.getCurrentBaseSite();
		if (currentBaseSite != null)
		{
			if (restriction.getRestrictedToSites().contains(currentBaseSite))
			{
				return ALLOW_RESULT;
			}
			else
			{
				return DENY_RESULT;
			}
		}

		// if there is no site then try the base store, target order or fallback to base store service.
		final BaseStoreModel currentBaseStore = order != null && order.getStore() != null ? order.getStore()
				: getBaseStoreService().getCurrentBaseStore();
		if (currentBaseStore != null)
		{
			if (CollectionUtils.containsAny(restriction.getRestrictedToSites(), currentBaseStore.getCmsSites()))
			{
				return ALLOW_RESULT;
			}
			else
			{
				return DENY_RESULT;
			}
		}

		// if we are in admin mode (solr export) we might not have a basesite or store. We will have activate catalogs so we use
		// these as a last result.

		final Set<CatalogModel> catalogs = getCatalogs(getCatalogVersionService().getSessionCatalogVersions());


		for (final BaseSiteModel allowedSite : restriction.getRestrictedToSites())
		{
			if (CollectionUtils.containsAny(catalogs, getBaseSiteService().getProductCatalogs(allowedSite)))
			{
				return ALLOW_RESULT;
			}
		}


		return DENY_RESULT;
	}

	protected final Set<CatalogModel> getCatalogs(final Collection<CatalogVersionModel> catalogVersions)
	{
		final Set<CatalogModel> catalogs = new LinkedHashSet<CatalogModel>();
		for (final CatalogVersionModel version : catalogVersions)
		{
			catalogs.add(version.getCatalog());
		}
		return catalogs;
	}

	/**
	 * @return the baseSiteService
	 */
	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	/**
	 * @param baseSiteService
	 *           the baseSiteService to set
	 */
	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	/**
	 * @return the baseStoreService
	 */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	/**
	 * @param baseStoreService
	 *           the baseStoreService to set
	 */
	@Required
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	/**
	 * @return the catalogVersionService
	 */
	public CatalogVersionService getCatalogVersionService()
	{
		return catalogVersionService;
	}

	/**
	 * @param catalogVersionService
	 *           the catalogVersionService to set
	 */
	@Required
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}

}
