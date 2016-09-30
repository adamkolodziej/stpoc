package com.hybris.showcase.guidedselling.controllers.pages;

import javax.annotation.Resource;

import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/guidedselling/seewhy")
public class GuidedSellingSeeWhyController
{

    private static final String SEEWHY_ADDON_PROPERTY = "seewhy.key";

    @Resource(name = "configurationService")
    private ConfigurationService configurationService;

	@RequestMapping(value = "/getCart", method = RequestMethod.GET)
	public String getSeeWhyCartData(final Model model)
	{
		return "addon:/seewhy/fragments/cart/addToCartPopup";
	}

    @RequestMapping(value = "/isConfigured", method = RequestMethod.GET)
    @ResponseBody
    public String isSeeWhyConfigured() {
        final String seeWhyProperty = configurationService.getConfiguration().getString(SEEWHY_ADDON_PROPERTY);
        if("value".equals(seeWhyProperty)) { // see CECS-549, if the value is 'mock' it will disable real implementation
            return "Y";
        }
        else {
            return "N";
        }
    }

}
