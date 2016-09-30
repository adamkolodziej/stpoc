/**
 * 
 */
package com.hybris.productlists.service;

import java.util.List;

import com.hybris.productlists.enums.ProductListType;
import com.hybris.productlists.model.ProductListModel;


/**
 * @author simonhuggins
 * 
 */
public interface ProductListsSessionService
{

	/**
	 * Returns a Product List Model corresponding to a specific guid
	 * 
	 * @param productList
	 *           The product list to get the hidden items for
	 * @return A list of items which were hidden or removed from the list
	 */
	ProductListModel getProductListByGuid(String guid);

	/**
	 * Returns a list of a product lists for the currently logged in user
	 * 
	 * @param guid
	 *           defined guid for a specific product list
	 * @return A specified product list for the given guid
	 */
	List<ProductListModel> getProductListsForLoggedInUser();

	/**
	 * Returns a list of a product lists for the currently logged in user for the specific product list type
	 * 
	 * @param guid
	 *           defined guid for a specific product list
	 * @return A specified product list for the given guid
	 */
	List<ProductListModel> getProductListsForLoggedInUser(ProductListType productListTypeFilter);

	/**
	 * Returns <code>true</code> if the current session already holds a list of product lists, <code>false</code>
	 * otherwise. Please use this instead of {@link #getSessionProductLists()} if you like to avoid unnecessary
	 * auto-creation of the sessions productlist.
	 * 
	 * @see #getSessionProductLists()
	 */
	boolean hasSessionProductLists();

	/**
	 * @param object
	 */
	void setSessionProductLists(List<ProductListModel> productlists);

	void addSessionProductList(ProductListModel list);


	List<ProductListModel> getSessionProductLists();

	List<ProductListModel> getSessionProductLists(ProductListType productListTypeFilter);


}
