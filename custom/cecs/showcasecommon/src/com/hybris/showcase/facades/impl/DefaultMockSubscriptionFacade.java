/**
 *
 */
package com.hybris.showcase.facades.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.subscriptionfacades.exceptions.SubscriptionFacadeException;
import de.hybris.platform.subscriptionfacades.impl.DefaultSubscriptionFacade;

import java.util.Map;

import javax.annotation.Nonnull;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.facades.ServicesSubscriptionFacade;


/**
 * @author Rafal Zymla
 *
 */
public class DefaultMockSubscriptionFacade extends DefaultSubscriptionFacade implements ServicesSubscriptionFacade
{

	private static final Logger LOG = Logger.getLogger(DefaultMockSubscriptionFacade.class);

	private UserService userService;
	private Converter<Map<String, String>, CCPaymentInfoData> ccPaymentInfoDataConverter;
	private Populator<CCPaymentInfoData, CreditCardPaymentInfoModel> cardPaymentInfoReversePopulator;

	@Override
	@Nonnull
	public CCPaymentInfoData createPaymentSubscription(final Map<String, String> paymentParameters)
			throws SubscriptionFacadeException
	{
		final CreditCardPaymentInfoModel paymentInfoModel = createPaymentSubscriptionInternal(paymentParameters);

		return getCreditCardPaymentInfoConverter().convert(paymentInfoModel);


	}

	protected CreditCardPaymentInfoModel createPaymentSubscriptionInternal(final Map<String, String> paymentParameters)
			throws SubscriptionFacadeException
	{
		final CCPaymentInfoData paymentInfoData = new CCPaymentInfoData();
		getCcPaymentInfoDataConverter().convert(paymentParameters, paymentInfoData);
		try
		{
			if (!getCheckoutFacade().hasCheckoutCart())
			{
				getCartService().getSessionCart(); // creates a session cart if none exists
			}

			final CCPaymentInfoData newPaymentSubscription = getCheckoutFacade().createPaymentSubscription(paymentInfoData);

			final CreditCardPaymentInfoModel paymentInfoModel = getModelService().get(PK.parse(newPaymentSubscription.getId()));
			if(StringUtils.isBlank(paymentInfoModel.getSubscriptionId())) {
				paymentInfoModel.setSubscriptionId(paymentParameters.get("merchantPaymentMethodId"));
			}

			paymentInfoModel.setSaved(true);
			getModelService().save(paymentInfoModel);

			return paymentInfoModel;
		}
		catch (final ModelSavingException e)
		{
			LOG.error("Saving the new payment info failed", e);
			throw new SubscriptionFacadeException("Saving the new payment info failed", e);
		}
	}

	@Override
	public CCPaymentInfoData changePaymentMethod(final CCPaymentInfoData paymentInfo, final String action, final boolean propagate,
			final Map<String, String> parameters) throws SubscriptionFacadeException
	{
		changePaymentMethodInternal(paymentInfo, action, propagate, parameters);
		return paymentInfo;
	}

	protected CreditCardPaymentInfoModel changePaymentMethodInternal(final CCPaymentInfoData paymentInfo, final String action,
			final boolean propagate, final Map<String, String> parameters) throws SubscriptionFacadeException
	{

		validateParameterNotNullStandardMessage("paymentInfo", paymentInfo);
		validateParameterNotNullStandardMessage("paymentInfoID", paymentInfo.getId());
		final CustomerModel currentCustomer = (CustomerModel) getUserService().getCurrentUser();
		final CreditCardPaymentInfoModel paymentInfoModel = getCustomerAccountService()
				.getCreditCardPaymentInfoForCode(currentCustomer, paymentInfo.getId());
		getCardPaymentInfoReversePopulator().populate(paymentInfo, paymentInfoModel);

		getModelService().save(paymentInfoModel);
		if (paymentInfoModel.getBillingAddress() != null)
		{
			getModelService().save(paymentInfoModel.getBillingAddress());
			getModelService().refresh(paymentInfoModel);
		}

		return paymentInfoModel;
	}

	// CECS-583	SOAP: change Business Agreement START
	@Override
	public boolean setEmailRemindersAndBillingEpaper(final Boolean isEmailEnabled, final Boolean isBillingEpaper)
	{
		try
		{
			final CustomerModel currentCustomer = (CustomerModel) getUserService().getCurrentUser();
			currentCustomer.setIsBillingEpaperEnabled(isBillingEpaper);
			currentCustomer.setIsEmailPaymentRemindersEnabled(isEmailEnabled);

			getModelService().save(currentCustomer);

			return true;
		}
		catch (final Exception ex)
		{
			return false;
		}
	}
	// CECS-583	SOAP: change Business Agreement END


	protected Converter<Map<String, String>, CCPaymentInfoData> getCcPaymentInfoDataConverter()
	{
		return ccPaymentInfoDataConverter;
	}

	@Required
	public void setCcPaymentInfoDataConverter(final Converter<Map<String, String>, CCPaymentInfoData> ccPaymentInfoDataConverter)
	{
		this.ccPaymentInfoDataConverter = ccPaymentInfoDataConverter;
	}


	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}


	public Populator<CCPaymentInfoData, CreditCardPaymentInfoModel> getCardPaymentInfoReversePopulator()
	{
		return cardPaymentInfoReversePopulator;
	}

	@Required
	public void setCardPaymentInfoReversePopulator(
			final Populator<CCPaymentInfoData, CreditCardPaymentInfoModel> cardPaymentInfoReversePopulator)
	{
		this.cardPaymentInfoReversePopulator = cardPaymentInfoReversePopulator;
	}
}
