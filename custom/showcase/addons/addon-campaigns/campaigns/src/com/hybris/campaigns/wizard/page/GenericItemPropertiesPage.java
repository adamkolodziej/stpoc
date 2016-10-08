/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.hybris.campaigns.wizard.page;

import de.hybris.platform.cockpit.model.meta.ObjectType;
import de.hybris.platform.cockpit.session.UISession;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.cockpit.wizards.generic.GenericItemMandatoryPage;
import de.hybris.platform.core.model.ItemModel;

import org.zkoss.zk.ui.Component;


/**
 * @author miroslaw.szot
 * 
 */
public class GenericItemPropertiesPage extends GenericItemMandatoryPage
{

	@Override
	public Component createRepresentationItself()
	{
		if (getWizard().getItem() == null)
		{
			final ObjectType currentType = getWizard().getCurrentType();
			final UISession session = UISessionUtils.getCurrentSession();
			final ItemModel model = session.getModelService().create(currentType.getCode());
			getWizard().setItem(session.getTypeService().wrapItem(model));
		}
		return super.createRepresentationItself();
	}


}
