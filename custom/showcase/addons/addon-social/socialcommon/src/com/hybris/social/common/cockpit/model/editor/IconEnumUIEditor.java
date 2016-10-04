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

import de.hybris.platform.cockpit.model.editor.impl.DefaultEnumUIEditor;

import org.apache.log4j.Logger;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;


/**
 * Created with IntelliJ IDEA. User: Zdary Date: 14/03/13 Time: 14:37 To change this template use File | Settings | File
 * Templates.
 */
public class IconEnumUIEditor extends DefaultEnumUIEditor
{
	private static final Logger LOG = Logger.getLogger(IconEnumUIEditor.class);

	@Override
	protected void addEnumToCombo(final Object value, final Combobox box)
	{
		super.addEnumToCombo(value, box);
		final Comboitem comboitem = (Comboitem) box.getLastChild();
		if (value != null)
		{
			comboitem.setImage("cmscockpit/images/socialcommon_icon_" + value + ".png");
		}
	}

}
