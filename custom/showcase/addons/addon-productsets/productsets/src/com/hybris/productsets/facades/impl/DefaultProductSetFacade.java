/**
 * 
 */
package com.hybris.productsets.facades.impl;

import de.hybris.platform.commercefacades.converter.ConfigurablePopulator;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.threadcontext.ThreadContextService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.addon.common.converters.ConfigurableConverter;
import com.hybris.productsets.constants.ProductsetsConstants;
import com.hybris.productsets.enums.ProductSetType;
import com.hybris.productsets.facades.BuyTheSetException;
import com.hybris.productsets.facades.ProductSetFacade;
import com.hybris.productsets.facades.data.ProductSetCartItemData;
import com.hybris.productsets.facades.data.ProductSetData;
import com.hybris.productsets.facades.data.VariantSelectOptionData;
import com.hybris.productsets.model.ProductSetModel;
import com.hybris.productsets.services.ProductSetService;


/**
 * @author dominik.strzyzewski
 * 
 */
public class DefaultProductSetFacade implements ProductSetFacade
{

	private ProductSetService productSetService;
	private ProductService productService;
	private ConfigurableConverter<ProductSetModel, ProductSetData, ProductOption> productSetConverter;
	private Converter<ProductModel, ProductData> productConverter;
	private ConfigurablePopulator<ProductModel, ProductData, ProductOption> productConfiguredPopulator;
	private CartFacade cartFacade;
	private Converter<CartModificationData, ProductSetCartItemData> productSetCartItemConverter;
	private Converter<ProductData, Set<VariantSelectOptionData>> variantSelectOptionConverter;
	private ThreadContextService threadContextService;

	@Override
	public List<ProductSetData> getProductSetsForProduct(final String productCode, final Collection<ProductOption> options)
	{
		final ProductModel productModel = getProductService().getProductForCode(productCode);
		final List<ProductSetModel> productSets = getProductSetService().getProductSetsWithProduct(productModel);
		return prepareData(productModel, productSets, options);
	}

	@Override
	public List<ProductSetData> getProductSetsForProductAndType(final String productCode, final ProductSetType productSetType,
			final Collection<ProductOption> options)
	{
		final ProductModel productModel = getProductService().getProductForCode(productCode);
		final List<ProductSetModel> productSets = getProductSetService().getProductSetsWithProductAndType(productModel,
				productSetType);
		return prepareData(productModel, productSets, options);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.productsets.facades.ProductSetFacade#getProductSetByCode(java.lang.String, java.util.Collection)
	 */
	@Override
	public ProductSetData getFilteredProductSetByCode(final String productCode, final String productSetCode,
			final Collection<ProductOption> options)
	{
		final ProductModel productModel = getProductService().getProductForCode(productCode);
		final ProductSetModel productSetModel = getProductSetService().getProductSetByCode(productSetCode);
		final ProductSetData result = getThreadContextService().executeInContext(
				new ThreadContextService.Executor<ProductSetData, ThreadContextService.Nothing>()
				{

					@Override
					public ProductSetData execute()
					{
						getThreadContextService().setAttribute(ProductsetsConstants.SESSION_CURRENT_PRODUCT, productModel);
						return getProductSetConverter().convert(productSetModel, options);
					}
				});

		return result;
	}

	@Override
	public ProductSetData getProductSetByCode(final String productSetCode, final Collection<ProductOption> options)
	{
		final ProductSetModel productSetModel = getProductSetService().getProductSetByCode(productSetCode);
		return getProductSetConverter().convert(productSetModel, options);

	}

	@Override
	public List<CartModificationData> addSetToCart(final Map<String, Integer> quantities, final Collection<ProductOption> options)
	{
		final List<CartModificationData> cartModifications = new ArrayList<>();

		for (final Entry<String, Integer> entry : quantities.entrySet())
		{
			final String code = entry.getKey();
			final long quantity = entry.getValue().longValue();
			final CartModificationData cartModification = addItemToCart(code, quantity);
			cartModification.setEntry(prepareOrderEntryData(code, quantity, options));

			cartModifications.add(cartModification);
		}
		return cartModifications;
	}

	private OrderEntryData prepareOrderEntryData(final String code, final long quantity, final Collection<ProductOption> options)
	{
		final OrderEntryData orderEntryData = new OrderEntryData();
		orderEntryData.setQuantity(Long.valueOf(quantity));

		final ProductModel productModel = getProductService().getProductForCode(code);
		final ProductData productData = getProductConverter().convert(productModel);
		getProductConfiguredPopulator().populate(productModel, productData, options);
		orderEntryData.setProduct(productData);

		return orderEntryData;
	}

	@Override
	public CartModificationData addItemToCart(final String code, final long quantity)
	{
		try
		{
			if (quantity <= 0)
			{
				throw new BuyTheSetException("QUANTITY_TO_LOW");
			}
			final CartModificationData cartModification = getCartFacade().addToCart(code, quantity);
			if (cartModification.getQuantityAdded() < quantity)
			{
				throw new BuyTheSetException(cartModification.getStatusCode());
			}
			return cartModification;
		}
		catch (final CommerceCartModificationException | BuyTheSetException e)
		{
			final CartModificationData cartModification = new CartModificationData();
			cartModification.setQuantity(quantity);
			cartModification.setQuantityAdded(0);
			cartModification.setStatusCode(e.getMessage());
			return cartModification;
		}
	}

	private List<ProductSetData> prepareData(final ProductModel currentProduct, final List<ProductSetModel> productSets,
			final Collection<ProductOption> options)
	{
		final List<ProductSetData> productSetsData = new ArrayList<>();
		for (final ProductSetModel productSetModel : productSets)
		{
			final ProductSetData result = getThreadContextService().executeInContext(
					new ThreadContextService.Executor<ProductSetData, ThreadContextService.Nothing>()
					{

						@Override
						public ProductSetData execute()
						{
							getThreadContextService().setAttribute(ProductsetsConstants.SESSION_CURRENT_PRODUCT, currentProduct);
							return getProductSetConverter().convert(productSetModel, options);

						}
					});
			productSetsData.add(result);

		}
		return productSetsData;
	}

	@Override
	public List<ProductSetCartItemData> getProductSetCartItemEntries(final List<CartModificationData> modifications)
	{
		final List<ProductSetCartItemData> entries = new ArrayList<ProductSetCartItemData>();
		for (final CartModificationData modificationData : modifications)
		{
			entries.add(getProductSetCartItemConverter().convert(modificationData));
		}
		return entries;
	}

	@Override
	public Set<VariantSelectOptionData> getSelectVariantsForProduct(final ProductData productData)
	{
		final Set<VariantSelectOptionData> variantSelectOptionData = getVariantSelectOptionConverter().convert(productData);
		return variantSelectOptionData;
	}

	@Override
	public Map<String, Set<VariantSelectOptionData>> getSelectVariantsForProducts(final Set<ProductData> products)
	{
		final Map<String, Set<VariantSelectOptionData>> entries = new HashMap<String, Set<VariantSelectOptionData>>();
		for (final ProductData productData : products)
		{
			final Set<VariantSelectOptionData> variantSelectOptionData = getSelectVariantsForProduct(productData);
			entries.put(productData.getCode(), variantSelectOptionData);
		}
		return entries;
	}



	public ProductSetService getProductSetService()
	{
		return productSetService;
	}

	@Required
	public void setProductSetService(final ProductSetService productSetService)
	{
		this.productSetService = productSetService;
	}

	public ConfigurableConverter<ProductSetModel, ProductSetData, ProductOption> getProductSetConverter()
	{
		return productSetConverter;
	}

	@Required
	public void setProductSetConverter(
			final ConfigurableConverter<ProductSetModel, ProductSetData, ProductOption> productSetConverter)
	{
		this.productSetConverter = productSetConverter;
	}

	public ProductService getProductService()
	{
		return productService;
	}

	@Required
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}

