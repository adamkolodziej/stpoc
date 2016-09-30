/**
 *
 */
package com.seewhy.addon.hook;

import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commerceservices.order.hook.CommercePlaceOrderMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.session.SessionService;

import org.apache.log4j.Logger;

import com.seewhy.addon.common.SeewhyInMemoryRepository;
import com.seewhy.addon.common.SeewhyOrderPlacedData;
import com.seewhy.addon.constants.SeewhyConstants;


/**
 * @author Mike Tinnion
 *
 */
public class SeewhyCommercePlaceOrderMethodHook implements CommercePlaceOrderMethodHook
{
	private static final Logger LOG = Logger.getLogger(SeewhyCommercePlaceOrderMethodHook.class);

	private SessionService sessionService = null;
	private SeewhyInMemoryRepository seewhyInMemoryRepository = null;
	private Populator orderPopulator = null;


	/**
	 * After an order is placed we gather various attributes of the order and add them to the Seewhy 'order placed'
	 * context variables.
	 *
	 * @param commerceCheckoutParameter
	 * @param commerceOrderResult
	 * @return
	 */
	@Override
	public void afterPlaceOrder(final CommerceCheckoutParameter commerceCheckoutParameter,
			final CommerceOrderResult commerceOrderResult)
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyCommercePlaceOrderMethodHook afterPlaceOrder called.");
			logHybrisCommerceOrderResult(commerceOrderResult, "afterPlaceOrder");
		}

		if (sessionService != null && sessionService.getCurrentSession() != null)
		{
			String strContext = null;
			OrderModel orderModel = null;
			CartModel cartModel = null;
			final SeewhyOrderPlacedData seewhyOrderPlacedData = new SeewhyOrderPlacedData();
			final OrderData orderData = new OrderData();

			//Retrieve the 'context' (session-based, though not session scope) information.
			strContext = sessionService.getCurrentSession().getSessionId();

			if (LOG.isDebugEnabled() == true)
			{
				LOG.debug("SeewhyCommercePlaceOrderMethodHook afterPlaceOrder. strContext: " + strContext);
			}

			//Retrieve the cart, ordermodel and populate the orderData instance.
			cartModel = commerceCheckoutParameter.getCart();
			orderModel = commerceOrderResult.getOrder();
			orderPopulator.populate(orderModel, orderData);

			if (LOG.isDebugEnabled() == true)
			{
				LOG.debug("SeewhyCommercePlaceOrderMethodHook afterPlaceOrder. commerceCartParameter cartModel.getCode(): "
						+ cartModel.getCode());
				LOG.debug("SeewhyCommercePlaceOrderMethodHook afterPlaceOrder. commerceCartParameter cartModel: " + ""
						+ cartModel.getTotalPrice() + "/" + cartModel.getSubtotal() + "/" + cartModel.getCalculated() + "/"
						+ cartModel.getPaymentCost());
				LOG.debug("SeewhyCommercePlaceOrderMethodHook afterPlaceOrder orderModel.getCode(): " + orderModel.getCode());
				LOG.debug("SeewhyCommercePlaceOrderMethodHook afterPlaceOrder orderData.getTotalPriceWithTax(): "
						+ orderData.getTotalPriceWithTax().getValue());
			}

			//Set the various attributes of the order placed action.
			seewhyOrderPlacedData.setOrderValue("" + cartModel.getSubtotal());
			seewhyOrderPlacedData.setOrderNumber(orderModel.getCode());
			seewhyOrderPlacedData.setTotalValue("" + orderData.getTotalPriceWithTax().getValue());

			//Add the action to the in-memory repository.
			this.seewhyInMemoryRepository.setVariable(strContext, SeewhyConstants.SEEWHY_ACTION_ORDER_PLACED,
					seewhyOrderPlacedData);
		}
	}

	/**
	 *
	 * @param commerceCheckoutParameter
	 * @return
	 */
	@Override
	public void beforePlaceOrder(final CommerceCheckoutParameter commerceCheckoutParameter)
	{
		// YTODO Auto-generated method stub
	}

	/**
	 *
	 * @param commerceCheckoutParameter
	 * @param commerceOrderResult
	 * @return
	 */
	@Override
	public void beforeSubmitOrder(final CommerceCheckoutParameter commerceCheckoutParameter,
			final CommerceOrderResult commerceOrderResult)
	{
		// YTODO Auto-generated method stub
	}

	/**
	 *
	 * @return
	 */
	public SessionService getSessionService()
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyCommercePlaceOrderMethodHook getSessionService called: " + sessionService);
		}

		return sessionService;
	}

	/**
	 *
	 * @param sessionService
	 */
	public void setSessionService(final SessionService sessionService)
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyCommercePlaceOrderMethodHook setSessionService called: " + sessionService);
		}

		this.sessionService = sessionService;
	}

	/**
	 *
	 * @return
	 */
	public SeewhyInMemoryRepository getSeewhyInMemoryRepository()
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyCommercePlaceOrderMethodHook getSeewhyInMemoryRepository called: " + seewhyInMemoryRepository);
		}

		return seewhyInMemoryRepository;
	}

	/**
	 *
	 * @param seewhyInMemoryRepository
	 */
	public void setSeewhyInMemoryRepository(final SeewhyInMemoryRepository seewhyInMemoryRepository)
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyCommercePlaceOrderMethodHook setSeewhyInMemoryRepository called: " + seewhyInMemoryRepository);
		}

		this.seewhyInMemoryRepository = seewhyInMemoryRepository;
	}

	/**
	 *
	 * @return
	 */
	public Populator getOrderPopulator()
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyCommercePlaceOrderMethodHook getOrderPopulator called: " + orderPopulator);
		}

		return orderPopulator;
	}

	/**
	 *
	 * @param orderPopulator
	 */
	public void setOrderPopulator(final Populator orderPopulator)
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyCommercePlaceOrderMethodHook setOrderPopulator called: " + orderPopulator);
		}

		this.orderPopulator = orderPopulator;
	}

	/**
	 *
	 * @param commerceOrderResult
	 * @param strMethod
	 */
	private void logHybrisCommerceOrderResult(final CommerceOrderResult commerceOrderResult, final String strMethod)
	{
		if (commerceOrderResult != null)
		{
			OrderModel orderModel = null;

			LOG.debug("SeewhyCommercePlaceOrderMethodHook " + strMethod + " commerceOrderResult not null.");

			orderModel = commerceOrderResult.getOrder();

			if (orderModel != null)
			{
				LOG.debug("SeewhyCommercePlaceOrderMethodHook " + strMethod + " orderModel not null.");

				LOG.debug("SeewhyCommercePlaceOrderMethodHook " + strMethod + " orderModel.getCode(): " + orderModel.getCode());
			}
			else
			{
				LOG.debug("SeewhyCommercePlaceOrderMethodHook " + strMethod + " orderModel IS null.");
			}
		}
		else
		{
			LOG.debug("SeewhyCommercePlaceOrderMethodHook " + strMethod + " commerceOrderResult IS null.");
		}
	}
}
