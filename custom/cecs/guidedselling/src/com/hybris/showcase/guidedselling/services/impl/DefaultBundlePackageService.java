package com.hybris.showcase.guidedselling.services.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.guidedselling.model.BundlePackageModel;
import com.hybris.showcase.guidedselling.services.BundlePackageService;


/**
 * Created by miroslaw.szot@sap.com on 2015-03-13.
 */
public class DefaultBundlePackageService implements BundlePackageService
{
	private ModelService modelService;
	private FlexibleSearchService flexibleSearchService;

	@Override
	public BundlePackageModel getPackageByCode(final String packageCode)
	{
		final BundlePackageModel packageModel = new BundlePackageModel();
		packageModel.setCode(packageCode);
		return flexibleSearchService.getModelByExample(packageModel);
	}

	@Override
	public void setPackageOnCartEntries(final BundlePackageModel packageModel, final AbstractOrderModel order,
			final Set<ProductModel> selectedProducts)
	{
		final List<AbstractOrderEntryModel> entries = order.getEntries();
		for (final AbstractOrderEntryModel entryModel : entries)
		{
			if (selectedProducts.contains(entryModel.getProduct()))
			{
				entryModel.setPackage(packageModel);
			}
		}
		modelService.saveAll(entries);
	}

    @Override
    public BundlePackageModel getFirstPackage() {
		FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery("SELECT pk FROM {BundlePackage}");
		List<BundlePackageModel> result = flexibleSearchService.<BundlePackageModel>search(flexibleSearchQuery).getResult();
		if (CollectionUtils.isEmpty(result)) {
			return null;
		}
		return result.get(0);
    }

    public ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}
}
