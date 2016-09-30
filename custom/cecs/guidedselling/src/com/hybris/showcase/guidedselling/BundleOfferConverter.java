package com.hybris.showcase.guidedselling;

import com.hybris.showcase.guidedselling.data.BundleOfferData;
import com.hybris.showcase.guidedselling.data.BundleOfferPopulatorParameters;
import com.hybris.showcase.guidedselling.enums.BundleComponentType;
import de.hybris.platform.configurablebundleservices.bundle.BundleTemplateService;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.converters.impl.AbstractPopulatingConverter;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miroslaw.szot@sap.com on 2015-03-13.
 */
public class BundleOfferConverter extends AbstractPopulatingConverter<BundleOfferPopulatorParameters, BundleOfferData> {

    private BundleTemplateService bundleTemplateService;

    @Override
    public void populate(BundleOfferPopulatorParameters parameters, BundleOfferData offerData) {
        final int bundleNo = parameters.getBundleNo();
        final AbstractOrderModel order = parameters.getOrder();
        offerData.setBundleNo(bundleNo);
        offerData.setOrderCode(order.getCode());
        offerData.setChangeOrder(order.getOrderChanges() != null);

        final List<AbstractOrderEntryModel> bundleEntries = extractBundleEntries(bundleNo, order);
        parameters.setBundleEntries(bundleEntries);

        populateBundleSource(offerData, bundleEntries);

        BundleTemplateModel bundleTemplate = bundleEntries.iterator().next().getBundleTemplate();
        bundleTemplate = getBundleTemplateService().getRootBundleTemplate(bundleTemplate);
        parameters.setBundleTemplate(bundleTemplate);
        offerData.setRootBundleTemplateId(bundleTemplate.getId());

        super.populate(parameters, offerData);
    }

    protected void populateBundleSource(BundleOfferData offerData, List<AbstractOrderEntryModel> bundleEntries) {
        String conditionalProductCode = null;
        for (AbstractOrderEntryModel entry : bundleEntries) {
            if( entry.getPackage() != null ) {
                offerData.setSourcePackageCode(entry.getPackage().getCode());
                break;
            }
            final BundleTemplateModel bundleTemplate = entry.getBundleTemplate();
            if( bundleTemplate != null && bundleTemplate.getBundleComponentType() == BundleComponentType.CONDITIONAL ) {
                conditionalProductCode = entry.getProduct().getCode();
            }
        }

        if( StringUtils.isBlank(offerData.getSourcePackageCode()) ) {
            offerData.setSourceProductCode(conditionalProductCode);
        }
    }

    protected List<AbstractOrderEntryModel> extractBundleEntries(final int bundleNo, final AbstractOrderModel order)
	{
		final List<AbstractOrderEntryModel> bundleEntries = new ArrayList<>();
		final List<AbstractOrderEntryModel> entries = order.getEntries();

		for (final AbstractOrderEntryModel entry : entries)
		{
			final Integer entryBundleNo = entry.getBundleNo();

			if (entryBundleNo != null && entryBundleNo.equals(Integer.valueOf(bundleNo)))
			{
				bundleEntries.add(entry);
			}
		}
		return bundleEntries;
	}

    public BundleTemplateService getBundleTemplateService() {
        return bundleTemplateService;
    }

    @Required
    public void setBundleTemplateService(BundleTemplateService bundleTemplateService) {
        this.bundleTemplateService = bundleTemplateService;
    }
}
