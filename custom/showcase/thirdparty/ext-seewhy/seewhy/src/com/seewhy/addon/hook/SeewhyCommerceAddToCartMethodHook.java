/**
 *
 */
package com.seewhy.addon.hook;

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.hook.CommerceAddToCartMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.session.SessionService;

import org.apache.log4j.Logger;

import com.seewhy.addon.common.SeewhyAddToCartData;
import com.seewhy.addon.common.SeewhyInMemoryRepository;
import com.seewhy.addon.constants.SeewhyConstants;


/**
 * @author Mike Tinnion
 *
 */
public class SeewhyCommerceAddToCartMethodHook implements CommerceAddToCartMethodHook
{
	private static final Logger LOG = Logger.getLogger(SeewhyCommerceAddToCartMethodHook.class);

	private SessionService sessionService = null;
	private SeewhyInMemoryRepository seewhyInMemoryRepository = null;

	/**
	 * After a product is added to cart we gather various attributes of the new cart entry and add them to the Seewhy
	 * 'add to cart' context variables.
	 *
	 * @param commerceCartParameter
	 * @param commerceCartModification
	 * @throws CommerceCartModificationException
	 * @return
	 */
	@Override
	public void afterAddToCart(final CommerceCartParameter commerceCartParameter,
			final CommerceCartModification commerceCartModification) throws CommerceCartModificationException
	{
		if (LOG.isDebugEnabled() == true)
		{
			LOG.debug("SeewhyCommerceAddToCartMethodHook afterAddToCart called.");
		}

		if (sessionService != null && sessionService.getCurrentSession() != null)
		{
			String strContext = null;

			//Retrieve the 'context' (session-based, though not session scope) information.
			strContext = sessionService.getCurrentSession().getSessionId();

			if (LOG.isDebugEnabled() == true)
			{
				LOG.debug("SeewhyCommerceAddToCartMethodHook afterAddToCart. strContext: " + strContext);
			}

			final SeewhyAddToCartData seewhyAddToCartData = new SeewhyAddToCartData();
			ProductModel productModel = null;
			CartModel cartModel = null;

			//Retrieve the product that has been added to the cart.
			productModel = commerceCartParameter.getProduct();

			//Retrieve the cart model.
			cartModel = commerceCartParameter.getCart();

			if (LOG.isDebugEnabled() == true)
			{
				LOG.debug("SeewhyCommerceAddToCartMethodHook afterAddToCart. commerceCartParameter productModel.getCode(): "
						+ productModel.getCode());
				LOG.debug("SeewhyCommerceAddToCartMethodHook afterAddToCart. commerceCartParameter commerceCartParameter.getQuantity(): "
						+ commerceCartParameter.getQuantity());
				LOG.debug("SeewhyCommerceAddToCartMethodHook afterAddToCart. commerceCartParameter cartModel: "
						+ (cartModel != null ? "" + cartModel.getTotalPrice() + "/" + cartModel.getSubtotal() + "/"
								+ cartModel.getCalculated() + "/" + cartModel.getPaymentCost() : "null"));
				LOG.debug("SeewhyCommerceAddToCartMethodHook afterAddToCart. commerceCartParameter cartModel.getCode(): "
						+ (cartModel != null ? cartModel.getCode() : ""));
			}

			//Add the product code,the quantity added and the cart subtotal to the context variables.
			seewhyAddToCartData.setCode(productModel.getCode());
			seewhyAddToCartData.setQuantity("" + commerceCartParameter.getQuantity());
			seewhyAddToCartData.setCartValue("" + cartModel.getSubtotal());

			//Add the action to the in-memory repository.
			this.seewhyInMemoryRepository.setVariable(strContext, SeewhyConstants.SEEWHY_ACTION_ADD_TO_CART, seewhyAddToCartData);
		}
	}

	/**
	 *
	 * @param commerceCartParameter
	 * @throws CommerceCartModificationException
	 * @return
	 */
	@Override
	public void beforeAddToCart(final CommerceCartParameter commerceCartParameter) throws CommerceCartModificationException
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
			LOG.debug("SeewhyCommerceAddToCartMethodHook getSessionService called: " + sessionService);
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
			LOG.debug("SeewhyCommerceAddToCartMethodHook setSessionService called: " + sessionService);
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
			LOG.debug("SeewhyCommerceAddToCartMethodHook getSeewhyInMemoryRepository called: " + seewhyInMemoryRepository);
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
			LOG.debug("SeewhyCommerceAddToCartMethodHook setSeewhyInMemoryRepository called: " + seewhyInMemoryRepository);
		}

		this.seewhyInMemoryRepository = seewhyInMemoryRepository;
	}
}
