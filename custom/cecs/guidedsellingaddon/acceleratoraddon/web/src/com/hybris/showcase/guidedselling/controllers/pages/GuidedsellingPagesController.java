/**
 *
 */
package com.hybris.showcase.guidedselling.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.checkout.steps.CheckoutStep;
import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.b2ctelcofacades.data.BundleTabData;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.configurablebundlefacades.order.BundleCartFacade;
import de.hybris.platform.configurablebundleservices.bundle.BundleTemplateService;
import de.hybris.platform.configurablebundleservices.model.BundleSelectionCriteriaModel;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.configurablebundleservices.model.PickExactlyNBundleSelectionCriteriaModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.session.SessionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hybris.showcase.guidedselling.data.*;
import com.hybris.showcase.guidedselling.enums.BundleComponentType;
import com.hybris.showcase.guidedselling.facades.OnePageGuidedSellingFacade;


@Controller
@RequestMapping("/guidedselling")
public class GuidedsellingPagesController extends AbstractAddOnPageController
{
	private static final Logger LOG = Logger.getLogger(GuidedsellingPagesController.class);
	private static final String GUIDEDSELLING_CMS_PAGE = "subscriptionBundle";

	// CECS-188 guidedselling: open in edit mode focused on TV Addons - TCO
	public static final String FOCUSED_COMPONENT_ID = "focusedComponentId_";


	//CECS-914
	public static final String PRESELECTED_PRODUCT_ID = "preselectedProductId_";

	@Resource(name = "guidedSellingFacade")
	private OnePageGuidedSellingFacade guidedSellingFacade;

	@Resource(name = "cartFacade")
	private BundleCartFacade cartFacade;

	@Resource(name = "bundleTemplateService")
	private BundleTemplateService bundleTemplateService;

	@Resource(name = "cartService")
	private CartService cartService;

	@Resource(name = "sessionService")
	private SessionService sessionService;

	@Resource(name = "guidedsellingMultiStepCheckout")
	private CheckoutStep guidedsellingMultiStepCheckout;

	@Resource(name = "configurePackagesCheckoutStep")
	private CheckoutStep configurePackagesCheckoutStep;

	@Resource(name = "summaryCheckoutStep")
	private CheckoutStep summaryCheckoutStep;

