/**
 *
 */
package com.hybris.showcase.ybillingintegration.services.impl;

import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.configurablebundleservices.bundle.BundleTemplateService;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.configurablebundleservices.model.ChangeProductPriceBundleRuleModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.exceptions.SystemException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.time.TimeService;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.google.common.collect.Lists;
import com.hybris.showcase.guidedselling.model.BundlePackageModel;
import com.hybris.showcase.guidedselling.model.OrderChangesModel;
import com.hybris.showcase.ybillingintegration.data.YBillingCharacteristicData;
import com.hybris.showcase.ybillingintegration.data.YBillingContractDetailsData;
import com.hybris.showcase.ybillingintegration.data.YBillingOrderDetailsData;
import com.hybris.showcase.ybillingintegration.model.ProductConfigurationTemplateModel;
import com.hybris.showcase.ybillingintegration.model.TechnicalAttributeModel;
import com.hybris.showcase.ybillingintegration.services.YBillingOrderService;
import com.hybris.showcase.ybillingintegration.ssc.ConfigurationMappingService;
import com.hybris.showcase.ybillingintegration.strategy.YBillingTechAttrStrategy;
import com.sap.document.sap.soap.functions.mc_style.CrmtIsxConfigApi;
import com.sap.document.sap.soap.functions.mc_style.CrmtIsxConfigApiT;
import com.sap.document.sap.soap.functions.mc_style.CrmtIsxContractChangeRfc;
import com.sap.document.sap.soap.functions.mc_style.CrmtIsxControlApi;
import com.sap.document.sap.soap.functions.mc_style.CrmtIsxControlChangeApi;
import com.sap.document.sap.soap.functions.mc_style.CrmtIsxDatesApi;
import com.sap.document.sap.soap.functions.mc_style.CrmtIsxGenDataDcApi;
import com.sap.document.sap.soap.functions.mc_style.CrmtIsxItemConfigApi;
import com.sap.document.sap.soap.functions.mc_style.CrmtIsxOrgmanApi;
import com.sap.document.sap.soap.functions.mc_style.CrmtIsxPartnerApi;
import com.sap.document.sap.soap.functions.mc_style.CrmtIsxProcessAttribRfc;
import com.sap.document.sap.soap.functions.mc_style.CrmtIsxProcessAttribRfcT;
import com.sap.document.sap.soap.functions.mc_style.CrmtIsxProvItemDcApi;
import com.sap.document.sap.soap.functions.mc_style.CrmtIsxProvItemDcApiT;
import com.sap.document.sap.soap.functions.mc_style.CrmtIsxProviderCreateApi;
import com.sap.document.sap.soap.functions.mc_style.CrmtIsxTechResApi;
import com.sap.document.sap.soap.functions.mc_style.CrmtIsxTechResApiT;
import com.sap.document.sap.soap.functions.mc_style.CrmtReturnObjectsStruc;
import com.sap.document.sap.soap.functions.mc_style.ZCRMWSBUPACONTRACTHISTORY;
import com.sap.document.sap.soap.functions.mc_style.ZCRMWSBUPAORDHISTDETAILS;
import com.sap.document.sap.soap.functions.mc_style.ZCRMWSCONTRACTCHANGE;
import com.sap.document.sap.soap.functions.mc_style.ZCRMWSZORDERCREATE;
import com.sap.document.sap.soap.functions.mc_style.ZcrmBupaContractHistory;
import com.sap.document.sap.soap.functions.mc_style.ZcrmBupaContractHistoryResponse;
import com.sap.document.sap.soap.functions.mc_style.ZcrmBupaOrderHistDetails;
import com.sap.document.sap.soap.functions.mc_style.ZcrmBupaOrderHistDetailsResponse;
import com.sap.document.sap.soap.functions.mc_style.ZcrmIsxBtxApiContChange;
import com.sap.document.sap.soap.functions.mc_style.ZcrmIsxBtxApiContChangeResponse;
import com.sap.document.sap.soap.functions.mc_style.ZcrmIsxBtxApiOrderCreate;
import com.sap.document.sap.soap.functions.mc_style.ZcrmIsxBtxApiOrderCreateResponse;
import com.sap.document.sap.soap.functions.mc_style.ZcrmtContractHistory;
import com.sap.document.sap.soap.functions.mc_style.ZcrmtItemConfigWrk;
import com.sap.document.sap.soap.functions.mc_style.ZcrmtOrderHistDetails;
import com.sap.document.sap.soap.functions.mc_style.ZcrmtOrderItemConfigWrk;


/**
 * @author Sebastian Weiner
 */
public class DefaultYBillingOrderService implements YBillingOrderService
{

	private static final Logger LOG = Logger.getLogger(DefaultYBillingOrderService.class);
	private static final String PARSE_ERR_MESSAGE = "Can't parse String into date: ";

	public static final String VALUE_X = "X";
	public static final String VALUE_ITEM_HANDLE = "0000000010";
	public static final String VALUE_PARENT_ITEM_HANDLE = "0000000000";
	public static final String DEFAULT_EMPLOYEE = "Hybrisuser";
	public static final String DEFAULT_ORDER_PROCESS_VALUE = "ZCONFIG_CHANGE_CH";

	private static final Integer DEFAULT_BUNDLE_NO = 1;
	public static final String NO_END_DATE = "0000-00-00";
	public static final String NO_END_DATE_DEC = "00000000000000";
	public static final String CURRENCY = "CURRENCY";
	public static final String USD = "USD";

