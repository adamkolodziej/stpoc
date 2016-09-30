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
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.data.ContractData;
import com.hybris.showcase.facades.ContractFacade;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;


/**
 * CECS-548 migrate seewhy e-mails into hybris e-mail process<br />
 *
 * Created by miroslaw.szot@sap.com on 2015-09-02.
 */
public class YConvertEmailContext extends AbstractEmailContext<StoreFrontCustomerProcessModel>
{
	public static final String Y_CONVERT_STEP = "yConvertStep";
	public static final String Y_CONVERT_EXPIRY_DATE = "yConvertExpiryDate";

	@Override
	public void init(final StoreFrontCustomerProcessModel businessProcessModel, final EmailPageModel emailPageModel)
	{
		super.init(businessProcessModel, emailPageModel);

		final Collection<BusinessProcessParameterModel> parameters = businessProcessModel.getContextParameters();
		for (final BusinessProcessParameterModel parameter : parameters)
		{
			if (parameter.getName().equals(Y_CONVERT_STEP) && parameter.getProcess().getCode().equals(businessProcessModel.getCode()))
			{
				put(parameter.getName(), parameter.getValue());
			}
		}

		put(Y_CONVERT_EXPIRY_DATE, LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("dd-MMM-uuuu")));
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
