/**
 * 
 */
package com.hybris.productlists.facades;

import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;

import java.util.Collection;
import java.util.List;

import com.hybris.productlists.data.ListToCartModificationData;
import com.hybris.productlists.data.ProductListData;
import com.hybris.productlists.data.ProductListEntryData;
import com.hybris.productlists.enums.ProductListType;


/** @author craig.wayman */
public interface ProductListsFacade
{
	boolean hasSessionProductLists();

	List<ProductListData> getAllSessionProductLists(Collection<ProductOption> options);

	List<ProductListData> getSessionProductLists(ProductListType listType, Collection<ProductOption> options);

	List<ProductListData> getOrCreateSessionProductLists(ProductListType listType, Collection<ProductOption> options);

	ProductListData getProductList(final String productListGuid, final Collection<ProductOption> options);

	ProductListData restoreSavedProductList(String productListGuid, Collection<ProductOption> options);

	String createProductList(ProductListData productListData);

	ProductListEntryData getListEntry(String productListGuid, String productCode, Collection<ProductOption> options);

	void addToProductList(String productListGuid, String productCode, Integer quantity);

	void updateProductList(String productListGuid, String productCode, Integer quantity);

	void updateProductListName(String guid, String name);

	boolean updateNotes(String productListGuid, String productCode, String notes);

	void updateProductOrder(String productListGuid, String beforeProductCode, String productToMoveCode);

	ListToCartModificationData moveToCart(String productListGuid, String productCode, Integer quantity)
			throws CommerceCartModificationException;


	void onLogin();
}
