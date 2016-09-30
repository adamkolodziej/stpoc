/**
 *
 */
package com.hybris.showcase.guidedselling.daos.impl;


import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.configurablebundleservices.daos.impl.DefaultBundleTemplateDao;
import de.hybris.platform.configurablebundleservices.enums.BundleTemplateStatusEnum;
import de.hybris.platform.configurablebundleservices.model.BundleTemplateModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;


/**
 * @author Rafal Zymla
 *
 */
public class GuidedSellingBundleTemplateDao extends DefaultBundleTemplateDao
{

	private static final String RESTRICTION_ONLY_LATEST_APPROVED_BUNDLE_TEMPLATE = " AND {version}  = ({{select MAX({b:version}) FROM {"
			+ BundleTemplateModel._TYPECODE + " AS b JOIN BundleTemplateStatus AS st ON {b:status} = {st:pk} JOIN "
			+ BundleTemplateStatusEnum._TYPECODE
			+ " AS ste ON {st:status} = {ste:pk} } WHERE {b:id} = {bt:id} AND {b:catalogVersion} = {bt:catalogVersion}  AND {ste:code} = 'approved' }})";
	private static final String FIND_BUNDLETEMPLATE_QUERY = "SELECT {" + BundleTemplateModel.PK + "} FROM {"
			+ BundleTemplateModel._TYPECODE + " AS bt} where {" + BundleTemplateModel.ID + "}= ?uid";
			//+ RESTRICTION_ONLY_LATEST_APPROVED_BUNDLE_TEMPLATE;


	@Override
	public BundleTemplateModel findBundleTemplateById(final String bundleId)
	{
		validateParameterNotNullStandardMessage("bundleId", bundleId);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_BUNDLETEMPLATE_QUERY);
		query.addQueryParameter("uid", bundleId);
		return searchUnique(query);
	}

}
