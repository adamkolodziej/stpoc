/**
 *
 */
package com.hybris.showcase.ybillingintegration.facades;

import de.hybris.platform.core.model.order.CartModel;


/**
 * @author Sebastian Weiner
 *
 */
public interface YBillingOrderFacade
{

	String createOrder(final CartModel cartModel);

}
