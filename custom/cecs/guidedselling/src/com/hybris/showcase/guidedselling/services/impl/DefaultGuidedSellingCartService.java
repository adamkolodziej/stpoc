package com.hybris.showcase.guidedselling.services.impl;

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.hook.CommerceAddToCartMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.configurablebundleservices.bundle.impl.DefaultBundleCommerceCartService;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.guidedselling.services.OrderChangesUnitOfWork;

import java.util.List;


public class DefaultGuidedSellingCartService extends DefaultBundleCommerceCartService
{
	private List<CommerceAddToCartMethodHook> bundleAddToCartMethodHooks;
	private OrderChangesUnitOfWork orderChangesUnitOfWork;



	@Override
	protected void checkAndRemoveDependentComponents(final CartModel masterCartModel, final int bundleNo,
			final BundleTemplateModel bundleTemplate)
	{
		if (!getOrderChangesUnitOfWork().isInEditMode())
		{
			super.checkAndRemoveDependentComponents(masterCartModel, bundleNo, bundleTemplate);
		}
	}

	@Override
	protected CommerceCartModification addProductToCart(CartModel masterCartModel, ProductModel productModel, long quantityToAdd, UnitModel unit, boolean forceNewEntry, int bundleNo, BundleTemplateModel bundleTemplateModel, String xmlProduct, boolean ignoreEmptyBundle) throws CommerceCartModificationException {
		final CommerceCartModification modification = super.addProductToCart(masterCartModel, productModel, quantityToAdd, unit, forceNewEntry, bundleNo, bundleTemplateModel, xmlProduct, ignoreEmptyBundle);

		CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(masterCartModel);
		parameter.setProduct(productModel);
		parameter.setQuantity(quantityToAdd);
		parameter.setUnit(unit);
		parameter.setCreateNewEntry(forceNewEntry);

		for (CommerceAddToCartMethodHook hook : bundleAddToCartMethodHooks) {

			hook.afterAddToCart(parameter, modification);
		}

		return modification;
	}

	protected OrderChangesUnitOfWork getOrderChangesUnitOfWork()
	{
		return orderChangesUnitOfWork;
	}

	@Required
	public void setOrderChangesUnitOfWork(final OrderChangesUnitOfWork orderChangesUnitOfWork)
	{
		this.orderChangesUnitOfWork = orderChangesUnitOfWork;
	}

	public List<CommerceAddToCartMethodHook> getBundleAddToCartMethodHooks() {
		return bundleAddToCartMethodHooks;
	}

	@Required
	public void setBundleAddToCartMethodHooks(List<CommerceAddToCartMethodHook> bundleAddToCartMethodHooks) {
		this.bundleAddToCartMethodHooks = bundleAddToCartMethodHooks;
	}
}

