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
//package com.hybris.addon.cockpits.cms.events.impl;
//
//import de.hybris.platform.cmscockpit.events.impl.CmsUrlChangeEvent;
//
//import com.hybris.addon.cockpits.session.liveedit.impl.AddOnFrontendAttributes;
//
//
///**
// * Support the ability to store additional Frontend Attributes on the UrlChangeEvent that can be taken advantage of by
// * extended WCMS Features.
// *
// * @author rmcotton
// *
// */
//public class AddOnCmsUrlChangeEvent extends CmsUrlChangeEvent
//{
//	protected AddOnFrontendAttributes addOnFrontEndAttributes;
//
//	public AddOnCmsUrlChangeEvent(final Object source, final String url, final String relatedPagePk, final String frontendUserPk,
//			final String jaloSessionUid, final AddOnFrontendAttributes addOnFrontEndAttributes)
//	{
//		super(source, url, relatedPagePk, frontendUserPk, jaloSessionUid);
//		setAddOnFrontendAttributes(addOnFrontEndAttributes);
//	}
//
//	public AddOnCmsUrlChangeEvent(final Object source, final String url, final String relatedPagePk, final String frontendUserPk,
//			final String jaloSessionUid)
//	{
//		super(source, url, relatedPagePk, frontendUserPk, jaloSessionUid);
//	}
//
//	public void setAddOnFrontendAttributes(final AddOnFrontendAttributes addOnFrontEndAttributes)
//	{
//		this.addOnFrontEndAttributes = addOnFrontEndAttributes;
//	}
//
//	public AddOnFrontendAttributes getAddOnFrontendAttributes()
//	{
//		return this.addOnFrontEndAttributes;
//	}
//}
