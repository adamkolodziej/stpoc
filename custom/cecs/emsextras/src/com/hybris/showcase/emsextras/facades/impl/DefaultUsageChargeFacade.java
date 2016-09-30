package com.hybris.showcase.emsextras.facades.impl;

import com.hybris.services.entitlements.condition.ConditionData;
import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.subscriptionfacades.SubscriptionFacade;
import de.hybris.platform.subscriptionfacades.data.*;
import de.hybris.platform.subscriptionfacades.exceptions.SubscriptionFacadeException;
import de.hybris.platform.subscriptionservices.model.PerUnitUsageChargeModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import de.hybris.platform.subscriptionservices.model.UsageChargeModel;
import de.hybris.platform.subscriptionservices.model.VolumeUsageChargeModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.hybris.services.entitlements.api.ExecuteResult;
import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.condition.CriterionData;
import com.hybris.showcase.dao.ProductEntitlementDao;
import com.hybris.showcase.emsextras.facades.EMSFacade;
import com.hybris.showcase.emsextras.facades.UsageChargeFacade;
import com.hybris.showcase.emsextras.services.UsageChargeService;
import com.hybris.showcase.emsextras.strategy.SubmitUsageStrategy;


/**
 * CECS-243 usage submit (to ems/brim) - charge customer Created by miroslaw.szot@sap.com on 2015-06-02.
 */
public class DefaultUsageChargeFacade implements UsageChargeFacade
{
	private static final Logger LOG = Logger.getLogger(DefaultUsageChargeFacade.class);
	private EMSFacade emsFacade;
	private ProductEntitlementDao productEntitlementDao;
	private UsageChargeService usageChargeService;
	private Converter<PerUnitUsageChargeModel, PerUnitUsageChargeData> perUnitUsageChargeConverter;
	private Converter<VolumeUsageChargeModel, VolumeUsageChargeData> volumeUsageChargeConverter;

	private SubscriptionFacade subscriptionFacade;
	private UserService userService;

	private List<SubmitUsageStrategy> submitUsageStrategyList;

	private ExecuteResult checkEntitlement(final String productCode, final String entitlementType) {
		final List<CriterionData> criteria = emsFacade.createCriteriaForProduct(productCode, entitlementType);
		criteria.add(emsFacade.createTimeCriterion());

		final ExecuteResult nonMeteredResult = emsFacade.check(entitlementType, criteria, true);
		ExecuteResult meteredResult = null;

		boolean granted = nonMeteredResult.isResult();

		if (granted)
		{
			return nonMeteredResult;
		}
		else
		{
			meteredResult = emsFacade.checkMetered(entitlementType, criteria, 1, true);
			return meteredResult;
		}
	}

	@Override
	public UsageChargeData getUsageChargeForGrant(final String productCode, final String entitlementType)
	{
		final ExecuteResult executeResult = checkEntitlement(productCode, entitlementType);
		return getUsageChargeDataForExecuteResult(executeResult);
	}

	@Override
	public UsageChargeData getUsageChargeDataForExecuteResult(final ExecuteResult emsResult)
	{
		final List<UsageChargeModel> usageCharges = new ArrayList<>();

		final Map<UsageChargeModel, String> grantSourceMap = new HashMap<>();
		for (final GrantData grantData : emsResult.getGrantDataList())
		{
			final String grantSource = grantData.getGrantSource();
			final ProductEntitlementModel productEntitlementModel = productEntitlementDao.findById(grantSource);
			final UsageChargeModel usageCharge = usageChargeService.getUsageChargeForProductEntitlement(productEntitlementModel);
			if (usageCharge != null)
			{
				usageCharges.add(usageCharge);

				if (productEntitlementModel.getSubscriptionProduct() instanceof SubscriptionProductModel)
				{
					grantSourceMap.put(usageCharge, productEntitlementModel.getSubscriptionProduct().getCode());
				}
			}
			else
			{
				// at least one grant w/o usage charge - it takes priority
				return null;
			}
		}



		for (final UsageChargeModel usageCharge : usageCharges)
		{
			// for now return first usage charge, but in theory we could decide which one to apply (the lowest cost?).
			final UsageChargeData chargeData = convert(usageCharge);
			chargeData.setSubscriptionProductCode(grantSourceMap.get(usageCharge));

			return chargeData;
		}
		return null;
	}

	@Override
	public UsageChargeEntryData getUsageChargeValue(ExecuteResult emsResult) {
		final UsageChargeData chargeData = getUsageChargeDataForExecuteResult(emsResult);
		return getUsageChargeValue(chargeData, emsResult);
	}

