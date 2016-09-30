/**
 *
 */
package com.hybris.showcase.services;

/**
 * @author Adrian Sbarcea (SAP - i309441)
 *
 */
public interface ShowcasecommonCalculationService
{
	/**
	 * Converts an Expense amount to equivalent value in the default currency
	 *
	 * @param expAmount
	 *           the expense value that needs to be converted
	 * @param expCurrIso
	 *           the expense currency
	 * @return double the new converted expense amount
	 */
	public double convertAmountToDefaultCurrency(final double expAmount, final String expCurrIso);

}