	private static final String DEFAULT_TZ_PROPERTY = "ybillingintegration.ws.timezone";
	private static final String CRM_DEFAULT_TZ = "CET";

	public static final String CONF_PACKAGE_KEY = "ybillingintegration.cstic.package";

	private ZCRMWSZORDERCREATE orderCreationWebService;
	private ZCRMWSCONTRACTCHANGE changeOrderWebService;
	private ZCRMWSBUPACONTRACTHISTORY orderHistoryWebService;
	private ZCRMWSBUPAORDHISTDETAILS orderDetailsWebService;
	private ConfigurationMappingService configurationMappingService;
	private ModelService modelService;
	private BundleTemplateService bundleTemplateService;
	private List<YBillingTechAttrStrategy> yBillingTechAttrStrategyList;
	private TimeService timeService;
	private CommonI18NService commonI18NService;
	private PriceDataFactory priceDataFactory;
	private ConfigurationService configurationService;

	@Override
	public String createOrder(final AbstractOrderModel order)
	{
		final List<AbstractOrderEntryModel> abstractOrderEntryModels = order.getEntries();

		if (order.getUser() instanceof CustomerModel)
		{
			final CustomerModel customerModel = (CustomerModel) (order.getUser());

			try
			{
				final ZcrmIsxBtxApiOrderCreate zcrmIsxBtxApiOrderCreate = createZcrmIsxBtxApiOrderCreate(abstractOrderEntryModels,
						customerModel, order.getContractStartDate());

				final ZcrmIsxBtxApiOrderCreateResponse response = getOrderCreationWebService()
						.zcrmIsxBtxApiOrderCreate(zcrmIsxBtxApiOrderCreate);

				final CrmtReturnObjectsStruc results = response.getEsCreatedOrder();

				String yBillingOrderId = null;

				if (results != null)
				{
					yBillingOrderId = results.getObjectId();
					order.setYBillingOrderId(yBillingOrderId);
					order.setYBillingContractId(results.getProviderContract());
					modelService.save(order);
					modelService.refresh(order);

					LOG.info("Created Order Id: " + yBillingOrderId);
				}

				return yBillingOrderId;
			}
			catch (final RuntimeException ex)
			{
				LOG.error("Error occured during creation of order.", ex);
				return null;
			}
		}
		else
		{
			LOG.error("Cart User is not a Customer: " + order.getUser().getName());
			return null;
		}
	}

	@Override
	public void changeOrder(final AbstractOrderModel order)
	{
		LOG.info("Changing order: " + order.getCode() + " (" + order.getYBillingOrderId() + ")");

		if (order.getUser() instanceof CustomerModel)
		{
			final OrderChangesModel orderChanges = order.getOrderChanges();
			final CustomerModel customerModel = (CustomerModel) (order.getUser());
			final String yBillingBusinessPartnerId = customerModel.getYBillingCustomerId();

			if (null != yBillingBusinessPartnerId && null != orderChanges)
			{

				final OrderModel changedOrder = orderChanges.getOrder();

				if (null != changedOrder.getYBillingContractId())
				{

					final YBillingContractDetailsData activeContractDetailsData = getActiveContractData(
							changedOrder.getYBillingContractId(), yBillingBusinessPartnerId);

					if (null != activeContractDetailsData && null != activeContractDetailsData.getYBillingOrderId())
					{
						final ZcrmIsxBtxApiContChange zcrmIsxBtxApiContChange = new ZcrmIsxBtxApiContChange();
						zcrmIsxBtxApiContChange.setIsChangeData(createIsChangeData(order));
						zcrmIsxBtxApiContChange.setIsControl(createIsControl());
						zcrmIsxBtxApiContChange.setIvItemNo(activeContractDetailsData.getContractVersion());
						zcrmIsxBtxApiContChange.setIvObjectId(changedOrder.getYBillingContractId());

						final ZcrmIsxBtxApiContChangeResponse results = getChangeOrderWebService()
								.zcrmIsxBtxApiContChange(zcrmIsxBtxApiContChange);

						if (results != null)
						{
							final String yBillingOrderId = results.getEsChangeOrder().getObjectId();
							order.setYBillingOrderId(yBillingOrderId);
							order.setYBillingContractId(changedOrder.getYBillingContractId());
							modelService.save(order);
							modelService.refresh(order);
						}
					}
					else
					{
						LOG.warn("Can't find an active order for the given contract id in YBILLING.");
					}
				}
				else
				{
					LOG.warn(
							"The is no connected order in ybilling for this changed order. No yBilling order id saved. The order change want be proceed.");
				}
			}
			else
			{
				LOG.warn("The user is no ybilling user. The order change want be proceed in ybilling.");
			}
		}
		else
		{
			LOG.warn("The user is not customer. The order change want be proceed.");
		}
	}

	@Override
	public List<YBillingContractDetailsData> getContractDetails(final String businessPartnerId)
	{
		if (null != businessPartnerId)
		{
			final ZcrmBupaContractHistory zcrmBupaContractHistory = new ZcrmBupaContractHistory();
			zcrmBupaContractHistory.setIvBusinesspartner(businessPartnerId);

			final List<YBillingContractDetailsData> contractDetailsDataList = createContractDetailsData(
					getOrderHistoryWebService().zcrmBupaContractHistory(zcrmBupaContractHistory));

			return contractDetailsDataList;
		}
		else
		{
			LOG.error("Can't get contract history without an BUSINESS PARTNER ID!");
			return null;
		}
	}

