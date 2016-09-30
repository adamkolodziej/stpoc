package com.hybris.showcase.guidedselling.populators;

import com.hybris.showcase.guidedselling.data.BundleComponentData;
import com.hybris.showcase.guidedselling.data.BundleOfferData;
import com.hybris.showcase.guidedselling.data.BundleOfferPopulatorParameters;
import com.hybris.showcase.guidedselling.data.ComponentData;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * CECS-85 guidedselling: bundle dependencies
 * Created by miroslaw.szot@sap.com on 2015-03-13.
 */
public class BundleDependenciesPopulator implements Populator<BundleOfferPopulatorParameters, BundleOfferData> {

    @Override
    public void populate(BundleOfferPopulatorParameters parameters, BundleOfferData offerData) throws ConversionException {
        final List<AbstractOrderEntryModel> bundleEntries = parameters.getBundleEntries();
        final List<BundleComponentData> components = offerData.getComponents();
        final Set<String> selectedComponents = new HashSet<String>();

        for (final AbstractOrderEntryModel bundleEntry : bundleEntries) {
            final BundleTemplateModel component = bundleEntry.getBundleTemplate();
            if( component != null ) {
                selectedComponents.add(component.getId());
            }
        }

        for (final BundleComponentData component : components) {
            component.setDisabled(false);

            for (final ComponentData requiredComponent : component.getRequiredComponents()) {
                if( ! selectedComponents.contains(requiredComponent.getId()) ) {
                    component.setDisabled(true);
                    component.setDisabledByDependencyMessage(requiredComponent.getName());
                    break;
                }
            }
        }
    }
}
