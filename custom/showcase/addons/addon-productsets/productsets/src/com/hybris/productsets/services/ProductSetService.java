/**
 * 
 */
package com.hybris.productsets.services;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

import com.hybris.productsets.enums.ProductSetType;
import com.hybris.productsets.model.ProductSetModel;


/**
 * @author dominik.strzyzewski
 * 
 */
public interface ProductSetService
{
	List<ProductSetModel> getProductSetsWithProduct(ProductModel product);

	List<ProductSetModel> getProductSetsWithProductAndType(ProductModel product, ProductSetType productSetType);

	List<ProductModel> getDisplayedProducts(ProductSetModel productSet, ProductModel currentProduct);

	ProductSetModel getProductSetByCode(String code);
}