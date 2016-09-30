/**
 *
 */
package com.hybris.showcase.guidedselling.daos.impl;

import de.hybris.platform.configurablebundleservices.daos.impl.AbstractOrderEntryDao;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.type.TypeModel;


/**
 * @author lmachnik
 *
 */
public class DefaultOrderEntryDao extends AbstractOrderEntryDao<OrderModel, OrderEntryModel>
{

	@Override
	public PK getItemType()
	{
		final TypeModel typeModel = getTypeService().getTypeForCode(OrderEntryModel._TYPECODE);
		return typeModel.getPk();
	}

}
