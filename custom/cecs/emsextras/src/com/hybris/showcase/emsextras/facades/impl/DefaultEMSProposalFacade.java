package com.hybris.showcase.emsextras.facades.impl;

import com.hybris.services.entitlements.api.ExecuteResult;
import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.condition.CriterionData;
import com.hybris.showcase.emsextras.facades.EMSFacade;
import com.hybris.showcase.emsextras.facades.EMSProposalFacade;
import com.hybris.showcase.servicescore.data.EntitlementProposalData;

import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.entitlementfacades.data.EntitlementData;
import de.hybris.platform.entitlementservices.entitlement.EntitlementService;
import de.hybris.platform.entitlementservices.model.EntitlementModel;
import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.time.TimeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.*;

/**
 * CECS-193 Notify customer about expiring subscriptions or entitlements
 *
 * Created by miroslaw.szot@sap.com on 2015-05-20.
 */
public class DefaultEMSProposalFacade implements EMSProposalFacade {
    private static final Logger LOG = Logger.getLogger(DefaultEMSProposalFacade.class);

    private EMSFacade emsFacade;
    private ProductFacade productFacade;
    private FlexibleSearchService flexibleSearchService;
    private UrlResolver<ProductModel> activationUrlResolver;
    private EntitlementService entitlementService;
    private Converter<EntitlementModel, EntitlementData> entitlementModelToDataConverter;
    private Populator<GrantData, EntitlementData> grantEntitlementPopulator;
    private String simulateUserId;
    private TimeService timeService;

    @Override
    public List<EntitlementProposalData> getProposals(String productCode, String entitlementType, String userUid, Collection<ProductOption> options) {
        Collection<GrantData> grants = simulateCheck(entitlementType, productCode);

        Set<String> productsFound = new HashSet<>();

        List<EntitlementProposalData> proposals = new ArrayList<>();
        for (GrantData grant : grants) {
            try {
                ProductEntitlementModel example = new ProductEntitlementModel();
                example.setId(grant.getGrantSource());
                ProductEntitlementModel productEntitlement = flexibleSearchService.getModelByExample(example);

                final ProductModel product = productEntitlement.getSubscriptionProduct();
                if( productsFound.contains(product.getCode())) {
                    continue;
                }
                productsFound.add(product.getCode());

                final EntitlementProposalData proposal = new EntitlementProposalData();
                proposal.setProduct(productFacade.getProductForOptions(product, options));
                proposal.setActivationUrl(activationUrlResolver.resolve(product));
                proposals.add(proposal);
            } catch(ModelNotFoundException e) {
                LOG.info("product entitlement not found: " + grant.getGrantSource() + ", skipping...");
            }
        }

        return proposals;
    }

     protected Collection<GrantData> simulateCheck(String entitlementType, String productCode) {
        final List<CriterionData> criteria = emsFacade.createCriteriaForProduct(productCode, entitlementType);

        ExecuteResult result = emsFacade.check(entitlementType, criteria, simulateUserId, true);
        boolean granted = result.isResult();

        Collection<GrantData> grants = Collections.emptyList();
        if( granted ) {
            grants = result.getGrantDataList();
        } else {
            result = emsFacade.checkMetered(entitlementType, criteria, simulateUserId, 1, true);
            granted = result.isResult();
            if( granted ) {
                grants = result.getGrantDataList();
            }
        }
        return grants;
    }

