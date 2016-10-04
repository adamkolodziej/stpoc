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
package com.hybris.cms.turbocmspages.cockpit.liveedit.captionhandler;


import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cmscockpit.components.liveedit.AbstractLiveEditCaptionButtonHandler;
import de.hybris.platform.cmscockpit.session.impl.FrontendAttributes;
import de.hybris.platform.cmscockpit.session.impl.LiveEditBrowserArea;
import de.hybris.platform.cmscockpit.session.impl.LiveEditBrowserModel;
import de.hybris.platform.cmscockpit.session.impl.LiveEditContentBrowser;

import org.springframework.beans.factory.annotation.Required;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Hbox;

import com.hybris.cms.turbocmspages.cockpit.liveedit.service.TurboCmsPagesLiveEditWorkflowService;
import com.hybris.cms.turbocmspages.cockpit.liveedit.wizards.LiveEditCategoryLandingPageWizardInvoker;


/**
 * @author rmcotton
 *
 */
public class MakeLandingPageLiveEditCaptionButtonHandler extends AbstractLiveEditCaptionButtonHandler
{

	private TurboCmsPagesLiveEditWorkflowService turboCmsPagesLiveEditWorkflowService;
	private LiveEditCategoryLandingPageWizardInvoker liveEditCategoryLandingPageWizardInvoker;


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.cmscockpit.components.liveedit.LiveEditCaptionButtonHandler#createButton(de.hybris.platform
	 * .cmscockpit.session.impl.LiveEditBrowserArea, de.hybris.platform.cmscockpit.session.impl.LiveEditBrowserModel,
	 * de.hybris.platform.cmscockpit.session.impl.LiveEditContentBrowser, org.zkoss.zul.Hbox)
	 */
	@Override
	public void createButton(final LiveEditBrowserArea area, final LiveEditBrowserModel browserModel,
			final LiveEditContentBrowser contentBrowser, final Hbox buttonContainer)
	{

		final FrontendAttributes frontendAttributes = browserModel.getFrontendAttributes();

		if (frontendAttributes != null
				&& frontendAttributes.getSearchState() != null
				&& getTurboCmsPagesLiveEditWorkflowService().canShowMakeLandingPageButton(browserModel.getRelatedPagePk(),
						frontendAttributes.getSearchState()))
		{
			createRightCaptionButton(Labels.getLabel("browser.createLandingPage"), "btnliveeditcontent_makelandingpage",
					buttonContainer, new org.zkoss.zk.ui.event.EventListener()
					{
						@Override
						public void onEvent(final Event event) throws Exception //NOPMD: ZK Specific
						{
							final CategoryModel category = getTurboCmsPagesLiveEditWorkflowService().getCategoryForPage(
									frontendAttributes.getSearchState());
							getLiveEditCategoryLandingPageWizardInvoker()
									.start(buttonContainer, category, browserModel.getPreviewData());
						}
					});
		}
	}


	public TurboCmsPagesLiveEditWorkflowService getTurboCmsPagesLiveEditWorkflowService()
	{
		return turboCmsPagesLiveEditWorkflowService;
	}


	@Required
	public void setTurboCmsPagesLiveEditWorkflowService(
			final TurboCmsPagesLiveEditWorkflowService turboCmsPagesLiveEditWorkflowService)
	{
		this.turboCmsPagesLiveEditWorkflowService = turboCmsPagesLiveEditWorkflowService;
	}


	public LiveEditCategoryLandingPageWizardInvoker getLiveEditCategoryLandingPageWizardInvoker()
	{
		return liveEditCategoryLandingPageWizardInvoker;
	}

	@Required
	public void setLiveEditCategoryLandingPageWizardInvoker(
			final LiveEditCategoryLandingPageWizardInvoker liveEditCategoryLandingPageWizardInvoker)
	{
		this.liveEditCategoryLandingPageWizardInvoker = liveEditCategoryLandingPageWizardInvoker;
	}


}
