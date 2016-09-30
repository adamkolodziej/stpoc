package com.hybris.showcase.ybillingintegrationaddon.facades.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hybris.showcase.ybillingintegration.data.YBillingCharacteristicData;
import com.hybris.showcase.ybillingintegration.data.YBillingOrderDetailsData;
import com.hybris.showcase.ybillingintegrationaddon.facades.YBillingDataMockFacade;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import org.springframework.beans.factory.annotation.Required;


public class YBillingDataMockFacadeImpl implements YBillingDataMockFacade
{
	private CommonI18NService commonI18NService;
	private PriceDataFactory priceDataFactory;


	@Override
	public List<YBillingOrderDetailsData> getYBillingOrderDetailsDataMock()
	{
		final List<YBillingOrderDetailsData> mockData = new ArrayList<>();
		for (int i = 1; i < 11; i++)
		{
			final YBillingOrderDetailsData yBillingOrderDetailsData = new YBillingOrderDetailsData();

			if ((i % 2) != 0)
			{
				yBillingOrderDetailsData.setYBillingOrderId(String.valueOf(i * 10));
			}
			else
			{
				yBillingOrderDetailsData.setYBillingOrderId(String.valueOf((i - 1) * 10));
			}

			yBillingOrderDetailsData.setOrderItem(String.valueOf((i - 1) % 2));
			yBillingOrderDetailsData.setOrderDate(new Date());

			yBillingOrderDetailsData.setOrderedProductCode("SAMPLE_PRODUCT");
			yBillingOrderDetailsData.setOrderStatus("RELEASED");
			yBillingOrderDetailsData.setOrderType("Sales Order");
			yBillingOrderDetailsData.setProcessType("ISTD");


			yBillingOrderDetailsData.setRecurringNetValue(createPriceData(BigDecimal.valueOf(10.0)));
			yBillingOrderDetailsData.setRecurringGrossValue(createPriceData(BigDecimal.valueOf(11.9)));
			yBillingOrderDetailsData.setRecurringTaxAmount(createPriceData(BigDecimal.valueOf(1.9)));
			yBillingOrderDetailsData.setRecurringTimeUnit("MONTH");
			yBillingOrderDetailsData.setRecurringDuration(BigDecimal.valueOf(1));
			yBillingOrderDetailsData.setOneTimeGrossValue(createPriceData(BigDecimal.valueOf(142.8)));
			yBillingOrderDetailsData.setOneTimeNetValue(createPriceData(BigDecimal.valueOf(120.0)));
			yBillingOrderDetailsData.setOneTimeTaxAmount(createPriceData(BigDecimal.valueOf(22.8)));
			yBillingOrderDetailsData.setSoldTo("1100220333");
			yBillingOrderDetailsData.setSoldToAddress("18101 Von Karman / Irvine CA 92612");
			yBillingOrderDetailsData.setShipTo("1100220334");
			yBillingOrderDetailsData.setShipToAddress("Wall Street 15 / NY");
			yBillingOrderDetailsData.setBillTo("1100220335");
			yBillingOrderDetailsData.setBillToAddress("Bank Street 30 / London");
			yBillingOrderDetailsData.setPayerTo("1100220336");
			yBillingOrderDetailsData.setPayerToAddress("Freiheits Str. 50 / Berlin");


			final List<YBillingCharacteristicData> changesList = new ArrayList<>();

			final YBillingCharacteristicData yBillingCharacteristicData1 = new YBillingCharacteristicData();
			yBillingCharacteristicData1.setBundleTemplate("FREE_MINUTES_OPTION");
			yBillingCharacteristicData1.setProductCode("FM_N" + i);
			changesList.add(yBillingCharacteristicData1);

			final YBillingCharacteristicData yBillingCharacteristicData2 = new YBillingCharacteristicData();
			yBillingCharacteristicData2.setBundleTemplate("FREE_SMS_OPTION");
			yBillingCharacteristicData2.setProductCode("FS_N" + i);
			changesList.add(yBillingCharacteristicData2);

			final YBillingCharacteristicData yBillingCharacteristicData3 = new YBillingCharacteristicData();
			yBillingCharacteristicData3.setBundleTemplate("NUMBER_OF_FREE_MIN");
			yBillingCharacteristicData3.setProductCode("" + (10 + i));
			changesList.add(yBillingCharacteristicData3);

			final YBillingCharacteristicData yBillingCharacteristicData4 = new YBillingCharacteristicData();
			yBillingCharacteristicData4.setBundleTemplate("NUMBER_OF_FREE_SMS");
			yBillingCharacteristicData4.setProductCode("" + (10 + i));
			changesList.add(yBillingCharacteristicData4);

			yBillingOrderDetailsData.setChangesList(changesList);

			mockData.add(yBillingOrderDetailsData);
		}
		return mockData;
	}

	private PriceData createPriceData(BigDecimal amount) {
		CurrencyModel currencyModel = getCommonI18NService().getCurrentCurrency();
		PriceDataType priceDataType = PriceDataType.BUY;
		return getPriceDataFactory().create(priceDataType, amount, currencyModel);
	}

	public CommonI18NService getCommonI18NService() {
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(CommonI18NService commonI18NService) {
		this.commonI18NService = commonI18NService;
	}

	public PriceDataFactory getPriceDataFactory() {
		return priceDataFactory;
	}

	@Required
	public void setPriceDataFactory(PriceDataFactory priceDataFactory) {
		this.priceDataFactory = priceDataFactory;
	}
}