	public UsageChargeEntryData getUsageChargeValue(UsageChargeData chargeData, ExecuteResult emsResult) {
		if( chargeData == null ) {
			return null;
		}

		int used = 0;
		for (GrantData grantData : emsResult.getGrantDataList()) {
			for (ConditionData conditionData : grantData.getConditions()) {
				if( "metered".equals(conditionData.getType())) {
					final int maxQuantity = Integer.valueOf(StringUtils.defaultIfBlank(conditionData.getProperty("maxQuantity"), "0"));
					final int remainingQuantity = Integer.valueOf(StringUtils.defaultIfBlank(conditionData.getProperty("remainingQuantity"), "0"));
					used += maxQuantity - remainingQuantity;
				}
			}
		}

		UsageChargeEntryData selectedEntry = null;
		for (UsageChargeEntryData entryData : chargeData.getUsageChargeEntries()) {
			if (entryData instanceof TierUsageChargeEntryData ) {
				final TierUsageChargeEntryData tiered = (TierUsageChargeEntryData) entryData;
				if( used >= tiered.getTierStart() && used < tiered.getTierEnd() ) {
					selectedEntry = entryData;
					break;
				}
			}
		}

		if( selectedEntry == null ) {
			for (UsageChargeEntryData entryData : chargeData.getUsageChargeEntries()) {
				if (entryData instanceof OverageUsageChargeEntryData) {
					return entryData;
				}
			}
		}

		return null; // unable to determinate charge
	}

	@Override
	public UsageChargeData getUsageChargeDataForProductEntitlementId(final String productEntitlementId)
	{
		final ProductEntitlementModel productEntitlementModel = productEntitlementDao.findById(productEntitlementId);
		final UsageChargeModel usageCharge = usageChargeService.getUsageChargeForProductEntitlement(productEntitlementModel);
		if (usageCharge != null)
		{
			return convert(usageCharge);
		}
		else
		{
			return null;
		}
	}

	protected UsageChargeData convert(final UsageChargeModel usageCharge)
	{
		if (usageCharge instanceof PerUnitUsageChargeModel)
		{
			return getPerUnitUsageChargeConverter().convert((PerUnitUsageChargeModel) usageCharge);
		}
		else if (usageCharge instanceof VolumeUsageChargeModel)
		{
			return getVolumeUsageChargeConverter().convert((VolumeUsageChargeModel) usageCharge);
		}

		return null;
	}

	@Override
	public boolean submitUsage(final String productCode, final String entitlementType, final int quantity)
	{

		final List<CriterionData> criteria = emsFacade.createCriteriaForProduct(productCode, entitlementType);
		criteria.add(emsFacade.createTimeCriterion());
		emsFacade.use(entitlementType, criteria, quantity, false);

		final ExecuteResult executeResult = checkEntitlement(productCode, entitlementType);
		final UsageChargeData chargeData = getUsageChargeDataForExecuteResult(executeResult);
		final UsageChargeEntryData charge = getUsageChargeValue(chargeData, executeResult);

		getSubmitUsageStrategyList().forEach(submitUsageStrategy ->
			submitUsageStrategy.submitUsage(productCode, entitlementType, quantity, charge)
		);

		return ! getSubmitUsageStrategyList().isEmpty();
	}

	protected SubscriptionData findSubscriptionByProductCode(final String productCode) throws SubscriptionFacadeException
	{
		try
		{
			return Iterables.find(subscriptionFacade.getSubscriptions(), new Predicate<SubscriptionData>()
			{
				@Override
				public boolean apply(final SubscriptionData subscription)
				{
					return subscription.getProductCode().equals(productCode);
				}
			});
		}
		catch (final NoSuchElementException e)
		{
			return null;
		}
	}

	public EMSFacade getEmsFacade()
	{
		return emsFacade;
	}

	@Required
	public void setEmsFacade(final EMSFacade emsFacade)
	{
		this.emsFacade = emsFacade;
	}

	public ProductEntitlementDao getProductEntitlementDao()
	{
		return productEntitlementDao;
	}

	@Required
	public void setProductEntitlementDao(final ProductEntitlementDao productEntitlementDao)
	{
		this.productEntitlementDao = productEntitlementDao;
	}

	public UsageChargeService getUsageChargeService()
	{
		return usageChargeService;
	}

	@Required
	public void setUsageChargeService(final UsageChargeService usageChargeService)
	{
		this.usageChargeService = usageChargeService;
	}

	public Converter<PerUnitUsageChargeModel, PerUnitUsageChargeData> getPerUnitUsageChargeConverter()
	{
		return perUnitUsageChargeConverter;
	}

	@Required
	public void setPerUnitUsageChargeConverter(
			final Converter<PerUnitUsageChargeModel, PerUnitUsageChargeData> perUnitUsageChargeConverter)
	{
		this.perUnitUsageChargeConverter = perUnitUsageChargeConverter;
	}

	public Converter<VolumeUsageChargeModel, VolumeUsageChargeData> getVolumeUsageChargeConverter()
	{
		return volumeUsageChargeConverter;
	}

	@Required
	public void setVolumeUsageChargeConverter(
			final Converter<VolumeUsageChargeModel, VolumeUsageChargeData> volumeUsageChargeConverter)
	{
		this.volumeUsageChargeConverter = volumeUsageChargeConverter;
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

	public SubscriptionFacade getSubscriptionFacade()
	{
		return subscriptionFacade;
	}

	@Required
	public void setSubscriptionFacade(final SubscriptionFacade subscriptionFacade)
	{
		this.subscriptionFacade = subscriptionFacade;
	}

	public List<SubmitUsageStrategy> getSubmitUsageStrategyList()
	{
		return submitUsageStrategyList;
	}

	@Required
	public void setSubmitUsageStrategyList(final List<SubmitUsageStrategy> submitUsageStrategyList)
	{
		this.submitUsageStrategyList = submitUsageStrategyList;
	}

}
