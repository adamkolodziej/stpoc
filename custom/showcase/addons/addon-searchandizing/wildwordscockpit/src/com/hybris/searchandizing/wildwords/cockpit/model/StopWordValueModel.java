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

import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrStopWordModel;



/**
 * @author piotr.kalinowski
 * 
 */
public class StopWordValueModel extends AbstractWildWordsValueModel
{
	private String stopWord;

	public StopWordValueModel()
	{
		this(false);
	}

	public StopWordValueModel(final boolean initialize)
	{
		setInitialized(initialize);
	}

	public String getStopWord()
	{
		return stopWord;
	}

	public void setStopWord(final String stopWord)
	{
		this.stopWord = stopWord;
		markAsChanged();
	}

	@Override
	public void save(final SolrFacetSearchConfigModel config, final LanguageModel language)
	{
		if (getModel() != null)
		{
			final SolrStopWordModel model = (SolrStopWordModel) getModel();

			if (getStopWord().equals(model.getStopWord()))
			{
				model.setStopWord(getStopWord());
			}
		}
		else
		{
			final SolrStopWordModel model = new SolrStopWordModel();
			model.setStopWord(getStopWord());
			model.setLanguage(language);
			model.setFacetSearchConfig(config);
			setModel(model);
		}
	}

	@Override
	public boolean isValid()
	{
		if (getStopWord() != null && !getStopWord().trim().isEmpty())
		{
			return true;
		}
		return false;
	}
}
