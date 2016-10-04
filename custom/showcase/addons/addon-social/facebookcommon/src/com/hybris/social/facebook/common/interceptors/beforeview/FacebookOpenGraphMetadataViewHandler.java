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
package com.hybris.social.facebook.common.interceptors.beforeview;

import de.hybris.platform.acceleratorservices.storefront.data.MetaElementData;
import de.hybris.platform.addonsupport.interceptors.BeforeViewHandlerAdaptee;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.i18n.CommerceCommonI18NService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;

import com.hybris.social.common.url.SharedPageUrlStrategy;
import com.hybris.social.facebook.common.model.FacebookApplicationModel;
import com.hybris.social.facebook.common.url.OpenGraphPageImageStrategy;


/**
 * 
 */
public class FacebookOpenGraphMetadataViewHandler implements BeforeViewHandlerAdaptee
{
	private SharedPageUrlStrategy sharedPageUrlStrategy;
	private CommerceCommonI18NService commerceCommonI18NService;
	private OpenGraphPageImageStrategy openGraphPageImageStrategy;

	protected MetaElementData createOgMetaElementData(final String property, final String content)
	{
		final MetaElementData meta = new MetaElementData();
		meta.setContent(content);
		meta.setProperty(property);
		return meta;
	}

	protected void doStandardOGSetup(final List<MetaElementData> metas, final HttpServletRequest request,
			final HttpServletResponse response, final ModelMap model)
	{
		metas.add(createOgMetaElementData("og:url", sharedPageUrlStrategy.getUrl(request, response)));
		metas.add(createOgMetaElementData("og:locale",
				commerceCommonI18NService.getLocaleForLanguage(commerceCommonI18NService.getCurrentLanguage()).toString()));

		String desc = null;
		for (final MetaElementData meta : metas)
		{
			if ("description".equals(meta.getName()))
			{
				desc = meta.getContent();
			}
		}
		if (StringUtils.isNotEmpty(desc))
		{
			metas.add(createOgMetaElementData("og:description", desc));
		}

		if (model.containsKey("facebookApplication") && model.get("facebookApplication") != null)
		{
			final FacebookApplicationModel app = (FacebookApplicationModel) model.get("facebookApplication");
			if (app != null && app.getApplicationId() != null)
			{
				metas.add(createOgMetaElementData("fb:app_id", app.getApplicationId().toString()));
			}
		}

		if (model.containsKey("siteName") && model.get("siteName") != null)
		{
			metas.add(createOgMetaElementData("og:site_name", model.get("siteName").toString()));
		}
	}

	protected void doProductSpecificOGSetup(final List<MetaElementData> metas,
			@SuppressWarnings("unused") final HttpServletRequest request, final ModelMap model, final ProductData product)
	{
		metas.add(createOgMetaElementData("og:type", "product"));
		metas.add(createOgMetaElementData("og:title", product.getName()));

		if (model.containsKey("galleryImages"))
		{
			final List<Map<String, ImageData>> imgs = (List<Map<String, ImageData>>) model.get("galleryImages");
			for (final Map<String, ImageData> imgMap : imgs)
			{
				if (imgMap.containsKey("product"))
				{
					final ImageData imageData = imgMap.get("product");
					if (imageData != null)
					{
						metas.add(createOgMetaElementData("og:image", openGraphPageImageStrategy.getUrl(request, imageData)));
					}
				}
			}
		}
	}

	protected void doNonProductSpecificOGSetup(final List<MetaElementData> metas, final ModelMap model)
	{
		metas.add(createOgMetaElementData("og:type", "website"));
		metas.add(createOgMetaElementData("og:title", (String) model.get("pageTitle")));
	}

	@Override
	public String beforeView(final HttpServletRequest request, final HttpServletResponse response, final ModelMap model,
			final String viewName) throws Exception
	{
		if (model.containsKey("metatags"))
		{
			final List<MetaElementData> metas = (List<MetaElementData>) model.get("metatags");

			doStandardOGSetup(metas, request, response, model);

			if (model.containsKey("pageType") && "PRODUCT".equals(model.get("pageType")) && model.containsKey("product")
					&& model.get("product") instanceof ProductData)
			{
				doProductSpecificOGSetup(metas, request, model, (ProductData) model.get("product"));
			}
			else
			{
				doNonProductSpecificOGSetup(metas, model);
			}
		}
		return viewName;
	}

	/**
	 * @param sharedPageUrlStrategy
	 *           the sharedPageUrlStrategy to set
	 */
	public void setSharedPageUrlStrategy(final SharedPageUrlStrategy sharedPageUrlStrategy)
	{
		this.sharedPageUrlStrategy = sharedPageUrlStrategy;
	}

	/**
	 * @param commerceCommonI18NService
	 *           the commerceCommonI18NService to set
	 */
	public void setCommerceCommonI18NService(final CommerceCommonI18NService commerceCommonI18NService)
	{
		this.commerceCommonI18NService = commerceCommonI18NService;
	}

	/**
	 * @param openGraphPageImageStrategy
	 *           the openGraphPageImageStrategy to set
	 */
	public void setOpenGraphPageImageStrategy(final OpenGraphPageImageStrategy openGraphPageImageStrategy)
	{
		this.openGraphPageImageStrategy = openGraphPageImageStrategy;
	}
}
