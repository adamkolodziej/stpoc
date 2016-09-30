package de.hybris.platform.ycommercewebservices.v2.controller;

import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commerceservices.enums.RecommendationType;
import de.hybris.platform.commercewebservicescommons.dto.product.RecommendationListWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.product.RecommendationWsDTO;
import de.hybris.platform.commercewebservicescommons.mapping.FieldSetLevelHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hybris.services.entitlements.api.ExecuteResult;
import com.hybris.services.entitlements.condition.CriterionData;
import com.hybris.showcase.emsextras.facades.EMSFacade;


/**
 * @author I321630
 *
 */
@Controller
@RequestMapping(value = "/{baseSiteId}//users/{userId}/recommendations")
public class RecommendationsController
{

	protected static final String DEFAULT_FIELD_SET = FieldSetLevelHelper.DEFAULT_LEVEL;
	protected static final String ENTITLEMENT_TYPE_VIDEO_STREAMING = "video_streaming";

	@Resource(name = "cwsProductFacade")
	private ProductFacade productFacade;
	@Resource(name = "emsFacade")
	private EMSFacade emsFacade;


	@Secured({ "ROLE_TRUSTED_CLIENT" })
	@RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
	@ResponseBody
	public RecommendationListWsDTO getRecommendations(@PathVariable final String userId, @PathVariable final String productId,
			@RequestParam(required = false, defaultValue = "5") final int resultRows,
			@RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields)
	{
		// it will be integration with yMarketing here (which returns list of recommended products?)

		final List<String> productCodes = Arrays.asList("gameofphones-s01-e01", "gameofphones-s01-e02", "creep-s01");

		final List<RecommendationWsDTO> recommendations = new ArrayList<>();

		for (final String productCode : productCodes)
		{
			final RecommendationWsDTO recommendation = new RecommendationWsDTO();
			recommendation.setProductId(productCode);
			recommendation.setType(checkEntitlements(productCode, ENTITLEMENT_TYPE_VIDEO_STREAMING, userId));
			recommendations.add(recommendation);
		}

		final RecommendationListWsDTO results = new RecommendationListWsDTO();
		results.setRecommendations(recommendations);

		return results;
	}

	private RecommendationType checkEntitlements(final String productCode, final String entitlementType, final String userId)
	{
		final List<CriterionData> criteria = emsFacade.createCriteriaForProduct(productCode, entitlementType);
		criteria.add(emsFacade.createTimeCriterion());

		ExecuteResult result = emsFacade.check(entitlementType, criteria, userId, true);

		boolean granted = result.isResult();

		if (!granted)
		{
			result = emsFacade.checkMetered(entitlementType, criteria, userId, 1, true);
			granted = result.isResult();
			return RecommendationType.SUBSCRIPTION;
		}

		final String free2watchEntitlement = "free2watch";

		final long free2watch = result.getGrantDataList().stream().filter(x ->
			x.getGrantSource().equals(free2watchEntitlement)
		).count();

		return free2watch > 0 ? RecommendationType.FREE : RecommendationType.INCLUDED;
	}

}
