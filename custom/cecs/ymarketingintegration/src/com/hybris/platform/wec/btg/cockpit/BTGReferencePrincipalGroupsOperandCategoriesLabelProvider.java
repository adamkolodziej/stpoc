package com.hybris.platform.wec.btg.cockpit;

import de.hybris.platform.btg.model.BTGReferencePrincipalGroupsOperandModel;
import de.hybris.platform.btgcockpit.service.label.AbstractBTGItemCollectionLabelProvider;

import java.util.Collection;

import com.hybris.showcase.cecs.ymarketingintegration.btg.model.BTGReferenceSAPCategoryOperandModel;


/**
 *
 * @author marius.bogdan.ionescu@sap.com
 *
 *         Provides a text representation for given {@link BTGReferencePrincipalGroupsOperandModel}
 */
public class BTGReferencePrincipalGroupsOperandCategoriesLabelProvider
		extends AbstractBTGItemCollectionLabelProvider<BTGReferenceSAPCategoryOperandModel, String>
{
	@Override
	protected Collection<String> getItemObjectCollection(final BTGReferenceSAPCategoryOperandModel item)
	{
		return item.getCategories();
	}

	@Override
	protected String getItemObjectName(final String itemObject)
	{
		return itemObject;
	}

	@Override
	protected String getMessagePrefix()
	{
		return "categories";
	}
}
