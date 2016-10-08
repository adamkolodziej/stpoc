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

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;



/**
 * @author piotr.kalinowski
 * 
 */
public interface WildWordsValueModel
{
	boolean isChanged();

	void setChanged(final boolean isChanged);

	ItemModel getModel();

	void setModel(ItemModel model);

	boolean isInitialized();

	void setInitialized(boolean isInitialized);

	void save(SolrFacetSearchConfigModel config, LanguageModel language);

	boolean isValid();
}
