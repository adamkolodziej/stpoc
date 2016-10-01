/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.hybris.showcase.controllers;

import com.hybris.showcase.cecs.sptelstore.model.components.FeaturedCollectionComponentModel;
import com.hybris.showcase.controllers.cms.TvShowReferencesComponentController;
import com.hybris.showcase.model.*;
import com.hybris.showcase.model.components.*;

/**
 */
public interface ShowcasecommonaddonControllerConstants
{
	// implement here controller constants used by this extension
    public static final String _Prefix = "/view/";
    public static final String _Suffix = "Controller";

    public static final String ProductPromotionFragmentController = _Prefix + ProductPromotionFragmentComponentModel._TYPECODE + _Suffix;
    public static final String ProductGridMerchandiseComponentController = _Prefix + ProductGridMerchandiseComponentModel._TYPECODE + _Suffix;
    public static final String Html5VideoComponentController = _Prefix + Html5VideoComponentModel._TYPECODE + _Suffix;
    public static final String FixedProductSetComponentController = _Prefix + FixedProductSetComponentModel._TYPECODE + _Suffix;
    public static final String SlickProductCarouselComponentController = _Prefix + SlickProductCarouselComponentModel._TYPECODE + _Suffix;
    public static final String SimpleNavigationBarComponentController = _Prefix + SimpleNavigationBarComponentModel._TYPECODE + _Suffix;
    public static final String MultiRowFixedProductSetComponentController = _Prefix + MultiRowFixedProductSetComponentModel._TYPECODE + _Suffix;
    public static final String DetailAccountBodyBillingSubComponentController = _Prefix + DetailAccountBodyBillingSubComponentModel._TYPECODE + _Suffix;
    public static final String FeaturedCollectionComponentController = _Prefix + FeaturedCollectionComponentModel._TYPECODE + _Suffix;
    public static final String TvShowReferencesComponent = _Prefix + TvShowReferencesComponentModel._TYPECODE + _Suffix;
    public interface Fragments {
        public static final String TVSeasonListComponent = "addon:/showcasecommonaddon/fragments/tvseasonlistcomponentitems";
    }
}
