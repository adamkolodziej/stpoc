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
package com.hybris.searchandizing.wildwords.cockpit.model;

import de.hybris.platform.core.model.ItemModel;

import java.util.ArrayList;
import java.util.List;




/**
 * @author piotr.kalinowski
 * 
 */
public abstract class AbstractWildWordsListModel implements WildWordsListModel
{
	private final List<ItemModel> modelsToRemove = new ArrayList<ItemModel>();

	@Override
	public boolean isChanged()
	{
		if (!modelsToRemove.isEmpty())
		{
			return true;
		}

		for (final AbstractWildWordsValueModel value : getValues())
		{
			if (value.isChanged())
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public List<ItemModel> getModelsToRemove()
	{
		return modelsToRemove;
	}

	@Override
	public void addModelToRemove(final ItemModel model)
	{
		this.modelsToRemove.add(model);
	}

}
