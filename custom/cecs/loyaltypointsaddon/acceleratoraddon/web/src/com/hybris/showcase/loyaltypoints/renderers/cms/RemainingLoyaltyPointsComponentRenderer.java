package com.hybris.showcase.loyaltypoints.renderers.cms;

import com.hybris.showcase.loyaltypoints.model.components.RemainingLoyaltyPointsComponentModel;
import com.hybris.showcase.loyaltypoints.services.LoyaltyPointsService;
import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.jsp.PageContext;
import java.util.Map;

/**
 * Created by mgolubovic on 17.4.2015..
 */
public class RemainingLoyaltyPointsComponentRenderer extends DefaultAddOnCMSComponentRenderer<RemainingLoyaltyPointsComponentModel>
{
    private LoyaltyPointsService loyaltyPointsService;

    @Override
    protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final RemainingLoyaltyPointsComponentModel component)
    {
        final Map<String, Object> variables = super.getVariablesToExpose(pageContext, component);
        variables.put("remainingLoyaltyPoints", loyaltyPointsService.getUsersRemainingLoyaltyPoints());

        return variables;
    }

    public LoyaltyPointsService getLoyaltyPointsService() {
        return loyaltyPointsService;
    }

    @Required
    public void setLoyaltyPointsService(LoyaltyPointsService loyaltyPointsService) {
        this.loyaltyPointsService = loyaltyPointsService;
    }
}
