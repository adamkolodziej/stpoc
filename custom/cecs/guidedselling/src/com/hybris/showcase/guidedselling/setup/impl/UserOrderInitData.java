/**
 *
 */
package com.hybris.showcase.guidedselling.setup.impl;

import java.util.List;

import com.hybris.showcase.guidedselling.model.BundlePackageModel;


/**
 * @author marius.bogdan.ionescu@sap.com
 *
 */
public class UserOrderInitData
{

	List<BundlePackageModel> bundles;

	/**
	 * @return the products
	 */
	public List<BundlePackageModel> getBundles()
	{
		return bundles;
	}

	/**
	 * @param products
	 *           the products to set
	 */
	public void setBundles(final List<BundlePackageModel> bundles)
	{
		this.bundles = bundles;
	}
}
