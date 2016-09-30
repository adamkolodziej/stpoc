/**
 *
 */
package com.hybris.showcase.services.impl;

import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.hybris.showcase.services.ShowcasecommonCalculationService;


/**
 * @author Adrian Sbarcea (SAP - i309441)
 *
 */
public class DefaultShowcasecommonCalculationService implements ShowcasecommonCalculationService
{
	private static final Logger LOG = Logger.getLogger(DefaultShowcasecommonCalculationService.class);

	@Resource(name = "commonI18NService")
	private CommonI18NService commonI18NService;

	/**
	 * Converts an Expense amount to equivalent value in the default currency
	 *
	 * @param expAmount
	 *           the expense value that needs to be converted
	 * @param expCurrIso
	 *           the expense currency
	 * @return double the new converted expense amount
	 */
	@Override
	public double convertAmountToDefaultCurrency(final double expAmount, final String expCurrIso)
	{
		double convertedPrice = expAmount;

		final CurrencyModel toCurr = getCommonI18NService().getCurrentCurrency();
		final String toCurrIso = toCurr.getIsocode();
		if (expCurrIso != null && !expCurrIso.equals(toCurrIso))
		{
			try
			{
				final CurrencyModel expCurrency = getCommonI18NService().getCurrency(expCurrIso);
				//conversion to the current currency
				convertedPrice = getCommonI18NService().convertAndRoundCurrency(expCurrency.getConversion().doubleValue(),
						toCurr.getConversion().doubleValue(), toCurr.getDigits().intValue(), convertedPrice);
			}
			catch (final UnknownIdentifierException e)
			{
				LOG.warn("Cannot convert from currency '" + expCurrIso + "' to currency '" + toCurrIso + "' since '" + expCurrIso
						+ "' doesn't exist any more - ignored");
			}
		}

		return convertedPrice;
	}

	/**
	 * @return the commonI18NService
	 */
	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

}
