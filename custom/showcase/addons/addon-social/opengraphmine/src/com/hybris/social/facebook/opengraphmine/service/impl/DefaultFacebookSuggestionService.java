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
package com.hybris.social.facebook.opengraphmine.service.impl;


import de.hybris.platform.basecommerce.strategies.BaseStoreSelectorStrategy;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.comments.model.CommentModel;
import de.hybris.platform.comments.model.ComponentModel;
import de.hybris.platform.comments.model.DomainModel;
import de.hybris.platform.comments.services.CommentService;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.service.FacebookAuthenticationService;
import com.hybris.social.facebook.opengraphmine.service.FacebookSuggestionService;
import com.hybris.social.facebook.opengraphmine.service.dao.FacebookSuggestionDao;


/**
 * @author rmcotton
 * @author javier
 * 
 *         FIXME rmcotton - Plenty of Scope for Performance Optimisations
 * 
 */
public class DefaultFacebookSuggestionService implements FacebookSuggestionService
{

	private static final Logger LOG = Logger.getLogger(DefaultFacebookSuggestionService.class.getName());

	private FacebookAuthenticationService facebookAuthenticationService;
	private FacebookSuggestionDao facebookSuggestionDao;
	private CatalogVersionService catalogVersionService;

	private CustomerAccountService customerAccountService;
	private CommentService commentService;
	private BaseStoreSelectorStrategy baseStoreSelectorStrategy;

	@Override
	public Map<ProductModel, Set<FacebookUserModel>> getLikedProducts(final Set<FacebookUserModel> facebookUsers)
	{
		final Map<ProductModel, Set<FacebookUserModel>> mapOfProductUsers = getFacebookSuggestionDao().getLikedProducts(
				facebookUsers);
		filterBySessionCatalogVersions(mapOfProductUsers);
		return mapOfProductUsers;
	}

	@Override
	public Map<ProductModel, Set<FacebookUserModel>> getProductsPurchasedBy(final Set<FacebookUserModel> facebookUsers)
	{
		return internalGetProductsPurchasedBy(facebookUsers, null);
	}

	/**
	 * FIXME - Suspect this is very slowww, users with lots of orders coupled with user with lots of friends. Faster way
	 * to do this would be to rely on flexi search query filtering rather than inmemory filtering.
	 */
	protected Map<ProductModel, Set<FacebookUserModel>> internalGetProductsPurchasedBy(final Set<FacebookUserModel> facebookUsers,
			final Set<ProductModel> productsToRetain)
	{

		// creates a map with the productmodel and the set of users related to it
		final Map<ProductModel, Set<FacebookUserModel>> mapOfProductUsers = new HashMap<ProductModel, Set<FacebookUserModel>>();

		if (facebookUsers != null && !facebookUsers.isEmpty())
		{
			for (final FacebookUserModel facebookUserModel : facebookUsers)
			{
				// gets the customer associated to the facebook user (if it exists)
				final CustomerModel linkedCustomerModel = facebookUserModel.getLinkedCustomer();
				if (linkedCustomerModel != null)
				{
					// obtains user's orders in the current store
					final List<OrderModel> listOfOrders = customerAccountService.getOrderList(linkedCustomerModel,
							baseStoreSelectorStrategy.getCurrentBaseStore(), null);

					// iterates through the history entries
					for (final OrderModel order : listOfOrders)
					{
						// obtains its entries
						final List<AbstractOrderEntryModel> abstractOrderEntryModels = order.getEntries();

						// iterates through its entries 
						for (final AbstractOrderEntryModel abstractOrderEntryModel : abstractOrderEntryModels)
						{
							// gets the product associated to the entry
							final ProductModel productModel = abstractOrderEntryModel.getProduct();

							if (CollectionUtils.isNotEmpty(productsToRetain)
									&& !compareProductCatalogVersionAgnostic(productModel, productsToRetain))
							{
								// not product we wish to retain info for
								continue;
							}

							// gets from the map the set of users associated to this product
							Set<FacebookUserModel> setOfUsersAsociatedToProduct = mapOfProductUsers.get(productModel);

							// if it does not exist already
							if (setOfUsersAsociatedToProduct == null)
							{
								// creates it and introduces it in the map
								setOfUsersAsociatedToProduct = new HashSet<FacebookUserModel>();
								mapOfProductUsers.put(productModel, setOfUsersAsociatedToProduct);
							}
							// the facebook user is added to the set of users related to the product
							setOfUsersAsociatedToProduct.add(facebookUserModel);
						}

					}
				}
			}
		}
		filterBySessionCatalogVersions(mapOfProductUsers);
		return mapOfProductUsers;
	}

	protected boolean compareProductCatalogVersionAgnostic(final ProductModel source, final Set<ProductModel> targets)
	{
		for (final ProductModel target : targets)
		{
			if (compareProductCatalogVersionAgnostic(source, target))
			{
				return true;
			}
		}

		return false;
	}

