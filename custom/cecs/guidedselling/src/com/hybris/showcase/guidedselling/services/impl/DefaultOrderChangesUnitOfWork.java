package com.hybris.showcase.guidedselling.services.impl;

import com.hybris.showcase.guidedselling.model.OrderChangesModel;
import com.hybris.showcase.guidedselling.order.hooks.OrderChanges;
import com.hybris.showcase.guidedselling.services.OrderChangesUnitOfWork;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;


/**
 * CECS-88 guidedselling: edit existing contract Created by miroslaw.szot@sap.com on 2015-03-26.
 */
public class DefaultOrderChangesUnitOfWork implements OrderChangesUnitOfWork
{
	private ModelService modelService;
	private CartService cartService;

	@Override
	public OrderChanges getUnitOfWork()
	{
		if (!isInEditMode())
		{
			return null;
		}

		final CartModel cart = cartService.getSessionCart();
		return new OrderChanges(cart);
	}

	@Override
	public boolean isInEditMode()
	{
		return cartService.getSessionCart().getOrderChanges() != null;
	}

	@Override
	public void save(OrderChanges orderChanges)
	{
		final CartModel cart = cartService.getSessionCart();

		final OrderChangesModel orderChangesModel = cart.getOrderChanges();
		orderChangesModel.setProductsAdded(new ArrayList<ProductModel>(orderChanges.getProductsAdded()));
		orderChangesModel.setProductsRemoved(new ArrayList<ProductModel>(orderChanges.getProductsRemoved()));
		modelService.save(orderChangesModel);
	}

	@Override
	public void cleanup()
	{
		final OrderChangesModel orderChangesModel = cartService.getSessionCart().getOrderChanges();
		modelService.remove(orderChangesModel);
	}

	@Override
	public void enterEditingMode(OrderModel order)
	{
		final OrderChangesModel orderChangesModel = modelService.create(OrderChangesModel.class);
		orderChangesModel.setOrder(order);
		final CartModel cart = cartService.getSessionCart();
		cart.setOrderChanges(orderChangesModel);
		modelService.save(cart);
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(ModelService modelService)
	{
		this.modelService = modelService;
	}

	public CartService getCartService()
	{
		return cartService;
	}

	@Required
	public void setCartService(CartService cartService)
	{
		this.cartService = cartService;
	}
}
