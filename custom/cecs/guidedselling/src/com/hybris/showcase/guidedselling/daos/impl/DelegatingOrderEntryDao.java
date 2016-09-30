package com.hybris.showcase.guidedselling.daos.impl;

import de.hybris.platform.configurablebundleservices.daos.impl.AbstractOrderEntryDao;
import de.hybris.platform.configurablebundleservices.daos.impl.DefaultCartEntryDao;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.type.TypeModel;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

import javax.annotation.Nonnull;


/**
 * Created by miroslaw.szot@sap.com on 2016-05-17.
 */
public class DelegatingOrderEntryDao extends AbstractOrderEntryDao<AbstractOrderModel, AbstractOrderEntryModel>
{
	private DefaultOrderEntryDao bundleOrderEntryDao;
	private DefaultCartEntryDao bundleCartEntryDao;

	@Override
	public PK getItemType()
	{
		final TypeModel typeModel = getTypeService().getTypeForCode(AbstractOrderEntryModel._TYPECODE);
		return typeModel.getPk();
	}

	protected AbstractOrderEntryDao getDelegate(AbstractOrderModel masterAbstractOrder)
	{
		return masterAbstractOrder instanceof CartModel ? bundleCartEntryDao : bundleOrderEntryDao;
	}

	@Nonnull
	@Override
	public List<AbstractOrderEntryModel> findEntriesByMasterCartAndBundleNo(@Nonnull AbstractOrderModel masterAbstractOrder,
			int bundleNo)
	{
		final AbstractOrderEntryDao delegate = getDelegate(masterAbstractOrder);
		return delegate.findEntriesByMasterCartAndBundleNo(masterAbstractOrder, bundleNo);
	}

	@Nonnull
	@Override
	public List<AbstractOrderEntryModel> findEntriesByMasterCartAndBundleNoAndTemplate(
			@Nonnull AbstractOrderModel masterAbstractOrder, int bundleNo, @Nonnull BundleTemplateModel bundleTemplate)
	{
		final AbstractOrderEntryDao delegate = getDelegate(masterAbstractOrder);
		return delegate.findEntriesByMasterCartAndBundleNoAndTemplate(masterAbstractOrder, bundleNo, bundleTemplate);
	}

	@Nonnull
	@Override
	public List<AbstractOrderEntryModel> findEntriesByMasterCartAndBundleNoAndProduct(
			@Nonnull AbstractOrderModel masterAbstractOrder, int bundleNo, @Nonnull ProductModel product)
	{
		final AbstractOrderEntryDao delegate = getDelegate(masterAbstractOrder);
		return delegate.findEntriesByMasterCartAndBundleNoAndProduct(masterAbstractOrder, bundleNo, product);
	}

    public DefaultOrderEntryDao getBundleOrderEntryDao() {
        return bundleOrderEntryDao;
    }

    @Required
    public void setBundleOrderEntryDao(DefaultOrderEntryDao bundleOrderEntryDao) {
        this.bundleOrderEntryDao = bundleOrderEntryDao;
    }

    public DefaultCartEntryDao getBundleCartEntryDao() {
        return bundleCartEntryDao;
    }

    @Required
    public void setBundleCartEntryDao(DefaultCartEntryDao bundleCartEntryDao) {
        this.bundleCartEntryDao = bundleCartEntryDao;
    }
}
