package com.hybris.showcase.renderers.cms;

import com.hybris.platform.ticketing.model.DisputeExpenseLinkActionModel;
import com.hybris.showcase.cecs.tricaststore.model.components.AccountExpensesComponentModel;
import com.hybris.showcase.data.CurrentExpensesData;
import com.hybris.showcase.model.ExpenseModel;
import com.hybris.showcase.services.ExpenseService;
import de.hybris.platform.acceleratorcms.model.actions.AbstractCMSActionModel;
import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;

import javax.servlet.jsp.PageContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by I307113 on 2015-11-12.
 */
public class AccountExpensesComponentRenderer extends DefaultAddOnCMSComponentRenderer<AccountExpensesComponentModel> {


    @Override
    protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final AccountExpensesComponentModel component) {
        Map<String, Object> variables = super.getVariablesToExpose(pageContext, component);

        variables.put("component", component);
        return variables;
    }

}
