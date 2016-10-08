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



/**
 * @author piotr.kalinowski
 * 
 */
public abstract class AbstractWildWordsValueModel implements WildWordsValueModel
{
	private boolean changed = false;
	private boolean initialized = false;
	private ItemModel model;

	@Override
	public boolean isChanged()
	{
		return changed;
	}

	@Override
	public void setChanged(final boolean changed)
	{
		this.changed = changed;
	}

	@Override
	public void setModel(final ItemModel model)
	{
		this.model = model;
	}

	@Override
	public ItemModel getModel()
	{
		return model;
	}

	@Override
	public boolean isInitialized()
	{
		return initialized;
	}

	@Override
	public void setInitialized(final boolean initialized)
	{
		this.initialized = initialized;
	}

	protected void markAsChanged()
	{
		if (isInitialized() && !isChanged())
		{
			setChanged(true);
		}
	}
}
