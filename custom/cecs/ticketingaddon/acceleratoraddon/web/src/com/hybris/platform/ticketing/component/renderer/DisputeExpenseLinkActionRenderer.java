package com.hybris.platform.ticketing.component.renderer;

import com.hybris.platform.ticketing.model.DisputeExpenseLinkActionModel;
import com.hybris.showcase.model.ExpenseModel;
import com.hybris.showcase.services.ExpenseService;
import de.hybris.platform.acceleratorcms.component.slot.CMSPageSlotComponentService;
import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.jsp.PageContext;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by I307113 on 2015-11-12.
 */
public class DisputeExpenseLinkActionRenderer extends DefaultAddOnCMSComponentRenderer<DisputeExpenseLinkActionModel> {

    ExpenseService expenseService;

    @Required
    public void setExpenseService(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Override
    protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final DisputeExpenseLinkActionModel component) {
        Map<String, Object> variables = new HashMap<>();

        String expenseCode = (String) pageContext.getRequest().getAttribute("expenseCode");
        if(expenseCode != null) {
            ExpenseModel expenseModel = expenseService.getExpenseForCode(expenseCode);
            if(expenseModel != null && expenseModel.getExpenseDisputes().size() > 0) {
                String disputeExpenseCode = expenseModel.getExpenseDisputes().iterator().next().getCode();
                component.setDisputeExpenseCode(disputeExpenseCode);

                variables.put("disputeExpenseCode", disputeExpenseCode);
            }

            variables.put("expenseCode", expenseCode);
        }

        return variables;
    }

}
