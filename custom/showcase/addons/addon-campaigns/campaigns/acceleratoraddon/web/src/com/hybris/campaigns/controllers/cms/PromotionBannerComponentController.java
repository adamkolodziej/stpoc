package com.hybris.campaigns.controllers.cms;

import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.promotions.model.AbstractPromotionModel;
import de.hybris.platform.promotions.model.ProductPromotionModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Stopwatch;
import com.hybris.campaigns.PromotionMerchandizingService;
import com.hybris.campaigns.model.cms.PromotionBannerComponentModel;


/**
 *
 * @author przemyslaw.muzyk
 */
@Controller("PromotionBannerComponentController")
@RequestMapping("/view/PromotionBannerComponentController")
public class PromotionBannerComponentController extends AbstractCMSAddOnComponentController<PromotionBannerComponentModel>
{
	protected static final Logger LOG = Logger.getLogger(PromotionBannerComponentController.class);

	@Resource(name = "promotionMerchandizingService")
	private PromotionMerchandizingService promotionMerchandizingService;

	@Resource(name = "productUrlConverter")
	private Converter<ProductModel, ProductData> productUrlConverter;

	@Resource(name = "categoryUrlConverter")
	private Converter<CategoryModel, CategoryData> categoryUrlConverter;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final PromotionBannerComponentModel component)
	{
		final Stopwatch timer = Stopwatch.createUnstarted();
		timer.start();
		final AbstractPromotionModel promotion = component.getPromotion();
		if (promotion != null)
		{
			boolean evaluated = true;
			if (Boolean.TRUE.equals(component.getEvaluateRestrictions()))
			{
				if (!(promotion instanceof ProductPromotionModel))
				{
					evaluated = !(promotionMerchandizingService.filterPromotionsByRestrictions(Collections.singletonList(promotion)))
							.isEmpty();
				}
				else
				{
					// If Product Page We Can Evaluate Directly Against The Selected Product
					//final String productCode = ProductDataHelper.getCurrentProduct(request);
					final ProductModel productModel = getRequestContextData(request).getProduct();
					if (productModel != null)
					{
						if (!promotionMerchandizingService.getProductPromotions(productModel, true).contains(promotion))
						{
							evaluated = false;
						}
					}

					if (evaluated)
					{
						evaluated = !(promotionMerchandizingService.filterPromotionsByRestrictions(
								Collections.singletonList(promotion),
								new ArrayList<ProductModel>(promotionMerchandizingService
										.getApplicableProductsFromPromotion((ProductPromotionModel) promotion))).isEmpty());
					}
				}

			}

			if (evaluated)
			{
				final MediaModel banner;
				if (component.getAdvanceNotificationBanner() != null && promotionMerchandizingService.isFuturePromotion(promotion))
				{
					banner = component.getAdvanceNotificationBanner();
				}
				else if (component.getAppliedBanner() != null && promotionMerchandizingService.isAppliedPromotion(promotion))
				{
					banner = component.getAppliedBanner();
				}
				else if (!promotionMerchandizingService.isPastPromotion(promotion))
				{
					banner = component.getDefaultBanner();
				}
				else
				{
					banner = null;
				}

				if (banner != null)
				{
					model.addAttribute("banner", banner);

					if (component.getLink() != null)
					{
						model.addAttribute("url", getUrl(component.getLink()));
					}

				}
			}

		}
		timer.stop();
		if (LOG.isInfoEnabled())
		{
			LOG.info("PromotionBannerComponentController took [" + timer.elapsed(TimeUnit.MILLISECONDS)
					+ "] millis and rendered banner [" + model.containsAttribute("banner") + "]");
		}
	}

	protected String getUrl(final CMSLinkComponentModel component)
	{
		{
			// Try to get the URL from the component
			{
				final String url = component.getUrl();
				if (url != null && !url.isEmpty())
				{
					return url;
				}
			}

			// Try to get the label for the content page
			{
				final ContentPageModel contentPage = component.getContentPage();
				if (contentPage != null)
				{
					return contentPage.getLabel();
				}
			}

			// Try to get the category and build a URL to the category
			final CategoryModel category = component.getCategory();
			if (category != null)
			{
				if (categoryUrlConverter != null)
				{
					return categoryUrlConverter.convert(category).getUrl();
				}

			}

			// Try to get the product and build a URL to the product
			final ProductModel product = component.getProduct();
			if (product != null)
			{
				if (productUrlConverter != null)
				{
					return productUrlConverter.convert(product).getUrl();
				}

			}
			return null;
		}


	}

}
