/**
 * 
 */
package com.hybris.productlists.daos;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Comparator;
import java.util.List;

import com.hybris.productlists.enums.ProductListType;
import com.hybris.productlists.model.ProductListEntryModel;
import com.hybris.productlists.model.ProductListModel;


/**
 * The DAO for the ProductLists. This is only concerned with custom read operations.
 * 
 * Any CRUD operations will be handles by the {@link ModelService}
 * 
 * @author craig.wayman
 * 
 */
public interface ProductListsDAO
{

	/**
	 * Returns the product list for the site being viewed
	 * 
	 * @param baseSite
	 *           The site being viewed by the user
	 * @param baseStore
	 *           Fallback incase site cannot be resolved
	 * @return The product list matching the current site being browsed.
	 */
	List<ProductListModel> getProductList(final BaseSiteModel baseSite, final BaseStoreModel baseStore);

	/**
	 * Returns the product list for the site being in the order that items were added to the product list
	 * 
	 * @param comparator
	 *           The comparator being used to sort the entries
	 * @param baseSite
	 *           The site being viewed by the user
	 * @param baseStore
	 *           Fallback incase site cannot be resolved
	 * @return The product list matching the current site being browsed in the order the products were added to the list
	 */
	List<ProductListModel> getProductListBy(Comparator comparator, BaseSiteModel baseSite, BaseStoreModel baseStore);

	/**
	 * Returns a list of the hidden items from the product list. This is all items with a priority of less than zero
	 * 
	 * @param productList
	 *           The product list to get the hidden items for
	 * @return A list of items which were hidden or removed from the list
	 */
	List<ProductListEntryModel> getHiddenProductListEntries(ProductListModel productList);

	/**
	 * Returns a list of a product list using its guid - to be used for Cookie and encoded link
	 * 
	 * @param guid
	 *           defined guid for a specific product list
	 * @return A specified product list for the given guid
	 */
	ProductListModel getProductListByGuid(String guid);

	/**
	 * Returns a list of a product lists using its base site, base store, and user / principal
	 * 
	 * @param guid
	 *           defined guid for a specific product list
	 * @return A specified product list for the given guid
	 */
	List<ProductListModel> getProductListForOwner(PrincipalModel principal, BaseSiteModel baseSite, BaseStoreModel baseStore,
			ProductListType productListTypeFilter);

}
