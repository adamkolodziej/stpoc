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
package com.hybris.searchandizing.wildwords.cockpit.wizards;

import de.hybris.platform.cmscockpit.components.liveedit.LiveEditViewModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;

import com.hybris.searchandizing.wildwords.cockpit.model.KeywordRedirectListModel;
import com.hybris.searchandizing.wildwords.cockpit.model.KeywordRedirectValueModel;
import com.hybris.searchandizing.wildwords.cockpit.model.StopWordListModel;
import com.hybris.searchandizing.wildwords.cockpit.model.StopWordValueModel;
import com.hybris.searchandizing.wildwords.cockpit.model.SynonymListModel;
import com.hybris.searchandizing.wildwords.cockpit.model.SynonymValueModel;
import com.hybris.searchandizing.wildwords.cockpit.wizards.events.WildWordsListener;
import com.hybris.searchandizing.wildwords.service.WildWordsAdminService;


/**
 * @author piotr.kalinowski
 * 
 */
public interface EditWildWordsViewModel
{
	void initModel(final LiveEditViewModel liveEditViewModel, final LanguageModel language);

	void registerListener(final WildWordsListener listener);

	void fireInitEvents();

	void fireChangeEvents();

	void fireSaveEvents();

	void fireErrorEvents(final String message);

	void reset();

	boolean save();

	void addNewValue(KeywordRedirectValueModel value);

	void addNewValue(SynonymValueModel value);

	void addNewValue(StopWordValueModel value);

	boolean isChanged();

	KeywordRedirectListModel getKeywordRedirectListModel();

	SynonymListModel getSynonymListModel();

	StopWordListModel getStopWordListModel();

	FacetSearchConfig getFacetSearchConfig();

	WildWordsAdminService getWildWordsAdminService();

	void startFullIndexJob();
}
