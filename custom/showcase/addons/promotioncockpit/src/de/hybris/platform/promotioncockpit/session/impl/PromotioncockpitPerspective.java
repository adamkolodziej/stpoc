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
package de.hybris.platform.promotioncockpit.session.impl;

import de.hybris.platform.cockpit.session.BrowserModel;
import de.hybris.platform.cockpit.session.SearchBrowserModel;
import de.hybris.platform.cockpit.session.impl.BaseUICockpitPerspective;

import org.apache.log4j.Logger;


/**
 * @author michal.milinski
 */
public class PromotioncockpitPerspective extends BaseUICockpitPerspective
{
	private static final Logger log = Logger.getLogger(PromotioncockpitPerspective.class);

	String createItemType;

	/**
	 * @return the createItemType
	 */
	public String getCreateItemType()
	{
		return createItemType;
	}

	/**
	 * @param createItemType
	 *           the createItemType to set
	 */
	public void setCreateItemType(final String createItemType)
	{
		this.createItemType = createItemType;
	}

	@Override
	public void onShow()
	{
		if (createItemType != null)
		{
			getEditorArea().setManagingPerspective(this);

			createTemplateList(createItemType);

			final BrowserModel b = getBrowserArea().getFocusedBrowser();

			if (b instanceof SearchBrowserModel)
			{
				final SearchBrowserModel searchBrowser = (SearchBrowserModel) b;

				if (searchBrowser.getResult() == null)
				{
					searchBrowser.updateItems(0);
				}
			}

			try
			{
				if (getActiveItem() != null)
				{
					this.activateItemInEditor(getActiveItem());
				}
			}
			catch (final Exception e)
			{
				log.warn("Error occurred when trying to load active item (Reason: '" + e.getMessage() + "').");
			}
		}
	}

}