	@Override
	public List<YBillingOrderDetailsData> getOrderDetails(final String businessPartnerId)
	{
		LOG.info("Retrieving order details for BP: " + businessPartnerId);

		if (null != businessPartnerId)
		{
			final ZcrmBupaOrderHistDetails zcrmBupaOrderHistDetails = new ZcrmBupaOrderHistDetails();
			zcrmBupaOrderHistDetails.setIvBusinesspartner(businessPartnerId);

			final List<YBillingOrderDetailsData> orderDetailsDataList = createOrderDetailsData(
					getOrderDetailsWebService().zcrmBupaOrderHistDetails(zcrmBupaOrderHistDetails));

			return orderDetailsDataList;
		}
		else
		{
			LOG.error("Can't get order details without an BUSINESS PARTNER ID!");
			return null;
		}
	}

	private YBillingContractDetailsData getActiveContractData(final String contractId, final String buPaId)
	{
		LOG.info("Retrieving active contract data for contract: " + contractId);
		YBillingContractDetailsData result = null;

		final String trimedSearchedContractId = contractId.replaceAll("^0*", "");

		final List<YBillingContractDetailsData> contractDetails = getContractDetails(buPaId);

		for (final YBillingContractDetailsData entry : contractDetails)
		{
			final String trimedContractIdFromList = entry.getProviderContractId().replaceAll("^0*", "");

			if (entry.getActivationStatus() && trimedContractIdFromList.equals(trimedSearchedContractId))
			{
				result = entry;
				break;
			}
		}


		return result;
	}

	private List<YBillingOrderDetailsData> createOrderDetailsData(
			final ZcrmBupaOrderHistDetailsResponse zcrmBupaOrderHistDetailsResponse)
	{
		final List<YBillingOrderDetailsData> orderDetailsDataList = new ArrayList<>();
		final Map<String, List<YBillingCharacteristicData>> characteristicMap = new HashMap<>();

		for (final ZcrmtOrderItemConfigWrk zcrmtOrderItemConfigWrk : zcrmBupaOrderHistDetailsResponse.getEtConfig().getItem())
		{
			final YBillingCharacteristicData yBillingCharacteristicData = new YBillingCharacteristicData();
			yBillingCharacteristicData.setBundleTemplate(zcrmtOrderItemConfigWrk.getCharcTxt());
			yBillingCharacteristicData.setProductCode(zcrmtOrderItemConfigWrk.getValueTxt());
			yBillingCharacteristicData.setCharCode(zcrmtOrderItemConfigWrk.getCharc());

			// Remove leading '0'
			final String trimedOrderItemId = zcrmtOrderItemConfigWrk.getOrderItem().replaceAll("^0*", "");
			final String trimedOrderId = zcrmtOrderItemConfigWrk.getOrderId().replaceAll("^0*", "");

			if (characteristicMap.containsKey(trimedOrderId + trimedOrderItemId))
			{
				characteristicMap.get(trimedOrderId + trimedOrderItemId).add(yBillingCharacteristicData);
			}
			else
			{
				characteristicMap.put(trimedOrderId + trimedOrderItemId, Lists.newArrayList(yBillingCharacteristicData));
			}
		}

		final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		final DateFormat dcstf = new SimpleDateFormat("yyyyMMddHHmmss");

		for (final ZcrmtOrderHistDetails zcrmtOrderHistDetails : zcrmBupaOrderHistDetailsResponse.getEtOrderHistDetails().getItem())
		{

			final YBillingOrderDetailsData yBillingOrderDetailsData = new YBillingOrderDetailsData();

			try
			{
				if (!zcrmtOrderHistDetails.getOrderDate().equals(NO_END_DATE))
				{
					yBillingOrderDetailsData.setOrderDate(df.parse(zcrmtOrderHistDetails.getOrderDate()));
				}

				if (!zcrmtOrderHistDetails.getContractStartTerm().equals(NO_END_DATE))
				{
					yBillingOrderDetailsData.setContractStartTerm(dcstf.parse("" + zcrmtOrderHistDetails.getContractStartTerm()));
				}


			}
			catch (final ParseException e)
			{
				LOG.error(PARSE_ERR_MESSAGE + e.getStackTrace(), e);
			}

			yBillingOrderDetailsData.setYBillingOrderId(zcrmtOrderHistDetails.getOrderId());
			yBillingOrderDetailsData.setOrderItem(zcrmtOrderHistDetails.getOrderItem());
			yBillingOrderDetailsData.setProcessType(zcrmtOrderHistDetails.getProcessType());
			yBillingOrderDetailsData.setOrderType(zcrmtOrderHistDetails.getOrderType());
			yBillingOrderDetailsData.setOrderedProductCode(zcrmtOrderHistDetails.getProductDescription());
			yBillingOrderDetailsData.setOrderStatus(zcrmtOrderHistDetails.getOrderStatus());

			yBillingOrderDetailsData.setOneTimeNetValue(createPriceData(zcrmtOrderHistDetails.getOneTimeNetValue()));
			yBillingOrderDetailsData.setOneTimeTaxAmount(createPriceData(zcrmtOrderHistDetails.getOneTimeTaxAmount()));
			yBillingOrderDetailsData.setOneTimeGrossValue(createPriceData(zcrmtOrderHistDetails.getOneTimeGrossValue()));
			yBillingOrderDetailsData.setRecurringNetValue(createPriceData(zcrmtOrderHistDetails.getRecurringNetValue()));
			yBillingOrderDetailsData.setRecurringTaxAmount(createPriceData(zcrmtOrderHistDetails.getRecurringTaxAmount()));
			yBillingOrderDetailsData.setRecurringGrossValue(createPriceData(zcrmtOrderHistDetails.getRecurringGrossValue()));

			yBillingOrderDetailsData.setRecurringDuration(zcrmtOrderHistDetails.getRecurringDuration());
			yBillingOrderDetailsData.setRecurringTimeUnit(zcrmtOrderHistDetails.getRecurringTimeUnit());

			yBillingOrderDetailsData.setSoldTo(zcrmtOrderHistDetails.getSoldTo());
			yBillingOrderDetailsData.setSoldToAddress(zcrmtOrderHistDetails.getSoldToAddress());
			yBillingOrderDetailsData.setShipTo(zcrmtOrderHistDetails.getShipTo());
			yBillingOrderDetailsData.setShipToAddress(zcrmtOrderHistDetails.getShipToAddress());
			yBillingOrderDetailsData.setBillTo(zcrmtOrderHistDetails.getBillTo());
			yBillingOrderDetailsData.setBillToAddress(zcrmtOrderHistDetails.getBillToAddress());
			yBillingOrderDetailsData.setPayerTo(zcrmtOrderHistDetails.getPayer());
			yBillingOrderDetailsData.setPayerToAddress(zcrmtOrderHistDetails.getPayerAddress());

			yBillingOrderDetailsData.setContractStartTermTimeZone(zcrmtOrderHistDetails.getContractStartTermTimeZone());
			yBillingOrderDetailsData.setProviderContractId(zcrmtOrderHistDetails.getProviderContractId());

			// Remove leading '0'
			final String trimedOrderItemId = zcrmtOrderHistDetails.getOrderItem().replaceAll("^0*", "");
			final String trimedOrderId = zcrmtOrderHistDetails.getOrderId().replaceAll("^0*", "");

			yBillingOrderDetailsData.setChangesList(characteristicMap.get(trimedOrderId + trimedOrderItemId));
			orderDetailsDataList.add(yBillingOrderDetailsData);
		}

		return orderDetailsDataList;
	}

