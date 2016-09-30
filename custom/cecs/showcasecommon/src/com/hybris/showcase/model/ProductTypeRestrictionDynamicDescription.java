/**
 *
 */
package com.hybris.showcase.model;

import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import de.hybris.platform.servicelayer.type.TypeService;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.model.restrictions.CMSProductTypeRestrictionModel;


public class ProductTypeRestrictionDynamicDescription implements DynamicAttributeHandler<String, CMSProductTypeRestrictionModel>
{
	private TypeService typeService;

	protected TypeService getTypeService()
	{
		return typeService;
	}

	@Required
	public void setTypeService(final TypeService typeService)
	{
		this.typeService = typeService;
	}

	@Override
	public String get(final CMSProductTypeRestrictionModel model)
	{
		return "Page only applies on product type: " + model.getProductType().toString();
	}

	@Override
	public void set(final CMSProductTypeRestrictionModel model, final String value)
	{
		throw new UnsupportedOperationException();
	}

}