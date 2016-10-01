/**
 *
 */
package com.hybris.showcase.ybillingintegration.setup.impl;

import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.commerceservices.setup.data.ImportData;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import reactor.util.StringUtils;

import com.hybris.showcase.setup.PostInitHook;
import com.hybris.showcase.ybillingintegration.facades.BusinessAgreementFacade;
import com.hybris.showcase.ybillingintegration.services.BusinessPartnerService;


/**
 * @author Rafal Zymla
 *
 */
public class YBillingCustomersPostInitHook implements PostInitHook
{
	private static final String DISABLE_YBILLING_DURING_INIT = "sptelstore_disableYBillingDuringInit";

	private ModelService modelService;

	private BusinessPartnerService businessPartnerService;

	private BusinessAgreementFacade businessAgreementFacade;

	private FlexibleSearchService flexibleSearchService;

	private CustomerAccountService customerAccountService;

	@Override
	public void performPostInitHooks(final AbstractSystemSetup systemSetup, final SystemSetupContext context,
			final List<ImportData> importDataList)
	{

		final boolean enableYBilling = "no".equals(context.getParameter(DISABLE_YBILLING_DURING_INIT));
			if (enableYBilling)
			{
			final List<CustomerModel> customerModels = getCustomersWithLoginDisabled();

			for (final CustomerModel customerModel : customerModels)
			{

				final String businessPartnerNo = getBusinessPartnerService().createBusinessParter(customerModel);

				if (!StringUtils.isEmpty(businessPartnerNo))
				{
					final List<CreditCardPaymentInfoModel> creditCardPaymentInfos = customerAccountService
							.getCreditCardPaymentInfos(customerModel, true);

					for (final CreditCardPaymentInfoModel creditCardPaymentInfoModel : creditCardPaymentInfos)
					{

						final String businessAgreementId = getBusinessAgreementFacade()
								.createBusinessAgreement(creditCardPaymentInfoModel, customerModel);
					}
				}


			}
		}


	}

	private List<CustomerModel> getCustomersWithLoginDisabled()
	{
		final String queryString = //
		"SELECT {p:" + CustomerModel.PK + "} "//
				+ "FROM {" + CustomerModel._TYPECODE + " AS p} WHERE {p:loginDisabled} = false";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);


		return flexibleSearchService.<CustomerModel> search(query).getResult();
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the businessPartnerService
	 */
	public BusinessPartnerService getBusinessPartnerService()
	{
		return businessPartnerService;
	}

	@Required
	public void setBusinessPartnerService(final BusinessPartnerService businessPartnerService)
	{
		this.businessPartnerService = businessPartnerService;
	}

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}



	/**
	 * @return the businessAgreementFacade
	 */
	public BusinessAgreementFacade getBusinessAgreementFacade()
	{
		return businessAgreementFacade;
	}

	/**
	 * @param businessAgreementFacade
	 *           the businessAgreementFacade to set
	 */
	public void setBusinessAgreementFacade(final BusinessAgreementFacade businessAgreementFacade)
	{
		this.businessAgreementFacade = businessAgreementFacade;
	}

	/**
	 * @return the customerAccountService
	 */
	public CustomerAccountService getCustomerAccountService()
	{
		return customerAccountService;
	}

	/**
	 * @param customerAccountService
	 *           the customerAccountService to set
	 */
	public void setCustomerAccountService(final CustomerAccountService customerAccountService)
	{
		this.customerAccountService = customerAccountService;
	}




}
