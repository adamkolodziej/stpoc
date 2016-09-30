package com.hybris.showcase.instantcheckout.renderers.cms;

import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.instantcheckout.model.InstantCheckoutComponentModel;


/**
 * Adrian Sbarcea - SAP (i309441)
 */
public class InstantCheckoutComponentRenderer extends DefaultAddOnCMSComponentRenderer<InstantCheckoutComponentModel>
{
	private ProductFacade productFacade;

	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext,
			final InstantCheckoutComponentModel component)
	{
		final Map<String, Object> variables = super.getVariablesToExpose(pageContext, component);

		final ProductData productData = getProductFacade().getProductForCodeAndOptions(
				getRequestContextData((HttpServletRequest) pageContext.getRequest()).getProduct().getCode(),
				Arrays.asList(ProductOption.VARIANT_FULL, ProductOption.STOCK, ProductOption.PRICE));

		if (productData != null)
		{
			variables.put("productForInstCheckout", productData);
		}
		return variables;
	}

	/**
	 * @return the productFacade
	 */
	public ProductFacade getProductFacade()
	{
		return productFacade;
	}


	/**
	 * @param productFacade
	 *           the productFacade to set
	 */
	@Required
	public void setProductFacade(final ProductFacade productFacade)
	{
		this.productFacade = productFacade;
	}

}
