/**
 *
 */
package com.hybris.showcase.renderers.cms;

import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.cecs.servicesshowcase.model.MovieProductModel;
import com.hybris.showcase.facades.BlogArticleFacade;
import com.hybris.showcase.model.components.MovieProductDetailsComponentModel;


/**
 *
 */
public class MovieProductDetailsComponentRenderer extends DefaultAddOnCMSComponentRenderer<MovieProductDetailsComponentModel>
{
	private ProductFacade productFacade;

	private BlogArticleFacade blogArticleFacade;

	private static final List<ProductOption> PRODUCT_OPTIONS = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE,
			ProductOption.GALLERY, ProductOption.DESCRIPTION, ProductOption.CLASSIFICATION, ProductOption.TV_SERIES,
			ProductOption.SUMMARY);

	@Required
	public ProductFacade getProductFacade()
	{
		return productFacade;
	}

	public void setProductFacade(final ProductFacade productFacade)
	{
		this.productFacade = productFacade;
	}

	@Required
	public BlogArticleFacade getBlogArticleFacade()
	{
		return blogArticleFacade;
	}

	public void setBlogArticleFacade(final BlogArticleFacade blogArticleFacade)
	{
		this.blogArticleFacade = blogArticleFacade;
	}

	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext,
			final MovieProductDetailsComponentModel component)
	{
		final Map<String, Object> variables = super.getVariablesToExpose(pageContext, component);
		final ProductModel productModel = getRequestContextData((HttpServletRequest) pageContext.getRequest()).getProduct();
		final ProductData productData = getProductFacade().getProductForOptions(productModel, PRODUCT_OPTIONS);
		final MovieProductModel movieProductModel = productModel instanceof MovieProductModel ? (MovieProductModel) productModel
				: null;

		variables.put("product", productData);
		variables.put("blog", getBlogArticleFacade().getNewestBlogArticleForProduct(productData.getCode()));
		if (movieProductModel != null && movieProductModel.getBackgroundImage() != null)
		{
			variables.put("backgroundImage", movieProductModel.getBackgroundImage().getURL());
		}

		return variables;
	}
}
