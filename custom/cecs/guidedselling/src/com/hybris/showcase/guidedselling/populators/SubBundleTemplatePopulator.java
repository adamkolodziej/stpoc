package com.hybris.showcase.guidedselling.populators;

import de.hybris.platform.configurablebundlefacades.converters.populator.BundleTemplatePopulator;
import de.hybris.platform.configurablebundlefacades.data.BundleTemplateData;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mgolubovic on 20.3.2015..
 */
public class SubBundleTemplatePopulator<SOURCE extends BundleTemplateModel, TARGET extends BundleTemplateData>  extends BundleTemplatePopulator<SOURCE, TARGET>
{
    private Converter<BundleTemplateModel, BundleTemplateData> bundleTemplateConverter;

    @Override
    public void populate(SOURCE source, TARGET target)
    {
        super.populate(source, target);
        final List<BundleTemplateData> childTemplatesData = new ArrayList<>();
        for(final BundleTemplateModel childBundleTemplateModel : source.getChildTemplates())
        {
            final BundleTemplateData childBundleTemplateData = new BundleTemplateData();
            bundleTemplateConverter.convert(childBundleTemplateModel, childBundleTemplateData);
            childTemplatesData.add(childBundleTemplateData);
        }

        target.setBundleTemplates(childTemplatesData);
    }

    public Converter<BundleTemplateModel, BundleTemplateData> getBundleTemplateConverter() {
        return bundleTemplateConverter;
    }

    @Required
    public void setBundleTemplateConverter(Converter<BundleTemplateModel, BundleTemplateData> bundleTemplateConverter) {
        this.bundleTemplateConverter = bundleTemplateConverter;
    }
}
