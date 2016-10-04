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
package com.hybris.commercesearch.searchandizing.cockpit.model.editor;

import de.hybris.platform.cockpit.model.editor.EditorListener;
import de.hybris.platform.cockpit.model.editor.impl.DefaultSelectUIEditor;
import de.hybris.platform.cockpit.services.config.AvailableValuesProvider;
import de.hybris.platform.cockpit.session.UISessionUtils;

import java.util.Map;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.HtmlBasedComponent;


/**
 * @author rmcotton
 * 
 */
public class GroupingPropertyEditor extends DefaultSelectUIEditor
{
	@Override
	public HtmlBasedComponent createViewComponent(final Object initialValue, final Map<String, ? extends Object> parameters,
			final EditorListener listener)
	{
		final AvailableValuesProvider provider = (AvailableValuesProvider) SpringUtil
				.getBean("groupingPropertyAvailableValuesProvider");
		setAvailableValues(UISessionUtils.getCurrentSession().getTypeService().wrapItems(provider.getAvailableValues(null)));
		return super.createViewComponent(initialValue, parameters, listener);
	}
}
