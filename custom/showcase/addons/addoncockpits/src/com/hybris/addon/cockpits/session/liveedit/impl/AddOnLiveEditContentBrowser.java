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
//import de.hybris.platform.catalog.model.CatalogVersionModel;
//import de.hybris.platform.cms2.model.preview.PreviewDataModel;
//import de.hybris.platform.cms2.model.site.CMSSiteModel;
//import de.hybris.platform.cms2.servicelayer.services.admin.CMSAdminSiteService;
//import de.hybris.platform.cmscockpit.components.liveedit.AbstractLiveEditCaptionButtonHandler;
//import de.hybris.platform.cmscockpit.enums.LiveEditVariant;
//import de.hybris.platform.cmscockpit.session.impl.LiveEditBrowserArea;
//import de.hybris.platform.cmscockpit.session.impl.LiveEditBrowserModel;
//import de.hybris.platform.cmscockpit.session.impl.LiveEditContentBrowser;
//import de.hybris.platform.cockpit.components.contentbrowser.AbstractBrowserComponent;
//import de.hybris.platform.cockpit.components.contentbrowser.AbstractContentBrowser;
//import de.hybris.platform.cockpit.session.AdvancedBrowserModel;
//import de.hybris.platform.cockpit.session.BrowserModel;
//import de.hybris.platform.cockpit.session.UIBrowserArea;
//import de.hybris.platform.cockpit.util.UITools;
//import de.hybris.platform.servicelayer.model.ModelService;
//import de.hybris.platform.yacceleratorcockpits.cmscockpit.session.impl.DefaultLiveEditBrowserArea;
//import de.hybris.platform.yacceleratorcockpits.cmscockpit.session.impl.DefaultLiveEditBrowserModel;
//import de.hybris.platform.yacceleratorcockpits.cmscockpit.session.impl.DefaultLiveEditContentBrowser;
//import de.hybris.platform.yacceleratorcockpits.components.liveedit.DefaultLiveEditView;
//
//import java.util.List;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Required;
//import org.zkoss.spring.SpringUtil;
//import org.zkoss.util.resource.Labels;
//import org.zkoss.zk.ui.Executions;
//import org.zkoss.zk.ui.event.Event;
//import org.zkoss.zul.Hbox;
//
//import com.hybris.addon.cockpits.components.liveedit.AddOnLiveEditView;
//import com.hybris.addon.cockpits.components.liveedit.LiveEditCaptionButtonHandler;
//
//
///**
// * @author rmcotton
// *
// */
//public class AddOnLiveEditContentBrowser extends DefaultLiveEditContentBrowser
//{
//
//	@Override
//	protected AbstractBrowserComponent createMainAreaComponent()
//	{
//		return new AddOnLiveEditMainAreaComponent(getModel(), this);
//	}
//
//	@Override
//	protected AbstractBrowserComponent createCaptionComponent()
//	{
//		return new AddOnLiveEditCaptionComponent(getModel(), this);
//	}
//
//	public class AddOnLiveEditMainAreaComponent extends DefaultLiveEditMainAreaComponent
//	{
//
//		/**
//		 * @param model
//		 * @param contentBrowser
//		 */
//		public AddOnLiveEditMainAreaComponent(final AdvancedBrowserModel model, final AbstractContentBrowser contentBrowser)
//		{
//			super(model, contentBrowser);
//		}
//
//		@Override
//		protected DefaultLiveEditView newDefaultLiveEditView(final DefaultLiveEditBrowserModel liveEditBrowserModel)
//		{
//			return new AddOnLiveEditView(liveEditBrowserModel.getViewModel(), createWelcomePanel());
//		}
//
//	}
//
//	public class AddOnLiveEditCaptionComponent extends DefaultLiveEditCaptionComponent
//	{
//		public AddOnLiveEditCaptionComponent(final AdvancedBrowserModel model, final AbstractContentBrowser contentBrowser)
//		{
//			super(model, contentBrowser);
//		}
//
//		@Override
//		protected void createAdditionalRightCaptionComponents(final Hbox hbox)
//		{
//			UITools.detachChildren(hbox);
//			final BrowserModel browserModel = getModel();
//			if (browserModel instanceof AddOnLiveEditBrowserModel)
//			{
//				final AddOnLiveEditBrowserModel liveEditBrowserModel = (AddOnLiveEditBrowserModel) getModel();
//				if (liveEditBrowserModel.getActiaveCatalogVersion() != null)
//				{
//					final UIBrowserArea area = getModel().getArea();
//					if (area instanceof DefaultLiveEditBrowserArea)
//					{
//
//						final List<LiveEditCaptionButtonHandler> handlers = (List<LiveEditCaptionButtonHandler>) SpringUtil.getBean(
//								"liveEditCaptionButtonHandlers", List.class);
//
//						for (final LiveEditCaptionButtonHandler handler : handlers)
//						{
//							handler.createButton((DefaultLiveEditBrowserArea) area, liveEditBrowserModel,
//									AddOnLiveEditContentBrowser.this, hbox);
//						}
//					}
//				}
//			}
//		}
//	}
//
//	public static class ActivateLiveEditCaptionButtonHandler extends AbstractLiveEditCaptionButtonHandler
//	{
//
//		/*
//		 * (non-Javadoc)
//		 *
//		 * @see com.hybris.addon.cockpits.components.liveedit.LiveEditCaptionButtonHandler#createButton(de.hybris.platform
//		 * .yacceleratorcockpits.cmscockpit.session.impl.DefaultLiveEditBrowserArea)
//		 */
//		@Override
//		public void createButton(final LiveEditBrowserArea area, final LiveEditBrowserModel browserModel,
//				final LiveEditContentBrowser contentBrowser, final Hbox buttonContainer)
//		{
//			final PreviewDataModel currentPreviewData = browserModel.getViewModel().getCurrentPreviewData();
//			final LiveEditVariant liveEditVariant = currentPreviewData != null ? currentPreviewData.getLiveEditVariant() : null;
//
//			final boolean liveEditModeEnabled = (area.isLiveEditModeEnabled() && liveEditVariant == LiveEditVariant.QUICKEDIT)
//					|| (area.isLiveEditModeEnabled() && liveEditVariant == null);
//			// Live edit button
//			createRightCaptionButton(Labels.getLabel(liveEditModeEnabled ? "browser.liveEditOn" : "browser.liveEditOff"),
//					(liveEditModeEnabled ? "btnliveeditcontent_quickedit_active" : "btnliveeditcontent_quickedit"), buttonContainer,
//					new org.zkoss.zk.ui.event.EventListener()
//					{
//						@Override
//						public void onEvent(final Event event) throws Exception //NOPMD: ZK Specific
//						{
//							handleLiveEditEvent(area, browserModel);
//						}
//
//					});
//		}
//
//		protected static void handleLiveEditEvent(final LiveEditBrowserArea area, final LiveEditBrowserModel browserModel)
//		{
//			final PreviewDataModel previewData = browserModel.getViewModel().getCurrentPreviewData();
//
//			final LiveEditVariant previousLiveEditType = previewData.getLiveEditVariant();
//
//			previewData.setLiveEditVariant(LiveEditVariant.QUICKEDIT);
//
//			final ModelService modelService = (ModelService) SpringUtil.getBean("modelService");
//			modelService.save(previewData);
//
//			if (previousLiveEditType != previewData.getLiveEditVariant())
//			{
//				area.fireModeChange(true);
//			}
//			else
//			{
//				area.fireModeChange();
//			}
//		}
//	}
//
//	public static class PreviewContextLiveEditCaptionButtonHandler extends AbstractLiveEditCaptionButtonHandler
//	{
//
//		/*
//		 * (non-Javadoc)
//		 *
//		 * @see
//		 * com.hybris.addon.cockpits.components.liveedit.LiveEditCaptionButtonHandler#createButton(de.hybris.platform.
//		 * yacceleratorcockpits.cmscockpit.session.impl.DefaultLiveEditBrowserArea, org.zkoss.zul.Hbox)
//		 */
//		@Override
//		public void createButton(final LiveEditBrowserArea area, final LiveEditBrowserModel browserModel,
//				final LiveEditContentBrowser contentBrowser, final Hbox buttonContainer)
//		{
//			final boolean isPreviewDataActive = browserModel.isPreviewDataVisible();
//			// Preview context button
//			createRightCaptionButton(Labels.getLabel("browser.previewData"),
//					(isPreviewDataActive ? "btnliveeditcontent_previewcontext_active" : "btnliveeditcontent_previewcontext"),
//					buttonContainer, new org.zkoss.zk.ui.event.EventListener()
//					{
//						@Override
//						public void onEvent(final Event event) throws Exception //NOPMD: ZK Specific
//						{
//							browserModel.fireTogglePreviewDataMode(contentBrowser);
//						}
//					});
//
//		}
//
//	}
//
//	public static class PageEditLiveEditCaptionButtonHandler extends AbstractLiveEditCaptionButtonHandler
//	{
//		private static final String PERSP_TAG = "persp";
//		private static final String EVENTS_TAG = "events";
//		private static final String PAGE_VIEW_PERSPECTIVE_ID = "cmscockpit.perspective.catalog";
//		private static final String CMS_NAVIGATION_EVENT = "pageviewnavigation";
//		private static final String CMS_PNAV_SITE = "pnav-site";
//		private static final String CMS_PNAV_CATALOG = "pnav-catalog";
//		private static final String CMS_PNAV_PAGE = "pnav-page";
//
//		private CMSAdminSiteService cmsAdminSiteService;
//
//
//		/*
//		 * (non-Javadoc)
//		 *
//		 * @see
//		 * com.hybris.addon.cockpits.components.liveedit.LiveEditCaptionButtonHandler#createButton(de.hybris.platform.
//		 * yacceleratorcockpits.cmscockpit.session.impl.DefaultLiveEditBrowserArea, org.zkoss.zul.Hbox)
//		 */
//		@Override
//		public void createButton(final LiveEditBrowserArea area, final LiveEditBrowserModel browserModel,
//				final LiveEditContentBrowser contentBrowser, final Hbox buttonContainer)
//		{
//			final CMSSiteModel activeSite = getCmsAdminSiteService().getActiveSite();
//			final CatalogVersionModel activeCatalogVersion = getCmsAdminSiteService().getActiveCatalogVersion();
//			if (activeSite != null && activeCatalogVersion != null)
//			{
//
//				final String sitePk = activeSite.getPk().toString();
//				final String catalogPk = activeCatalogVersion.getPk().toString();
//
//				// Open in page edit button
//				if (StringUtils.isNotBlank(browserModel.getRelatedPagePk()))
//				{
//					createRightCaptionButton(Labels.getLabel("browser.openInPageEdit"), "btnliveeditcontent_pageedit",
//							buttonContainer, new org.zkoss.zk.ui.event.EventListener()
//							{
//								@Override
//								public void onEvent(final Event event) throws Exception //NOPMD: ZK Specific
//								{
//									final StringBuilder urlBuilder = new StringBuilder();
//									urlBuilder.append("?").append(PERSP_TAG);
//									urlBuilder.append("=").append(PAGE_VIEW_PERSPECTIVE_ID);
//									urlBuilder.append("&").append(EVENTS_TAG);
//									urlBuilder.append("=").append(CMS_NAVIGATION_EVENT);
//									urlBuilder.append("&").append(CMS_PNAV_SITE);
//									urlBuilder.append("=").append(sitePk);
//									urlBuilder.append("&").append(CMS_PNAV_CATALOG);
//									urlBuilder.append("=").append(catalogPk);
//									urlBuilder.append("&").append(CMS_PNAV_PAGE);
//									urlBuilder.append("=").append(browserModel.getRelatedPagePk());
//
//
//
//									Executions.getCurrent().sendRedirect(urlBuilder.toString());
//								}
//							});
//				}
//			}
//		}
//
//		@Required
//		public void setCmsAdminSiteService(final CMSAdminSiteService cmsAdminSiteService)
//		{
//			this.cmsAdminSiteService = cmsAdminSiteService;
//		}
//
//		protected CMSAdminSiteService getCmsAdminSiteService()
//		{
//
//			return this.cmsAdminSiteService;
//		}
//	}
//
//}
