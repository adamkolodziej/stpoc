package com.hybris.showcase.populators;

import com.hybris.showcase.data.ContractData;
import com.hybris.showcase.model.ContractModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;


public class BasicContractPopulator implements Populator<ContractModel, ContractData>
{

	@Override
	public void populate(final ContractModel source, final ContractData target)
	{
		target.setCode(source.getCode());
		target.setUserUid(source.getUser().getUid());

		int noOfServices = 0;
		for (final AbstractOrderEntryModel entry : source.getOrderEntries())
		{
			if (entry.getProduct() instanceof SubscriptionProductModel)
			{
				noOfServices++;
			}
		}
		target.setNoOfServices(noOfServices);
	}

}
