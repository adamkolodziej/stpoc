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
package com.hybris.social.facebook.opengraphmine.facade.impl;

import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.social.facebook.opengraphmine.enums.CategoriesOperator;
import com.hybris.social.facebook.opengraphmine.enums.SocialContentPopulationRule;
import com.hybris.social.facebook.opengraphmine.facade.FacebookSuggestionFacade;
import com.hybris.social.facebook.opengraphmine.facade.data.AbstractFacebookSuggestedData;
import com.hybris.social.facebook.opengraphmine.facade.data.FacebookSuggestedProductData;
import com.hybris.social.facebook.opengraphmine.facade.data.FacebookSuggestedProductPriorityComparator;
import com.hybris.social.facebook.opengraphmine.facade.data.FacebookUserData;
import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;
import com.hybris.social.facebook.opengraphmine.service.FacebookAuthenticationService;
import com.hybris.social.facebook.opengraphmine.service.FacebookSuggestionService;


/**
 * @author rmcotton
 * @author javier
 * @author kbaranski
 * 
 *         FIXME - Far too much logic in this facade
 * 
 */
public class DefaultFacebookSuggestionFacade implements FacebookSuggestionFacade
{
	private ProductFacade productFacade;
	private ProductService productService;
	private FacebookSuggestionService facebookSuggestionService;
	private FacebookAuthenticationService facebookAuthenticationService;
	private FacebookSuggestedProductPriorityComparator productPriorityComparator;

	@Resource(name = "categoryService")
	CategoryService categoryService;

	private static final int MODE_PRODUCTS_LIKE = 0;
	private static final int MODE_PRODUCTS_PURCHASE = 1;
	private static final int MODE_PRODUCTS_COMMENT = 2;

	private static final int SCORE_PURCHASE = 3;
	private static final int SCORE_LIKE = 1;
	private static final int SCORE_COMMENT = 2;

	@Resource(name = "facebookUserDataConverter")
	Converter<FacebookUserModel, FacebookUserData> facebookUserDataConverter;
	@Resource(name = "facebookSuggestedProductDataConverter")
	Converter<ProductModel, FacebookSuggestedProductData> facebookSuggestedProductDataConverter;
	@Resource(name = "facebookBestCandidatePopulator")
	Populator<Object, FacebookSuggestedProductData> facebookBestCandidatePopulator;

	protected Set<FacebookUserModel> getApplicableUsers(final SocialContentPopulationRule populationRule)
	{

		// users to get likes, purchases and comments from
		Set<FacebookUserModel> setOfUsers = new LinkedHashSet<FacebookUserModel>();

		// gets the logged facebook user
		final FacebookUserModel loggedFacebookUser = getFacebookAuthenticationService().getCurrentFacebookUser();

		// only when there is fb user logged in
		if (loggedFacebookUser != null)
		{

			//  working only with logged user
			if (populationRule == SocialContentPopulationRule.LOGGEDUSERONLY)
			{
				// only connected user
				setOfUsers = Collections.singleton(loggedFacebookUser);
			}
			//  working only with friends or (logged user + friends)
			else
			{
				// gets all facebook users related to the facebookuser/ user
				setOfUsers = getFacebookAuthenticationService().getAllAssociatedFacebookUsers();
				if (populationRule == SocialContentPopulationRule.FRIENDSONLY)
				{
					// working only with friends
					setOfUsers.remove(loggedFacebookUser);
				}
			}
		}

		return setOfUsers;

	}

	@Override
	public List<FacebookSuggestedProductData> getSuggestions(final SocialContentPopulationRule populationRule,
			final Collection<CategoryModel> categories, final CategoriesOperator categoriesOperator, final boolean fullCategoryPath)
	{
		List<FacebookSuggestedProductData> facebookSuggestedProducts = getSuggestions(populationRule);
		if (categories != null && !categories.isEmpty())
		{
			final List<String> categoryCodes = new ArrayList<String>();
			for (final CategoryModel c : categories)
			{
				categoryCodes.add(c.getCode());
			}
			facebookSuggestedProducts = filterFacebookSuggestedProducts(facebookSuggestedProducts, categoryCodes,
					categoriesOperator, fullCategoryPath);
		}
		return facebookSuggestedProducts;
	}

