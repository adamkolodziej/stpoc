package com.hybris.showcase.guidedselling.populators;

import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.OrderFacade;
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.subscriptionfacades.data.BillingTimeData;
import de.hybris.platform.subscriptionfacades.data.OrderEntryPriceData;
import de.hybris.platform.subscriptionfacades.data.OrderPriceData;
import de.hybris.platform.subscriptionservices.model.BillingTimeModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.guidedselling.constants.GuidedsellingConstants;
import com.hybris.showcase.guidedselling.data.BundleOfferData;
import com.hybris.showcase.guidedselling.data.BundleOfferPopulatorParameters;
import com.hybris.showcase.guidedselling.data.GuidedSellingTotalsEntry;
import com.hybris.showcase.services.ShowcasecommonCalculationService;


/**
 * Created by miroslaw.szot@sap.com on 2015-03-13.
 */
public class BundleTotalsPopulator implements Populator<BundleOfferPopulatorParameters, BundleOfferData>
{
	private PriceDataFactory priceDataFactory;
	private Converter<BillingTimeModel, BillingTimeData> billingTimeConverter;
	private CommonI18NService commonI18NService;
	@Resource(name = "showcasecommonCalculationService")
	ShowcasecommonCalculationService showcasecommonCalculationService;

	@Override
	public void populate(final BundleOfferPopulatorParameters parameters, final BundleOfferData offerData)
			throws ConversionException
	{
		List<OrderPriceData> orderPrices;
		final List<OrderEntryData> orderEntries;
		final AbstractOrderModel order = parameters.getOrder();
		if (order instanceof CartModel)
		{
			orderPrices = getCartFacade().getSessionCart().getOrderPrices();
		}
		else
		{
			final AbstractOrderData orderData = getOrderFacade().getOrderDetailsForCode(order.getCode());
			orderPrices = orderData.getOrderPrices();
		}

		final Map<String, AbstractOrderModel> billingTimeOrderMap = new HashMap<>();
		billingTimeOrderMap.put(order.getBillingTime().getCode(), order);
		for (final AbstractOrderModel child : order.getChildren())
		{
			billingTimeOrderMap.put(child.getBillingTime().getCode(), child);
		}

		for (final OrderPriceData orderPrice : orderPrices)
		{
			final List<GuidedSellingTotalsEntry> totalsEntries = new ArrayList<>();

			final String billingTimeCode = orderPrice.getBillingTime().getCode();
			final AbstractOrderModel orderModel = billingTimeOrderMap.get(billingTimeCode);

			for (final AbstractOrderEntryModel orderEntry : orderModel.getEntries())
			{
				if (!orderEntry.isLoyaltyPayment() && orderEntry.getBasePrice().intValue() == 0)// still not perfect
				{
					continue;
				}
				if (orderEntry.isLoyaltyPayment() && orderEntry.getLoyaltyPoints() != null
						&& orderEntry.getLoyaltyPoints().doubleValue() == 0.0d)
				{
					continue;
				}

				final GuidedSellingTotalsEntry totalsEntry = new GuidedSellingTotalsEntry();
				totalsEntry.setProductCode(orderEntry.getProduct().getCode());
				totalsEntry.setProductName(orderEntry.getProduct().getName());

				OrderEntryPriceData orderEntryPrice;
				if (orderEntry.isLoyaltyPayment())
				{
					orderEntryPrice = createOrderEntryLoyaltyPointsDataForEntry(orderEntry);
				}
				else
				{
					orderEntryPrice = createOrderEntryPriceDataForEntry(orderEntry);
				}
				totalsEntry.setTotalPrice(orderEntryPrice.getTotalPrice());

				if (orderEntry.getMasterEntry() == null)
				{
					totalsEntry.setAddedToExistingContract(orderEntry.isAddedToExistingContract());
				}
				else
				{
					totalsEntry.setAddedToExistingContract(orderEntry.getMasterEntry().isAddedToExistingContract());
				}

				totalsEntries.add(totalsEntry);
			}
			orderPrice.setTotalsEntries(totalsEntries);
		}

		offerData.setOrderPrices(orderPrices);
	}


	protected OrderEntryPriceData createOrderEntryPriceDataForEntry(final AbstractOrderEntryModel entry)
	{
		final OrderEntryPriceData orderEntryPrice = new OrderEntryPriceData();
		orderEntryPrice.setTotalPrice(createPrice(entry, entry.getTotalPrice()));
		orderEntryPrice.setBasePrice(createPrice(entry, entry.getBasePrice()));
		final BillingTimeData billingTime = getBillingTimeConverter().convert(entry.getOrder().getBillingTime());
		orderEntryPrice.setBillingTime(billingTime);

		return orderEntryPrice;
	}

	protected OrderEntryPriceData createOrderEntryLoyaltyPointsDataForEntry(final AbstractOrderEntryModel entry)
	{
		final OrderEntryPriceData orderEntryPrice = new OrderEntryPriceData();
		final PriceData loyaltyPoints = createLoyaltyPointsPrice(entry, entry.getLoyaltyPoints());

		orderEntryPrice.setTotalPrice(loyaltyPoints);
		orderEntryPrice.setBasePrice(loyaltyPoints);
		final BillingTimeData billingTime = getBillingTimeConverter().convert(entry.getOrder().getBillingTime());
		orderEntryPrice.setBillingTime(billingTime);

		return orderEntryPrice;
	}

	protected PriceData createPrice(final AbstractOrderEntryModel orderEntry, final Double val)
	{
		return getPriceDataFactory().create(
				PriceDataType.BUY, new BigDecimal(getShowcasecommonCalculationService()
						.convertAmountToDefaultCurrency(val.doubleValue(), orderEntry.getOrder().getCurrency().getIsocode())),
				getCommonI18NService().getCurrentCurrency().getIsocode());
	}

	protected PriceData createLoyaltyPointsPrice(final AbstractOrderEntryModel orderEntry, final Double val)
	{
		final CurrencyModel currencyModel = getCommonI18NService().getCurrency(GuidedsellingConstants.CRD_CURRENCY_ISO);
		return getPriceDataFactory().create(PriceDataType.BUY, BigDecimal.valueOf(val.doubleValue()), currencyModel);
	}

	protected CartFacade getCartFacade()
	{
		throw new UnsupportedOperationException("use lookup-method to inject cartFacade");
	}

	protected OrderFacade getOrderFacade()
	{
		throw new UnsupportedOperationException("use lookup-method to inject orderFacade");
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

	public Converter<BillingTimeModel, BillingTimeData> getBillingTimeConverter()
	{
		return billingTimeConverter;
	}

	@Required
	public void setBillingTimeConverter(final Converter<BillingTimeModel, BillingTimeData> billingTimeConverter)
	{
		this.billingTimeConverter = billingTimeConverter;
	}

	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	/**
	 * @return the showcasecommonCalculationService
	 */
	public ShowcasecommonCalculationService getShowcasecommonCalculationService()
	{
		return showcasecommonCalculationService;
	}

}
