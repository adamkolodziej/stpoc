package com.hybris.showcase.guidedselling.order.hooks;

import com.hybris.showcase.guidedselling.model.OrderChangesModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.io.Serializable;
import java.util.*;

/*
 * CECS-88 guidedselling: edit existing contract
 */
public class OrderChanges implements Serializable
{
    private static final long serialVersionUID = 1L;

	private final Collection<ProductModel> productsExisting = new HashSet<>();
	private final Collection<ProductModel> productsAdded = new HashSet<>();
	private final Collection<ProductModel> productsRemoved = new HashSet<>();

    public OrderChanges(CartModel cartModel) {
		productsAdded.addAll(cartModel.getOrderChanges().getProductsAdded());
		productsRemoved.addAll(cartModel.getOrderChanges().getProductsRemoved());

		final List<AbstractOrderEntryModel> existingEntries = cartModel.getOrderChanges().getOrder().getEntries();
		for (AbstractOrderEntryModel entry : existingEntries) {
			productsExisting.add(entry.getProduct());
		}
	}

    public Collection<ProductModel> getProductsAdded()
	{
		return Collections.unmodifiableCollection(productsAdded);
	}

	public Collection<ProductModel> getProductsRemoved()
	{
		return Collections.unmodifiableCollection(productsRemoved);
	}

	public void addProduct(final ProductModel product)
	{
		productsRemoved.remove(product);

		if( productsExisting.contains(product)) {
			productsAdded.remove(product);
		} else {
			productsAdded.add(product);
		}
	}

	public void removeProduct(final ProductModel product)
	{
		productsAdded.remove(product);
		if( productsExisting.contains(product)) {
			productsRemoved.add(product);
		}
	}

	public void apply(CartModel cartModel) {
		final OrderChangesModel orderChangesModel = cartModel.getOrderChanges();
		orderChangesModel.setProductsAdded(this.productsAdded);
		orderChangesModel.setProductsRemoved(this.productsRemoved);
	}
}
