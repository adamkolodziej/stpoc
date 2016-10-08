///*
// * [y] hybris Platform
// *
// * Copyright (c) 2000-2013 hybris AG
// * All rights reserved.
// *
// * This software is the confidential and proprietary information of hybris
// * ("Confidential Information"). You shall not disclose such Confidential
// * Information and shall use it only in accordance with the terms of the
// * license agreement you entered into with hybris.
// *
// *
// */
//package com.hybris.addon.cockpits.session.liveedit.impl;
//
//import de.hybris.platform.cmscockpit.events.impl.CmsUrlChangeEvent;
//import de.hybris.platform.cockpit.components.contentbrowser.AbstractContentBrowser;
//import de.hybris.platform.servicelayer.model.ModelService;
//import de.hybris.platform.yacceleratorcockpits.cmscockpit.session.impl.DefaultLiveEditBrowserModel;
//
//import org.zkoss.spring.SpringUtil;
//
//import com.hybris.addon.cockpits.cms.events.impl.AddOnCmsUrlChangeEvent;
//
//
///**
// * @author rmcotton
// *
// */
//public class AddOnLiveEditBrowserModel extends DefaultLiveEditBrowserModel
//{
//	private AddOnFrontendAttributes addonOnFrontAttributes;
//	private ModelService modelService;
//
//	@Override
//	public AbstractContentBrowser createViewComponent()
//	{
//		return new AddOnLiveEditContentBrowser();
//	}
//
//	/**
//	 * Sets all frontend attributes transferred to WCMSCockpit. </p>
//	 */
//	@Override
//	public void setFrontendAttributes(final CmsUrlChangeEvent cmsUrlChangeEvent)
//	{
//		super.setFrontendAttributes(cmsUrlChangeEvent);
//		// fix to ensure on page refresh we dont build a url from the page since there is
//		// not a 1-1 mapping to cms page and url and the services will prepare a url based on the category restriction or preview category.
//		getViewModel().setPage(null);
//		if (cmsUrlChangeEvent instanceof AddOnCmsUrlChangeEvent)
//		{
//			this.addonOnFrontAttributes = ((AddOnCmsUrlChangeEvent) cmsUrlChangeEvent).getAddOnFrontendAttributes();
//		}
//		else
//		{
//			this.addonOnFrontAttributes = null;
//		}
//	}
//
//	public AddOnFrontendAttributes getAddOnFrontendAttributes()
//	{
//		return this.addonOnFrontAttributes;
//	}
//
//	/**
//	 * Retrieves Model Service
//	 *
//	 * @return Model Service
//	 */
//	@Override
//	protected ModelService getModelService()
//	{
//		if (this.modelService == null)
//		{
//			this.modelService = (ModelService) SpringUtil.getBean("modelService");
//		}
//		return this.modelService;
//	}
//
//}
