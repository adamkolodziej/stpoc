/**
 *
 */
package com.hybris.showcase.controllers.cms;

import com.hybris.showcase.cecs.servicesshowcase.model.TVEpisodeProductModel;
import com.sun.javafx.binding.StringFormatter;
import com.sun.jersey.api.client.ClientHandlerException;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.cms2.servicelayer.data.CMSDataFactory;
import de.hybris.platform.cms2.servicelayer.data.RestrictionData;
import de.hybris.platform.cms2.servicelayer.services.CMSRestrictionService;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.subscriptionfacades.data.UsageChargeData;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybris.showcase.cecs.servicesshowcase.model.VideoProductModel;
import com.hybris.showcase.controllers.ShowcasecommonaddonControllerConstants;
import com.hybris.showcase.emsextras.facades.UsageChargeFacade;
import com.hybris.showcase.model.Html5VideoComponentModel;



/**
 * @author npavlovic
 * 
 *         CECS-185: Consumption site - watching video on a tablet
 */
@Controller("Html5VideoComponentController")
@RequestMapping(value = ShowcasecommonaddonControllerConstants.Html5VideoComponentController)
public class Html5VideoComponentController extends AbstractCMSAddOnComponentController<Html5VideoComponentModel>
{
	protected static final Logger LOG = Logger.getLogger(Html5VideoComponentController.class);

	@Resource(name = "cmsDataFactory")
	private CMSDataFactory cmsDataFactory;

	@Resource(name = "cmsRestrictionService")
	private CMSRestrictionService cmsRestrictionService;

	@Resource(name = "usageChargeFacade")
	private UsageChargeFacade usageChargeFacade;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	@Resource(name = "i18nService")
	private I18NService i18nService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController#fillModel(javax.servlet.http
	 * .HttpServletRequest, org.springframework.ui.Model,
	 * de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel)
	 */
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final Html5VideoComponentModel component)
	{
		final ProductModel product = getRequestContextData(request).getProduct();
		MediaModel video = null;
		boolean freePreview = true;

		VideoProductModel videoProduct = null;
		if (product instanceof VideoProductModel ) {
			videoProduct = (VideoProductModel) product;
		}
		
		boolean showFreeVersion = component.isShowFreeVersion();
		model.addAttribute("showFreeVersion", showFreeVersion);
				
		if (component.getVideo() != null)
		{
			video = component.getVideo();
		}
		else if (videoProduct != null ) {
			if(videoProduct.getFullVersionMedia() != null && !showFreeVersion) {
				video = videoProduct.getFullVersionMedia();
				freePreview = false;
			} else {
				video = videoProduct.getFreePreviewMedia();
			}
		}

		final String productCode = videoProduct != null ? videoProduct.getCode() : null;
		if (video != null)
		{
			model.addAttribute("videoUrl", video.getURL());

			model.addAttribute("videoMediaType", video.getMime());
			model.addAttribute("playerId", component.getUid());
			model.addAttribute("freePreview", freePreview);
			model.addAttribute("videoProductCode", productCode);
			createEventsData(model, component);
		}

		// CECS-243 usage submit (to ems/brim) - charge customer
		if( videoProduct != null ) {
			final String entitlementType = "video_streaming"; // YTODO this could be configured somehow (product and/or component)
			model.addAttribute("entitlementType", entitlementType);
			try {
				UsageChargeData usageCharge = usageChargeFacade.getUsageChargeForGrant(productCode, entitlementType);
				model.addAttribute("usageCharge", usageCharge);
			}
			catch (ClientHandlerException ex) {
				String exception = messageSource.getMessage("video.exception.connection", null,
						"video.exception.connection", i18nService.getCurrentLocale());
				model.addAttribute("exception", exception);

				LOG.error(exception, ex);
			}

			if(product instanceof TVEpisodeProductModel) {
				Integer seasonNumber = ((TVEpisodeProductModel)product).getSeason();
				Integer episodeNumber = ((TVEpisodeProductModel)product).getEpisode();
				String showName = ((TVEpisodeProductModel)product).getTvSeason().getTvShow().getName();
				String episodeName = ((TVEpisodeProductModel)product).getName();

				String videoName = String.format("%s - S%02dE%02d: %s", showName, seasonNumber, episodeNumber, episodeName);
				model.addAttribute("videoName", videoName);
			}
			else {
				model.addAttribute("videoName", videoProduct.getName());
			}
		}

	}

	private void createEventsData(final Model model, final Html5VideoComponentModel component)
	{
		final List<AbstractCMSComponentModel> timeUpdateEvents = new ArrayList<AbstractCMSComponentModel>(
				component.getTimeUpdateEvents());
		final RestrictionData restrictionData = cmsDataFactory.createRestrictionData();
		final List<AbstractCMSComponentModel> evaluatedComponents = cmsRestrictionService.evaluateCMSComponents(timeUpdateEvents,
				restrictionData);
		model.addAttribute("timeEvents", evaluatedComponents);
	}

}
