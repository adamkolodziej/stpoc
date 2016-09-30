package com.hybris.servicesshowcase.services;

import de.hybris.platform.btg.model.BTGSegmentModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;

import java.util.List;

/**
 * Created by m.golubovic on 29.6.2015..
 */
public interface BTGSegmentEvaluationService
{
    List<BTGSegmentModel> getApplicableBtgSegments(final List<CMSSiteModel> cmsSiteModels);

    void evaluateSegment(final BTGSegmentModel segment);
}