	@Override
	public List<FacebookSuggestedProductData> getSuggestions(final SocialContentPopulationRule populationRule)
	{
		// creates the result list
		final List<FacebookSuggestedProductData> listResultFinal = new ArrayList<FacebookSuggestedProductData>();

		final Set<FacebookUserModel> setOfUsers = getApplicableUsers(populationRule);

		if (!setOfUsers.isEmpty())
		{
			// contains the map of product code and users associated
			final Map<String, FacebookSuggestedProductData> tmpMapResultFinal = new HashMap<String, FacebookSuggestedProductData>();

			// gets the products that users like
			final Map<ProductModel, Set<FacebookUserModel>> productsUsersLike = getFacebookSuggestionService().getLikedProducts(
					setOfUsers);

			completeResultList(productsUsersLike, tmpMapResultFinal, MODE_PRODUCTS_LIKE);

			// gets the products that friends have already purchased
			final Map<ProductModel, Set<FacebookUserModel>> productsUsersPurchased = getFacebookSuggestionService()
					.getProductsPurchasedBy(setOfUsers);

			completeResultList(productsUsersPurchased, tmpMapResultFinal, MODE_PRODUCTS_PURCHASE);

			// gets the products that friends have commented
			final Map<ProductModel, Set<FacebookUserModel>> productsUsersCommented = getFacebookSuggestionService()
					.getProductsCommentedBy(setOfUsers);

			completeResultList(productsUsersCommented, tmpMapResultFinal, MODE_PRODUCTS_COMMENT);

			// adds the values to the result list
			listResultFinal.addAll(tmpMapResultFinal.values());


			if (!listResultFinal.isEmpty())
			{
				for (final FacebookSuggestedProductData suggestion : listResultFinal)
				{
					facebookBestCandidatePopulator.populate(null, suggestion);
				}

				// only if in logged user only
				if (populationRule == SocialContentPopulationRule.LOGGEDUSERONLY
						|| populationRule == SocialContentPopulationRule.LOGGEDUSERANDFRIENDS)
				{
					// we do not want to show products that were only purchased by the logged user (and not liked and commented by him/ her or anybody) 
					removeProductPurchasedByLoggedUserIfNotNeeded(listResultFinal);
				}
				// sorts the list of products according to the comparator
				Collections.sort(listResultFinal, getProductPriorityComparator());
			}

		}

		return listResultFinal;
	}

	private void removeProductPurchasedByLoggedUserIfNotNeeded(final List<FacebookSuggestedProductData> listProducts)
	{
		for (final Iterator iterator = listProducts.iterator(); iterator.hasNext();)
		{
			final FacebookSuggestedProductData productInList = (FacebookSuggestedProductData) iterator.next();
			// if it is only purchased by logged user with no more other users
			if (isOnlyPurchasedByLoggedUser(productInList))
			{
				// removes the product from the list
				iterator.remove();
			}

		}
	}

	/**
	 * It indicates that the product was purchased by the logged user and
	 * 
	 * 1) he did not like or comment it and
	 * 
	 * 2) no other friend liked, commented or purchased it
	 * 
	 * This method is used when in facebookCarousel mode LoggedUserOnly: it makes no sense to show the product in the
	 * carousel in this situation
	 */
	boolean isOnlyPurchasedByLoggedUser(final AbstractFacebookSuggestedData suggestedData)
	{
		return CollectionUtils.isNotEmpty(suggestedData.getUsersThatPurchasedIt())
				&& suggestedData.getUsersThatPurchasedIt().contains(suggestedData.getLoggedUser())
				&& CollectionUtils.isEmpty(suggestedData.getUsersThatLikeIt())
				&& CollectionUtils.isEmpty(suggestedData.getUsersThatCommentedIt())
				&& CollectionUtils.isNotEmpty(suggestedData.getUsersThatPurchasedIt())
				&& CollectionUtils.size(suggestedData.getUsersThatPurchasedIt()) == 1;
	}

