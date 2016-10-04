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
package com.hybris.social.facebook.opengraphmine.service.dao.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.service.dao.FacebookSuggestionDao;


/**
 * @author rmcotton
 * 
 */
public class DefaultFacebookSuggestionDao extends AbstractItemDao implements FacebookSuggestionDao
{

	private static final String UNION_QUERY = "select x.PROD, x.USER FROM ({{ %s %s }} UNION {{ %s %s }} ) x ORDER BY x.PROD desc";

	private static final String LIKED_URL_PRODUCT_QUERY = "select {p2.PK} AS PROD, {user2urllikes.source} AS USER " //
			+ "from {Product as p2}, {FacebookUser2UrlLikes as user2urllikes}, {FacebookUrlLike as urllike} " //
			+ "where {p2.PK} = {urllike.linkedProduct}  " //
			+ "and   {urllike.PK} = {user2urllikes.target} " //
			+ "and   {user2urllikes.source} IN (?users) ";


	private static final String LIKED_PRODUCTS_QUERY = "select {p.PK} AS PROD , {user2likes.source} AS USER " //
			+ "from {Product as p}, {FacebookReference2Products as ref}, {FacebookUser2Likes as user2likes} , {FacebookPage as page} "
			+ "where {p.PK} = {ref.target} " //
			+ "and {ref.source} = {page.PK} " //
			+ "and {user2likes.target} = {page.PK} " //
			+ "and {user2likes.source} IN (?users) "; //

	private static final String LIKED_PRODUCTS_QUERY_PRODUCTS_WHERE_PART = "and {p.PK} in (?products) ";
	private static final String LIKED_URL_PRODUCT_QUERY_PRODUCTS_WHERE_PART = "and {p2.PK} in (?products) ";


	public Map<ProductModel, Set<FacebookUserModel>> internalGetLikedProducts(final Set<FacebookUserModel> facebookUsers,
			final Set<ProductModel> products)
	{
		// creates the result map
		final Map<ProductModel, Set<FacebookUserModel>> mapOfProductsUsers = new HashMap<ProductModel, Set<FacebookUserModel>>();

		final String productFilter1 = CollectionUtils.isNotEmpty(products) ? LIKED_PRODUCTS_QUERY_PRODUCTS_WHERE_PART : "";
		final String productFilter2 = CollectionUtils.isNotEmpty(products) ? LIKED_URL_PRODUCT_QUERY_PRODUCTS_WHERE_PART : "";


		final StringBuilder query = new StringBuilder(String.format(UNION_QUERY, LIKED_PRODUCTS_QUERY, productFilter1,
				LIKED_URL_PRODUCT_QUERY, productFilter2));


		final FlexibleSearchQuery fsq = new FlexibleSearchQuery(query.toString());
		// each row of the resulting array contains two columns: productmodel and facebook user
		fsq.setResultClassList(Arrays.asList(ProductModel.class, FacebookUserModel.class));
		fsq.addQueryParameter("users", facebookUsers);
		if (CollectionUtils.isNotEmpty(products))
		{
			fsq.addQueryParameter("products", products);
		}

		final SearchResult searchResult = getFlexibleSearchService().search(fsq);
		final List<List> resultOuterList = searchResult.getResult();

		for (final List resultInnerList : resultOuterList)
		{
			// gets the product
			final ProductModel productModel = (ProductModel) resultInnerList.get(0);
			// gets the facebook user related to the product
			final FacebookUserModel facebookUserModel = (FacebookUserModel) resultInnerList.get(1);

			// obtains the set of facebook userd already related to the product from the map
			Set<FacebookUserModel> setOfUsersAsociatedToProduct = mapOfProductsUsers.get(productModel);
			// if it does not exist yet
			if (setOfUsersAsociatedToProduct == null)
			{
				// creates it and introduces it in the map
				setOfUsersAsociatedToProduct = new HashSet<FacebookUserModel>();
				mapOfProductsUsers.put(productModel, setOfUsersAsociatedToProduct);
			}
			// adds the current facebook user to the set of users associated to the current product
			setOfUsersAsociatedToProduct.add(facebookUserModel);

		}

		return mapOfProductsUsers;
	}

	@Override
	public Map<ProductModel, Set<FacebookUserModel>> getLikedProducts(final Set<FacebookUserModel> facebookUsers)
	{
		return internalGetLikedProducts(facebookUsers, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.social.facebook.service.dao.FacebookSuggestionDao#getThoseThatLikeProduct(java.util.Set,
	 * de.hybris.platform.core.model.product.ProductModel)
	 */
	@Override
	public Set<FacebookUserModel> getThoseThatLikeProducts(final Set<FacebookUserModel> facebookUsers,
			final Set<ProductModel> products)
	{
		final Map<ProductModel, Set<FacebookUserModel>> result = internalGetLikedProducts(facebookUsers, products);

		final Set<FacebookUserModel> users = new LinkedHashSet<FacebookUserModel>();

		for (final ProductModel product : products)
		{
			if (result.containsKey(product))
			{
				users.addAll(result.get(product));
			}
		}

		return users;
	}
}
