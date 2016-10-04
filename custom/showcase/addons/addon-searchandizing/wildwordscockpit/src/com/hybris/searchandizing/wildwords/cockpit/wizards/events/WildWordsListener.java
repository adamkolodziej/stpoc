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
package com.hybris.searchandizing.wildwords.cockpit.wizards.events;

import java.util.EventListener;


/**
 * @author piotr.kalinowski
 * 
 */
public interface WildWordsListener extends EventListener
{
	void onLoad();

	void onChange();

	void onSave();

	void onError(String message);
}
