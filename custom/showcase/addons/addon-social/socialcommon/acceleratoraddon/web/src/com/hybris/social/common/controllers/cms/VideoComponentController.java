/**
 * 
 */
package com.hybris.social.common.controllers.cms;

import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybris.social.common.constants.SocialcommonConstants;
import com.hybris.social.common.converters.populator.VideoMediaPopulator;
import com.hybris.social.common.data.VideoMediaData;
import com.hybris.social.common.model.VideoComponentModel;


/**
 * @author mgolubovic
 * 
 */
@Controller("VideoComponentController")
@RequestMapping(value = SocialcommonConstants.VideoComponentController)
public class VideoComponentController extends AbstractCMSAddOnComponentController<VideoComponentModel>
{
	private static final Logger LOG = Logger.getLogger(VideoComponentController.class.getName());

	@Autowired
	private VideoMediaPopulator videoMediaPopulator;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final VideoComponentModel component)
	{
		final VideoMediaData videoMediaData = new VideoMediaData();
		videoMediaPopulator.populate(component.getVideoMedia(), videoMediaData);
		if (component.getStartingPictureMedia() != null)
		{
			videoMediaData.setStartingPictureUrl(component.getStartingPictureMedia().getDownloadURL());
		}

		model.addAttribute("videoMediaData", videoMediaData);
		model.addAttribute("title", component.getTitle());
		model.addAttribute("isAutoplay", component.getAutoplay());
	}
}
