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
package com.hybris.showcase.controllers.cms;

import com.hybris.showcase.controllers.ShowcasecommonaddonControllerConstants;
import com.hybris.showcase.model.components.TvShowReferencesComponentModel;
import de.hybris.platform.acceleratorcms.model.components.ProductReferencesComponentModel;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductReferenceData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.yacceleratorstorefront.controllers.ControllerConstants;
import de.hybris.platform.yacceleratorstorefront.controllers.cms.AbstractCMSComponentController;
import de.hybris.platform.yacceleratorstorefront.controllers.cms.ProductReferencesComponentController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hybris.showcase.model.components.TvShowReferencesComponentModel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;




/**
 * Controller for CMS ProductReferencesComponent
 */
@Controller("TvShowReferencesComponentController")
@RequestMapping(value = ShowcasecommonaddonControllerConstants.TvShowReferencesComponent)
public class TvShowReferencesComponentController extends AbstractCMSAddOnComponentController<TvShowReferencesComponentModel>
{
	protected static final List<ProductOption> PRODUCT_OPTIONS = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE);

	@Resource(name = "accProductFacade")
	private ProductFacade productFacade;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final TvShowReferencesComponentModel component)
	{
		final ProductModel currentProduct = getRequestContextData(request).getProduct();
		if (currentProduct != null)
		{
			final List<ProductReferenceData> productReferences = productFacade.getProductReferencesForCode(currentProduct.getCode(),
					component.getProductReferenceTypes(), PRODUCT_OPTIONS, component.getMaximumNumberProducts());

			model.addAttribute("title", component.getTitle());
			model.addAttribute("productReferences", productReferences);
		}
	}
}
