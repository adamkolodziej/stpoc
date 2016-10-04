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
package com.hybris.searchandizing.wildwords.cockpit.callbackevent;

import de.hybris.platform.cmscockpit.components.liveedit.LiveEditView;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.addon.cockpits.components.liveedit.CallbackEventHandler;
import com.hybris.searchandizing.wildwords.cockpit.wizards.EditWildWordsWizard;


/**
 * @author rmcotton
 * 
 */
public class WildWordsCallbackEventHandler implements CallbackEventHandler<LiveEditView>
{
	@Resource(name = "commonI18NService")
	private CommonI18NService commonI18NService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.addon.cockpits.components.liveedit.CallbackEventHandler#getEventId()
	 */
	@Override
	public String getEventId()
	{
		return "editWildWords";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.addon.cockpits.components.liveedit.CallbackEventHandler#onCallbackEvent(de.hybris.platform.cmscockpit
	 * .components.liveedit.LiveEditView, java.lang.String[])
	 */
	@Override
	public void onCallbackEvent(final LiveEditView view, final String[] passedAttributes) throws Exception
	{
		final LanguageModel language = getCommonI18NService().getLanguage(passedAttributes[1]);
		new EditWildWordsWizard(language, view.getModel()).show(view);
	}

	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(final CommonI18NService commonI18nService)
	{
		this.commonI18NService = commonI18nService;
	}

}
