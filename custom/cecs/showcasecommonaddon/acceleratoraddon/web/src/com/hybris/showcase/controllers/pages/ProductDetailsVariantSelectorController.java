package com.hybris.showcase.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.*;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hybris.showcase.cecs.servicesshowcase.model.MerchandiseProductModel;
import com.hybris.showcase.cecs.servicesshowcase.model.MerchandiseVariantProductModel;


@Controller
@RequestMapping(value = "/**/p")
public class ProductDetailsVariantSelectorController extends AbstractPageController
{

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(ProductDetailsVariantSelectorController.class);

	private static final String PRODUCT_CODE_PATH_VARIABLE_PATTERN = "/{productCode:.*}/{colorCode:.*}";

	@Resource(name = "productFacade")
	private ProductFacade productFacade;

	@Resource(name = "productService")
	private ProductService productService;

	@Resource(name = "enumerationService")
	private EnumerationService enumerationService;


	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN + "/selectColor", method = RequestMethod.GET)
	public @ResponseBody List<String> getSizeForColor(@PathVariable("productCode") final String productCode,
			@PathVariable("colorCode") final String colorCode) throws CMSItemNotFoundException
	{
		final ProductModel productModel = productService.getProductForCode(productCode);
		final List<String> data = new ArrayList<String>();
		Collection<VariantProductModel> variants = new ArrayList<VariantProductModel>();

		if (productModel instanceof MerchandiseProductModel)
		{
			variants = productModel.getVariants();
		}
		else
		{
			final MerchandiseVariantProductModel merchandiseVariantProductModel = (MerchandiseVariantProductModel) productModel;
			variants = merchandiseVariantProductModel.getBaseProduct().getVariants();
		}

		iterateOverVariantsAndGetSizesAndUrl(colorCode, data, variants);

		return data;
	}


	private void iterateOverVariantsAndGetSizesAndUrl(final String colorCode, final List<String> data,
			final Collection<VariantProductModel> variants)
	{
		for (final Iterator<VariantProductModel> iterator = variants.iterator(); iterator.hasNext();)
		{
			final MerchandiseVariantProductModel mercandiseVariantProduct = (MerchandiseVariantProductModel) iterator.next();
			if (enumerationService.getEnumerationName(mercandiseVariantProduct.getColour()).equals(colorCode))
			{
				final ProductData productData = productFacade.getProductForCodeAndOptions(mercandiseVariantProduct.getCode(),
						Arrays.asList(ProductOption.BASIC));

				data.add(enumerationService.getEnumerationName(mercandiseVariantProduct.getSize()));
				data.add(productData.getUrl());
			}
		}
	}
}
