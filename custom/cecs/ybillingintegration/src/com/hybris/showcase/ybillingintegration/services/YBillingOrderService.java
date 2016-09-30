/**
 *
 */
package com.hybris.showcase.ybillingintegration.services;

import com.hybris.showcase.ybillingintegration.data.YBillingContractDetailsData;
import com.hybris.showcase.ybillingintegration.data.YBillingOrderDetailsData;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;

import java.util.List;


/**
 * @author Sebastian Weiner
 *
 */
public interface YBillingOrderService
{
	String createOrder(final AbstractOrderModel order);

	void changeOrder(final AbstractOrderModel order);

	List<YBillingContractDetailsData> getContractDetails(final String businessPartnerId);

	List<YBillingOrderDetailsData> getOrderDetails(final String businessPartnerId);
}
