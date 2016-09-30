package com.hybris.showcase.guidedselling.renderers.cms;

import com.hybris.showcase.guidedselling.model.components.GuidedSellingComponentModel;
import de.hybris.platform.acceleratorservices.storefront.data.JavaScriptVariableData;
import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import org.apache.commons.collections.CollectionUtils;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.PageContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by m.golubovic on 4.6.2015..
 */
public class GuidedSellingComponentRenderer extends DefaultAddOnCMSComponentRenderer<GuidedSellingComponentModel>
{
    private static final String JS_VARIABLES = "jsVariables";

    @Override
    protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final GuidedSellingComponentModel component)
    {
        final Map<String, Object> variables = super.getVariablesToExpose(pageContext, component);
        final List<JavaScriptVariableData> jsVariables = getJsVariables(pageContext.getRequest());

        final JavaScriptVariableData hidePreselectedProducts = new JavaScriptVariableData();
        hidePreselectedProducts.setQualifier("hidePreselectedProducts");
        hidePreselectedProducts.setValue(String.valueOf(component.isHidePreselectedProducts()));
        jsVariables.add(hidePreselectedProducts);

        final JavaScriptVariableData hideDisabledProducts = new JavaScriptVariableData();
        hideDisabledProducts.setQualifier("hideDisabledProducts");
        hideDisabledProducts.setValue(String.valueOf(component.isHideDisabledProducts()));
        jsVariables.add(hideDisabledProducts);

        return variables;
    }

    private List<JavaScriptVariableData> getJsVariables(final ServletRequest request)
    {
        List<JavaScriptVariableData> jsVariables = (List<JavaScriptVariableData>) request.getAttribute(JS_VARIABLES);
        if(CollectionUtils.isEmpty(jsVariables))
        {
            jsVariables = new ArrayList<>();
            request.setAttribute(JS_VARIABLES, jsVariables);
        }
        return jsVariables;
    }
}