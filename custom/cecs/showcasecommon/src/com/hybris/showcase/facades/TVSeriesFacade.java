package com.hybris.showcase.facades;

import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.Collection;
import java.util.List;

import com.hybris.showcase.cecs.servicesshowcase.model.TVSeasonProductModel;
import com.hybris.showcase.cecs.servicesshowcase.model.TVShowProductModel;


/**
 * Created by miroslaw.szot@sap.com on 2016-04-01.
 */
public interface TVSeriesFacade
{

	/**
	 * Strategy for obtaining season when productModel points for show
	 */
	public enum SeasonGetStrategy
	{
		NEWEST, OLDEST
	}

	public ProductData populate(ProductModel productModel, Collection<ProductOption> options);

	public List<ProductData> populate(Collection<? extends ProductModel> productModelList, Collection<ProductOption> options);

	public List<ProductData> getPopulatedEpisodesForSeasonCode(String code, Collection<ProductOption> options);

	public TVShowProductModel getShowForProduct(ProductModel productModel);

	public TVSeasonProductModel getSeasonForProduct(ProductModel productModel, SeasonGetStrategy seasonGetStrategy);

	String getFirstEpisodeFromSeason(TVSeasonProductModel source);

}
