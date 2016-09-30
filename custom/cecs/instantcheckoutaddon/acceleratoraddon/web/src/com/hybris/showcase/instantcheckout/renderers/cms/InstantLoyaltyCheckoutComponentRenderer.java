package com.hybris.showcase.instantcheckout.renderers.cms;

import com.hybris.showcase.instantcheckout.model.InstantLoyaltyCheckoutComponentModel;
import com.hybris.showcase.loyaltypoints.services.LoyaltyPointsService;
import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.core.model.product.ProductModel;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import java.util.Map;

/**
 * Created by m.golubovic on 16.6.2015..
 */
public class InstantLoyaltyCheckoutComponentRenderer extends DefaultAddOnCMSComponentRenderer<InstantLoyaltyCheckoutComponentModel>
{
    private LoyaltyPointsService loyaltyPointsService;

    @Override
    protected Map<String, Object> getVariablesToExpose(final PageContext pageContext,
           final InstantLoyaltyCheckoutComponentModel component)
    {
        final Map<String, Object> variables = super.getVariablesToExpose(pageContext, component);
        variables.put("loyaltyPayment", Boolean.TRUE);

        final Integer remainingLoyaltyPoints = loyaltyPointsService.getUsersRemainingLoyaltyPoints();
        final ProductModel productModel = getRequestContextData((HttpServletRequest)pageContext.getRequest()).getProduct();
        boolean disabled = true;
        boolean hidden = true;
        if(productModel != null)
        {
            final Double loyaltyPointsPrice = loyaltyPointsService.getLoyaltyPointsPriceForProduct(productModel);
            if(loyaltyPointsPrice != null && loyaltyPointsPrice.doubleValue() > 0.0d)
            {
                hidden = false;
                variables.put("loyaltyPointsPrice", loyaltyPointsPrice.toString());
            }
            if(remainingLoyaltyPoints.doubleValue() >= loyaltyPointsPrice.doubleValue())
            {
                disabled = false;
            }
        }
        variables.put("disabled", new Boolean(disabled));
        variables.put("hidden", new Boolean(hidden));

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
