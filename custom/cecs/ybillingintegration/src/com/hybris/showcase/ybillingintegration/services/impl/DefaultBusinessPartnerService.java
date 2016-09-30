/**
 *
 */
package com.hybris.showcase.ybillingintegration.services.impl;

import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.strategies.CustomerNameStrategy;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.xml.ws.WebServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.ybillingintegration.services.BusinessPartnerService;
import com.sap.document.sap.soap.functions.mc_style.AddBillingDataObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.AddPaymentCardToBusinessPartnerObjectFactory;
import com.sap.document.sap.soap.functions.mc_style.Bapiadsmtp;
import com.sap.document.sap.soap.functions.mc_style.Bapibus1006030Pricing;
import com.sap.document.sap.soap.functions.mc_style.Bapibus1006Address;
import com.sap.document.sap.soap.functions.mc_style.Bapibus1006Central;
import com.sap.document.sap.soap.functions.mc_style.Bapibus1006CentralPerson;
import com.sap.document.sap.soap.functions.mc_style.Bapibus1006PcardData;
import com.sap.document.sap.soap.functions.mc_style.Bapibus1006SalesArea;
import com.sap.document.sap.soap.functions.mc_style.TableOfBapiadsmtp;
import com.sap.document.sap.soap.functions.mc_style.ZBAPIBUPAZCREATEFROMDATA;
import com.sap.document.sap.soap.functions.mc_style.ZCRMWSBAPIBUPAFRG0030ADD;
import com.sap.document.sap.soap.functions.mc_style.ZCRMWSBUPAPCARDADD;
import com.sap.document.sap.soap.functions.mc_style.ZbapiBupaCreateFromData;
import com.sap.document.sap.soap.functions.mc_style.ZbapiBupaCreateFromDataResponse;
import com.sap.document.sap.soap.functions.mc_style.ZbapiBupaFrg0030Add;
import com.sap.document.sap.soap.functions.mc_style.ZbapiBupaPcardAdd;
import com.sap.document.sap.soap.functions.mc_style.ZbapiBupaPcardAddResponse;


/**
 * @author Rafal Zymla
 */
public class DefaultBusinessPartnerService implements BusinessPartnerService
{

	private static final Logger LOG = Logger.getLogger(DefaultBusinessPartnerService.class);

	public static final String VALUE_X = "X";

	public static final String ERR_MESSAGE = "Error occured during creation of business partner";

	private ZBAPIBUPAZCREATEFROMDATA businessPartnerWebService;

	private ZCRMWSBUPAPCARDADD paymentCardAddWebService;

	private ZCRMWSBAPIBUPAFRG0030ADD customerAddBillingDataWebService;

	private CustomerAccountService customerAccountService;
	private ModelService modelService;
	private CustomerNameStrategy customerNameStrategy;

	private AddPaymentCardToBusinessPartnerObjectFactory addPCardObjectFactory = new AddPaymentCardToBusinessPartnerObjectFactory();
	private final AddBillingDataObjectFactory addBiilingDataObjectFactory = new AddBillingDataObjectFactory();


	@Override
	public String createBusinessParter(final CustomerModel customer)
	{
		LOG.info("Creating BP for customer: " + customer.getUid());
		try
		{
			final ZbapiBupaCreateFromDataResponse response = getBusinessPartnerWebService()
					.zbapiBupaCreateFromData(createZbapiBupaCreateFromDataRequest(customer));

			final String businessPartnerNo = response.getBusinesspartner();

			if (StringUtils.isBlank(businessPartnerNo))
			{
				throw new WebServiceException("No BP Id in webservice response");
			}

			final String currencyIso = customer.getSessionCurrency() != null ? customer.getSessionCurrency().getIsocode() : "EUR";

			LOG.info("Adding billing data to BP: " + businessPartnerNo);
			getCustomerAddBillingDataWebService().zbapiBupaFrg0030Add(createAddBillingDataRequest(businessPartnerNo, currencyIso));

			customer.setYBillingCustomerId(businessPartnerNo);
			modelService.save(customer);

			LOG.info("Successfully created BP " + businessPartnerNo + " for customer: " + customer.getUid());

			return businessPartnerNo;
		}
		catch (final RuntimeException ex)
		{
			LOG.error(ERR_MESSAGE, ex);
			return null;
		}
	}

