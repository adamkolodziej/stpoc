package com.hybris.solrrollup.services;

import de.hybris.platform.core.model.product.ProductModel;

public interface SolrRollupService {
	
	/**
	 * If the Product hierarchy contains a nominated rollup product type and the type of product exists in the hierarchy this will return that product, otherwise null.
	 */
	ProductModel getRollupProduct(ProductModel leaf);
	
	ProductModel getRollupProduct(ProductModel leaf, ProductModel fallback);
	
	ProductModel getRootProduct(ProductModel product);


}
