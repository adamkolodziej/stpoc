package com.hybris.showcase.guidedselling.order.hooks;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.hybris.showcase.dao.ContractDao;
import com.hybris.showcase.guidedselling.enums.BundleComponentType;
import com.hybris.showcase.guidedselling.services.OrderChangesUnitOfWork;
import com.hybris.showcase.model.ContractModel;
import de.hybris.platform.commerceservices.order.hook.CommercePlaceOrderMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


public class ModifyContractEntriesPlaceOrderMethodHook implements CommercePlaceOrderMethodHook
{
	private OrderChangesUnitOfWork orderChangesUnitOfWork;
	private ModelService modelService;
	private ContractDao contractDao;

	@Override
	public void afterPlaceOrder(final CommerceCheckoutParameter parameter, final CommerceOrderResult result)
	{
		if (orderChangesUnitOfWork.isInEditMode())
		{
			updateExistingContract(result.getOrder());
		}
		else if (orderContainsConditionalProduct(result.getOrder()))
		{
			createNewContract(result.getOrder());
		}
	}

	private boolean orderContainsConditionalProduct(final OrderModel order)
	{
		return Iterables.any(order.getEntries(), new Predicate<AbstractOrderEntryModel>()
		{
			@Override
			public boolean apply(final AbstractOrderEntryModel entry)
			{
				return entry.getBundleTemplate() != null
						&& Objects.equals(entry.getBundleTemplate().getBundleComponentType(), BundleComponentType.CONDITIONAL);
			}
		});
	}

	private void createNewContract(final OrderModel order)
	{
		final ContractModel contract = getModelService().create(ContractModel.class);
		contract.setCode(order.getCode());
		contract.setUser(order.getUser());
		addEntriesToContract(contract, order.getEntries());
		getModelService().save(contract);
	}

	private void updateExistingContract(final OrderModel order)
	{
		final ContractModel contract = getContract(order);
		removeEntries(contract);
		addNewEntriesToContract(contract, order);
		contract.setCode(order.getCode());
		getModelService().save(contract);
	}

	private ContractModel getContract(final OrderModel order)
	{
		final String contractCode = order.getOrderChanges().getOrder().getCode();
		final ContractModel contract = getContractDao().getContractForCode(contractCode);
		if (contract == null)
		{
			throw new IllegalStateException(String.format("There's no contract for code: %s", contractCode));
		}
		return contract;
	}

	private void removeEntries(final ContractModel contract)
	{
		final Collection<ProductModel> productsRemoved = getOrderChangesUnitOfWork().getUnitOfWork().getProductsRemoved();
		final Collection<AbstractOrderEntryModel> entriesToSurvive = Collections2.filter(contract.getOrderEntries(),
				new Predicate<AbstractOrderEntryModel>()
				{
					@Override
					public boolean apply(final AbstractOrderEntryModel entry)
					{
						return !productsRemoved.contains(entry.getProduct());
					}
				});
		contract.setOrderEntries(entriesToSurvive);
	}

	private void addNewEntriesToContract(final ContractModel contract, final OrderModel order)
	{
		final Collection<AbstractOrderEntryModel> newEntries = getNewEntries(order.getEntries());
		addEntriesToContract(contract, newEntries);
	}

	private void addEntriesToContract(final ContractModel contract, final Collection<AbstractOrderEntryModel> newEntries)
	{
		final Collection<AbstractOrderEntryModel> entries;
		if (CollectionUtils.isEmpty(contract.getOrderEntries()))
		{
			entries = new ArrayList<>();
		}
		else
		{
			entries = new ArrayList<>(contract.getOrderEntries());
		}

		for (final AbstractOrderEntryModel entry : newEntries)
		{
			entry.setContract(contract);
			entries.add(entry);
			getModelService().save(entry);
		}

		contract.setOrderEntries(entries);
	}

	private Collection<AbstractOrderEntryModel> getNewEntries(final Collection<AbstractOrderEntryModel> entries)
	{
		final Collection<ProductModel> productsAdded = getOrderChangesUnitOfWork().getUnitOfWork().getProductsAdded();
		return Collections2.filter(entries, new Predicate<AbstractOrderEntryModel>()
		{
			@Override
			public boolean apply(final AbstractOrderEntryModel entry)
			{
				return productsAdded.contains(entry.getProduct());
			}
		});
	}

	@Override
	public void beforePlaceOrder(final CommerceCheckoutParameter parameter)
	{
		// no op
	}

	@Override
	public void beforeSubmitOrder(final CommerceCheckoutParameter parameter, final CommerceOrderResult result)
	{
		// no op
	}

	public OrderChangesUnitOfWork getOrderChangesUnitOfWork()
	{
		return orderChangesUnitOfWork;
	}

	@Required
	public void setOrderChangesUnitOfWork(final OrderChangesUnitOfWork orderChangesUnitOfWork)
	{
		this.orderChangesUnitOfWork = orderChangesUnitOfWork;
	}

	protected ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	protected ContractDao getContractDao()
	{
		return contractDao;
	}

	@Required
	public void setContractDao(final ContractDao contractDao)
	{
		this.contractDao = contractDao;
	}

}
