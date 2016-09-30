/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package de.hybris.platform.yacceleratorstorefront.controllers.pages;

import com.hybris.showcase.cecs.servicesshowcase.model.TVSeasonProductModel;
import com.hybris.showcase.cecs.servicesshowcase.model.VideoProductModel;
import com.hybris.showcase.facades.TVSeriesFacade;
import com.hybris.showcase.services.RestrictedContentPagesCMSPageService;
import de.hybris.platform.acceleratorservices.data.RequestContextData;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.CategoryPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commerceservices.search.facetdata.BreadcrumbData;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.yacceleratorstorefront.controllers.ControllerConstants;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;


@Controller
@Scope("tenant")
@RequestMapping(value = "/**/video")
public class VideoPlaybackPageController extends AbstractSearchPageController {
    protected static final Logger LOG = Logger.getLogger(VideoPlaybackPageController.class);

    private static final String VIDEOPLAYBACK_CMS_PAGE = "videoPlaybackPage";

    private static final String PRODUCT_CODE_PATH_VARIABLE_PATTERN = "/v/{productCode:.*}";

    @Resource(name = "restrictedContentPagesCMSPageService")
    private RestrictedContentPagesCMSPageService restrictedContentPagesCMSPageService;

    @Resource(name = "productService")
    private ProductService productService;

    @Resource(name = "tVSeriesFacade")
    private TVSeriesFacade tVSeriesFacade;


    @RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
    public String category(@PathVariable("productCode") final String productCode, final Model model,
                           final HttpServletRequest request) throws UnsupportedEncodingException {
        final ProductModel productModel = productService.getProductForCode(productCode);
        getRequestContextData(request).setProduct(productModel);

        ContentPageModel page = null;
        try {
            page = getContentPageForLabel(VIDEOPLAYBACK_CMS_PAGE, getRequestContextData(request));
        } catch (final CMSItemNotFoundException e) {
            LOG.error("Getting Content Page Model failed", e);
        }

        storeCmsPageInModel(model, page);
        setUpMetaDataForContentPage(model, page);

        if( productModel instanceof VideoProductModel) {
            MediaModel backgroundImage = getBackgroundImage((VideoProductModel) productModel);

            if (backgroundImage != null)
            {
                model.addAttribute("backgroundImage", backgroundImage.getURL());
            }
        }

        return getViewForPage(model);
    }

    private MediaModel getBackgroundImage(VideoProductModel videoProduct) {
        MediaModel backgroundImage = videoProduct.getBackgroundImage();

        if( backgroundImage == null ) {
            final TVSeasonProductModel season = tVSeriesFacade.getSeasonForProduct(videoProduct, TVSeriesFacade.SeasonGetStrategy.NEWEST);
            if( season != null  ) {
                backgroundImage = season.getBackgroundImage();

                if( backgroundImage == null ) {
                    backgroundImage = season.getTvShow().getBackgroundImage();
                }
            }

        }
        return backgroundImage;
    }

    protected <QUERY> void updatePageTitle(final CategoryModel category, final List<BreadcrumbData<QUERY>> appliedFacets,
                                           final Model model) {
        storeContentPageTitleInModel(model, getPageTitleResolver().resolveCategoryPageTitle(category));
    }

    protected CategoryPageModel getCategoryPage(final CategoryModel category) {
        try {
            return restrictedContentPagesCMSPageService.getPageForCategory(category);
        } catch (final CMSItemNotFoundException ignore) {
            // Ignore
        }
        return null;
    }

    protected ContentPageModel getContentPageForLabel(final String label, final RequestContextData requestContextData)
            throws CMSItemNotFoundException {
        return restrictedContentPagesCMSPageService.getPageForLabel(label, requestContextData);
    }

}
