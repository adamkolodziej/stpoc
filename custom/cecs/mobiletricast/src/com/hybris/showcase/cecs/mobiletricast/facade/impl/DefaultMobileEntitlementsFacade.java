package com.hybris.showcase.cecs.mobiletricast.facade.impl;

import com.hybris.services.entitlements.api.EntitlementFacade;
import com.hybris.services.entitlements.api.ExecuteResult;
import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.condition.ConditionData;
import com.hybris.services.entitlements.condition.CriterionData;
import com.hybris.showcase.cecs.mobiletricast.constants.MobiletricastConstants;
import com.hybris.showcase.cecs.mobiletricast.dao.MobileEntitlementTokenDao;
import com.hybris.showcase.cecs.mobiletricast.data.GrantMobileEntitlementResultData;
import com.hybris.showcase.cecs.mobiletricast.exceptions.MobileEntitlementAuthorizationException;
import com.hybris.showcase.cecs.mobiletricast.facade.MobileEntitlementsFacade;
import com.hybris.showcase.cecs.mobiletricast.model.MobileEntitlementTokenModel;
import com.hybris.showcase.emsextras.facades.EMSFacade;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class DefaultMobileEntitlementsFacade implements MobileEntitlementsFacade {

    private static final String MOBILE_APP_URL_CONFIG_PARAM = "tricast.mobile.app.url";

    private static final Logger LOG = Logger.getLogger(DefaultMobileEntitlementsFacade.class);

    private MobileEntitlementTokenDao mobileEntitlementTokenDao;
    private UserService userService;
    private EntitlementFacade entitlementFacade;
    private ModelService modelService;
    private ConfigurationService configurationService;
    private CMSSiteService cmsSiteService;
    private EMSFacade emsFacade;

    @Override
    public GrantMobileEntitlementResultData grantMobileEntitlement(final String authToken) {
        final MobileEntitlementTokenModel token = getMobileEntitlementTokenDao().getToken(authToken);

        checkTokenValidity(token);
        useToken(token);

        final CustomerModel customer = token.getCustomer();
        if (entitlementNotGranted(customer)) {
            addGrant(customer);
        }

        final GrantMobileEntitlementResultData resultData = new GrantMobileEntitlementResultData();
        resultData.setAppUrl(generateAppUrl());
        return resultData;
    }

    private boolean entitlementNotGranted(final CustomerModel customer) {
        final List<CriterionData> criteria = new ArrayList<>();
        criteria.add(emsFacade.createMeteredCriterion(1));

        ExecuteResult executeResult =
                emsFacade.check(MobiletricastConstants.MOBILE_ACCESS_ENTITLEMENT, criteria, customer.getUid(), true);

        return CollectionUtils.isEmpty(executeResult.getGrantDataList());
    }

    private String generateEntitlementSource() {
        return getCmsSiteService().getCurrentSite().getUid();
    }

    private void checkTokenValidity(final MobileEntitlementTokenModel token) {
        if (token == null) {
            LOG.info("Access denied: no token.");
            throw new MobileEntitlementAuthorizationException("No given token.");
        }

        final boolean oneTimeTokens = getConfigurationService().getConfiguration()
                .getBoolean("mobiletricast.tokens.onetime", false);
        if (oneTimeTokens && token.isUsed()) {
            LOG.info(String.format("Access denied: token (%s) is already used.", token.getToken()));
            throw new MobileEntitlementAuthorizationException("Token is already used.");
        }
    }


    private void useToken(final MobileEntitlementTokenModel token) {
        token.setUsed(true);
        getModelService().save(token);
    }

    private void addGrant(final CustomerModel currentCustomer) {
        final GrantData grant = new GrantData();
        grant.setEntitlementType(MobiletricastConstants.MOBILE_ACCESS_ENTITLEMENT);
        grant.setUserId(currentCustomer.getUid());

        final String source = generateEntitlementSource();
        grant.setGrantSourceId(source);
        grant.setGrantSource(source);

        final ConditionData condition = new ConditionData();
        condition.setType("metered");
        condition.setProperty("maxQuantity", String.valueOf(3));
        grant.setCondition(condition);

        getEntitlementFacade().createGrant(grant);
    }

    private String generateAppUrl() {
        return getConfigurationService().getConfiguration().getString(MOBILE_APP_URL_CONFIG_PARAM, "http://APP_URL_NOT_SPECIFIED");
    }

    @Override
    public String generateToken() {
        final CustomerModel currentCustomer = (CustomerModel) getUserService().getCurrentUser();
        return generateToken(currentCustomer);
    }

    @Override
    public String generateToken(final CustomerModel currentCustomer) {
        if (getUserService().isAnonymousUser(currentCustomer)) {
            LOG.info("Token is not generated: not logged in user.");
            throw new MobileEntitlementAuthorizationException("Not logged in user!");
        }
        final MobileEntitlementTokenModel token = new MobileEntitlementTokenModel();
        token.setToken(UUID.randomUUID().toString());
        token.setCustomer(currentCustomer);
        token.setUsed(false);
        getModelService().save(token);
        return token.getToken();
    }

    protected MobileEntitlementTokenDao getMobileEntitlementTokenDao() {
        return mobileEntitlementTokenDao;
    }

    @Required
    public void setMobileEntitlementTokenDao(final MobileEntitlementTokenDao mobileEntitlementTokenDao) {
        this.mobileEntitlementTokenDao = mobileEntitlementTokenDao;
    }

    protected UserService getUserService() {
        return userService;
    }

    @Required
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    protected EntitlementFacade getEntitlementFacade() {
        return entitlementFacade;
    }

    @Required
    public void setEntitlementFacade(final EntitlementFacade entitlementFacade) {
        this.entitlementFacade = entitlementFacade;
    }

    protected ModelService getModelService() {
        return modelService;
    }

    @Required
    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

    protected ConfigurationService getConfigurationService() {
        return configurationService;
    }

    @Required
    public void setConfigurationService(final ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    protected CMSSiteService getCmsSiteService() {
        return cmsSiteService;
    }

    @Required
    public void setCmsSiteService(final CMSSiteService cmsSiteService) {
        this.cmsSiteService = cmsSiteService;
    }

    public EMSFacade getEmsFacade() {
        return emsFacade;
    }

    @Required
    public void setEmsFacade(EMSFacade emsFacade) {
        this.emsFacade = emsFacade;
    }
}
