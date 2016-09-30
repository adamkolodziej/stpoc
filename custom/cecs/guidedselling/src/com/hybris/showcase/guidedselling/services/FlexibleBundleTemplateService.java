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
package com.hybris.showcase.guidedselling.services;

import de.hybris.platform.configurablebundleservices.bundle.BundleTemplateService;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;

import java.util.EnumSet;
import java.util.List;

import com.hybris.showcase.guidedselling.enums.BundleComponentType;

/**
 * 
 * @author miroslaw.szot
 */
public interface FlexibleBundleTemplateService extends BundleTemplateService {
	/**
	 * DSHOW-91 Subscription management - dynamic subscription bundles<br />
	 * Returns all child bundle templates (components) with products of the type
	 * <code>clazz</code>.
	 * 
	 * @param bundleTemplate
	 *            the bundleTemplate for which the child components are searched
	 * @param clazzes
	 *            class of the products instances that are in the list of
	 *            assigned products of the searched <code>bundleTemplate</code>
	 *            (e.g. WirelessServiceModel.class)
	 * @return a list of the matching components
	 */
	List<BundleTemplateModel> getAllComponentsOfType(
			BundleTemplateModel bundleTemplate,
			EnumSet<BundleComponentType> clazzes);

	/**
	 * DSHOW-91 Subscription management - dynamic subscription bundles
	 * 
	 * @param bundleTemplate
	 * @param clazzes
	 * @return true of false
	 */
	boolean containsComponenentProductsOfType(
			BundleTemplateModel bundleTemplate,
			EnumSet<BundleComponentType> clazzes);

}
