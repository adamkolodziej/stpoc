/**
 *
 */
package com.hybris.showcase.renderers.cms;

import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import com.hybris.showcase.cecs.servicesshowcase.model.TVSeasonProductModel;
import com.hybris.showcase.facades.BlogArticleFacade;
import com.hybris.showcase.facades.TVSeriesFacade;
import com.hybris.showcase.facades.TVSeriesFacade.SeasonGetStrategy;
import com.hybris.showcase.model.components.TvShowProductDetailsComponentModel;


/**
 * @author i327223
 */

public class TvShowProductDetailsComponentRenderer extends DefaultAddOnCMSComponentRenderer<TvShowProductDetailsComponentModel>
{

	private TVSeriesFacade tvSeriesFacade;

	private BlogArticleFacade blogArticleFacade;

	private static final List<ProductOption> PRODUCT_OPTIONS = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE,
			ProductOption.DESCRIPTION, ProductOption.CLASSIFICATION, ProductOption.TV_SERIES, ProductOption.SUMMARY);


	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext,
			final TvShowProductDetailsComponentModel component)
	{
		final Map<String, Object> variables = super.getVariablesToExpose(pageContext, component);
		final ProductModel productModel = getRequestContextData((HttpServletRequest) pageContext.getRequest()).getProduct();
		final TVSeasonProductModel seasonModel = getTVSeriesFacade().getSeasonForProduct(productModel, SeasonGetStrategy.NEWEST);
		final ProductData productData = getTVSeriesFacade().populate(seasonModel, PRODUCT_OPTIONS);
		variables.put("numberOfSeasons", Integer.valueOf(seasonModel.getTvShow().getSeasons().size()));
		variables.put("episodeToWatch", getTVSeriesFacade().getFirstEpisodeFromSeason(seasonModel));
		variables.put("tvProduct", productData);
		variables.put("blog", getBlogArticleFacade().getNewestBlogArticleForProduct(seasonModel.getTvShow().getCode()));

		final MediaModel backgroundImage = getBackgroundImage(seasonModel);

		if (backgroundImage != null)
		{
			variables.put("backgroundImage", backgroundImage.getURL());
		}
		return variables;
	}

	private MediaModel getBackgroundImage(final TVSeasonProductModel seasonModel)
	{
		MediaModel backgroundImage = seasonModel.getBackgroundImage();
		if (backgroundImage == null)
		{
			backgroundImage = seasonModel.getTvShow().getBackgroundImage();
		}
		return backgroundImage;
	}

	public TVSeriesFacade getTVSeriesFacade()
	{
		return tvSeriesFacade;
	}

	public void setTVSeriesFacade(final TVSeriesFacade tvSeriesFacade)
	{
		this.tvSeriesFacade = tvSeriesFacade;
	}

	public void setBlogArticleFacade(final BlogArticleFacade blogArticleFacade)
	{
		this.blogArticleFacade = blogArticleFacade;
	}

	public BlogArticleFacade getBlogArticleFacade()
	{
		return blogArticleFacade;
	}
}

