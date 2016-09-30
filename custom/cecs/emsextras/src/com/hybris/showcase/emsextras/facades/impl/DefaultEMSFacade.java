package com.hybris.showcase.emsextras.facades.impl;

import com.hybris.services.entitlements.api.Actions;
import com.hybris.services.entitlements.api.EntitlementFacade;
import com.hybris.services.entitlements.api.ExecuteResult;
import com.hybris.services.entitlements.condition.CriterionData;
import com.hybris.showcase.emsextras.facades.EMSFacade;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.time.TimeService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * CECS-242 entitlement check
 *
 * Created by miroslaw.szot@sap.com on 2015-05-20.
 */
public class DefaultEMSFacade implements EMSFacade {
    private static final Logger LOG = Logger.getLogger(DefaultEMSFacade.class);
    private ProductService productService;
    private Map<String, Converter<ProductModel, List<CriterionData>>> criteriaConvertersMap;
    private EntitlementFacade entitlementFacade;
    private TimeService timeService;
    private UserService userService;

    @Override
    public List<CriterionData> createCriteriaForProduct(String productCode, String entitlementType) {
        final Converter<ProductModel, List<CriterionData>> converter = criteriaConvertersMap.get(entitlementType);
        if( converter == null ) {
            throw new IllegalArgumentException(entitlementType + " is not supported, unable to create criteria");
        }
        final ProductModel productModel = productService.getProductForCode(productCode);
        return converter.convert(productModel);
    }

    @Override
    public ExecuteResult check(String entitlementType, List<CriterionData> criteria, boolean details) {
        final String currentUserId = userService.getCurrentUser().getUid();
        return check(entitlementType, criteria, currentUserId, details);
    }

    @Override
    public ExecuteResult check(String entitlementType, List<CriterionData> criteria, String userUid, boolean details) {
        final ExecuteResult result = entitlementFacade.execute(Actions.CHECK, userUid, entitlementType, criteria, details);
        return result;
    }

    @Override
    public ExecuteResult checkMetered(String entitlementType, List<CriterionData> criteria, int quantity, boolean details) {
        final String currentUserId = userService.getCurrentUser().getUid();
        return checkMetered(entitlementType, criteria, currentUserId, quantity, details);
    }

    @Override
    public ExecuteResult checkMetered(String entitlementType, List<CriterionData> criteria, String userUid, int quantity, boolean details) {
        criteria.add(createMeteredCriterion(quantity));
        final ExecuteResult result = entitlementFacade.execute(Actions.CHECK, userUid, entitlementType, criteria, details);
        return result;
    }

    @Override
    public ExecuteResult use(String entitlementType, List<CriterionData> criteria, int quantity, boolean details) {
        final String currentUserId = userService.getCurrentUser().getUid();
        return use(entitlementType, criteria, currentUserId, quantity, details);
    }

    @Override
    public ExecuteResult use(String entitlementType, List<CriterionData> criteria, String userUid, int quantity, boolean details) {
        criteria.add(createMeteredCriterion(quantity));
        final ExecuteResult result = entitlementFacade.execute(Actions.USE, userUid, entitlementType, criteria, details);
        return result;
    }

    @Override
    public CriterionData createMeteredCriterion(int quantity) {
        final CriterionData criterionData = new CriterionData();
        criterionData.setType("metered");
        criterionData.setProperty("quantity", String.valueOf(quantity));
        return criterionData;
    }

    @Override
    public CriterionData createTimeCriterion(Date date) {
        final CriterionData crit = new CriterionData();
        crit.setType("timeframe");
        final SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FRAME_DATE_FORMAT);
        crit.setProperty("time", dateFormat.format(date));
        return crit;
    }

    @Override
    public CriterionData createTimeCriterion() {
        return createTimeCriterion(timeService.getCurrentTime());
    }

    public ProductService getProductService() {
        return productService;
    }

    @Required
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public Map<String, Converter<ProductModel, List<CriterionData>>> getCriteriaConvertersMap() {
        return criteriaConvertersMap;
    }

    @Required
    public void setCriteriaConvertersMap(Map<String, Converter<ProductModel, List<CriterionData>>> criteriaConvertersMap) {
        this.criteriaConvertersMap = criteriaConvertersMap;
    }

    public EntitlementFacade getEntitlementFacade() {
        return entitlementFacade;
    }

    @Required
    public void setEntitlementFacade(EntitlementFacade entitlementFacade) {
        this.entitlementFacade = entitlementFacade;
    }

    public TimeService getTimeService() {
        return timeService;
    }

    @Required
    public void setTimeService(TimeService timeService) {
        this.timeService = timeService;
    }

    public UserService getUserService() {
        return userService;
    }

    @Required
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