	@Override
	public String addPaymentCard(final CustomerModel customer, final CreditCardPaymentInfoModel cardModel,
			final String nonMaskedCardNo)
	{
		LOG.info("Adding Payment Card " + cardModel.getNumber() + " to: " + customer.getUid() + " ("
				+ customer.getYBillingCustomerId() + ")");
		try
		{
			final ZbapiBupaPcardAddResponse response = paymentCardAddWebService
					.zbapiBupaPcardAdd(createRequest(customer, cardModel, nonMaskedCardNo));

			if (StringUtils.isBlank(response.getCardIdOut()))
			{
				throw new WebServiceException("No card id in WS response");
			}

			if (LOG.isDebugEnabled())
			{
				LOG.debug("Payment Card ID is: " + response.getCardIdOut());
			}
			cardModel.setYBillingCardId(response.getCardIdOut());
			modelService.save(cardModel);

			LOG.info("Successfully add Payment Card: " + response.getCardIdOut() + " to BP " + customer.getYBillingCustomerId());
			return response.getCardIdOut();
		}
		catch (final Exception ex)
		{
			LOG.error(ERR_MESSAGE, ex);
			//throw new WebServiceException(ERR_MESSAGE, ex);
			return null;
		}
	}

	private ZbapiBupaFrg0030Add createAddBillingDataRequest(final String businessPartnerNo, final String currencyIso)
	{
		final ZbapiBupaFrg0030Add request = addBiilingDataObjectFactory.createZbapiBupaFrg0030Add();
		request.setBusinesspartner(businessPartnerNo);
		request.setData(createPricingData(currencyIso));
		request.setReturn(addBiilingDataObjectFactory.createTableOfBapiret2());
		request.setSalesArea(createSalesArea());


		return request;
	}

	private Bapibus1006030Pricing createPricingData(final String currencyIso)
	{
		final Bapibus1006030Pricing pricingData = addBiilingDataObjectFactory.createBapibus1006030Pricing();

		pricingData.setCustPricProc("Z");
		pricingData.setCurrencyIso(currencyIso);
		pricingData.setCurrency(currencyIso);
		return pricingData;
	}

	private Bapibus1006SalesArea createSalesArea()
	{
		final Bapibus1006SalesArea salesArea = addBiilingDataObjectFactory.createBapibus1006SalesArea();
		salesArea.setDivision("10");
		salesArea.setDistributionChannel("10");
		salesArea.setSalesOrganization("O 50000608");

		return salesArea;
	}



	private ZbapiBupaPcardAdd createRequest(final CustomerModel customerModel, final CreditCardPaymentInfoModel cardModel,
			final String nonMaskedCardNo)
	{
		final ZbapiBupaPcardAdd request = addPCardObjectFactory.createZbapiBupaPcardAdd();
		request.setBusinesspartner(customerModel.getYBillingCustomerId());

		final Bapibus1006PcardData cardData = addPCardObjectFactory.createBapibus1006PcardData();
		cardData.setCardNumber(nonMaskedCardNo);
		cardData.setCardType("Y001");
		cardData.setCreditcardname(cardModel.getCcOwner());
		request.setData(cardData);
		request.setReturn(addPCardObjectFactory.createTableOfBapiret2());
		return request;
	}


	private ZbapiBupaCreateFromData createZbapiBupaCreateFromDataRequest(final CustomerModel customerModel)
	{
		final ZbapiBupaCreateFromData request = new ZbapiBupaCreateFromData();

		final List<AddressModel> addressModels = customerAccountService.getAllAddressEntries(customerModel);

		if (!addressModels.isEmpty())
		{
			request.setAddressdata(createBapibus1006Address(addressModels.get(0)));
		}

		request.setAcceptError(VALUE_X);
		request.setCentraldata(createBapibus1006Central(customerModel));
		request.setCentraldataperson(createBapibus1006CentralPerson(customerModel));
		request.setEMaildata(createTableOfBapiadsmtp(customerModel));
		request.setPartnercategory("1");
		request.setPartnergroup("YN01");

		return request;
	}

	private Bapibus1006Address createBapibus1006Address(final AddressModel addressModel)
	{
		if (addressModel == null)
		{
			return new Bapibus1006Address();
		}

		final Bapibus1006Address address = new Bapibus1006Address();
		address.setStandardaddress(VALUE_X);
		address.setCity(addressModel.getTown());
		address.setPostlCod1(addressModel.getPostalcode());
		address.setStreet(addressModel.getStreetname());
		address.setHouseNo(addressModel.getStreetnumber());
		address.setDistrict(addressModel.getDepartment());
		final String region = addressModel.getRegion() != null ? parseRegion(addressModel.getRegion().getIsocode()) : "NY";
		address.setRegion(region);
		final String countryIso = addressModel.getCountry() != null ? addressModel.getCountry().getIsocode() : "US";
		address.setCountry(countryIso);

		return address;
	}