	private PriceData createPriceData(final BigDecimal amount)
	{
		final CurrencyModel currencyModel = getCommonI18NService().getCurrentCurrency();
		final PriceDataType priceDataType = PriceDataType.BUY;
		return getPriceDataFactory().create(priceDataType, amount, currencyModel);
	}

	private List<YBillingContractDetailsData> createContractDetailsData(
			final ZcrmBupaContractHistoryResponse zcrmBupaContractHistoryResponse)
	{
		final List<YBillingContractDetailsData> orderHistoryDataList = new ArrayList<>();
		final Map<String, List<YBillingCharacteristicData>> characteristicMap = new HashMap<>();

		for (final ZcrmtItemConfigWrk zcrmtItemConfigWrk : zcrmBupaContractHistoryResponse.getEtConfig().getItem())
		{
			final YBillingCharacteristicData yBillingCharacteristicData = new YBillingCharacteristicData();
			yBillingCharacteristicData.setBundleTemplate(zcrmtItemConfigWrk.getCharcTxt());
			yBillingCharacteristicData.setProductCode(zcrmtItemConfigWrk.getValueTxt());
			yBillingCharacteristicData.setCharCode(zcrmtItemConfigWrk.getCharc());

			// Remove leading '0'
			final String trimedContractVersionId = zcrmtItemConfigWrk.getContractVersion().replaceAll("^0*", "");
			final String trimedContractNoId = zcrmtItemConfigWrk.getContractNo().replaceAll("^0*", "");

			if (characteristicMap.containsKey(trimedContractNoId + trimedContractVersionId))
			{
				characteristicMap.get(trimedContractNoId + trimedContractVersionId).add(yBillingCharacteristicData);
			}
			else
			{
				characteristicMap.put(trimedContractNoId + trimedContractVersionId, Lists.newArrayList(yBillingCharacteristicData));
			}
		}

		final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		final DateFormat dcstf = new SimpleDateFormat("yyyyMMddHHmmss");

		for (final ZcrmtContractHistory zcrmtContractHistory : zcrmBupaContractHistoryResponse.getEtContractHistory().getItem())
		{

			final YBillingContractDetailsData yBillingContractDetailsData = new YBillingContractDetailsData();

			try
			{
				if (!zcrmtContractHistory.getContractVersionStartDate().equals(NO_END_DATE))
				{
					yBillingContractDetailsData
							.setContractVersionStartDate(df.parse(zcrmtContractHistory.getContractVersionStartDate()));
				}
				if (!zcrmtContractHistory.getContractVersionEndDate().equals(NO_END_DATE))
				{
					yBillingContractDetailsData.setContractVersionEndDate(df.parse(zcrmtContractHistory.getContractVersionEndDate()));
				}
				if (!zcrmtContractHistory.getOrderDate().equals(NO_END_DATE))
				{
					yBillingContractDetailsData.setOrderDate(df.parse(zcrmtContractHistory.getOrderDate()));
				}
				if (!zcrmtContractHistory.getContractStartTerm().equals(NO_END_DATE_DEC))
				{
					yBillingContractDetailsData.setContractStartDate(dcstf.parse("" + zcrmtContractHistory.getContractStartTerm()));
				}
				if (!zcrmtContractHistory.getContractEndTerm().equals(NO_END_DATE_DEC))
				{
					yBillingContractDetailsData.setContractEndDate(dcstf.parse("" + zcrmtContractHistory.getContractEndTerm()));
				}
			}
			catch (final ParseException e)
			{
				LOG.error(PARSE_ERR_MESSAGE + e.getStackTrace(), e);
			}

			yBillingContractDetailsData.setContractNumber(zcrmtContractHistory.getContractNumber());
			yBillingContractDetailsData.setContractVersion(zcrmtContractHistory.getContractVersion());
			yBillingContractDetailsData.setActivationStatus(BooleanUtils.toBoolean(zcrmtContractHistory.getActivationStatus()));
			yBillingContractDetailsData.setYBillingOrderId(zcrmtContractHistory.getOrderId());
			yBillingContractDetailsData.setProcessType(zcrmtContractHistory.getProcessType());
			yBillingContractDetailsData.setOrderedProductCode(zcrmtContractHistory.getOrderedProductDescription());
			yBillingContractDetailsData.setOrderStatus(zcrmtContractHistory.getContractItemStatus());
			yBillingContractDetailsData.setOrderType(zcrmtContractHistory.getOrderType());
			yBillingContractDetailsData.setContractStartTermTimeZone(zcrmtContractHistory.getContractStartTermTimeZone());
			yBillingContractDetailsData.setProviderContractId(zcrmtContractHistory.getProviderContractId());


			// Remove leading '0'
			final String trimedContractVersionId = zcrmtContractHistory.getContractVersion().replaceAll("^0*", "");
			final String trimedContractNoId = zcrmtContractHistory.getContractNumber().replaceAll("^0*", "");

			yBillingContractDetailsData.setChangesList(characteristicMap.get(trimedContractNoId + trimedContractVersionId));
			orderHistoryDataList.add(yBillingContractDetailsData);
		}

		return orderHistoryDataList;
	}

