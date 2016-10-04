/**
 * 
 */
package com.hybris.productsets.services.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.productsets.enums.ProductSetType;
import com.hybris.productsets.model.ProductSetModel;
import com.hybris.productsets.services.ProductSetService;
import com.hybris.productsets.services.dao.ProductSetDao;


/**
 * @author dominik.strzyzewski
 * 
 */
public class DefaultProductSetService implements ProductSetService
{

	private ProductSetDao productSetDao;


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.productsets.services.ProductSetService#findProductSetsWithProduct(de.hybris.platform.core.model.product
	 * .ProductModel)
	 */
	@Override
	public List<ProductSetModel> getProductSetsWithProduct(final ProductModel product)
	{
		return getProductSetDao().findSetsHavingAnyProduct(getProductsToMatch(product));
	}

	protected List<ProductModel> getProductsToMatch(final ProductModel product)
	{
		final List<ProductModel> products = new LinkedList<ProductModel>();
		recursiveAddToBase(product, products);
		return products;
	}

	protected void recursiveAddToBase(final ProductModel product, final List<ProductModel> list)
	{
		list.add(product);
		if (product instanceof VariantProductModel)
		{
			final ProductModel base = ((VariantProductModel) product).getBaseProduct();
			recursiveAddToBase(base, list);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.productsets.services.ProductSetService#findProductSetsWithProductAndType(de.hybris.platform.core.model
	 * .product.ProductModel, com.hybris.productsets.enums.ProductSetType)
	 */
	@Override
	public List<ProductSetModel> getProductSetsWithProductAndType(final ProductModel product, final ProductSetType productSetType)
	{
		return getProductSetDao().findSetsHavingAnyProductAndType(getProductsToMatch(product), productSetType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.productsets.services.ProductSetService#getDisplayedProducts(com.hybris.productsets.model.ProductSetModel
	 * , de.hybris.platform.core.model.product.ProductModel)
	 */
	@Override
	public List<ProductModel> getDisplayedProducts(final ProductSetModel productSet, final ProductModel currentProduct)
	{
		final List<ProductModel> prods = new LinkedList<ProductModel>();
		for (final ProductModel product : productSet.getProducts())
		{
			if (!(currentProduct != null && getProductsToMatch(currentProduct).contains(product)))
			{
				prods.add(product);
			}
		}
		return prods;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.productsets.services.ProductSetService#findProductSetByCode(java.lang.String)
	 */
	@Override
	public ProductSetModel getProductSetByCode(final String code)
	{
		return getProductSetDao().findSetByCode(code);
	}

	public ProductSetDao getProductSetDao()
	{
		return productSetDao;
	}

	@Required
	public void setProductSetDao(final ProductSetDao productSetDao)
	{
		this.productSetDao = productSetDao;
	}



}
