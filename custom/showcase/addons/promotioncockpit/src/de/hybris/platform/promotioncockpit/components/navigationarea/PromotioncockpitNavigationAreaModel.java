/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2011 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package de.hybris.platform.promotioncockpit.components.navigationarea;

import de.hybris.platform.cockpit.components.navigationarea.DefaultNavigationAreaModel;
import de.hybris.platform.cockpit.session.impl.AbstractUINavigationArea;

import de.hybris.platform.promotioncockpit.session.impl.PromotioncockpitNavigationArea;


/**
 * Promotioncockpit navigation area model.
 */
public class PromotioncockpitNavigationAreaModel extends DefaultNavigationAreaModel
{
	public PromotioncockpitNavigationAreaModel()
	{
		super();
	}

	public PromotioncockpitNavigationAreaModel(final AbstractUINavigationArea area)
	{
		super(area);
	}

	@Override
	public PromotioncockpitNavigationArea getNavigationArea()
	{
		return (PromotioncockpitNavigationArea) super.getNavigationArea();
	}
}
