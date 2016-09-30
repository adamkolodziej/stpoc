/**
 *
 */
package com.hybris.showcase.ybillingintegration.facades.impl;

import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.ybillingintegration.facades.BusinessAgreementFacade;
import com.hybris.showcase.ybillingintegration.services.BusinessAgreementService;
import com.hybris.showcase.ybillingintegration.services.BusinessPartnerService;
import com.hybris.showcase.ybillingintegration.services.PaymentCardService;


/**
 * @author Sebastian Weiner
 */
public class DefaultBusinessAgreementFacade implements BusinessAgreementFacade
{
	private static final Logger LOG = Logger.getLogger(DefaultBusinessAgreementFacade.class);

	private BusinessAgreementService businessAgreementService;
	private PaymentCardService paymentCardService;
	private BusinessPartnerService businessPartnerService;
	private CustomerAccountService customerAccountService;
	private UserService userService;
	private ModelService modelService;


	@Override
	public String createBusinessAgreement(final CreditCardPaymentInfoModel paymentCardModel)
	{

		final CustomerModel currentCustomer = (CustomerModel) getUserService().getCurrentUser();
		return createBusinessAgreement(paymentCardModel, currentCustomer);
	}

	@Override
	public String createBusinessAgreement(final CreditCardPaymentInfoModel paymentCardModel, final CustomerModel customerModel)
	{

		final String generatedCardNo = paymentCardService.createPaymentCard(paymentCardModel);

		if (!StringUtils.isEmpty(generatedCardNo))
		{
			final String cardId = businessPartnerService.addPaymentCard(customerModel, paymentCardModel, generatedCardNo);
			if (!StringUtils.isEmpty(cardId))
			{
				return getBusinessAgreementService().createBusinessAgreement(customerModel, paymentCardModel);

			}
		}

		return null;

	}

	// CECS-583 SOAP:change Business Agreement START
	@Override
	public boolean changeBusinessAgreement(final CreditCardPaymentInfoModel paymentInfoModel)
	{
		final CustomerModel customerModel = (CustomerModel) getUserService().getCurrentUser();

		return businessAgreementService.changeBusinessAgreement(customerModel, paymentInfoModel, false);
	}

	@Override
	public boolean changeBusinessAgreementBillingMethod(final Boolean isBillingEpaperEnabled)
	{
		final CustomerModel customerModel = (CustomerModel) getUserService().getCurrentUser();

		final List<CreditCardPaymentInfoModel> ccPaymentInfos = customerAccountService.getCreditCardPaymentInfos(customerModel,
				true);

		for (final CreditCardPaymentInfoModel paymentInfoModel : ccPaymentInfos)
		{
			businessAgreementService.changeBusinessAgreement(customerModel, paymentInfoModel, isBillingEpaperEnabled);
		}

		return true;
	}



	// CECS-583 SOAP:change Business Agreement END


	/**
	 * @return the businessAgreementService
	 */
	public BusinessAgreementService getBusinessAgreementService()
	{
		return businessAgreementService;
	}


	/**
	 * @param businessAgreementService
	 *           the businessAgreementService to set
	 */
	public void setBusinessAgreementService(final BusinessAgreementService businessAgreementService)
	{
		this.businessAgreementService = businessAgreementService;
	}

	/**
	 * @return the paymentCardService
	 */
	public PaymentCardService getPaymentCardService()
	{
		return paymentCardService;
	}



	/**
	 * @return the businessPartnerService
	 */
	public BusinessPartnerService getBusinessPartnerService()
	{
		return businessPartnerService;
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
	@Required
	public void setCustomerAccountService(final CustomerAccountService customerAccountService)
	{
		this.customerAccountService = customerAccountService;
	}

	/**
	 * @param businessPartnerService
	 *           the businessPartnerService to set
	 */

	public void setBusinessPartnerService(final BusinessPartnerService businessPartnerService)
	{
		this.businessPartnerService = businessPartnerService;
	}

	/**
	 * @param paymentCardService
	 *           the paymentCardService to set
	 */
	@Required
	public void setPaymentCardService(final PaymentCardService paymentCardService)
	{
		this.paymentCardService = paymentCardService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService
	 *           the userService to set
	 */
	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}




}
