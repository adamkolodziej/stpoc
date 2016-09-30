package com.sap.hybris.reco.addon.controller;

import de.hybris.platform.acceleratorcms.component.slot.CMSPageSlotComponentService;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.commercefacades.product.data.ProductReferenceData;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sap.hybris.reco.addon.dao.InteractionContext;
import com.sap.hybris.reco.addon.facade.ProductRecommendationManagerFacade;
import com.sap.hybris.reco.addon.bo.RecommendationContextProvider;
import com.sap.hybris.reco.common.util.HMCConfigurationReader;
import com.sap.hybris.reco.constants.SapproductrecommendationConstants;

/**
 * Controller for RecommendationList view.
 */
@Controller("SAPRecommendationRetrieveController")
public class SAPRecommendationRetrieveController
{
	private final static Logger LOG = Logger.getLogger( SAPRecommendationRetrieveController.class.getName() );

	@Resource(name = "sapProductRecommendationManagerFacade")
	private ProductRecommendationManagerFacade productRecommendationManagerFacade;
	
	@Resource(name = "hmcConfigurationReader")
	private HMCConfigurationReader configuration;
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private CMSPageSlotComponentService cmsPageSlotComponentService;
	
	/**
	 * @param id 
	 * @param title
	 * @param recotype
	 * @param productCode
	 * @param itemType
	 * @param includeCart
	 * @param model
	 * @return viewName
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/action/recommendations/")
	public String retrieveRecommentdations(@RequestParam("id") final String id, @RequestParam("title") final String title,
										   @RequestParam("recoType") final String recotype, @RequestParam("productCode") final String productCode,
										   @RequestParam("itemType") final String itemType, @RequestParam("includeCart") final String includeCart,
										   @RequestParam(value = "componentId", required = false) final String componentId, final Model model)
			throws UnsupportedEncodingException
	{
		final String viewName = "addon:/sapprodrecoaddon/cms/recommendationlist";
		if (recotype == null || recotype.equals(""))
		{
			LOG.error("Recommendation Model has to be specified.");
			return viewName;
		}
		final RecommendationContextProvider context = productRecommendationManagerFacade.createRecommendationContextProvider();
		context.setProductId(productCode);
		context.setRecotype(recotype);
		context.setIncludeCart(includeCart);
		context.setItemDataSourceType(itemType);
		if (productRecommendationManagerFacade.getSessionUserId().equals("anonymous")){
			context.setUserId(null);
		}else{
			context.setUserId(productRecommendationManagerFacade.getSessionUserId());
		}
		context.setUserType(configuration.getUserType());
		model.addAttribute("title", title);
		model.addAttribute("recoId", id);
		model.addAttribute("recoType", recotype);
		model.addAttribute("itemType", itemType);

		final List<ProductReferenceData> productReferences = productRecommendationManagerFacade
				.getProductRecommendation(context);

		model.addAttribute("productReferences", productReferences);

		if( StringUtils.isNotBlank(componentId) ) {
			final AbstractCMSComponentModel component = cmsPageSlotComponentService.getComponentForId(componentId);
			model.addAttribute("component", component);
			model.addAttribute("actions", component.getActions());
		}

		return viewName;
	}

	/**
	 * @param id
	 * @param itemType
	 * @param scenarioId
	 * @param prodURL
	 * @param prodImageURL
	 *
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/action/interaction/", method = RequestMethod.POST)
	@ResponseBody
	public void registerClickthrough(@RequestParam("id") final String id, @RequestParam("itemType") final String itemType, @RequestParam("scenarioId") final String scenarioId,
									 @RequestParam("prodURL") final String prodURL, @RequestParam("prodImageURL") final String prodImageURL)
			throws UnsupportedEncodingException
	{
		if (configuration.getUsage().equals(SapproductrecommendationConstants.SCENARIO))
		{
			final InteractionContext context = new InteractionContext();
			context.setProductId(id);
			context.setProductType(itemType);
			context.setScenarioId(scenarioId);
			if (productRecommendationManagerFacade.getSessionUserId().equals("anonymous"))
			{
				context.setUserId(null);
			}
			else
			{
				context.setUserId(productRecommendationManagerFacade.getSessionUserId());
			}
			context.setSourceObjectId(request.getSession().getId());
			context.setUserType(configuration.getUserType());
			context.setTimestamp(getInteractionTimeStamp());
			context.setProductNavURL(prodURL);
			context.setProductImageURL(prodImageURL);
			productRecommendationManagerFacade.postInteraction(context);
		}
	}

	/**
	 * Get the interaction Time stamp in the following format
	 * YYYYMMDDhhmmss.mmmuuun
	 * 20150515161616.0000000
	 */
	private String getInteractionTimeStamp(){
		final Calendar cal = Calendar.getInstance();
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
		final String time = sdf.format(cal.getTime());
		return time + "0000";
	}

}

