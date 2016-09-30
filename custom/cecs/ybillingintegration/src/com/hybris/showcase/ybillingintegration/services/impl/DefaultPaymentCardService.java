/**
 *
 */
package com.hybris.showcase.ybillingintegration.services.impl;

import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.time.TimeService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.ybillingintegration.services.PaymentCardService;
import com.sap.document.sap.soap.functions.mc_style.Bapibus1186MasterData;
import com.sap.document.sap.soap.functions.mc_style.CreatePaymentCardObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.ZCRMWSPCARDMASTERCREATE;
import com.sap.document.sap.soap.functions.mc_style.ZbapiPcaMasterCreate;


/**
 * @author Rafal Zymla
 *
 */
public class DefaultPaymentCardService implements PaymentCardService
{

	private static final Logger LOG = Logger.getLogger(DefaultBusinessPartnerService.class);

	private static final String ERR_MESSAGE = "Error occured during creation of credit card";

	public static final String CARD_TYPE_NO_VALIDATIONS = "Y001";

	private ZCRMWSPCARDMASTERCREATE paymentCardCreateWebService;
	private ModelService modelService;
	private TimeService timeService;

	private final CreatePaymentCardObjectFactory factory = new CreatePaymentCardObjectFactory();

	/**
	 * @return generated credit card number
	 */
	@Override
	public String createPaymentCard(final CreditCardPaymentInfoModel ccPaymentInfo)
	{
		LOG.info("Creating Payment Card for:" + ccPaymentInfo.getCode());
		try
		{
			final String generatedCardNo = generateCardNumber(timeService.getCurrentTime());
			paymentCardCreateWebService.zbapiPcaMasterCreate(createZbapiPcaMasterCreate(ccPaymentInfo, generatedCardNo));
			modelService.save(ccPaymentInfo);
			LOG.info("Created Payment Card No: " + generatedCardNo);
			return generatedCardNo;
		}
		catch (final Exception ex)
		{
			LOG.error(ERR_MESSAGE, ex);
			//throw new WebServiceException(ERR_MESSAGE, ex);
			return null;
		}
	}

	/**
	 * @return the paymentCardCreateWebService
	 */
	public ZCRMWSPCARDMASTERCREATE getPaymentCardCreateWebService()
	{
		return paymentCardCreateWebService;
	}

	/**
	 * @param paymentCardCreateWebService
	 *           the paymentCardCreateWebService to set
	 */
	public void setPaymentCardCreateWebService(final ZCRMWSPCARDMASTERCREATE paymentCardCreateWebService)
	{
		this.paymentCardCreateWebService = paymentCardCreateWebService;
	}

	private ZbapiPcaMasterCreate createZbapiPcaMasterCreate(final CreditCardPaymentInfoModel paymentCardModel,
			final String nonMaskedCardNo)
	{

		final ZbapiPcaMasterCreate request = factory.createZbapiPcaMasterCreate();

		final String maskedCardNo = maskCardNumber(nonMaskedCardNo);

		paymentCardModel.setNumber(maskedCardNo);

		request.setCardNumber(nonMaskedCardNo);
		request.setCardType(CARD_TYPE_NO_VALIDATIONS);

		final Bapibus1186MasterData data = createMasterData(paymentCardModel, maskedCardNo);
		request.setData(data);
		request.setReturn(factory.createTableOfBapiret2());
		request.setSaveDirect("X");

		return request;
	}


	private Bapibus1186MasterData createMasterData(final CreditCardPaymentInfoModel paymentCardModel, final String maskedCardNo)
	{
		final Bapibus1186MasterData data = factory.createBapibus1186MasterData();
		data.setCardType(CARD_TYPE_NO_VALIDATIONS);
		data.setMaskNumber(maskedCardNo);
		data.setValidTo(getDate(Integer.valueOf(paymentCardModel.getValidToMonth()),
				Integer.valueOf(paymentCardModel.getValidToYear()), "yyyyMMdd"));
		if (StringUtils.isNotEmpty(paymentCardModel.getValidFromYear())
				&& StringUtils.isNotEmpty(paymentCardModel.getValidFromMonth()))
		{
			data.setValidFrom(getDateString(paymentCardModel.getValidFromYear(), paymentCardModel.getValidFromMonth(), "01"));
		}
		data.setStampName(paymentCardModel.getCcOwner());
		return data;
	}

	private String getDateString(final String year, final String month, final String day)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append(year).append(month).append(day);
		return sb.toString();
	}

	private static String getDate(final int month, final int year, final String pattern)
	{
		final Calendar calendar = Calendar.getInstance();
		// passing month-1 because 0-->jan, 1-->feb... 11-->dec
		calendar.set(year, month - 1, 1);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		final Date date = calendar.getTime();
		final DateFormat DATE_FORMAT = new SimpleDateFormat(pattern);
		return DATE_FORMAT.format(date);
	}

	private static String generateCardNumber(final Date date)
	{
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm'0'SSS");
		return formatter.format(date);
	}


	public static String maskCardNumber(final String cardNumber)
	{
		String maskedCardNumber = "************";
		if (cardNumber.length() >= 4)
		{
			final String endPortion = cardNumber.trim().substring(cardNumber.length() - 4);
			maskedCardNumber = maskedCardNumber + endPortion;
			return maskedCardNumber;
		}
		return null;
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
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}


	public TimeService getTimeService()
	{
		return timeService;
	}

	@Required
	public void setTimeService(final TimeService timeService)
	{
		this.timeService = timeService;
	}





}
