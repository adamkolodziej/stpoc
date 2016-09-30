package com.hybris.showcase.controllers.cms;

import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.data.PromotionData;

import java.util.Arrays;
import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybris.showcase.controllers.ShowcasecommonaddonControllerConstants;
import com.hybris.showcase.facades.ProductPriceEvaluationFacade;
import com.hybris.showcase.model.ProductPromotionFragmentComponentModel;


/**
 * @author Sebastian Weiner
 *
 */
@Controller("ProductPromotionFragmentComponentController")
@RequestMapping(value = ShowcasecommonaddonControllerConstants.ProductPromotionFragmentController)
public class ProductPromotionFragmentComponentController extends
		AbstractCMSAddOnComponentController<ProductPromotionFragmentComponentModel>
{

	@Resource(name = "productFacade")
	private ProductFacade productFacade;

	@Resource(name = "productPriceEvaluationFacade")
	private ProductPriceEvaluationFacade productPriceEvaluationFacade;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController#fillModel(javax.servlet.http
	 * .HttpServletRequest, org.springframework.ui.Model,
	 * de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel)
	 */
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
			final ProductPromotionFragmentComponentModel component)
	{

		//final ProductModel productModel = getRequestContextData(request).getProduct();

		final ProductData productData = productFacade.getProductForCodeAndOptions(component.getProductCode(),
				Arrays.asList(ProductOption.BASIC, ProductOption.PRICE, ProductOption.GALLERY, ProductOption.PROMOTIONS));

		final Collection<PromotionData> potentialPromotions = productData.getPotentialPromotions();

		final String promotionDescription;
		final PriceData priceData;

		if (null != potentialPromotions && potentialPromotions.iterator().hasNext())
		{
			final PromotionData promotionData = potentialPromotions.iterator().next();
			promotionDescription = promotionData.getName();
			priceData = productPriceEvaluationFacade.getPriceDataAfterPromotionEvaluation(promotionData, productData.getPrice());
		}
		else
		{
			promotionDescription = "";
			priceData = productData.getPrice();
		}

		model.addAttribute("components", component.getComponents());
		model.addAttribute("title", promotionDescription);
		model.addAttribute("product", productData);
		model.addAttribute("priceData", priceData);

	}
}
