package com.hybris.solrrollup.cockpit.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import de.hybris.platform.cockpit.model.meta.PropertyDescriptor;
import de.hybris.platform.cockpit.services.config.AvailableValuesProvider;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.type.ComposedTypeModel;
import de.hybris.platform.servicelayer.type.TypeService;

public class ProductRollupTypeAvailableValuesProvider implements AvailableValuesProvider {
	
	private TypeService typeService;
	
	@Override
	public	List<? extends Object> getAvailableValues(PropertyDescriptor propertyDescriptor) {
		final ComposedTypeModel composedType = getTypeService().getComposedTypeForClass(ProductModel.class);
		final List<ComposedTypeModel> types = new ArrayList<ComposedTypeModel>();
		types.add(composedType);
		types.addAll(composedType.getAllSubTypes());
		return types;
	}

	@Required
	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}
	
	public TypeService getTypeService()
	{
		return this.typeService;
	}
}
