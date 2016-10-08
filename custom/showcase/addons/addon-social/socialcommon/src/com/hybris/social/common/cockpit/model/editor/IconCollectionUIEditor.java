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
package com.hybris.social.common.cockpit.model.editor;

import de.hybris.platform.cockpit.model.editor.EditorListener;
import de.hybris.platform.cockpit.model.editor.UIEditor;
import de.hybris.platform.cockpit.model.editor.impl.GenericCollectionUIEditor;
import de.hybris.platform.cockpit.model.meta.DefaultPropertyEditorDescriptor;
import de.hybris.platform.cockpit.model.meta.PropertyEditorDescriptor;

import java.util.Map;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.HtmlBasedComponent;



/**
 * Created with IntelliJ IDEA. User: Zdary Date: 22/03/13 Time: 12:17 To change this template use File | Settings | File
 * Templates.
 */
public class IconCollectionUIEditor extends GenericCollectionUIEditor
{
	private static final Logger LOG = Logger.getLogger(IconCollectionUIEditor.class);
	protected DefaultPropertyEditorDescriptor propertyEditorDescriptor = new DefaultPropertyEditorDescriptor()
	{
		@Override
		public UIEditor createUIEditor(final String mode)
		{
			return new IconEnumUIEditor();
		}
	};

	@Override
	public HtmlBasedComponent createViewComponent(final Object initialValue, final Map<String, ? extends Object> parameters,
			final EditorListener listener)
	{
		final HtmlBasedComponent htmlBasedComponent = super.createViewComponent(initialValue, parameters, listener);
		return htmlBasedComponent;
	}

	@Override
	public PropertyEditorDescriptor getSingleValueEditorDescriptor()
	{
		return this.propertyEditorDescriptor;
	}





}