	private void completeResultList(final Map<ProductModel, Set<FacebookUserModel>> mapProductFacebookUserModel,
			final Map<String, FacebookSuggestedProductData> resultFinal, final int mode)
	{
		for (final Entry<ProductModel, Set<FacebookUserModel>> entry : mapProductFacebookUserModel.entrySet())
		{
			final ProductModel productModel = entry.getKey();
			final Set<FacebookUserModel> users = entry.getValue();

			// gets the code of the product
			final String productCode = productModel.getCode();
			// obtains the product data associated to the productcode
			FacebookSuggestedProductData facebookSuggestedProductData = resultFinal.get(productCode);

			// if the productdata had not already been introduced
			if (facebookSuggestedProductData == null)
			{
				facebookSuggestedProductData = facebookSuggestedProductDataConverter.convert(productModel);
			}

			// modifications will be done taking into account mode
			switch (mode)
			{
				case MODE_PRODUCTS_LIKE:
					facebookSuggestedProductData.getUsersThatLikeIt().addAll(convertAll(users));
					break;
				case MODE_PRODUCTS_PURCHASE:
					facebookSuggestedProductData.getUsersThatPurchasedIt().addAll(convertAll(users));
					break;
				case MODE_PRODUCTS_COMMENT:
					facebookSuggestedProductData.getUsersThatCommentedIt().addAll(convertAll(users));
					break;
				default:
					break;
			}

			if (CollectionUtils.isNotEmpty(facebookSuggestedProductData.getUsersThatCommentedIt())
					|| CollectionUtils.isNotEmpty(facebookSuggestedProductData.getUsersThatLikeIt())
					|| CollectionUtils.isNotEmpty(facebookSuggestedProductData.getUsersThatPurchasedIt()))
			{
				// puts it on the final map
				resultFinal.put(productCode, facebookSuggestedProductData);
			}
		}
	}

	protected Set<FacebookUserData> convertAll(final Set<FacebookUserModel> facebookUserModelSet)
	{
		final Set<FacebookUserData> facebookUserModelSetResult = new HashSet<FacebookUserData>();

		for (final FacebookUserModel facebookUserModel : facebookUserModelSet)
		{
			facebookUserModelSetResult.add(facebookUserDataConverter.convert(facebookUserModel));
		}

		return facebookUserModelSetResult;
	}

	/**
	 * @param facebookSuggestedProducts
	 * @param categoryCodes
	 * @param categoriesOperator
	 * @param fullCategoryPath
	 * @return Filtered list of products that should be presented as suggested. FIXME (kbaranski): Move this method to
	 *         facade
	 */
	private List<FacebookSuggestedProductData> filterFacebookSuggestedProducts(
			final List<FacebookSuggestedProductData> facebookSuggestedProducts, final Collection<String> categoryCodes,
			final CategoriesOperator categoriesOperator, final boolean fullCategoryPath)
	{
		final List<FacebookSuggestedProductData> filteredFacebookSuggestedProducts = new ArrayList<FacebookSuggestedProductData>();

		switch (categoriesOperator)
		{
			case AND:
				for (final FacebookSuggestedProductData fsp : facebookSuggestedProducts)
				{
					// get all product categories used to compare
					final HashSet<String> productCategoryCodes = getProductCategoryCodes(fsp, fullCategoryPath);

					// check if product categories contains ALL category codes from categories
					if (productCategoryCodes.containsAll(categoryCodes))
					{
						filteredFacebookSuggestedProducts.add(fsp);
					}
				}
				break;

			case OR:
				for (final FacebookSuggestedProductData fsp : facebookSuggestedProducts)
				{
					// get all product categories used to compare
					final HashSet<String> productCategoryCodes = getProductCategoryCodes(fsp, fullCategoryPath);

					// check if product categories contains at least one category code from categories
					boolean categoryFound = false;
					for (final String categoryCode : productCategoryCodes)
					{
						if (categoryCodes.contains(categoryCode))
						{
							categoryFound = true;
							break;
						}
					}

					if (categoryFound)
					{
						filteredFacebookSuggestedProducts.add(fsp);
					}
				}
				break;

			default:
				break;
		}

		return filteredFacebookSuggestedProducts;
	}

