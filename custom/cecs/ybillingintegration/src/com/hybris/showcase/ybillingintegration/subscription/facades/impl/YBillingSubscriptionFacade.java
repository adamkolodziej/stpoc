/**
 *
 */
package com.hybris.showcase.ybillingintegration.subscription.facades.impl;

import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.subscriptionfacades.exceptions.SubscriptionFacadeException;

import java.util.Map;

import javax.annotation.Nonnull;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.facades.impl.DefaultMockSubscriptionFacade;
import com.hybris.showcase.ybillingintegration.facades.BusinessAgreementFacade;


/**
 * @author Rafal Zymla
 *
 */
public class YBillingSubscriptionFacade extends DefaultMockSubscriptionFacade
{
	private static final Logger LOG = Logger.getLogger(YBillingSubscriptionFacade.class);

	private BusinessAgreementFacade businessAgreementFacade;

	@Override
	@Nonnull
	public CCPaymentInfoData createPaymentSubscription(final Map<String, String> paymentParameters)
			throws SubscriptionFacadeException
	{
		final CreditCardPaymentInfoModel paymentInfo = createPaymentSubscriptionInternal(paymentParameters);

		try
		{
			businessAgreementFacade.createBusinessAgreement(paymentInfo);
		}
		catch (final RuntimeException ex)
		{
			LOG.error("Unable to create BuAG", ex);
		}


		return getCreditCardPaymentInfoConverter().convert(paymentInfo);
	}

	@Override
	public boolean setEmailRemindersAndBillingEpaper(final Boolean isEmailEnabled, final Boolean isBillingEpaper)
	{
		final Boolean valuesSetup = super.setEmailRemindersAndBillingEpaper(isEmailEnabled, isBillingEpaper);
		getBusinessAgreementFacade().changeBusinessAgreementBillingMethod(isBillingEpaper);

		return valuesSetup;
	}

	@Override
	public CCPaymentInfoData changePaymentMethod(final CCPaymentInfoData paymentInfo, final String action, final boolean propagate,
			final Map<String, String> parameters) throws SubscriptionFacadeException
	{

		final CreditCardPaymentInfoModel paymentInfoModel = changePaymentMethodInternal(paymentInfo, action, propagate, parameters);

		// y-billing integration
		try
		{
			getBusinessAgreementFacade().changeBusinessAgreement(paymentInfoModel);
		}
		catch (final Exception ex)
		{
			LOG.error("Error during integration with yBilling", ex);
		}

		return paymentInfo;
	}


	/**
	 * @return the businessAgreementFacade
	 */
	public BusinessAgreementFacade getBusinessAgreementFacade()
	{
		return businessAgreementFacade;
	}


	@Required
	public void setBusinessAgreementFacade(final BusinessAgreementFacade businessAgreementFacade)
	{
		this.businessAgreementFacade = businessAgreementFacade;
	}


}
