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
package com.hybris.social.common.cms.renderers;

import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import com.hybris.social.common.model.IFrameListComponentModel;


/**
 * 
 * @author sebastian.mekal
 * 
 * @param <C>
 */
public class IFrameListComponentRenderer<C extends IFrameListComponentModel> extends DefaultAddOnCMSComponentRenderer<C>
{

	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final C component)
	{
		final Map<String, Object> model = new HashMap<String, Object>();
		model.put(IFrameListComponentModel.TITLE, component.getTitle());
		model.put(IFrameListComponentModel.ITEMS, component.getItems());
		return model;
	}

}
