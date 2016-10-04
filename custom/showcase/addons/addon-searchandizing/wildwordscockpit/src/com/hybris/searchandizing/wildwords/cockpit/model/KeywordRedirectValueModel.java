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
import de.hybris.platform.solrfacetsearch.enums.KeywordRedirectMatchType;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.solrfacetsearch.model.redirect.SolrAbstractKeywordRedirectModel;
import de.hybris.platform.solrfacetsearch.model.redirect.SolrFacetSearchKeywordRedirectModel;


/**
 * @author piotr.kalinowski
 */
public class KeywordRedirectValueModel extends AbstractWildWordsValueModel
{
	private String keyword;
	private KeywordRedirectMatchType matchType;
	private SolrAbstractKeywordRedirectModel redirect;
	private Boolean ignoreCase;

	public KeywordRedirectValueModel()
	{
		this(false);
	}

	public KeywordRedirectValueModel(final boolean initialize)
	{
		setInitialized(initialize);
	}

	public String getKeyword()
	{
		return keyword;
	}

	public void setKeyword(final String keyword)
	{
		this.keyword = keyword;
		markAsChanged();
	}

	public Boolean isIgnoreCase()
	{
		return ignoreCase;
	}

	public void setIgnoreCase(final Boolean ignoreCase)
	{
		this.ignoreCase = ignoreCase;
		markAsChanged();
	}

	public KeywordRedirectMatchType getMatchType()
	{
		return matchType;
	}

	public void setMatchType(final KeywordRedirectMatchType matchType)
	{
		this.matchType = matchType;
		markAsChanged();
	}

	public SolrAbstractKeywordRedirectModel getRedirect()
	{
		return redirect;
	}

	public void setRedirect(final SolrAbstractKeywordRedirectModel redirect)
	{
		this.redirect = redirect;
		markAsChanged();
	}

	@Override
	public void save(final SolrFacetSearchConfigModel config, final LanguageModel language)
	{
		if (getModel() != null)
		{
			final SolrFacetSearchKeywordRedirectModel model = (SolrFacetSearchKeywordRedirectModel) getModel();
			if (!getKeyword().equals(model.getKeyword()))
			{
				model.setKeyword(getKeyword());
			}
			if (!getMatchType().equals(model.getMatchType()))
			{
				model.setMatchType(getMatchType());
			}
			if (!getRedirect().equals(model.getRedirect()))
			{
				model.setRedirect(getRedirect());
			}
			if (!isIgnoreCase().equals(model.getIgnoreCase()))
			{
				model.setIgnoreCase(isIgnoreCase());
			}
		}
		else
		{
			final SolrFacetSearchKeywordRedirectModel model = new SolrFacetSearchKeywordRedirectModel();
			model.setKeyword(getKeyword());
			model.setMatchType(getMatchType());
			model.setRedirect(getRedirect());
			model.setIgnoreCase(isIgnoreCase());
			model.setLanguage(language);
			model.setFacetSearchConfig(config);
			setModel(model);
		}
	}

	@Override
	public boolean isValid()
	{
		if (getKeyword() != null && !getKeyword().trim().isEmpty() && getRedirect() != null && getMatchType() != null)
		{
			return true;
		}
		return false;
	}

}
