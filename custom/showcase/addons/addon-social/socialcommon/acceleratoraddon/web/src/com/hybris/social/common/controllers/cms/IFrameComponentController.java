package com.hybris.social.common.controllers.cms;

/**
 * 
 */


import de.hybris.platform.addonsupport.controllers.cms.GenericCMSAddOnComponentController;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hybris.social.common.model.IFrameComponentModel;


/**
 * @author dominik.strzyzewski
 * 
 */
@Controller("IFrameComponentController")
@RequestMapping("/view/IFrameComponentController")
public class IFrameComponentController extends GenericCMSAddOnComponentController
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.social.common.controllers.cms.copy.DefaultCMSComponentController#fillModel(javax.servlet.http.
	 * HttpServletRequest, org.springframework.ui.Model,
	 * de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel)
	 */
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final AbstractCMSComponentModel component)
	{
		super.fillModel(request, model, component);
		final IFrameComponentModel iframe = (IFrameComponentModel) component;
		model.addAttribute("iframe", iframe.getIframe());
	}

}
