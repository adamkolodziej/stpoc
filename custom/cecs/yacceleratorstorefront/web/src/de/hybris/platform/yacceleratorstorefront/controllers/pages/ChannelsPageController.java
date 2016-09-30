package de.hybris.platform.yacceleratorstorefront.controllers.pages;

import com.hybris.showcase.facades.ChannelsFacade;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by miroslaw.szot@sap.com on 2016-04-18.
 */
@Controller
@RequestMapping(value = "/channels")
public class ChannelsPageController extends AbstractPageController {
    public static String CHANNELS_PAGE_LABEL = "/channels";

    @Autowired
    private ChannelsFacade channelsFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String channels(Model model) throws CMSItemNotFoundException {
        storeCmsPageInModel(model, getContentPageForLabelOrId(CHANNELS_PAGE_LABEL));

        List<ProductData> channels = channelsFacade.getChannels(ProductOption.BASIC, ProductOption.DIGITAL_MEDIA, ProductOption.TV_CHANNELS);
        model.addAttribute("channels", channels);

        return getViewForPage(model);
    }

    @RequestMapping(value = "/{channelCode:.*}", method = RequestMethod.GET)
    public String selectChannel(@PathVariable("channelCode") final String channelCode, final Model model) throws CMSItemNotFoundException
    {
        model.addAttribute("channelCode", channelCode);

        return channels(model);
    }
}
