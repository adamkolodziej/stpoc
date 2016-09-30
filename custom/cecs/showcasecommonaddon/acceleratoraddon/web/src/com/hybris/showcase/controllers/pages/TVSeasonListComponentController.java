/**
 *
 */
package com.hybris.showcase.controllers.pages;

import de.hybris.platform.commercefacades.product.ProductOption;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hybris.showcase.controllers.ShowcasecommonaddonControllerConstants;
import com.hybris.showcase.facades.TVSeriesFacade;
import com.hybris.showcase.renderers.cms.TVSeasonListComponentRenderer;


@Controller
@RequestMapping(TVSeasonListComponentController.TV_SEASONS_LIST_COMPONENT_REQUESTPATH)
public class TVSeasonListComponentController
{

	public static final List<ProductOption> episodePopulators = TVSeasonListComponentRenderer.episodePopulators;

	public static final String TV_SEASONS_LIST_COMPONENT_REQUESTPATH = "/tvseasonlistcomponent";
	public static final String TV_SEASONS_LIST_COMPONENT_SEASON_REQUESTPATH = "/season";

	@Resource(name = "tVSeriesFacade")
	private TVSeriesFacade tVSeriesFacade;

	@RequestMapping(value = TV_SEASONS_LIST_COMPONENT_SEASON_REQUESTPATH)
	public String getEpisodesForSeason(@RequestParam("seasonCode") final String productCode, final Model model)
	{
		model.addAttribute("seasonsEpisodeList", tVSeriesFacade.getPopulatedEpisodesForSeasonCode(productCode, episodePopulators));
		return ShowcasecommonaddonControllerConstants.Fragments.TVSeasonListComponent;
	}

}
