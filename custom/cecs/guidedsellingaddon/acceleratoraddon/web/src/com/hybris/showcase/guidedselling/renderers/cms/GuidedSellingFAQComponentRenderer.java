package com.hybris.showcase.guidedselling.renderers.cms;

import com.hybris.showcase.guidedselling.constants.GeneratedGuidedsellingConstants;
import com.hybris.showcase.guidedselling.model.components.GuidedSellingFAQComponentModel;
import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.configurablebundlefacades.data.BundleTemplateData;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import java.util.Map;

/**
 * Created by mgolubovic on 19.3.2015..
 */
public class GuidedSellingFAQComponentRenderer extends DefaultAddOnCMSComponentRenderer<GuidedSellingFAQComponentModel>
{
    private Converter<BundleTemplateModel, BundleTemplateData> bundleTemplateConverter;

    @Override
    protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final GuidedSellingFAQComponentModel component)
    {
        final Map<String, Object> variables = super.getVariablesToExpose(pageContext, component);

        final BundleTemplateModel bundleTemplateModel = getRequestContextData((HttpServletRequest)pageContext.getRequest()).getBundleTemplate();
        final BundleTemplateData bundleTemplateData = bundleTemplateConverter.convert(bundleTemplateModel);
        variables.put("bundleTemplateData", bundleTemplateData);

        return variables;
    }

    public Converter<BundleTemplateModel, BundleTemplateData> getBundleTemplateConverter() {
        return bundleTemplateConverter;
    }

    @Required
    public void setBundleTemplateConverter(Converter<BundleTemplateModel, BundleTemplateData> bundleTemplateConverter) {
        this.bundleTemplateConverter = bundleTemplateConverter;
    }
}
