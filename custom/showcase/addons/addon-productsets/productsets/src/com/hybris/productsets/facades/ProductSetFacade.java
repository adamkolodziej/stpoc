/**
 * 
 */
package com.hybris.productsets.facades;

import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hybris.productsets.enums.ProductSetType;
import com.hybris.productsets.facades.data.ProductSetCartItemData;
import com.hybris.productsets.facades.data.ProductSetData;
import com.hybris.productsets.facades.data.VariantSelectOptionData;


/**
 * @author dominik.strzyzewski
 * 
 */
public interface ProductSetFacade
{
	List<ProductSetData> getProductSetsForProduct(final String productCode, final Collection<ProductOption> options);

	List<ProductSetData> getProductSetsForProductAndType(final String productCode, ProductSetType productSetType,
			final Collection<ProductOption> options);

	ProductSetData getProductSetByCode(final String productSetCode, final Collection<ProductOption> options);

	ProductSetData getFilteredProductSetByCode(final String productCode, final String productSetCode,
			final Collection<ProductOption> options);

	List<CartModificationData> addSetToCart(Map<String, Integer> quantities, final Collection<ProductOption> options);

	CartModificationData addItemToCart(final String code, final long quantity);

	List<ProductSetCartItemData> getProductSetCartItemEntries(final List<CartModificationData> modifications);

	Set<VariantSelectOptionData> getSelectVariantsForProduct(final ProductData product);

	Map<String, Set<VariantSelectOptionData>> getSelectVariantsForProducts(final Set<ProductData> products);

}