	@RequestMapping(value = "/{bundleTemplateId}", method = RequestMethod.GET)
	public String subscriptionBundlePage(@PathVariable("bundleTemplateId") final String bundleTemplateId, final Model model,
			final HttpServletRequest request) throws CMSItemNotFoundException
	{
		final ContentPageModel page = getContentPageForLabelOrId(GUIDEDSELLING_CMS_PAGE);
		storeCmsPageInModel(model, page);
		setUpMetaDataForContentPage(model, page);

		final List<BundleTabData> bundleTabs = guidedSellingFacade.getBundleTabs(bundleTemplateId);
		model.addAttribute("bundleTabs", bundleTabs);
		model.addAttribute("priceData", new PriceData());

		final BundleTemplateModel bundleTemplateModel = getBundleTemplateService().getBundleTemplateForCode(bundleTemplateId);
		getRequestContextData(request).setBundleTemplate(bundleTemplateModel);

		final BundleOfferData offerData = guidedSellingFacade.getBundleOffer(1);
		model.addAttribute("offer", offerData);

		return getViewForPage(model);
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String getCurrentOffer(final Model model, final HttpServletRequest request) throws CMSItemNotFoundException
	{
		if (!cartService.hasSessionCart())
		{
			return "redirect:/";
		}

		final BundleOfferData offerData = guidedSellingFacade.getBundleOffer(1);
		final BundleTemplateModel bundleTemplateModel = getBundleTemplateService()
				.getBundleTemplateForCode(offerData.getRootBundleTemplateId());
		getRequestContextData(request).setBundleTemplate(bundleTemplateModel);

		model.addAttribute("offer", offerData);
		model.addAttribute("bundleTemplateId", offerData.getRootBundleTemplateId());

		final ContentPageModel page = getContentPageForLabelOrId(GUIDEDSELLING_CMS_PAGE);
		storeCmsPageInModel(model, page);
		setUpMetaDataForContentPage(model, page);
		return getViewForPage(model);
	}

	@RequestMapping(value = "/offer/{bundleNo}", method = RequestMethod.GET)
	@ResponseBody
	public BundleOfferData getOffer(@PathVariable("bundleNo") final int bundleNo, final HttpSession session)
	{
		final BundleOfferData offer = guidedSellingFacade.getBundleOffer(bundleNo);

		// CECS-188 guidedselling: open in edit mode focused on TV Addons - TCO
		offer.setFocusedComponentId((String) session.getAttribute(FOCUSED_COMPONENT_ID + offer.getRootBundleTemplateId()));

		if (StringUtils.isNotEmpty((String) session.getAttribute(PRESELECTED_PRODUCT_ID)))
		{
			preselectOption((String) session.getAttribute(PRESELECTED_PRODUCT_ID + offer.getRootBundleTemplateId()), offer);
		}

		return offer;
	}

	@RequestMapping(value = "/{bundleTemplateId}/package/{packageCode}", method = RequestMethod.GET)
	public String startWithPackage(@PathVariable("bundleTemplateId") final String bundleTemplateId,
			@PathVariable("packageCode") final String packageCode, final Model model, final HttpServletRequest request)
					throws CMSItemNotFoundException
	{
		final ContentPageModel page = getContentPageForLabelOrId(GUIDEDSELLING_CMS_PAGE);
		storeCmsPageInModel(model, page);
		setUpMetaDataForContentPage(model, page);
		final BundleTemplateModel bundleTemplateModel = getBundleTemplateService().getBundleTemplateForCode(bundleTemplateId);
		getRequestContextData(request).setBundleTemplate(bundleTemplateModel);

		BundleOfferData offerData;
		if (guidedSellingFacade.canReusePackageOffer(bundleTemplateId, packageCode))
		{
			offerData = guidedSellingFacade.getBundleOffer(1);
		}
		else
		{
			cartFacade.removeSessionCart();
			offerData = guidedSellingFacade.startWithPackage(bundleTemplateId, packageCode);
		}

		model.addAttribute("offer", offerData);
		model.addAttribute("bundleTemplateId", bundleTemplateId);

		final Map<String, String> multiTransitions = guidedsellingMultiStepCheckout.getTransitions();
		final Map<String, String> multiTransitionsNew = new HashMap<>();
		for (final Map.Entry<String, String> entry : multiTransitions.entrySet())
		{
			if (entry.getKey().toString().equals("next"))
			{
				multiTransitionsNew.put(entry.getKey(), "redirect:/guidedselling/Connectivity/package/" + packageCode);
			}
			else
			{
				multiTransitionsNew.put(entry.getKey(), entry.getValue());
			}
		}
		guidedsellingMultiStepCheckout.setTransitions(multiTransitionsNew);


		final Map<String, String> confTransitions = configurePackagesCheckoutStep.getTransitions();
		final Map<String, String> confTransitionsNew = new HashMap<>();
		for (final Map.Entry<String, String> entry : confTransitions.entrySet())
		{
			if (entry.getKey().toString().equals("current"))
			{
				confTransitionsNew.put(entry.getKey(), "redirect:/guidedselling/Connectivity/package/" + packageCode);
			}
			else
			{
				confTransitionsNew.put(entry.getKey(), entry.getValue());
			}
		}
		configurePackagesCheckoutStep.setTransitions(confTransitionsNew);


		final Map<String, String> summaryTransitions = summaryCheckoutStep.getTransitions();
		final Map<String, String> summaryTransitionsNew = new HashMap<>();

		for (final Map.Entry<String, String> entry : summaryTransitions.entrySet())
		{
			if (entry.getKey().toString().equals("previous"))
			{
				summaryTransitionsNew.put(entry.getKey(), "redirect:/guidedselling/Connectivity/package/" + packageCode);
			}
			else
			{
				summaryTransitionsNew.put(entry.getKey(), entry.getValue());
			}
		}
		summaryCheckoutStep.setTransitions(summaryTransitionsNew);

		return getViewForPage(model);
	}

	@RequestMapping(value = "/{bundleTemplateId}/conditional/{productCode}", method = RequestMethod.GET)
	public String startWithConditional(@PathVariable("bundleTemplateId") final String bundleTemplateId,
			@PathVariable("productCode") final String productCode, final Model model, final HttpServletRequest request)
					throws CMSItemNotFoundException
	{
		final ContentPageModel page = getContentPageForLabelOrId(GUIDEDSELLING_CMS_PAGE);
		storeCmsPageInModel(model, page);
		setUpMetaDataForContentPage(model, page);
		final BundleTemplateModel bundleTemplateModel = getBundleTemplateService().getBundleTemplateForCode(bundleTemplateId);
		getRequestContextData(request).setBundleTemplate(bundleTemplateModel);

		final List<ConfigMessage> messages = new ArrayList<>();

		try
		{
			BundleOfferData offerData;
			if (guidedSellingFacade.canReuseProductOffer(bundleTemplateId, productCode))
			{
				offerData = guidedSellingFacade.getBundleOffer(1);
			}
			else
			{
				final int bundleNo = -1;
				cartFacade.removeSessionCart();
				offerData = guidedSellingFacade.startWithConditionalProduct(bundleTemplateId, productCode, bundleNo);
			}
			model.addAttribute("offer", offerData);
		}
		catch (final CommerceCartModificationException e)
		{
			LOG.error("Unable to choose product", e);
			final ConfigMessage msg = new ConfigMessage();
			msg.setSeverity(Severity.ERROR);
			msg.setContent(e.getMessage());
			messages.add(msg);
		}
		model.addAttribute("messages", messages);
		model.addAttribute("bundleTemplateId", bundleTemplateId);

		return getViewForPage(model);
	}

	@RequestMapping(value = "/conditional/select", method = RequestMethod.POST)
	@ResponseBody
	public BundleOfferData selectConditional(@RequestBody final BundleRequest request) throws CommerceCartModificationException
	{
		cartFacade.removeSessionCart();
		final BundleOfferData offerData = guidedSellingFacade.chooseProduct(request.getBundleTemplateId(), request.getProductCode(),
				request.getBundleNo());

		return offerData;
	}

	@RequestMapping(value = "/option/add", method = RequestMethod.POST)
	@ResponseBody
	public BundleOfferData addOption(@RequestBody final OptionAddRequest request)
	{
		final List<ConfigMessage> messages = new ArrayList<>();
		final String productCode = request.getProductCode();
		try
		{
			cartFacade.addToCart(productCode, 1, request.getBundleNo(), request.getBundleTemplateId(), request.getRemoveOthers());
		}
		catch (final CommerceCartModificationException e)
		{
			final ConfigMessage msg = new ConfigMessage();
			msg.setSeverity(Severity.ERROR);
			msg.setContent(e.getMessage());
			messages.add(msg);
		}

		final BundleOfferData offerData = guidedSellingFacade.getBundleOffer(request.getBundleNo());
		offerData.getMessages().addAll(messages);
		guidedSellingFacade.addMessagesForComponent(messages, request.getBundleTemplateId(), offerData);

		return offerData;
	}

	@RequestMapping(value = "/option/remove", method = RequestMethod.POST)
	@ResponseBody
	public BundleOfferData removeOption(@RequestBody final OptionRemoveRequest request)
	{
		final List<ConfigMessage> messages = new ArrayList<>();
		try
		{
			cartFacade.updateCartEntry(request.getEntryNumber(), 0);
		}
		catch (final CommerceCartModificationException e)
		{
			final ConfigMessage msg = new ConfigMessage();

			msg.setSeverity(Severity.ERROR);
			msg.setContent(e.getMessage());
			messages.add(msg);
		}

		final BundleOfferData offerData = guidedSellingFacade.getBundleOffer(request.getBundleNo());
		offerData.getMessages().addAll(messages);
		guidedSellingFacade.addMessagesForComponent(messages, request.getBundleTemplateId(), offerData);

		return offerData;
	}

	public BundleTemplateService getBundleTemplateService()
	{
		return bundleTemplateService;
	}

	public void setBundleTemplateService(final BundleTemplateService bundleTemplateService)
	{
		this.bundleTemplateService = bundleTemplateService;
	}

	@RequestMapping(value = "/edit/{orderCode}", method = RequestMethod.GET)
	@RequireHardLogIn
	public String editContract(@PathVariable("orderCode") final String orderCode, final Model model, final HttpSession session)
			throws CMSItemNotFoundException
	{
		return editContract(orderCode, null, model, session);
	}

	@RequestMapping(value = "/edit/{orderCode}/focus/{focusedComponentId}", method = RequestMethod.GET)
	@RequireHardLogIn
	public String editContract(@PathVariable("orderCode") final String orderCode,
			@PathVariable("focusedComponentId") final String focusedComponentId, final Model model, final HttpSession session)
					throws CMSItemNotFoundException
	{
		final ContentPageModel page = getContentPageForLabelOrId(GUIDEDSELLING_CMS_PAGE);
		storeCmsPageInModel(model, page);
		setUpMetaDataForContentPage(model, page);

		cartFacade.removeSessionCart();
		final BundleOfferData offerData = guidedSellingFacade.loadOrder(orderCode);

		// CECS-188 guidedselling: open in edit mode focused on TV Addons - TCO
		session.setAttribute(FOCUSED_COMPONENT_ID + offerData.getRootBundleTemplateId(), focusedComponentId);
		offerData.setFocusedComponentId(focusedComponentId);

		model.addAttribute("offer", offerData);
		model.addAttribute("bundleTemplateId", guidedSellingFacade.getRootBundleTemplate(orderCode));

		return getViewForPage(model);
	}

	@RequestMapping(value = "/edit/{orderCode}/focus/{focusedComponentId}/preselect/{preselectedProductId}", method = RequestMethod.GET)
	@RequireHardLogIn
	public String editContract(@PathVariable("orderCode") final String orderCode,
			@PathVariable("focusedComponentId") final String focusedComponentId,
			@PathVariable("preselectedProductId") final String preselectedProductId, final Model model, final HttpSession session)
					throws CMSItemNotFoundException
	{
		final ContentPageModel page = getContentPageForLabelOrId(GUIDEDSELLING_CMS_PAGE);
		storeCmsPageInModel(model, page);
		setUpMetaDataForContentPage(model, page);

		cartFacade.removeSessionCart();
		final BundleOfferData offerData = guidedSellingFacade.loadOrder(orderCode);

		// CECS-188 guidedselling: open in edit mode focused on TV Addons - TCO
		session.setAttribute(FOCUSED_COMPONENT_ID + offerData.getRootBundleTemplateId(), focusedComponentId);
		offerData.setFocusedComponentId(focusedComponentId);

		// CECS-914 preselecting TCO when coming from promotion email
		session.setAttribute(PRESELECTED_PRODUCT_ID + offerData.getRootBundleTemplateId(), preselectedProductId);

		BundleTemplateModel component = bundleTemplateService.getBundleTemplateForCode(focusedComponentId);
		boolean removeCurrentProducts = shouldRemoveOtherProductsOnPreselect(component);

		try
		{
			cartFacade.addToCart(preselectedProductId, 1, offerData.getBundleNo(), focusedComponentId, removeCurrentProducts);
		}
		catch (final CommerceCartModificationException e)
		{
			final ConfigMessage msg = new ConfigMessage();
			msg.setSeverity(Severity.ERROR);
			msg.setContent(e.getMessage());
			offerData.getMessages().add(msg);
		}

		model.addAttribute("offer", offerData);
		model.addAttribute("bundleTemplateId", guidedSellingFacade.getRootBundleTemplate(orderCode));

		return getViewForPage(model);
	}

	// CECS-916 When I click on Sports offer after trial has expired, Sports channel option is not preselected in check-out
	private boolean shouldRemoveOtherProductsOnPreselect(BundleTemplateModel component)
	{
		boolean removeCurrentProducts = false;

		final BundleSelectionCriteriaModel selectionCriteria = component.getBundleSelectionCriteria();
		if (selectionCriteria instanceof PickExactlyNBundleSelectionCriteriaModel)
		{
			PickExactlyNBundleSelectionCriteriaModel pickNCriteria = (PickExactlyNBundleSelectionCriteriaModel) selectionCriteria;
			if (pickNCriteria.getN() < 2)
			{
				removeCurrentProducts = true;
			}
		}
		else if (component.getBundleComponentType() == BundleComponentType.CONDITIONAL)
		{
			removeCurrentProducts = true;
		}
		return removeCurrentProducts;
	}

	private void preselectOption(final String preselectedProductId, final BundleOfferData offerData)
	{
		for (final BundleComponentData component : offerData.getComponents())
		{
			for (final BundleComponentOptionData option : component.getOptions())
			{
				if (preselectedProductId.equals(option.getProduct().getCode()))
				{
					option.setSelected(true);
					option.setAddedToExistingContract(true);
				}
			}
		}
	}

}