	/**
	 * @param fsp
	 * @param fullCategoryPath
	 * @return set of product categories
	 */
	private HashSet<String> getProductCategoryCodes(final FacebookSuggestedProductData fsp, final boolean fullCategoryPath)
	{
		final HashSet<String> categoryCodes = new HashSet<String>();

		if (fsp.getProduct().getCategories() != null)
		{
			if (fullCategoryPath)
			{
				for (final CategoryData c : fsp.getProduct().getCategories())
				{
					addCategoryTreeToSet(c, categoryCodes);
				}
			}
			else
			{
				for (final CategoryData c : fsp.getProduct().getCategories())
				{
					categoryCodes.add(c.getCode());
				}
			}
		}
		return categoryCodes;
	}

	/**
	 * @param c
	 * @param categories
	 */
	private void addCategoryTreeToSet(final CategoryData c, final Set<String> categories)
	{
		categories.add(c.getCode());
		final CategoryModel categoryModel = categoryService.getCategoryForCode(c.getCode());
		final Collection<CategoryModel> supercategories = categoryService.getAllSupercategoriesForCategory(categoryModel);
		if (supercategories != null && !supercategories.isEmpty())
		{
			for (final CategoryModel categoryModel2 : supercategories)
			{
				categories.add(categoryModel2.getCode());
			}
		}
	}

	@Override
	public FacebookSuggestedProductData getProductSocialData(final ProductData product,
			final SocialContentPopulationRule populationRule, final Collection<? extends ProductOption> options)
	{
		// gets the logged facebook user
		final FacebookUserModel loggedFacebookUser = getFacebookAuthenticationService().getCurrentFacebookUser();

		final Set<FacebookUserModel> setOfUsers = getApplicableUsers(populationRule);

		final ProductModel productModel = getProductService().getProductForCode(product.getCode());

		if (CollectionUtils.isNotEmpty(setOfUsers))
		{
			// converts from model to data
			final FacebookUserData loggedFacebookUserData = getFacebookUserData(loggedFacebookUser);

			final Set<FacebookUserModel> usersThatLike = getFacebookSuggestionService().getCommunityThatLike(productModel);
			final Map<String, FacebookSuggestedProductData> tmpMapResultFinal = new HashMap<String, FacebookSuggestedProductData>();
			completeResultList(loggedFacebookUserData, Collections.singletonMap(productModel, usersThatLike), tmpMapResultFinal,
					options, MODE_PRODUCTS_LIKE);

			final Set<FacebookUserModel> usersThatCommented = getFacebookSuggestionService().getCommunityThatCommented(productModel);
			completeResultList(loggedFacebookUserData, Collections.singletonMap(productModel, usersThatCommented),
					tmpMapResultFinal, options, MODE_PRODUCTS_COMMENT);

			final Set<FacebookUserModel> usersThatPurchased = getFacebookSuggestionService().getCommunityThatPurchased(productModel);
			completeResultList(loggedFacebookUserData, Collections.singletonMap(productModel, usersThatPurchased),
					tmpMapResultFinal, options, MODE_PRODUCTS_PURCHASE);

			if (tmpMapResultFinal.isEmpty())
			{
				return null;
			}

			final FacebookSuggestedProductData suggestion = tmpMapResultFinal.values().iterator().next();
			calculateBestCandidate(suggestion);

			return suggestion;

		}

		return null;
	}

