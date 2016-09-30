/**
 * 
 */
package com.hybris.showcase.renderers.cms;

import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.jsp.PageContext;

import com.hybris.showcase.AccountPagePaymentDetailsControllerBillingForm;
import com.hybris.showcase.model.BillingOptionsComponentModel;


/**
 * @author I307113
 * 
 */
public class BillingOptionComponentRenderer extends DefaultAddOnCMSComponentRenderer<BillingOptionsComponentModel>
{
	@Resource
	UserService userService;

	@Override
	protected Map<String, Object> getVariablesToExpose(final PageContext pageContext, final BillingOptionsComponentModel component)
	{
		final Map<String, Object> variables = new HashMap<>();
		final CustomerModel customerModel = (CustomerModel) userService.getCurrentUser();

		final boolean isBillingEpaperEnabled = customerModel.getIsBillingEpaperEnabled().booleanValue();

		final boolean isEmailPaymentRemindersEnabled = customerModel.getIsEmailPaymentRemindersEnabled().booleanValue();

		final AccountPagePaymentDetailsControllerBillingForm billingForm = new AccountPagePaymentDetailsControllerBillingForm(
				isBillingEpaperEnabled, isEmailPaymentRemindersEnabled);

		variables.put("billingForm", billingForm);

		return variables;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

}
