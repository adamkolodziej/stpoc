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

import de.hybris.platform.solrfacetsearch.model.config.SolrSynonymConfigModel;

import java.util.ArrayList;
import java.util.List;


/**
 * @author piotr.kalinowski
 * 
 */
public class SynonymListModel extends AbstractWildWordsListModel
{
	private final List<SynonymValueModel> synonyms = new ArrayList<SynonymValueModel>();


	@SuppressWarnings("PMD")
	public SynonymListModel(final List<SolrSynonymConfigModel> synonymList)
	{
		for (final SolrSynonymConfigModel synonym : synonymList)
		{
			addSynonym(synonym);
		}
	}

	public void addSynonym(final SolrSynonymConfigModel synonym)
	{
		final SynonymValueModel synonymValue = new SynonymValueModel();
		synonymValue.setSynonymFrom(synonym.getSynonymFrom());
		synonymValue.setSynonymTo(synonym.getSynonymTo());
		synonymValue.setModel(synonym);
		addSynonym(synonymValue);
	}

	public void addSynonym(final SynonymValueModel synonymValue)
	{
		synonymValue.setInitialized(true);
		synonyms.add(synonymValue);
	}

	public boolean removeSynonym(final SynonymValueModel synonymValue)
	{
		if (synonyms.contains(synonymValue))
		{
			if (synonymValue.getModel() != null)
			{
				addModelToRemove(synonymValue.getModel());
			}
			synonyms.remove(synonymValue);
			return true;
		}
		return false;
	}

	public boolean containsValue(final SynonymValueModel value)
	{
		return synonyms.contains(value);
	}

	@Override
	public List<SynonymValueModel> getValues()
	{
		return synonyms;
	}
}
