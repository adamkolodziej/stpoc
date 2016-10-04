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
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.cockpit.session.impl.CreateContext;
import de.hybris.platform.cockpit.session.impl.TemplateListEntry;
import de.hybris.platform.cockpit.wizards.Wizard;
import de.hybris.platform.cockpit.wizards.generic.GenericTypeSelectorPage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.fest.util.Collections;


/**
 * SHOW-1549 New Campaign Wizard
 * 
 * @author miroslaw.szot
 */
public class CampaignTypeSelectorPage extends GenericTypeSelectorPage
{
	private String currentTypeString;

	public CampaignTypeSelectorPage()
	{
		this(null);
	}

	public CampaignTypeSelectorPage(final CreateContext createContext)
	{
		this(null, null, createContext);
	}

	public CampaignTypeSelectorPage(final String pageTitle, final Wizard wizard, final CreateContext createContext)
	{
		super(pageTitle, wizard, createContext);
	}

	@Override
	protected List<TemplateListEntry> getTemplateListEntry()
	{
		final List<TemplateListEntry> templateListEntry = super.getTemplateListEntry();
		if (Collections.isEmpty(templateListEntry) && StringUtils.isNotBlank(currentTypeString))
		{
			final ObjectType currentType = UISessionUtils.getCurrentSession().getTypeService().getObjectType(currentTypeString);
			getWizard().setCurrentType(currentType);
			return super.getTemplateListEntry();
		}
		return templateListEntry;
	}

	@Override
	protected List<TemplateListEntry> filterAllowedTypeEntries(final List<TemplateListEntry> entries)
	{
		final List filteredTypes = new ArrayList();

		for (final TemplateListEntry objectTypeEntry : entries)
		{
			if (objectTypeEntry.getTemplate().isAbstract())
			{
				continue;
			}
			filteredTypes.add(objectTypeEntry);
		}

		return filteredTypes;
	}


	public String getCurrentTypeString()
	{
		return currentTypeString;
	}

	public void setCurrentTypeString(final String currentTypeString)
	{
		this.currentTypeString = currentTypeString;
	}

}
