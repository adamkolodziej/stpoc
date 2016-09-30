/**
 *
 */
package com.hybris.showcase.populators;

import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.hybris.showcase.data.CurrentExpensesData;
import com.hybris.showcase.model.ExpenseModel;
import com.hybris.showcase.services.ShowcasecommonCalculationService;


/**
 * @author Sebastian Weiner, Adrian Sbarcea (SAP - i309441)
 *
 */
public class BasicExpensePopulator implements Populator<ExpenseModel, CurrentExpensesData>
{
	private static final Logger LOG = Logger.getLogger(BasicExpensePopulator.class);

	PriceDataFactory priceDataFactory;

	@Resource(name = "commonI18NService")
	private CommonI18NService commonI18NService;

	@Resource(name = "showcasecommonCalculationService")
	ShowcasecommonCalculationService showcasecommonCalculationService;


	@Override
	public void populate(final ExpenseModel source, final CurrentExpensesData target) throws ConversionException
	{
		target.setCode(source.getCode());
		target.setName(source.getName());

		target.setExpenseDate(source.getExpenseDate());

		target.setType(source.getType());

		final PriceData priceData = getPriceDataFactory().create(
				PriceDataType.BUY, new BigDecimal(getShowcasecommonCalculationService()
						.convertAmountToDefaultCurrency(source.getAmount().doubleValue(), source.getCurrencyIso())),
				getCommonI18NService().getCurrentCurrency().getIsocode());
		target.setAmount(priceData);
	}


	public PriceDataFactory getPriceDataFactory()
	{
		return priceDataFactory;
	}


	public void setPriceDataFactory(final PriceDataFactory priceDataFactory)
	{
		this.priceDataFactory = priceDataFactory;
	}


	/**
	 * @return the commonI18NService
	 */
	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	/**
	 * @return the showcasecommonCalculationService
	 */
	public ShowcasecommonCalculationService getShowcasecommonCalculationService()
	{
		return showcasecommonCalculationService;
	}

}
