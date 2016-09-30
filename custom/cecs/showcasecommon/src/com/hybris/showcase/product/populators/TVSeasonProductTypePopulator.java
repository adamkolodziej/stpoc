package com.hybris.showcase.product.populators;

import com.hybris.showcase.cecs.servicesshowcase.model.TVEpisodeProductModel;
import com.hybris.showcase.cecs.servicesshowcase.model.TVSeasonProductModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 * CECS-530 Series name and episode
 * Created by Ovidiu Rosoiu on 08/09/2015.
 */
public class TVSeasonProductTypePopulator implements Populator<ProductModel, ProductData>
{
    @Override
    public void populate(ProductModel productModel, ProductData productData) throws ConversionException
    {
        if(productModel instanceof TVSeasonProductModel)
        {

            productData.setTvShowName(((TVSeasonProductModel) productModel).getTvShow().getName());
            productData.setSeasonNumber(((TVSeasonProductModel) productModel).getSeason());
        }
    }
}