	protected void calculateBestCandidate(final FacebookSuggestedProductData suggestion)
	{

		final Map<FacebookUserData, Integer> mapFacebookUserScore = new HashMap<FacebookUserData, Integer>();

		// users that purchased the product are the most important
		for (final FacebookUserData facebookUserData : suggestion.getUsersThatPurchasedIt())
		{
			mapFacebookUserScore.put(facebookUserData, Integer.valueOf(SCORE_PURCHASE));
		}

		// then users that Commented
		for (final FacebookUserData facebookUserData : suggestion.getUsersThatCommentedIt())
		{
			final Integer tmpPriorityFacebookUser = mapFacebookUserScore.get(facebookUserData);
			mapFacebookUserScore.put(
					facebookUserData,
					tmpPriorityFacebookUser == null ? Integer.valueOf(SCORE_COMMENT) : Integer.valueOf(tmpPriorityFacebookUser
							.intValue() + SCORE_COMMENT));
		}

		// then the ones that like the product
		for (final FacebookUserData facebookUserData : suggestion.getUsersThatLikeIt())
		{
			final Integer tmpPriorityFacebookUser = mapFacebookUserScore.get(facebookUserData);
			mapFacebookUserScore.put(
					facebookUserData,
					tmpPriorityFacebookUser == null ? Integer.valueOf(SCORE_LIKE) : Integer.valueOf(tmpPriorityFacebookUser.intValue()
							+ SCORE_LIKE));
		}

		// if there is any single friend that liked, purchased or commented the product
		// the logged user will not be taken into consideration to become a candidate
		if (mapFacebookUserScore.size() > 1)
		{
			// removes from the candidate map the logged ser
			mapFacebookUserScore.remove(suggestion.getLoggedUser());
		}

		int currentPriority = -1;
		FacebookUserData currentBestCandidate = null;

		for (final FacebookUserData tmpCandidate : mapFacebookUserScore.keySet())
		{
			final int tmpPriority = mapFacebookUserScore.get(tmpCandidate).intValue();
			// if more priority or same one but user with lower firtname (alphabetical)
			if (tmpPriority > currentPriority || (tmpPriority == currentPriority)
					&& tmpCandidate.getFirstname().compareTo(currentBestCandidate.getFirstname()) < 0)
			{
				currentPriority = tmpPriority;
				currentBestCandidate = tmpCandidate;
			}
		}
		suggestion.setBestCandidate(currentBestCandidate);
	}

	protected FacebookSuggestedProductData createFacebookSuggestedProductData(final ProductModel productModel,
			final FacebookUserData loggedUser, final Collection<? extends ProductOption> options)
	{
		// obtains the product data associated to the productmodel
		final ProductData productData = getProductFacade().getProductForOptions(productModel, (Collection<ProductOption>) options);
		// creates new wrapper of productdata
		final FacebookSuggestedProductData facebookSuggestedProductData = new FacebookSuggestedProductData();
		facebookSuggestedProductData.setProduct(productData);
		facebookSuggestedProductData.setLoggedUser(loggedUser);
		return facebookSuggestedProductData;
	}

	private void completeResultList(final FacebookUserData loggedUser,
			final Map<ProductModel, Set<FacebookUserModel>> mapProductFacebookUserModel,
			final Map<String, FacebookSuggestedProductData> resultFinal, final Collection<? extends ProductOption> options,
			final int mode)
	{

		for (final ProductModel productModel : mapProductFacebookUserModel.keySet())
		{
			// gets the code of the product
			final String productCode = productModel.getCode();
			// obtains the product data associated to the productcode
			FacebookSuggestedProductData facebookSuggestedProductData = resultFinal.get(productCode);

			// if the productdata had not already been introduced
			if (facebookSuggestedProductData == null)
			{
				facebookSuggestedProductData = createFacebookSuggestedProductData(productModel, loggedUser, options);


			}

			// modifications will be done taking into account mode
			switch (mode)
			{
				case MODE_PRODUCTS_LIKE:
					addUsersThatLikeIt(facebookSuggestedProductData.getUsersThatLikeIt(),
							getFacebookUsersData(mapProductFacebookUserModel.get(productModel)));
					break;
				case MODE_PRODUCTS_PURCHASE:
					addUsersThatPurchasedIt(facebookSuggestedProductData.getUsersThatPurchasedIt(),
							getFacebookUsersData(mapProductFacebookUserModel.get(productModel)));
					break;
				case MODE_PRODUCTS_COMMENT:
					addUsersThatCommentedIt(facebookSuggestedProductData.getUsersThatCommentedIt(),
							getFacebookUsersData(mapProductFacebookUserModel.get(productModel)));
					break;
				default:
					break;
			}


			if (CollectionUtils.isNotEmpty(facebookSuggestedProductData.getUsersThatCommentedIt())
					|| CollectionUtils.isNotEmpty(facebookSuggestedProductData.getUsersThatLikeIt())
					|| CollectionUtils.isNotEmpty(facebookSuggestedProductData.getUsersThatPurchasedIt()))
			{
				// puts it on the final map
				resultFinal.put(productCode, facebookSuggestedProductData);
			}

		}

	}

