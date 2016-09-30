package com.hybris.showcase.emsextras.job;

import com.hybris.services.entitlements.api.EntitlementFacade;
import com.hybris.showcase.emsextras.facades.GrantingEntitlementsFacade;
import com.hybris.showcase.emsextras.model.EntitlementsSimulationExportCronJobModel;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.entitlementservices.exception.EntitlementFacadeException;
import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.site.BaseSiteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

/**
 * CECS-244 cronjob that grants all entitlements to simulate user account
 *
 * Created by miroslaw.szot@sap.com on 2015-05-27.
 */
public class EntitlementsSimulationExportJob extends AbstractJobPerformable<EntitlementsSimulationExportCronJobModel> {
    private static final Logger LOG = Logger.getLogger(EntitlementsSimulationExportJob.class);

    private static final String ALL_PE_QUERY = "SELECT {pe:pk} FROM {ProductEntitlement AS pe " +//
                " JOIN Product AS p ON {p:pk} = {pe:" + ProductEntitlementModel.SUBSCRIPTIONPRODUCT +"}}" +//
                " WHERE {p:customOffer} IS NULL OR {p:customOffer} = false";

    private GrantingEntitlementsFacade grantingEntitlementsFacade;
    private BaseSiteService baseSiteService;
    private EntitlementFacade entitlementFacade;

    @Override
    public PerformResult perform(final EntitlementsSimulationExportCronJobModel cronJob) {

        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(ALL_PE_QUERY);
        final List<ProductEntitlementModel> result = flexibleSearchService.<ProductEntitlementModel>search(searchQuery).getResult();

        if( result.isEmpty() ) {
            return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        }

        final BaseSiteModel site = cronJob.getSite();
        final String simulateUserId = cronJob.getSimulateUserId();

        entitlementFacade.revokeGrants(simulateUserId, null, null, null);

        sessionService.executeInLocalView(new SessionExecutionBody() {
            @Override
            public void executeWithoutResult() {
                baseSiteService.setCurrentBaseSite(site, true);
                for (ProductEntitlementModel productEntitlementModel : result) {
                    try {
                        grantingEntitlementsFacade.grantProductEntitlement(simulateUserId, productEntitlementModel);
                    } catch (EntitlementFacadeException e) {
                        LOG.error("Unable to process " + productEntitlementModel.getId() + " " + productEntitlementModel.getCatalogVersion().getMnemonic(), e);
                    }
                }
            }
        });

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    public GrantingEntitlementsFacade getGrantingEntitlementsFacade() {
        return grantingEntitlementsFacade;
    }

    @Required
    public void setGrantingEntitlementsFacade(GrantingEntitlementsFacade grantingEntitlementsFacade) {
        this.grantingEntitlementsFacade = grantingEntitlementsFacade;
    }

    public BaseSiteService getBaseSiteService() {
        return baseSiteService;
    }

    @Required
    public void setBaseSiteService(BaseSiteService baseSiteService) {
        this.baseSiteService = baseSiteService;
    }

    public EntitlementFacade getEntitlementFacade() {
        return entitlementFacade;
    }

    @Required
    public void setEntitlementFacade(EntitlementFacade entitlementFacade) {
        this.entitlementFacade = entitlementFacade;
    }
}
