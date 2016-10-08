package com.hybris.solrrollup.services.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.solrrollup.services.SolrRollupService;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.type.ComposedTypeModel;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.variants.model.VariantProductModel;

public class DefaultSolrRollupService implements SolrRollupService {

	private TypeService typeService;
	
	@Override
	public ProductModel getRollupProduct(ProductModel leaf) {
		return findNearestRollupProduct(leaf);
	}
	
	public ProductModel getRollupProduct(ProductModel leaf, ProductModel fallback) {
		final ProductModel p = findNearestRollupProduct(leaf);
		return (p != null) ? p : fallback;
	}

	
	private ProductModel findNearestRollupProduct(ProductModel product)
	{
		if (!(product instanceof VariantProductModel))
		{
			return null;
		}
		else if (((VariantProductModel)product).getBaseProduct() == null)
		{
			return null;
		}
		
		return  recursiveFindRollupProduct(product, new HashMap<ComposedTypeModel, ProductModel>(), new LinkedList<ComposedTypeModel>());
		
	}
	
	protected ProductModel recursiveFindRollupProduct(ProductModel product, Map<ComposedTypeModel,ProductModel> productsByComposedType, List<ComposedTypeModel> rollupTypes)
	{
		final ComposedTypeModel currComposedType = getTypeService().getComposedTypeForClass(product.getClass());
		productsByComposedType.put(currComposedType, product);

		if (product.getRollupType() != null )
		{
			if (!rollupTypes.contains(product.getRollupType()))
			{
				rollupTypes.add(product.getRollupType());
			}
			
		
		}
		for (ComposedTypeModel rollupType : rollupTypes)
		{
			// Note only exact match not Assignable, since for example ApparelSize can sub-type ApparelStyle and if we wanted to do a style rollup, size would still match
			if (productsByComposedType.containsKey(rollupType))
			{
				return productsByComposedType.get(rollupType);
			}
		}
		
		if (!(product instanceof VariantProductModel))
		{
			return null;
		}
		else if (((VariantProductModel)product).getBaseProduct() == null)
		{
			return product;
		}
		else
		{
			return recursiveFindRollupProduct(((VariantProductModel)product).getBaseProduct(), productsByComposedType, rollupTypes);
		}
		
	}
	
	public ProductModel getRootProduct(ProductModel product)
	{
		if (!(product instanceof VariantProductModel))
		{
			return product;
		}
		else if (((VariantProductModel)product).getBaseProduct() == null)
		{
			return product;
		}
		else
		{
			return getRootProduct(((VariantProductModel)product).getBaseProduct());
		}
	}
	
	@Required
	public void setTypeService(TypeService typeService)
	{
		this.typeService = typeService;
	}
	
	public TypeService getTypeService()
	{
		return this.typeService;
	}
	
}