	public CartFacade getCartFacade()
	{
		return cartFacade;
	}

	@Required
	public void setCartFacade(final CartFacade cartFacade)
	{
		this.cartFacade = cartFacade;
	}

	/**
	 * @return the productConverter
	 */
	public Converter<ProductModel, ProductData> getProductConverter()
	{
		return productConverter;
	}

	/**
	 * @param productConverter
	 *           the productConverter to set
	 */
	@Required
	public void setProductConverter(final Converter<ProductModel, ProductData> productConverter)
	{
		this.productConverter = productConverter;
	}

	/**
	 * @return the productConfiguredPopulator
	 */
	public ConfigurablePopulator<ProductModel, ProductData, ProductOption> getProductConfiguredPopulator()
	{
		return productConfiguredPopulator;
	}

	/**
	 * @param productConfiguredPopulator
	 *           the productConfiguredPopulator to set
	 */
	@Required
	public void setProductConfiguredPopulator(
			final ConfigurablePopulator<ProductModel, ProductData, ProductOption> productConfiguredPopulator)
	{
		this.productConfiguredPopulator = productConfiguredPopulator;
	}

	/**
	 * @return the productSetCartItemConverter
	 */
	public Converter<CartModificationData, ProductSetCartItemData> getProductSetCartItemConverter()
	{
		return productSetCartItemConverter;
	}

	/**
	 * @param productSetCartItemConverter
	 *           the productSetCartItemConverter to set
	 */
	@Required
	public void setProductSetCartItemConverter(
			final Converter<CartModificationData, ProductSetCartItemData> productSetCartItemConverter)
	{
		this.productSetCartItemConverter = productSetCartItemConverter;
	}

	/**
	 * @return the variantSelectOptionConverter
	 */
	public Converter<ProductData, Set<VariantSelectOptionData>> getVariantSelectOptionConverter()
	{
		return variantSelectOptionConverter;
	}

	/**
	 * @param variantSelectOptionConverter
	 *           the variantSelectOptionConverter to set
	 */
	@Required
	public void setVariantSelectOptionConverter(
			final Converter<ProductData, Set<VariantSelectOptionData>> variantSelectOptionConverter)
	{
		this.variantSelectOptionConverter = variantSelectOptionConverter;
	}

	/**
	 * @return the threadContextService
	 */
	public ThreadContextService getThreadContextService()
	{
		return threadContextService;
	}

	/**
	 * @param threadContextService
	 *           the threadContextService to set
	 */
	@Required
	public void setThreadContextService(final ThreadContextService threadContextService)
	{
		this.threadContextService = threadContextService;
	}
}
