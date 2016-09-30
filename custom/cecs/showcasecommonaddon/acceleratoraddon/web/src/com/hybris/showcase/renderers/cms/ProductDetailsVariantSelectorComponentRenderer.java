package com.hybris.showcase.renderers.cms;

import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.cecs.servicesshowcase.model.MerchandiseProductModel;
import com.hybris.showcase.cecs.servicesshowcase.model.MerchandiseVariantProductModel;
import com.hybris.showcase.enums.data.VariantSelectorEnumData;
import com.hybris.showcase.model.components.ProductDetailsVariantSelectorComponentModel;


public class ProductDetailsVariantSelectorComponentRenderer
		extends DefaultAddOnCMSComponentRenderer<ProductDetailsVariantSelectorComponentModel>
{
	private ProductFacade productFacade;

	@Resource(name = "productService")
	private ProductService productService;

	@Resource(name = "enumerationService")
	private EnumerationService enumerationService;

	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext,
			final ProductDetailsVariantSelectorComponentModel component)
	{
		final Map<String, Object> variables = super.getVariablesToExpose(pageContext, component);

		final ProductModel productModel = getRequestContextData((HttpServletRequest) pageContext.getRequest()).getProduct();

		final List<String> color = new ArrayList<String>();
		final List<VariantSelectorEnumData> sizeList = new ArrayList<VariantSelectorEnumData>();

		Collection<VariantProductModel> variants = new ArrayList<VariantProductModel>();

		if (productModel instanceof MerchandiseProductModel)
		{
			variants = productModel.getVariants();
		}
		else
		{
			final MerchandiseVariantProductModel merchandiseVariantProductModel = (MerchandiseVariantProductModel) productModel;
			final String selectedColor = merchandiseVariantProductModel.getColour().getCode();
			variables.put("selectedColor", selectedColor);

			final String selectedSize = enumerationService.getEnumerationName(merchandiseVariantProductModel.getSize());
			variables.put("selectedSize", selectedSize);

			variants = merchandiseVariantProductModel.getBaseProduct().getVariants();
		}

		iterateOverVariantsAndGetSizeAndColor(color, sizeList, variants);

		final List<String> colorList = new ArrayList<String>(new HashSet<String>(color));
		variables.put("colors", colorList);

		Collections.sort(sizeList, new Comparator<VariantSelectorEnumData>()
		{
			@Override
			public int compare(final VariantSelectorEnumData size1, final VariantSelectorEnumData size2)
			{
				return (size1.getSizeEnumCode()).compareTo(size2.getSizeEnumCode());
			}
		});
		variables.put("sizes", sizeList);

		return variables;

	}


	private void iterateOverVariantsAndGetSizeAndColor(final List<String> color, final List<VariantSelectorEnumData> sizeList,
			final Collection<VariantProductModel> variants)
	{
		for (final Iterator<VariantProductModel> iterator = variants.iterator(); iterator.hasNext();)
		{
			final MerchandiseVariantProductModel mercandiseVariantProduct = (MerchandiseVariantProductModel) iterator.next();

			getSizeAndColorForVariant(color, sizeList, mercandiseVariantProduct);
		}
	}

	private void getSizeAndColorForVariant(final List<String> color, final List<VariantSelectorEnumData> size,
			final MerchandiseVariantProductModel mercandiseVariantProduct)
	{
		final VariantSelectorEnumData sizeData = new VariantSelectorEnumData();

		sizeData.setSizeEnumCode(mercandiseVariantProduct.getSize().getCode());
		sizeData.setSizeEnumName(enumerationService.getEnumerationName(mercandiseVariantProduct.getSize()));

		size.add(sizeData);

		color.add(enumerationService.getEnumerationName(mercandiseVariantProduct.getColour()));
	}

	final public ProductFacade getProductFacade()
	{
		return productFacade;
	}

	@Required
	public void setProductFacade(final ProductFacade productFacade)
	{
		this.productFacade = productFacade;
	}
}
