/**
 *
 */
package com.hybris.showcase.populators;

import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.data.InvoiceData;
import com.hybris.showcase.model.InvoiceModel;
import com.hybris.showcase.services.ShowcasecommonCalculationService;


/**
 * @author Rafal Zymla, Adrian Sbarcea (SAP - i309441)
 *
 */
public class DefaultInvoicePopulator implements Populator<InvoiceModel, InvoiceData>
{

	PriceDataFactory priceDataFactory;

	@Resource(name = "showcasecommonCalculationService")
	ShowcasecommonCalculationService showcasecommonCalculationService;

	@Resource(name = "commonI18NService")
	private CommonI18NService commonI18NService;

	@Override
	public void populate(final InvoiceModel source, final InvoiceData target) throws ConversionException
	{
		target.setInvoiceNumber(source.getInvoiceNumber());
		target.setBillingPeriodStart(source.getBillingPeriodStart());
		target.setBillingPeriodEnd(source.getBillingPeriodEnd());
		target.setDocURL(source.getPdfUrl());
		target.setInvoiceDate(source.getInvoiceDate());
		target.setDueDate(source.getDueDate());
		//TODO how to map statuses - especially when source.getAmountRemaining() is null
		if (source.getAmountRemaining() != null && source.getAmountRemaining() > 0)
		{
			target.setPaymentStatus(PaymentStatus.TO_PAY);
		}
		else
		{
			target.setPaymentStatus(PaymentStatus.PAID);
		}

		final PriceData totalPriceData = getPriceDataFactory().create(PriceDataType.BUY,
				new BigDecimal(getShowcasecommonCalculationService()
						.convertAmountToDefaultCurrency(source.getAmountDue().doubleValue(), source.getCurrency().getIsocode())),
				getCommonI18NService().getCurrentCurrency().getIsocode());
		target.setAmountDue(totalPriceData);

		if (source.getAmountRemaining() != null)
		{
			final PriceData remainingPriceData = getPriceDataFactory().create(PriceDataType.BUY,
					new BigDecimal(source.getAmountRemaining().doubleValue()), source.getCurrency());
			target.setAmountRemaining(remainingPriceData);
		}

		if (source.getAmountPaid() != null)
		{
			final PriceData paidPriceData = getPriceDataFactory().create(PriceDataType.BUY,
					new BigDecimal(source.getAmountPaid().doubleValue()), source.getCurrency());
			target.setAmountPaid(paidPriceData);
		}

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

	/**
	 * @return the showcasecommonCalculationService
	 */
	public ShowcasecommonCalculationService getShowcasecommonCalculationService()
	{
		return showcasecommonCalculationService;
	}

	/**
	 * @return the commonI18NService
	 */
	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

}
