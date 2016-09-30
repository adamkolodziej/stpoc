/**
 *
 */
package com.hybris.showcase.ybillingintegration.services.impl;

import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.xml.ws.WebServiceException;

import org.apache.log4j.Logger;

import com.hybris.showcase.ybillingintegration.constants.YBillingResponseType;
import com.hybris.showcase.ybillingintegration.services.BusinessAgreementService;
import com.sap.document.sap.soap.functions.mc_style.Bapibus1006130GnrlData;
import com.sap.document.sap.soap.functions.mc_style.Bapibus1006130GnrlDataExp;
import com.sap.document.sap.soap.functions.mc_style.Bapibus1006130GnrlDataX;
import com.sap.document.sap.soap.functions.mc_style.Bapibus1006130SpecData;
import com.sap.document.sap.soap.functions.mc_style.Bapibus1006130SpecDataExp;
import com.sap.document.sap.soap.functions.mc_style.Bapibus1006130SpecDataX;
import com.sap.document.sap.soap.functions.mc_style.Bapiret2;
import com.sap.document.sap.soap.functions.mc_style.Bapiret2T;
import com.sap.document.sap.soap.functions.mc_style.ChangeBusinessAgreementObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.TableOfBapiret2;
import com.sap.document.sap.soap.functions.mc_style.ZCRMWSBUAGCHANGE;
import com.sap.document.sap.soap.functions.mc_style.ZCRMWSBUAGCREATE;
import com.sap.document.sap.soap.functions.mc_style.ZcrmBuagChange;
import com.sap.document.sap.soap.functions.mc_style.ZcrmBuagChangeResponse;
import com.sap.document.sap.soap.functions.mc_style.ZcrmBuagCreate;
import com.sap.document.sap.soap.functions.mc_style.ZcrmBuagCreateResponse;


/**
 * @author Sebastian Weiner
 *
 */
public class DefaultBusinessAgreementService implements BusinessAgreementService
{

	// CECS-583 SOAP:change Business Agreement START
	private static final Logger LOG = Logger.getLogger(DefaultBusinessAgreementService.class);

	private static final String ERR_MESSAGE = "Error occured during creation of business agreement";

	private static final String BUAG_TEXT = "yB-yC BUAG";
	private static final String VALUE_X = "X";


	private static final String ID_BAPI = "BAPI";

	private static final String CHANGE_BA_DATAGNRL_BABR_TEXT = "BuAG with CC";
	private static final String CORRESP_VARIANT_TO_EBILL = "ZK01";
	private static final String CORRESP_VARIANT_STANDARD = "0001";
	private static final String CHANGE_BA_DATAGNRLX_BABR_TEXT = "X";


	final ChangeBusinessAgreementObjectFactory bAChangeObjectFactory = new ChangeBusinessAgreementObjectFactory();


	private ZCRMWSBUAGCREATE businessAgreementWebService;
	private ZCRMWSBUAGCHANGE businessAgreementChangeWebService;
	private ModelService modelService;


	@Override
	public String createBusinessAgreement(final CustomerModel customer, final CreditCardPaymentInfoModel cardModel)
	{
		LOG.info("Creating BuAG for customer " + customer.getUid() + " (" + customer.getYBillingCustomerId() + ") and card: "
				+ cardModel.getYBillingCardId());
		try
		{

			final ZcrmBuagCreateResponse response = getBusinessAgreementWebService()
					.zcrmBuagCreate(createZcrmBuagCreateRequest(customer, cardModel));


			final Bapiret2T results = response.getReturn();
			if (results != null && results.getItem() != null)
			{
				for (final Bapiret2 item : results.getItem())
				{
					if (YBillingResponseType.S.toString().equals(item.getType()) && ID_BAPI.equals(item.getId()))
					{
						final String businessAgreementId = item.getMessageV2();

						cardModel.setYBillingBusinessAgreementId(businessAgreementId);
						modelService.save(cardModel);

						LOG.info("Successfully created BuAG: " + businessAgreementId);
						return businessAgreementId;
					}
				}
			}

			throw new WebServiceException("No BuAG Id in response");


		}
		catch (final Exception ex)
		{
			LOG.error(ERR_MESSAGE, ex);
			//throw new WebServiceException(ERR_MESSAGE, ex);
			return null;
		}
	}

