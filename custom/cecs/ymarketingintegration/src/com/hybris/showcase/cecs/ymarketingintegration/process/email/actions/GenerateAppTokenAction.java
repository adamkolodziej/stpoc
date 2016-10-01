/**
 * 
 */
package com.hybris.showcase.cecs.ymarketingintegration.process.email.actions;

import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import de.hybris.platform.processengine.model.BusinessProcessParameterModel;
import de.hybris.platform.task.RetryLaterException;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;



/**
 * @author npavlovic
 * 
 */
public class GenerateAppTokenAction extends AbstractSimpleDecisionAction<StoreFrontCustomerProcessModel>
{
	private static final Logger LOG = Logger.getLogger(GenerateAppTokenAction.class);
	private static final String APP_TOKEN = "appToken";


	@Override
	public Transition executeAction(final StoreFrontCustomerProcessModel businessProcessModel) throws RetryLaterException
	{
		final CustomerModel customer = businessProcessModel.getCustomer();

		if (customer != null)
		{
			final String token = "1";

			if (!StringUtils.isBlank(token))
			{
				final BusinessProcessParameterModel tokenParameter = new BusinessProcessParameterModel();
				tokenParameter.setName(APP_TOKEN);
				tokenParameter.setValue(token);
				tokenParameter.setProcess(businessProcessModel);

				final Collection<BusinessProcessParameterModel> parameters = new ArrayList<BusinessProcessParameterModel>();
				parameters.add(tokenParameter);

				businessProcessModel.setContextParameters(parameters);

				LOG.info("Mobile application token generated");
				getModelService().save(businessProcessModel);

				return Transition.OK;
			}
		}

		LOG.info("Could not generate mobile application token");
		return Transition.NOK;
	}
}
