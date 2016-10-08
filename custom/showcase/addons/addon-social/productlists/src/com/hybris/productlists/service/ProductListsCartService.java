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
package com.hybris.productlists.service;

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import com.hybris.productlists.data.ListToCartModification;
import com.hybris.productlists.model.ProductListModel;


/**
 * Service to move items to-from cart and product lists updating quantities where appropriate at from/to components.
 * 
 * @author rmcotton
 * 
 */
public interface ProductListsCartService
{
	/**
	 * Adds to the (existing) {@link CartModel} the (existing) {@link ProductModel} in the given {@link UnitModel} and
	 * with the given <code>quantity</code>. If in the cart already an entry with the given product and given unit exists
	 * the given <code>quantity</code> is added to the the quantity of this cart entry unless <code>forceNewEntry</code>
	 * is set to true. After this the cart is calculated.
	 * 
	 * @param cartModel
	 *           the cart model that exists
	 * @param productModel
	 *           the product model that will be added to the cart
	 * @param quantity
	 *           the quantity of the product
	 * @param unit
	 *           if <code>null</code> {@link ProductService#getOrderableUnit(ProductModel)} is used to determine the unit
	 * @param forceNewEntry
	 *           the force new entry if set to true, new cart entry will be created in any case
	 * @return the cart modification data that includes a statusCode and the actual quantity added to the cart
	 * @throws CommerceCartModificationException
	 *            if the <code>product</code> is a base product OR the quantity is less than 1 or no usable unit was
	 *            found (only when given <code>unit</code> is also <code>null</code>) or any other reason the cart could
	 *            not be modified.
	 */
	ListToCartModification moveItemToCart(ProductListModel from, CartModel to, ProductModel productModel, long quantity,
			UnitModel unit, boolean forceNewEntry) throws CommerceCartModificationException;

	/**
	 * Adds to cart an entry specified with given arguments.
	 * 
	 * @param cartModel
	 *           - cart that will be updated
	 * @param productModel
	 *           § - product that will be added
	 * @param deliveryPointOfService
	 *           - point of service that new entry will be assigned to
	 * @param quantity
	 * @param unit
	 * @param forceNewEntry
	 * @return {@link CommerceCartModification} object
	 * @throws CommerceCartModificationException
	 */
	ListToCartModification moveItemToCart(ProductListModel from, CartModel to, ProductModel productModel,
			PointOfServiceModel deliveryPointOfService, long quantity, UnitModel unit, boolean forceNewEntry)
			throws CommerceCartModificationException;

}
