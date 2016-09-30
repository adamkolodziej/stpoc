/**
 *
 */
package com.hybris.showcase.renderers.cms;

import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.cecs.servicesshowcase.model.TVSeasonProductModel;
import com.hybris.showcase.facades.TVSeriesFacade;
import com.hybris.showcase.facades.TVSeriesFacade.SeasonGetStrategy;
import com.hybris.showcase.model.components.TVSeasonListComponentModel;


/**
 *
 */
public class TVSeasonListComponentRenderer extends DefaultAddOnCMSComponentRenderer<TVSeasonListComponentModel>
{

	public static final List<ProductOption> episodePopulators = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE,
			ProductOption.SUMMARY, ProductOption.DESCRIPTION, ProductOption.GALLERY, ProductOption.CATEGORIES, ProductOption.REVIEW,
			ProductOption.PROMOTIONS, ProductOption.CLASSIFICATION, ProductOption.VARIANT_FULL, ProductOption.STOCK,
			ProductOption.VOLUME_PRICES, ProductOption.DELIVERY_MODE_AVAILABILITY, ProductOption.TV_SERIES);

	public static final List<ProductOption> seasonPopulators = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE,
			ProductOption.SUMMARY, ProductOption.DESCRIPTION, ProductOption.GALLERY, ProductOption.CATEGORIES, ProductOption.REVIEW,
			ProductOption.PROMOTIONS, ProductOption.CLASSIFICATION, ProductOption.VARIANT_FULL, ProductOption.STOCK,
			ProductOption.VOLUME_PRICES, ProductOption.DELIVERY_MODE_AVAILABILITY);

	private TVSeriesFacade tVSeriesFacade;

	@Required
	public TVSeriesFacade gettVSeriesFacade()
	{
		return tVSeriesFacade;
	}

	public void settVSeriesFacade(final TVSeriesFacade tVSeriesFacade)
	{
		this.tVSeriesFacade = tVSeriesFacade;
	}

	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final TVSeasonListComponentModel component)
	{
		final Map<String, Object> variables = super.getVariablesToExpose(pageContext, component);
		final ProductModel productModel = getRequestContextData((HttpServletRequest) pageContext.getRequest()).getProduct();

		final TVSeasonProductModel tvSeasonProductModel = gettVSeriesFacade().getSeasonForProduct(productModel,
				SeasonGetStrategy.NEWEST);

		variables.put("seasonsColection", getPopulatedTvSeasons(productModel));
		variables.put("currentSeason", tvSeasonProductModel);
		variables.put("seasonsEpisodeList",
				gettVSeriesFacade().getPopulatedEpisodesForSeasonCode(tvSeasonProductModel.getCode(), episodePopulators));

		return variables;
	}

	protected List<ProductData> getPopulatedTvSeasons(final ProductModel productModel)
	{
		return gettVSeriesFacade().populate(gettVSeriesFacade().getShowForProduct(productModel).getSeasons(), seasonPopulators);
	}

}
