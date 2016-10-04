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

import de.hybris.platform.solrfacetsearch.model.redirect.SolrFacetSearchKeywordRedirectModel;

import java.util.ArrayList;
import java.util.List;


/**
 * @author piotr.kalinowski
 * 
 */
public class KeywordRedirectListModel extends AbstractWildWordsListModel
{
	private final List<KeywordRedirectValueModel> keywordRedirects = new ArrayList<KeywordRedirectValueModel>();

	@SuppressWarnings("PMD")
	public KeywordRedirectListModel(final List<SolrFacetSearchKeywordRedirectModel> keywordRedirectList)
	{
		for (final SolrFacetSearchKeywordRedirectModel keywordRedirect : keywordRedirectList)
		{
			addKeywordRedirect(keywordRedirect);
		}
	}

	public void addKeywordRedirect(final SolrFacetSearchKeywordRedirectModel keywordRedirect)
	{
		final KeywordRedirectValueModel keywordRedirectValue = new KeywordRedirectValueModel();
		keywordRedirectValue.setKeyword(keywordRedirect.getKeyword());
		keywordRedirectValue.setMatchType(keywordRedirect.getMatchType());
		keywordRedirectValue.setRedirect(keywordRedirect.getRedirect());
		keywordRedirectValue.setIgnoreCase(keywordRedirect.getIgnoreCase());
		keywordRedirectValue.setModel(keywordRedirect);
		addKeywordRedirect(keywordRedirectValue);
	}

	public void addKeywordRedirect(final KeywordRedirectValueModel keywordRedirectValue)
	{
		keywordRedirectValue.setInitialized(true);
		keywordRedirects.add(keywordRedirectValue);
	}

	public boolean removeKeywordRedirect(final KeywordRedirectValueModel keywordRedirectValue)
	{
		if (keywordRedirects.contains(keywordRedirectValue))
		{
			if (keywordRedirectValue.getModel() != null)
			{
				addModelToRemove(keywordRedirectValue.getModel());
			}
			keywordRedirects.remove(keywordRedirectValue);
			return true;
		}
		return false;
	}

	public boolean containsValue(final KeywordRedirectValueModel value)
	{
		return keywordRedirects.contains(value);
	}

	@Override
	public List<KeywordRedirectValueModel> getValues()
	{
		return keywordRedirects;
	}

}
