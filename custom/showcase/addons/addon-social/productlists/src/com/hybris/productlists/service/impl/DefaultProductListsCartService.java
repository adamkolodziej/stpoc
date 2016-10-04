/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 * 
 */
package com.hybris.productlists.service.impl;

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.productlists.data.ListToCartModification;
import com.hybris.productlists.model.ProductListModel;
import com.hybris.productlists.service.ProductListsCartService;
import com.hybris.productlists.service.ProductListsService;
import com.hybris.productlists.service.strategy.ProductListModifiableStrategy;


/**
 * @author rmcotton
 * 
 */
public class DefaultProductListsCartService implements ProductListsCartService
{
	private CommerceCartService commerceCartService;
	private ProductListsService productListsService;
	private Converter<CommerceCartModification, ListToCartModification> listToCartModificationConverter;
	private ProductListModifiableStrategy productListModifiableStrategy;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.productlists.service.ProductListsCartService#moveItemToCart(com.hybris.productlists.model.ProductListModel
	 * , de.hybris.platform.core.model.order.CartModel, de.hybris.platform.core.model.product.ProductModel, long,
	 * de.hybris.platform.core.model.product.UnitModel, boolean)
	 */
	@Override
	public ListToCartModification moveItemToCart(final ProductListModel from, final CartModel to, final ProductModel productModel,
			final long quantity, final UnitModel unit, final boolean forceNewEntry) throws CommerceCartModificationException
	{
		final CommerceCartModification modification = getCommerceCartService().addToCart(to, productModel, quantity, unit,
				forceNewEntry);
		return postProcessCartModification(modification, from, to, productModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.productlists.service.ProductListsCartService#moveItemToCart(com.hybris.productlists.model.ProductListModel
	 * , de.hybris.platform.core.model.order.CartModel, de.hybris.platform.core.model.product.ProductModel,
	 * de.hybris.platform.storelocator.model.PointOfServiceModel, long, de.hybris.platform.core.model.product.UnitModel,
	 * boolean)
	 */
	@Override
	public ListToCartModification moveItemToCart(final ProductListModel from, final CartModel to, final ProductModel productModel,
			final PointOfServiceModel deliveryPointOfService, final long quantity, final UnitModel unit, final boolean forceNewEntry)
			throws CommerceCartModificationException
	{
		final CommerceCartModification modification = getCommerceCartService().addToCart(to, productModel, deliveryPointOfService,
				quantity, unit, forceNewEntry);
		return postProcessCartModification(modification, from, to, productModel);
	}

	protected ListToCartModification postProcessCartModification(final CommerceCartModification cartModification,
			final ProductListModel from, final CartModel to, final ProductModel productModel)
	{
		final ListToCartModification listToCartModification = getListToCartModificationConverter().convert(cartModification);
		if (cartModification.getQuantityAdded() > 0 && getProductListModifiableStrategy().canModify(from))
		{
			getProductListsService().updateProductReceivedAmount(from,
					getProductListsService().getProductListEntryFromList(from, productModel.getCode()),
					Integer.valueOf((int) (cartModification.getQuantityAdded())));
			listToCartModification.setListModified(Boolean.TRUE);
		}
		else
		{
			listToCartModification.setListModified(Boolean.FALSE);
		}
		return listToCartModification;
	}

	public CommerceCartService getCommerceCartService()
	{
		return commerceCartService;
	}

	@Required
	public void setCommerceCartService(final CommerceCartService commerceCartService)
	{
		this.commerceCartService = commerceCartService;
	}

	public ProductListsService getProductListsService()
	{
		return productListsService;
	}

	@Required
	public void setProductListsService(final ProductListsService productListsService)
	{
		this.productListsService = productListsService;
	}

	public Converter<CommerceCartModification, ListToCartModification> getListToCartModificationConverter()
	{
		return listToCartModificationConverter;
	}

	@Required
	public void setListToCartModificationConverter(
			final Converter<CommerceCartModification, ListToCartModification> listToCartModificationConverter)
	{
		this.listToCartModificationConverter = listToCartModificationConverter;
	}

	public ProductListModifiableStrategy getProductListModifiableStrategy()
	{
		return productListModifiableStrategy;
	}

	@Required
	public void setProductListModifiableStrategy(final ProductListModifiableStrategy productListModifiableStrategy)
	{
		this.productListModifiableStrategy = productListModifiableStrategy;
	}



}
