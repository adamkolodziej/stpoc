/**
 * 
 */
package com.hybris.showcase;



/**
 * @author I307113
 * 
 */
public class AccountPagePaymentDetailsControllerBillingForm
{
	private boolean billingOption;
	private boolean remainder;

	public AccountPagePaymentDetailsControllerBillingForm()
	{
	}

	public AccountPagePaymentDetailsControllerBillingForm(final boolean billingOption, final boolean remainder)
	{
		this.billingOption = billingOption;
		this.remainder = remainder;
	}

	public boolean isBillingOption()
	{
		return billingOption;
	}

	public void setBillingOption(final boolean billingOption)
	{
		this.billingOption = billingOption;
	}

	public boolean isRemainder()
	{
		return remainder;
	}

	public void setRemainder(final boolean remainder)
	{
		this.remainder = remainder;
	}

}
