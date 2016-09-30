package com.hybris.showcase.guidedselling.renderers.cms;

import com.hybris.showcase.guidedselling.data.BundlePackageData;
import com.hybris.showcase.guidedselling.facades.OnePageGuidedSellingFacade;
import com.hybris.showcase.guidedselling.model.components.SimplePackageDetailsComponentModel;
import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.util.UrlPathHelper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import java.util.Map;

/**
 * CECS-213 Page template fix and basic components
 *
 * Created by mgolubovic on 18.3.2015..
 */
public class SimplePackageDetailsComponentRenderer extends DefaultAddOnCMSComponentRenderer<SimplePackageDetailsComponentModel>
{
    private static final String PACKAGE_PATH = "/package/";
    private final UrlPathHelper urlPathHelper = new UrlPathHelper();

    private OnePageGuidedSellingFacade guidedSellingFacade;

    @Override
    protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final SimplePackageDetailsComponentModel component)
    {
        final Map<String, Object> variables = super.getVariablesToExpose(pageContext, component);

        final BundlePackageData packageData = getBundlePackageFromPath(pageContext);
        if(packageData != null)
        {
            variables.put("packageData", packageData);
            variables.put("renderComponent", Boolean.TRUE);
        }
        else
        {
            variables.put("renderComponent", Boolean.FALSE);
        }

        final BundleTemplateModel bundleTemplateModel = getRequestContextData((HttpServletRequest)pageContext.getRequest()).getBundleTemplate();
        if(bundleTemplateModel != null)
        {
            variables.put("bundleTemplateId", bundleTemplateModel.getId());
        }

        return variables;
    }

    protected BundlePackageData getBundlePackageFromPath(final PageContext pageContext)
    {
        final String requestUrl = urlPathHelper.getOriginatingRequestUri((HttpServletRequest)pageContext.getRequest());
        if(requestUrl.contains(PACKAGE_PATH))
        {
            final String packageCode = requestUrl.substring(requestUrl.indexOf(PACKAGE_PATH) + PACKAGE_PATH.length());
            return guidedSellingFacade.getPackageByCode(packageCode);
        }
        return null;
    }

    public OnePageGuidedSellingFacade getGuidedSellingFacade() {
        return guidedSellingFacade;
    }

    @Required
    public void setGuidedSellingFacade(OnePageGuidedSellingFacade guidedSellingFacade) {
        this.guidedSellingFacade = guidedSellingFacade;
    }
}
