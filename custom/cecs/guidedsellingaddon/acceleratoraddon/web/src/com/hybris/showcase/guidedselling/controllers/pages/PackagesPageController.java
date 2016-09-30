package com.hybris.showcase.guidedselling.controllers.pages;

import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hybris.showcase.guidedselling.controllers.GuidedsellingaddonControllerConstants;
import com.hybris.showcase.guidedselling.data.BundlePackageData;
import com.hybris.showcase.guidedselling.facades.OnePageGuidedSellingFacade;


/**
 * CECS-148 Packages Page
 *
 * Created by I303845 on 2015-03-03.
 */
@Controller
@RequestMapping("/packages")
public class PackagesPageController extends AbstractAddOnPageController
{
	private static final String PACKAGES_PAGE = "packagesPage";

	@Resource(name = "guidedSellingFacade")
	private OnePageGuidedSellingFacade guidedSellingFacade;

	@RequestMapping(value = "/{bundleTemplateId}", method = RequestMethod.GET)
	public String packagesPage(@PathVariable("bundleTemplateId") final String bundleTemplateId, final Model model)
			throws CMSItemNotFoundException
	{
		final ContentPageModel page = getContentPageForLabelOrId(PACKAGES_PAGE);
		storeCmsPageInModel(model, page);
		setUpMetaDataForContentPage(model, page);

		final List<BundlePackageData> packages = guidedSellingFacade.getPackages(bundleTemplateId);
		model.addAttribute("bundleTemplateId", bundleTemplateId);

		if (packages.isEmpty())
		{
			final List<ProductOption> options = Arrays.asList(ProductOption.BASIC, ProductOption.DESCRIPTION, ProductOption.PRICE);
			final List<ProductData> plans = guidedSellingFacade.getConditionalProducts(bundleTemplateId, options);
			model.addAttribute("plans", plans);
			return GuidedsellingaddonControllerConstants.Views.Pages.GuidedSelling.PlansPage;
		}
		else
		{
			model.addAttribute("packages", packages);
			return GuidedsellingaddonControllerConstants.Views.Pages.GuidedSelling.PackagesPage;
		}

	}

}
