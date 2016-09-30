package com.hybris.showcase.emsextras.facades.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.entitlementservices.data.EmsGrantData;
import de.hybris.platform.entitlementservices.exception.EntitlementFacadeException;
import de.hybris.platform.entitlementservices.facades.EntitlementFacadeDecorator;
import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.time.TimeService;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.services.entitlements.api.EntitlementFacade;
import com.hybris.services.entitlements.api.GrantData;
import com.hybris.showcase.emsextras.facades.GrantingEntitlementsFacade;


public class DefaultGrantingEntitlementsFacade implements GrantingEntitlementsFacade
{
	private BaseStoreService baseStoreService;
	private EntitlementFacade entitlementFacade;
	private Converter<ProductEntitlementModel, EmsGrantData> productEntitlementEmsGrantConverter;
	private EntitlementFacadeDecorator entitlementFacadeDecorator;
	private ProductService productService;
	private TimeService timeService;

	@Override
	public List<GrantData> grantEntitlementsFromProduct(final String userId, final String productCode)
			throws EntitlementFacadeException
	{
		final List<GrantData> grants = new ArrayList<>();
		final ProductModel product = productService.getProductForCode(productCode);
		for (final ProductEntitlementModel productEntitlementModel : product.getProductEntitlements())
		{
			final GrantData grantData = grantProductEntitlement(userId, productEntitlementModel);
			grants.add(grantData);
		}

		return grants;
	}

	@Override
	public GrantData grantProductEntitlement(final String userId, final ProductEntitlementModel productEntitlementModel)
			throws EntitlementFacadeException
	{
		final EmsGrantData emsGrantData = convert(productEntitlementModel);
		emsGrantData.setUserId(userId);
		//emsGrantData.setOrderCode(orderModel.getCode());
		emsGrantData.setCreatedAt(timeService.getCurrentTime());
		emsGrantData.setBaseStoreUid(getBaseStoreService().getCurrentBaseStore().getUid());
		//emsGrantData.setOrderEntryNumber(String.valueOf(entry.getEntryNumber()));

		final String grantId = getEntitlementFacadeDecorator().createEntitlement(emsGrantData);

		return entitlementFacade.getGrant(grantId);
	}

	protected EmsGrantData convert(final ProductEntitlementModel entitlement)
	{
		return getProductEntitlementEmsGrantConverter().convert(entitlement);
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

	protected EntitlementFacade getEntitlementFacade()
	{
		return entitlementFacade;
	}

	@Required
	public void setEntitlementFacade(final EntitlementFacade entitlementFacade)
	{
		this.entitlementFacade = entitlementFacade;
	}

	protected Converter<ProductEntitlementModel, EmsGrantData> getProductEntitlementEmsGrantConverter()
	{
		return productEntitlementEmsGrantConverter;
	}

	@Required
	public void setProductEntitlementEmsGrantConverter(
			final Converter<ProductEntitlementModel, EmsGrantData> productEntitlementEmsGrantConverter)
	{
		this.productEntitlementEmsGrantConverter = productEntitlementEmsGrantConverter;
	}

	protected EntitlementFacadeDecorator getEntitlementFacadeDecorator()
	{
		return entitlementFacadeDecorator;
	}

	@Required
	public void setEntitlementFacadeDecorator(final EntitlementFacadeDecorator entitlementFacadeDecorator)
	{
		this.entitlementFacadeDecorator = entitlementFacadeDecorator;
	}

	public ProductService getProductService()
	{
		return productService;
	}

	@Required
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}

	/**
	 * @return the timeService
	 */
	public TimeService getTimeService()
	{
		return timeService;
	}

	@Required
	public void setTimeService(final TimeService timeService)
	{
		this.timeService = timeService;
	}


}

