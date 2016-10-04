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

import de.hybris.platform.cockpit.wizards.generic.DecisionPage;
import de.hybris.platform.cockpit.wizards.generic.GenericItemWizard;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;


/**
 * SHOW-1549 New Campaign Wizard
 * 
 * @author miroslaw.szot
 */
public class CampaignDecisionPage extends DecisionPage
{
	protected static final String DEFAULT_ELEMENT_IMAGE = "/cockpit/images/defaultWizardNode.gif";

	private Map<String, String> decisionsMap;

	public CampaignDecisionPage()
	{
		this(null);
	}

	public CampaignDecisionPage(final String pageTitle)
	{
		this(pageTitle, null);
	}

	public CampaignDecisionPage(final String pageTitle, final GenericItemWizard wizard)
	{
		super(pageTitle, wizard);
	}

	@Override
	public Component createRepresentationItself()
	{
		if (CollectionUtils.isEmpty(decisions))
		{
			createDecisions();
		}
		return super.createRepresentationItself();
	}

	private void createDecisions()
	{
		for (final Entry<String, String> entry : decisionsMap.entrySet())
		{
			final String decisionLabel = entry.getKey();
			final String nextWizardPageId = entry.getValue();

			decisions.add(new Decision(nextWizardPageId, Labels.getLabel(decisionLabel), DEFAULT_ELEMENT_IMAGE));
		}
	}

	public Map<String, String> getDecisionsMap()
	{
		return decisionsMap;
	}

	public void setDecisionsMap(final Map<String, String> decisionsMap)
	{
		this.decisionsMap = decisionsMap;
	}

}
