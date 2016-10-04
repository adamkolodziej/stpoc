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
package com.hybris.productsets.cockpit.callbackevent;

import de.hybris.platform.cmscockpit.components.liveedit.LiveEditView;
import de.hybris.platform.cmscockpit.url.impl.FrontendUrlDecoder;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.productsets.model.ProductSetModel;
import com.hybris.productsets.services.ProductSetService;

import de.hybris.liveeditaddon.cockpit.callbackevent.AbstractLiveEditCallbackEventHandler;



/**
 * @author rmcotton
 *
 */
public class EditProductSetsCallbackEventHandler<V extends LiveEditView> extends AbstractLiveEditCallbackEventHandler<V>
{

	private ProductSetService productSetService;
	private FrontendUrlDecoder<ProductModel> productFrontendUrlDecoder;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hybris.addon.cockpits.components.liveedit.CallbackEventHandler#getEventId()
	 */
	@Override
	public String getEventId()
	{
		return "editProductSets";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hybris.addon.cockpits.components.liveedit.CallbackEventHandler#onCallbackEvent(de.hybris.platform.cmscockpit
	 * .components.liveedit.LiveEditView, java.lang.String[])
	 */
	@Override
	public void onCallbackEvent(final V view, final Map<String,Object> passedAttributes) throws Exception
	{
		final ProductModel product = getProductForPreviewCatalogVersions(view, view.getModel().getCurrentUrl());

		if (product != null)
		{
			final TypedObject wrappedProduct = getCockpitTypeService().wrapItem(product);
			final Collection<ProductSetModel> productSets = getProductSetService().getProductSetsWithProduct(product);
			final Collection<TypedObject> wrappedSets = getCockpitTypeService().wrapItems(productSets);

			UISessionUtils
					.getCurrentSession()
					.getCurrentPerspective()
					.openReferenceCollectionInBrowserContext(wrappedSets,
							getCockpitTypeService().getObjectTemplate(ProductSetModel._TYPECODE), wrappedProduct,
							Collections.<String, Object> emptyMap());
		}
	}

	protected ProductModel getProductForPreviewCatalogVersions(final V view, final String url)
	{
		return ((ProductModel) getSessionService().executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public Object execute()
			{
				getCatalogVersionService().setSessionCatalogVersions(
						getCounterpartProductCatalogVersionsStrategy().getCounterpartProductCatalogVersions());
				return getProductFrontendUrlDecoder().decode(url);

			}
		}));
	}

	public ProductSetService getProductSetService()
	{
		return productSetService;
	}

	@Required
	public void setProductSetService(final ProductSetService productSetService)
	{
		this.productSetService = productSetService;
	}

	public FrontendUrlDecoder<ProductModel> getProductFrontendUrlDecoder()
	{
		return productFrontendUrlDecoder;
	}

	@Required
	public void setProductFrontendUrlDecoder(final FrontendUrlDecoder<ProductModel> productFrontendUrlDecoder)
	{
		this.productFrontendUrlDecoder = productFrontendUrlDecoder;
	}

}