	private boolean isDefault(final CustomerModel customer, final CreditCardPaymentInfoModel card)
	{
		if (customer.getDefaultPaymentInfo() == null || card.getPk().equals(customer.getDefaultPaymentInfo().getPk()))
		{
			return true;
		}
		return false;

	}

	// CECS-583 SOAP:change Business Agreement START
	@Override
	public boolean changeBusinessAgreement(final CustomerModel customerModel, final CreditCardPaymentInfoModel paymentInfoModel,
			final boolean isBillingEpaperEnabled)
	{
		try
		{
			final ZcrmBuagChangeResponse response = getBusinessAgreementChangeWebService()
					.zcrmBuagChange(createZcrmBuagChangeRequest(customerModel, paymentInfoModel, isBillingEpaperEnabled));

			final TableOfBapiret2 results = response.getReturn();
			if (results != null && results.getItem() != null)
			{
				for (final Bapiret2 item : results.getItem())
				{
					if (YBillingResponseType.S.toString().equals(item.getType()) && ID_BAPI.equals(item.getId()))
					{
						return true;
					}
				}
			}

			throw new WebServiceException("Business agreement not changed ");

		}
		catch (final Exception ex)
		{
			LOG.error("Error occured during change of business agreement", ex);
			return false;
		}
	}
	// CECS-583 SOAP:change Business Agreement END

	private ZcrmBuagChange createZcrmBuagChangeRequest(final CustomerModel customerModel,
			final CreditCardPaymentInfoModel cardModel, final boolean isBillingEpaperEnabled)
	{

		final ZcrmBuagChange request = bAChangeObjectFactory.createZcrmBuagChange();

		request.setDatagnrl(createChangeBADatagnrl(isBillingEpaperEnabled));
		request.setDatagnrlx(createChangeBADatagnrlx());
		request.setDataspec(createChangeBADataspec(cardModel, isDefault(customerModel, cardModel)));
		request.setDataspecx(createChangeBapibus1006130SpecDataX());

		request.setBusinesspartner(customerModel.getYBillingCustomerId());
		request.setBusinessagreement(cardModel.getYBillingBusinessAgreementId());
		request.setReturn(bAChangeObjectFactory.createTableOfBapiret2());

		return request;

	}

	private ZcrmBuagCreate createZcrmBuagCreateRequest(final CustomerModel customer, final CreditCardPaymentInfoModel cardModel)
	{
		final ZcrmBuagCreate request = new ZcrmBuagCreate();

		request.setCsDatagnrlExp(createBapibus1006130GnrlDataExp());
		request.setCsDataspecExp(createBapibus1006130SpecDataExp(cardModel.getYBillingCardId(), isDefault(customer, cardModel)));
		request.setIvBuagPaymtype("03");
		request.setIvBuagUsage("00");
		request.setIvBupaId(customer.getYBillingCustomerId());
		request.setIvSave(VALUE_X);
		request.setReturn(new Bapiret2T());

		return request;
	}



	private Bapibus1006130GnrlData createChangeBADatagnrl(final boolean isBillingEpaperEnabled)
	{
		final Bapibus1006130GnrlData bapibus1006130GnrlData = bAChangeObjectFactory.createBapibus1006130GnrlData();

		bapibus1006130GnrlData.setBabrText(CHANGE_BA_DATAGNRL_BABR_TEXT);
		bapibus1006130GnrlData.setCorrespVariant(isBillingEpaperEnabled ? CORRESP_VARIANT_TO_EBILL : CORRESP_VARIANT_STANDARD);

		return bapibus1006130GnrlData;
	}

