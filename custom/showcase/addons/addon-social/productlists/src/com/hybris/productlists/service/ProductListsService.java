/**
 * 
 */
package com.hybris.productlists.service;

import java.util.List;

import com.hybris.productlists.model.ProductListEntryModel;
import com.hybris.productlists.model.ProductListModel;


/**
 * The service for the product lists addon. This service provides the methods to create, update and perform user
 * interface actions on the Product List.
 * 
 * @author craig.wayman
 */
public interface ProductListsService
{

	/**
	 * Creates a product List
	 * 
	 * @param productList
	 *           The product list to be saved by the service
	 * @return The saved product list with its primary keys and guids populated
	 */
	ProductListModel createProductList(ProductListModel productList);

	/**
	 * The method to add an entry to a list.
	 * 
	 * If there is already an existing entry of this type on the list then they will be merged.
	 * 
	 * New products will have a lower priority than entries previous added to the list.
	 * 
	 * @param productList
	 *           The list to add the entry too.
	 * @param entry
	 *           The entry to add
	 * @return The newly updated list with the entry added
	 */
	ProductListModel addProduct(ProductListModel productList, ProductListEntryModel entry);

	/**
	 * The method to update the desired amount of an entry on a list.
	 * 
	 * This will overwrite the existing desired amount for that entry
	 * 
	 * @param productList
	 *           The list to update the entry on.
	 * @param entry
	 *           The entry to update the desired amount on
	 */
	void updateProductDesiredAmount(ProductListModel productList, ProductListEntryModel entry, Integer desiredAmount);

	/**
	 * The method to update the received amount of an entry on a list.
	 * 
	 * This will overwrite the existing received amount for that entry
	 * 
	 * @param productList
	 *           The list to update the entry on.
	 * @param entry
	 *           The entry to update the received amount on
	 */
	void updateProductReceivedAmount(ProductListModel productList, ProductListEntryModel entry, Integer desiredAmount);


	/**
	 * The method to allow the re-ording of components
	 * 
	 * The 'before' param will be moved beneith the 'entry' in priority. All entries which come after the entry will then
	 * follow the 'before' entry.
	 * 
	 * @param productList
	 *           The list that holds the items to be re-ordered
	 * @param before
	 *           The entry to move before, i.e. have the next priority from
	 * @param entry
	 *           The entry which will be immediately above the 'before' parameter.
	 * @return The newly updated list in the correct order
	 */
	void updateProductOrder(ProductListModel productList, ProductListEntryModel before, ProductListEntryModel entry);

	/**
	 * Returns the product list for the site being viewed
	 * 
	 * @return The product list matching the current site being browsed.
	 */
	List<ProductListModel> getProductList();


	/**
	 * Returns a list of the hidden items from the product list. This is all items with a priority of less than zero
	 * 
	 * @param productList
	 *           The product list to get the hidden items for
	 * @return A list of items which were hidden or removed from the list
	 */
	List<ProductListEntryModel> getHiddenProductListEntries(ProductListModel productList);

	/**
	 * Returns a boolean as to whether the productlist contains this product
	 * 
	 * @param productList
	 *           the list to search
	 * @param code
	 *           the code of the product to search upon
	 * @return true if the list has the product, false otherwise
	 */
	Boolean hasProduct(ProductListModel productList, String code);

	/**
	 * Returns the product entry from the list based on the code
	 * 
	 * @param productList
	 *           the list to search
	 * @param code
	 *           the code of the product to search upon
	 * @return the product entry model if the product exists, null otherwise
	 */
	ProductListEntryModel getProductListEntryFromList(ProductListModel productList, String productCode);

	/**
	 * Updates the product list model on the db and refreshes it.
	 * 
	 * @param productListModel
	 *           the model with the updated values
	 */
	void updateProductListModel(ProductListModel productListModel);


	/**
	 * Updates the specified product list entry model on the db and refreshes it.
	 */
	void updateProductListEntryModel(ProductListEntryModel productListEntryModel);

}