	private CrmtIsxControlChangeApi createIsControl()
	{
		final CrmtIsxControlChangeApi crmtIsxControlChangeApi = new CrmtIsxControlChangeApi();
		crmtIsxControlChangeApi.setSaveOrder(VALUE_X);
		crmtIsxControlChangeApi.setReleaseOrder(VALUE_X);
		crmtIsxControlChangeApi.setCommitAfterSave(VALUE_X);
		return crmtIsxControlChangeApi;
	}

	private CrmtIsxContractChangeRfc createIsChangeData(final AbstractOrderModel order)
	{
		final Date contractStartDate = order.getContractStartDate();

		final String timezone = configurationService.getConfiguration().getString(DEFAULT_TZ_PROPERTY, CRM_DEFAULT_TZ);
		final String dateString = getDateAsString(contractStartDate, null);

		final CrmtIsxContractChangeRfc crmtIsxContractChangeRfc = new CrmtIsxContractChangeRfc();
		crmtIsxContractChangeRfc.setProcess(DEFAULT_ORDER_PROCESS_VALUE);
		crmtIsxContractChangeRfc.setActivationDate(new BigDecimal(dateString));
		crmtIsxContractChangeRfc.setTimezone(timezone);

		crmtIsxContractChangeRfc.setProcessAttributes(createItems(order));

		return crmtIsxContractChangeRfc;
	}

	private List<ChangeProductPriceBundleRuleModel> getPriceRulesWithCsticOverrides(final AbstractOrderEntryModel entry)
	{
		if (entry.getMasterEntry() != null)
		{
			LOG.warn("Looks like it's not master entry!" + //
					" entry no:" + entry.getEntryNumber() + //
					" cart: " + entry.getOrder().getCode());
		}
		final List<ChangeProductPriceBundleRuleModel> priceRules = new ArrayList<>();

		if (entry.getPriceRule() != null)
		{
			priceRules.add(entry.getPriceRule());
		}

		for (final AbstractOrderEntryModel child : entry.getChildEntries())
		{
			if (child.getPriceRule() != null)
			{
				priceRules.add(child.getPriceRule());
			}
		}

		return priceRules.stream() //
				.filter(pr -> MapUtils.isNotEmpty(pr.getCsticValueOverrides())) //
				.collect(Collectors.toList());
	}

	private CrmtIsxProcessAttribRfcT createItems(final AbstractOrderModel order)
	{
		final CrmtIsxProcessAttribRfcT items = new CrmtIsxProcessAttribRfcT();

		final Collection<ProductModel> products = order.getOrderChanges().getProductsAdded();

		final List<AbstractOrderEntryModel> addedEntries = order.getEntries().stream() //
				.filter(e -> e.isAddedToExistingContract() && products.contains(e.getProduct())) //
				.collect(Collectors.toList());

		addItems(addedEntries, items);

		final Collection<ProductModel> productsToRemove = order.getOrderChanges().getProductsRemoved();

		addItemsToRemove(productsToRemove, items);

		return items;
	}

