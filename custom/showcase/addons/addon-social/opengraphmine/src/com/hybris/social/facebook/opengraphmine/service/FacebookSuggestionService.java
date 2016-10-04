/**
 * 
 */
package com.hybris.social.facebook.opengraphmine.service;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.Map;
import java.util.Set;

import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;


/**
 * Suggest products based on likes, interests and groups in the users profile.
 * 
 * @author rmcotton
 * @author javier
 */
public interface FacebookSuggestionService
{
	/**
	 * This method receives a list of facebook users and returns a map with products and the set of those facebook passed
	 * by parameter that like them
	 * 
	 * @param facebookUsers
	 *           The users from which the products need to be retrieved
	 * @return A map of productmodel and the set of users that liked them
	 */
	Map<ProductModel, Set<FacebookUserModel>> getLikedProducts(final Set<FacebookUserModel> facebookUsers);

	/**
	 * This method receives a list of facebook users and returns a map with products and the set of those facebook passed
	 * by parameter that have purchased the product
	 * 
	 * @param facebookUsers
	 *           The users from which the products need to be retrieved
	 * @return A map of productmodel and the set of users that purchased them
	 */
	Map<ProductModel, Set<FacebookUserModel>> getProductsPurchasedBy(final Set<FacebookUserModel> facebookUsers);

	/**
	 * This method receives a list of facebook users and returns a map with products and the set of those facebook passed
	 * by parameter that have commented the product
	 * 
	 * @param facebookUsers
	 *           The users from which the comments need to be retrieved
	 * @return A map of productmodel and the set of users that commented them
	 */
	Map<ProductModel, Set<FacebookUserModel>> getProductsCommentedBy(final Set<FacebookUserModel> facebookUsers);

	/**
	 * Returns the current user and any of their friends that liked the given product.
	 */
	Set<FacebookUserModel> getCommunityThatLike(ProductModel productModel);

	/**
	 * Returns the current user and any of their friends that purchased the given product
	 */
	Set<FacebookUserModel> getCommunityThatPurchased(ProductModel productModel);

	/**
	 * Returns the current user and any of their friends that commented on the given product
	 */
	Set<FacebookUserModel> getCommunityThatCommented(ProductModel productModel);
}
