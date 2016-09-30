package com.hybris.showcase.ybillingintegration.strategy.impl;

import com.hybris.showcase.ybillingintegration.constants.YbillingintegrationConstants;
import com.hybris.showcase.ybillingintegration.model.TechnicalAttributeModel;
import com.hybris.showcase.ybillingintegration.strategy.YBillingTechAttrStrategy;
import com.sap.document.sap.soap.functions.mc_style.CrmtIsxTechResApi;


/**
 * @author Sebastian Weiner on 2015-10-30.
 */
public class YBillingKnownTechAttrStrategy implements YBillingTechAttrStrategy
{

	@Override
	public void process(final CrmtIsxTechResApi crmtIsxTechResApi, final TechnicalAttributeModel techAttr,
			final String stringSlotNumber, final String yBillingBpId)
	{
		if (YbillingintegrationConstants.PHONE_NUMBER_TECH_ATTR_NAME.equals(techAttr.getDataId())
				|| YbillingintegrationConstants.MAC_ADDRESS_TECH_ATTR_NAME.equals(techAttr.getDataId())
				|| YbillingintegrationConstants.TV_ID_TECH_ATTR_NAME.equals(techAttr.getDataId()))
		{
			crmtIsxTechResApi.setTrType(techAttr.getDataType());
			crmtIsxTechResApi.setTrSlot(stringSlotNumber);
			if (null != yBillingBpId)
			{
				//to simplify, businessPartnerId used as technicalAttrId. it is used in chargableItemChargingWS
				crmtIsxTechResApi.setTrObjId(yBillingBpId);
			}
			//			else
			//			{
			//				crmtIsxTechResApi.setTrObjId(createRandomNumber(10));
			//			}
		}
	}

	public final static String createRandomNumber(final long len)
	{
		if (len > 18)
		{
			throw new IllegalStateException("To many digits");
		}
		final long tLen = (long) Math.pow(10, len - 1) * 9;

		final long number = (long) (Math.random() * tLen) + (long) Math.pow(10, len - 1) * 1;

		final String tVal = number + "";
		if (tVal.length() != len)
		{
			throw new IllegalStateException("The random number '" + tVal + "' is not '" + len + "' digits");
		}
		return tVal;
	}
}