    @Override
    public List<EntitlementData> getExpiredGrants(ExecuteResult... results) {
        TreeSet<GrantData> grants = new TreeSet<>(new Comparator<GrantData>() {
            @Override
            public int compare(GrantData o1, GrantData o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });

        for (ExecuteResult result : results) {
            if( result != null ) {
                grants.addAll(result.getGrantDataList());
            }
        }

        final List<EntitlementData> result = convertGrantsToEntitlements(grants);
        final List<EntitlementData> expiredGrants = new ArrayList<>();

        final Date currentTime = timeService.getCurrentTime();

        for (EntitlementData entitlementData : result) {
            if( entitlementData.getRemainingQuantity() == 0 && entitlementData.getUsageUnit() != null ) {
                expiredGrants.add(entitlementData);
            }

            if( entitlementData.getEndTime() != null && ! currentTime.before(entitlementData.getEndTime()) ) {
                expiredGrants.add(entitlementData);
            }
        }

        return expiredGrants;
    }

    protected List<EntitlementData> convertGrantsToEntitlements(Collection<GrantData> grants) {
        final Map<String, EntitlementModel> models = new HashMap<>();
        final List<EntitlementData> result = new ArrayList<>();
        for (GrantData currentGrant : grants) {
            final String entitlementId = currentGrant.getEntitlementType();


            EntitlementData entitlementData;
            try
            {
                EntitlementModel entitlementModel = getEntitlementById(entitlementId, models);
                entitlementData = entitlementModelToDataConverter.convert(entitlementModel);
                grantEntitlementPopulator.populate(currentGrant, entitlementData);
                result.add(entitlementData);
            }
            catch (ModelNotFoundException e)
            {
                // ignore remnants
            }
        }
        return result;
    }

    // Duplicated from de.hybris.platform.entitlementfacades.impl.DefaultCoreEntitlementFacade
    protected EntitlementModel getEntitlementById(final String id, final Map<String, EntitlementModel> cache) throws ModelNotFoundException
    {
        final EntitlementModel entitlementModel;
        if (cache.containsKey(id))
        {
            entitlementModel = cache.get(id);
        }
        else
        {
            entitlementModel = entitlementService.getEntitlementForCode(id);
            cache.put(entitlementModel.getId(), entitlementModel);
        }
        return entitlementModel;
    }

    protected Collection<GrantData> extractGrants(ExecuteResult nonMeteredResult, ExecuteResult meteredResult) {
        TreeSet<GrantData> grants = new TreeSet<>(new Comparator<GrantData>() {
            @Override
            public int compare(GrantData o1, GrantData o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });

        if( meteredResult != null ) {
            grants.addAll(meteredResult.getGrantDataList());
        }

        grants.addAll(nonMeteredResult.getGrantDataList());
        return grants;
    }

    public EMSFacade getEmsFacade() {
        return emsFacade;
    }

    @Required
    public void setEmsFacade(EMSFacade emsFacade) {
        this.emsFacade = emsFacade;
    }

    public ProductFacade getProductFacade() {
        return productFacade;
    }

    @Required
    public void setProductFacade(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    @Required
    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    public UrlResolver<ProductModel> getActivationUrlResolver() {
        return activationUrlResolver;
    }

    @Required
    public void setActivationUrlResolver(UrlResolver<ProductModel> activationUrlResolver) {
        this.activationUrlResolver = activationUrlResolver;
    }

    public EntitlementService getEntitlementService() {
        return entitlementService;
    }

    @Required
    public void setEntitlementService(EntitlementService entitlementService) {
        this.entitlementService = entitlementService;
    }

    public Converter<EntitlementModel, EntitlementData> getEntitlementModelToDataConverter() {
        return entitlementModelToDataConverter;
    }

    @Required
    public void setEntitlementModelToDataConverter(Converter<EntitlementModel, EntitlementData> entitlementModelToDataConverter) {
        this.entitlementModelToDataConverter = entitlementModelToDataConverter;
    }

    public Populator<GrantData, EntitlementData> getGrantEntitlementPopulator() {
        return grantEntitlementPopulator;
    }

    @Required
    public void setGrantEntitlementPopulator(Populator<GrantData, EntitlementData> grantEntitlementPopulator) {
        this.grantEntitlementPopulator = grantEntitlementPopulator;
    }

    public String getSimulateUserId() {
        return simulateUserId;
    }

    @Required
    public void setSimulateUserId(String simulateUserId) {
        this.simulateUserId = simulateUserId;
    }

    public TimeService getTimeService() {
        return timeService;
    }

    @Required
    public void setTimeService(TimeService timeService) {
        this.timeService = timeService;
    }
}
