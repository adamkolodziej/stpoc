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
package com.hybris.searchandizing.controllers.cms;

import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.FacetSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.site.BaseSiteService;

import java.util.List;
import java.util.ListIterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybris.searchandizing.cms.model.FollowMeProductSpotlightComponentModel;


/**
 * Showcase specific controller rendering follow-me spotlight products at the top of the lister page.
 * 
 * @author rmcotton
 */
@Controller("FollowMeProductSpotlightComponentController")
@RequestMapping("/view/FollowMeProductSpotlightComponentController")
public class FollowMeProductSpotlightComponentController extends
		AbstractFollowMeComponentController<FollowMeProductSpotlightComponentModel>
{


	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.yacceleratorstorefront.controllers.cms.AbstractCMSComponentController#fillModel(javax.servlet
	 * .http.HttpServletRequest, org.springframework.ui.Model,
	 * de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel)
	 */
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final AbstractCMSComponentModel component)
	{
		final FollowMeProductSpotlightComponentModel spotlightComponent = (FollowMeProductSpotlightComponentModel) component;
		final SearchPageData searchPageData = getRequestContextData(request).getSearch();
		SearchStateData searchStateData = null;
		if (searchPageData instanceof FacetSearchPageData)
		{
			searchStateData = ((FacetSearchPageData<SearchStateData, ProductData>) searchPageData).getCurrentQuery();
		}

		if (searchStateData != null && (spotlightComponent.getPrimarySort() != null || spotlightComponent.getSecondarySort() != null))
		{
			if (spotlightComponent.getStickyCategory() == null
					&& getRequestContextData(request).getSearch().getPagination().getTotalNumberOfResults() > 2)
			{
				model.addAttribute("productData", followMeFacade.getSpotlightProducts(searchStateData, getCurrentCategory(request),
						spotlightComponent.getPrimarySort(), spotlightComponent.getSecondarySort(),
						spotlightComponent.getStickyCategory()));
			}
			else if (spotlightComponent.getStickyCategory() != null)
			{
				final List<ProductData> spotlightProducts = followMeFacade.getSpotlightProducts(searchStateData, getCurrentCategory(request),
						spotlightComponent.getPrimarySort(), spotlightComponent.getSecondarySort(),
						spotlightComponent.getStickyCategory());
				final SearchPageData<ProductData> searchResults = getRequestContextData(request).getSearch();

				// filter products that are the same. This could be the case with sticky categories if we for example
				// have a sticky category of 'canon' and the user is already browsing canon.
				if (searchResults.getPagination().getTotalNumberOfResults() <= 2)
				{
					for (final ListIterator<ProductData> li = spotlightProducts.listIterator(); li.hasNext();)
					{
						final ProductData productData = li.next();
						if (searchPageHasProduct(productData, searchResults))
						{
							li.remove();
						}

					}
				}
				model.addAttribute("productData", spotlightProducts);

			}
		}

	}

	protected boolean searchPageHasProduct(final ProductData productToTest, final SearchPageData<ProductData> searchResults)
	{
		for (final ProductData product : searchResults.getResults())
		{
			if (productToTest.getCode().equals(product.getCode()))
			{
				return true;
			}
		}
		return false;
	}


	@Override
	protected String getCmsComponentFolder()
	{
		return super.getCmsComponentFolder() + "/"
				+ StringUtils.lowerCase(this.baseSiteService.getCurrentBaseSite().getChannel().getCode());
	}

}