	private Bapibus1006130GnrlDataX createChangeBADatagnrlx()
	{
		final Bapibus1006130GnrlDataX bapibus1006130GnrlDataX = bAChangeObjectFactory.createBapibus1006130GnrlDataX();
		bapibus1006130GnrlDataX.setBabrText(CHANGE_BA_DATAGNRLX_BABR_TEXT);
		bapibus1006130GnrlDataX.setCorrespVariant(VALUE_X);

		return bapibus1006130GnrlDataX;
	}

	private Bapibus1006130SpecData createChangeBADataspec(final CreditCardPaymentInfoModel paymentInfoModel,
			final boolean isDefault)
	{
		final Bapibus1006130SpecData bapibus1006130SpecData = bAChangeObjectFactory.createBapibus1006130SpecData();
		if (isDefault)
		{
			bapibus1006130SpecData.setBuagDefault(VALUE_X);
		}

		return bapibus1006130SpecData;
	}

	private Bapibus1006130SpecDataX createChangeBapibus1006130SpecDataX()
	{
		final Bapibus1006130SpecDataX bapibus1006130SpecDataX = bAChangeObjectFactory.createBapibus1006130SpecDataX();
		bapibus1006130SpecDataX.setBuagDefault(VALUE_X);

		return bapibus1006130SpecDataX;
	}


	private Bapibus1006130SpecDataExp createBapibus1006130SpecDataExp(final String cardId, final boolean isDefault)
	{
		final Bapibus1006130SpecDataExp bapibus1006130SpecDataExp = new Bapibus1006130SpecDataExp();

		bapibus1006130SpecDataExp.setBuagText(BUAG_TEXT);
		if (isDefault)
		{
			bapibus1006130SpecDataExp.setBuagDefault(VALUE_X);
		}
		bapibus1006130SpecDataExp.setBuagClass("YN001");
		bapibus1006130SpecDataExp.setCreditCardInc(cardId);
		bapibus1006130SpecDataExp.setCreditCardOutg(cardId);

		return bapibus1006130SpecDataExp;
	}


	private Bapibus1006130GnrlDataExp createBapibus1006130GnrlDataExp()
	{
		final Bapibus1006130GnrlDataExp bapibus1006130GnrlDataExp = new Bapibus1006130GnrlDataExp();

		bapibus1006130GnrlDataExp.setBabrText(BUAG_TEXT);
		bapibus1006130GnrlDataExp.setMethodInc("K");
		bapibus1006130GnrlDataExp.setMethodOutg("6");
		bapibus1006130GnrlDataExp.setTofpaym("0001");
		bapibus1006130GnrlDataExp.setCorrespVariant(CORRESP_VARIANT_STANDARD);

		return bapibus1006130GnrlDataExp;
	}


	/**
	 * @return the businessAgreementWebService
	 */
	public ZCRMWSBUAGCREATE getBusinessAgreementWebService()
	{
		return businessAgreementWebService;
	}

	/**
	 * @param businessAgreementWebService
	 *           the businessAgreementWebService to set
	 */
	public void setBusinessAgreementWebService(final ZCRMWSBUAGCREATE businessAgreementWebService)
	{
		this.businessAgreementWebService = businessAgreementWebService;
	}

	/**
	 * @return the businessAgreementChangeWebService
	 */
	public ZCRMWSBUAGCHANGE getBusinessAgreementChangeWebService()
	{
		return businessAgreementChangeWebService;
	}

	/**
	 * @param businessAgreementChangeWebService
	 *           the businessAgreementChangeWebService to set
	 */
	public void setBusinessAgreementChangeWebService(final ZCRMWSBUAGCHANGE businessAgreementChangeWebService)
	{
		this.businessAgreementChangeWebService = businessAgreementChangeWebService;
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




}
