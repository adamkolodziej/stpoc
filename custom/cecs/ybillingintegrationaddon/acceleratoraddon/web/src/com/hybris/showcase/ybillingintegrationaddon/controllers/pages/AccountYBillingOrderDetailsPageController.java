package com.hybris.showcase.ybillingintegrationaddon.controllers.pages;

import com.hybris.showcase.ybillingintegrationaddon.facades.YBillingDataMockFacade;
import com.hybris.showcase.ybillingintegration.data.YBillingCharacteristicData;
import com.hybris.showcase.ybillingintegration.data.YBillingOrderDetailsData;
import com.hybris.showcase.ybillingintegration.services.YBillingOrderService;
import com.hybris.showcase.ybillingintegrationaddon.controllers.YbillingintegrationaddonControllerConstants;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;


/**
 * @author Sebastian Weiner
 *
 */
@Controller
@RequestMapping("/my-account")
public class AccountYBillingOrderDetailsPageController extends AbstractAddOnPageController
{
    @Resource(name = "accountBreadcrumbBuilder")
    private ResourceBreadcrumbBuilder accountBreadcrumbBuilder;

    @Resource(name = "yBillingOrderService")
    private YBillingOrderService yBillingOrderService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "ybillingDataMockFacade")
    private YBillingDataMockFacade ybillingDataMockFacade;

	private static final String ACCOUNT_CURRENT_YBILLING_ORDER_DETAILS_PAGE_ID = "accountYBillingOrderDetails";

    @RequestMapping(value = "/ybillingOrderDetails/{orderId}", method = RequestMethod.GET)
    public String ybillingOrderDetails(@PathVariable("orderId") final String orderId, final Model model, final HttpServletRequest request) throws CMSItemNotFoundException
    {
        List<YBillingOrderDetailsData> orderDataList = new ArrayList<>();
        List<Map<String,String>> characteristicMapList = new ArrayList<>();
        final Map<String,String> chracteristicMap = new HashMap<>();
        if(null != orderId) {

            // Remove leading '0'
            String trimedOrderId = orderId.replaceAll("^0*", "");

            String yBillingCustomerId = getCustomer().getYBillingCustomerId();
            List<YBillingOrderDetailsData> yBillingOrderDetailsDatas = yBillingOrderService.getOrderDetails(yBillingCustomerId);

            if (null == yBillingOrderDetailsDatas) {
                yBillingOrderDetailsDatas = ybillingDataMockFacade.getYBillingOrderDetailsDataMock();
            }

            for (YBillingOrderDetailsData yBillingOrderDetailsData : yBillingOrderDetailsDatas) {
                if (trimedOrderId.equals(yBillingOrderDetailsData.getYBillingOrderId())) {
                    orderDataList.add(yBillingOrderDetailsData);

                    Map<String,String> changesMap = new HashMap<>();
                    if(null != yBillingOrderDetailsData.getChangesList() && !yBillingOrderDetailsData.getChangesList().isEmpty())
                    {
                        for (YBillingCharacteristicData yBillingCharacteristicData : yBillingOrderDetailsData.getChangesList())
                        {
                            if(!yBillingCharacteristicData.getCharCode().equals(YbillingintegrationaddonControllerConstants.Views.Pages.VARIANT_CONDITIONS)) {
                                changesMap.put(yBillingCharacteristicData.getCharCode(), yBillingCharacteristicData.getProductCode());
                                chracteristicMap.put(yBillingCharacteristicData.getCharCode(), yBillingCharacteristicData.getBundleTemplate());
                            }
                        }
                    }
                    characteristicMapList.add(changesMap);
                }
            }

        }
        storeCmsPageInModel(model, getContentPageForLabelOrId(ACCOUNT_CURRENT_YBILLING_ORDER_DETAILS_PAGE_ID));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(ACCOUNT_CURRENT_YBILLING_ORDER_DETAILS_PAGE_ID));

        model.addAttribute("orderDataList", orderDataList);
        model.addAttribute("characteristicMapList", characteristicMapList);
        model.addAttribute("chracteristicMap", chracteristicMap);
        model.addAttribute("breadcrumbs", accountBreadcrumbBuilder.getBreadcrumbs("text.account.order.ybilling.details"));

        return getViewForPage(model);
    }

    private CustomerModel getCustomer()
    {
        final UserModel currentUser = userService.getCurrentUser();
        if (currentUser instanceof CustomerModel)
        {
            return (CustomerModel) currentUser;
        }
        return null;
    }
}
