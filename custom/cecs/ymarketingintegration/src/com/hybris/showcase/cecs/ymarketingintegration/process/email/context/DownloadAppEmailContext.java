/**
 * 
 */
package com.hybris.showcase.cecs.ymarketingintegration.process.email.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.processengine.model.BusinessProcessParameterModel;

import java.util.Collection;


/**
 * @author npavlovic
 * 
 */
public class DownloadAppEmailContext extends AbstractEmailContext<StoreFrontCustomerProcessModel>
{
	private static final String APP_TOKEN = "appToken";

	@Override
	public void init(final StoreFrontCustomerProcessModel businessProcessModel, final EmailPageModel emailPageModel)
	{
		super.init(businessProcessModel, emailPageModel);

		final Collection<BusinessProcessParameterModel> parameters = businessProcessModel.getContextParameters();
		for (final BusinessProcessParameterModel parameter : parameters)
		{
			if (parameter.getName().equals(APP_TOKEN) && parameter.getProcess().getCode().equals(businessProcessModel.getCode()))
			{
				put(APP_TOKEN, parameter.getValue());
			}
		}
	}

	@Override
	protected BaseSiteModel getSite(final StoreFrontCustomerProcessModel businessProcessModel)
	{
		return businessProcessModel.getSite();
	}

	@Override
	protected CustomerModel getCustomer(final StoreFrontCustomerProcessModel businessProcessModel)
	{
		return businessProcessModel.getCustomer();
	}

	@Override
	protected LanguageModel getEmailLanguage(final StoreFrontCustomerProcessModel businessProcessModel)
	{
		return businessProcessModel.getLanguage();
	}
}
