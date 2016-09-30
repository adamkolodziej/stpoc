package com.hybris.showcase.ybillingintegration.strategy.impl;

import com.hybris.showcase.ybillingintegration.constants.YbillingintegrationConstants;
import com.hybris.showcase.ybillingintegration.model.TechnicalAttributeModel;
import com.hybris.showcase.ybillingintegration.strategy.YBillingTechAttrStrategy;
import com.sap.document.sap.soap.functions.mc_style.CrmtIsxTechResApi;
import org.apache.log4j.Logger;

/**
 * @author Sebastian Weiner on 2015-10-30.
 */
public class DefaultYBillingTechAttrStrategy implements YBillingTechAttrStrategy {

    private static final Logger LOGGER = Logger.getLogger(DefaultYBillingTechAttrStrategy.class);

    @Override
    public void process(CrmtIsxTechResApi crmtIsxTechResApi, TechnicalAttributeModel techAttr, String stringSlotNumber, String yBillingBpId) {
        if(!YbillingintegrationConstants.PHONE_NUMBER_TECH_ATTR_NAME.equals(techAttr.getDataId()) &&
                !YbillingintegrationConstants.MAC_ADDRESS_TECH_ATTR_NAME.equals(techAttr.getDataId()) &&
                !YbillingintegrationConstants.TV_ID_TECH_ATTR_NAME.equals(techAttr.getDataId())){
            LOGGER.warn("Unknown Technical Attribute ID: ### " + techAttr.getDataId() + " ###");
        }
    }
}
