package com.sap.hybris.reco.addon.controller;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sap.hybris.reco.addon.facade.ProductRecommendationManagerFacade;
import com.sap.hybris.reco.model.CMSSAPRecommendationComponentModel;
import com.sap.hybris.reco.addon.constants.SapprodrecoaddonConstants;
import com.sap.hybris.reco.constants.SapproductrecommendationConstants;


/**
 * @author SAP AG
 * Controller for CMS CMSSAPRecommendationComponentController.
 */
@Controller("CMSSAPRecommendationComponentController")
@RequestMapping(value = "/view/CMSSAPRecommendationComponentController")
public class CMSSAPRecommendationComponentController extends AbstractCMSAddOnComponentController<CMSSAPRecommendationComponentModel>
{
	@Override
	protected String getAddonUiExtensionName(final CMSSAPRecommendationComponentModel component)
	{
		return SapprodrecoaddonConstants.EXTENSIONNAME;
	}

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
							 final CMSSAPRecommendationComponentModel component)
	{
		String productCode = "null";
		final ProductModel currentProduct = getRequestContextData(request).getProduct();
		final CategoryModel currentCategory = getRequestContextData(request).getCategory();
		final String leadingItemType = component.getLeadingitemtype();
		if(leadingItemType != null){
			if (currentProduct != null && leadingItemType.equalsIgnoreCase(SapproductrecommendationConstants.PRODUCT))
			{
				productCode = currentProduct.getCode();
			}
			else if (currentCategory != null && leadingItemType.equalsIgnoreCase(SapproductrecommendationConstants.CATEGORY))
			{
				productCode = currentCategory.getCode();
			}
		}

		model.addAttribute("productCode", productCode);
		model.addAttribute("recoType", component.getRecotype());
		model.addAttribute("title", component.getTitle());
		model.addAttribute("itemType", component.getDatasourcetype());

		if (component.isIncludecart() == true)
		{
			model.addAttribute("includeCart", Boolean.toString(Boolean.TRUE));
		}
		else
		{
			model.addAttribute("includeCart", Boolean.toString(Boolean.FALSE));
		}
		model.addAttribute("leadingItemType", component.getLeadingitemtype());
	}
}