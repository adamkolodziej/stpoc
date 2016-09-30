package com.hybris.showcase.emsextras.populators.entitlements;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.entitlementfacades.data.EntitlementData;
import de.hybris.platform.entitlementservices.model.EntitlementModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.subscriptionfacades.data.UsageUnitData;
import de.hybris.platform.subscriptionservices.model.UsageUnitModel;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by miroslaw.szot@sap.com on 2015-08-06.
 */
public class UsageEntitlementModelToDataPopulator<SOURCE extends EntitlementModel, TARGET extends EntitlementData> implements Populator<SOURCE, TARGET> {
    private Converter<UsageUnitModel, UsageUnitData> usageUnitConverter;

    @Override
    public void populate(SOURCE source, TARGET target) throws ConversionException {
        if (source.getDefaultUsageUnit() != null)
        {
            target.setUsageUnit(getUsageUnitConverter().convert(source.getDefaultUsageUnit()));
        }
    }

    public Converter<UsageUnitModel, UsageUnitData> getUsageUnitConverter() {
        return usageUnitConverter;
    }

    @Required
    public void setUsageUnitConverter(Converter<UsageUnitModel, UsageUnitData> usageUnitConverter) {
        this.usageUnitConverter = usageUnitConverter;
    }
}
