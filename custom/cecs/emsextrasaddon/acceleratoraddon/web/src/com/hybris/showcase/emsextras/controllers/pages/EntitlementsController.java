package com.hybris.showcase.emsextras.controllers.pages;

import com.google.common.base.Stopwatch;
import com.hybris.services.entitlements.api.EntitlementFacade;
import com.hybris.services.entitlements.api.ExecuteResult;
import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.condition.CriterionData;
import com.hybris.showcase.emsextras.BatchProductResult;
import com.hybris.showcase.emsextras.controllers.EmsextrasControllerConstants;
import com.hybris.showcase.emsextras.facades.EMSFacade;
import com.hybris.showcase.emsextras.facades.EMSProposalFacade;
import com.hybris.showcase.emsextras.facades.GrantingEntitlementsFacade;
import com.hybris.showcase.emsextras.facades.UsageChargeFacade;
import com.hybris.showcase.emsextras.strategy.SubmitUsageStrategy;
import com.hybris.showcase.servicescore.data.EntitlementProposalData;
import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.entitlementfacades.data.EntitlementData;
import de.hybris.platform.entitlementservices.exception.EntitlementFacadeException;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.subscriptionfacades.data.UsageChargeData;
import de.hybris.platform.subscriptionfacades.data.UsageChargeEntryData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.ProductData;

/**
 * CECS-242 entitlement check
 * CECS-193 Notify customer about expiring subscriptions or entitlements
 */
@Controller
@RequestMapping("/entitlements")
public class EntitlementsController extends AbstractAddOnPageController
{
	private static final Logger LOG = Logger.getLogger(EntitlementsController.class);

	@Autowired
	private EMSFacade emsFacade;

	@Autowired
	private EMSProposalFacade emsProposalFacade;

	@Autowired
	private GrantingEntitlementsFacade grantingEntitlementsFacade;

	@Autowired
	private UsageChargeFacade usageChargeFacade;
	
	@Resource(name="configurationService")
	private ConfigurationService configurationService;

	@RequestMapping(value = "/grant", method = RequestMethod.GET)
	@ResponseBody
	public List<GrantData> grantEntitlement(@RequestParam("userId") final String userId, @RequestParam("productCode") final String productCode) throws EntitlementFacadeException {
		return grantingEntitlementsFacade.grantEntitlementsFromProduct(userId, productCode);
	}

	@RequestMapping(value = "/evaluate/product", method = RequestMethod.POST, produces = "application/json")
	public String evaluateProduct(@RequestParam("entitlementType") final String entitlementType, @RequestParam("productCode") final String productCode, final Model model)
	{
		final String userUid = getUser().getUid();
		final List<CriterionData> criteria = emsFacade.createCriteriaForProduct(productCode, entitlementType);
		criteria.add(emsFacade.createTimeCriterion());

		ExecuteResult nonMeteredResult = emsFacade.check(entitlementType, criteria, userUid, true);
		ExecuteResult meteredResult = null;

		boolean granted = nonMeteredResult.isResult();

		if( ! granted ) {
			meteredResult = emsFacade.checkMetered(entitlementType, criteria, userUid, 1, true);
			granted = meteredResult.isResult();

			final List<ProductOption> options = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE);
			List<EntitlementProposalData> proposals = emsProposalFacade.getProposals(productCode, entitlementType, userUid, options);
			model.addAttribute("proposals", proposals);
		}

		List<EntitlementData> expiredGrants = emsProposalFacade.getExpiredGrants(nonMeteredResult, meteredResult);
		model.addAttribute("expiredGrants", expiredGrants);

		model.addAttribute("granted", granted);

		return EmsextrasControllerConstants.Views.Fragments.EntitlementsEvaluation;
	}

	@RequestMapping(value = "/evaluate/products", method = RequestMethod.POST)
	@ResponseBody
	public List<BatchProductResult> evaluateProducts(final Model model, @RequestParam("entitlementType") final String entitlementType, @RequestParam(value="productCodes[]", required=false) final List<String> productCodes) {
		if (productCodes == null) {
			LOG.warn("No product codes for evaluateProducts");
			return new ArrayList<>();
		}
		
		productCodes.removeIf(code -> code == null || code.isEmpty());
		
		if (productCodes.isEmpty()) {
			LOG.warn("No valid product codes for evaluateProducts");
			return new ArrayList<>();
		}
		
		final String userUid = getUser().getUid();

		final Stopwatch timer = Stopwatch.createUnstarted();
		timer.start();

		final List<BatchProductResult> results = productCodes.stream() //
				.map(productCode -> evaluateSingleProduct(productCode, entitlementType, userUid)) //
				.collect(Collectors.toList());

		timer.stop();
		if (LOG.isInfoEnabled())
		{
			LOG.info("evaluateProducts() took [" + timer.elapsed(TimeUnit.MILLISECONDS) + "] millis");
		}
		return results;
	}

	public BatchProductResult evaluateSingleProduct(String productCode, String entitlementType, String userUid) {
		final BatchProductResult result = new BatchProductResult();

		final List<CriterionData> criteria = emsFacade.createCriteriaForProduct(productCode, entitlementType);
		criteria.add(emsFacade.createTimeCriterion());

		ExecuteResult executeResult = emsFacade.check(entitlementType, criteria, userUid, true);

		boolean granted = executeResult.isResult();

		if( ! granted ) {
			executeResult = emsFacade.checkMetered(entitlementType, criteria, userUid, 1, true);
			granted = executeResult.isResult();
		}

		result.setProductCode(productCode);
		result.setGranted(granted);

		final UsageChargeEntryData chargeValue = usageChargeFacade.getUsageChargeValue(executeResult);
		result.setWithUsageCharges(chargeValue != null && chargeValue.getPrice().getValue().compareTo(BigDecimal.ZERO) > 0);
		
		if (configurationService.getConfiguration().getBoolean("showcasecommon.ppv.enabled", false)) {
			// nullsafe get/set formatedPrice
         Optional.ofNullable(chargeValue)
            .map(UsageChargeEntryData::getPrice)
            .map(PriceData::getFormattedValue)
            .ifPresent(result::setFormatedPrice);
		}
		
		return result;
	}

	@RequestMapping(value = "/submit/usage/product", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public boolean submitUsage(@RequestParam("entitlementType") final String entitlementType, @RequestParam("productCode") final String productCode)
	{
		return usageChargeFacade.submitUsage(productCode, entitlementType, 1);
	}

}