	/**
	 * Region is in country-region form but webservice required only region so if you have US-NY it returns NY
	 *
	 * @param regionIso
	 * @return
	 */
	private String parseRegion(final String regionIso)
	{
		if (StringUtils.isNotEmpty(regionIso))
		{
			final String[] splitted = regionIso.split("-");
			if (splitted.length > 1)
			{
				return splitted[1];
			}
		}
		return null;
	}

	private Bapibus1006CentralPerson createBapibus1006CentralPerson(final CustomerModel customerModel)
	{
		final Bapibus1006CentralPerson customer = new Bapibus1006CentralPerson();


		final String[] names = getCustomerNameStrategy().splitName(customerModel.getName());
		if (names != null && names.length > 0)
		{
			if (names.length > 1)
			{
				customer.setFirstname(names[0]);
				customer.setLastname(names[1]);
			}
			else
			{
				customer.setLastname(names[0]);
			}
		}

		return customer;
	}

	private TableOfBapiadsmtp createTableOfBapiadsmtp(final CustomerModel customerModel)
	{
		final TableOfBapiadsmtp emails = new TableOfBapiadsmtp();
		final Bapiadsmtp email = new Bapiadsmtp();
		email.setEMail(customerModel.getContactEmail());
		email.setStdNo(VALUE_X);
		emails.getItem().add(email);

		return emails;

	}

	private Bapibus1006Central createBapibus1006Central(final CustomerModel customerModel)
	{
		final Bapibus1006Central centralData = new Bapibus1006Central();

		final String[] names = getCustomerNameStrategy().splitName(customerModel.getName());
		if (names != null && names.length > 0)
		{
			if (names.length > 1)
			{
				centralData.setSearchterm1(names[0]);
				centralData.setSearchterm2(names[1]);
			}
			else
			{
				centralData.setSearchterm1(names[0]);
			}
		}


		return centralData;
	}



	public ZBAPIBUPAZCREATEFROMDATA getBusinessPartnerWebService()
	{
		return businessPartnerWebService;
	}


	@Required
	public void setBusinessPartnerWebService(final ZBAPIBUPAZCREATEFROMDATA businessPartnerWebService)
	{
		this.businessPartnerWebService = businessPartnerWebService;
	}



	/**
	 * @return the customerAccountService
	 */
	public CustomerAccountService getCustomerAccountService()
	{
		return customerAccountService;
	}



	@Required
	public void setCustomerAccountService(final CustomerAccountService customerAccountService)
	{
		this.customerAccountService = customerAccountService;
	}



	/**
	 * @return the customerNameStrategy
	 */
	public CustomerNameStrategy getCustomerNameStrategy()
	{
		return customerNameStrategy;
	}



	@Required
	public void setCustomerNameStrategy(final CustomerNameStrategy customerNameStrategy)
	{
		this.customerNameStrategy = customerNameStrategy;
	}

	/**
	 * @return the paymentCardAddWebService
	 */
	public ZCRMWSBUPAPCARDADD getPaymentCardAddWebService()
	{
		return paymentCardAddWebService;
	}

	/**
	 * @param paymentCardAddWebService
	 *           the paymentCardAddWebService to set
	 */
	public void setPaymentCardAddWebService(final ZCRMWSBUPAPCARDADD paymentCardAddWebService)
	{
		this.paymentCardAddWebService = paymentCardAddWebService;
	}

	/**
	 * @return the addPCardObjectFactory
	 */
	public AddPaymentCardToBusinessPartnerObjectFactory getAddPCardObjectFactory()
	{
		return addPCardObjectFactory;
	}

	/**
	 * @param addPCardObjectFactory
	 *           the addPCardObjectFactory to set
	 */
	public void setAddPCardObjectFactory(final AddPaymentCardToBusinessPartnerObjectFactory addPCardObjectFactory)
	{
		this.addPCardObjectFactory = addPCardObjectFactory;
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

	/**
	 * @return the customerAddBillingDataWebService
	 */
	public ZCRMWSBAPIBUPAFRG0030ADD getCustomerAddBillingDataWebService()
	{
		return customerAddBillingDataWebService;
	}

	@Required
	public void setCustomerAddBillingDataWebService(final ZCRMWSBAPIBUPAFRG0030ADD customerAddBillingDataWebService)
	{
		this.customerAddBillingDataWebService = customerAddBillingDataWebService;
	}







}
