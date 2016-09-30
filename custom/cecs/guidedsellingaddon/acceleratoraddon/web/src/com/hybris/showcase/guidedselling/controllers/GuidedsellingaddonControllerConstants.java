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
package com.hybris.showcase.guidedselling.controllers;

/**
 */
public interface GuidedsellingaddonControllerConstants
{
    interface Views
    {
        interface Pages
        {
            interface GuidedSelling
            {
                String PackagesPage = "addon:/guidedsellingaddon/pages/packages";
                String PlansPage = "addon:/guidedsellingaddon/pages/plans";
            }

            interface Order {
                String RecapPage = "addon:/guidedsellingaddon/pages/orderRecap";
                String BundleSummaryPage = "addon:/guidedsellingaddon/pages/bundleSummary";
            }

        }
    }
}
