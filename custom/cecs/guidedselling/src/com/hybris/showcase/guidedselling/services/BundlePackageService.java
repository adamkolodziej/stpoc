package com.hybris.showcase.guidedselling.services;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.Set;

import com.hybris.showcase.guidedselling.model.BundlePackageModel;


/**
 * Created by miroslaw.szot@sap.com on 2015-03-13.
 */
public interface BundlePackageService
{
	BundlePackageModel getPackageByCode(String packageCode);

	void setPackageOnCartEntries(BundlePackageModel packageModel, AbstractOrderModel order, Set<ProductModel> selectedProducts);
}
