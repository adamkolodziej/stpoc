package com.hybris.showcase.facades.impl;

import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.cecs.servicesshowcase.model.TVEpisodeProductModel;
import com.hybris.showcase.cecs.servicesshowcase.model.TVSeasonProductModel;
import com.hybris.showcase.cecs.servicesshowcase.model.TVShowProductModel;
import com.hybris.showcase.facades.TVSeriesFacade;


/**
 * Created by miroslaw.szot@sap.com on 2016-04-01.
 */
public class DefaultTVSeriesFacade implements TVSeriesFacade
{
	private static final Logger LOG = Logger.getLogger(DefaultTVSeriesFacade.class);
	private ProductService productService;
	private ProductFacade productFacade;

	public ProductService getProductService()
	{
		return productService;
	}

	@Required
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}

	public ProductFacade getProductFacade()
	{
		return productFacade;
	}

	@Required
	public void setProductFacade(final ProductFacade productFacade)
	{
		this.productFacade = productFacade;
	}

	@Override
	public ProductData populate(final ProductModel productModel, final Collection<ProductOption> options)
	{
		return productFacade.getProductForOptions(productModel, options);
	}

	@Override
	public List<ProductData> populate(final Collection<? extends ProductModel> productModelList,
			final Collection<ProductOption> options)
	{
		final ArrayList<ProductData> productDataList = new ArrayList<>();

		for (final ProductModel model : productModelList)
		{
			productDataList.add(populate(model, options));
		}

		return productDataList;
	}

	@Override
	public List<ProductData> getPopulatedEpisodesForSeasonCode(final String code, final Collection<ProductOption> options)
	{
		final TVSeasonProductModel productModel = (TVSeasonProductModel) productService.getProductForCode(code);
		final Collection<? extends ProductModel> episodes = productModel == null ? Collections.emptyList()
				: productModel.getEpisodes();

		return populate(episodes, options);
	}

	@Override
	public TVShowProductModel getShowForProduct(final ProductModel productModel)
	{
		if (productModel instanceof TVShowProductModel)
		{
			return (TVShowProductModel) productModel;
		}

		if (productModel instanceof TVEpisodeProductModel)
		{
			final TVEpisodeProductModel tVEpisodeProductModel = (TVEpisodeProductModel) productModel;
			return tVEpisodeProductModel.getTvSeason().getTvShow();
		}

		if (productModel instanceof TVSeasonProductModel)
		{
			final TVSeasonProductModel tVSeasonProductModel = (TVSeasonProductModel) productModel;
			return tVSeasonProductModel.getTvShow();
		}

		return null;
	}

	@Override
	public TVSeasonProductModel getSeasonForProduct(final ProductModel productModel, final SeasonGetStrategy seasonGetStrategy)
	{
		if (seasonGetStrategy == null)
		{
			throw new NullPointerException();
		}

		if (productModel instanceof TVEpisodeProductModel)
		{
			final TVEpisodeProductModel tVEpisodeProductModel = (TVEpisodeProductModel) productModel;
			return tVEpisodeProductModel.getTvSeason();
		}
		else if (productModel instanceof TVSeasonProductModel)
		{
			return (TVSeasonProductModel) productModel;
		}
		else if (productModel instanceof TVShowProductModel)
		{
			final TVShowProductModel tVShowProductModel = (TVShowProductModel) productModel;
			final TVSeasonProductModel[] seasonList = tVShowProductModel.getSeasons().toArray(new TVSeasonProductModel[0]);

			if (seasonList.length == 0)
			{
				return null;
			}
			else if (SeasonGetStrategy.NEWEST.equals(seasonGetStrategy))
			{
				return seasonList[seasonList.length - 1];
			}
			else
			{
				return seasonList[0];
			}
		}

		return null;
	}

	@Override
	public String getFirstEpisodeFromSeason(final TVSeasonProductModel source)
	{
		final Collection<TVEpisodeProductModel> episodes = source.getEpisodes();
		if (CollectionUtils.isNotEmpty(source.getEpisodes()))
		{
			final Optional<TVEpisodeProductModel> firstEpisode = episodes.stream().findFirst();
			return firstEpisode.get().getCode();
		}
		LOG.warn("Selected tv season hasn`t episode.");
		return null;
	}
}
