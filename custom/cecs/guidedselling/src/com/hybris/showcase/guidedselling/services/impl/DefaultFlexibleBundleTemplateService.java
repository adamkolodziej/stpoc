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
package com.hybris.showcase.guidedselling.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.b2ctelcoservices.model.DeviceModel;
import de.hybris.platform.b2ctelcoservices.model.ServiceAddOnModel;
import de.hybris.platform.b2ctelcoservices.model.ServicePlanModel;
import de.hybris.platform.configurablebundleservices.bundle.impl.DefaultBundleTemplateService;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hybris.showcase.guidedselling.enums.BundleComponentType;
import com.hybris.showcase.guidedselling.services.FlexibleBundleTemplateService;

/**
 * 
 * @author miroslaw.szot
 */
public class DefaultFlexibleBundleTemplateService extends DefaultBundleTemplateService implements FlexibleBundleTemplateService
{
	private static final Logger LOG = Logger.getLogger(DefaultBundleTemplateService.class);

	private final Map<Class<? extends ProductModel>, BundleComponentType> bundleTypeMap;

	public DefaultFlexibleBundleTemplateService()
	{
		bundleTypeMap = new HashMap<>();
		bundleTypeMap.put(DeviceModel.class, BundleComponentType.TARGET);
		bundleTypeMap.put(ServicePlanModel.class, BundleComponentType.CONDITIONAL);
		bundleTypeMap.put(ServiceAddOnModel.class, BundleComponentType.ADDON);
	}

	@Override
	public List<BundleTemplateModel> getAllComponentsOfType(final BundleTemplateModel bundleTemplate,
			final EnumSet<BundleComponentType> clazzes)
	{
		validateParameterNotNullStandardMessage("bundleTemplate", bundleTemplate);
		validateParameterNotNullStandardMessage("clazzes", clazzes);

		final List<BundleTemplateModel> components = new ArrayList<BundleTemplateModel>();
		for (final BundleTemplateModel component : bundleTemplate.getChildTemplates())
		{
			if (containsComponenentProductsOfType(component, clazzes))
			{
				components.add(component);
			}
		}

		return components;
	}

	@Override
	public boolean containsComponenentProductsOfType(final BundleTemplateModel bundleTemplate,
			final EnumSet<BundleComponentType> clazzes)
	{
		validateParameterNotNullStandardMessage("bundleTemplate", bundleTemplate);
		validateParameterNotNullStandardMessage("clazz", clazzes);

		final List<ProductModel> products = bundleTemplate.getProducts();
		if (products.isEmpty())
		{
			LOG.warn("BundleTemplate" + bundleTemplate.getId() + " has no products assigned");
			return false;
		}

		return clazzes.contains(bundleTemplate.getBundleComponentType());
	}

	protected EnumSet<BundleComponentType> convertToEnums(final Class<? extends ProductModel>... clazzes)
	{
		final List<BundleComponentType> bundleTypesList = new ArrayList<>();
		for (final Class<? extends ProductModel> cls : clazzes)
		{
			final BundleComponentType bundleComponentType = bundleTypeMap.get(cls);
			if (bundleComponentType != null)
			{
				bundleTypesList.add(bundleComponentType);
			}
			else
			{
				LOG.warn("Unknown bundle type mapping source: " + cls);
			}
		}

		final EnumSet<BundleComponentType> bundleTypes = EnumSet.copyOf(bundleTypesList);
		return bundleTypes;
	}

	@Override
	public List<BundleTemplateModel> getAllComponentsOfType(final BundleTemplateModel bundleTemplate,
			final Class<? extends ProductModel>... clazzes)
	{
		final EnumSet<BundleComponentType> bundleTypes = convertToEnums(clazzes);
		return getAllComponentsOfType(bundleTemplate, bundleTypes);
	}

	@Override
	public boolean containsComponenentProductsOfType(final BundleTemplateModel bundleTemplate,
			final Class<? extends ProductModel>... clazzes)
	{
		final EnumSet<BundleComponentType> bundleTypes = convertToEnums(clazzes);
		return containsComponenentProductsOfType(bundleTemplate, bundleTypes);
	}

}
