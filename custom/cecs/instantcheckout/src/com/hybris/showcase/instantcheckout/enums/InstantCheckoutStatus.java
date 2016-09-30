/**
 * 
 */
package com.hybris.showcase.instantcheckout.enums;

import de.hybris.platform.commerceservices.order.CommerceCartModificationStatus;


/**
 * @author mgolubovic
 * 
 */
public interface InstantCheckoutStatus extends CommerceCartModificationStatus
{
	String MISSING_DELIVERY_INFO = "missingDelivery";
	String MISSING_PAYMENT_INFO = "missingPayment";
	String AUTH_REQUIRED = "authenticationRequired";
	String ALREADY_ENTITLED = "alreadyEntitled";
	String SUCCESS = "success";
	String FAIL = "fail";
}
