package com.hybris.showcase.cecs.tricaststore.solrfacetsearch.provider.impl;

import com.hybris.showcase.cecs.servicesshowcase.model.TVEpisodeProductModel;
import com.hybris.showcase.cecs.servicesshowcase.model.TVSeasonProductModel;
import com.hybris.showcase.cecs.servicesshowcase.model.TVShowProductModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import org.springframework.beans.factory.annotation.Required;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ovidiu Rosoiu on 09/09/2015.
 */
public class TvShowNameValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable
{
    private FieldNameProvider fieldNameProvider;
    private CommonI18NService commonI18NService;

    protected FieldNameProvider getFieldNameProvider()
    {
        return fieldNameProvider;
    }

    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
    {
        this.fieldNameProvider = fieldNameProvider;
    }

    protected CommonI18NService getCommonI18NService()
    {
        return commonI18NService;
    }

    @Required
    public void setCommonI18NService(final CommonI18NService commonI18NService)
    {
        this.commonI18NService = commonI18NService;
    }

    @Override
    public Collection<FieldValue> getFieldValues(IndexConfig indexConfig, IndexedProperty indexedProperty,
                                                 final Object model) throws FieldValueProviderException
    {
        final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
        if(model instanceof ProductModel) {
            final ProductModel product = (ProductModel) model;
            if (indexedProperty.isLocalized()) {
                final Collection<LanguageModel> languages = indexConfig.getLanguages();
                for (final LanguageModel language : languages) {
                    fieldValues.addAll(createFieldValue(product, language, indexedProperty));
                }
            } else {
                fieldValues.addAll(createFieldValue(product, null, indexedProperty));
            }
            return fieldValues;
        }
        else
        {
            throw new FieldValueProviderException("Cannot evaluate product fields for non-product item");
        }
    }

    protected List<FieldValue> createFieldValue(final ProductModel product, final LanguageModel language,
                                                final IndexedProperty indexedProperty)
    {
        final List<FieldValue> fieldValues = new ArrayList<FieldValue>();

        final String tvShowName = getTvShowName(product, language);
        if (tvShowName != null)
        {
            addFieldValues(fieldValues, indexedProperty, language, tvShowName);
        }

        return fieldValues;
    }

    protected void addFieldValues(final List<FieldValue> fieldValues, final IndexedProperty indexedProperty,
                                  final LanguageModel language, final Object value)
    {
        final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty,
                language == null ? null : language.getIsocode());
        for (final String fieldName : fieldNames)
        {
            fieldValues.add(new FieldValue(fieldName, value));
        }
    }

    private String getTvShowName(final ProductModel product, final LanguageModel language)
    {
        String tvShowName = null;
        Locale loc = commonI18NService.getLocaleForLanguage(language);
        if (product instanceof TVEpisodeProductModel) {
            tvShowName = ((TVEpisodeProductModel) product).getTvSeason().getTvShow().getName(loc);
        } else if (product instanceof TVSeasonProductModel) {
            tvShowName = ((TVSeasonProductModel) product).getTvShow().getName(loc);
        } else if (product instanceof TVShowProductModel) {
            tvShowName = product.getName(loc);
        }
        return tvShowName;
    }
}