	protected FacebookUserData getFacebookUserData(final FacebookUserModel facebookUserModel)
	{
		final FacebookUserData facebookUserData = new FacebookUserData();
		facebookUserData.setProfileLink(facebookUserModel.getProfileLink());
		facebookUserData.setFirstname(facebookUserModel.getFirstname());
		facebookUserData.setLastname(facebookUserModel.getLastname());
		facebookUserData.setSmallProfilePictureURL(facebookUserModel.getSmallProfilePicture().getURL());

		return facebookUserData;
	}

	protected Set<FacebookUserData> getFacebookUsersData(final Set<FacebookUserModel> facebookUserModelSet)
	{
		final Set<FacebookUserData> facebookUserModelSetResult = new HashSet<FacebookUserData>();

		for (final FacebookUserModel facebookUserModel : facebookUserModelSet)
		{
			facebookUserModelSetResult.add(getFacebookUserData(facebookUserModel));
		}

		return facebookUserModelSetResult;
	}

	private static void addUsersThatLikeIt(final Set<FacebookUserData> existing, final Set<FacebookUserData> usersThatLikeIt)
	{
		if (usersThatLikeIt != null && !usersThatLikeIt.isEmpty())
		{
			existing.addAll(usersThatLikeIt);
		}

	}

	private static void addUsersThatPurchasedIt(final Set<FacebookUserData> existing,
			final Set<FacebookUserData> usersThatPurchasedIt)
	{
		if (usersThatPurchasedIt != null && !usersThatPurchasedIt.isEmpty())
		{
			existing.addAll(usersThatPurchasedIt);
		}

	}

	private static void addUsersThatCommentedIt(final Set<FacebookUserData> existing,
			final Set<FacebookUserData> usersThatCommentedIt)
	{
		if (usersThatCommentedIt != null && !usersThatCommentedIt.isEmpty())
		{
			existing.addAll(usersThatCommentedIt);
		}

	}

	@Required
	public void setFacebookSuggestionService(final FacebookSuggestionService facebookSuggestionService)
	{
		this.facebookSuggestionService = facebookSuggestionService;
	}

	public FacebookSuggestionService getFacebookSuggestionService()
	{
		return this.facebookSuggestionService;
	}

	/**
	 * @param productFacade
	 *           the productFacade to set
	 */
	@Required
	public void setProductFacade(final ProductFacade productFacade)
	{
		this.productFacade = productFacade;
	}

	/**
	 * @return the productFacade
	 */
	public ProductFacade getProductFacade()
	{
		return productFacade;
	}

	/**
	 * @return the facebookAuthenticationService
	 */
	public FacebookAuthenticationService getFacebookAuthenticationService()
	{
		return facebookAuthenticationService;
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
	 * @return the productPriorityComparator
	 */
	public FacebookSuggestedProductPriorityComparator getProductPriorityComparator()
	{
		return productPriorityComparator;
	}

	/**
	 * @param productPriorityComparator
	 *           the productPriorityComparator to set
	 */
	@Required
	public void setProductPriorityComparator(final FacebookSuggestedProductPriorityComparator productPriorityComparator)
	{
		this.productPriorityComparator = productPriorityComparator;
	}

	public ProductService getProductService()
	{
		return this.productService;
	}

	@Required
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}

}