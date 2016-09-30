package com.hybris.showcase.guidedselling.order.hooks;

import de.hybris.platform.commerceservices.order.hook.CommercePlaceOrderMethodHook;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.Collection;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Required;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.hybris.services.entitlements.api.EntitlementFacade;
import com.hybris.showcase.dao.ContractDao;
import com.hybris.showcase.guidedselling.services.OrderChangesUnitOfWork;
import com.hybris.showcase.model.ContractModel;


public class RevokeEntitlementsPlaceOrderMethodHook implements CommercePlaceOrderMethodHook
{
	private OrderChangesUnitOfWork orderChangesUnitOfWork;
	private EntitlementFacade entitlementFacade;
	private ContractDao contractDao;
	private UserService userService;
	private BaseStoreService baseStoreService;

	@Override
	public void afterPlaceOrder(final CommerceCheckoutParameter parameter, final CommerceOrderResult result)
	{
		if (!getOrderChangesUnitOfWork().isInEditMode())
		{
			return;
		}

		final Collection<ProductModel> productsRemoved = getOrderChangesUnitOfWork().getUnitOfWork().getProductsRemoved();
		final ContractModel contract = getContractDao()
				.getContractForCode(result.getOrder().getOrderChanges().getOrder().getCode());
		final String userId = getUserService().getCurrentUser().getUid();
		String source = getBaseStoreService().getCurrentBaseStore().getUid();
		for (final ProductModel product : productsRemoved)
		{
			final AbstractOrderEntryModel entry = findEntryForProduct(contract, product);
			for (final ProductEntitlementModel entitlement : entry.getProduct().getProductEntitlements())
			{

				final String entitlementType = entitlement.getEntitlement().getId();
				source = entitlement.getId();
				final String sourceId = generateSourceId(entry);
				entitlementFacade.revokeGrants(userId, entitlementType, source, sourceId);
			}
		}
	}

	private AbstractOrderEntryModel findEntryForProduct(final ContractModel contract, final ProductModel product)
	{
		return Iterables.find(contract.getOrderEntries(), new Predicate<AbstractOrderEntryModel>()
		{
			@Override
			public boolean apply(final AbstractOrderEntryModel entry)
			{
				return Objects.equals(entry.getProduct(), product);
			}
		});
	}

	// see de.hybris.platform.emsclient.populators.EmsGrantGrantPopulator
	private String generateSourceId(final AbstractOrderEntryModel entry)
	{
		String sourceId = entry.getOrder().getCode();
		if (entry.getEntryNumber() != null)
		{
			if (sourceId == null)
			{
				sourceId = entry.getEntryNumber().toString();
			}
			else
			{
				sourceId = String.format("%s_%s", sourceId, entry.getEntryNumber());
			}
		}
		return sourceId;
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

	protected EntitlementFacade getEntitlementFacade()
	{
		return entitlementFacade;
	}

	@Required
	public void setEntitlementFacade(final EntitlementFacade entitlementFacade)
	{
		this.entitlementFacade = entitlementFacade;
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

	protected UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	protected BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	@Required
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}
}