	private void addItemsToRemove(final Collection<ProductModel> productsToRemove, final CrmtIsxProcessAttribRfcT items)
	{
		for (final ProductModel product : productsToRemove)
		{
			if (StringUtils.isEmpty(product.getYBillingIdOnRemove()))
			{
				continue;
			}

			final CrmtIsxProcessAttribRfc item = new CrmtIsxProcessAttribRfc();
			item.setName(product.getCsticName());
			item.setValue(product.getYBillingIdOnRemove());

			items.getItem().add(item);

		}

	}


	private void addItems(final Collection<AbstractOrderEntryModel> entries, final CrmtIsxProcessAttribRfcT items)
	{
		for (final AbstractOrderEntryModel e : entries)
		{
			if (e.getBundleTemplate() == null)
			{
				continue;
			}

			// CECS-717 yBilling enhanced bundle rules
			final List<ChangeProductPriceBundleRuleModel> priceRules = getPriceRulesWithCsticOverrides(e);
			if (!priceRules.isEmpty())
			{
				for (final ChangeProductPriceBundleRuleModel priceRule : priceRules)
				{
					final Map<String, String> csticValueOverrides = priceRule.getCsticValueOverrides();
					for (final Map.Entry<String, String> me : csticValueOverrides.entrySet())
					{
						final CrmtIsxProcessAttribRfc item = new CrmtIsxProcessAttribRfc();
						item.setName(me.getKey());
						item.setValue(me.getValue());
						items.getItem().add(item);
					}
				}
			}
			else
			{
				final CrmtIsxProcessAttribRfc item = new CrmtIsxProcessAttribRfc();
				item.setName(getYBillingComponentId(e.getBundleTemplate(), e));
				item.setValue(getYBillingProductId(e));

				items.getItem().add(item);
			}
		}
	}


	private ZcrmIsxBtxApiOrderCreate createZcrmIsxBtxApiOrderCreate(final List<AbstractOrderEntryModel> abstractOrderEntryModels,
			final CustomerModel customerModel, final Date contractStartDate)
	{
		final ZcrmIsxBtxApiOrderCreate request = new ZcrmIsxBtxApiOrderCreate();

		request.setIsControl(createCrmtIsxControlApi());
		final CrmtIsxProviderCreateApi crmtIsxProviderCreateApi = createCrmtIsxProviderCreateApi(abstractOrderEntryModels,
				customerModel, contractStartDate);
		request.setIsOrderData(crmtIsxProviderCreateApi);
		return request;
	}


	private CrmtIsxProviderCreateApi createCrmtIsxProviderCreateApi(final List<AbstractOrderEntryModel> orderEntries,
			final CustomerModel customerModel, final Date contractStartDate)
	{
		final BundleTemplateModel rootBundleTemplate = getBundleTemplateService()
				.getRootBundleTemplate(orderEntries.get(0).getBundleTemplate());

		final ProductConfigurationTemplateModel mappingTemplate = getConfigurationMappingService()
				.getProductConfigurationTemplate(rootBundleTemplate);

		if (mappingTemplate == null)
		{
			throw new SystemException("Unable to find mapping template for bundle: " + rootBundleTemplate.getId() + " ver: "
					+ rootBundleTemplate.getVersion());
		}

		final CrmtIsxProviderCreateApi crmtIsxProviderCreateApi = new CrmtIsxProviderCreateApi();
		final CrmtIsxGenDataDcApi crmtIsxGenDataDcApi = createCrmtIsxGenDataDcApi(customerModel, mappingTemplate);
		crmtIsxProviderCreateApi.setGenData(crmtIsxGenDataDcApi);

		final Map<String, String> characteristicMap = new HashMap<>();

		for (final AbstractOrderEntryModel entry : orderEntries)
		{
			if (DEFAULT_BUNDLE_NO.equals(entry.getBundleNo()))
			{
				final BundleTemplateModel bundleTemplate = entry.getBundleTemplate();
				// CECS-717 yBilling enhanced bundle rules
				final List<ChangeProductPriceBundleRuleModel> priceRules = getPriceRulesWithCsticOverrides(entry);
				if (!priceRules.isEmpty())
				{
					for (final ChangeProductPriceBundleRuleModel priceRule : priceRules)
					{
						final Map<String, String> csticValueOverrides = priceRule.getCsticValueOverrides();
						for (final Map.Entry<String, String> me : csticValueOverrides.entrySet())
						{
							final CrmtIsxProcessAttribRfc pAttr = new CrmtIsxProcessAttribRfc();
							pAttr.setName(me.getKey());
							pAttr.setValue(me.getValue());
							characteristicMap.put(me.getKey(), me.getValue());
						}
					}
				}
				else
				{
					characteristicMap.put(getYBillingComponentId(bundleTemplate, entry), getYBillingProductId(entry));
				}
			}
		}
		if (orderEntries.size() > 0)
		{
			final Configuration yConfig = configurationService.getConfiguration();
			final String packageCsticId = yConfig.getString(CONF_PACKAGE_KEY);

			final BundlePackageModel bundlePackage = orderEntries.get(0).getPackage();
			characteristicMap.put(packageCsticId, bundlePackage.getYBillingId());
		}

		characteristicMap.put(CURRENCY, USD);

		final CrmtIsxProvItemDcApiT crmtIsxProvItemDcApiT = createCrmtIsxProvItemDcApiT(mappingTemplate, characteristicMap,
				customerModel.getYBillingCustomerId(), contractStartDate);

		crmtIsxProviderCreateApi.setItems(crmtIsxProvItemDcApiT);

		return crmtIsxProviderCreateApi;


	}

