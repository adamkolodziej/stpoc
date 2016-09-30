package com.hybris.showcase.guidedselling.order.hooks;

import com.hybris.showcase.dao.ContractDao;
import com.hybris.showcase.model.ContractModel;
import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartModificationStatus;
import de.hybris.platform.commerceservices.order.hook.CommerceAddToCartMethodHook;
import de.hybris.platform.commerceservices.order.hook.CommerceUpdateCartEntryHook;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.configurablebundleservices.bundle.BundleTemplateService;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.hybris.showcase.guidedselling.enums.BundleComponentType;
import com.hybris.showcase.guidedselling.services.OrderChangesUnitOfWork;


/*
 * CECS-88 guidedselling: edit existing contract
 */
public class EditContractCartMethodsHook implements CommerceAddToCartMethodHook, CommerceUpdateCartEntryHook
{

	private static final Logger LOG = Logger.getLogger(EditContractCartMethodsHook.class);

	private OrderChangesUnitOfWork orderChangesUnitOfWork;
	private ModelService modelService;
	private ContractDao contractDao;
	private UserService userService;
	private BundleTemplateService bundleTemplateService;

	@Override
	public void afterAddToCart(final CommerceCartParameter parameter, final CommerceCartModification result)
			throws CommerceCartModificationException
	{
		if (shouldNotTrackChange(parameter, result))
		{
			return;
		}

		final OrderChanges orderChanges = orderChangesUnitOfWork.getUnitOfWork();
		removePreviuosConditionalProduct(orderChanges, parameter);
		orderChanges.addProduct(parameter.getProduct());
		orderChangesUnitOfWork.save(orderChanges);

		if (orderChanges.getProductsAdded().contains(parameter.getProduct()))
		{
			final AbstractOrderEntryModel entry = result.getEntry();
			entry.setAddedToExistingContract(true);
			modelService.save(entry);
		}
	}

	private void removePreviuosConditionalProduct(final OrderChanges orderChanges, final CommerceCartParameter parameter)
	{
		final CartModel cart = parameter.getCart();
		final AbstractOrderEntryModel entryForProduct = getEntryForProduct(parameter.getProduct(), cart);
		if (isConditional(entryForProduct))
		{
			removeOldConditionalProduct(orderChanges, cart.getUser(), parameter.getProduct());
		}
	}

	private AbstractOrderEntryModel getEntryForProduct(final ProductModel product, final CartModel cart)
	{
		return Iterables.find(cart.getEntries(), new Predicate<AbstractOrderEntryModel>()
		{
			@Override
			public boolean apply(final AbstractOrderEntryModel entry)
			{
				return entry.getProduct().equals(product);
			}
		});
	}

	private void removeOldConditionalProduct(final OrderChanges orderChanges, final UserModel user, final ProductModel addedProduct)
	{
		final ContractModel contract = getContractDao().getLatestContractForUser(user);
		final AbstractOrderEntryModel oldConditionalEntry = Iterables.find(contract.getOrderEntries(),
				new Predicate<AbstractOrderEntryModel>()
				{
					@Override
					public boolean apply(final AbstractOrderEntryModel entry)
					{
						return isConditional(entry);
					}
				});
		if (!oldConditionalEntry.getProduct().equals(addedProduct))
		{
			orderChanges.removeProduct(oldConditionalEntry.getProduct());
		}

		for (final ProductModel product : orderChanges.getProductsAdded())
		{
			if (isConditional(product))
			{
				orderChanges.removeProduct(product);
			}
		}
	}

	private boolean isConditional(final AbstractOrderEntryModel entry)
	{
		if (entry.getBundleTemplate() != null)
		{
			return entry.getBundleTemplate().getBundleComponentType() == BundleComponentType.CONDITIONAL;
		}

		return isConditional(entry.getProduct());
	}

	private boolean isConditional(final ProductModel product)
	{
		final UserModel currentUser = getUserService().getCurrentUser();
		final ContractModel contract = getContractDao().getLatestContractForUser(currentUser);
		final BundleTemplateModel conditionalBundleTemplate = getConditionalBundleTemplate(contract);
		return conditionalBundleTemplate.getProducts().contains(product);
	}

	private BundleTemplateModel getConditionalBundleTemplate(final ContractModel contract) {
		final BundleTemplateModel childBundleTemplate = contract.getOrderEntries().iterator().next().getBundleTemplate();
		final BundleTemplateModel rootBundleTemplate = getBundleTemplateService().getRootBundleTemplate(childBundleTemplate);
		return Iterables.find(rootBundleTemplate.getChildTemplates(), new Predicate<BundleTemplateModel>() {
			@Override
			public boolean apply(final BundleTemplateModel bundleTemplate) {
				return bundleTemplate.getBundleComponentType() == BundleComponentType.CONDITIONAL;
			}
		});
	}

	@Override
	public void afterUpdateCartEntry(final CommerceCartParameter parameter, final CommerceCartModification result)
	{
		if (shouldNotTrackChange(parameter, result))
		{
			return;
		}

		if (result.getQuantity() != 0)
		{
			return;
		}

		final OrderChanges orderChanges = orderChangesUnitOfWork.getUnitOfWork();
		orderChanges.removeProduct(parameter.getProduct());
		orderChangesUnitOfWork.save(orderChanges);
	}

	private boolean shouldNotTrackChange(final CommerceCartParameter parameter, final CommerceCartModification result)
	{
		return editingContractIsNotEnabled() || notSuccesfulCartUpdate(result) || notMasterCard(parameter);
	}

	private boolean editingContractIsNotEnabled()
	{
		return !orderChangesUnitOfWork.isInEditMode();
	}

	private boolean notSuccesfulCartUpdate(final CommerceCartModification result)
	{
		return result == null || !StringUtils.equals(result.getStatusCode(), CommerceCartModificationStatus.SUCCESS);
	}

	private boolean notMasterCard(final CommerceCartParameter parameter)
	{
		return parameter.getCart().getParent() != null;
	}


	@Override
	public void beforeAddToCart(final CommerceCartParameter parameters) throws CommerceCartModificationException
	{
	}

	@Override
	public void beforeUpdateCartEntry(final CommerceCartParameter parameter)
	{
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

	public ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public ContractDao getContractDao()
	{
		return contractDao;
	}

	@Required
	public void setContractDao(final ContractDao contractDao)
	{
		this.contractDao = contractDao;
	}

	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	public BundleTemplateService getBundleTemplateService()
	{
		return bundleTemplateService;
	}

	@Required
	public void setBundleTemplateService(final BundleTemplateService bundleTemplateService)
	{
		this.bundleTemplateService = bundleTemplateService;
	}
}
