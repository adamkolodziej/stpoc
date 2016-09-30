package com.hybris.showcase.loyaltypoints.controllers.pages;

import com.hybris.showcase.loyaltypoints.services.LoyaltyPointsService;
import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by mgolubovic on 7.4.2015..
 */
@Controller
@RequestMapping("/loyalty-points")
public class LoyaltyPointsController extends AbstractAddOnPageController
{
    @Resource(name = "loyaltyPointsService")
    private LoyaltyPointsService loyaltyPointsService;

    @RequestMapping(value = "/remaining", method = RequestMethod.GET)
    @ResponseBody
    public Integer getRemainingLoyaltyPoints()
    {
        return loyaltyPointsService.getUsersRemainingLoyaltyPoints();
    }
}