	protected boolean compareProductCatalogVersionAgnostic(final ProductModel source, final ProductModel target)
	{
		return source.getCode().equals(target.getCode())
				&& source.getCatalogVersion().getCatalog().getId().equals(target.getCatalogVersion().getCatalog().getId());
	}

	@Override
	public Map<ProductModel, Set<FacebookUserModel>> getProductsCommentedBy(final Set<FacebookUserModel> facebookUsers)
	{
		return internalGetProductsCommentedBy(facebookUsers, Collections.<ProductModel> emptySet());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * FIXME - This method is just not efficient at all. Pure needs to be totally rewritten.
	 * 
	 * @see de.hybris.platform.social.facebook.service.FacebookSuggestionService#getProductsCommentedBy(java.util.Set)
	 */
	protected Map<ProductModel, Set<FacebookUserModel>> internalGetProductsCommentedBy(final Set<FacebookUserModel> facebookUsers,
			final Set<ProductModel> productsToRetain)
	{
		// creates a map with the productmodel and the set of users related to it
		final Map<ProductModel, Set<FacebookUserModel>> mapOfProductUsers = new HashMap<ProductModel, Set<FacebookUserModel>>();

		if (facebookUsers != null && !facebookUsers.isEmpty())
		{
			// gets the facebook domain
			final DomainModel facebookDomain = getCommentService().getDomainForCode("facebookDomain");

			if (facebookDomain == null)
			{
				LOG.error("Domain model facebookDomain was not found");
				filterBySessionCatalogVersions(mapOfProductUsers);
				return mapOfProductUsers;
			}

			// gets the facebook component related to the domain
			final ComponentModel facebookComponent = getCommentService().getComponentForCode(facebookDomain, "facebookComponent");

			if (facebookComponent == null)
			{
				LOG.error("Component model facebookComponent was not found");
				filterBySessionCatalogVersions(mapOfProductUsers);
				return mapOfProductUsers;
			}

			// iterates through the users
			for (final FacebookUserModel facebookUserModel : facebookUsers)
			{
				// obtains the comments related to user and component
				final List<CommentModel> listOfExistingComments = getCommentService().getComments(facebookUserModel,
						Collections.singleton(facebookComponent), -1, -1);

				// iterates through list of comments
				for (final CommentModel commentModel : listOfExistingComments)
				{
					// if not empty and with one single element
					if (commentModel.getRelatedItems() != null && commentModel.getRelatedItems().size() == 1)
					{
						// gets the related item
						final ItemModel relatedItem = commentModel.getRelatedItems().iterator().next();

						// if related element is a product
						if (relatedItem instanceof ProductModel)
						{
							// gets the product
							final ProductModel productLinked = (ProductModel) commentModel.getRelatedItems().iterator().next();

							if (CollectionUtils.isNotEmpty(productsToRetain)
									&& !compareProductCatalogVersionAgnostic(productLinked, productsToRetain))
							{
								continue;
							}

							// gets the existing map of users related to it
							Set<FacebookUserModel> setOfUsers = mapOfProductUsers.get(productLinked);

							if (setOfUsers == null)
							{
								// if does not exist, creates it and puts it in the map
								setOfUsers = new HashSet<FacebookUserModel>();
								mapOfProductUsers.put(productLinked, setOfUsers);
							}
							// adds current user
							setOfUsers.add(facebookUserModel);
						}
					}
				}

			}
		}
		filterBySessionCatalogVersions(mapOfProductUsers);
		return mapOfProductUsers;
	}

	protected void filterBySessionCatalogVersions(final Map<ProductModel, Set<FacebookUserModel>> mapToFilter)
	{
		final Collection<CatalogVersionModel> versions = getCatalogVersionService().getSessionCatalogVersions();
		final Set<ProductModel> toRemove = new HashSet<ProductModel>();
		if (CollectionUtils.isEmpty(versions))
		{
			return;
		}
		for (final ProductModel p : mapToFilter.keySet())
		{
			if (!versions.contains(p.getCatalogVersion()))
			{
				toRemove.add(p);
			}
		}
		for (final ProductModel rem : toRemove)
		{
			mapToFilter.remove(rem);
		}
	}

	protected Set<FacebookUserModel> getCommunity()
	{

		if (getFacebookAuthenticationService().getCurrentFacebookUser() != null)
		{
			final Set<FacebookUserModel> users = new LinkedHashSet<FacebookUserModel>();
			users.add(getFacebookAuthenticationService().getCurrentFacebookUser());
			users.addAll(getFacebookAuthenticationService().getCurrentFacebookUser().getFriends());
			return users;
		}
		return Collections.emptySet();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.social.facebook.service.FacebookSuggestionService#getCommunityThatLike(de.hybris.platform.core
	 * .model.product.ProductModel)
	 */
	@Override
	public Set<FacebookUserModel> getCommunityThatLike(final ProductModel productModel)
	{
		final Set<FacebookUserModel> users = getCommunity();
		if (CollectionUtils.isNotEmpty(users))
		{
			return getFacebookSuggestionDao().getThoseThatLikeProducts(users, getAllProductsToMatch(productModel));
		}
		return Collections.emptySet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.social.facebook.service.FacebookSuggestionService#getCommunityThatPurchased(de.hybris.platform
	 * .core.model.product.ProductModel)
	 */
	@Override
	public Set<FacebookUserModel> getCommunityThatPurchased(final ProductModel productModel)
	{
		final Set<FacebookUserModel> users = getCommunity();
		if (CollectionUtils.isNotEmpty(users))
		{
			final Map<ProductModel, Set<FacebookUserModel>> result = internalGetProductsPurchasedBy(users,
					getAllProductsToMatch(productModel));
			if (!result.isEmpty())
			{
				final Set<FacebookUserModel> r = new LinkedHashSet<FacebookUserModel>();
				for (final ProductModel p : result.keySet())
				{
					r.addAll(result.get(p));
				}
			}
		}
		return Collections.emptySet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.social.facebook.service.FacebookSuggestionService#getCommunityThatCommented(de.hybris.platform
	 * .core.model.product.ProductModel)
	 */
	@Override
	public Set<FacebookUserModel> getCommunityThatCommented(final ProductModel productModel)
	{
		final Set<FacebookUserModel> users = getCommunity();
		if (CollectionUtils.isNotEmpty(users))
		{
			final Map<ProductModel, Set<FacebookUserModel>> result = internalGetProductsCommentedBy(users,
					getAllProductsToMatch(productModel));
			if (!result.isEmpty())
			{
				final Set<FacebookUserModel> r = new LinkedHashSet<FacebookUserModel>();
				for (final ProductModel p : result.keySet())
				{
					r.addAll(result.get(p));
				}
			}
		}
		return Collections.emptySet();
	}

	protected Set<ProductModel> getAllProductsToMatch(final ProductModel product)
	{
		final Set<ProductModel> runningList = new LinkedHashSet<ProductModel>();
		runningList.add(product);
		recursiveAddAllChildProducts(product, runningList);
		return runningList;
	}

	protected void recursiveAddAllChildProducts(final ProductModel product, final Set<ProductModel> runningList)
	{
		final Collection<VariantProductModel> variants = product.getVariants();

		if (CollectionUtils.isEmpty(variants))
		{
			return;
		}

		runningList.addAll(product.getVariants());
	}

	/**
	 * @param facebookAuthenticationService
	 *           the facebookAuthenticationService to set
	 */
	@Required
	public void setFacebookAuthenticationService(final FacebookAuthenticationService facebookAuthenticationService)
	{
		this.facebookAuthenticationService = facebookAuthenticationService;
	}

	/**
	 * @return the facebookAuthenticationService
	 */
	public FacebookAuthenticationService getFacebookAuthenticationService()
	{
		return facebookAuthenticationService;
	}

	/**
	 * @param facebookSuggestionDao
	 *           the facebookSuggestionDao to set
	 */
	@Required
	public void setFacebookSuggestionDao(final FacebookSuggestionDao facebookSuggestionDao)
	{
		this.facebookSuggestionDao = facebookSuggestionDao;
	}

	/**
	 * @return the facebookSuggestionDao
	 */
	public FacebookSuggestionDao getFacebookSuggestionDao()
	{
		return facebookSuggestionDao;
	}

	/**
	 * @return the customerAccountService
	 */
	public CustomerAccountService getCustomerAccountService()
	{
		return customerAccountService;
	}

	/**
	 * @param customerAccountService
	 *           the customerAccountService to set
	 */
	@Required
	public void setCustomerAccountService(final CustomerAccountService customerAccountService)
	{
		this.customerAccountService = customerAccountService;
	}

	/**
	 * @return the commentService
	 */
	public CommentService getCommentService()
	{
		return commentService;
	}

	/**
	 * @param commentService
	 *           the commentService to set
	 */
	@Required
	public void setCommentService(final CommentService commentService)
	{
		this.commentService = commentService;
	}

	/**
	 * @return the baseStoreSelectorStrategy
	 */
	public BaseStoreSelectorStrategy getBaseStoreSelectorStrategy()
	{
		return baseStoreSelectorStrategy;
	}

	/**
	 * @param baseStoreSelectorStrategy
	 *           the baseStoreSelectorStrategy to set
	 */
	@Required
	public void setBaseStoreSelectorStrategy(final BaseStoreSelectorStrategy baseStoreSelectorStrategy)
	{
		this.baseStoreSelectorStrategy = baseStoreSelectorStrategy;
	}

	@Required
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}

	public CatalogVersionService getCatalogVersionService()
	{
		return this.catalogVersionService;
	}
}
