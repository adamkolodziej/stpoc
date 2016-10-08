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
import de.hybris.platform.solrfacetsearch.model.config.SolrSynonymConfigModel;



/**
 * @author piotr.kalinowski
 * 
 */
public class SynonymValueModel extends AbstractWildWordsValueModel
{
	private String synonymFrom;
	private String synonymTo;


	public SynonymValueModel()
	{
		this(false);
	}

	public SynonymValueModel(final boolean initialize)
	{
		setInitialized(initialize);
	}

	public String getSynonymFrom()
	{
		return synonymFrom;
	}

	public String getSynonymTo()
	{
		return synonymTo;
	}

	public void setSynonymFrom(final String synonymFrom)
	{
		this.synonymFrom = synonymFrom;
		markAsChanged();
	}

	public void setSynonymTo(final String synonymTo)
	{
		this.synonymTo = synonymTo;
		markAsChanged();
	}

	@Override
	public void save(final SolrFacetSearchConfigModel config, final LanguageModel language)
	{
		if (getModel() != null)
		{
			final SolrSynonymConfigModel model = (SolrSynonymConfigModel) getModel();

			if (!getSynonymFrom().equals(model.getSynonymFrom()))
			{
				model.setSynonymFrom(getSynonymFrom());
			}
			if (!getSynonymTo().equals(model.getSynonymTo()))
			{
				model.setSynonymTo(getSynonymTo());
			}
		}
		else
		{
			final SolrSynonymConfigModel model = new SolrSynonymConfigModel();
			model.setSynonymFrom(getSynonymFrom());
			model.setSynonymTo(getSynonymTo());
			model.setLanguage(language);
			model.setFacetSearchConfig(config);
			setModel(model);
		}
	}

	@Override
	public boolean isValid()
	{
		if (getSynonymFrom() != null && !getSynonymFrom().trim().isEmpty() && getSynonymTo() != null
				&& !getSynonymTo().trim().isEmpty())
		{
			return true;
		}
		return false;
	}
}
