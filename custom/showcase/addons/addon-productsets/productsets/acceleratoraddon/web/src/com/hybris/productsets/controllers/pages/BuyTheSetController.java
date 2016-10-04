/**
 *
 */
package com.hybris.productsets.controllers.pages;

import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.servicelayer.services.CMSComponentService;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hybris.productsets.facades.ProductSetFacade;
import com.hybris.productsets.facades.data.ProductSetCartItemData;
import com.hybris.productsets.facades.data.ProductSetData;
import com.hybris.productsets.facades.data.VariantSelectOptionData;
import com.hybris.productsets.model.components.BuyTheSetComponentModel;


/**
 * @author dominik.strzyzewski
 *
 */
@Controller
@RequestMapping("/buytheset")
public class BuyTheSetController
{
	private static final Logger LOG = Logger.getLogger(BuyTheSetController.class);
	private static final String ERROR_MSG_TYPE = "errorMsg";
	private static final String CART_POPUP = "addon:/productsets/fragments/addSetToCartPopup";
	private static final String CART_SINGLE_POPUP = "fragments/cart/addToCartPopup";
	private static final String REQUEST_QUANTITY_KEY = "product_";
	private static final String REQUEST_VARIANT_KEY = "variant_product_";

	@Resource(name = "productBuySetOptionsList")
	private List<ProductOption> productBuySetOptionsList;

	@Autowired
	private ProductSetFacade productSetFacade;

	@Autowired
	private CMSComponentService cmsComponentService;

	@Autowired
	private ProductFacade productFacade;

	@Autowired
	private CartFacade cartFacade;

	@RequestMapping(method = RequestMethod.GET)
	public String getProductSet(@RequestParam("productSetCode") final String productSetCode,
			@RequestParam("currentProductCode") final String currentProductCode,
			@RequestParam("componentUid") final String componentUid, final Model model) throws CMSItemNotFoundException
	{

		final BuyTheSetComponentModel component = cmsComponentService.getSimpleCMSComponent(componentUid);
		final ProductData currentProduct = productFacade.getProductForCodeAndOptions(currentProductCode, productBuySetOptionsList);
		final ProductSetData productSet = getProductSet(productSetCode, currentProduct);
		final Map<String, Set<VariantSelectOptionData>> productSelectOptions = productSetFacade
				.getSelectVariantsForProducts(productSet.getProducts());
		final Set<VariantSelectOptionData> currentProductSelectOptions = productSetFacade
				.getSelectVariantsForProduct(currentProduct);

		model.addAttribute("currentProduct", currentProduct);
		model.addAttribute("productSet", productSet);
		model.addAttribute("productSelectOptions", productSelectOptions);
		model.addAttribute("currentProductSelectOptions", currentProductSelectOptions);
		model.addAttribute("productsInRow", Integer.valueOf((int) Math.ceil(Math.sqrt(productSet.getProducts().size()))));
		model.addAttribute("buyButtonText", component.getBuyButtonText());

		return "addon:/productsets/pages/buytheset";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addProductsToCart(final HttpServletRequest request, final Model model)
	{
		final Map<String, Integer> quantities = getProductQuantities(request);

		for (final Entry<String, Integer> entry : new HashMap<>(quantities).entrySet())
		{
			final String code = entry.getKey();

			final int quantity = entry.getValue().intValue();
			if (quantity < 0)
			{
				model.addAttribute(ERROR_MSG_TYPE, "basket.error.quantity.invalid");
				model.addAttribute("quantity", Long.valueOf(0L));
				return CART_POPUP;
			}
			else if (quantity == 0 || code.isEmpty())
			{
				quantities.remove(code);
			}
		}

		final List<CartModificationData> modifications = productSetFacade.addSetToCart(quantities, productBuySetOptionsList);
		final List<ProductSetCartItemData> entries = productSetFacade.getProductSetCartItemEntries(modifications);

		model.addAttribute("entries", entries);

		return CART_POPUP;
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addProductToCart(@RequestParam("quantity") final long quantity,
			@RequestParam("productCode") final String productCode, final Model model) throws CommerceCartModificationException
	{
		if (quantity < 0)
		{
			model.addAttribute(ERROR_MSG_TYPE, "basket.error.quantity.invalid");
			model.addAttribute("quantity", Long.valueOf(0L));
		}
		else if (quantity > 0 && !productCode.isEmpty())
		{
			final CartModificationData cartModification = productSetFacade.addItemToCart(productCode, quantity);
			model.addAttribute("quantity", Long.valueOf(cartModification.getQuantityAdded()));
			model.addAttribute("entry", cartModification.getEntry());
			model.addAttribute("product", productFacade.getProductForCodeAndOptions(productCode, productBuySetOptionsList));

			if (cartModification.getQuantityAdded() == 0L)
			{
				model.addAttribute(ERROR_MSG_TYPE, "basket.information.quantity.noItemsAdded." + cartModification.getStatusCode());
			}
			else if (cartModification.getQuantityAdded() < quantity)
			{
				model.addAttribute(ERROR_MSG_TYPE,
						"basket.information.quantity.reducedNumberOfItemsAdded." + cartModification.getStatusCode());
			}
		}
		else
		{
			model.addAttribute(ERROR_MSG_TYPE, "basket.error.occurred");
		}

		return CART_SINGLE_POPUP;
	}

	private ProductSetData getProductSet(final String productSetCode, final ProductData currentProduct)
	{
		final ProductSetData productSet;
		if (currentProduct != null)
		{
			productSet = productSetFacade.getFilteredProductSetByCode(currentProduct.getCode(), productSetCode,
					productBuySetOptionsList);
		}
		else
		{
			productSet = productSetFacade.getProductSetByCode(productSetCode, productBuySetOptionsList);
		}
		return productSet;
	}

	private Map<String, Integer> getProductQuantities(final HttpServletRequest request)
	{
		final Map<String, Integer> quantities = new HashMap<>();
		final Set<String> keySet = request.getParameterMap().keySet();
		for (final String key : keySet)
		{
			if (key.startsWith(REQUEST_QUANTITY_KEY))
			{
				final String productCode = key.replace(REQUEST_QUANTITY_KEY, "");
				final Integer productQuantity = Integer.valueOf(Integer.parseInt(request.getParameter(key)));

				quantities.put(productCode, productQuantity);
			}
		}
		return quantities;
	}

	@RequestMapping(value = "/variant", method = RequestMethod.GET)
	public String getVariantHtml(@RequestParam("productCode") final String productCode,
			@RequestParam("baseProductCode") final String baseProductCode, final Model model)
	{
		final ProductData product = productFacade.getProductForCodeAndOptions(productCode, productBuySetOptionsList);
		final Set<VariantSelectOptionData> productSelectOptions = productSetFacade.getSelectVariantsForProduct(product);
		model.addAttribute("product", product);
		model.addAttribute("baseProductCode", baseProductCode);
		model.addAttribute("productSelectOptions", productSelectOptions);
		return "addon:/productsets/fragments/variantSelector";
	}
}
