/**
 * 
 */
package com.hybris.showcase.services;

import de.hybris.platform.btg.model.BTGSegmentModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Collection;


/**
 * @author n.pavlovic
 * 
 */
public interface OfflineBTGEvaluationService
{
	void evaluateSegments(CMSSiteModel... sites);

	void evaluateSegments(Collection<CustomerModel> customers, Collection<CMSSiteModel> siteList, boolean invalidateResults);

	void evaluateSiteSegments(CMSSiteModel site, Collection<CustomerModel> customers, boolean invalidateResults);

	void evaluateSegment(BTGSegmentModel segment, Collection<CustomerModel> customers, Collection<CMSSiteModel> siteList,
			boolean invalidateResults);
}
