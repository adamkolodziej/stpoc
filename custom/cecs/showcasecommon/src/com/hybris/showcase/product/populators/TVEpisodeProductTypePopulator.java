package com.hybris.showcase.product.populators;

import com.hybris.showcase.cecs.servicesshowcase.model.TVEpisodeProductModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 * CECS-530 Series name and episode
 * Created by Ovidiu Rosoiu on 08/09/2015.
 */
public class TVEpisodeProductTypePopulator implements Populator<ProductModel, ProductData>
{
    @Override
    public void populate(ProductModel productModel, ProductData productData) throws ConversionException
    {
        if(productModel instanceof TVEpisodeProductModel)
        {
            productData.setTvShowName(((TVEpisodeProductModel) productModel).getTvSeason().getTvShow().getName());
            productData.setSeasonNumber(((TVEpisodeProductModel) productModel).getSeason());
            productData.setEpisodeNumber(((TVEpisodeProductModel) productModel).getEpisode());
        }
    }
}