	private String getYBillingComponentId(final BundleTemplateModel bundleTemplate, final AbstractOrderEntryModel entry)
	{
		if (StringUtils.isNotBlank(entry.getProduct().getCsticName()))
		{
			return entry.getProduct().getCsticName();
		}
		else if (StringUtils.isNotBlank(bundleTemplate.getYBillingId()))
		{
			return bundleTemplate.getYBillingId();
		}
		return bundleTemplate.getId();
	}

	private String getYBillingProductId(final AbstractOrderEntryModel entry)
	{
		if (StringUtils.isNotBlank(entry.getProduct().getYBillingId()))
		{
			return entry.getProduct().getYBillingId();
		}
		return entry.getProduct().getCode();
	}

	private String getYBillingOnRemoveProductId(final AbstractOrderEntryModel entry)
	{
		if (StringUtils.isNotBlank(entry.getProduct().getYBillingIdOnRemove()))
		{
			return entry.getProduct().getYBillingIdOnRemove();
		}
		return null;
	}

	private String getYBillingTemplate(final ProductConfigurationTemplateModel productConfigurationTemplateModel)
	{
		if (StringUtils.isNotBlank(productConfigurationTemplateModel.getYBillingId()))
		{
			return productConfigurationTemplateModel.getYBillingId();
		}
		return productConfigurationTemplateModel.getCode();
	}

	private CrmtIsxProvItemDcApiT createCrmtIsxProvItemDcApiT(final ProductConfigurationTemplateModel template,
			final Map<String, String> characteristicMap, final String yBillingBpId, final Date contractStartDate)
	{
		final CrmtIsxProvItemDcApiT crmtIsxProvItemDcApiT = new CrmtIsxProvItemDcApiT();

		final List<CrmtIsxProvItemDcApi> crmtIsxProvItemDcApis = new ArrayList<>();

		final CrmtIsxProvItemDcApi item = new CrmtIsxProvItemDcApi();

		item.setItemHandle(VALUE_ITEM_HANDLE);
		item.setParentItemHandle(VALUE_PARENT_ITEM_HANDLE);
		item.setProductId(getYBillingTemplate(template));

		final Collection<TechnicalAttributeModel> techAttrs = template.getTechnicalAttributes();

		item.setItemConfig(createCrmtIsxItemConfigApi(techAttrs, yBillingBpId, characteristicMap));
		item.setTimeframe(createTimeframe(template, contractStartDate));

		crmtIsxProvItemDcApis.add(item);

		crmtIsxProvItemDcApiT.getItem().addAll(crmtIsxProvItemDcApis);

		return crmtIsxProvItemDcApiT;
	}


	private CrmtIsxItemConfigApi createCrmtIsxItemConfigApi(final Collection<TechnicalAttributeModel> techAttrs,
			final String yBillingBpId, final Map<String, String> characteristicMap)
	{
		final CrmtIsxItemConfigApi crmtIsxItemConfigApi = new CrmtIsxItemConfigApi();

		crmtIsxItemConfigApi.setConfig(createCrmtIsxConfigApiT(characteristicMap));

		int slotNumber = 1;
		final CrmtIsxTechResApiT crmtIsxTechResApiT = new CrmtIsxTechResApiT();

		for (final TechnicalAttributeModel techAttr : techAttrs)
		{
			final String stringSlotNumber = String.format("%03d", slotNumber);

			final CrmtIsxTechResApi crmtIsxTechResApi = new CrmtIsxTechResApi();

			getyBillingTechAttrStrategyList().forEach(yBillingTechAttrStrategy -> yBillingTechAttrStrategy.process(crmtIsxTechResApi,
					techAttr, stringSlotNumber, yBillingBpId));

			if (null != crmtIsxTechResApi.getTrObjId())
			{
				crmtIsxTechResApiT.getItem().add(crmtIsxTechResApi);
				slotNumber++;
			}
		}

		crmtIsxItemConfigApi.setTechRes(crmtIsxTechResApiT);

		return crmtIsxItemConfigApi;
	}

	private CrmtIsxConfigApiT createCrmtIsxConfigApiT(final Map<String, String> characteristicMap)
	{

		final CrmtIsxConfigApiT crmtIsxConfigApiT = new CrmtIsxConfigApiT();

		for (final Map.Entry<String, String> characteristicEntry : characteristicMap.entrySet())
		{
			final CrmtIsxConfigApi crmtIsxConfigApi = new CrmtIsxConfigApi();

			crmtIsxConfigApi.setCharacteristic(characteristicEntry.getKey());
			crmtIsxConfigApi.setValue(characteristicEntry.getValue());

			crmtIsxConfigApiT.getItem().add(crmtIsxConfigApi);
		}

		return crmtIsxConfigApiT;
	}

