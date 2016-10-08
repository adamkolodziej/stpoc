/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 * 
 */
package com.hybris.cms.productpages.component.renderer;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import com.hybris.addon.common.renderer.GenericAddOnCMSComponentRenderer;
import com.hybris.cms.productpages.model.SimpleProductImageComponentModel;


/**
 * @author rmcotton
 * 
 */
public class SimpleProductImageComponentRenderer<C extends SimpleProductImageComponentModel> extends
		GenericAddOnCMSComponentRenderer<C>
{
	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final C component)
	{
		final Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(SimpleProductImageComponentModel.SHOWGALLERY, component.getShowGallery());
		variables.put(SimpleProductImageComponentModel.GALLERYPOSITION, component.getGalleryPosition().getCode());
		return variables;
	}
}
