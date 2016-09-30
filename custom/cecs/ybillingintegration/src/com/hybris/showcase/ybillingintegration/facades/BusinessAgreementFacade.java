/**
 *
 */
package com.hybris.showcase.ybillingintegration.facades;

import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;


/**
 * @author Sebastian Weiner
 *
 */
public interface BusinessAgreementFacade
{

	boolean changeBusinessAgreement(final CreditCardPaymentInfoModel paymentInfoModel);

	boolean changeBusinessAgreementBillingMethod(final Boolean isBillingEpaperEnabled);

	/**
	 * Creates credit card and business agreement in yBilling
	 *
	 * @param paymentCardModel
	 *           credit card details (customer will be taken from session)
	 * @return business agreement id
	 */
	String createBusinessAgreement(CreditCardPaymentInfoModel paymentCardModel);

	/**
	 * Creates credit card and business agreement in yBilling
	 *
	 * @param paymentCardModel
	 *           credit card details
	 * @param customerModel
	 *           customer, who is credit card owner
	 * @return business agreement id
	 */
	String createBusinessAgreement(CreditCardPaymentInfoModel paymentCardModel, CustomerModel customerModel);
}