	private CrmtIsxDatesApi createTimeframe(final ProductConfigurationTemplateModel template, final Date contractStartDate)
	{
		final String timezone = configurationService.getConfiguration().getString(DEFAULT_TZ_PROPERTY, CRM_DEFAULT_TZ);
		final String dateString = getDateAsString(contractStartDate, null);

		final CrmtIsxDatesApi crmtIsxDatesApi = new CrmtIsxDatesApi();
		crmtIsxDatesApi.setContractStart(new BigDecimal(dateString));
		crmtIsxDatesApi.setContractDuration(new BigDecimal(template.getContractDuration()));
		crmtIsxDatesApi.setTimezone(timezone);
		crmtIsxDatesApi.setDurationUnit(template.getPeriodUnit());
		return crmtIsxDatesApi;
	}

	private String getDateAsString(final Date date, final TimeZone tz)
	{
		final SimpleDateFormat dateSdf = new SimpleDateFormat("yyyyMMddHHmmss");
		if (tz != null)
		{
			dateSdf.setTimeZone(tz);
		}
		final String formatedDate = dateSdf.format(date);

		final String result = formatedDate.substring(0, 12).concat("00");

		return result;
	}

	private CrmtIsxGenDataDcApi createCrmtIsxGenDataDcApi(final CustomerModel customerModel,
			final ProductConfigurationTemplateModel template)
	{
		if (StringUtils.isBlank(customerModel.getYBillingCustomerId()))
		{
			throw new IllegalArgumentException("No BP for customer: " + customerModel.getUid());
		}

		final CrmtIsxGenDataDcApi crmtIsxGenDataDcApi = new CrmtIsxGenDataDcApi();

		final CrmtIsxPartnerApi crmtIsxPartnerApi = new CrmtIsxPartnerApi();
		crmtIsxPartnerApi.setPartnerNo(customerModel.getYBillingCustomerId());
		crmtIsxGenDataDcApi.setSoldToParty(crmtIsxPartnerApi);

		crmtIsxGenDataDcApi.setEmployee(DEFAULT_EMPLOYEE);

		final CrmtIsxOrgmanApi crmtIsxOrgmanApi = new CrmtIsxOrgmanApi();
		crmtIsxOrgmanApi.setSalesOrg(template.getSalesOrganization());
		crmtIsxOrgmanApi.setDisChannel(template.getDistributionChannel());
		crmtIsxGenDataDcApi.setOrgman(crmtIsxOrgmanApi);

		return crmtIsxGenDataDcApi;
	}


	private CrmtIsxControlApi createCrmtIsxControlApi()
	{
		final CrmtIsxControlApi crmtIsxControlApi = new CrmtIsxControlApi();

		crmtIsxControlApi.setSubmitOrder(VALUE_X);
		crmtIsxControlApi.setSaveOrder(VALUE_X);
		crmtIsxControlApi.setCommitAfterSave(VALUE_X);
		crmtIsxControlApi.setSaveWithErrors(VALUE_X);
		crmtIsxControlApi.setNoBdocSend(VALUE_X);
		crmtIsxControlApi.setNoTerminationWhenError(VALUE_X);

		return crmtIsxControlApi;
	}

	public ZCRMWSZORDERCREATE getOrderCreationWebService()
	{
		return orderCreationWebService;
	}

	@Required
	public void setOrderCreationWebService(final ZCRMWSZORDERCREATE orderCreationWebService)
	{
		this.orderCreationWebService = orderCreationWebService;
	}

	public ConfigurationMappingService getConfigurationMappingService()
	{
		return configurationMappingService;
	}

	@Required
	public void setConfigurationMappingService(final ConfigurationMappingService configurationMappingService)
	{
		this.configurationMappingService = configurationMappingService;
	}

	public List<YBillingTechAttrStrategy> getyBillingTechAttrStrategyList()
	{
		return yBillingTechAttrStrategyList;
	}

	@Required
	public void setyBillingTechAttrStrategyList(final List<YBillingTechAttrStrategy> yBillingTechAttrStrategyList)
	{
		this.yBillingTechAttrStrategyList = yBillingTechAttrStrategyList;
	}

	public ZCRMWSCONTRACTCHANGE getChangeOrderWebService()
	{
		return changeOrderWebService;
	}

	@Required
	public void setChangeOrderWebService(final ZCRMWSCONTRACTCHANGE changeOrderWebService)
	{
		this.changeOrderWebService = changeOrderWebService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	public ZCRMWSBUPACONTRACTHISTORY getOrderHistoryWebService()
	{
		return orderHistoryWebService;
	}

	@Required
	public void setOrderHistoryWebService(final ZCRMWSBUPACONTRACTHISTORY orderHistoryWebService)
	{
		this.orderHistoryWebService = orderHistoryWebService;
	}

	public ZCRMWSBUPAORDHISTDETAILS getOrderDetailsWebService()
	{
		return orderDetailsWebService;
	}

	@Required
	public void setOrderDetailsWebService(final ZCRMWSBUPAORDHISTDETAILS orderDetailsWebService)
	{
		this.orderDetailsWebService = orderDetailsWebService;
	}

	public BundleTemplateService getBundleTemplateService()
	{
		return bundleTemplateService;
	}

	@Required
	public void setBundleTemplateService(final BundleTemplateService bundleTemplateService)
	{
		this.bundleTemplateService = bundleTemplateService;
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


	public PriceDataFactory getPriceDataFactory()
	{
		return priceDataFactory;
	}

	@Required
	public void setPriceDataFactory(final PriceDataFactory priceDataFactory)
	{
		this.priceDataFactory = priceDataFactory;
	}

	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	@Required
	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}

}
