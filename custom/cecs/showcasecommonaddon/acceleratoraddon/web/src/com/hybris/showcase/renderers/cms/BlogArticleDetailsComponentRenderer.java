package com.hybris.showcase.renderers.cms;

import com.hybris.showcase.data.BlogArticleData;
import com.hybris.showcase.model.components.BlogArticleDetailsComponentModel;
import com.hybris.showcase.model.components.BlogArticleModel;
import com.hybris.showcase.populators.BlogArticlePopulator;
import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.enumeration.EnumerationService;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import java.util.Map;


/**
 * Created by m.golubovic on 22.6.2015..
 */
public class BlogArticleDetailsComponentRenderer extends DefaultAddOnCMSComponentRenderer<BlogArticleDetailsComponentModel>
{
	protected static final Logger LOG = Logger.getLogger(BlogArticleModel.class);

	EnumerationService enumerationService;

	BlogArticlePopulator blogArticlePopulator;

	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext,
			final BlogArticleDetailsComponentModel component)
	{
		final Map<String, Object> variables = super.getVariablesToExpose(pageContext, component);
		final BlogArticleModel blogArticleModel = getRequestContextData((HttpServletRequest) pageContext.getRequest())
				.getBlogArticle();
		variables.put("blogArticle", blogArticleModel);
		variables.put("productTitle", getProductTitle(blogArticleModel.getProduct()));
        variables.put("productCategory", getProductCategory(blogArticleModel));
		if (blogArticleModel.getItemOfInterest() != null) {
			variables.put("itemOfInterest", blogArticleModel.getItemOfInterest());
			variables.put("itemOfInterestLocale", enumerationService.getEnumerationName(blogArticleModel.getItemOfInterest()));
		}
		return variables;
	}

	private String getProductTitle(final ProductModel product) {
		if (product == null) {
			return "";
		}
		return product.getName();
	}

	private String getProductCategory(final BlogArticleModel blogArticleModel) {
		BlogArticleData blogData=new BlogArticleData();
		blogArticlePopulator.populate(blogArticleModel, blogData);
		return blogData.getCategoryName();
	}

	public void setBlogArticlePopulator(BlogArticlePopulator blogArticlePopulator) {
		this.blogArticlePopulator = blogArticlePopulator;
	}

	public void setEnumerationService(EnumerationService enumerationService) {
		this.enumerationService = enumerationService;
	}
}
