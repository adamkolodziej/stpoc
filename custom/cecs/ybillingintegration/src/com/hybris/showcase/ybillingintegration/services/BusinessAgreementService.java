/**
 *
 */
package com.hybris.showcase.ybillingintegration.services;

import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;


/**
 * @author Sebastian Weiner
 *
 */
public interface BusinessAgreementService
{

	String createBusinessAgreement(final CustomerModel customer, CreditCardPaymentInfoModel creditCard);

	boolean changeBusinessAgreement(final CustomerModel customerModel, final CreditCardPaymentInfoModel paymentInfoModel,
									final boolean isBillingEpaperEnabled);
}
