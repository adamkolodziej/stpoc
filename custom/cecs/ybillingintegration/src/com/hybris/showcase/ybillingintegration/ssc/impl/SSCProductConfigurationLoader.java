package com.hybris.showcase.ybillingintegration.ssc.impl;

import static java.lang.System.err;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.core.Registry;
import de.hybris.platform.sap.productconfig.runtime.interf.ConfigurationProvider;
import de.hybris.platform.sap.productconfig.runtime.interf.impl.KBKeyImpl;
import de.hybris.platform.sap.productconfig.runtime.interf.model.ConfigModel;
import de.hybris.platform.sap.productconfig.runtime.interf.model.CsticModel;
import de.hybris.platform.sap.productconfig.runtime.interf.model.CsticValueModel;
import de.hybris.platform.sap.productconfig.services.intf.ProductConfigurationService;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.showcase.ybillingintegration.ssc.ProductConfigurationLoader;

/**
 * CECS-381 Testing implementation for SSC
 *
 * Created by miroslaw.szot@sap.com on 2015-10-14.
 */
public class SSCProductConfigurationLoader implements ProductConfigurationLoader {
    private ConfigurationProvider sapProductConfigConfigurationProvider;
    private ProductConfigurationService productConfigurationService;
    private BaseSiteService baseSiteService;
    private CMSSiteService cmsSiteService;
    private SessionService sessionService;

    @Override
    public ConfigModel getConfigurationModel(String code, CMSSiteModel siteModel, CatalogVersionModel catalogVersionModel) {
        return sessionService.executeInLocalView(new SessionExecutionBody() {
            @Override
            public Object execute() {
                baseSiteService.setCurrentBaseSite(siteModel, true);

                try {
                    cmsSiteService.setCurrentCatalogVersion(catalogVersionModel);
                } catch (CMSItemNotFoundException e) {
                    e.printStackTrace();
                }
                return getConfigurationModel(code);
            }
        });
    }

    @Override
    public ConfigModel getConfigurationModel(String code) {
        ConfigModel configModel = null;
        try {
            if( sapProductConfigConfigurationProvider == null ) {
                sapProductConfigConfigurationProvider = (ConfigurationProvider) Registry.getApplicationContext().getBean("sapProductConfigConfigurationProvider");
            }
            KBKeyImpl kbKeyImpl = new KBKeyImpl(code, null, null, null);

            configModel = sapProductConfigConfigurationProvider.createDefaultConfiguration(kbKeyImpl);
            dumpConfigInfo(configModel);
           
//            if( productConfigurationService == null ) {
//                productConfigurationService = (ProductConfigurationService) Registry.getApplicationContext().getBean("sapProductConfigConfigurationService");
//            }
//            configModel = productConfigurationService.createDefaultConfiguration(kbKeyImpl);
//            dumpConfigInfo(configModel);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return configModel;
    }

    private void dumpConfigInfo(ConfigModel configModel) {
        err.println();
        err.println("config id: " + configModel.getId());
        err.println("config instance id: " + configModel.getRootInstance().getId());
        err.println("config instance name: " + configModel.getRootInstance().getName());
        err.println("name: " + configModel.getRootInstance().getLanguageDependentName());
        err.println();
        final List<CsticModel> cstics = configModel.getRootInstance().getCstics();
        for (CsticModel cstic : cstics) {
            err.println("cstic " + cstic.getName());
            err.println("cstic " + cstic.getLanguageDependentName());
            final List<CsticValueModel> assignableValues = cstic.getAssignableValues();
            for (CsticValueModel value : assignableValues) {
                err.println("cstic value: '" + value.getName() + "' " + value.getDeltaPrice().getPriceValue() + " " + value.getDeltaPrice().getCurrency());
            }
            err.println();
        }
    }

    public BaseSiteService getBaseSiteService() {
        return baseSiteService;
    }

    @Required
    public void setBaseSiteService(BaseSiteService baseSiteService) {
        this.baseSiteService = baseSiteService;
    }

    public CMSSiteService getCmsSiteService() {
        return cmsSiteService;
    }

    @Required
    public void setCmsSiteService(CMSSiteService cmsSiteService) {
        this.cmsSiteService = cmsSiteService;
    }

    public SessionService getSessionService() {
        return sessionService;
    }

    @Required
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }
}
