package com.hybris.platform.ticketing.facades.converters.populator;

import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.platform.ticketing.data.ProductEntitlementData;
import com.hybris.services.entitlements.api.EntitlementFacade;
import com.hybris.services.entitlements.api.GrantData;

public class GiftEntitlementPopulator<SOURCE extends ProductEntitlementModel, TARGET extends ProductEntitlementData>
		implements Populator<SOURCE, TARGET> {
	
	private static final Logger LOG = Logger.getLogger(GiftEntitlementPopulator.class.getName());
	
	private ProductFacade productFacade;
	private UserService userService;
	private EntitlementFacade entitlementFacade;
	
	@Override
	public void populate(final SOURCE source, final TARGET target) {
		target.setId(source.getId());
		target.setDescription(source.getDescription());
		target.setGiftValue(source.getGiftValue());
		target.setQuantity(source.getQuantity());
		target.setName(source.getDescription());
		
		final ProductData productData = productFacade.getProductForOptions(source.getSubscriptionProduct(), Arrays.asList(ProductOption.BASIC));
		target.setProduct(productData);
		
		CustomerModel customer = (CustomerModel) userService.getCurrentUser();
		
		try {
			target.setGranted(false);
			final List<GrantData> grants = entitlementFacade.getGrants(customer.getUid(), null, null, null);
			if (CollectionUtils.isNotEmpty(grants))
			{
				for (final GrantData grant : grants)
				{
					if(source.getId().contains(grant.getGrantSource())) {
						target.setGranted(true);
					}
				}
			}
		}
		catch (RuntimeException e) {
			LOG.error("Problem with grant entitlements", e);
		}
	}
	
	public ProductFacade getProductFacade() {
		return productFacade;
	}

	@Required
	public void setProductFacade(ProductFacade productFacade) {
		this.productFacade = productFacade;
	}


	public UserService getUserService() {
		return userService;
	}

	@Required
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public EntitlementFacade getEntitlementFacade() {
		return entitlementFacade;
	}

	@Required
	public void setEntitlementFacade(EntitlementFacade entitlementFacade) {
		this.entitlementFacade = entitlementFacade;
	}

}
