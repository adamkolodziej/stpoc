package com.hybris.showcase.emsextras.populators.entitlements;

import com.hybris.showcase.emsextras.facades.UsageChargeFacade;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.entitlementfacades.data.EntitlementData;
import de.hybris.platform.entitlementservices.model.ProductEntitlementModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.subscriptionfacades.data.UsageChargeData;
import de.hybris.platform.subscriptionfacades.data.UsageUnitData;
import de.hybris.platform.subscriptionservices.model.UsageUnitModel;
import org.springframework.beans.factory.annotation.Required;

/**
 * CECS-364 Configure your package page needs reworking
 * Created by m.golubovic on 10.6.2015..
 */
public class EntitlementUsageChargePopulator<SOURCE extends ProductEntitlementModel, TARGET extends EntitlementData> implements Populator<SOURCE, TARGET>
{
    private UsageChargeFacade usageChargeFacade;
    private Converter<UsageUnitModel, UsageUnitData> usageUnitConverter;

    @Override
    public void populate(SOURCE source, TARGET target) throws ConversionException
    {
        final UsageChargeData usageChargeData = usageChargeFacade.getUsageChargeDataForProductEntitlementId(source.getId());
        target.setUsageCharge(usageChargeData);

        if( source.getBillingUsageUnit() != null ) {
            target.setUsageUnit(usageUnitConverter.convert(source.getBillingUsageUnit()));
        }
        else if( source.getEntitlement().getDefaultUsageUnit() != null ) {
            target.setUsageUnit(usageUnitConverter.convert(source.getEntitlement().getDefaultUsageUnit()));
        }
    }

    public UsageChargeFacade getUsageChargeFacade() {
        return usageChargeFacade;
    }

    @Required
    public void setUsageChargeFacade(UsageChargeFacade usageChargeFacade) {
        this.usageChargeFacade = usageChargeFacade;
    }

    public Converter<UsageUnitModel, UsageUnitData> getUsageUnitConverter() {
        return usageUnitConverter;
    }

    @Required
    public void setUsageUnitConverter(Converter<UsageUnitModel, UsageUnitData> usageUnitConverter) {
        this.usageUnitConverter = usageUnitConverter;
    }
}
