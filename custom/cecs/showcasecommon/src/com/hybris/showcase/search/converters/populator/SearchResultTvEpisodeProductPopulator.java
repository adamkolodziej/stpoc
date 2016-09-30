package com.hybris.showcase.search.converters.populator;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 * Created by Ovidiu Rosoiu on 08/09/2015.
 */
public class SearchResultTvEpisodeProductPopulator implements Populator<SearchResultValueData, ProductData> {

    @Override
    public void populate(SearchResultValueData source, ProductData target) throws ConversionException {
        target.setTvShowName(this.<String> getValue(source, "tvShowName"));
        target.setSeasonNumber(this.<Integer> getValue(source, "season"));
        target.setEpisodeNumber(this.<Integer> getValue(source, "episode"));
    }

    protected <T> T getValue(final SearchResultValueData source, final String propertyName)
    {
        if (source.getValues() == null)
        {
            return null;
        }

        // DO NOT REMOVE the cast (T) below, while it should be unnecessary it is required by the javac compiler
        return (T) source.getValues().get(propertyName);
    }
}
