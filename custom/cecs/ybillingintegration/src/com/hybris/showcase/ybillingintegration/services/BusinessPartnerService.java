/**
 *
 */
package com.hybris.showcase.ybillingintegration.services;

import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;


/**
 * @author Rafal Zymla
 *
 */
public interface BusinessPartnerService
{


	String createBusinessParter(final CustomerModel customer);

	String addPaymentCard(CustomerModel customerModel, CreditCardPaymentInfoModel cardModel, String nonMaskedCardNo);

}
