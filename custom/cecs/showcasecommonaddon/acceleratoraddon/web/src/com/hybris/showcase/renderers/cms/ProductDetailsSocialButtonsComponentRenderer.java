package com.hybris.showcase.renderers.cms;

import com.hybris.showcase.model.Html5VideoComponentModel;
import com.hybris.showcase.model.components.ProductDetailsSocialButtonsComponentModel;
import com.hybris.social.common.url.SharedPageUrlStrategy;
import com.hybris.social.facebook.socialplugins.model.FacebookSendComponentModel;
import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.jsp.PageContext;
import java.util.Map;

/**
 * CECS-171 Product basic details component
 *
 * Created by mgolubovic on 5.3.2015..
 */
public class ProductDetailsSocialButtonsComponentRenderer extends DefaultAddOnCMSComponentRenderer<ProductDetailsSocialButtonsComponentModel>
{
    private SharedPageUrlStrategy sharedPageUrlStrategy;

    @Override
    protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final ProductDetailsSocialButtonsComponentModel component)
    {
        final Map<String, Object> variables = super.getVariablesToExpose(pageContext, component);
        variables.put(FacebookSendComponentModel.URLTOSEND, getSharedPageUrlStrategy().getUrl(pageContext));

        // TODO: add logic for tweeter, google+ and LinkedIn (separate task)
        // probably related to CEI

        return variables;
    }

    /**
     * @param sharedPageUrlStrategy
     *           the sharedPageUrlStrategy to set
     */
    @Required
    public void setSharedPageUrlStrategy(final SharedPageUrlStrategy sharedPageUrlStrategy)
    {
        this.sharedPageUrlStrategy = sharedPageUrlStrategy;
    }

    public SharedPageUrlStrategy getSharedPageUrlStrategy()
    {
        return this.sharedPageUrlStrategy;
    }
}
