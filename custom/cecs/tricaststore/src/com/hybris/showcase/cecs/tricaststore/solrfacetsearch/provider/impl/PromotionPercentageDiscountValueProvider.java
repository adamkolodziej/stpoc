/**
 *
 */
package com.hybris.showcase.cecs.tricaststore.solrfacetsearch.provider.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.promotions.PromotionsService;
import de.hybris.platform.promotions.model.ProductPercentageDiscountPromotionModel;
import de.hybris.platform.promotions.model.ProductPromotionModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;


/**
 *
 */
public class PromotionPercentageDiscountValueProvider extends AbstractPropertyFieldValueProvider
		implements FieldValueProvider, Serializable
{
	private FieldNameProvider fieldNameProvider;
	private PromotionsService promotionService;

	protected FieldNameProvider getFieldNameProvider()
	{
		return fieldNameProvider;
	}

	@Required
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}

	protected PromotionsService getPromotionsService()
	{
		return promotionService;
	}

	@Required
	public void setPromotionsService(final PromotionsService promotionService)
	{
		this.promotionService = promotionService;
	}

	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
			final Object model) throws FieldValueProviderException
	{
		if (model instanceof ProductModel)
		{
			final ProductModel product = (ProductModel) model;

			final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();

			if (indexedProperty.isMultiValue())
			{
				fieldValues.addAll(createFieldValues(product, indexConfig, indexedProperty));
			}
			else
			{
				fieldValues.addAll(createFieldValue(product, indexConfig, indexedProperty));
			}
			return fieldValues;
		}
		else
		{
			throw new FieldValueProviderException("Cannot get promotion percentage of non-product item");
		}
	}

	protected List<FieldValue> createFieldValue(final ProductModel product, final IndexConfig indexConfig,
			final IndexedProperty indexedProperty)
	{
		final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
		final BaseSiteModel baseSiteModel = indexConfig.getBaseSite();
		if (baseSiteModel != null && baseSiteModel.getDefaultPromotionGroup() != null)
		{
			final List<ProductPromotionModel> promotions = getPromotionsService()
					.getProductPromotions(Collections.singletonList(baseSiteModel.getDefaultPromotionGroup()), product);

			if (!promotions.isEmpty() && promotions.get(0) instanceof ProductPercentageDiscountPromotionModel)
			{
				final ProductPercentageDiscountPromotionModel discountPromotion = (ProductPercentageDiscountPromotionModel) promotions
						.get(0);
				addFieldValues(fieldValues, indexedProperty, null, discountPromotion.getPercentageDiscount());
			}
		}

		return fieldValues;

	}

	protected List<FieldValue> createFieldValues(final ProductModel product, final IndexConfig indexConfig,
			final IndexedProperty indexedProperty)
	{
		final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
		final BaseSiteModel baseSiteModel = indexConfig.getBaseSite();
		if (baseSiteModel != null && baseSiteModel.getDefaultPromotionGroup() != null)
		{
			for (final ProductPromotionModel promotion : getPromotionsService()
					.getProductPromotions(Collections.singletonList(baseSiteModel.getDefaultPromotionGroup()), product))
			{
				if (promotion instanceof ProductPercentageDiscountPromotionModel)
				{
					final ProductPercentageDiscountPromotionModel discountPromotion = (ProductPercentageDiscountPromotionModel) promotion;
					addFieldValues(fieldValues, indexedProperty, null, discountPromotion.getPercentageDiscount());
				}
			}
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

}
