/**
 *
 */
package com.hybris.showcase.ybillingintegration.services;

import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;


/**
 * @author Rafal Zymla
 *
 */
public interface PaymentCardService
{


	String createPaymentCard(CreditCardPaymentInfoModel paymentCardModel);


}
