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

import de.hybris.platform.solrfacetsearch.model.config.SolrStopWordModel;

import java.util.ArrayList;
import java.util.List;


/**
 * @author piotr.kalinowski
 * 
 */
public class StopWordListModel extends AbstractWildWordsListModel
{
	private final List<StopWordValueModel> stopWords = new ArrayList<StopWordValueModel>();

	@SuppressWarnings("PMD")
	public StopWordListModel(final List<SolrStopWordModel> stopWordList)
	{
		for (final SolrStopWordModel stopWord : stopWordList)
		{
			addStopWord(stopWord);
		}
	}

	public void addStopWord(final SolrStopWordModel stopWord)
	{
		final StopWordValueModel stopWordValue = new StopWordValueModel();
		stopWordValue.setStopWord(stopWord.getStopWord());
		stopWordValue.setModel(stopWord);
		addStopWord(stopWordValue);
	}

	public void addStopWord(final StopWordValueModel stopWordValue)
	{
		stopWordValue.setInitialized(true);
		stopWords.add(stopWordValue);
	}

	public boolean removeStopWord(final StopWordValueModel stopWordValue)
	{
		if (this.stopWords.contains(stopWordValue))
		{
			if (stopWordValue.getModel() != null)
			{
				addModelToRemove(stopWordValue.getModel());
			}
			stopWords.remove(stopWordValue);
			return true;
		}
		return false;
	}

	public boolean containsValue(final StopWordValueModel value)
	{
		return stopWords.contains(value);
	}

	@Override
	public List<StopWordValueModel> getValues()
	{
		return stopWords;
	}
}
