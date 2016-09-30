package com.hybris.showcase.ybillingintegration.strategy;

import com.hybris.showcase.ybillingintegration.model.TechnicalAttributeModel;
import com.sap.document.sap.soap.functions.mc_style.CrmtIsxTechResApi;

/**
 * @author Sebastian Weiner on 2015-10-30.
 */
public interface YBillingTechAttrStrategy {

    void process(CrmtIsxTechResApi crmtIsxTechResApi, TechnicalAttributeModel techAttr, String stringSlotNumber, String yBillingBpId);
}
